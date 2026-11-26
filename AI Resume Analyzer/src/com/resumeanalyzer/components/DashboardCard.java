package com.resumeanalyzer.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * DashboardCard
 * -------------
 * A stat card used on the Dashboard panel, e.g. "Total Resumes: 128".
 * Flat, minimal style: white background, thin border, a slim colored
 * top accent line, icon, big number, and label. A subtle border-color
 * change on hover replaces any shadow/lift animation.
 *
 * The number displayed is a static placeholder today; wire setValue()
 * to live JDBC query results once the backend is implemented.
 */
public class DashboardCard extends JPanel {

    private static final Color BORDER = new Color(226, 232, 240);

    private final Color accentColor;
    private boolean hover = false;
    private final JLabel valueLabel;

    public DashboardCard(String icon, String title, String value, Color accentColor) {
        this.accentColor = accentColor;
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 18, 20));
        setPreferredSize(new Dimension(230, 120));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(iconLabel, BorderLayout.WEST);

        valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        valueLabel.setForeground(new Color(30, 41, 59));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titleLabel.setForeground(new Color(100, 116, 139));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        valueLabel.setAlignmentX(LEFT_ALIGNMENT);
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);
        textPanel.add(Box.createVerticalStrut(12));
        textPanel.add(valueLabel);
        textPanel.add(Box.createVerticalStrut(3));
        textPanel.add(titleLabel);

        add(top, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    public void setValue(String value) {
        valueLabel.setText(value);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 10;

        // card body
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arc, arc));

        // thin border (accent color on hover, neutral otherwise)
        g2.setColor(hover ? accentColor : BORDER);
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arc, arc));

        // slim top accent line
        g2.setColor(accentColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, 4, arc, arc));
        g2.fillRect(0, 2, getWidth() - 1, 2);

        g2.dispose();
        super.paintComponent(g);
    }
}
