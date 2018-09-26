package tests;

import ui.Course;
import ui.Schedule;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestShedule {
    Course c;
    Boolean[] b = {true, false, true, false, false};

    @Test
    public void testFindCursesOnDay () {
        Schedule schedule = new Schedule();
        ArrayList<Course> courses = new ArrayList();
        c = new Course("COMM 290", 8,2, b);
        courses.add(c);
        assertEquals(schedule.findCoursesOnDay(0), courses);
    }

}
