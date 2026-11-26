package com.resumeanalyzer.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * ProgressCard
 * ------------
 * Displays a labeled metric (e.g. "Resume Score", "Skills Match",
 * "ATS Compatibility") with a smooth, custom-painted horizontal
 * progress bar and a percentage readout.
 *
 * The percentage is a static placeholder; call setPercentage() once
 * real analysis results are available from the backend.
 */
public class ProgressCard extends JPanel {

    private int percentage;
    private final Color barColor;
    private final JLabel percentLabel;
    private final BarPanel bar;

    public ProgressCard(String label, int percentage, Color barColor) {
        this.percentage = percentage;
        this.barColor = barColor;

        setOpaque(false);
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel titleLabel = new JLabel(label);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(51, 65, 85));

        percentLabel = new JLabel(percentage + "%");
        percentLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        percentLabel.setForeground(barColor);

        header.add(titleLabel, BorderLayout.WEST);
        header.add(percentLabel, BorderLayout.EAST);

        bar = new BarPanel();
        bar.setPreferredSize(new Dimension(100, 12));

        add(header, BorderLayout.NORTH);
        add(bar, BorderLayout.CENTER);
    }

    public void setPercentage(int percentage) {
        this.percentage = Math.max(0, Math.min(100, percentage));
        percentLabel.setText(this.percentage + "%");
        bar.repaint();
    }

    private class BarPanel extends JPanel {
        BarPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int h = getHeight();
            int w = getWidth();

            // track
            g2.setColor(new Color(226, 232, 240));
            g2.fill(new RoundRectangle2D.Float(0, 0, w, h, h, h));

            // fill (flat color, no gradient)
            int fillWidth = (int) (w * (percentage / 100.0));
            if (fillWidth > 0) {
                g2.setColor(barColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, fillWidth, h, h, h));
            }

            g2.dispose();
        }
    }
}
