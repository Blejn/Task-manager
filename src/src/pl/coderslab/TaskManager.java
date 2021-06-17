package pl.coderslab;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};

    static String[][] tasks;


    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option:" + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }

    }

    public static void main(String[] args) throws IOException {
        tasks = loadDataToTab(FILE_NAME);
        printOptions(OPTIONS);


    }

    public static String[][] loadDataToTab(String fileName) throws IOException {
        Path dir = Paths.get(fileName);
        if (!Files.exists(dir)) {
            System.out.println("File not exist");
            System.exit(0);
        }
        String[][] tab = null;
        try {
            List<String> strings = Files.readAllLines(dir);
            tab = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return tab;
    }


}
