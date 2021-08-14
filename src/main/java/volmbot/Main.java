package volmbot;

import art.arcane.quill.execution.Looper;
import lombok.NonNull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import volmbot.commands.Shutdown;
import volmbot.commands.*;
import volmbot.listeners.AutoWiki;
import volmbot.listeners.OwOListener;
import volmbot.listeners.Prefix;
import volmbot.toolbox.Toolkit;

import javax.security.auth.login.LoginException;
import java.net.http.WebSocket;
import java.util.Objects;


public class Main extends ListenerAdapter {


    public static final Logger LOGGER = LoggerFactory.getLogger(WebSocket.Listener.class);


    public static final IBotProvider provider = new BotProvider();


    // BOT BUILDER BOYS
    public static JDA getJDA() {
        return provider.get().getJDA();
    }

    public static void main(String[] args) throws LoginException {
        org.slf4j.simple.SimpleServiceProvider.class.getSimpleName();
        // Status
        LOGGER.info("Initializing");


        Toolkit.get().botID = getJDA().getSelfUser().getIdLong();
        Toolkit.get().botUser = getJDA().getUserById(Toolkit.get().botID);
        Toolkit.get().botName = Objects.requireNonNull(Toolkit.get().botUser).getName();

        /// Listener Registrar
        // Log incoming messages
        getJDA().addEventListener(new Main());

        // Listeners
        getJDA().addEventListener(new Toolkit());
        getJDA().addEventListener(new OwOListener());
        getJDA().addEventListener(new AutoWiki());
        getJDA().addEventListener(new Prefix());

        // Commands
        getJDA().addEventListener(new Links());
        getJDA().addEventListener(new Ping());
        getJDA().addEventListener(new Poll());
        getJDA().addEventListener(new Log());
        getJDA().addEventListener(new Shutdown());
        getJDA().addEventListener(new Commands(getJDA())); // This one MUST be last

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
            if (Shutdown.checkOverrideAdmin) {
                if (e.getMessage().getContentRaw().contains("yes I am")) {
                    e.getMessage().delete().queue();
                    shutdown();
                }
                if (!e.getMessage().getContentDisplay().contains("shutdown") && !e.getMessage().getContentDisplay().contains("stop")) {
                    Shutdown.checkOverrideAdmin = false;
                }
            }
        }
    }

    @Override
    public void onReady(@NonNull ReadyEvent e) {
        LOGGER.info("{} IS WATCHING THE UNIVERSE", e.getJDA().getSelfUser().getAsTag());
    }

    public static void shutdown() {
        getJDA().getPresence().setStatus(OnlineStatus.OFFLINE);
        getJDA().shutdown();
        System.exit(1);
    }

    public static void warn(String message) {
        LOGGER.warn(" \\/ {}", message);
    }

    public static void info(String message) {
        LOGGER.info(" \\/ {}", message);
    }

    public static void error(String message) {
        LOGGER.error(" \\/ {}", message);
    }

    public static void debug(String message) {
        LOGGER.debug(" \\/ {}", message);
    }
}

