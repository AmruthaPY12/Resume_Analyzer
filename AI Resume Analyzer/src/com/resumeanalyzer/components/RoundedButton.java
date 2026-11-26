package com.resumeanalyzer.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * RoundedButton
 * -------------
 * A simple, flat button with soft rounded corners and a light hover
 * shade. No gradients, no drop shadows -- kept intentionally plain
 * for a clean, minimal look. Supports three styles:
 *   PRIMARY   -> solid blue fill, white text
 *   SECONDARY -> white fill, thin gray border, dark text
 *   DANGER    -> solid red fill, white text
 */
public class RoundedButton extends JButton {

    public enum Style { PRIMARY, SECONDARY, DANGER }

    private static final Color BLUE = new Color(37, 99, 235);
    private static final Color BLUE_HOVER = new Color(29, 78, 216);
    private static final Color SECONDARY_BG = Color.WHITE;
    private static final Color SECONDARY_BG_HOVER = new Color(248, 250, 252);
    private static final Color BORDER = new Color(209, 213, 219);
    private static final Color DANGER = new Color(220, 38, 38);
    private static final Color DANGER_HOVER = new Color(185, 28, 28);

    private final int arc;
    private final Style style;
    private boolean hover = false;

    public RoundedButton(String text, Style style) {
        this(text, style, 8);
    }

    public RoundedButton(String text, Style style, int arc) {
        super(text);
        this.style = style;
        this.arc = arc;

        setFont(new Font("Segoe UI", Font.PLAIN, 13));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(style == Style.SECONDARY ? new Color(55, 65, 81) : Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(9, 18, 9, 18));

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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        switch (style) {
            case PRIMARY:
                g2.setColor(hover ? BLUE_HOVER : BLUE);
                break;
            case DANGER:
                g2.setColor(hover ? DANGER_HOVER : DANGER);
                break;
            case SECONDARY:
            default:
                g2.setColor(hover ? SECONDARY_BG_HOVER : SECONDARY_BG);
                break;
        }

        g2.fill(shape);

        if (style == Style.SECONDARY) {
            g2.setColor(BORDER);
            g2.setStroke(new BasicStroke(1f));
            g2.draw(shape);
        }

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        return new Dimension(Math.max(d.width, 90), Math.max(d.height, 38));
    }
}
