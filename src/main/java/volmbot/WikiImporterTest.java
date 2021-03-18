package volmbot;

import art.arcane.quill.collections.tuple.Tuple2d;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class WikiImporterTest {
    private static final String path = "https://raw.githubusercontent.com/VolmitSoftware/documentation/iris/";
    /*
    We're still getting links and we're not getting all categories
     */
    public static void main(String[] args) throws IOException {
        List<String> siteContent = scraper(new URL(path + "SUMMARY.md"));
        print("Resulting object: \n" + new JSONObject(TableOfContents(siteContent)).toString(4));
    }

    // Scrapes URL for its HTML
    private static List<String> scraper(URL url) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String inputLine;
        List<String> out = new ArrayList<>();
        while ((inputLine = in.readLine()) != null) {
            out.add("." + inputLine);
        }
        in.close();
        return out;
    }

    // Maps the table of contents to a Hash
    private static Map<String, Object> TableOfContents(List<String> in){

        // Remove first item (Table of contents header)
        in.remove(0);

        // Init map
        Map<String, Object> map = new HashMap<>();

        // Init statistic variables
        String category = null;
        List<String> sub = new ArrayList<>();

        // Loop over all lines
        for (String line : in){

            // Continue if the line is empty
            if (line.equalsIgnoreCase(".")) continue;

            //print("LINE: " + line);

            // If there is a new category
            if (line.startsWith(".#")) {

                // If we have just started
                if (category == null){

                    // Add all items to the main map
                    sub.forEach(subb -> {
                        String[] res = getKeyAndURL(subb);
                        map.put(res[0], res[1]);
                    });
                // If we are already in true categories
                } else {

                    // Add the entire submap
                    map.put(category, mapLines(sub, "* "));
                }

                // Set the new category name
                category = line.replace(".## ", "");

                // Reset the sub storage
                sub = new ArrayList<>();

            // If it's a plain old line
            } else {

                // Add the line to the sub storage
                sub.add(line);
            }
        }
        // Return hash
        return map;
    }

    // Converts some lines into a HashMap (recursive)
    private static Map<String, Object> mapLines(List<String> lines, String path){

        // Remove empty char from path
        path = path.replace("^", "");

        // Initialize map
        Map<String, Object> map = new HashMap<>();

        // Subs and category
        String category = null;
        List<String> subs = new ArrayList<>();

        // Add first item to map
        String[] first = getKeyAndURL(lines.remove(0));
        map.put(first[0], first[1]);
        String resMain = first[first.length - 2];

        // Loop over all lines
        for (String line : lines){

            // Get cleaned up key and value
            String[] res = getKeyAndURL(line);
            print(path + "SUB (" + path + "): " + Arrays.toString(res));
            String[] temp = res[1].split("/");

            //1 Category null and readme    -> set category, reset subs, add self to subs, save endCat
            //2 Category null and no readme -> add to map
            //3 Category set  and readme    -> add category to map, reset category, add self to map
            //4 Category set  and <Exit>    -> add category to map, reset category, add self to map
            //5 Category set  and no readme -> add to subs

            // 1
            if (category == null && res[1].contains("README.md")) {
                subs = new ArrayList<>();
                category = res[0];
                subs.add(line);
                print("NEW CATEGORY: " + temp[temp.length - 2]);
            // 2
            } else if (category == null) {
                map.put(res[0], res[1]);
            // 3
            } else if (res[1].contains("README.md")) {
                print(subs.toString());
                map.put(category, mapLines(subs, "  " + path));

                subs = new ArrayList<>();
                category = res[0];
                subs.add(line);
                print("NEW CATEGORY: " + temp[temp.length - 2]);
            // 4
            } else if (!res[1].contains(resMain)) {
                print(subs.toString());
                map.put(category, mapLines(subs, "  " + path));
                map.put(res[0], res[1]);

            // 5
            } else {
                subs.add(line);
            }

            resMain = temp[temp.length - 2];
        }
        return map;
    }

    // Converts a line such that it can be be easily interpreted
    private static String[] getKeyAndURL(String line){
        line = line.replace("* [", "").substring(1).stripLeading();
        boolean start = true;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i++){
            Character c = line.charAt(i);
            if (start && c.equals(" ".charAt(0))) continue;
            else if (start) start = false;
            else if (c.equals("(".charAt(0))) continue;
            else if (c.equals(")".charAt(0))) continue;
            else if (c.equals("]".charAt(0))) c = ("~".charAt(0));
            str.append(c);
        }
        return str.toString().split("~");
    }

    // Prints a message
    private static void print(String msg){
        System.out.println(msg);
    }
}
