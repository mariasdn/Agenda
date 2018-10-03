package ui;


import model.Editor;
import model.Viewer;

import java.util.ArrayList;
import java.util.Scanner;

public class Schedule implements Editor, Viewer {

    private ArrayList<Course> courseList;

    //REQUIRES: nothing
    //MODIFIES: this, weekDays
    //EFFECTS:  constructs a schedule with a course list that has set courses in it
    public Schedule() {
        courseList = new ArrayList<>();

        /*boolean[] b = {true, false, true, false, false};

        Course courseA = new Course("COMM", "290", 8, 2, b);

        Course courseB = new Course("COMM", "205", 10, 2, b);
        courseB.setWeekDays(false, false, true, false, true);

        Course courseC = new Course("COMM", "295", 9, 2, b);
        courseC.setWeekDays(false, true, false, true, false);

        courseList.add(courseA);
        courseList.add(courseB);
        courseList.add(courseC);*/

    }

    public void viewSchedule() {
        for (Course c : courseList) {
            System.out.println(c);
        }
    }

    public void viewCoursesOnDay(String day) {
        if (Course.isDayValid(day)) {
            for (Course c : courseList) {
                if (c.isOnDay(day)) {
                    System.out.println(c);
                }
            }
        } else {
            System.out.println("You have entered an invalid week day.");
        }
    }

    public void viewCoursesBySubject(String subject) {
        boolean areThereNone = true;
        for (Course c : courseList) {
            if (c.checkCourseSubject(subject)) {
                System.out.println(c);
                areThereNone = false;
            }
        }
        if (areThereNone) {
            System.out.println("!!!! There are either no courses of this subject in your schedule !!!!\n" +
                    "!!!!              or entered subject code is incorrect.           !!!!\n");
        }
    }

    public void addCourse(String code, String num, int startTime, int length, boolean[] weekDays) throws IllegalArgumentException {
        try {
            Course newCourse = new Course(code, num, startTime, length, weekDays);
            courseList.add(newCourse);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public void addCourse (String[] sr) {
        String c = sr[0];
        String n = sr[1];
        int st = Integer.parseInt(sr[2]);
        int l = Integer.parseInt(sr[3]);
        boolean m = Boolean.valueOf(sr[4]);
        boolean t = Boolean.valueOf(sr[5]);
        boolean w = Boolean.valueOf(sr[6]);
        boolean th = Boolean.valueOf(sr[7]);
        boolean f = Boolean.valueOf(sr[8]);
        boolean[] wd = {m,t,w,th,f};
        Course newCourse = new Course(c,n,st,l,wd);
        courseList.add(newCourse);
    }

    public ArrayList<String> coursesAsString() {
        ArrayList<String> courses = new ArrayList<>();
        for (Course c: courseList) {
            courses.add( c.toSave());
        }
        return courses;
    }

/*
    public void deleteCourse(){

    }

    public void changeCourse() {

    }
}
*/


}
