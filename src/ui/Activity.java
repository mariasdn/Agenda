package ui;

import exceptions.InvalidArgumentException;

public class Activity extends ScheduleItem {

    public Activity (String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        super(name,startTime,length,weekDays);
        this.itemType = "activity";
    }
}
