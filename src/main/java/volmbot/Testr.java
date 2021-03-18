package volmbot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.List;

public class Testr extends VolmitCommand {
    public Testr() {
        super(
                "Testr",
                new String[]{"testr", "test", "tst"},
                null,
                "Runs some tests",
                false,
                null
        );
    }
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e){
        VolmitEmbed embed = new VolmitEmbed("Testr", e.getMessage());
        embed.send();
    }
}
