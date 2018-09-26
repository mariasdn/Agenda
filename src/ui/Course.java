package ui;


public class Course {
    private String name;
    private int length;
    private int startTime;
    private int endTime;
    private Boolean[] weekDays;
    public static int numCourses = 0;

    //REQUIRES: string value name, an integer value between start time between 0 and 24,
    //          an integer value length between 0 and 10, boolean array week days
    //MODIFIES: this
    //EFFECTS:  Constructs the course initialising its name, start time, length and week days its on according to
    //          the parameters, as well increasing number of courses by 1
    public Course (String name, int startTime, int length, Boolean[] weekDays) {
        this.name = name;
        this.length = length;
        this.startTime = startTime;
        endTime = startTime + length;
        this.weekDays = new Boolean[5];
        this.weekDays[0] = weekDays[0];
        this.weekDays[1] = weekDays[1];
        this.weekDays[2] = weekDays[2];
        this.weekDays[3] = weekDays[3];
        this.weekDays[4] = weekDays[4];
        numCourses++;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS:  returns the name of the course
    public String getName() {
        return name;
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

    //REQUIRES: string value name
    //MODIFIES: this
    //EFFECTS:  sets clown's name to the one given as a parameter
    public void setName(String name) {
        this.name = name;
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

    //REQUIRES: an integer between 0 and 4
    //MODIFIES: nothing
    //EFFECTS:  returns true if the course lecture is on this day of the week, false otherwise
    public Boolean isCourseOnThisDay(int d) {
        return weekDays[d];
    }


}
