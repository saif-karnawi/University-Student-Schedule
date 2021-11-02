package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----
//In addition, the JSON files in data folder used are also modeled after those from the program mentioned above.

class JsonWriterTest {

    Course course;
    Course course2;

    @BeforeEach
    void runBefore(){
        LinkedList<String> days = new LinkedList<String>();
        days.add("Thursday");
        days.add("Tuesday");
        course = new Course("CPSC 210", 7, 3, 3, days, 1500);
        course2 = new Course("ATSC 113", 8, 4, 5, days, 1500);
    }
    @Test
    void testWriterInvalidFile() {
        try {

            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTermCourses() {
        try {
            TermCourses termOne = new TermCourses("Winter Term");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUserEntry.json");
            writer.open();
            writer.write(termOne);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUserEntry.json");
            termOne = reader.read();
            assertEquals("Winter Term", termOne.getName());
            assertEquals(0, termOne.getTermCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTermCourses() {
        try {
            TermCourses termOne = new TermCourses("Winter Term");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUserEntry.json");
            termOne.addCourse(course);
            termOne.addCourse(course2);
            writer.open();
            writer.write(termOne);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUserEntry.json");
            termOne = reader.read();
            assertEquals("Winter Term", termOne.getName());
            List<Course> lines = termOne.getTermCourses();
            assertEquals(2, lines.size());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
