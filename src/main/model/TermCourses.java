package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.LinkedList;

// Represents a LinkedList of type Course, for all the courses the user adds
public class TermCourses implements Writeable {

    LinkedList<Course> data;
    String name;

    //EFFECTS: Instantiates a new LinkedList called data
    public TermCourses(String name) {
        data = new LinkedList<Course>();
        this.name = name;
    }

    public void remove(Course course) {
        for (Course next : data) {
            if (course.getName() == next.getName()) {
                data.remove(next);
            }
        }
    }

    public LinkedList<Course> getTermCourses() {
        return data;
    }

    //MODIFIES: this.
    //EFFECTS: Adds a course
    public void addCourse(Course courseToBeAdded) {
        data.add(courseToBeAdded);
    }

    //EFFECTS: Returns the sum of either maximum study hours or minimum study hours
    // for all he courses depending on the String argument passed. If equal to 'max',
    //it returns max hours, and returns minimum hours otherwise.
    public int sumOfHours(String typeOfHours) {
        if (typeOfHours.equalsIgnoreCase("max")) {
            int sumOfMaxHours = 0;

            for (Course nextCourse : data) {
                sumOfMaxHours += nextCourse.getMaxWeeklyHours();
            }
            return sumOfMaxHours;
        } else {
            int sumOfMinHours = 0;

            for (Course nextCourse : data) {
                sumOfMinHours += nextCourse.getMinWeeklyHours();
            }

            return sumOfMinHours;
        }
    }

    //EFFECTS: returns the approximate daily maximum study hours
    public int getDailyMaxHours() {

        return Math.round(sumOfHours("max") / 7);

    }

    public String getName() {
        return name;
    }

    //EFFECTS: returns the approximate monthly maximum hours
    public int getMonthlyMaxHours() {

        return Math.round(sumOfHours("max") * 4);

    }

    //EFFECTS: returns the approximate daily minimum hours
    public int getDailyMinHours() {

        return Math.round(sumOfHours("min") / 7);

    }

    //EFFECTS: returns the approximate monthly minimum hours
    public int getMonthlyMinHours() {

        return Math.round(sumOfHours("min") * 4);

    }

    //EFFECTS: returns the average difficulty of all the courses
    public int getTermDifficulty() {
        int sumOfDifficulties = 0;
        int difficultyAverage = 0;

        for (Course nextCourse: data) {
            sumOfDifficulties = sumOfDifficulties + nextCourse.getCourseDifficulty();
        }

        difficultyAverage = sumOfDifficulties / getTermCourses().size();
        return difficultyAverage;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("courses", coursesToJson());
        return json;
    }

    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course t : data) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
