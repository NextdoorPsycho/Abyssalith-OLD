package volmbot.listeners;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import volmbot.util.VolmitEmbed;

public class AutoWiki extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String message = e.getMessage().getContentRaw().toLowerCase();
        if (e.getAuthor().isBot()) return;
        VolmitEmbed embed = new VolmitEmbed("", e.getMessage());
        boolean something = true;


        //Iris-help
        if (message.contains("need") && message.contains("help") && message.contains("iris") && !message.contains("react")) {
            embed.setTitle("Have you seen the wiki?", "https://docs.volmit.com/iris/getting-started>")
                    .setDescription("<https://docs.volmit.com/iris/getting-started>");
        } else if (message.contains("get") && message.contains("help") && message.contains("iris") && !message.contains("react")) {
            embed.setTitle("Have you seen the wiki?", "https://docs.volmit.com/iris/getting-started>")
                    .setDescription("<https://docs.volmit.com/iris/getting-started>");
        }


        //React-help
        else if (message.contains("need") && message.contains("help") && message.contains("react")) {
            embed.setTitle("Have you seen the wiki?", "https://docs.volmit.com/react/getting-started>")
                    .setDescription("<https://docs.volmit.com/react/getting-started>");
        } else if (message.contains("get") && message.contains("help") && message.contains("react")) {
            embed.setTitle("Have you seen the wiki?", "https://docs.volmit.com/react/getting-started>")
                    .setDescription("<https://docs.volmit.com/react/getting-started>");
        }


        //Any-help
        else if (message.contains("need") && message.contains("help") && message.contains("i")) {
            embed.setTitle("What do you need help with?");
        } else if (message.contains("get") && message.contains("help") && message.contains("i")) {
            embed.setTitle("What do you need help with?");
        } else {
            something = false;
        }

        // Send the embed
        if (something) embed.send(e.getChannel());
    }
}
