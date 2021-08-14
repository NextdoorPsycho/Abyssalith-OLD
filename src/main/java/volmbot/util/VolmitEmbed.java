package volmbot.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import volmbot.Main;
import volmbot.toolbox.Toolkit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VolmitEmbed extends EmbedBuilder {
    private final Message message;

    /*
        Creates a new default VolmitEmbed object.
        String `title` - the title of the embed
        String `name` - the name greeted in the top of the embed
     */
    public VolmitEmbed(String title, String name) {
        this.message = null;
        this.setAuthor("Requested by: " + name, null)
                .setTitle(!title.equals("") ? title : "\u200E")
                .setColor(Color.decode(Toolkit.get().BotColor))
                .setFooter(Toolkit.get().Company, Toolkit.get().BotGIF);
    }

    /*
        Creates a new default VolmitEmbed object.
        String `title` - the title of the embed
        String `message` - the message used to greet the user with
     */
    public VolmitEmbed(String title, Message message) {
        this.message = message;
        this.setAuthor("Requested by: " + message.getAuthor().getName(), null, message.getAuthor().getAvatarUrl())
                .setTitle(!title.equals("") ? title : "\u200E")
                .setColor(Color.decode(Toolkit.get().BotColor))
                .setFooter(Toolkit.get().Company, Toolkit.get().BotGIF);
    }

    /*
        Creates a new default VolmitEmbed object.
        String `title` - the title of the embed
     */
    public VolmitEmbed(String title) {
        this.message = null;
        this.setTitle(title)
                .setColor(Color.decode(Toolkit.get().BotColor))
                .setFooter(Toolkit.get().Company, Toolkit.get().BotGIF);
    }

    /*
        Creates a new shortened VolmitEmbed object.
        String `title` - the title of the embed
        boolean `useShort` - toggles footer
     */
    public VolmitEmbed(String title, boolean useShort) {
        this.message = null;
        this.setTitle(title).setColor(Color.decode(Toolkit.get().BotColor));
        if (!useShort) {
            this.setFooter(Toolkit.get().Company, Toolkit.get().BotGIF);
        }
    }

    /*
        Creates a new default VolmitEmbed object.
        This has no title (unless set later)
     */
    public VolmitEmbed() {
        this.message = null;
        this.setColor(Color.decode(Toolkit.get().BotColor))
                .setFooter(Toolkit.get().Company, Toolkit.get().BotGIF);
    }

    // Send embed in the channel of the message already saved. Does not send if no message was specified.
    public void send() {
        this.send(this.message, null, false, 0);
    }

    // Send embed in the channel of the message already saved. Does not send if no message was specified. Adds reactions
    public void send(List<String> reactions) {
        this.send(this.message, null, false, 0, reactions);
    }

    // Send embed in `channel`
    public void send(TextChannel channel) {
        this.send(null, channel, false, 0);
    }

    // Send embed in `channel` with reactions `reactions`
    public void send(TextChannel channel, List<String> reactions) {
        this.send(null, channel, false, 0, reactions);
    }

    // Send embed in channel of `message`
    public void send(Message message) {
        this.send(message, false);
    }

    // Send embed in channel of `message` with reactions `reactions`
    public void send(Message message, List<String> reactions) {
        this.send(message, false, reactions);
    }


    // Send embed in channel of `message` and delete original if `deletesMSG`
    public void send(Message message, boolean deleteMSG) {
        this.send(message, deleteMSG, 0);
    }

    // Send embed in channel of `message` and delete original if `deletesMSG` with reactions `reactions`
    public void send(Message message, boolean deleteMSG, List<String> reactions) {
        this.send(message, deleteMSG, 0, reactions);
    }


    // Send embed in channel of `message` and delete original if `deleteMSG` after `deleteAfterMS`
    public void send(Message message, boolean deleteMSG, int deleteAfterMS) {
        this.send(message, null, deleteMSG, deleteAfterMS);
    }

    // Send embed in channel of `message` and delete original if `deleteMSG` after `deleteAfterMS` with reactions `reactions`
    public void send(Message message, boolean deleteMSG, int deleteAfterMS, List<String> reactions) {
        this.send(message, null, deleteMSG, deleteAfterMS, reactions);
    }


    // Send embed in channel of `message` (if null, send in `channel` instead), delete if `deleteMSG` after `deleteAfterMS`
    public void send(Message message, TextChannel channel, boolean deleteMSG, int deleteAfterMS) {
        send(message, channel, deleteMSG, deleteAfterMS, null);
    }

    // Send embed in channel of `message` (if null, send in `channel` instead), delete if `deleteMSG` after `deleteAfterMS`, with reactions `reactions`
    public void send(Message message, TextChannel channel, boolean deleteMSG, int deleteAfterMS, List<String> reactions) {
        if (reactions == null) reactions = new ArrayList<>();
        if (message == null && channel == null) {
            Main.error("No channel and message specified.");
        } else if (message != null) {
            List<String> finalReactions = reactions;
            message.getChannel().sendMessage(this.build()).queue(msg -> {
                for (String emoji : finalReactions) {
                    msg.addReaction(emoji).queue();
                }
            });
            if (deleteMSG) {
                message.delete().queueAfter(deleteAfterMS, TimeUnit.MILLISECONDS);
            }
        } else {
            List<String> finalReactions = reactions;
            channel.sendMessage(this.build()).queue(msg -> {
                for (String emoji : finalReactions) {
                    msg.addReaction(emoji).queue();
                }
            });
        }
    }
}