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
    public static void display() {
        System.out.println(ConsoleColors.CYAN + "Please select an option: " + ConsoleColors.RESET);  //Wyświetlanie opcji

        System.out.println(ConsoleColors.GREEN + "add" + ConsoleColors.RESET);

        System.out.println(ConsoleColors.RED + "remove" + ConsoleColors.RESET);

        System.out.println(ConsoleColors.BLUE + "list" + ConsoleColors.RESET);

        System.out.println(ConsoleColors.YELLOW + "exit" + ConsoleColors.RESET);


    }

    public static String[][] loadTabToFile(String file) {                   //wczytywanie danych z pliku
        String tab[][] = null;
        Path path = Paths.get("./tasks.csv");                            // ścieżka
        if (!Files.exists(path)) {                                          //  sprawdzamy czy plik istnieje
            System.out.println(" Sorry file  doesn't exit");
        } else {
            System.out.println("File exist");
        }
        try {
            List<String> task = Files.readAllLines(path);
            tab = new String[task.size()][task.get(0).split(",").length];
            for (int i = 0; i < task.size(); i++) {
                String[] split = task.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;

    }
    public static void displaytable(String[][] tab){             //wyswietlanie danych z pliku
        for (int i=0;i<tab.length;i++) {
            System.out.println(i+ " ");
            for (int j=0;j<tab.length;j++){
                System.out.println(tab[i][j]+ "  ");
            }
            System.out.println();
        }
    }



    public static void addTasks() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Write description task: ");
        String des = scanner.nextLine();
        System.out.print("Write data: ");
        String data = scanner.nextLine();
        System.out.println("Is task is important for you: (true/false)?");
        String meaning = scanner.nextLine();
        tabTasks = Arrays.copyOf(tabTasks, tabTasks.length + 1);
        tabTasks[tabTasks.length - 1] = new String[3];
        tabTasks[tabTasks.length - 1][0] = des;
        tabTasks[tabTasks.length - 1][1] = data;
        tabTasks[tabTasks.length - 1][2] = meaning;


    }

    private static void removeTask(String[][] table, int number) {
        try {
            if (number < table.length) {
                tabTasks = ArrayUtils.remove(table, number);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("This element doesn't exist in table");
        }
    }



    static String[][] tabTasks;
    static final String taskscsv = "tasks.csv";


    public static void main(String[] args) {
        display();
        tabTasks = loadTabToFile(taskscsv);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String choosertext = scanner.nextLine();
            switch (choosertext) {
                case "list":
                    displaytable(tabTasks);
                    break;
                case "add":
                    addTasks();
                    break;
                case "remove":
                    Scanner scan = new Scanner(System.in);
                    System.out.println("choose number to remove.");
                    int number = scan.nextInt();
                    removeTask(tabTasks,number);
                    break;
                case "exit":
                    System.out.println(ConsoleColors.RED + "Goodbye" + ConsoleColors.RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println("ERROR! ,Choose option from list");


            }
            display();

        }


    }

}
