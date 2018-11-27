package ui;


import exceptions.InvalidArgumentException;
import exceptions.InvalidWeekDayException;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<ScheduleItem> items;
    private Map<String, ScheduleItem> itemMap;


    //REQUIRES: nothing
    //MODIFIES: this, weekDays
    //EFFECTS:  constructs a schedule with a course list that has set courses in it
    public Schedule() {
        items = new ArrayList<>();
        itemMap = new HashMap<>();
        boolean[] weekD = {true,true,false,false,true};
        try{
            addItem("Course","COMM 101", 12,2, weekD);
            addItem("Course","COMM 102", 12,2, weekD);
            addItem("Course","COMM 103", 12,2, weekD);
            addItem("Course","COMM 104", 12,2, weekD);
            addItem("Course","COMM 105", 12,2, weekD);
        } catch(InvalidArgumentException e) {

        }

    }

    public String[] viewSchedule() {
        String[] result = new String[items.size()+3];
        int index = 0;
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
        result[index] = "===========================\nCourses:";
        for (ScheduleItem c : courseList) {
            index ++;
            result[index] = c.toString();
        }
        index++;
        result[index] = "===========================\nLabs:";
        for (ScheduleItem l : labList) {
            index ++;
            result[index] = l.toString();
        }
        index++;
        result[index] = "===========================\nActivities:";
        for (ScheduleItem a : activityList) {
            index++;
            result[index] = a.toString();
        }
        return result;
    }

    public ArrayList<String> viewItemsOnDay(String day) throws InvalidWeekDayException{
        if (ScheduleItem.isDayValid(day)) {
            ArrayList<String> result = new ArrayList<String>();
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
            result.add("===========================\nCourses:");
            for (ScheduleItem c : courseList) {
                result.add(c.toString());
            }
            result.add("===========================\nLabs:");
            for (ScheduleItem l : labList) {
                result.add(l.toString());
            }
            result.add("===========================\nActivities:");
            for (ScheduleItem a : activityList) {
                result.add(a.toString());
            }
            return result;

        } else {
            throw new InvalidWeekDayException("You have entered an invalid week day.");
        }
    }

    public String viewItemsByName(String subject) {
        if (itemMap.get(subject)!= null) {
            return itemMap.get(subject).toString();
        }
        return "!!!!  There are either no items with  !!!!\n        this name in your schedule\n       or entered name is incorrect.\n";
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

    public String addItem(String itemType, String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        try {
            ScheduleItem newItem = new ScheduleItem(itemType,name, startTime, length, weekDays);
            items.add(newItem);
            itemMap.put(name,newItem);
            return newItem.toString();
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


    public String deleteItem(String name){
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
        return si.toString();
    }

}
