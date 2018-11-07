package model;

import exceptions.InvalidArgumentException;

public interface Editor {
    void addCourse(String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException;
    void addLab(String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException;
    void addActivity(String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException;
    void deleteItem(String name);

}
