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
        String decision = scanner.nextLine();

        if (decision.equalsIgnoreCase("quit")) {
            System.out.println("Thanks for using our application! Goodbye.");
        } else if (decision.equals("add")) {
            boolean addAnotherCourse = true;
            while (addAnotherCourse) {
                String decision2 = null;
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
        } else {
            System.out.println("Selection not valid. Please refresh the program and try again.");
        }

    }

    //EFFECTS: Shows introduction to the program and gives two options to user
    private void showMainMenu() {
        System.out.println("Welcome to University Student Schedule!");
        System.out.println("To add a course, type 'add'");
        System.out.println("To exit the program, type 'quit'");
    }

    //MODIFIES: this
    //EFFECTS: Gets the course info from user and then adds the course to our LinkedList
    private void addCourse() {
        String courseName = null;
        int maxWeeklyHours = 0;
        int minWeeklyHours = 0;
        int courseDifficulty = 0;
        int time = 0;

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

    public LinkedList<String> getCourseDays() {

        LinkedList<String> days = new LinkedList<String>();
        boolean anotherDay = true;
        String day;
        String decision = null;

        while (anotherDay) {
            day = null;
            System.out.println("What days do you have this course? If there are multiple, enter the first day only");
            day = scanner.nextLine();
            days.add(day);
            System.out.println("Do you have this course another day? Type either 'yes' or anything else for no");
            decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("yes")) {
                anotherDay = true;
            } else {
                anotherDay = false;
            }
        }

        return days;
    }

    //Shows the info of the courses and tells the user goodbye
    public void showResults() {
        int courseNumber = 0;
        System.out.println("Course times:");
        for (Course nextCourse : termOne.getTermCourses()) {
            courseNumber += 1;
            System.out.println("Course " + courseNumber + ": " + nextCourse.getName());
            System.out.print("Time: ");
            LinkedList<String> days = nextCourse.getDays();
            for (String nextDay : days) {

                String lastDay = nextCourse.getDays().getLast();
                if (nextDay.equalsIgnoreCase(lastDay)) {
                    System.out.print(nextDay);
                } else {
                    System.out.print(nextDay + ", ");
                }
            }
            System.out.println(" at " + nextCourse.getTime());
        }

        int difficulty = Math.round(termOne.termDifficulty() / termOne.getTermCourses().size());
        System.out.println("\n\nApproximate term difficulty: " + (difficulty));
        displayHourStats();
    }

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
