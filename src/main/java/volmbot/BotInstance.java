package volmbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotInstance {
    private final JDA jda;

    public BotInstance(String s) throws LoginException {
        System.out.println("Creating a bot with " + s);
        jda = JDABuilder.createDefault(s).build();
    }

    public void close() {
        System.out.println("Closing a bot with ");
        jda.shutdown();
    }

    public JDA getJDA() {
        return jda;
    }
}
