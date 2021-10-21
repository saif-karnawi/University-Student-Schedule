package persistence;

import org.json.JSONObject;
//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----

public interface Writeable {
    JSONObject toJson();
}
