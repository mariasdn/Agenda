package ui;


import exceptions.InvalidArgumentException;

public class Course extends ScheduleItem {
    //REQUIRES: string value name, an integer value between start time between 0 and 24,
    //          an integer value length between 0 and 10, boolean array week days
    //MODIFIES: this
    //EFFECTS:  Constructs the course initialising its name, start time, length and week days its on according to
    //          the parameters, as well increasing number of courses by 1
    public Course (String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        super(name,startTime,length,weekDays);
        this.itemType = "course";
    }
}
