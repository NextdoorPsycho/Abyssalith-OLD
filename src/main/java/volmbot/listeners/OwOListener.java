package volmbot.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import volmbot.toolbox.Toolkit;

import java.util.concurrent.ThreadLocalRandom;

public class OwOListener extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (!e.getMessage().getAuthor().isBot()) {
            if (e.getMessage().getContentRaw().toLowerCase().contains("owo")) {
                int rand = ThreadLocalRandom.current().nextInt(10) % Toolkit.get().owo.size();
                String randomElement = Toolkit.get().owo.get(rand);
                e.getChannel().sendMessage(randomElement).queue();
            }
        }
    }
}