package org.musicsource.codezillas.server;

import java.io.File;
import java.io.IOException;

public class ServerFileManager {

    private final File folder = new File("src/main/serverData");
    private final String standard = "src/main/serverData";

    public ServerFileManager() {
    }

    public String[] listServerFilesForFolder() {
        String[] serverFileNames = new String[folder.listFiles().length];
        int index = 0;
        for (File fileEntry : folder.listFiles()) {
            serverFileNames[index] = fileEntry.getName();
            index++;
        }
        return serverFileNames;
    }

    public String serverFilesString() {
        String[] serverFiles = listServerFilesForFolder();
        String wrapper = "\n";
        String result = "";
        for (String file : serverFiles) {
            result += file + wrapper;
        }
        return result;
    }

    public void uploadFile(String string) {
        File fileData = new File(standard + "/" + string);
        //fileData.mkdir();
        try {
            fileData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String downloadFile(String string) {
        String[] serverFiles = listServerFilesForFolder();
        String requestedFile = "";
        for (String file : serverFiles) {
            if (file.equals(string)) {
                requestedFile = file;
            }
        }
        return requestedFile;
    }
}
