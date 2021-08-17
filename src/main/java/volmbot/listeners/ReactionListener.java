package volmbot.listeners;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import volmbot.Main;
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
                        Main.info("Added: " + r + " to " + e.getUserIdLong());
                        break;
                    }
                    ff++;
                }
            }
        }

    }
    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent e)
    {
        long uid = e.getUserIdLong();
        if (Main.getJDA().getSelfUser().getIdLong() != uid) {
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
                        e.getGuild().removeRoleFromMember(uid, r).queue();
                        Main.info("Removed: " + r + " to " + uid);
                        break;
                    }
                    ff++;
                }
            }
        }
    }
}
