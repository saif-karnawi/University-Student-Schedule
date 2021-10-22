package model;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----
//In addition, the JSON files in data folder used are also modeled after those from the program mentioned above.

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            UserEntry userEntry = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUserEntry() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUserEntry.json");
        try {
            UserEntry userEntry = reader.read();
            assertEquals("My work room", userEntry.getName());
            assertEquals(0, userEntry.numLines());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUserEntry() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUserEntry.json");
        try {
            UserEntry userEntry = reader.read();
            assertEquals("Entry 123", userEntry.getName());
            List<EntryLine> lines = userEntry.getLines();
            assertEquals(2, lines.size());
            checkLine("First Line",lines.get(0));
            checkLine("Second Line",lines.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    protected void checkLine(String name, EntryLine line) {
        assertEquals(name, line.getLine());

    }

}

