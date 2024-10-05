package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class dashboard extends JFrame {

    public dashboard() {
        // Set frame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.decode("#ffd13b"));

        // Setting default close operation to exit the application when dashboard is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dashboard button (does nothing)
        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(30, 50, 250, 40);
        dashboardButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        dashboardButton.setBorderPainted(false);
        dashboardButton.setContentAreaFilled(false);
        dashboardButton.setFocusPainted(false);
        dashboardButton.setForeground(Color.BLACK);
        addHoverEffect(dashboardButton); // Add hover effect
        add(dashboardButton);

        // Feedback button (opens feedback window)
        JButton feedbackButton = new JButton("Feedback");
        feedbackButton.setBounds(250, 50, 250, 40);
        feedbackButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        feedbackButton.setBorderPainted(false);
        feedbackButton.setContentAreaFilled(false);
        feedbackButton.setFocusPainted(false);
        feedbackButton.setForeground(Color.BLACK);
        addHoverEffect(feedbackButton); // Add hover effect
        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFeedbackWindow(); // Opens feedback window
            }
        });
        add(feedbackButton);

        // Profile button (opens profile window)
        JButton profileButton = new JButton("Profile");
        profileButton.setBounds(470, 50, 250, 40);
        profileButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        profileButton.setBorderPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setFocusPainted(false);
        profileButton.setForeground(Color.BLACK);
        addHoverEffect(profileButton); // Add hover effect
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProfileWindow(); // Opens profile window
            }
        });
        add(profileButton);

        // Set layout manager to null for custom positioning
        setLayout(null);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }

    // Method to open the profile window
    private void openProfileWindow() {
        new profile(); // Opens the Profile window
    }

    // Method to add hover effect for buttons (underline when hovered)
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            Font originalFont = button.getFont();
            Font hoverFont = originalFont.deriveFont(Font.BOLD); // Changed to bold only

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(hoverFont);
                button.setForeground(Color.decode("#48311b")); // Change hover color to #48311b
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(originalFont);
                button.setForeground(Color.BLACK);
            }
        });
    }

    // Method to open feedback window with a static QR code image
    private void openFeedbackWindow() {
        // Create new window (JFrame)
        JFrame feedbackFrame = new JFrame("Feedback");
        feedbackFrame.setSize(500, 500);
        feedbackFrame.getContentPane().setBackground(Color.decode("#ffd13b"));

        // Set layout manager to null for custom positioning
        feedbackFrame.setLayout(null);

        // QR Code Label (Title)
        JLabel qrLabel = new JLabel("Scan to provide Feedback:");
        qrLabel.setBounds(100, 30, 300, 30);
        qrLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        qrLabel.setForeground(Color.BLACK);
        feedbackFrame.add(qrLabel);

        // Adding a QR Code Image (Set your image path here)
        ImageIcon qrCodeIcon = new ImageIcon("D:/Coding/Java/TiffinManagementSystem/src/images/Feedback Form QR Code.png");

        // Scale the image to fit within 300x300 without reducing resolution
        Image scaledImage = qrCodeIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Maintains quality
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Convert back to ImageIcon

        JLabel qrCodeLabel = new JLabel(scaledIcon);
        qrCodeLabel.setBounds(100, 80, 300, 300); // Adjust the position and size based on your QR image size
        feedbackFrame.add(qrCodeLabel);

        // Ensure the QR code image is loaded successfully
        if (qrCodeIcon.getIconWidth() == -1) {
            System.out.println("QR Code image not found at the specified path!");
        }

        // Set the feedback window close operation to hide the frame
        feedbackFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  // This will not stop the program when this window is closed

        // Set the feedback window to visible
        feedbackFrame.setLocationRelativeTo(null);
        feedbackFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new dashboard();
    }
}
