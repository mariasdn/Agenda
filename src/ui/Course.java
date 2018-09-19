package ui;

import java.util.ArrayList;

public class Course {
    private String name;
    private int length;
    private int startTime;
    private int endTime;
    private Boolean[] weekDays;

    public Course () {
        name = "";
        length = 2;
        startTime = 12;
        endTime = startTime + length;
        weekDays = new Boolean[5];
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setWeekDays(Boolean m, Boolean tue, Boolean w, Boolean th, Boolean f) {
        weekDays[0] = m;
        weekDays[1] = tue;
        weekDays[2] = w;
        weekDays[3] = th;
        weekDays[4] = f;
    }

    public void setNewWeekDays(Boolean[] weekDays) {
        this.weekDays = weekDays;
    }

    public Boolean getForIndex(int d) {
        return weekDays[d];
    }


}
