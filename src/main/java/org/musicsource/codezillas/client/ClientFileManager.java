package org.musicsource.codezillas.client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientFileManager {

    private final String pathPrefix = "C:\\Users\\";
    private final String pathSuffix = "\\Desktop\\SourceCLIENT";
    private String userRoot;
    private final File folder = new File(pathBuilder(userRoot));
    private String fileName;

    public ClientFileManager() {
        fileName = "";
    }

    public String[] listClientFilesForFolder() {
        String[] clientFileNames = new String[folder.listFiles().length];
        int index = 0;
        for (File fileEntry : folder.listFiles()) {
            clientFileNames[index] = fileEntry.getName();
            index++;
        }
        return clientFileNames;
    }

    public byte[] uploadFile(Integer selectedFile) {
        int index = 0;
        File file = null;
        byte[] data = null;

        for (File fileEntry : folder.listFiles()) {
            if (index == selectedFile - 1) {
                fileName = fileEntry.getName();
                file = fileEntry;
            }
            index++;
        }

        if (file != null){

            try {

                FileInputStream reader = new FileInputStream(file);
                data = reader.readAllBytes();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return data;
    }

    public void downloadFile(byte[] fileData, String fileName) {
        File file = new File(pathBuilder(userRoot) + "/" + fileName);

        System.out.println("Download is starting...");

        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file.getPath());
            out.write(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initClientDirectory() {
        Path createPath = Paths.get(pathBuilder(userRoot));
        System.out.println(createPath.toAbsolutePath().toString());
        if (!Files.exists(createPath)) {

            try {
                Files.createDirectory(createPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Directory created");
        } else {
            System.out.println("Directory already exists");
        }
    }

    private String pathBuilder(String userRoot) {
        return pathPrefix + userRoot + pathSuffix;
    }

    public String getFileName() {
        return fileName;
    }

    public void setUserRoot(String userRoot) {
        this.userRoot = userRoot;
    }
}
