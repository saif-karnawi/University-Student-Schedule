package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----
//In addition, the JSON files in data folder used are also modeled after those from the program mentioned above.

class JsonReaderTest {

    Course course;
    Course course2;
    TermCourses termCourses;
    @BeforeEach
    void runBefore(){
        LinkedList<String> days = new LinkedList<String>();
        days.add("Thursday");
        days.add("Tuesday");
        course = new Course("CPSC 210", 7, 3, 3, days, 1500);
        course2 = new Course("ATSC 113", 8, 4, 5, days, 1500);
        termCourses = new TermCourses("Term");
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TermCourses termCourses = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTermCourses() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUserEntry.json");
        try {
            TermCourses termCourses = reader.read();
            assertEquals("My work room", termCourses.getName());
            assertEquals(0, termCourses.getTermCourses().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTermCourses() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUserEntry.json");
        try {

            termCourses.addCourse(course);
            termCourses.addCourse(course2);
            TermCourses termCourses = reader.read();
            assertEquals("Term", termCourses.getName());
            List<Course> lines = termCourses.getTermCourses();
            assertEquals(2, lines.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}

