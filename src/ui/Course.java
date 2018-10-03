package ui;


import model.Finder;

public class Course implements Finder {
    private String code;
    private String num;
    private int length;
    private int startTime;
    private int endTime;
    private boolean[] weekDays;
    public static int numCourses = 0;

    //REQUIRES: string value name, an integer value between start time between 0 and 24,
    //          an integer value length between 0 and 10, boolean array week days
    //MODIFIES: this
    //EFFECTS:  Constructs the course initialising its name, start time, length and week days its on according to
    //          the parameters, as well increasing number of courses by 1
    public Course (String code, String num, int startTime, int length, boolean[] weekDays) throws IllegalArgumentException {
        if (inputsValid (code, num, startTime, length, weekDays)) {
            this.code = code;
            this.num = num;
            this.length = length;
            this.startTime = startTime;
            endTime = startTime + length;
            this.weekDays = new boolean[5];
            this.weekDays[0] = weekDays[0];
            this.weekDays[1] = weekDays[1];
            this.weekDays[2] = weekDays[2];
            this.weekDays[3] = weekDays[3];
            this.weekDays[4] = weekDays[4];
            numCourses++;
        } else {
            throw new IllegalArgumentException("Invalid arguments");
        }

    }

    public static boolean inputsValid (String code, String num, int startTime, int length, boolean[] weekDays) {
        return (code != null && num != null && startTime > 0 && length > 0 && weekDays.length == 5);
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

    public boolean checkCourseSubject(String subject) {
        return this.code.equals(subject);
    }

    public String toString() {
        return "---------------------------\n" + code + " " + num +
        "\nStart time: " + startTime +
        "\nEnd time: " + endTime;
    }

    public String toSave() {
        return code + "," + num + "," + startTime + "," + length + ","
                + weekDays[0] + "," + weekDays[1] + "," + weekDays[2] + "," + weekDays[3] + "," + weekDays[4];
    }


}
