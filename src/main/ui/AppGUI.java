package ui;

import model.Course;
import model.TermCourses;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    JLabel picture;

    JFrame frame;
    JPanel coursesAddedPanel;
    JPanel picturePanel;
    JMenuBar menuBar;
    JMenu file;
    JMenuItem load;
    JMenuItem save;
    JMenuItem quit;

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


    //EFFECTS: Constructs the window, its size, location, and title. Also calls helper methods.
    //Modifies: this
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
        jmenu();
        frame.setVisible(true);

    }

    //EFFECTS: Initiates the menue
    //Modifies: this
    public void jmenu() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        quit = new JMenuItem("Quit");

        load.addActionListener(this);
        save.addActionListener(this);
        quit.addActionListener(this);

        file.add(load);
        file.add(save);
        file.add(quit);
        menuBar.add(file);

        frame.setJMenuBar(menuBar);
    }

    //EFFECTS: Initiates the output box
    //MODIFIES: this
    public void output() {
        output = new JTextArea();
        output.setBounds(25,250,460,200);
        output.setBackground(Color.lightGray);
        frame.add(output);
    }

    //EFFECTS: Initiates the window
    //MOIFIES: this
    public void initiateWindow() {
        frame.setTitle("University Student Schedule");
        frame.setSize(810, 540);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

    }

    //EFFECTS: Initiates the course name label and it's textField
    //MOIFIES: this
    public void courseName() {
        label1 = new JLabel("Course Name: ");
        label1.setBounds(25,25,100,25);
        frame.add(label1);

        courseName = new JTextField();
        courseName.setPreferredSize(new Dimension(250,40));
        courseName.setBounds(125,25,100,25);
        frame.add(courseName);
    }

    //EFFECTS: Initiates the difficulty lable and it's textfield
    //MOIFIES: this
    public void difficulty() {
        label3 = new JLabel("Difficulty (1-5):");
        label3.setBounds(250,25,100, 25);
        frame.add(label3);

        difficulty = new JTextField();
        difficulty.setPreferredSize(new Dimension(250,40));
        difficulty.setBounds(365,25,100,25);
        frame.add(difficulty);
    }

    //EFFECTS: Inititates the max and min lables and their textfields
    //MOIFIES: this
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

    //EFFECTS: Initiates a jpane that will include all the courses added
    //MOIFIES: this
    public void myJPane() {
        label2 = new JLabel("Courses Added:");
        label2.setBounds(575,25,100, 25);
        frame.add(label2);

        coursesAddedPanel = new JPanel();
        coursesAddedPanel.setBackground(Color.lightGray);
        coursesAddedPanel.setBounds(575,50,200,400);

    }

    //EFFECTS: Initiates the dats label and it's textfield
    //MOIFIES: this
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

    //EFFECTS: Initiates the time label and it's textField
    //MOIFIES: this
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

    //EFFECTS: Initiates the three buttons to add a course, remove a course, or show results
    //MOIFIES: this
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

    //Event listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            addCourseButtonClicked();
        }

        if (e.getSource() == showResultsButton) {
            showResults();
            picturePanel.remove(picture);
            picturePanel.updateUI();
        }

        if (e.getSource() == removeSelectedButton) {
            removeSelectedButtonClicked();
        }

        if (e.getSource() == quit) {
            System.exit(0);
        }

        if (e.getSource() == load) {
            loadClicked();
        }

        if (e.getSource() == save) {
            saveClicked();
        }
    }


    //EFFECTS: writes the json file and tells the user that progress is saved
    //MODIFIES: this
    public void saveClicked() {

        final String JSON_STORE = "./data/TermCourses.json";
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

        try {
            jsonWriter.open();
            jsonWriter.write(term);
            jsonWriter.close();
            System.out.println("Saved " + term.getName() + " to " + JSON_STORE);
            System.out.println("Thanks for using our application! Goodbye.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

        JOptionPane.showMessageDialog(null, "Entry Saved!", "Saved", JOptionPane.PLAIN_MESSAGE);
    }

    //EFFECTS: Checks if any courses are added. If there are any, tells the user they cannot load entry.
    //If there are not any, hides the buttons and calls a method to read the json file
    //MODIFIES: this
    public void loadClicked() {
        if (checkBoxes.size() != 0) {
            JOptionPane.showMessageDialog(null, "Cannot load entry since you have courses added.",
                    "Error", JOptionPane.PLAIN_MESSAGE);
        } else {
            loadEntry();
//            addCourseButton.setVisible(false);
//            showResultsButton.setVisible(false);
//            removeSelectedButton.setVisible(false);
        }

    }

    //EFFECTS: reads the json file
    //MODIFIES: this
    public void loadEntry() {
        final String JSON_STORE = "./data/TermCourses.json";
        JsonReader jsonReader = new JsonReader(JSON_STORE);

        try {
            term = jsonReader.read();
            System.out.println("Loaded " + term.getName() + " from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        showResults();
        picture = new JLabel();


        ImageIcon imageIcon = new ImageIcon("./data/check.png");

        Image image = imageIcon.getImage();
        Image resized = image.getScaledInstance(50,50, Image.SCALE_SMOOTH);
        ImageIcon iconResized = new ImageIcon(resized);

        picture.setIcon(iconResized);

        picturePanel = new JPanel();
        picturePanel.setBounds(505,400,50,50);
        picturePanel.add(picture);

        frame.add(picturePanel);
        picturePanel.updateUI();
    }

    //EFFECTS: Creates a course when the add course button is clicked
    //MODIFIES: this
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

    //EFFECTS: Adds the course to the panel and list of courses
    //MODIFIES: this
    public void createAddedCourseCheckButton(String courseName) {
        JCheckBox courseToAdd = new JCheckBox(courseName);
        coursesAddedPanel.add(courseToAdd);
        coursesAddedPanel.updateUI();
        checkBoxes.add(courseToAdd);
        addCourseToSystem(course);
    }

    //EFFECTS: resets UI and adds course to the term
    //MODIFIES: this
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

    //EFFECTS: shows result of entry
    //MODIFIES: this
    public void showResults() {

        output.setText("Loaded Entry");
        output.setText(output.getText() + "\n\nCourses: ");
        for (Course nextCourse : term.getTermCourses()) {

            if (nextCourse.getName().equalsIgnoreCase(term.getTermCourses().getLast().getName())) {
                output.setText(output.getText() + " and " + nextCourse.getName());
            } else {
                output.setText(output.getText() + nextCourse.getName() + ", ");
            }

        }
        output.setText(output.getText() + "\nApproximate term difficulty: " + (term.getTermDifficulty()));
        output.setText(output.getText() + "\nThe maximum daily hours you will be studying is approximately "
                + term.getDailyMaxHours());
        output.setText(output.getText() + "\nThe maximum monthly hours you will be studying is approximately "
                + term.getMonthlyMaxHours());
        output.setText(output.getText() + "\nThe minimum daily hours you will be studying is approximately "
                + term.getDailyMinHours());
        output.setText(output.getText() + "\nThe minimum monthly hours you will be studying is approximately "
                + term.getMonthlyMinHours());
        output.setText(output.getText() + "\n\nPlease restart the application to make your own entry");
    }

    //EFFECTS: remove course
    //MODIFIES: this
    public void removeSelectedButtonClicked() {
        for (JCheckBox nextCheckBox: checkBoxes) {
            if (nextCheckBox.isSelected()) {
                String name = nextCheckBox.getText();
                for (Course nextCourse : term.getTermCourses()) {
                    if (name.equalsIgnoreCase(nextCourse.getName())) {
                        term.remove(nextCourse);
                        coursesAddedPanel.remove(nextCheckBox);
                        coursesAddedPanel.updateUI();
                        checkBoxes.remove(nextCheckBox);
                    }
                }
            }
        }
    }



}
