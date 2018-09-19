package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {

    public static void userInteractionOptions(ArrayList<Course> courseList) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("==================What would you like to do?========================\n" +
                    "- to view your courses for a specific day, type in - [D]\n" +
                    "- to see your course schedule, type in - [S]\n" +
                    "- to add a course to your schedule, type in - [C]\n" +
                    "- to quit, type in - [Q]");
            String input = scanner.nextLine();


            if (input.equals("C")) {
                Course firstCourse = new Course();
                courseList.add(firstCourse);
                System.out.println("Please enter name of the first course.");
                firstCourse.setName(scanner.nextLine());
                System.out.println("Please enter start of the " + firstCourse.getName() + " lecture time (use integers 0-24).");
                firstCourse.setStartTime(scanner.nextInt());
                System.out.println("How long are " + firstCourse.getName() + " lectures (in # of hours)?");
                firstCourse.setLength(scanner.nextInt());
                scanner.nextLine();
                Boolean[] weekDays = new Boolean[5];
                while (true) {
                    System.out.println("Enter one day which " + firstCourse.getName() + " lecture is on [m,tue,w,th,f]?\n" +
                            "Or enter [q] if there are no other days.");
                    String day = scanner.nextLine();
                    if (day.equals("q")) {
                        break;
                    } else {
                        weekDays = enterDays(weekDays, day);
                    }
                }
                firstCourse.setNewWeekDays(weekDays);
            }


            else if (input.equals("S")) {
                printCourses(courseList);
            }



            else if (input.equals("D")) {
                System.out.println("Enter the day you are interested in.");
                String d = scanner.nextLine();
                System.out.println("These are your courses on " + d + ": ");
                if (d.equals("m")) {
                    printCourses(findCoursesOnDay(courseList, 0));
                }
                if (d.equals("tue")) {
                    printCourses(findCoursesOnDay(courseList, 1));
                }
                if (d.equals("w")) {
                    printCourses(findCoursesOnDay(courseList, 2));
                }
                if (d.equals("th")) {
                    printCourses(findCoursesOnDay(courseList, 3));
                }
                if (d.equals("f")) {
                    printCourses(findCoursesOnDay(courseList, 4));
                }
            }


            else if (input.equals("Q")) {
                break;
            }
        }
    }

    public static void printCourses(ArrayList<Course> courseList) {
        for (Course c : courseList) {
            System.out.println("--------------------");
            System.out.println(c.getName() + ":");
            System.out.println("Starts at: " + c.getStartTime());
            System.out.println("Ends at: " + c.getEndTime());
        }
    }


    public static Boolean[] enterDays(Boolean[] weekDays, String day) {
        if (day.equals("m")) {
            weekDays[0] = true;
        }
        else if (day.equals("tue")) {
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

    public static ArrayList<Course> findCoursesOnDay(ArrayList<Course> courseList, int d) {
        ArrayList<Course> coursesThatDay = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getForIndex(d)){
                coursesThatDay.add(c);
            }
        }
        return coursesThatDay;
    }


    public static void createSchedule(ArrayList<Course> courseList) {
        Course courseA = new Course();
        courseA.setName("COMM 290");
        courseA.setStartTime(8);
        courseA.setWeekDays (true,false,true,false,false);

        Course courseB = new Course();
        courseB.setName("COMM 205");
        courseB.setStartTime(10);
        courseB.setWeekDays (false,false,true,false,true);

        Course courseC = new Course();
        courseC.setName("COMM 295");
        courseC.setStartTime(9);
        courseC.setWeekDays (false,true,false,true,false);

        courseList.add(courseA);
        courseList.add(courseB);
        courseList.add(courseC);
    }

    public static void main(String[] args) {

        ArrayList<Course> courseList = new ArrayList<>();
        createSchedule(courseList);
        userInteractionOptions(courseList);
    }
}