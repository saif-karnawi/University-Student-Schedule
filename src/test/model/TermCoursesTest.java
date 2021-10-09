package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TermCoursesTest {

    TermCourses termOne;

    @BeforeEach
    public void runBefore() {
        termOne = new TermCourses();
    }

    @Test
    public void testAddCourse() {
        //empty term, no courses added yet
        assertEquals(0,termOne.getTermCourses().size());

        //Add a course
        LinkedList<String> days = new LinkedList<String>();
        days.add("Thursday");
        days.add("Tuesday");
        Course newCourse = new Course("CPSC 210", 8, 4, 3, days, 1300);
        termOne.addCourse(newCourse);

        assertEquals(1,termOne.getTermCourses().size());
    }

    @Test
    public void testGetTermCourses() {

    }

    @Test
    public void testGetDailyMaxHours() {

    }

    @Test
    public void testGetMonthlyMaxHours() {

    }

    @Test
    public void testGetMonthlyMinHours() {

    }

    @Test
    public void testTermDifficulty() {

    }

}