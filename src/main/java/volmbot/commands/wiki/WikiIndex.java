package volmbot.commands.wiki;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.Main;
import volmbot.util.IndexedWiki;
import volmbot.util.VolmitCommand;

import java.util.List;

public class WikiIndex extends VolmitCommand {
    // Constructor
    public WikiIndex() {
        super(
                "Index",
                new String[]{"index", "ind", "i", "update"},
                null, // Add role name here. Empty: always / 1+: at least one.
                "Prints a full wiki index.",
                true,
                "wiki index <plugin>"
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        IndexedWiki.getWikis().forEach(wiki -> {
            //if wiki.getName()
        });
    }
}
