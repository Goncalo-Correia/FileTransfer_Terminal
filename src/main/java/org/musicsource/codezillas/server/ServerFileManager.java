package org.musicsource.codezillas.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerFileManager {

    private final File folder = new File("src/main/serverData");
    private final String path = "src/main/serverData";
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ServerFileManager(Socket socket) {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.socket = socket;
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

    public void uploadFile(String string) {
        File fileData = new File(path + "/" + string);
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
