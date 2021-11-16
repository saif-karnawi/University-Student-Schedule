package persistence;

import model.Course;
import model.TermCourses;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
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
    public TermCourses read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTermCourses(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses termCourses from JSON object and returns it
    private TermCourses parseTermCourses(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        TermCourses termCourses = new TermCourses(name);
        addCourses(termCourses, jsonObject);
        return termCourses;
    }

    // MODIFIES: termOne
    // EFFECTS: parses courses from JSON object and adds them to termCourses
    private void addCourses(TermCourses termOne, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextLine = (JSONObject) json;
            addCourse(termOne, nextLine);
        }
    }

    // MODIFIES: termOne
    // EFFECTS: parses course from JSON object and adds it to termCourses
    private void addCourse(TermCourses termOne, JSONObject jsonObject) {
        String name = jsonObject.getString("courseName");
        int max = jsonObject.getInt("maxWeekly");
        int min = jsonObject.getInt("minWeekly");
        int time = jsonObject.getInt("time");
        int difficulty = jsonObject.getInt("difficulty");

        LinkedList<String> days = new LinkedList<String>();
        int dayCounter = jsonObject.getInt("dayCounter");

        for (int i = 0; i < dayCounter; i++) {
            days.add(jsonObject.getString("day" + (i + 1)));
        }

        Course course = new Course(name, max, min,
                    difficulty, days, time);
        termOne.addCourse(course);
    }
}
