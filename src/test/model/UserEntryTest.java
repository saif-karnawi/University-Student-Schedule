package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class UserEntryTest {

    private String name;
    private List<EntryLine> lines;
    private UserEntry userEntry;
    private EntryLine entryLine;

    @BeforeEach
    void createAnObject() {
        userEntry = new UserEntry("User Entry Test");
        entryLine = new EntryLine("First Line");
    }

    @Test
    void testGetName(){
        assertEquals("User Entry Test" , userEntry.getName());
    }

    @Test
    void testAddLine () {
        userEntry.addLine(entryLine);
        assertTrue(userEntry.getLines().contains(entryLine));
    }

    @Test
    void testNumLines () {
        assertEquals(0, userEntry.numLines());
        userEntry.addLine(new EntryLine("Second Line"));
        assertEquals(1, userEntry.numLines());
    }

    @Test
    void testToJson () {
        assertTrue(userEntry.toJson().has("name"));
        assertTrue(userEntry.toJson().has("lines"));
    }

}
