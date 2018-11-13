package model;

import exceptions.InvalidArgumentException;

public interface Editor {
    void addItem(String itemType, String name, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException;
    void deleteItem(String name);

}
