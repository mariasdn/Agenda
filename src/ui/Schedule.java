package ui;


import exceptions.InvalidArgumentException;
import exceptions.InvalidWeekDayException;
import model.Editor;
import model.Viewer;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class Schedule implements Editor, Viewer {

    private Map<String, Course> courseMap;
    private Map<String, Lab> labMap;
    private Map<String, Activity> activityMap;
    private ArrayList<Course> courseList;
    private ArrayList<Lab> labList;
    private ArrayList<Activity> activityList;


    //REQUIRES: nothing
    //MODIFIES: this, weekDays
    //EFFECTS:  constructs a schedule with a course list that has set courses in it
    public Schedule() {
        courseMap = new HashMap<>();
        labMap = new HashMap<>();
        activityMap = new HashMap<>();
        courseList = new ArrayList<>();
        labList = new ArrayList<>();
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
        System.out.println("\nLabs:");
        for (Lab l : labList) {
            System.out.println(l);
        }
        System.out.println("\nActivities:");
        for (Activity a : activityList) {
            System.out.println(a);
        }
    }

    public void viewItemsOnDay(String day) throws InvalidWeekDayException{
        if (Course.isDayValid(day)) {
            System.out.println("Courses:");
            for (Course c : courseList) {
                if (c.isOnDay(day)) {
                    System.out.println(c);
                }
            }
            System.out.println("\nLabs:");
            for (Lab l : labList) {
                if (l.isOnDay(day)) {
                    System.out.println(l);
                }
            }
            System.out.println("\nActivities:");
            for (Activity a : activityList) {
                if (a.isOnDay(day)) {
                    System.out.println(a);
                }
            }

        } else {
            throw new InvalidWeekDayException("You have entered an invalid week day.");
        }
    }

    public void viewItemsByName(String subject) {
        boolean areThereNone = true;
        if (courseMap.get(subject)!= null) {
            System.out.println(courseMap.get(subject));
            areThereNone = false;
        } else if (labMap.get(subject)!= null) {
            System.out.println(labMap.get(subject));
            areThereNone = false;
        } else if (activityMap.get(subject)!= null) {
            System.out.println(activityMap.get(subject));
            areThereNone = false;
        }
        if (areThereNone) {
            System.out.println("!!!! There are either no items with this name in your schedule !!!!\n" +
                    "!!!!              or entered name is incorrect.                !!!!\n");
        }
    }

    public void addCourse(String code, String num, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        try {
            Course newCourse = new Course(code, num, startTime, length, weekDays);
            courseList.add(newCourse);
            courseMap.put(code + " " + num,newCourse);
        } catch (InvalidArgumentException e) {
            throw e;
        }
    }

    public void addCourse (String[] sr) {
        try {
            String c = sr[1];
            String n = sr[2];
            int st = Integer.parseInt(sr[3]);
            int l = Integer.parseInt(sr[4]);
            boolean m = Boolean.valueOf(sr[5]);
            boolean t = Boolean.valueOf(sr[6]);
            boolean w = Boolean.valueOf(sr[7]);
            boolean th = Boolean.valueOf(sr[8]);
            boolean f = Boolean.valueOf(sr[9]);
            boolean[] wd = {m, t, w, th, f};
            Course newCourse = new Course(c, n, st, l, wd);
            courseList.add(newCourse);
            courseMap.put(c + " " + n,newCourse);
        } catch (InvalidArgumentException e) {
            System.out.println("Saved file is corrupted");
        }
    }


    public void addLab(String code, String num, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        try {
            Lab newLab = new Lab(code, num, startTime, length, weekDays);
            labList.add(newLab);
            labMap.put(code + " " + num,newLab);
        } catch (InvalidArgumentException e) {
            throw e;
        }
    }

    public void addLab (String[] sr) {
        try {
            String c = sr[1];
            String n = sr[2];
            int st = Integer.parseInt(sr[3]);
            int l = Integer.parseInt(sr[4]);
            boolean m = Boolean.valueOf(sr[5]);
            boolean t = Boolean.valueOf(sr[6]);
            boolean w = Boolean.valueOf(sr[7]);
            boolean th = Boolean.valueOf(sr[8]);
            boolean f = Boolean.valueOf(sr[9]);
            boolean[] wd = {m, t, w, th, f};
            Lab newLab = new Lab(c, n, st, l, wd);
            labList.add(newLab);
            labMap.put(c + " " + n,newLab);
        } catch (InvalidArgumentException e) {
            System.out.println("Saved file is corrupted");
        }
    }

    public void  addActivity(String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        try {
            Activity newActivity = new Activity(name, startTime, length, weekDays);
            activityList.add(newActivity);
            activityMap.put(name,newActivity);
        } catch (InvalidArgumentException e) {
            throw e;
        }
    }

    public void addActivity (String[] sr) {
        try {
            String name = sr[1];
            int st = Integer.parseInt(sr[2]);
            int l = Integer.parseInt(sr[3]);
            boolean m = Boolean.valueOf(sr[4]);
            boolean t = Boolean.valueOf(sr[5]);
            boolean w = Boolean.valueOf(sr[6]);
            boolean th = Boolean.valueOf(sr[7]);
            boolean f = Boolean.valueOf(sr[8]);
            boolean[] wd = {m, t, w, th, f};
            Activity newActivity = new Activity(name, st, l, wd);
            activityList.add(newActivity);
            activityMap.put(name,newActivity);
        } catch (InvalidArgumentException e) {
            System.out.println("Saved file is corrupted");
        }
    }

    public ArrayList<String> coursesAsString() {
        ArrayList<String> courses = new ArrayList<>();
        for (Course c: courseList) {
            courses.add( c.toSave());
        }
        return courses;
    }

    public ArrayList<String> labsAsString() {
        ArrayList<String> labs = new ArrayList<>();
        for (Lab l: labList) {
            labs.add( l.toSave());
        }
        return labs;
    }

    public ArrayList<String> activitiesAsString() {
        ArrayList<String> activities = new ArrayList<>();
        for (Activity a: activityList) {
            activities.add( a.toSave());
        }
        return activities;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }


    public void deleteItem(String name){
        boolean areThereNone = true;
        Course c = courseMap.get(name);
        Lab l = labMap.get(name);
        Activity a = activityMap.get(name);
        if (c!= null) {
            courseList.remove(c);
            courseMap.remove(name);
            System.out.println(c);
            System.out.println("COURSE WAS SUCCESSFULLY DELETED");
            areThereNone = false;
        }
        if (l!= null) {
            labList.remove(l);
            labMap.remove(name);
            System.out.println(l);
            System.out.println("LAB WAS SUCCESSFULLY DELETED");
            areThereNone = false;
        }
        if (a!= null) {
            activityList.remove(a);
            activityMap.remove(name);
            System.out.println(a);
            System.out.println("ACTIVITY WAS SUCCESSFULLY DELETED");
            areThereNone = false;
        }
        if (areThereNone) {
            System.out.println("!!!! There are either no items with this name in your schedule !!!!\n" +
                    "!!!!              or entered name is incorrect.                !!!!\n");
        }
    }
/*
    public void changeCourse() {

    }
}
*/


}
