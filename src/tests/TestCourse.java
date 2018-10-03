package tests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ui.Course;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCourse {
    Course c;
    private String code;
    private String num;
    private int startTime;
    private int length;
    boolean[] b = {true, false, true, false, false};

    @BeforeEach
    public void doEachTime() {
        code = "CPSC";
        num = "213";
        startTime = 12;
        length = 3;
        c = new Course(code, num, startTime, length, b);
    }

    @Test
    public void testInputsValid() {
        length = 0;
        assertFalse(c.inputsValid(code, num, startTime, length, b));
    }

    @Test
    public void testIsOnDay() {
        assertTrue(c.isOnDay("m"));
        assertFalse(c.isOnDay("t"));
    }

    @Test
    public void testIsDayValid() {
        assertTrue(c.isDayValid("f"));
        assertFalse(c.isDayValid("g"));
    }

    @Test
    public void testCheckCourseSubject() {
        assertTrue(c.checkCourseSubject("CPSC"));
        assertFalse(c.checkCourseSubject("123"));
    }


}
