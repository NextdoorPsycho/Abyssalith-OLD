package volmbot.commands.eco;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.toolbox.UserDirector;
import volmbot.util.Econator;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.List;

public class Give extends VolmitCommand {
    // Constructor
    public Give() {
        super(
                "Ecogive", //Name
                new String[]{"eg", "ecogive","eadd"}, //Alias's
                new String[]{"ADMINISTRATOR"}, // Always permitted if empty. User must have at least one if specified.
                "Gives a Number of money to the user", // Description
                true, // Does it use Args
                "eg 100" //Example - the prefix
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        System.out.println("Made it here...");
        VolmitEmbed embed = new VolmitEmbed("Transaction Receipt!", e.getMessage());
        embed.addField("Money given: ", args.get(1) + " From: " + e.getAuthor().getAsMention(), false);
        Econator.Add(e.getMessage(), Integer.parseInt(args.get(1)));
        UserDirector m = UserDirector.load(e.getMessage().getMentionedMembers().get(0).getIdLong());
        embed.addField("New Total For " + e.getMessage().getMentionedMembers().get(0).getEffectiveName() + ": ", m.getMoney(), false);
        embed.send(e.getMessage(), true, 1000);
    }
}
