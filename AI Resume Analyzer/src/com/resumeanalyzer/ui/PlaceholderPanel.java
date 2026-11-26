package com.resumeanalyzer.ui;

import com.resumeanalyzer.components.RoundedButton;
import com.resumeanalyzer.components.RoundedPanel;

import javax.swing.*;
import java.awt.*;

/**
 * PlaceholderPanel
 * ----------------
 * A reusable "coming soon" style card used for sidebar sections whose
 * detailed UI (Skills Match, Reports, Settings, Help) is out of scope
 * for this frontend-only build, but which still need a polished,
 * on-brand screen wired into the navigation.
 */
public class PlaceholderPanel extends JPanel {

    private static final Color BG = new Color(248, 250, 252);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);

    public PlaceholderPanel(String icon, String title, String description, String actionLabel) {
        setLayout(new GridBagLayout());
        setBackground(BG);

        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(48, 60, 48, 60));
        card.setPreferredSize(new Dimension(460, 300));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 44));
        iconLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(TEXT_DARK);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel("<html><body style='text-align:center; width:280px'>" + description + "</body></html>", SwingConstants.CENTER);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descLabel.setForeground(TEXT_MUTED);
        descLabel.setAlignmentX(CENTER_ALIGNMENT);

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(16));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(descLabel);

        if (actionLabel != null) {
            card.add(Box.createVerticalStrut(20));
            RoundedButton button = new RoundedButton(actionLabel, RoundedButton.Style.PRIMARY);
            button.setAlignmentX(CENTER_ALIGNMENT);
            card.add(button);
        }

        add(card);
    }
}
