package org.musicsource.codezillas.server;

import org.musicsource.codezillas.generic.AbstractFileManager;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ServerFileManager extends AbstractFileManager {

    private final String pathPrefix = "/Users/";
    private final String pathSuffix = "/Desktop/SourceSERVER";
    private String userRoot;
    private File folder;
    private String fileName;

    public ServerFileManager(String userRoot) {
        this.fileName = "";
        this.userRoot = userRoot;
        this.folder = new File(pathBuilder(userRoot));
    }

    public String[] listServerFilesForFolder() {
        return super.listClientFilesForFolder(folder);
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

        System.out.println("<UPLOAD>");

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

        System.out.println("<DOWNLOAD>");

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
            System.out.println("\nServer directory created");
        } else {
            System.out.println("\nServer directory already exists");
        }
    }

    private String pathBuilder(String userRoot) {
        return pathPrefix + userRoot + pathSuffix;
    }

    public String getFileName() {
        return fileName;
    }

}
