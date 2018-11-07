package model;

public interface Finder {
    boolean isOnDay(String day);
    boolean checkItemName(String name);
    boolean inputsValid (String name, int startTime, int length, boolean[] weekDays);
}
