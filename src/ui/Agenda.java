package ui;

import java.util.Scanner;

public class Agenda {
    private Schedule s;
//    private ToDoList tdl;

    public Agenda () {
        s = new Schedule();
//        tdl = null;

    }


    //REQUIRES: variable input one of "D", "S", "C" or "Q" strings, startTime an integer between 0 and 24,
    //          length an integer between 0 and 10, day one of "m", "t", "w", "th", "f" or "q" strings
    //MODIFIES: list courseList
    //EFFECTS:  provides user interaction options
    public void userInteractionOptions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("==================What would you like to do?========================\n" +
                    "- to view your courses for a specific day, type in - [D]\n" +
                    "- to see your course schedule, type in - [S]\n" +
                    "- to add a course to your schedule, type in - [C]\n" +
                    "- to quit, type in - [Q]\n" +
                    "--------------------------------------------------------------------");
            String input = scanner.nextLine();


            if (input.equals("C")) {
                System.out.println("Please enter name of the first course.");
                String name = (scanner.nextLine());
                System.out.println("Please enter start of the " + name + " lecture time (use integers 0-24).");
                int startTime = (scanner.nextInt());
                System.out.println("How long are " + name + " lectures (in # of hours)?");
                int length = (scanner.nextInt());
                scanner.nextLine();
                Boolean[] weekDays = new Boolean[5];
                while (true) {
                    System.out.println("Enter one day which " + name + " lecture is on [m/t/w/th/f]?\n" +
                            "Or enter [q] if there are no other days.");
                    String day = scanner.nextLine();
                    if (day.equals("q")) {
                        break;
                    } else {
                        weekDays = enterDays(weekDays, day);
                    }
                }

                Course firstCourse = new Course(name, startTime, length, weekDays);
                s.add(firstCourse);
            }


            else if (input.equals("S")) {
                s.printCourses();
            }



            else if (input.equals("D")) {
                System.out.println("Please enter the day you are interested in.");
                String day = scanner.nextLine();
                System.out.println("These are your courses on " + day + ": ");
                s.printCoursesOnDay(day);
            }


            else if (input.equals("Q")) {
                break;
            }
        }
    }

    //REQUIRES: boolean array weekDays and string that is only one of "m", "t", "w", "th" or "f"
    //MODIFIES: boolean array weekDays
    //EFFECTS:  return a boolean array with a boolean, corresponding to the parameter day, set to true
    private Boolean[] enterDays(Boolean[] weekDays, String day) {
        if (day.equals("m")) {
            weekDays[0] = true;
        }
        else if (day.equals("t")) {
            weekDays[1] = true;
        }
        else if (day.equals("w")) {
            weekDays[2] = true;
        }
        else if (day.equals("th")) {
            weekDays[3] = true;
        }
        else if (day.equals("f")) {
            weekDays[4] = true;
        }
        return weekDays;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS:  creates a new agenda and starts the program
    public static void main(String[] args) {
        Agenda myAgenda = new Agenda();
        myAgenda.userInteractionOptions();
    }
}