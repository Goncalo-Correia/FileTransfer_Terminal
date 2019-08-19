package org.musicsource.codezillas.server;

import java.io.*;
import java.net.Socket;

public class ServerFileManager {

    private final File folder = new File("src/main/serverData");
    private final String path = "src/main/serverData";

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

    public void uploadFile(byte[] fileData, String fileName) {
        File file = new File(path + "/" + fileName);

        System.out.println("Download is starting...");

        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file.getPath());
            out.write(fileData);

        } catch (IOException e) {
            System.err.println(e.getMessage());
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
