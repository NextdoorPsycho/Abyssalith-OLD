package volmbot.toolbox;

import art.arcane.quill.cache.AtomicCache;
import art.arcane.quill.execution.J;
import art.arcane.quill.io.FileWatcher;
import art.arcane.quill.io.IO;
import art.arcane.quill.json.JSONObject;
import art.arcane.quill.logging.L;
import com.google.gson.Gson;
import lombok.NonNull;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Toolkit extends ListenerAdapter {
    // Set from config
    public static String ModRole = "Support";
    public static String AdminRole = "Admin";
    public static String Company = "Volmit Software";
    public static String BotGIF = "https://media.giphy.com/media/mJAUdQb73H8zdPhCeI/giphy.gif";
    public static String BotColor = "0xFFFF00";
    public static String BotToken = "ODc1OTczMTYxODkwNTA4ODMw.YRdTlA.CbQTuIkYBIPjKv6vZQXWJPrcb7g";
    public static String BotOwnerID = "";//Leave blank change in config
    public static String BotPrefix = ".";
    public static List<String> owo = Arrays.asList("OwO", "owo", "uwu", "ÒwÓ", "□w□", "●w●", "Owo", "owO", "♡w♡", "**OWO GET INTO MY PANTS NOW,  AND NUZZLE ME**");
    // Set from main class
    public static Long botID;
    public static User botUser;
    public static String botName;

    // Used for hot-loading and config
    private static final FileWatcher fw = new FileWatcher(getFile());
    private static  AtomicCache<Toolkit> instance = new AtomicCache<>();

    public void save() {
        File file = getFile();
        file.getParentFile().mkdirs();

        J.attempt(() -> IO.writeAll(file, new JSONObject(new Gson().toJson(this)).toString(4)));
    }

    public static void tick(){
        if (fw.checkModified()) {

            instance = new AtomicCache<>();
            L.v("Hot-loaded Config");
        }
    }
    public static Toolkit get() {
        return instance.aquire(() -> {
            File f = getFile();
            f.getParentFile().mkdirs();
            Toolkit dummy = new Toolkit();

            if (!f.exists()) {
                dummy.save();
            }
            try {
                return new Gson().fromJson(IO.readAll(f), Toolkit.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dummy;
        });
    }

    private static File getFile() {
        return new File("config/toolkit.json");
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if (!e.getMessage().getAuthor().isBot()) {
            get();
        }
    }
}
