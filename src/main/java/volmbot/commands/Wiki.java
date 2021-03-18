package volmbot.commands;

import volmbot.commands.wiki.*;
import volmbot.util.VolmitCommand;


public class Wiki extends VolmitCommand {
    // Constructor
    public Wiki() {
        super(
                "Wiki",
                new String[]{"Wiki", "w"},
                new String[]{}, // Add role name here. Empty: always / 1+: at least one.
                "Wiki command category",
                true,
                "wiki <subcommand>",
                new VolmitCommand[]{
                        new WikiIndex(),
                        new WikiSearch(),
                        new WikiCreate(),
                        new WikiUpdated(),
                        new WikiGet()
                }
        );
    }
}