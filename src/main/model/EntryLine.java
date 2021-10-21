package model;

import org.json.JSONObject;
import persistence.Writeable;

//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----

public class EntryLine implements Writeable {

    private String line;


    // EFFECTS: constructs a thingy with a name and category
    public EntryLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("line", line);
        return json;
    }
}
