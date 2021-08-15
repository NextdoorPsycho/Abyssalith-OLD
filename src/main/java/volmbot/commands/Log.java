package volmbot.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import volmbot.Main;
import volmbot.toolbox.Director;
import volmbot.util.VolmitCommand;
import volmbot.util.VolmitEmbed;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class Log extends VolmitCommand {
    // Constructor
    public Log() {
        super(
                "log",
                new String[]{"log", "pastebin", "autohelp"},
                new String[]{}, // Add role name here. Empty: always / 1+: at least one.
                "Analyses a log file with for some common errors",
                true,
                "log <pastebin link>"
        );
    }

    // Handle
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent e) {
        String stem = args.get(1).replace("https://pastebincom/", "");
        String properURL = "https://pastebin.com/raw/" + stem;
        Document doc;
        try {
            URL url = new URL(properURL);
            doc = Jsoup.parse(url.openStream(), "UTF-8", url.toString()); // Get Document object ('url' is a java.net.URL object)
        } catch (IOException ex) {
            e.getChannel().sendMessage("Issue when processing URL. Please double-check!");
            ex.printStackTrace();
            return;
        }

        Director a = Director.load(100001);

        VolmitEmbed embed = new VolmitEmbed("Automated Error Detector", e.getMessage());
        embed.setTitle("Automated Detriment Detector");
        embed.setDescription(a.getSKey1() + "||" + properURL + "||");
        Main.info("PROCESSING PASTEBIN FILE");
        int prob = 0;

        // Shitty loop for stuff
        if (doc.text().contains("[Iris]: Couldn't find Object:")) { // Use element.text() to get the text of the element as a String
            embed.addField("Objects are Broken!: ", a.getSKey2(), false);
            prob++;
        }
        if (doc.text().contains("Couldn't read Biome file:")) { // Use element.text() to get the text of the element as a String
            embed.addField("You have a typo in a Biome file: ", a.getSKey3(), false);
            prob++;
        }
        if (doc.text().contains("[Iris]: Failed to generate parallax")) { // Use element.text() to get the text of the element as a String
            embed.addField("Iris's Parallax Layer has failed: ", a.getSKey4(), false);
            prob++;
        }
        if (doc.text().contains("configured to generate Overworld!")) { // Use element.text() to get the text of the element as a String
            embed.addField("Iris is being used in the Bukkit.yml file: ", a.getSKey5(), false);
            prob++;
        }
        if (doc.text().contains("Failed to insert parallax at chunk")) { // Use element.text() to get the text of the element as a String
            embed.addField("Iris's Parallax Layer generation: ", a.getSKey6(), false);
            prob++;
        }
        if (doc.text().contains("and may increase memory usage!")) { // Use element.text() to get the text of the element as a String
            embed.addField("Large objects are in use", a.getSKey7(), false);
            prob++;
        }
        if (doc.text().contains("DO NOT REPORT THIS TO PAPER - THIS IS NOT A BUG OR A CRASH")) { // Use element.text() to get the text of the element as a String
            embed.addField("Paper Watchdog Spam", a.getSKey8(), false);
            prob++;
        }
        if (!doc.text().contains("[Iris] Enabling Iris")) {
            embed.addField("This does not contain a **full** log with Iris installed, perhaps try again if you want more information.", "", false);
        }

        // NO PROBLEMS
        if (prob < 1) {
            embed.addField("Well, This is not good.", "I cant seem to figure anything out; try asking the support team about the issue!", false);
        }

        embed.send(e.getMessage(), true, 1000);
    }
}




