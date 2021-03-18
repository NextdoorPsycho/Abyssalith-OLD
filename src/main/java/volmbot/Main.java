package volmbot;

import art.arcane.quill.execution.Looper;
import lombok.Getter;
import lombok.NonNull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import volmbot.commands.Shutdown;
import volmbot.commands.*;
import volmbot.listeners.AutoWiki;
import volmbot.listeners.TZHeartListener;
import volmbot.listeners.OwOListener;
import volmbot.listeners.Prefix;
import volmbot.toolbox.Toolkit;
import volmbot.util.IndexedWiki;

import javax.security.auth.login.LoginException;
import java.net.URL;
import java.net.http.WebSocket;
import java.util.Objects;


public class Main extends ListenerAdapter {

    public static final Logger LOGGER = LoggerFactory.getLogger(WebSocket.Listener.class);
    @Getter
    private static JDA jda;

    // BOT BUILDER BOYS
    public static void main(String[] args) throws LoginException {

        // Status
        LOGGER.info("Loading main class...");

        // Load toolkit definitions
        Toolkit.get();

        // Load wikis
        LOGGER.info(IndexedWiki.loadAll());

        // Build JDA
        jda = JDABuilder.createDefault(Toolkit.BotToken).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.watching("over Volmit: `!help`"));
        //jda.getSelfUser().getManager().setName(Toolkit.BotName);
        try {
            // TODO: Fix this
            jda.getSelfUser().getManager().setAvatar(Icon.from(new URL(Toolkit.BotGIF).openStream()));
        } catch (Exception e) {
            e.printStackTrace();
            Main.error("Issue when getting avatar icon");
        }

        // Save user and name
        Toolkit.botID = jda.getSelfUser().getIdLong();
        Toolkit.botUser = jda.getUserById(Toolkit.botID);
        Toolkit.botName = Objects.requireNonNull(Toolkit.botUser).getName();

        /// Listener Registrar
        // Log incoming messages
        jda.addEventListener(new Main());

        // Listeners
        jda.addEventListener(new Toolkit());
        jda.addEventListener(new OwOListener());
        jda.addEventListener(new AutoWiki());
        jda.addEventListener(new Prefix());
        jda.addEventListener(new TZHeartListener());

        // Commands
        jda.addEventListener(new Links());
        jda.addEventListener(new Poll());
        jda.addEventListener(new Log());
        jda.addEventListener(new Wiki());
        jda.addEventListener(new Shutdown());
        jda.addEventListener(new Testr());
        jda.addEventListener(new Commands(jda)); // This one MUST be last

        new Looper() {
            @Override
            protected long loop() {
                Toolkit.tick();
                return 1000;
            }
        }.start();
    }



    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getAuthor().isBot()) {
            // Updates configurations
            Toolkit.get();
            Main.LOGGER.info(e.getAuthor().getName() + ": " + e.getMessage().getContentDisplay());
            if (Shutdown.checkOverrideAdmin){
                if (e.getMessage().getContentRaw().contains("yes I am")) {
                    e.getMessage().delete().queue();
                    shutdown();
                }
                if (!e.getMessage().getContentDisplay().contains("shutdown") && !e.getMessage().getContentDisplay().contains("stop")){
                    Shutdown.checkOverrideAdmin = false;
                }
            }
        }
    }
    @Override
    public void onReady(@NonNull ReadyEvent e) {
        LOGGER.info("{} IS FUCKING READY", e.getJDA().getSelfUser().getAsTag());
    }

    public static void shutdown(){
        jda.getPresence().setStatus(OnlineStatus.OFFLINE);
        jda.shutdown();
        System.exit(1);
    }

    public static void warn(String message){
        LOGGER.warn(" \\/ {}", message);
    }

    public static void info(String message){
        LOGGER.info(" \\/ {}", message);
    }

    public static void error(String message){
        LOGGER.error(" \\/ {}", message);
    }

    public static void debug(String message){
        LOGGER.debug(" \\/ {}", message);
    }
}

