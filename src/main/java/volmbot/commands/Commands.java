package volmbot.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.toolbox.Toolkit;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.ArrayList;
import java.util.List;

public class Commands extends VolmitCommand {

    // Commands stored
    private VolmitCommand[] botCommands = null;

    // Constructor
    public Commands(JDA jda) {
        super(
                "commands",
                new String[]{"help"},
                new String[]{}, // Always permitted if empty. User must have at least one if specified.
                "Sends the command help page (this one)",
                false,
                null
        );
        setCommands(processCMDs(jda));
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {

        // Init embed
        VolmitEmbed embed = new VolmitEmbed("The Abyssalith - " + Toolkit.get().botName + " Info Page!", e.getMessage());

        // Add explanation
        embed.addField(
                "All commands you can use",
                Toolkit.get().BotPrefix + "<command> for more help on the command",
                false
        );

        // Loop over and add all commands with their respective information
        for (VolmitCommand command : botCommands) {
            String cmd = Toolkit.get().BotPrefix + command.getName().substring(0, 1).toUpperCase() + command.getName().substring(1);
            if (command.getCommands().size() < 2) {
                embed.addField(cmd, "`*no aliases*`\n" + command.getDescription(), true);
            } else {
                StringBuilder body = new StringBuilder();
                body
                        .append("\n`")
                        .append(Toolkit.get().BotPrefix)
                        .append(
                                command.getCommands().size() == 2 ?
                                        command.getCommands().get(1) :
                                        "" + command.getCommands().subList(1, command.getCommands().size()).toString()
                                                .replace("[", "").replace("]", "")
                        )
                        .append("`\n")
                        .append(command.getDescription())
                        .append(command.getExample() != null ? "\n**Usage**\n" + command.getExample() : "");
                if (command.getRoles() != null && command.getRoles().size() != 0) {
                    if (command.getRoles().size() == 1) {
                        body.append("\n__Required:__ ").append(command.getRoles().get(0));
                    } else {
                        body.append("\n__Required:__ ").append(command.getRoles().toString()
                                .replace("[", "").replace("]", ""));
                    }
                }
                embed.addField(
                        cmd,
                        body.toString(),
                        true
                );
            }
        }

        // Send the embed
        embed.send(e.getMessage(), true, 1000);
    }

    /// Other functions
    // Sets the commands
    public void setCommands(List<VolmitCommand> commands) {
        botCommands = commands.toArray(new VolmitCommand[0]);
    }

    // Gets all listeners of the specified JDA
    public List<VolmitCommand> processCMDs(JDA jda) {
        List<VolmitCommand> foundCommands = new ArrayList<>();
        jda.getRegisteredListeners().forEach(c -> {

            if (c instanceof VolmitCommand && c.getClass().getPackageName().contains(".commands")) {
                foundCommands.add((VolmitCommand) c);
            }
        });
        foundCommands.add(this);
        return foundCommands;
    }
}
