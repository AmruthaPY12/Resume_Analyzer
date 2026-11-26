package com.resumeanalyzer.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * RoundedPanel
 * ------------
 * A plain white "card" container with a thin light-gray border and
 * softly rounded corners. No drop shadow -- kept flat and minimal.
 * Used to wrap sections such as the Analysis Summary, Candidate
 * Profile fields, Recommendations, etc.
 */
public class RoundedPanel extends JPanel {

    private static final Color BORDER = new Color(226, 232, 240);

    private final int arc;
    private final Color background;

    public RoundedPanel() {
        this(12, Color.WHITE);
    }

    public RoundedPanel(int arc, Color background) {
        this.arc = arc;
        this.background = background;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(background);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arc, arc));

        g2.setColor(BORDER);
        g2.setStroke(new BasicStroke(1f));
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arc, arc));

        g2.dispose();
        super.paintComponent(g);
    }
}
