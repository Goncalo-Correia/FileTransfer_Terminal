package org.musicsource.codezillas.client;

import java.io.File;

public class ClientFileManager {

    private final File folder = new File("src/main/clientData");

    public ClientFileManager() {
    }

    public String[] listClientFilesForFolder() {
        String[] fileNames = new String[folder.listFiles().length];
        int index = 0;
        for (File fileEntry : folder.listFiles()) {
            fileNames[index] = fileEntry.getName();
            index++;
        }
        return fileNames;
    }

    public String clientFiles(Integer selectedFile) {
        int index = 0;
        String fileInfo = "";
        for (File fileEntry : folder.listFiles()) {
            if (index == selectedFile - 1) {
                fileInfo = fileEntry.getName();
            }
            index++;
        }
        return fileInfo;
    }

}
