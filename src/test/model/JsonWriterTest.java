package model;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----
//In addition, the JSON files in data folder used are also modeled after those from the program mentioned above.

class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            UserEntry userEntry = new UserEntry("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUserEntry() {
        try {
            UserEntry userEntry = new UserEntry("Entry 123");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUserEntry.json");
            writer.open();
            writer.write(userEntry);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUserEntry.json");
            userEntry = reader.read();
            assertEquals("Entry 123", userEntry.getName());
            assertEquals(0, userEntry.numLines());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUserEntry() {
        try {
            UserEntry userEntry = new UserEntry("Entry 123");
            userEntry.addLine(new EntryLine("First Line"));
            userEntry.addLine(new EntryLine("Second Line"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUserEntry.json");
            writer.open();
            writer.write(userEntry);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUserEntry.json");
            userEntry = reader.read();
            assertEquals("Entry 123", userEntry.getName());
            List<EntryLine> lines = userEntry.getLines();
            assertEquals(2, lines.size());
            checkLine("First Line" , lines.get(0));
            checkLine("Second Line" , lines.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    protected void checkLine(String name, EntryLine line) {
        assertEquals(name, line.getLine());

    }
}
