package ui;

//import java.util.InputMismatchException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    public Schedule s;
    private  Scanner scanner;
//    private ToDoList tdl;

    public Agenda() {
        s = new Schedule();
        scanner= new Scanner(System.in);
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("Save.txt")));
            readInFile(br);
        } catch (FileNotFoundException e){
            File f = new File("Save.txt");
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println("Fatal error: could not create Save.txt");
                System.exit(-1);
            }
        }
//        tdl = null;

    }

    public void interactionOptions() {
        while (true) {
            System.out.println("==================What would you like to do?========================\n" +
                    "   - to view, type in - [V]\n" +
                    "   - to edit, type in - [E]\n" +
                    "   - to save your edits, type in [S]\n" +
                    "   - to quit, type in - [Q]\n" +
                    "====================================================================");
            String input = scanner.nextLine();
            if (input.equals("V")) {
                viewItems();
            } else if (input.equals("E")) {
                editItems();
            } else if (input.equals("S")) {
                saveCourses();
            } else if (input.equals("Q")) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
            System.out.println("\n\n");
        }
    }

    private void viewItems() {
        while (true) {
            System.out.println("--------------------Viewing items-----------------------\n" +
                    "   - to view the entire schedule, type in - [s]\n" +
                    "   - to view items on a certain day, type in - [d]\n" +
                    "   - to view items with a certain name, type in - [n]\n" +
                    "   - to go back, type in - [b]\n" +
                    "--------------------------------------------------------");
            String input = scanner.nextLine();
            if (input.equals("s")) {
                s.viewSchedule();
            } else if (input.equals("d")) {
                System.out.println("Please enter the day you are interested in. One of [m],[t],[w],[th] or [f]");
                String day = scanner.nextLine();
                s.viewItemsOnDay(day);
            } else if (input.equals("n")) {
                System.out.println("Please enter the name of an item you are interested in.");
                String subject = scanner.nextLine();
                s.viewItemByName(subject);
            } else if (input.equals("b")) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
            System.out.println("\n");
        }
    }

    private void editItems() {
        while (true) {
            System.out.println("----------------Editing items-------------------\n" +
                    "   - to add a course, type in - [ac]\n" +
                    "   - to add an activity, type in - [aa]\n" +
                    "   - to delete an item, type in - [d]\n" +
                    "   - to change item's information, type in - [c]\n" +
                    "   - to go back, type in - [b]\n" +
                    "------------------------------------------------");
            String input = scanner.nextLine();
            if (input.equals("ac")) {
                System.out.println("Please enter course's subject code.");
                String code = scanner.nextLine();
                System.out.println("Please enter course number.");
                String num = scanner.nextLine();
                int startTime = 0;
                int length = 0;
                while (true) {
                    System.out.println("Please enter start time of the " + code + " " + num + " lecture");
                    try {
                        startTime = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input!");
                    }
                }
                while (true) {
                    System.out.println("Please enter length of the " + code + " " + num + " lecture");
                    try {
                        length = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input!");
                    }
                }
                boolean[] weekDays = inputWeekDays();
                try {
                    s.addCourse(code, num, startTime, length, weekDays);
                } catch (IllegalArgumentException e) {
                    System.out.println("You have entered invalid information.");
                }
            } else if (input.equals("aa")) {
                System.out.println("Please enter name of the activity.");
                String name = scanner.nextLine();
                int startTime = 0;
                int length = 0;
                while (true) {
                    System.out.println("Please enter start time of " + name);
                    try {
                        startTime = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input!");
                    }
                }
                while (true) {
                    System.out.println("Please enter length of " + name);
                    try {
                        length = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input!");
                    }
                }
                boolean[] weekDays = inputWeekDays();
                try {
                    s.addActivity(name, startTime, length, weekDays);
                } catch (IllegalArgumentException e) {
                    System.out.println("You have entered invalid information.");
                }
            } else if (input.equals("d")) {
                //s.deleteCourse();
            } else if (input.equals("c")) {
                //s.changeCourse();
            } else if (input.equals("b")) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
            System.out.println("\n");
        }
    }

    public void saveCourses () {
        try {
            PrintWriter pw = new PrintWriter(new File("Save.txt"));
            ArrayList<String> courses = s.coursesAsString();
            for (String c: courses) {
                pw.println(c);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
    }

    private boolean[] inputWeekDays() {
        boolean[] weekDays = new boolean[5];
        while (true) {
            System.out.println("Please enter a week day it is on (one of [m],[t],[w].[th] or [f],\n" +
                    " if there are no more days enter [q]");
            String day = scanner.nextLine();
            if (day.equals("q")) {
                return weekDays;
            } else if (day.equals("m")) {
                weekDays[0] = true;
            } else if (day.equals("t")) {
                weekDays[1] = true;
            } else if (day.equals("w")) {
                weekDays[2] = true;
            } else if (day.equals("th")) {
                weekDays[3] = true;
            } else if (day.equals("f")) {
                weekDays[4] = true;
            } else {
                System.out.println("You have entered invalid week day.");
            }
        }
    }

    public void readInFile(BufferedReader br) {
        try {
            String st;
            while ((st = br.readLine()) != null) {
                this.s.addCourse(st.split(","));
            }
        } catch (IOException e) {
            System.exit(-1);
        }
    }


    public static void main(String[] args) {
        Agenda myAgenda = new Agenda();
        myAgenda.interactionOptions();
    }
}