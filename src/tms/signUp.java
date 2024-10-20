package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class signUp extends JFrame implements ActionListener {
    JButton login, save;
    JTextField username, mobile_no, address, allergy, email;
    JPasswordField password;
    JCheckBox showPasswordCheckBox;
    JComboBox<String> genderComboBox; // Gender dropdown

    signUp() {
        setTitle("User Info");
        // Setting the frame size
        setSize(1000, 650);
        getContentPane().setBackground(Color.decode("#ffd13b"));

        JLabel titleLabel = new JLabel("User Info");
        titleLabel.setBounds(400, 20, 200, 50);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        // Create a panel for the line
        JPanel linePanel = new JPanel();
        linePanel.setBackground(Color.decode("#d9d9d9"));
        linePanel.setBounds(250, 80, 500, 2);
        add(linePanel);

        // Setting default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating and positioning the labels
        JLabel l1 = new JLabel("Username");
        l1.setBounds(270, 115, 150, 50);
        l1.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l1);

        JLabel l2 = new JLabel("Mobile No.");
        l2.setBounds(270, 165, 150, 50);
        l2.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l2);

        JLabel l3 = new JLabel("Address");
        l3.setBounds(270, 215, 150, 50);
        l3.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l3);

        JLabel l4 = new JLabel("Allergy");
        l4.setBounds(270, 265, 150, 50);
        l4.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l4);

        JLabel l5 = new JLabel("E-mail");
        l5.setBounds(270, 315, 150, 50);
        l5.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l5);

        JLabel l6 = new JLabel("Password");
        l6.setBounds(270, 365, 150, 50);
        l6.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l6);

        JLabel l7 = new JLabel("Gender");
        l7.setBounds(270, 415, 150, 50);  // Position label for gender
        l7.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l7);

        // Creating and positioning the text fields
        username = new JTextField();
        username.setBounds(470, 120, 250, 40);
        username.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(username);

        mobile_no = new JTextField();
        mobile_no.setBounds(470, 170, 250, 40);
        mobile_no.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(mobile_no);

        address = new JTextField();
        address.setBounds(470, 220, 250, 40);
        address.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(address);

        allergy = new JTextField();
        allergy.setBounds(470, 270, 250, 40);
        allergy.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(allergy);

        email = new JTextField();
        email.setBounds(470, 320, 250, 40);
        email.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(email);

        // Creating and positioning the password field
        password = new JPasswordField();
        password.setBounds(470, 370, 250, 40);
        add(password);

        // Creating and positioning the show password checkbox
        showPasswordCheckBox = new JCheckBox();
        showPasswordCheckBox.setBounds(720, 380, 20, 20);
        showPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        showPasswordCheckBox.addActionListener(this);
        add(showPasswordCheckBox);

        // Creating and positioning the gender dropdown
        String[] genders = { "Male", "Female", "Other" }; // Options for gender
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(470, 420, 250, 40); // Position dropdown for gender
        genderComboBox.setFont(new Font("Segoe", Font.PLAIN, 20));
        add(genderComboBox);

        // Creating and positioning the log in button
        login = new JButton("Log In");
        login.setBounds(620, 500, 100, 50);
        login.setFont(new Font("Tahoma", Font.BOLD, 20));
        login.setBackground(Color.decode("#ffd13b"));
        login.setForeground(Color.black);
        login.addActionListener(this);
        add(login);

        // Creating and positioning the save button
        save = new JButton("Save");
        save.setBounds(470, 500, 100, 50);
        save.setFont(new Font("Tahoma", Font.BOLD, 20));
        save.setBackground(Color.decode("#ffd13b"));
        save.setForeground(Color.black);
        save.addActionListener(this);
        add(save);

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
        } else if (e.getSource() == login) {
            // Hide the signUp page
            this.setVisible(false);
            // Create and show the logIn page
            new logIn();
        } else if (e.getSource() == save) {
            // Save data to the database
            String user = username.getText();
            String pass = new String(password.getPassword());
            String mobile = mobile_no.getText();
            String addr = address.getText();
            String alg = allergy.getText();
            String mail = email.getText();
            String gender = (String) genderComboBox.getSelectedItem(); // Get selected gender

            // Validate mobile number length
            if (mobile.length() != 10) {
                JOptionPane.showMessageDialog(this, "Invalid Mobile Number!! Please enter a valid 10-digit number.");
                return; // Stop further processing if mobile number is invalid
            }

            // Validate email format
            if (!mail.endsWith("@gmail.com")) {
                JOptionPane.showMessageDialog(this, "Invalid Email!! Please use a valid Gmail address.");
                return; // Stop further processing if email is invalid
            }

            try {
                connection conn = new connection();

                // Insert data into signup table
                String signupQuery = "INSERT INTO userinfo (username, password, mobileno, address, allergy, email, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps1 = conn.c.prepareStatement(signupQuery);
                ps1.setString(1, user);
                ps1.setString(2, pass);
                ps1.setString(3, mobile);
                ps1.setString(4, addr);
                ps1.setString(5, alg);
                ps1.setString(6, mail);
                ps1.setString(7, gender);
                ps1.executeUpdate();

                // Insert data into login table
                String loginQuery = "INSERT INTO userlogin (username, password) VALUES (?, ?)";
                PreparedStatement ps2 = conn.c.prepareStatement(loginQuery);
                ps2.setString(1, user);
                ps2.setString(2, pass);
                ps2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Sign Up Successful!");

                // Optionally, navigate to login page or home page
                this.setVisible(false);
                new logIn();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new signUp();
    }
}