package volmbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class BotInstance {
    private final JDA jda;

    public BotInstance(String s) throws LoginException {
        jda = JDABuilder.createDefault(s).build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.watching("The Universe"));
    }
    public void close() {
        System.out.println("Closing a bot with ");
        jda.shutdown();
    }

    public JDA getJDA() {
        return jda;
    }
}
