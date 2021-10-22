package persistence;

import org.json.JSONObject;

//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----

//Interface that has one method which is overridden by both classes that implement it, jsonReader and jsonWriter
public interface Writeable {
    JSONObject toJson();
}
