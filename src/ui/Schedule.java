package ui;


import exceptions.InvalidArgumentException;
import exceptions.InvalidWeekDayException;
import model.Editor;
import model.Viewer;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class Schedule implements Editor, Viewer {

    private ArrayList<ScheduleItem> items;
    private Map<String, ScheduleItem> itemMap;


    //REQUIRES: nothing
    //MODIFIES: this, weekDays
    //EFFECTS:  constructs a schedule with a course list that has set courses in it
    public Schedule() {
        items = new ArrayList<>();
        itemMap = new HashMap<>();

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
        ArrayList<ScheduleItem> courseList = new ArrayList<>();
        ArrayList<ScheduleItem> labList = new ArrayList<>();
        ArrayList<ScheduleItem> activityList = new ArrayList<>();

        for(ScheduleItem si: items) {
            if (si.getType().equals("Course")) {
                courseList.add(si);
            } else if (si.getType().equals("Lab")){
                labList.add(si);
            } else if (si.getType().equals("Activity")){
                activityList.add(si);
            }
        }
        System.out.println("Courses:");
        for (ScheduleItem c : courseList) {
            System.out.println(c);
        }
        System.out.println("\nLabs:");
        for (ScheduleItem l : labList) {
            System.out.println(l);
        }
        System.out.println("\nActivities:");
        for (ScheduleItem a : activityList) {
            System.out.println(a);
        }
    }

    public void viewItemsOnDay(String day) throws InvalidWeekDayException{
        if (ScheduleItem.isDayValid(day)) {

            ArrayList<ScheduleItem> courseList = new ArrayList<>();
            ArrayList<ScheduleItem> labList = new ArrayList<>();
            ArrayList<ScheduleItem> activityList = new ArrayList<>();

            for(ScheduleItem si: items) {
                if (si.isOnDay(day)) {
                    if (si.getType().equals("Course")) {
                        courseList.add(si);
                    } else if (si.getType().equals("Lab")) {
                        labList.add(si);
                    } else if (si.getType().equals("Activity")) {
                        activityList.add(si);
                    }
                }
            }
            System.out.println("Courses:");
            for (ScheduleItem c : courseList) {
                System.out.println(c);
            }
            System.out.println("\nLabs:");
            for (ScheduleItem l : labList) {
                System.out.println(l);
            }
            System.out.println("\nActivities:");
            for (ScheduleItem a : activityList) {
                System.out.println(a);
            }

        } else {
            throw new InvalidWeekDayException("You have entered an invalid week day.");
        }
    }

    public void viewItemsByName(String subject) {
        boolean areThereNone = true;
        if (itemMap.get(subject)!= null) {
            System.out.println(itemMap.get(subject));
            areThereNone = false;
        }
        if (areThereNone) {
            System.out.println("!!!! There are either no items with this name in your schedule !!!!\n" +
                    "!!!!              or entered name is incorrect.                !!!!\n");
        }
    }

    public void addItem(String[] sr) {
        if(sr.length == 9){
            String itemType = sr[0];
            String name = sr[1];
            int st = Integer.parseInt(sr[2]);
            int l = Integer.parseInt(sr[3]);
            boolean m = Boolean.valueOf(sr[4]);
            boolean t = Boolean.valueOf(sr[5]);
            boolean w = Boolean.valueOf(sr[6]);
            boolean th = Boolean.valueOf(sr[7]);
            boolean f = Boolean.valueOf(sr[8]);
            boolean[] wd = {m, t, w, th, f};
            try {
                ScheduleItem si = new ScheduleItem(itemType,name,st,l,wd);
                itemMap.put(name,si);
                items.add(si);
            } catch (InvalidArgumentException e) {
                System.out.println("Saved file is corrupted");
            }
        }
    }

    public void addItem(String itemType, String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        try {
            ScheduleItem newItem = new ScheduleItem(itemType,name, startTime, length, weekDays);
            items.add(newItem);
            itemMap.put(name,newItem);
        } catch (InvalidArgumentException e) {
            throw e;
        }
    }

    public ArrayList<String> itemsAsString() {
        ArrayList<String> results = new ArrayList<>();
        for (ScheduleItem c: items) {
            results.add( c.toSave());
        }
        return results;
    }


    public void deleteItem(String name){
        boolean areThereNone = true;
        ScheduleItem si = itemMap.get(name);
        if (si!= null) {
            items.remove(si);
            itemMap.remove(name);
            System.out.println(si);
            System.out.println(si.getType() + " was successfully DELETED");
            areThereNone = false;
        }
        if (areThereNone) {
            System.out.println("!!!! There are either no items with this name in your schedule !!!!\n" +
                    "!!!!              or entered name is incorrect.                !!!!\n");
        }
    }

}
