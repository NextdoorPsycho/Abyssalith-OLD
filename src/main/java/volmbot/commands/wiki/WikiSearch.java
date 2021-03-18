package volmbot.commands.wiki;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.Main;
import volmbot.util.VolmitCommand;

import java.util.List;

public class WikiSearch extends VolmitCommand {
    // Constructor
    public WikiSearch() {
        super(
                "Search",
                new String[]{"search", "src", "s", "?"},
                new String[]{}, // Add role name here. Empty: always / 1+: at least one.
                "Finds something on the wiki",
                true, // Weather command needs arguments or not
                "wiki search <plugin> <query> [amount]"
                // For an example for a category, see command.Wiki
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        Main.info("Wiki search ran!");
    }
}
