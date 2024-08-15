package tms;

import javax.swing.*;
import java.awt.*;

public class RoundedBackgroundPanel extends JPanel {
    private final Color backgroundColor;
    private final int panelWidth;
    private final int panelHeight;



    public RoundedBackgroundPanel(Color color, int width, int height) {
        this.backgroundColor = color;
        this.panelWidth = width;
        this.panelHeight = height;
        setOpaque(false); // Allow background to be painted

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = panelWidth;
        int height = panelHeight;
        int arc = 30; // Arc size for rounded corners
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, width, height, arc, arc);
    }
}

