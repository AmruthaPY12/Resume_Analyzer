package com.resumeanalyzer.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * SkillTag
 * --------
 * A small rounded "pill" label used to display an extracted skill
 * (e.g. "Java", "JDBC", "SQL") in the Resume Analysis panel. Flat
 * style: light background tint with a thin matching border.
 */
public class SkillTag extends JLabel {

    private static final Color BG = new Color(239, 246, 255);
    private static final Color BORDER = new Color(191, 219, 254);
    private static final Color FG = new Color(29, 78, 216);

    public SkillTag(String text) {
        super(text);
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setForeground(FG);
        setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(BG);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight()));
        g2.setColor(BORDER);
        g2.setStroke(new BasicStroke(1f));
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight()));
        g2.dispose();
        super.paintComponent(g);
    }
}
