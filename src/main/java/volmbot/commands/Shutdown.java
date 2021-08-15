package volmbot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.Main;
import volmbot.toolbox.Toolkit;
import volmbot.util.VolmitCommand;

import java.util.List;

public class Shutdown extends VolmitCommand {
    public static boolean checkOverrideAdmin = false;

    // Constructor
    public Shutdown() {
        super(
                "Stop",
                new String[]{"stop","shutdown",".s"},
                new String[]{}, // Add role name here. Empty: always / 1+: at least one.
                "Stops the Bot boi",
                false,
                null
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        String oidcheck = e.getMessage().getAuthor().getId();
        if (oidcheck.equals(Toolkit.get().BotOwnerID)) {
            Main.warn("KILLING BOT");
            e.getMessage().delete().queue();
            Main.shutdown();
        } else {
            e.getChannel().sendMessage("uR noT my DAddY!").queue();
            checkOverrideAdmin = true;
        }
    }
}
