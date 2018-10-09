package ui;


import model.Editor;
import model.Viewer;

import java.util.ArrayList;

public class Schedule implements Editor, Viewer {

    private ArrayList<Course> courseList;
    private ArrayList<Activity> activityList;

    //REQUIRES: nothing
    //MODIFIES: this, weekDays
    //EFFECTS:  constructs a schedule with a course list that has set courses in it
    public Schedule() {
        courseList = new ArrayList<>();
        activityList = new ArrayList<>();

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
        System.out.println("Courses:");
        for (Course c : courseList) {
            System.out.println(c);
        }
        System.out.println("\nActivities:");
        for (Activity a : activityList) {
            System.out.println(a);
        }
    }

    public void viewItemsOnDay(String day) {
        if (Course.isDayValid(day)) {
            System.out.println("Courses:");
            for (Course c : courseList) {
                if (c.isOnDay(day)) {
                    System.out.println(c);
                }
            }

            System.out.println("\nActivities:");
            for (Activity a : activityList) {
                if (a.isOnDay(day)) {
                    System.out.println(a);
                }
            }

        } else {
            System.out.println("You have entered an invalid week day.");
        }
    }

    public void viewItemByName(String subject) {
        boolean areThereNone = true;
        for (Course c : courseList) {
            if (c.checkItemName(subject)) {
                System.out.println(c);
                areThereNone = false;
            }
        }
        for (Activity a : activityList) {
            if (a.checkItemName(subject)) {
                System.out.println(a);
                areThereNone = false;
            }
        }
        if (areThereNone) {
            System.out.println("!!!! There are either no items with this name in your schedule !!!!\n" +
                    "!!!!              or entered name is incorrect.                !!!!\n");
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

    public void  addActivity(String name, int startTime, int length, boolean[] weekDays) throws IllegalArgumentException {
        try {
            Activity newActivity = new Activity(name, startTime, length, weekDays);
            activityList.add(newActivity);
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

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    /*
    public void deleteCourse(){

    }

    public void changeCourse() {

    }
}
*/


}
