package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ui.Agenda;
import ui.Course;
import ui.Schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSaveAndLoad {
    Schedule s;
    Course c;
    Agenda myAgenda;
    private String code;
    private String num;
    private int startTime;
    private int length;
    boolean[] b = {true, false, true, false, false};

    @BeforeEach
    public void doEachTime() {
        myAgenda = new Agenda();
        s = new Schedule();
        code = "CPSC";
        num = "213";
        startTime = 12;
        length = 3;
        c = new Course(code, num, startTime, length, b);
    }

    /*@Test
    public void testSaveAndLoad() {
        myAgenda.s.addCourse(code, num, startTime, length, b);
        myAgenda.saveEdits();
        Agenda agenda2 = new Agenda();
        assertEquals(agenda2.s.getCourseList(), myAgenda.s.getCourseList());
    }*/

}
