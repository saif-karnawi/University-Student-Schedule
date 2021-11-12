package ui;

import model.Course;
import model.TermCourses;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

//Represents a graphical user interface of the program
public class AppGUI extends JFrame implements ActionListener {

    Course course;
    TermCourses term;
    LinkedList<JCheckBox> checkBoxes;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JLabel label7;


    JFrame frame;
    JPanel coursesAddedPanel;

    JTextField courseName;
    JTextField difficulty;
    JTextField minHours;
    JTextField maxHours;
    JTextField time;

    JButton addCourseButton;
    JButton removeSelectedButton;
    JButton showResultsButton;

    JCheckBox monday;
    JCheckBox tuesday;
    JCheckBox wednesday;
    JCheckBox thursday;
    JCheckBox friday;

    JTextArea output;


    // Constructs the window, its size, location, and title
    public AppGUI() {
        checkBoxes = new LinkedList<>();
        term = new TermCourses("Term");
        frame = new JFrame();
        frame.setLayout(null);
        myJPane();
        frame.add(coursesAddedPanel);

        initiateWindow();
        courseName();
        difficulty();
        minAndMaxHours();
        days();
        time();
        buttons();
        output();
        frame.setVisible(true);

    }

    public void output() {
        output = new JTextArea();
        output.setBounds(25,250,460,200);
        output.setBackground(Color.lightGray);
        frame.add(output);
    }

    public void initiateWindow() {
        frame.setTitle("University Student Schedule");
        frame.setSize(810, 540);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

    }

    public void courseName() {
        label1 = new JLabel("Course Name: ");
        label1.setBounds(25,25,100,25);
        frame.add(label1);

        courseName = new JTextField();
        courseName.setPreferredSize(new Dimension(250,40));
        courseName.setBounds(125,25,100,25);
        frame.add(courseName);
    }

    public void difficulty() {
        label3 = new JLabel("Difficulty (1-5):");
        label3.setBounds(250,25,100, 25);
        frame.add(label3);

        difficulty = new JTextField();
        difficulty.setPreferredSize(new Dimension(250,40));
        difficulty.setBounds(365,25,100,25);
        frame.add(difficulty);
    }

    public void minAndMaxHours() {
        label4 = new JLabel("Minimum Weekly Hours:");
        label4.setBounds(25,70,200, 25);
        frame.add(label4);

        label5 = new JLabel("Maximum Weekly Hours:");
        label5.setBounds(250,70,200, 25);
        frame.add(label5);

        minHours = new JTextField();
        minHours.setPreferredSize(new Dimension(250,40));
        minHours.setBounds(180,70,45,25);
        frame.add(minHours);

        maxHours = new JTextField();
        maxHours.setPreferredSize(new Dimension(250,40));
        maxHours.setBounds(420,70,45,25);
        frame.add(maxHours);
    }

    public void myJPane() {
        label2 = new JLabel("Courses Added:");
        label2.setBounds(575,25,100, 25);
        frame.add(label2);

        coursesAddedPanel = new JPanel();
        coursesAddedPanel.setBackground(Color.lightGray);
        coursesAddedPanel.setBounds(575,50,200,400);

    }

    public void days() {
        label6 = new JLabel("Days:");
        label6.setBounds(25,115,200, 25);
        frame.add(label6);

        monday = new JCheckBox("Mon");
        tuesday = new JCheckBox("Tues");
        wednesday = new JCheckBox("Wed");
        thursday = new JCheckBox("Thurs");
        friday = new JCheckBox("Fri");

        monday.setBounds(60,115,75, 25);
        tuesday.setBounds(120,115,75, 25);
        wednesday.setBounds(180,115,75, 25);
        thursday.setBounds(240,115,75, 25);
        friday.setBounds(310,115,75, 25);

        frame.add(monday);
        frame.add(tuesday);
        frame.add(wednesday);
        frame.add(thursday);
        frame.add(friday);

    }

    public void time() {
        label7 = new JLabel("Class Time:");
        label7.setBounds(25,160,100, 25);
        frame.add(label7);

        time = new JTextField();
        time.setPreferredSize(new Dimension(250,40));
        time.setBounds(100,160,100,25);
        frame.add(time);

        label7 = new JLabel("Examples: '945' for 9:45am, '2000' for 8pm");
        label7.setBounds(210,160,300, 25);
        frame.add(label7);
    }

