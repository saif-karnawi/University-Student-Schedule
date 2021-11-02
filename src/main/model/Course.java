package model;

import org.json.JSONObject;

import java.util.LinkedList;

//Each course object has a name, maximum and minimum weekly hours, difficulty out of 5,
// days of the course, and the time.
public class Course {

    private int maxWeeklyHours;
    private int minWeeklyHours;
    private int courseDifficulty;
    private int time;
    private String courseName;
    private LinkedList<String> days;

    //REQUIRES: an actual course name and not null, max and min weekly hours to be integers > 0, course difficulty
    // to be an integer between 1 and 5, days to have at least one day, and the time to be an int > 0 and
    // less than or equal to 2400 - representing a time on a 24-hour clock.
    //MODIFIES: this.
    //EFFECTS: initializes all the fields by setting them to the arguments passed to the constructor
    public Course(String courseName, int maxWeeklyHours, int minWeeklyHours, int courseDifficulty,
                  LinkedList<String> days, int time) {
        this.maxWeeklyHours = maxWeeklyHours;
        this.minWeeklyHours = minWeeklyHours;
        this.courseDifficulty = courseDifficulty;
        this.days = days;
        this.time = time;
        this.courseName = courseName;
    }

    public String getName() {
        return courseName;
    }

    public int getMaxWeeklyHours() {
        return maxWeeklyHours;
    }

    public int getMinWeeklyHours() {
        return minWeeklyHours;
    }

    public int getCourseDifficulty() {
        return courseDifficulty;
    }

    public int getTime() {
        return time;
    }

    public LinkedList<String> getDays() {
        return days;
    }

    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("courseName", getName());
        json.put("maxWeekly", getMaxWeeklyHours());
        json.put("minWeekly", getMinWeeklyHours());
        json.put("difficulty", getCourseDifficulty());
        json.put("time", getTime());

        return json;
    }

}
