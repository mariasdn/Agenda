package tests;

import org.junit.jupiter.api.BeforeEach;
import ui.Course;
import ui.Schedule;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSchedule {
    Schedule s;
    Course c;
    boolean[] b = {true, false, true, false, false};
    private String code;
    private String num;
    private int startTime;
    private int length;

    @BeforeEach
    public void doEachTime() {
        s = new Schedule();
        code = "CPSC";
        num = "213";
        startTime = 12;
        length = 3;
        c = new Course(code, num, startTime, length, b);
    }

    @Test
    public void testCoursesAsString() {
        //s.addCourse(code, num, startTime, length, b);
        String st = "CPSC,213,12,3,true,false,true,false,false";
        ArrayList<String> sa = new ArrayList<>();
        sa.add(st);
        s.addCourse(code, num, startTime, length, b);
        assertEquals(s.coursesAsString(), sa);
    }
}
