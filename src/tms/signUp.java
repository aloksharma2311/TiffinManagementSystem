package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signUp extends JFrame implements ActionListener {
    JButton login, save;
    JTextField username;
    JTextField mobile_no;
    JTextField address;
    JTextField allergy;
    JTextField email;
    JPasswordField password;
    JCheckBox showPasswordCheckBox;

    signUp() {
        // Setting the frame size
        setSize(1000, 650);
        getContentPane().setBackground(Color.decode("#ffd13b"));

        JLabel titleLabel = new JLabel("SignUp");
        titleLabel.setBounds(420, 20, 200, 50); // Adjust position and size as needed
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40)); // Set font and size
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        // Create a panel for the line
        JPanel linePanel = new JPanel();
        linePanel.setBackground(Color.decode("#d9d9d9")); // Set line color
        linePanel.setBounds(250, 80, 500, 2); // Set position and thickness (2 pixels)
        add(linePanel);

        // Setting default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating and positioning the labels
        JLabel l1 = new JLabel("Username");
        l1.setBounds(270, 145, 150, 50);
        l1.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l1);

        JLabel l2 = new JLabel("Mobile No.");
        l2.setBounds(270, 195, 150, 50);
        l2.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l2);

        JLabel l3 = new JLabel("Address");
        l3.setBounds(270, 245, 150, 50);
        l3.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l3);

        JLabel l4 = new JLabel("Allergy");
        l4.setBounds(270, 295, 150, 50);
        l4.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l4);

        JLabel l5 = new JLabel("E-mail");
        l5.setBounds(270, 345, 150, 50);
        l5.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l5);

        JLabel l6 = new JLabel("Password");
        l6.setBounds(270, 395, 150, 50);
        l6.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l6);

        // Creating and positioning the text fields
        username = new JTextField();
        username.setBounds(470, 150, 250, 40);
        username.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(username);

        mobile_no = new JTextField();
        mobile_no.setBounds(470, 200, 250, 40);
        mobile_no.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(mobile_no);

        address = new JTextField();
        address.setBounds(470, 250, 250, 40);
        address.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(address);

        allergy = new JTextField();
        allergy.setBounds(470, 300, 250, 40);
        allergy.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(allergy);

        email = new JTextField();
        email.setBounds(470, 350, 250, 40);
        email.setFont(new Font("Segoe", Font.PLAIN, 24));
        add(email);

        // Creating and positioning the password field
        password = new JPasswordField();
        password.setBounds(470, 400, 250, 40);
        add(password);

        // Creating and positioning the show password checkbox
        showPasswordCheckBox = new JCheckBox();
        showPasswordCheckBox.setBounds(720, 409, 20, 20);
        showPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        showPasswordCheckBox.addActionListener(this);
        add(showPasswordCheckBox);

        // Creating and positioning the sign in button
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
            // Handle save action here (e.g., save data to the backend)
            JOptionPane.showMessageDialog(this, "Data Saved!!");
        }
    }

    public static void main(String[] args) {
        new signUp();
    }
}
