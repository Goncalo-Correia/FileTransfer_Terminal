package org.musicsource.codezillas.client;

import org.musicsource.codezillas.generic.AbstractFileManager;

import java.io.*;
import java.net.Socket;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ClientFileManager extends AbstractFileManager {

    private String pathPrefix;
    private String pathSuffix;
    private String userRoot;
    private File folder;
    private String fileName;

    public ClientFileManager(String pathPrefix, String pathSuffix, String userRoot) {
        fileName = "";
        this.pathPrefix = pathPrefix;
        this.pathSuffix = pathSuffix;
        this.userRoot = userRoot;
        this.folder = new File(pathBuilder(userRoot));
    }

    public String[] listClientFilesForFolder() {
        return super.listClientFilesForFolder(folder);
    }

    public byte[] uploadFile(Integer selectedFile) {
        int index = 1;
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

                System.out.println("Uploading...");

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

        System.out.println("Downloading...");

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
        if (!Files.exists(createPath)) {

            try {
                Files.createDirectory(createPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Root directory created");
        } else {
            System.out.println("Root directory already exists");
        }
    }

    private String pathBuilder(String userRoot) {
        return pathPrefix + userRoot + pathSuffix;
    }

    public String getFileName() {
        return fileName;
    }

    //public void setUserRoot(String userRoot) {
    //    this.userRoot = userRoot;
    //}
}
