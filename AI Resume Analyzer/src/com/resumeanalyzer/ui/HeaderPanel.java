package com.resumeanalyzer.ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * HeaderPanel
 * -----------
 * Top application header: logo/icon, title, subtitle, current date,
 * and a user profile icon (placeholder avatar circle with initials).
 */
public class HeaderPanel extends JPanel {

    private static final Color BLUE = new Color(37, 99, 235);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);

    public HeaderPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(14, 24, 14, 24)));
        setPreferredSize(new Dimension(100, 76));

        add(buildLeft(), BorderLayout.WEST);
        add(buildRight(), BorderLayout.EAST);
    }

    private JPanel buildLeft() {
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        left.setOpaque(false);

        // Simple emoji-based logo as a placeholder for a real icon asset.
        JLabel logoText = new JLabel("\uD83D\uDCC4");
        logoText.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));

        JPanel textBlock = new JPanel();
        textBlock.setOpaque(false);
        textBlock.setLayout(new BoxLayout(textBlock, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("AI Resume Analyzer");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(TEXT_DARK);
        title.setAlignmentX(LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Smart Resume Screening System");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(TEXT_MUTED);
        subtitle.setAlignmentX(LEFT_ALIGNMENT);

        textBlock.add(title);
        textBlock.add(subtitle);

        left.add(logoText);
        left.add(textBlock);
        return left;
    }

    private JPanel buildRight() {
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 0));
        right.setOpaque(false);

        JLabel dateLabel = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy")));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        dateLabel.setForeground(TEXT_MUTED);

        JLabel avatar = new JLabel("HR", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BLUE);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        avatar.setForeground(Color.WHITE);
        avatar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        avatar.setPreferredSize(new Dimension(36, 36));
        avatar.setOpaque(false);

        right.add(dateLabel);
        right.add(avatar);
        return right;
    }
}
