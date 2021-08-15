package volmbot.commands.eco;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.toolbox.UserDirector;
import volmbot.util.Econator;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.List;

public class Take extends VolmitCommand {
    // Constructor
    public Take() {
        super(
                "EcoRemove", //Name
                new String[]{"er","etake","eremove","et"}, //Alias's
                new String[]{"ADMINISTRATOR"}, // Always permitted if empty. User must have at least one if specified.
                "Takes a Number of money to the user", // Description
                true, // Does it use Args
                "eg 100" //Example - the prefix
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        VolmitEmbed embed = new VolmitEmbed("Transaction Receipt!", e.getMessage());
        embed.addField("Money Removed: ", args.get(1) + " Removed By: " + e.getAuthor().getAsMention(), false);
        Econator.Sub(e.getMessage(), Integer.parseInt(args.get(1)));
        UserDirector m = UserDirector.load(e.getMessage().getMentionedMembers().get(0).getIdLong());
        embed.addField("New Total For " + e.getMessage().getMentionedMembers().get(0).getEffectiveName() + ": ", m.getMoney(), false);
        embed.send(e.getMessage(), true, 1000);
    }
}
