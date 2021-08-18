package volmbot.listeners;

import net.dv8tion.jda.api.entities.Role;
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

            if (!oMsg.getRoles().isEmpty()) {

                String[] unicodeEmoji = {"🇦", "🇧", "🇨", "🇩", "🇪", "🇫", "🇬", "🇭", "🇮", "🇯", "🇰", "🇱", "🇲", "🇳", "🇴", "🇵", "🇶", "🇷", "🇸", "🇹", "🇺", "🇻", "🇼", "🇽", "🇾", "🇿"};
                int ff = 0;
                for (String temp : unicodeEmoji) {
                    if (e.getReactionEmote().getEmoji().contentEquals(temp)) {
                        try {
                            String id = oMsg.getRoles().get(ff);
                            Role r = e.getGuild().getRoleById(id);
                            assert r != null;
                            e.getGuild().addRoleToMember(e.getUserIdLong(), r).queue();
                            Main.info("Added: " + r + " to " + e.getUserIdLong());
                            break;
                        } catch (IndexOutOfBoundsException ev) {
                            Main.info("had a strokE");
                        }
                        ff++;
                    } else {
                        ff++;
                    }
                }
            }
        }
    }

        public void onGuildMessageReactionRemove (GuildMessageReactionRemoveEvent e){
            long uid = e.getUserIdLong();
            if (Main.getJDA().getSelfUser().getIdLong() != uid) {
                long ReMessage = e.getMessageIdLong();
                ReactionRoleDirector oMsg = ReactionRoleDirector.load(ReMessage);

                if (!oMsg.getRoles().isEmpty()) {

                    String[] unicodeEmoji = {"🇦", "🇧", "🇨", "🇩", "🇪", "🇫", "🇬", "🇭", "🇮", "🇯", "🇰", "🇱", "🇲", "🇳", "🇴", "🇵", "🇶", "🇷", "🇸", "🇹", "🇺", "🇻", "🇼", "🇽", "🇾", "🇿"};
                    int ff = 0;
                    for (String temp : unicodeEmoji) {
                        if (e.getReactionEmote().getEmoji().contentEquals(temp)) {
                            try {
                                String id = oMsg.getRoles().get(ff);
                                Role r = e.getGuild().getRoleById(id);
                                assert r != null;
                                e.getGuild().removeRoleFromMember(uid, r).queue();
                                Main.info("Removed: " + r + " to " + uid);
                                break;

                            } catch (IndexOutOfBoundsException ev) {
                                Main.info("Someone reacted to a role message with no role that they need");
                            }
                        }
                        ff++;
                    }
                }

            }
        }
    }
