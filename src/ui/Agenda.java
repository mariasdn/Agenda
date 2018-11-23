package ui;

//import java.util.InputMismatchException;

import exceptions.InvalidArgumentException;
import exceptions.InvalidWeekDayException;
import observer.ScheduleObserver;
import web.ReadWebPageEx;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda implements ScheduleObserver {
    public Schedule s;
    private  Scanner scanner;
//    private ToDoList tdl;

    public Agenda() {
        s = new Schedule();
        s.addObserver(this);
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
                saveEdits();
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
                    "   - to view a certain item, type in - [n]\n" +
                    "   - to go back, type in - [b]\n" +
                    "--------------------------------------------------------");
            String input = scanner.nextLine();
            if (input.equals("s")) {
                s.viewSchedule();
            } else if (input.equals("d")) {
                System.out.println("Please enter the day you are interested in. One of [m],[t],[w],[th] or [f]");
                String day = scanner.nextLine();
                try{
                    s.viewItemsOnDay(day);
                } catch (InvalidWeekDayException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.equals("n")) {
                System.out.println("Please enter a name of an activity you are interested in or a course code.");
                String subject = scanner.nextLine();
                s.viewItemsByName(subject);
            } else if (input.equals("b")) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
            System.out.println("\n");
        }
    }

    @Override
    public void update(ScheduleItem item){
        System.out.println("\nNew item was added to the schedule!\n" +
        item.toString());
    }

    private void editItems() {
        while (true) {
            System.out.println("----------------Editing items-------------------\n" +
                    "   - to add a course, type in - [ac]\n" +
                    "   - to add an activity, type in - [aa]\n" +
                    "   - to delete an item, type in - [d]\n" +
                    "   - to go back, type in - [b]\n" +
                    "------------------------------------------------");
            String input = scanner.nextLine();
            if (input.equals("ac")) {
                userAddsCourse();
            } else if (input.equals("aa")) {
                userAddsActivity();
            } else if (input.equals("d")) {
                userDeletesItem();
            } else if (input.equals("b")) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
            System.out.println("\n");
        }
    }

    public void saveEdits () {
        try {
            PrintWriter pw = new PrintWriter(new File("Save.txt"));
            ArrayList<String> items = s.itemsAsString();
            for (String si: items) {
                pw.println(si);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        } finally {
            System.out.println("Save is over");
        }
    }

    public void readInFile(BufferedReader br) {
        try {
            String st;
            while ((st = br.readLine()) != null) {
                String[] data = st.split(",");
                this.s.addItem(data);
            }
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    public void userAddsActivity() {
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
            s.addItem("Activity", name, startTime, length, weekDays);
        } catch (InvalidArgumentException e) {
            System.out.println("You have entered invalid information.");
        }
    }

    public void userAddsCourse() {
        System.out.println("Please enter course's subject code.");
        String code = scanner.nextLine();
        System.out.println("Please enter course number.");
        String num = scanner.nextLine();
        String name = code + " " + num;
        int startTime = 0;
        int length = 0;
        while (true) {
            System.out.println("Please enter start time of the " + name + " lecture");
            try {
                startTime = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        }
        while (true) {
            System.out.println("Please enter length of the " + name + " lecture");
            try {
                length = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        }
        boolean[] weekDays = inputWeekDays();
        try {
            s.addItem("Course", name, startTime, length, weekDays);
        } catch (InvalidArgumentException e) {
            System.out.println("You have entered invalid information.");
        }
        while (true) {
            System.out.println("Does this course have a lab? [yes] or [no]");
            String hasLab = scanner.nextLine();
            if (hasLab.equals("yes")) {
                userAddsLab(name);
                break;
            } else if (hasLab.equals("no")) {
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    public void userAddsLab(String name) {
        int startTime = 0;
        int length = 0;
        while (true) {
            System.out.println("Please enter start time of the " + name + " lab");
            try {
                startTime = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        }
        while (true) {
            System.out.println("Please enter length of the " + name + " lab");
            try {
                length = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input!");
            }
        }
        boolean[] weekDays = inputWeekDays();
        try {
            s.addItem("Lab", name, startTime, length, weekDays);
        } catch (InvalidArgumentException e) {
            System.out.println("You have entered invalid information.");
        }
    }

    private boolean[] inputWeekDays() {
        boolean[] weekDays = new boolean[5];
        while (true) {
            try {
                System.out.println("Please enter a week day it is on (one of [m],[t],[w].[th] or [f];\n" +
                        " if there are no more days, enter [q]");
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
                    throw new InvalidWeekDayException("You have entered an invalid week day.");
                }
            } catch (InvalidWeekDayException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void userDeletesItem() {
        System.out.println("Please enter a name of an item you would like to delete");
        s.deleteItem(scanner.nextLine());
    }

    public static void main(String[] args) {
        Agenda myAgenda = new Agenda();
        JFrame frame = new JFrame("Agenda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayout cl = new CardLayout();
        frame.setVisible(true);
        //myAgenda.interactionOptions();
    }
}