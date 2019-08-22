package org.musicsource.codezillas.generic;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileManager {

    public String[] listClientFilesForFolder(File folder) {
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
        System.out.println("Fetching server files...");
        return arr;
    }

}
