package volmbot.commands;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.Main;
import volmbot.toolbox.ReactionRoleDirector;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.LinkedList;
import java.util.List;


public class ReactionRole extends VolmitCommand {
    // Constructor
    public ReactionRole() {
        super(
                "reactionrole",
                new String[]{"reactionrole", "rr"},
                new String[]{}, // Always permitted if empty. User must have at least one if specified.
                "This is the reaction Role command!",
                true,
                ".rr <ROLE ID> <ROLE ID>..."
        );
    }


    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        args.remove(0);// remove command
        int counter = args.size();

        //dictionary
        String[] unicodeEmoji = {"\uD83C\uDDE6", "\uD83C\uDDE7", "\uD83C\uDDE8", "\uD83C\uDDE9", "\uD83C\uDDEA", "\uD83C\uDDEB", "\uD83C\uDDEC", "\uD83C\uDDED", "\uD83C\uDDEE", "\uD83C\uDDEF", "\uD83C\uDDF0", "\uD83C\uDDF1", "\uD83C\uDDF2", "\uD83C\uDDF3", "\uD83C\uDDF4", "\uD83C\uDDF5", "\uD83C\uDDF6", "\uD83C\uDDF7", "\uD83C\uDDF8", "\uD83C\uDDF9", "\uD83C\uDDFA", "\uD83C\uDDFB", "\uD83C\uDDFC", "\uD83C\uDDFD", "\uD83C\uDDFE", "\uD83C\uDDFF"};
        String[] emoji = {":regional_indicator_a:", ":regional_indicator_b:", ":regional_indicator_c:", ":regional_indicator_d:", ":regional_indicator_e:", ":regional_indicator_f:", ":regional_indicator_g:", ":regional_indicator_h:", ":regional_indicator_i:", ":regional_indicator_j:", ":regional_indicator_k:", ":regional_indicator_l:", ":regional_indicator_m:", ":regional_indicator_n:", ":regional_indicator_o:", ":regional_indicator_p:", ":regional_indicator_q:", ":regional_indicator_r:", ":regional_indicator_s:", ":regional_indicator_t:", ":regional_indicator_u:", ":regional_indicator_v:", ":regional_indicator_w:", ":regional_indicator_x:", ":regional_indicator_y:", ":regional_indicator_z:"};
        List<String> r = new LinkedList<>();
        VolmitEmbed embed = new VolmitEmbed();

        String role = e.getGuild().getRoleById(args.get(1)).getAsMention();
        String roleName = e.getGuild().getRoleById(args.get(1)).getName();
        Main.info("Creating Roles: " + role);
        //Write the file for the role

        int unie = 0;
        for (String temp : args) {
            String rrr = e.getGuild().getRoleById(args.get(unie)).getName();
            r.add(temp);
            Role rr = e.getChannel().getGuild().getRoleById(temp);

            assert rr != null;
            embed.addField("The `" + rrr + "` role:", emoji[unie] + " for " + rr.getAsMention(), true);
            unie++;
        }

        embed.setTitle("Reaction Role Page!");
        embed.setDescription("Please react to a role:\n");
        e.getChannel().sendMessage(embed.build()).queue(message -> {
            ReactionRoleDirector dir = ReactionRoleDirector.load(message.getIdLong());
            for (int i = 0; i < args.size(); i++) { // Adds reactions to message
                message.addReaction(unicodeEmoji[i]).queue();
                Main.info("Parsed Roles: " + r);

            }
            dir.setRoles(r);
            dir.save();
            embed.addField("The `" + roleName + "` role:", dir.getRoles() + " for " + role, true);
//            message.addReaction(reaction2).queue();
        });


    }
}