package org.musicsource.codezillas.client;

import java.io.File;
import java.io.IOException;

public class ClientFileManager {

    private final File folder = new File("src/main/clientData");
    private final String path = "src/main/clientData";

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

    public void downloadFile(String fileName) {
        File fileData = new File(path + "/" + fileName);
        //fileData.mkdir();
        try {
            fileData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
