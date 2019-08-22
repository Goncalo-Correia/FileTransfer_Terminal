package org.musicsource.codezillas.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerFileManager {

    private final String pathPrefix = "C:\\Users\\";
    private final String pathSuffix = "\\Desktop\\SourceSERVER";
    private String userRoot;
    private final File folder = new File(pathBuilder(userRoot));
    private String fileName;

    public ServerFileManager() {
        fileName = "";
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
        File file = new File(pathBuilder(userRoot) + "/" + fileName);

        System.out.println("Download is starting...");

        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file.getPath());
            out.write(fileData);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public byte[] downloadFile(String string) {
        File file = null;
        byte[] data = null;

        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().equals(string)) {
                file = fileEntry;
                fileName = fileEntry.getName();
            }
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

    public void initServerDirectory() {

        Path createPath = Paths.get(pathBuilder(userRoot));

        if (!Files.exists(createPath.toAbsolutePath())) {

            try {
                Files.createDirectory(createPath.toAbsolutePath());
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
