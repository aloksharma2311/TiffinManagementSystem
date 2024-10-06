package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class profile extends JFrame implements ActionListener {
    JButton save;
    JTextField username, mobile_no, address, allergy, email;
    JPasswordField password;
    JComboBox<String> genderComboBox; // Gender dropdown

    profile() {
        setTitle("Profile");
        // Set frame size to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.decode("#ffd13b"));

        // Fetch user data from the database
        loadUserData();

        // Create and position the labels
        createLabels();

        // Create and position the text fields
        createTextFields();

        // Create and position the save button
        save = new JButton("Save");
        save.setBounds(470, 500, 100, 50);
        save.setFont(new Font("Tahoma", Font.BOLD, 20));
        save.setBackground(Color.decode("#ffd13b"));
        save.setForeground(Color.black);
        save.addActionListener(this);
        add(save);

        // Set layout manager to null for custom positioning
        setLayout(null);

        // Centering the frame on the screen
        setLocationRelativeTo(null);

        // Making the frame visible
        setVisible(true);
    }

    private void createLabels() {
        JLabel titleLabel = new JLabel("Profile");
        titleLabel.setBounds(720, 20, 200, 50);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        // Creating and positioning the labels
        String[] labels = {"Username", "Mobile No.", "Address", "Allergy", "E-mail", "Password", "Gender"};
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(270, 115 + (i * 50), 150, 50);
            label.setFont(new Font("Tahoma", Font.BOLD, 20));
            add(label);
        }
    }

    private void createTextFields() {
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

        // Creating and positioning the gender dropdown
        String[] genders = {"Male", "Female", "Other"}; // Options for gender
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(470, 420, 250, 40); // Position dropdown for gender
        genderComboBox.setFont(new Font("Segoe", Font.PLAIN, 20));
        add(genderComboBox);
    }

    private void loadUserData() {
        try {
            connection conn = new connection();
            String query = "SELECT username, mobileno, address, allergy, email, password, gender FROM userinfo WHERE <your_condition>"; // Add condition to fetch specific user

            PreparedStatement ps = conn.c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                username.setText(rs.getString("username"));
                mobile_no.setText(rs.getString("mobileno"));
                address.setText(rs.getString("address"));
                allergy.setText(rs.getString("allergy"));
                email.setText(rs.getString("email"));
                password.setText(rs.getString("password"));
                genderComboBox.setSelectedItem(rs.getString("gender"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            // Save updated data to the database
            updateUserData();
        }
    }

    private void updateUserData() {
        String user = username.getText();
        String mobile = mobile_no.getText();
        String addr = address.getText();
        String alg = allergy.getText();
        String mail = email.getText();
        String pass = new String(password.getPassword());
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

            // Update data in userinfo table
            String updateQuery = "UPDATE userinfo SET mobileno=?, address=?, allergy=?, email=?, password=?, gender=? WHERE username=?";
            PreparedStatement ps = conn.c.prepareStatement(updateQuery);
            ps.setString(1, mobile);
            ps.setString(2, addr);
            ps.setString(3, alg);
            ps.setString(4, mail);
            ps.setString(5, pass);
            ps.setString(6, gender);
            ps.setString(7, user); // Use username to identify the record to update
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new profile(); // Create an instance of the profile class to display the UI
    }
}