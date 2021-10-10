package model;

import java.util.LinkedList;

public class TermCourses {

    LinkedList<Course> data;

    public TermCourses() {
        data = new LinkedList<Course>();
    }

    public LinkedList<Course> getTermCourses() {
        return data;
    }

    public void addCourse(Course courseToBeAdded) {
        data.add(courseToBeAdded);
    }

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

    public int getDailyMaxHours() {

        return Math.round(sumOfHours("max") / 7);

    }

    public int getMonthlyMaxHours() {

        return Math.round(sumOfHours("max") * 4);

    }

    public int getDailyMinHours() {

        return Math.round(sumOfHours("min") / 7);

    }

    public int getMonthlyMinHours() {

        return Math.round(sumOfHours("min") * 4);

    }

    public int getTermDifficulty() {
        int sumOfDifficulties = 0;
        int difficultyAverage = 0;

        for (Course nextCourse: data) {
            sumOfDifficulties += nextCourse.getCourseDifficulty();
        }

        difficultyAverage = sumOfDifficulties / getTermCourses().size();
        return difficultyAverage;
    }


}
