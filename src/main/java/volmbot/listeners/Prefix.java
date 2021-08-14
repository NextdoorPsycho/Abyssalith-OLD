package volmbot.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import volmbot.Main;
import volmbot.toolbox.Toolkit;
import volmbot.util.VolmitEmbed;

public class Prefix extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().contains(Main.getJDA().getSelfUser().getId())) {
            String Sender = e.getMessage().getAuthor().getName();
            VolmitEmbed embed = new VolmitEmbed("¯\\_(ツ)_/¯", e.getMessage());
            embed
                    .setAuthor("Hello " + Sender)
                    .setDescription("Everytime you @ me, it hurts... Use my prefix please.")
                    .addField("Here is my prefix", "`" + Toolkit.get().BotPrefix + "`", false);

            embed.send(e.getMessage(), true, 1000);
        }
    }
}
