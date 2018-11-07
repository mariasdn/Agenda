package ui;

import exceptions.InvalidArgumentException;

public class Lab extends ScheduleItem {

    public Lab (String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        super(name,startTime,length,weekDays);
        this.itemType = "lab";
    }
}