    public void buttons() {
        addCourseButton = new JButton("Add Course");
        addCourseButton.setBounds(25,205,150,25);
        frame.add(addCourseButton);
        addCourseButton.addActionListener(this);

        removeSelectedButton = new JButton("Remove Course");
        removeSelectedButton.setBounds(175,205,150,25);
        frame.add(removeSelectedButton);
        removeSelectedButton.addActionListener(this);

        showResultsButton = new JButton("Show Results");
        showResultsButton.setBounds(325,205,150,25);
        frame.add(showResultsButton);
        showResultsButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            addCourseButtonClicked();
        }

        if (e.getSource() == showResultsButton) {
            showResultsButtonClicked();
        }

        if (e.getSource() == removeSelectedButton) {
            removeSelectedButtonClicked();
        }
    }

    public void addCourseButtonClicked() {

        String name = courseName.getText();
        int min = Integer.parseInt(minHours.getText());
        int max = Integer.parseInt(maxHours.getText());
        int diff = Integer.parseInt(difficulty.getText());
        int courseTime = Integer.parseInt(time.getText());

        LinkedList<String> courseDays = new LinkedList<>();
        if (monday.isSelected()) {
            courseDays.add("Mon");
        }
        if (tuesday.isSelected()) {
            courseDays.add("Tues");
        }
        if (wednesday.isSelected()) {
            courseDays.add("Wed");
        }
        if (thursday.isSelected()) {
            courseDays.add("thurs");
        }
        if (friday.isSelected()) {
            courseDays.add("Fri");
        }
        course = new Course(name, max, min,diff, courseDays, courseTime);
        createAddedCourseCheckButton(name);
    }

    public void createAddedCourseCheckButton(String courseName) {
        JCheckBox courseToAdd = new JCheckBox(courseName);
        coursesAddedPanel.add(courseToAdd);
        coursesAddedPanel.updateUI();
        checkBoxes.add(courseToAdd);
        addCourseToSystem(course);
    }

    public void addCourseToSystem(Course course) {
        courseName.setText(null);
        difficulty.setText(null);
        maxHours.setText(null);
        minHours.setText(null);
        time.setText(null);
        monday.setSelected(false);
        tuesday.setSelected(false);
        wednesday.setSelected(false);
        thursday.setSelected(false);
        friday.setSelected(false);
        term.addCourse(course);
    }

    public void showResultsButtonClicked() {
        int courseNumber = 0;

        System.out.println("\nCourse times:");
        for (Course nextCourse : term.getTermCourses()) {
            courseNumber += 1;
            System.out.println("Course " + courseNumber + ": " + nextCourse.getName());
            System.out.print("Time: ");
            String days = daysAsString(nextCourse);
            System.out.print(days);
            System.out.print(" at " + nextCourse.getTime() + "\n");
            //saveCourse(nextCourse, courseNumber, days);
        }
        System.out.println("\n\nApproximate term difficulty: " + (term.getTermDifficulty()));
        displayHourStats();
        //optionToSave();
    }

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

    public void displayHourStats() {

        System.out.println("The maximum daily hours you will be studying is approximately "
                + term.getDailyMaxHours());
        System.out.println("The maximum monthly hours you will be studying is approximately "
                + term.getMonthlyMaxHours());
        System.out.println("The minimum daily hours you will be studying is approximately "
                + term.getDailyMinHours());
        System.out.println("The minimum monthly hours you will be studying is approximately "
                + term.getMonthlyMinHours());
    }

    public void removeSelectedButtonClicked() {
        for (JCheckBox nextCheckBox: checkBoxes) {
            if (nextCheckBox.isSelected()) {
                String name = nextCheckBox.getText();
                for (Course nextCourse : term.getTermCourses()) {
                    if (name == nextCourse.getName()) {
                        term.remove(nextCourse);
                        coursesAddedPanel.remove(nextCheckBox);
                        coursesAddedPanel.updateUI();
                    }
                }
            }
        }
    }

}
