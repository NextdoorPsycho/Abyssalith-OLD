package volmbot.util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import volmbot.toolbox.Director;
import volmbot.toolbox.UserDirector;

public class Econator extends ListenerAdapter {
    public static void Add(Message msg, int num) {
        long f = msg.getMentionedMembers().get(0).getIdLong();

        UserDirector m = UserDirector.load(f); // Make a new Director for the User if its not there
        m.save(); // Save it because why not

        int money;
        try {
            money = Integer.parseInt(m.getMoney());  // Get stuff to int
            money = Math.addExact(money, num); // Add

            m.setMoney(String.valueOf(money)); // Set values
            System.out.println(money); // output debug
            m.save(); // Save
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

    }
    public static void Sub(Message msg, int num) {
        long f = msg.getMentionedMembers().get(0).getIdLong();

        UserDirector m = UserDirector.load(f); // Make a new Director for the User if its not there
        m.save(); // Save it because why not

        int money;
        try {
            money = Integer.parseInt(m.getMoney());  // Get stuff to int
            money = Math.subtractExact(money, num); // Subtract

            m.setMoney(String.valueOf(money)); // Set values
            System.out.println(money); // output debug
            m.save(); // Save
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

    }
    public static void Set(Message msg, int num) {
        long f = msg.getMentionedMembers().get(0).getIdLong();

        UserDirector m = UserDirector.load(f); // Make a new Director for the User if its not there
        m.save(); // Save it because why not

        try {
            m.setMoney(String.valueOf(num)); // Set values
            System.out.println(num); // output debug
            m.save(); // Save
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

    }

}
