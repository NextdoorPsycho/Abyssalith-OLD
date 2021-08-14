package volmbot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;
import java.util.List;
public class Ping extends VolmitCommand {
    // Constructor
    public Ping() {
        super(
                "ping",
                new String[]{"links", "link"},
                new String[]{}, // Always permitted if empty. User must have at least one if specified.
                "Sends useful links (like the wiki)",
                false,
                null
        );
    }
    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        VolmitEmbed embed = new VolmitEmbed(" Here you go!", e.getMessage());
        //Commands
        embed.addField("Name Here", "" + "Value here", false);
        embed.send(e.getMessage(), true, 1000);
    }
}