package ui;

//University Student Schedule app! this class contains the user interface methods.

import model.Course;
import model.TermCourses;

import java.util.LinkedList;
import java.util.Scanner;

public class UniversityStudentScheduleApp {

    private Scanner scanner = new Scanner(System.in);
    TermCourses termOne = new TermCourses();

    //EFFECTS: Runs the run application method and initiates a new LinkedList data
    UniversityStudentScheduleApp() {
        runApplication();
    }

    //MODIFIES: this
    //EFFECTS: Processes user decision regarding if they would like to add course(s)
    public void runApplication() {
        showMainMenu();
    }

    //EFFECTS: Shows introduction to the program and gives two options to user
    private void showMainMenu() {

    }

    //MODIFIES: this
    //EFFECTS: Gets the course info from user and then adds the course to our LinkedList
    private void addCourse() {

    }

    public LinkedList<String> getCourseDays() {
        return null; //stub
    }

    //Shows the info of the courses and tells the user goodbye
    public void showResults() {

    }

    public void displayHourStats() {

    }

}
