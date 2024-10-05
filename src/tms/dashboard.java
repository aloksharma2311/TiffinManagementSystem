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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a custom panel with background image
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null); // Set layout manager to null for custom positioning
        add(backgroundPanel); // Add the background panel to the frame

        // Dashboard button (does nothing)
        JButton dashboardButton = createButton("Dashboard", 25, 50);
        backgroundPanel.add(dashboardButton);

        // Feedback button (opens feedback window)
        JButton feedbackButton = createButton("Feedback", 270, 50);
        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFeedbackWindow(); // Opens feedback window
            }
        });
        backgroundPanel.add(feedbackButton);

        // Profile button (opens profile window)
        JButton profileButton = createButton("Profile", 1250, 50);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProfileWindow(); // Opens profile window
            }
        });
        backgroundPanel.add(profileButton);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }

    // Method to create buttons
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 40);
        button.setFont(new Font("Tahoma", Font.PLAIN, 45));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE); // Set button text color to white
        addHoverEffect(button); // Add hover effect
        return button;
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
                button.setForeground(Color.decode("#ffffff")); // Change hover color to white
                /*button.setBackground(Color.decode("#48311b")); // Set hover background color
                button.setOpaque(true); // Make background visible*/
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(originalFont);
                button.setForeground(Color.WHITE); // Maintain button text color
                button.setBackground(null); // Reset to default background
                button.setOpaque(false); // Make background transparent
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

    // Custom panel class to handle background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            // Load the background image (make sure to provide the correct path)
            ImageIcon icon = new ImageIcon("src/images/dashboard.png");
            backgroundImage = icon.getImage();

            // Check if the image is loaded successfully
            if (icon.getIconWidth() == -1) {
                System.out.println("Background image not found at the specified path!");
            } else {
                System.out.println("Background image loaded successfully.");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Create a Graphics2D object from the Graphics context
            Graphics2D g2d = (Graphics2D) g;

            // Enable anti-aliasing for smoother scaling
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            // Set a fallback background color
            g2d.setColor(Color.YELLOW); // Change to any color you want
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // Draw the background image if loaded
            if (backgroundImage != null) {
                // Draw the image, scaling it to fit the panel
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
