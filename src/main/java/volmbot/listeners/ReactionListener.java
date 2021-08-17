package volmbot.listeners;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import volmbot.toolbox.ReactionRoleDirector;

public class ReactionListener extends ListenerAdapter {

    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent e) {
        if (!e.getUser().isBot()) {
            long ReMessage = e.getMessageIdLong();
            ReactionRoleDirector oMsg = ReactionRoleDirector.load(ReMessage);

            if (!oMsg.getRoles().get(0).isEmpty()) {

                String[] unicodeEmoji = {"🇦", "🇧", "🇨", "🇩", "🇪", "🇫", "🇬", "🇭", "🇮", "🇯", "🇰", "🇱", "🇲", "🇳", "🇴", "🇵", "🇶", "🇷", "🇸", "🇹", "🇺", "🇻", "🇼", "🇽", "🇾", "🇿"};
                int ff = 0;
                for (String temp : unicodeEmoji) {
                    if (e.getReactionEmote().getEmoji().contentEquals(temp)) {
                        String id = oMsg.getRoles().get(ff);
                        Role r = e.getGuild().getRoleById(id);
                        assert r != null;
                        e.getGuild().addRoleToMember(e.getUserIdLong(), r).queue();
                        System.out.println("Added: " + r + " to " + e.getUserIdLong());
                        break;
                    } else {
                        System.out.println("Not a valid emoji!");
                    }
                    ff++;
                }
            }
        }

    }
    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent e)
    {
        if (!e.getUser().isBot()) {
            long ReMessage = e.getMessageIdLong();
            ReactionRoleDirector oMsg = ReactionRoleDirector.load(ReMessage);

            if (!oMsg.getRoles().get(0).isEmpty()) {

                String[] unicodeEmoji = {"🇦", "🇧", "🇨", "🇩", "🇪", "🇫", "🇬", "🇭", "🇮", "🇯", "🇰", "🇱", "🇲", "🇳", "🇴", "🇵", "🇶", "🇷", "🇸", "🇹", "🇺", "🇻", "🇼", "🇽", "🇾", "🇿"};
                int ff = 0;
                for (String temp : unicodeEmoji) {
                    if (e.getReactionEmote().getEmoji().contentEquals(temp)) {
                        String id = oMsg.getRoles().get(ff);
                        Role r = e.getGuild().getRoleById(id);
                        assert r != null;
                        e.getGuild().removeRoleFromMember(e.getUserIdLong(), r).queue();
                        System.out.println("Removed: " + r + " to " + e.getUserIdLong());
                        break;
                    } else {
                        System.out.println("Not a valid emoji!");
                    }
                    ff++;
                }
            }
        }
    }
}
