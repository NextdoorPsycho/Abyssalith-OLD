package volmbot.commands.eco;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.commands.Ping;
import volmbot.toolbox.Director;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;
import java.util.List;
public class Give extends VolmitCommand {
    // Constructor
    public Give() {
        super(
                "Ecogive", //Name
                new String[]{"eg", "ecogive"}, //Alias's
                new String[]{}, // Always permitted if empty. User must have at least one if specified.
                "Gives a Number of money to the user", // Description
                true, // Does it use Args
                "eg 100" //Example - the prefix
        );
    }
    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        VolmitEmbed embed = new VolmitEmbed("Transaction Receipt!", e.getMessage());
        embed.addField("Money given: ", args.get(1) + " From: " + e.getAuthor().getAsMention(), false);
        long id = e.getMessage().getAuthor().getIdLong(); //TODO GET THE ACTUAL MENTION
        Director m =  Director.load(id); // Make a new Director for the User
        m.save(); // Save it because why not
        int money;
        try{
            money = Integer.parseInt(m.getMoney());  // Get stuff to int
            money = Math.addExact(money, Integer.parseInt(args.get(1))); // Add
            m.setMoney(String.valueOf(money)); // Set values
            System.out.println(money); // output debug
            m.save(); // Save
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        embed.addField("New Total For "+ id +": ", m.getMoney(), false);
        embed.send(e.getMessage(), true, 1000);
    }
}