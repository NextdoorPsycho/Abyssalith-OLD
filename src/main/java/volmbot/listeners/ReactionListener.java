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
        Role role = null;//ADD THE ROLE HERE
        MessageReaction.ReactionEmote emote = e.getReactionEmote();
        TextChannel oChannel = e.getChannel();

        String[] unicodeEmoji = {"\uD83C\uDDE6", "\uD83C\uDDE7", "\uD83C\uDDE8", "\uD83C\uDDE9", "\uD83C\uDDEA", "\uD83C\uDDEB", "\uD83C\uDDEC", "\uD83C\uDDED", "\uD83C\uDDEE", "\uD83C\uDDEF", "\uD83C\uDDF0", "\uD83C\uDDF1", "\uD83C\uDDF2", "\uD83C\uDDF3", "\uD83C\uDDF4", "\uD83C\uDDF5", "\uD83C\uDDF6", "\uD83C\uDDF7", "\uD83C\uDDF8", "\uD83C\uDDF9", "\uD83C\uDDFA", "\uD83C\uDDFB", "\uD83C\uDDFC", "\uD83C\uDDFD", "\uD83C\uDDFE", "\uD83C\uDDFF"};
        String[] emoji = {":regional_indicator_a:", ":regional_indicator_b:", ":regional_indicator_c:", ":regional_indicator_d:", ":regional_indicator_e:", ":regional_indicator_f:", ":regional_indicator_g:", ":regional_indicator_h:", ":regional_indicator_i:", ":regional_indicator_j:", ":regional_indicator_k:", ":regional_indicator_l:", ":regional_indicator_m:", ":regional_indicator_n:", ":regional_indicator_o:", ":regional_indicator_p:", ":regional_indicator_q:", ":regional_indicator_r:", ":regional_indicator_s:", ":regional_indicator_t:", ":regional_indicator_u:", ":regional_indicator_v:", ":regional_indicator_w:", ":regional_indicator_x:", ":regional_indicator_y:", ":regional_indicator_z:"};

        System.out.println(emote.getAsReactionCode());
        if (!emote.isEmote()){
            oChannel.sendMessage("i saw that! " + "`"+ emote + "`"+ "`"+ emote.getAsReactionCode() + "`"+ "`"+ emote.getEmoji() + "`").queue(message -> {



            message.addReaction(emote.getEmote()).queue();
//            message.addReaction(reaction3).queue();
            });
        }


    }
    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent e)
    {
        Role role = null;//ADD THE ROLE HERE
        TextChannel oChannel = e.getChannel();
        MessageReaction.ReactionEmote emote = e.getReactionEmote();

        oChannel.sendMessage("i saw that! " + "`"+ emote + "`").queue();
    }
}
