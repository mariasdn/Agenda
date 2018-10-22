package model;

import exceptions.InvalidArgumentException;

public interface Editor {
    void addCourse(String code, String num, int startTime, int length, boolean[] weekDays) throws InvalidArgumentException;
    //void deleteCourse();
    //void changeCourse();

}
