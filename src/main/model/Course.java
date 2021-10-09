package model;

import java.util.LinkedList;

public class Course {

    private int maxWeeklyHours = 0;
    private int minWeeklyHours = 0;
    private int courseDifficulty = 0;
    private int time = 0;
    String courseName = null;
    private LinkedList<String> days = new LinkedList<String>();

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


    //hours per day - hours per week, difficulty average
}
