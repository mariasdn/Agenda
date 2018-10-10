package ui;

import model.Finder;

public abstract class ScheduleItem implements Finder {
    protected String itemType;
    protected int startTime;
    protected int length;
    protected int endTime;
    protected boolean[] weekDays;

    abstract String getName ();
    abstract String toSave ();

    public boolean checkItemName(String name) {
        return this.getName().equals(name);
    }

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
