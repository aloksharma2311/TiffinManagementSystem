package tms;

import javax.swing.*;
import java.awt.*;

public class paymentGateway extends JFrame {

    public paymentGateway() {
        setTitle("Payment Gateway");

        // Set frame properties
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this window only
        getContentPane().setBackground(Color.decode("#ffd13b"));
        setLayout(new BorderLayout());

        /*// Create a simple label for the payment gateway
        JLabel paymentLabel = new JLabel("Scan for Payment");
        paymentLabel.setBounds(100, 30, 300, 30);
        paymentLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        add(paymentLabel);*/

        // Adding a QR Code Image for payment
        ImageIcon qrCode = new ImageIcon("src/images/Feedback Form QR Code.png");

        // Scale the image to fit within 300x300 without reducing resolution
        Image scaledImage = qrCode.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Maintains quality
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Convert back to ImageIcon

        JLabel qrCodeLabel = new JLabel(scaledIcon);
        qrCodeLabel.setBounds(100, 80, 300, 300); // Adjust the position and size based on your QR image size
        add(qrCodeLabel);

        // Ensure the QR code image is loaded successfully
        if (qrCode.getIconWidth() == -1) {
            System.out.println("QR Code image not found at the specified path!");
        }

        // Set frame to visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new paymentGateway();
    }
}
