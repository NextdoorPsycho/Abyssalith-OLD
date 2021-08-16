package volmbot.toolbox;

import art.arcane.quill.collections.KMap;
import art.arcane.quill.execution.J;
import art.arcane.quill.io.IO;
import com.google.gson.Gson;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReactionRoleDirector {
    private static final KMap<Long, ReactionRoleDirector> kash = new KMap<>();


    // Add dans shitty variables here, all will be added to json perfectly // accessed
    private String rolePostId = "";
    private String roleChannelId = "";
    private List<String> roles = new ArrayList<>();
    private long rId;



    private ReactionRoleDirector(long id) {
        this.rId = id;
    }


    public void save() {
        File f = new File("data/ReactionRoles/" + rId + ".json");
        f.getParentFile().mkdirs();

        //Try-catch, but better ;)
        J.attempt(() -> IO.writeAll(f, new Gson().toJson(this)));

        //Never used: Hunk<String> dna = Hunk.newMappedHunk(5, 5, 5);


    }

    public static ReactionRoleDirector load(long id) {

        return kash.compute(id, (k, v) -> {
            if (v == null) {

                File f = new File("data/ReactionRoles/" + id + ".json");
                f.getParentFile().mkdirs();
                ReactionRoleDirector u = new ReactionRoleDirector(id);

                if (!f.exists()) {
                    try {
                        IO.writeAll(f, new Gson().toJson(u));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    return new Gson().fromJson(IO.readAll(f), ReactionRoleDirector.class);
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
        Runtime.getRuntime().addShutdownHook(new Thread(() -> kash.v().forEach(ReactionRoleDirector::save)));
    }

}

