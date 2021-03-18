package volmbot.util;

import com.google.gson.Gson;
import lombok.Getter;
import org.json.JSONObject;
import volmbot.Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IndexedWiki {
    // Global wiki variables
    private static final String absolutePath = "data/wikis/";
    @Getter
    private static final List<IndexedWiki> wikis = new ArrayList<>();

    // This wiki's variables
    @Getter
    private final String name;
    private JSONObject wiki;
    @Getter
    private boolean updated = false;
    @Getter
    private LocalDateTime updatedDate = null;
    @Getter
    private final String creationInfo;

    // Load wiki
    private IndexedWiki(File wiki, boolean update){
        this.name = wiki.getName().replace(".json", "");
        this.wiki = load(wiki);
        if (this.wiki == null) {
            this.creationInfo = "FAILED";
        } else {
            this.creationInfo = "Successfully loaded";
        }
        if (update) update();
        save();
    }

    public IndexedWiki(String name, String rawTextURL, boolean update){
        this.name = name;
        this.wiki = null;
        this.creationInfo = create(rawTextURL);
        if (update) update();
        save();
    }

    private String create(String rawTextURL) {
        for (IndexedWiki wiki : wikis){
           if (wiki.getName().equalsIgnoreCase(name)) {
                return "This wiki already exists!";
           }
        }
        File path = new File(absolutePath + name + ".json");
        try {
            path.createNewFile();
        } catch (IOException ex) {
            Main.error("Failed to create wiki file at: " + path.getAbsolutePath());
            return "FAILED";
        }
        System.out.println(path.getAbsolutePath());
        wiki.put("name", name)
                .put("path", path.getAbsolutePath())
                .put("URL", (rawTextURL.endsWith("SUMMARY.md") ? rawTextURL : rawTextURL + "SUMMARY.md"));
        if (!save()) {
            Main.error("Failed to save wiki file at: " + path.getAbsolutePath());
            return "FAILED";
        }
        JSONObject newWiki = load();
        if (newWiki == null) {
            Main.error("Failed to load wiki from: " + path.getAbsolutePath());
            return "FAILED";
        } else {
            wiki = newWiki;
            return "Successfully created";
        }
    }

    // Saves this wiki to saved path & name
    private boolean save(){
        return save(new File(absolutePath + name + ".json"));
    }
    // Saves this wiki to `toFile`
    private boolean save(File toFile){
        FileWriter fw;
        try {
            fw = new FileWriter(toFile);
        } catch (IOException ex){
            Main.error("Failed to open filewriter at: " + toFile.getAbsolutePath());
            return false;
        }

        new Gson().toJson(wiki, fw);
        return true;
    }


    // Loads this wiki from saved path & name
    private JSONObject load() {
        return load(new File(absolutePath + name + ".json"));
    }
    // Loads this wiki from `fromFile`
    private static JSONObject load(File fromFile){
        FileReader fr;
        try {
            fr = new FileReader(fromFile);
        } catch (IOException ex){
            Main.error("Failed to open filereader at: " + fromFile.getAbsolutePath());
            return null;
        }

        return new Gson().fromJson(fr, JSONObject.class);
    }

    // Updates the wiki
    private String update() {
        String old = wiki.toString();
        String status = create((String) wiki.getString("URL"));
        if (status.equalsIgnoreCase("Successfully created")) return status;
        updated = true;
        updatedDate = LocalDateTime.now();
        if (wiki.toString().equalsIgnoreCase(old)) return "Nothing changed";
        return "Successfully updated wiki";
    }

    // Load all wikis in wikis folder and builds a nice info message
    public static String loadAll() {
        File f = new File(absolutePath);
        f.mkdirs();
        StringBuilder status = new StringBuilder("Loadall: ");
        File[] files = f.listFiles();
        if (files == null || files.length < 1) return "No wikis loaded";
        for (File file : files){
            if (file.isFile() && file.getName().endsWith(".json")){
                IndexedWiki newWiki = new IndexedWiki(file, true);
                if (newWiki.wiki != null) {
                    status.append("+")
                            .append(file.getName())
                            .append(" ");
                } else {
                    status.append("-error:")
                            .append(file.getName())
                            .append(" ");
                }
            }
        }
        return status.toString().equalsIgnoreCase("Loadall: ") ? "No wikis loaded" : status.toString();
    }
}
