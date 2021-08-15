package volmbot.toolbox;

import art.arcane.quill.cache.AtomicCache;
import art.arcane.quill.execution.J;
import art.arcane.quill.io.FileWatcher;
import art.arcane.quill.io.IO;
import art.arcane.quill.json.JSONObject;
import art.arcane.quill.logging.L;
import com.google.gson.Gson;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import volmbot.Main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Toolkit extends ListenerAdapter {
    // Set from config
    public String ModRole;//Leave blank change in config
    public String AdminRole;//Leave blank change in config
    public String MoneyName;//Leave blank change in config
    public String MoneyEmoji;//Leave blank change in config
    public String Company;//Leave blank change in config
    public String BotGIF;//Leave blank change in config
    public String BotColor;//Leave blank change in config
    public String BotToken;//Leave blank change in config
    public String BotOwnerID;//Leave blank change in config
    public String BotPrefix;//Leave blank change in config
    public List<String> owo = Arrays.asList("OwO", "owo", "uwu", "()w()", "OvO", "owO");
    // Set from main class
    public Long botID;
    public User botUser;
    public String botName;

    // Used for hot-loading and config
    private static final FileWatcher fw = new FileWatcher(getFile());
    private static AtomicCache<Toolkit> instance = new AtomicCache<>();

    public void save() {
        File file = getFile();
        file.getParentFile().mkdirs();

        J.attempt(() -> IO.writeAll(file, new JSONObject(new Gson().toJson(this)).toString(4)));
    }

    public static void tick() {
        if (fw.checkModified()) {

            instance = new AtomicCache<>();
            L.v("Hot-loaded Config");
            Main.getJDA();
        }
    }

    public static Toolkit get() {
        return instance.aquire(() -> {
            File f = getFile();
            System.out.println(f.getAbsolutePath());
            f.getParentFile().mkdirs();
            Toolkit dummy = new Toolkit();

            if (!f.exists()) {
                dummy.save();
            }
            try {
                Toolkit tk = new Gson().fromJson(IO.readAll(f), Toolkit.class);
                System.out.println(tk.BotToken);
                return tk;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dummy;
        });
    }

    private static File getFile() {
        return new File("config/toolkit.json");
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (!e.getMessage().getAuthor().isBot()) {
            get();
        }
    }
}
