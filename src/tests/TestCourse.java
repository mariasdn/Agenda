package tests;

import ui.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCourse {
    Course c;
    Boolean[] b = {true, false, true, false, false};

   /* @BeforeEach
    public void doEachTime {

    }*/


    @Test
    public void testCourseConstructor() {
        c = new Course("COMM 290", 8,2, b);
        assertEquals(c.getName(), "COMM 290");
        assertEquals(c.getEndTime(), 8+2);
        assertEquals(c.getStartTime(), 8);
    }

    @Test
    public void testIsCourceOnThisDay() {
        c = new Course("COMM 295", 8,2, b);
        assertTrue(c.isCourseOnThisDay(0));
    }
}
