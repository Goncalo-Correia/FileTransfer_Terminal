package org.musicsource.codezillas.server;

import java.io.File;
import java.io.IOException;

public class ServerFileManager {

    private final File folder = new File("src/main/serverData");
    private String standard = "src/main/serverData";

    public ServerFileManager() {
    }

    public String[] listServerFilesForFolder() {
        String[] fileNames = new String[folder.listFiles().length];
        int index = 0;
        for (File fileEntry : folder.listFiles()) {
            fileNames[index] = fileEntry.getName();
            index++;
        }
        return fileNames;
    }

    public void uploadFile(String string) {
        File fileData = new File(standard + "/" + string);
        fileData.mkdir();
        try {
            fileData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
