package tests;

import exceptions.InvalidArgumentException;
import exceptions.InvalidWeekDayException;
import org.junit.jupiter.api.BeforeEach;
import ui.Course;
import ui.Schedule;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.fail;

public class TestSchedule {
    Schedule s;
    Course c;
    boolean[] b = {true, false, true, false, false};
    private String code;
    private String num;
    private int startTime;
    private int length;
    private String st;
    private ArrayList sa;

    @BeforeEach
    public void doEachTime() {
        s = new Schedule();
        code = "CPSC";
        num = "213";
        startTime = 12;
        length = 3;
        try {
            c = new Course(code, num, startTime, length, b);
        } catch (InvalidArgumentException e) {

        }
        st = "course,CPSC,213,12,3,true,false,true,false,false";
        sa = new ArrayList<String>();
        sa.add(st);


    }

    @Test
    public void testCoursesAsRightString() {
        try {
            s.addCourse(code, num, startTime, length, b);
        } catch (InvalidArgumentException e) {
            fail("Unexpected exception");
        }
        assertEquals(s.coursesAsString(), sa);
    }

    @Test
    public void testAddCourseProperly() {
        try {
            s.addCourse(code, num, startTime, length, b);
            assertTrue(true);
        } catch (InvalidArgumentException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testAddCourseIncorrectly() {
        try {
            s.addCourse(code, num, startTime, 0, b);
            fail("Expecting an exception");
        } catch (InvalidArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddLabProperly() {
        try {
            s.addLab(code, num, startTime, length, b);
            assertTrue(true);
        } catch (InvalidArgumentException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testAddLabIncorrectly() {
        try {
            s.addLab(code, num, startTime, 0, b);
            fail("Expecting an exception");
        } catch (InvalidArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddActivityProperly() {
        try {
            s.addActivity("Yoga", startTime, length, b);
            assertTrue(true);
        } catch (InvalidArgumentException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testAddActivityIncorrectly() {
        try {
            s.addActivity("Yoga", startTime, 0, b);
            fail("Expecting an exception");
        } catch (InvalidArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testItemsOnDayIncorrectly() {
        try {
            s.viewItemsOnDay("q");
            fail("Expecting an exception");
        } catch (InvalidWeekDayException e) {
            assertTrue(true);
        }
    }
}
