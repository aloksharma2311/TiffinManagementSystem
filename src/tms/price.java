package tms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class price extends JFrame {

    public price() {
        setTitle("Plans");
        getContentPane().setBackground(Color.decode("#ffd13b"));
        setSize(1400, 800);
//        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to full screen
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //Hide this window when closed

        // Use absolute positioning
        setLayout(null);

        // Title
        JLabel titleLabel = new JLabel("Plans");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        titleLabel.setBounds(620, 100, 500, 60); // x, y, width, height
        add(titleLabel);

        // Sub headings
        String[] planNames = {"Standard Tiffin Plan", "Premium Tiffin Plan", "Family Tiffin Plan", "Diet Tiffin Plan"};
        String[] standardPrices = {" Rs. 1200", "Rs. 4800", "Rs. 12500"};
        String[] premiumPrices = {"Rs. 1700", "Rs. 6500", "Rs. 18950"};
        String[] familyPrices = {"Rs. 600", "Rs. 2400", "Rs. 5500"};
        String[] dietPrices = {"Rs. 2000", "Rs. 8800", "Rs. 26500"};

        // Durations for all plans except Family Plan
        String[] durations = {"1 week", "1 month", "3 months"};

        // Durations for Family Plan (updated)
        String[] familyDurations = {"3-4 person", "6-8 person", "12-15 person"};

        String[][] prices = {standardPrices, premiumPrices, familyPrices, dietPrices};

        JLabel[] planLabels = new JLabel[4];
        JComboBox<String>[] planDropdowns = new JComboBox[4];
        JLabel[] priceLabels = new JLabel[4];
        JButton[] paymentButtons = new JButton[4];

        // X and Y coordinates for layout
        int labelX = 45;
        int labelY = 300;
        int dropdownY = 350;
        int priceY = 350;
        int buttonY = 400;

        for (int i = 0; i < 4; i++) {
            // Plan name
            planLabels[i] = new JLabel(planNames[i]);
            planLabels[i].setFont(new Font("Tahoma", Font.BOLD, 25));
            planLabels[i].setBounds(labelX, labelY, 250, 30);
            add(planLabels[i]);

            // Dropdown for plan duration
            if (i == 2) { // For Family Plan, use the custom options
                planDropdowns[i] = new JComboBox<>(familyDurations);
            } else {
                planDropdowns[i] = new JComboBox<>(durations);
            }
            planDropdowns[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
            planDropdowns[i].setBounds(labelX, dropdownY, 150, 30);
            add(planDropdowns[i]);

            // Price label
            priceLabels[i] = new JLabel(prices[i][0]); // Set initial price to 1 week/first option
            priceLabels[i].setFont(new Font("Tahoma", Font.PLAIN, 20));
            priceLabels[i].setBounds(labelX + 160, priceY, 100, 30);
            add(priceLabels[i]);

            // Add action listener to update price based on selection
            int finalI = i; // Use for lambda
            planDropdowns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = planDropdowns[finalI].getSelectedIndex();
                    priceLabels[finalI].setText(prices[finalI][selectedIndex]);
                }
            });

            // Payment button
            paymentButtons[i] = new JButton("Proceed to Payment");
            paymentButtons[i].setFont(new Font("Tahoma", Font.PLAIN, 20));
            paymentButtons[i].setBounds(labelX, buttonY, 250, 40);
            add(paymentButtons[i]);

            // Add action listener to the payment buttons
            paymentButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openPaymentGateway();
                }
            });

            // Increment X positions to space out the next set of elements
            labelX += 350;
        }

        // Center the frame on the screen
        setLocationRelativeTo(null);
        // Make the frame visible
        setVisible(true);
    }

    // Method to open the payment gateway window
    private void openPaymentGateway() {
        new paymentGateway();
    }

    public static void main(String[] args) {
        new price();
    }
}