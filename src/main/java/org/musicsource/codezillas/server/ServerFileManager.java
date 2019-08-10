package org.musicsource.codezillas.server;

import java.io.File;

public class ServerFileManager {

    private final File folder = new File("src/main/serverData");

    public ServerFileManager() {
    }

    public void listFilesForFolder() {
        for (File fileEntry : folder.listFiles()) {
            System.out.println(fileEntry.getName());
            System.out.println(fileEntry.getPath());
        }
    }
}
