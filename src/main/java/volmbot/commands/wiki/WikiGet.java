package volmbot.commands.wiki;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.util.IndexedWiki;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.List;

public class WikiGet extends VolmitCommand {
    // Constructor
    public WikiGet() {
        super(
                "Get",
                new String[]{"get", "getall", "all"},
                new String[]{}, // Add role name here. Empty: always / 1+: at least one.
                "Sends all wikis currently loaded",
                false, // Weather command needs arguments or not
                "wiki get"
                // For an example for a category, see command.Wiki
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        VolmitEmbed embed = new VolmitEmbed("Loaded wikis", e.getMessage());
        for (IndexedWiki wiki : IndexedWiki.getWikis()){
            embed.addField(wiki.getName(), "", true);
            embed.send();
        }
        if (IndexedWiki.getWikis().size() == 0){
            embed.setTitle("There are no loaded wikis.")
                    .setDescription("Perhaps you can create a new one?");
            embed.send();
            new WikiCreate().sendHelp(e.getMessage());
        }
    }
}
