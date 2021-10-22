package persistence;

import model.EntryLine;
import model.UserEntry;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----

// Represents a reader that reads UserEntry from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserEntry read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserEntry(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private UserEntry parseUserEntry(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        UserEntry userEntry = new UserEntry(name);
        addThingies(userEntry, jsonObject);
        return userEntry;
    }

    // MODIFIES: userEntry
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addThingies(UserEntry userEntry, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("lines");
        for (Object json : jsonArray) {
            JSONObject nextLine = (JSONObject) json;
            addThingy(userEntry, nextLine);
        }
    }

    // MODIFIES: userEntry
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(UserEntry userEntry, JSONObject jsonObject) {
        String name = jsonObject.getString("line");
        EntryLine line = new EntryLine(name);
        userEntry.addLine(line);
    }
}
