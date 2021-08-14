package volmbot.commands.old;

public class garbage {

/*
    //if it contains message
            if(!e.getMessage().getAttachments().isEmpty()) {
        e.getMessage().getAttachments().forEach((i) -> {
            //make a temp file
            File f = new File("temp/" + UUID.randomUUID().toString());


            f.getParentFile().mkdirs();
            String fname = f.getName();
            //runnable to download file
            J.a(() -> {
*//*                        i.downloadToFile(f).join();
                        try {
                            Long filesize = Files.size(f.toPath());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }*//*
                f.deleteOnExit();
                if (fname.contains(".log")) {
                    //Confirmed its a log file
                    if (f.toString().contains("[Iris]: Couldn't read Biome file")) {
                        e.getChannel().sendMessage("Cant read Biome").queue();

                    }


                }

            });
        });
    }*/











    /*        if (args[0].equalsIgnoreCase(Toolkit.BotPrefix + "logger")) {
            //SENDER
            Long id = Long.parseLong(e.getMessage().getAuthor().getId());
            String name = e.getMessage().getContentRaw();

            User a = User.load(id);
            a.setData1(name);
            a.setData2(name);
            a.setData3(name);
            a.setData4(name);
            a.setOnCd(true);

            //FASTTT Save
            J.a(a::save);

            e.getChannel().sendMessage("Serialized: " + Toolkit.dir).queue();
        }*/


}
