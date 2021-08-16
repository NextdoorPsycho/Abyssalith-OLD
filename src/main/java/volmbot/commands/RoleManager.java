package volmbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import volmbot.toolbox.ReactionRoleDirector;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class RoleManager extends VolmitCommand {
    // Constructor
    public RoleManager() {
        super(
                "reactionrole",
                new String[]{"reactionrole", "rr"},
                new String[]{}, // Always permitted if empty. User must have at least one if specified.
                "This is the reaction Role command!",
                true,
                ".rr <EMOJI> <ROLE ID>"
        );
    }


    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        args.remove(0);// remove command
//        long msgId = e.getMessage().getIdLong(); //TODO GET ACTUAL MESSAGE ROLE

        VolmitEmbed embed = new VolmitEmbed();
        String roleid = args.get(1);
        String role = e.getGuild().getRoleById(args.get(1)).getAsMention();
        String roleName = e.getGuild().getRoleById(args.get(1)).getName();
        String roleReact = args.get(0).toString();
        System.out.println(role);
        System.out.println(roleReact);

        //Write the file for the role
        ReactionRoleDirector dir = ReactionRoleDirector.load(11111);
        dir.setRolePostId(""+roleid);
        dir.setReactionEmojiId(""+roleReact);
        dir.save();
        embed.addField("The `" + roleName + "` role:", roleReact + " for " +role, true);


        //Embed

        embed.setTitle("Reaction Role Page!");
        embed.setDescription("Please react to a role:\n");

        e.getChannel().sendMessage(embed.build()).queue(message -> {
            message.addReaction(args.get(0)).queue();
//            message.addReaction(reaction2).queue();
//            message.addReaction(reaction3).queue();
        });


    }
}