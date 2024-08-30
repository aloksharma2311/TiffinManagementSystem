package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class logIn extends JFrame implements ActionListener {
    JTextField username;
    JPasswordField password;
    JCheckBox showPasswordCheckBox;

    public logIn() {
        // Setting the frame size
        setSize(500, 350);
        getContentPane().setBackground(Color.decode("#ffd13b"));

        // Setting default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Log In");
        titleLabel.setBounds(200, 20, 100, 30);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        // Create a panel for the line
        JPanel linePanel = new JPanel();
        linePanel.setBackground(Color.decode("#d9d9d9")); // Set line color
        linePanel.setBounds(80, 60, 325, 2); // Set position and thickness (2 pixels)
        add(linePanel);

        // Creating and positioning the username label
        JLabel l1 = new JLabel("Username");
        l1.setBounds(100, 115, 150, 30);
        l1.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(l1);

        // Creating and positioning the password label
        JLabel l2 = new JLabel("Password");
        l2.setBounds(100, 155, 150, 30);
        l2.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(l2);

        // Creating and positioning the username text field
        username = new JTextField();
        username.setBounds(250, 115, 150, 30);
        username.setFont(new Font("Segoe", Font.PLAIN, 16));
        add(username);

        // Creating and positioning the password field
        password = new JPasswordField();
        password.setBounds(250, 155, 150, 30);
        add(password);

        // Creating and positioning the show password checkbox
        showPasswordCheckBox = new JCheckBox();
        showPasswordCheckBox.setBounds(400, 160 , 20, 20);
        showPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        showPasswordCheckBox.addActionListener(this);
        add(showPasswordCheckBox);

        // Creating and positioning the log in button
        JButton logInButton = new JButton("Log In");
        logInButton.setBounds(150, 240, 100, 40);
        logInButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        logInButton.setBackground(Color.decode("#ffd13b"));
        logInButton.setForeground(Color.black);
        logInButton.addActionListener(this);
        add(logInButton);

        // Creating and positioning the New User button
        JButton newUserButton = new JButton("New User?");
        newUserButton.setBounds(300, 240, 100, 40);
        newUserButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        newUserButton.setBackground(Color.decode("#ffd13b"));
        newUserButton.setForeground(Color.black);
        newUserButton.addActionListener(e -> {
            // Close the current login window and open the sign up window
            this.setVisible(false);
            new signUp();
        });
        add(newUserButton);

        // Setting layout manager to null for custom positioning
        setLayout(null);

        // Centering the frame on the screen
        setLocationRelativeTo(null);

        // Making the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPasswordCheckBox) {
            if (showPasswordCheckBox.isSelected()) {
                // Show password
                password.setEchoChar((char) 0);
            } else {
                // Hide password
                password.setEchoChar('*');
            }
        } else if (e.getActionCommand().equals("Log In")) {
            // Connect to the database and verify credentials
            String user = username.getText();
            String pass = new String(password.getPassword());

            try {
                // Creating connection object
                connection conn = new connection();

                // Executing query
                String query = "SELECT * FROM login WHERE username = '" + user + "' AND password = '" + pass + "'";
                ResultSet rs = conn.s.executeQuery(query);

                // Checking if any record found
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful!!");
                    this.setVisible(false);
                    new home();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials!!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new logIn();
    }
}
