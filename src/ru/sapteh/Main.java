package ru.sapteh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название новой папки: ");
        String nameDir = bf.readLine();

        Path sourcePath = Paths.get("C:/Test/directory");

        if (Files.exists(sourcePath.resolve(nameDir))){
            System.out.println("Папка уже существует\n");
        } else {
            Files.createDirectory(sourcePath.resolve(nameDir));
            System.out.println("Папка создана\n");
        }

        System.out.println("Введите название нового файла: ");
        String nameFile = bf.readLine();

        if (Files.exists(sourcePath.resolve(nameFile))){
            System.out.println("Такой файл уже существует\n");
        } else {
            Files.createFile(sourcePath.resolve(nameFile));
            System.out.println("Файл создан\n");
        }

        System.out.println("Введите то что вам нужно найти в файлах: ");
        String fileHaveName = bf.readLine();
        FileCount fileCount = new FileCount();
        if (fileHaveName.isEmpty()){
            fileHaveName = null;
        }
        fileCount.setHaveName(fileHaveName);
//
//        System.out.println("Введите файл который хотите скопировать: ");
//        Files.copy(sourcePath.resolve("io.txt"), sourcePath.resolve("d/resolv.txt"));

        Files.walkFileTree(sourcePath, fileCount);

        System.out.printf("Кол-во папок в директории: %d\n",fileCount.getCountDirectory());
        System.out.printf("Кол-во файлов в директории: %d\n",fileCount.getCountFiles());
        System.out.printf("Вес всех файлов: %.2f KB\n",fileCount.getBytes()/1024f);
        System.out.printf("Путь к файлам которые содержат данную запись: %s",fileCount.getFilePath());
    }
}
