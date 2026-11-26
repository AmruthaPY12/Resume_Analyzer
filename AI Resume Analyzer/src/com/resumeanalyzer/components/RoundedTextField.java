package com.resumeanalyzer.components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * RoundedTextField
 * ----------------
 * A reusable rounded-border JTextField with placeholder text support
 * and a focus-highlight animation (border color shifts to accent blue).
 *
 * Designed for reuse across UploadPanel, CandidatePanel, etc.
 * Validation (e.g. "required", "invalid email") is handled by an
 * external JLabel placed beneath the field -- see ValidationLabel usage
 * in UploadPanel for the pattern to be wired to JDBC-backed validation later.
 */
public class RoundedTextField extends JTextField {

    private static final Color BORDER_DEFAULT = new Color(226, 232, 240);
    private static final Color BORDER_FOCUS = new Color(37, 99, 235);
    private static final Color TEXT_MUTED = new Color(148, 163, 184);

    private final String placeholder;
    private boolean focused = false;

    public RoundedTextField(String placeholder) {
        this(placeholder, 20);
    }

    public RoundedTextField(String placeholder, int columns) {
        super(columns);
        this.placeholder = placeholder;

        setOpaque(false);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(new Color(30, 41, 59));
        setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        setCaretColor(BORDER_FOCUS);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                focused = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                focused = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 12, 12));
        g2.dispose();

        super.paintComponent(g);

        if (getText().isEmpty() && placeholder != null && !focused) {
            Graphics2D pg = (Graphics2D) g.create();
            pg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            pg.setColor(TEXT_MUTED);
            pg.setFont(getFont());
            FontMetrics fm = pg.getFontMetrics();
            pg.drawString(placeholder, getInsets().left, (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
            pg.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(focused ? 1.8f : 1.2f));
        g2.setColor(focused ? BORDER_FOCUS : BORDER_DEFAULT);
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 12, 12));
        g2.dispose();
    }

    /** Convenience helper so future JDBC validation code can listen for changes. */
    public void onChange(Runnable callback) {
        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { callback.run(); }
            public void removeUpdate(DocumentEvent e) { callback.run(); }
            public void changedUpdate(DocumentEvent e) { callback.run(); }
        });
    }
}
