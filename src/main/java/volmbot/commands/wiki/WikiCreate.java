package volmbot.commands.wiki;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.util.IndexedWiki;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.List;

public class WikiCreate extends VolmitCommand {
    // Constructor
    public WikiCreate() {
        super(
                "Create",
                new String[]{"create", "c", "+", "new"},
                new String[]{}, // Add role name here. Empty: always / 1+: at least one.
                "Create a new wiki",
                true, // Weather command needs arguments or not
                "wiki create <wikiname> <raw GitHub path>"
                // For an example for a category, see command.Wiki
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        VolmitEmbed embed = new VolmitEmbed("Wiki Creation", e.getMessage());
        if (args.size() < 3){
            e.getMessage().reply("You did not specify the name and/or github path");
            this.sendHelp(e.getMessage());
            return;
        }
        embed.setDescription("Wiki creation returned: " +
                new IndexedWiki(args.get(0), args.get(1), true).getCreationInfo()
        );
        embed.send();
    }
}