package volmbot.commands.wiki;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.util.IndexedWiki;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.List;

public class WikiUpdated extends VolmitCommand {
    // Constructor
    public WikiUpdated() {
        super(
                "Updated",
                new String[]{"updated", "when", "lastupdate"},
                new String[]{}, // Add role name here. Empty: always / 1+: at least one.
                "Returns the date and time of the last wiki update",
                true, // Weather command needs arguments or not
                "wiki updated <name>"
                // For an example for a category, see command.Wiki
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        VolmitEmbed embed = new VolmitEmbed("Wiki Updated on:", e.getMessage());
        for (IndexedWiki wiki : IndexedWiki.getWikis()){
            if (wiki.getName().equalsIgnoreCase(args.get(0))){
                embed.setDescription("Wiki last updated on: " + wiki.getUpdatedDate().toString());
                return;
            }
        }
        embed.setTitle("Cannot find that wiki")
                .setDescription("Please try entering the name again");
        embed.send();
    }
}