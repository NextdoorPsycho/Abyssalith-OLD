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
                new String[]{"ping","hello","p"},
                new String[]{}, // Always permitted if empty. User must have at least one if specified.
                "Useful for Pings!",
                false,
                null
        );
    }
    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        VolmitEmbed embed = new VolmitEmbed(" PONG!", e.getMessage());
        //Commands
        //embed.addField("Name Here", "" + "Value here", false);
        embed.send(e.getMessage(), true, 1000);

    }
}