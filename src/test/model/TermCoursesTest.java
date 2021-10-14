package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

//tests for the TermCourses class
class TermCoursesTest {

    TermCourses termOne;
    Course newCourse;
    Course newCourse2;

    @BeforeEach
    public void runBefore() {
        termOne = new TermCourses();

        LinkedList<String> days = new LinkedList<String>();
        days.add("Thursday");
        days.add("Tuesday");
        newCourse = new Course("CPSC 210", 7, 3, 3, days, 1500);
        newCourse2 = new Course("MATH 200", 8, 4, 5, days, 900);
        assertEquals(newCourse.getDays(), days);
        assertEquals(newCourse.getTime(), 1500);
        assertEquals(newCourse.getName(), "CPSC 210");
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
    public void testSumOfHours() {

        termOne.addCourse(newCourse);
        termOne.addCourse(newCourse2);
        //testing sum of max hours, 8+7
        assertEquals(15 , termOne.sumOfHours("max"));
        //testing sum of min hours, 3 + 4
        assertEquals(7 , termOne.sumOfHours("min"));

    }

    @Test
    public void testGetDailyMaxHours() {

        termOne.addCourse(newCourse);
        termOne.addCourse(newCourse2);

        assertEquals(Math.round((8 + 7) / 7), termOne.getDailyMaxHours());
    }

    @Test
    public void testGetMonthlyMaxHours() {

        termOne.addCourse(newCourse);
        termOne.addCourse(newCourse2);

        assertEquals(((8 + 7) * 4), termOne.getMonthlyMaxHours());
    }

    @Test
    public void testGetMonthlyMinHours() {

        termOne.addCourse(newCourse);
        termOne.addCourse(newCourse2);

        assertEquals(((4 + 3) * 4), termOne.getMonthlyMinHours());
    }

    @Test
    public void testGetDailyMinHours() {

        termOne.addCourse(newCourse);
        termOne.addCourse(newCourse2);

        assertEquals(Math.round((4 + 3) / 7), termOne.getDailyMinHours());

    }

    @Test
    public void testGetTermDifficulty() {
        //term difficulty: (5+3) / 2
        termOne.addCourse(newCourse);
        termOne.addCourse(newCourse2);
        assertEquals(Math.round(((5+3) / 2)) , termOne.getTermDifficulty());
    }

    @Test
    public void testGetTermCourses() {
        termOne.addCourse(newCourse);
        termOne.addCourse(newCourse2);

        LinkedList<Course> testList = new LinkedList<Course>();
        testList.add(newCourse);
        testList.add(newCourse2);

        assertEquals(testList, termOne.getTermCourses());
    }

}