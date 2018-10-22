package ui;

import exceptions.InvalidArgumentException;

public class Activity extends ScheduleItem {
    private String name;

    public Activity (String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException {
        if (inputsValid(name, startTime, length, weekDays)) {
            this.itemType = "activity";
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

    public boolean inputsValid (String name, int startTime, int length, boolean[] weekDays) {
        return (name != null && startTime > 0 && length > 0 && weekDays.length == 5);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toSave() {
        return itemType + "," + name + "," + startTime + "," + length + ","
                + weekDays[0] + "," + weekDays[1] + "," + weekDays[2] + "," + weekDays[3] + "," + weekDays[4];
    }
}
