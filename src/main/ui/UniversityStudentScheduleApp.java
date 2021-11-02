package ui;


import model.Course;
import model.TermCourses;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

//------Please note, class includes code that it modeled after/ copied from JsonSerializationDemo
// as shared on edx Phase 2 page. Github URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo -----

//University Student Schedule app! this class contains the user interface methods.
public class UniversityStudentScheduleApp {

    private Scanner scanner = new Scanner(System.in);
    TermCourses termOne = new TermCourses("Term");

    private static final String JSON_STORE = "./data/TermCourses.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: Runs the run application method and initiates a new LinkedList data
    public UniversityStudentScheduleApp() {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApplication();
    }

    //MODIFIES: this
    //EFFECTS: Processes user decision regarding if they would like to add another course(s)
    //If not, it shows the results of their input
    public void runApplication() {
        showMainMenu();
        String decision = scanner.nextLine();

        if (decision.equalsIgnoreCase("quit")) {
            System.out.println("Thanks for using our application! Goodbye.");
        } else if (decision.equals("add")) {
            boolean addAnotherCourse = true;
            while (addAnotherCourse) {
                String decision2;
                addCourse();
                System.out.println("Would you like to add another course?");
                System.out.println("Type 'yes' if you want to add more courses, type anything else for no");
                scanner.nextLine();
                decision2 = scanner.nextLine();

                if (!decision2.equalsIgnoreCase("yes")) {
                    addAnotherCourse = false;
                }
            }
            showResults();
        } else if (decision.equalsIgnoreCase("load")) {
            loadSavedEntry();
        } else {
            System.out.println("Selection not valid. Please refresh the program and try again.");
        }

    }

    //MODIFIES: this.
    //EFFECTS: loads userEntry from file
    public void loadSavedEntry() {
        try {
            termOne = jsonReader.read();
            System.out.println("Loaded " + termOne.getName() + " from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        showResults();
    }

    //EFFECTS: Shows introduction to the program and gives two options to user
    private void showMainMenu() {
        System.out.println("Welcome to University Student Schedule!");
        System.out.println("To add a course, type 'add'");
        System.out.println("To exit the program, type 'quit'");
        System.out.println("To view the latest entry, please type 'load'");
    }

    //MODIFIES: this
    //EFFECTS: Gets the course info from user, creates a course object
    // and then adds the course to our LinkedList
    private void addCourse() {
        String courseName;
        int maxWeeklyHours;
        int minWeeklyHours;
        int courseDifficulty;
        int time;

        System.out.println("Please enter the name of your course.");
        courseName = scanner.nextLine();
        LinkedList<String> days = getCourseDays();
        System.out.println("Please enter what time do you have the course");
        System.out.println("Examples: for 6pm, enter 1800. For 6:20pm, enter 1820, and for 9:30am enter 930");
        time = scanner.nextInt();
        System.out.println("Please enter the expected difficulty of the course out of 5");
        courseDifficulty = scanner.nextInt();
        System.out.println("Please enter the maximum weekly hours you will spend on the course");
        maxWeeklyHours = scanner.nextInt();
        System.out.println("Please enter the minimum weekly hours you will spend on the course");
        minWeeklyHours = scanner. nextInt();

        Course newCourse = new Course(courseName, maxWeeklyHours,minWeeklyHours,courseDifficulty, days, time);
        termOne.addCourse(newCourse);
    }

    //MODIFIES: this.
    //EFFECTS: Gets the days that the user has class in a LinkedList<String>
    public LinkedList<String> getCourseDays() {

        LinkedList<String> days = new LinkedList<String>();
        boolean anotherDay = true;
        String day;
        String decision;
        System.out.println("What days do you have this course?");

        while (anotherDay) {
            System.out.println("Enter one day only:");
            day = scanner.nextLine();
            days.add(day);
            System.out.println("Do you have this course another day? Type either 'yes' or anything else for no");
            decision = scanner.nextLine();
            if (!decision.equalsIgnoreCase("yes")) {
                anotherDay = false;
            }
        }

        return days;
    }

    //MODIFIES: this.
    //Effects: Shows the info of the courses and tells the user goodbye
    public void showResults() {
        int courseNumber = 0;

        System.out.println("\nCourse times:");
        for (Course nextCourse : termOne.getTermCourses()) {
            courseNumber += 1;
            System.out.println("Course " + courseNumber + ": " + nextCourse.getName());
            System.out.print("Time: ");
            String days = daysAsString(nextCourse);
            System.out.print(days);
            System.out.print(" at " + nextCourse.getTime() + "\n");
            //saveCourse(nextCourse, courseNumber, days);
        }
        System.out.println("\n\nApproximate term difficulty: " + (termOne.getTermDifficulty()));
        displayHourStats();
        optionToSave();
    }

    //EFFECTS: returns the days user has class in a form of a string
    public String daysAsString(Course course) {
        String daysString = "";
        LinkedList<String> days = course.getDays();
        for (String nextDay : days) {

            String lastDay = course.getDays().getLast();
            if (nextDay.equalsIgnoreCase(lastDay)) {
                daysString += nextDay;
            } else {
                daysString += nextDay + ", ";
            }
        }

        return daysString;
    }

    //EFFECTS: gives the option to save, and if the user wants to save, it saves the userEntry to file.
    // otherwise tells the user goodbye.
    public void optionToSave() {

        System.out.println("\nWould you like to save this entry?");
        String decision = scanner.nextLine();
        if (decision.equalsIgnoreCase("yes")) {

            try {
                jsonWriter.open();
                jsonWriter.write(termOne);
                jsonWriter.close();
                System.out.println("Saved " + termOne.getName() + " to " + JSON_STORE);
                System.out.println("Thanks for using our application! Goodbye.");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        } else {
            System.out.println("Thanks for using our application! Goodbye.");
        }

    }

    //EFFECTS: shows the user the info regarding study hours
    public void displayHourStats() {

        System.out.println("The maximum daily hours you will be studying is approximately "
                + termOne.getDailyMaxHours());
        System.out.println("The maximum monthly hours you will be studying is approximately "
                + termOne.getMonthlyMaxHours());
        System.out.println("The minimum daily hours you will be studying is approximately "
                + termOne.getDailyMinHours());
        System.out.println("The minimum monthly hours you will be studying is approximately "
                + termOne.getMonthlyMinHours());
    }

}
