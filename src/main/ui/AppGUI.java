package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Represents a graphical user interface of the program
public class AppGUI extends JFrame {

    // Constructs the window, its size, location, and title
    public AppGUI() {

        JFrame frame = new JFrame();
        frame.setTitle("University Student Schedule");
        frame.setSize(810, 540);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
