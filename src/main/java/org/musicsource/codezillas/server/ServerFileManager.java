package org.musicsource.codezillas.server;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ServerFileManager {

    private final String pathPrefix = "/Users/";
    private final String pathSuffix = "/SourceSERVER";
    private String userRoot;
    private final File folder = new File(pathBuilder("codecadet"));
    private String fileName;

    public ServerFileManager() {
        fileName = "";
    }

    public String[] listServerFilesForFolder() {

        /*String[] serverFileNames = new String[folder.listFiles().length];
        int index = 1;

        serverFileNames[0] = "Back";
        for (File fileEntry : folder.listFiles()) {
            serverFileNames[index] = fileEntry.getName();
            index++;
        }

        return serverFileNames;*/
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
        File file = new File(pathBuilder("codecadet") + "/" + fileName);

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
