package net.kuronekoserver.reactrolebot.profile;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ProfileManager {
    private final Gson gson = new Gson();

    public ProfileManager() {}

    public Profile loadProfile(String profile_raw) {
        File configFolder = new File("profiles");
        if (!configFolder.exists() || !configFolder.isDirectory()) {
            configFolder.mkdir();
        }

        File configFile = new File("profiles/"+profile_raw+".json");

        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            try {
                return gson.fromJson(new FileReader(configFile), Profile.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
