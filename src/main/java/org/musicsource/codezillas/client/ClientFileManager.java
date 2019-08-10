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

    public String[] clientFile(Integer selectedFile) {
        int index = 0;
        String[] fileInfo = new String[2];
        for (File fileEntry : folder.listFiles()) {
            if (index == selectedFile - 1) {
                fileInfo[0] = fileEntry.getName();
                fileInfo[1] = fileEntry.getPath();
            }
            index++;
        }
        return fileInfo;
    }

}
