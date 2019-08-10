package org.musicsource.codezillas.server;

import java.io.File;

public class ServerFileManager {

    private final File folder = new File("src/main/serverData");

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
}
