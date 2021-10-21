package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----

public class UserEntry implements Writeable {

    private String name;
    private List<EntryLine> lines;

    // EFFECTS: constructs workroom with a name and empty list of thingies
    public UserEntry(String name) {
        this.name = name;
        lines = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this workroom
    public void addLine(EntryLine line) {
        lines.add(line);
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<EntryLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    // EFFECTS: returns number of thingies in this workroom
    public int numThingies() {
        return lines.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("lines", linesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray linesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (EntryLine t : lines) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}


