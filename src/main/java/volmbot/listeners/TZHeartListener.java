package volmbot.listeners;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class TZHeartListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getGuild().getId().equals("624052215711006770")) {
            if (e.getMessage().getContentRaw().contains(":hearts:")) {
                Emote emote;
                try {
                    emote = e.getGuild().getEmotesByName("bancat", true).get(0);
                } catch (IndexOutOfBoundsException ex) {
                    //Oof
                    emote = null;
                }
                if (emote != null)
                    e.getMessage().addReaction(emote).complete();
            }
        }
    }
}