package volmbot.commands.eco;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.toolbox.Toolkit;
import volmbot.toolbox.UserDirector;
import volmbot.util.Econator;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.List;

public class Set extends VolmitCommand {
    // Constructor
    public Set() {
        super(
                "ecoset", //Name
                new String[]{"ecoset","es","eset"}, //Alias's
                new String[]{"ADMINISTRATOR"}, // Always permitted if empty. User must have at least one if specified.
                "Sets the users balance", // Description
                true, // Does it use Args
                "*.es 10 @Psycho*" //Example - the prefix
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        String moneyName = Toolkit.get().MoneyName;
        String moneyEmoji = Toolkit.get().MoneyEmoji;

        VolmitEmbed embed = new VolmitEmbed("Transaction Receipt!", e.getMessage());
        embed.addField(moneyEmoji+ moneyName+ " set: ", args.get(1) + " Set By: " + e.getAuthor().getAsMention(), false);
        Econator.Set(e.getMessage(), Integer.parseInt(args.get(1)));
        UserDirector m = UserDirector.load(e.getMessage().getMentionedMembers().get(0).getIdLong());
        embed.addField("New Total For " + e.getMessage().getMentionedMembers().get(0).getEffectiveName() + ": ", m.getMoney(), false);
        embed.send(e.getMessage(), true, 1000);
    }
}
