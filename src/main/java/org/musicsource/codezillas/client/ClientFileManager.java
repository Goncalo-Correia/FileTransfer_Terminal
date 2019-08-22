package org.musicsource.codezillas.client;

import java.io.*;
import java.net.Socket;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ClientFileManager {

    private final String pathPrefix = "/Users/";
    private final String pathSuffix = "/SourceCLIENT";
    private String userRoot;
    private final File folder = new File(pathBuilder("codecadet"));
    private String fileName;

    public ClientFileManager() {
        fileName = "";
    }

    public String[] listClientFilesForFolder() {
        /*int size = folder.listFiles().length;
        if (size == 0) {
            size = 1;
        }
        System.out.println("enter");
        String[] clientFileNames = new String[size];
        int index = 1;

        clientFileNames[0] = "Back";
        for (File fileEntry : folder.getAbsoluteFile().listFiles()) {
            clientFileNames[index] = fileEntry.getName();
            System.out.println("in loop");
            index++;
        }
        System.out.println("out");
        return clientFileNames;*/
        final List<Path> pathsToFiles = new ArrayList<>();

        try {
            Files.walkFileTree(folder.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isRegularFile(file)) {
                        pathsToFiles.add(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] arr = new String[pathsToFiles.toArray().length + 1];
        arr[0] = "Back";
        int index = 1;
        for (Object obj : pathsToFiles.toArray()) {
            String str = obj.toString().split("/")[4];
            arr[index] = str;
            index++;
        }
        return arr;
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
