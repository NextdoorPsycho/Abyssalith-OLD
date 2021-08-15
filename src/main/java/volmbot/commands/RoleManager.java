package volmbot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.Arrays;
import java.util.List;


public class RoleManager extends VolmitCommand {
    // Constructor
    public RoleManager() {
        super(
                "reactionrole",
                new String[]{"reactionrole", "rr"},
                new String[]{}, // Always permitted if empty. User must have at least one if specified.
                "This is the reaction Role command!",
                false,
                ".rr :hearts:@role1 :coin:@role2"
        );
    }
    // Handle
    @Override
    public void handle(List<String> fff, GuildMessageReceivedEvent e) {
        long channel = e.getMessage().getChannel().getIdLong();
        String d;
        String[] a = e.getMessage().getContentRaw().split("@", 2);
        System.out.println(Arrays.toString(a));
        VolmitEmbed embed = new VolmitEmbed();

        for (String j : a) {
            String trole;
            String temoji;
        }



        embed.setTitle("Reaction Role Page!");





        embed.addField("Please react to a role:", "", true);
        embed.send(e.getMessage(), true, 1000);

    }
}