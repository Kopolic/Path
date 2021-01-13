package ru.sapteh;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileCount extends SimpleFileVisitor<Path> {

    private int countDirectory;
    private int countFiles;
    private long bytes;
    private String haveName;
    private Set<String> filePath = new HashSet<>();


    public int getCountDirectory() {
        return countDirectory;
    }
    public int getCountFiles() {
        return countFiles;
    }
    public long getBytes() {
        return bytes;
    }
    public String getFilePath(){
        String str = "";
        for (String strPath: filePath) {
            str += "\n" + strPath;
        }
        return str;
    }
    public void setHaveName(String haveName) {
        this.haveName = haveName;
    }


    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes atr){
        countDirectory++;
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes atr){
        try {
            countFiles++;
            bytes += atr.size();
            List<String> str = Files.readAllLines(path);
            for (String string : str) {
                if (string.contains(haveName)) {
                    filePath.add(path.toAbsolutePath().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

}
