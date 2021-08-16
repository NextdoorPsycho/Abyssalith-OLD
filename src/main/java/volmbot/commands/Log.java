package volmbot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.List;

public class Log extends VolmitCommand {
    // Constructor
    public Log() {
        super(
                "log",
                new String[]{"log","l"},
                new String[]{}, // Always permitted if empty. User must have at least one if specified.
                "Gets the log message reply",
                false,
                null
        );
    }
    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        VolmitEmbed embed = new VolmitEmbed("**WHAT IS A LOG?**", e.getMessage());
        embed.setDescription("This message was sent because we are asking for a log, and you don't know how to get one, or sent something that is not a log. *If you are worried about privacy you can have a private thread for support, just ask the support team and we can get that setup for you!*");
        embed.addField("*__Why do we ask for Logs__*",
                "**1:** so we can see what  the actual problem is.\n" +
                "**2:**  so we can check what parts are failing\n" +
                "**3:**  to see Versions for Java, Server, And Plugins\n" +
                "**4:**  Other Reasons", false);
        embed.addField("What **__NOT__** to do",
                "**-**  Send us a snippet of error codes, you probably don't know why we ask.\n" +
                "**-**  Send images, people on phones cant read it\n" +
                "**-**  Removing information You don't know more about the errors than we do", false);
        embed.addField("How to get a log",
                "**-**  The `latest.log`  file from your server's Log folder\n" +
                "**-**  Go to <https://pastebin.com/> And paste that file there.\n" +
                "**-**  Alternatively <https://mclo.gs/> and paste it there.\n" +
                "**-**  Or just send the file", false);

        //Commands
        //embed.addField("Name Here", "" + "Value here", false);
        embed.send(e.getMessage(), true, 1000);

    }
}