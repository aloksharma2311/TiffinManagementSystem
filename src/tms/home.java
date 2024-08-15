package tms;

import javax.swing.*;
import java.awt.*;

public class home extends JFrame {

    public home() {
        // Set the frame size
        setSize(1920, 1080);
        getContentPane().setBackground(Color.decode("#ffd13b"));

        // Setting default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Welcome to the Home Page");
        titleLabel.setBounds(200, 50, 800, 50);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        // Set layout manager to null for custom positioning
        setLayout(null);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new home();
    }
}

