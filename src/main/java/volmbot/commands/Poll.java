package volmbot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.toolbox.Toolkit;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.Arrays;
import java.util.List;

public class Poll extends VolmitCommand {

    private static final String[] unicodeEmoji = {"\uD83C\uDDE6", "\uD83C\uDDE7", "\uD83C\uDDE8", "\uD83C\uDDE9", "\uD83C\uDDEA", "\uD83C\uDDEB", "\uD83C\uDDEC", "\uD83C\uDDED", "\uD83C\uDDEE", "\uD83C\uDDEF", "\uD83C\uDDF0", "\uD83C\uDDF1", "\uD83C\uDDF2", "\uD83C\uDDF3", "\uD83C\uDDF4", "\uD83C\uDDF5", "\uD83C\uDDF6", "\uD83C\uDDF7", "\uD83C\uDDF8", "\uD83C\uDDF9", "\uD83C\uDDFA", "\uD83C\uDDFB", "\uD83C\uDDFC", "\uD83C\uDDFD", "\uD83C\uDDFE", "\uD83C\uDDFF"};
    private static final String[] emoji = {":regional_indicator_a:", ":regional_indicator_b:", ":regional_indicator_c:", ":regional_indicator_d:", ":regional_indicator_e:", ":regional_indicator_f:", ":regional_indicator_g:", ":regional_indicator_h:", ":regional_indicator_i:", ":regional_indicator_j:", ":regional_indicator_k:", ":regional_indicator_l:", ":regional_indicator_m:", ":regional_indicator_n:", ":regional_indicator_o:", ":regional_indicator_p:", ":regional_indicator_q:", ":regional_indicator_r:", ":regional_indicator_s:", ":regional_indicator_t:", ":regional_indicator_u:", ":regional_indicator_v:", ":regional_indicator_w:", ":regional_indicator_x:", ":regional_indicator_y:", ":regional_indicator_z:"};


    // Constructor
    public Poll() {
        super(
                "Poll",
                new String[]{},
                new String[]{Toolkit.ModRole}, // Add role name here. Empty: always / 1+: at least one.
                "Poll creator. (max 20 options)",
                true,
                "poll <intro with spaces>~ option one, option two, c, d, e"
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage("Creating Poll...").queue();
        VolmitEmbed embed = new VolmitEmbed("Volmit Poll!", e.getMessage());
        embed.setFooter("Please React to this poll with the emojis.", Toolkit.BotGIF);

        StringBuilder body = new StringBuilder();

        boolean intro = true;
        int introIndex = -1;
        for(int i = 1; i < args.size(); i++) {
            if (intro) {
                if (args.get(i).trim().contains("~:")) {
                    intro = false;
                    introIndex = i + 1;
                    if (i < args.size() - 20) {
                        e.getChannel().sendMessage("Exceeded the maximum amount of options available in the poll command!").queue();
                        return;
                    }
                }
                body.append(" ").append(args.get(i).replace("~", "\n"));
            } else {
                body.append(emoji[i - introIndex]).append(": **").append(args.get(i).trim()).append("**\n");
            }
        }
        embed.setDescription(body);

        List<String> reactions = Arrays.asList(unicodeEmoji).subList(0, args.size() - introIndex);
        embed.send(e.getMessage(), true, reactions);
    }
}

