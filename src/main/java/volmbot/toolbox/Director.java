package volmbot.toolbox;

import art.arcane.quill.collections.KMap;
import art.arcane.quill.execution.J;
import art.arcane.quill.io.IO;
import com.google.gson.Gson;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
public class Director {
    private static final KMap<Long, Director> kash = new KMap<>();


    // Add dans shitty variables here, all will be added to json perfectly // accessed
    private String SKey10 = "";
    private String SKey9 = "";
    private String SKey8 = "";
    private String SKey7 = "";
    private String SKey6 = "";
    private String SKey5 = "";
    private String SKey4 = "";
    private String SKey3 = "";
    private String SKey2 = "";
    private String SKey1 = "";
    private String Money = "0";
    private long id;
    private Boolean onCd = false;


    /*
    User s = User.load(id);
    //FAST Save
    J.a(s::save);
    */


    private Director(long id) {
        this.id = id;
    }


    public void save() {
        File f = new File("data/" + id + ".json");
        f.getParentFile().mkdirs();

        //Try-catch, but better ;)
        J.attempt(() -> IO.writeAll(f, new Gson().toJson(this)));

        //Never used: Hunk<String> dna = Hunk.newMappedHunk(5, 5, 5);


    }

    public static Director load(long id) {

        return kash.compute(id, (k, v) -> {
            if (v == null) {

                File f = new File("data/" + id + ".json");
                f.getParentFile().mkdirs();
                Director u = new Director(id);

                if (!f.exists()) {
                    try {
                        IO.writeAll(f, new Gson().toJson(u));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    return new Gson().fromJson(IO.readAll(f), Director.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return u;

            }
            return v;
        });


    }

    static {
        //:(:[
        Runtime.getRuntime().addShutdownHook(new Thread(() -> kash.v().forEach(Director::save)));
    }

}

