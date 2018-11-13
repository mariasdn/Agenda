package ui;

import exceptions.InvalidArgumentException;
import model.Finder;

import java.util.Arrays;
import java.util.Objects;

public class ScheduleItem implements Finder {
    protected String name;
    protected String itemType;
    protected int startTime;
    protected int length;
    protected int endTime;
    protected boolean[] weekDays;

    public ScheduleItem (String itemType, String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        if (inputsValid(name, startTime, length, weekDays)) {
            this.itemType = itemType;
            this.name = name;
            this.length = length;
            this.startTime = startTime;
            endTime = startTime + length;
            this.weekDays = new boolean[5];
            this.weekDays[0] = weekDays[0];
            this.weekDays[1] = weekDays[1];
            this.weekDays[2] = weekDays[2];
            this.weekDays[3] = weekDays[3];
            this.weekDays[4] = weekDays[4];
        } else {
            throw new InvalidArgumentException("Invalid arguments");
        }
    }

    public String getType() {
        return this.itemType;
    }

    public String getName() {
        return this.name;
    }

    public String toSave() {
        return itemType + "," + name + "," + startTime + "," + length + ","
                + weekDays[0] + "," + weekDays[1] + "," + weekDays[2] + "," + weekDays[3] + "," + weekDays[4];
    }

    public boolean checkItemName(String name) {
        return this.getName().equals(name);
    }

    @Override
    public String toString() {
        return "---------------------------\n" + getName() +
                "\nStart time: " + startTime +
                "\nEnd time: " + endTime;
    }

    public boolean isOnDay(String day) {
        if (day.equals("m")) {
            return weekDays[0];
        } else if (day.equals("t")) {
            return weekDays[1];
        } else if (day.equals("w")) {
            return weekDays[2];
        } else if (day.equals("th")) {
            return weekDays[3];
        } else if (day.equals("f")) {
            return weekDays[4];
        } else {
            return false;
        }
    }

    public static boolean isDayValid (String day) {
        if (day.equals("m")) {
            return true;
        } else if (day.equals("t")) {
            return true;
        } else if (day.equals("w")) {
            return true;
        } else if (day.equals("th")) {
            return true;
        } else if (day.equals("f")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean inputsValid (String name, int startTime, int length, boolean[] weekDays) {
        return (name != null && startTime > 0 && length > 0 && weekDays.length == 5);
    }


    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS:  returns the start time of the course
    public int getStartTime() {
        return startTime;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS:  returns the end time of the course
    public int getEndTime() {
        return endTime;
    }


    //REQUIRES: an integer value length between 0 and 10
    //MODIFIES: this
    //EFFECTS:  sets the length of class and adjusts its end time according to it
    public void setLength(int length) {
        this.length = length;
        this.endTime = this.startTime + this.length;
    }

    //REQUIRES: an integer value start time between 0 and 24
    //MODIFIES: this
    //EFFECTS:  sets the start time of class and adjusts its end time according to it
    public void setStartTime(int startTime) {
        this.startTime = startTime;
        this.endTime = this.startTime + this.length;
    }

    //REQUIRES: 5 boolean values corresponding with weekdays the class is on
    //MODIFIES: this
    //EFFECTS:  sets the entire weekday boolean array with passed boolean parameters
    public void setWeekDays(Boolean m, Boolean t, Boolean w, Boolean th, Boolean f) {
        weekDays[0] = m;
        weekDays[1] = t;
        weekDays[2] = w;
        weekDays[3] = th;
        weekDays[4] = f;
    }

}
