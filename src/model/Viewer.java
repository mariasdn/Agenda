package model;

import exceptions.InvalidWeekDayException;

public interface Viewer {
    void viewSchedule();
    void viewItemsOnDay(String day) throws InvalidWeekDayException;
    void viewItemByName(String subject);
}
