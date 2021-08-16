package volmbot.commands;

import volmbot.commands.eco.*;
import volmbot.util.VolmitCommand;

public class Eco extends VolmitCommand {
    // Constructor
    public Eco() {
        super(
                "eco",
                new String[]{},
                new String[]{}, // Add role name here. Empty: always / 1+: at least one.
                "Economy Category",
                true,
                "Eco <subcommand>",
                new VolmitCommand[]{
                        new Give(),
                        new Set(),
                        new Take(),
                }
        );
    }
}