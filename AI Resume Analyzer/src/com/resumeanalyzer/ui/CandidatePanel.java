package com.resumeanalyzer.ui;

import com.resumeanalyzer.components.RoundedButton;
import com.resumeanalyzer.components.RoundedPanel;
import com.resumeanalyzer.components.SkillTag;

import javax.swing.*;
import java.awt.*;

/**
 * CandidatePanel
 * --------------
 * "Candidate Profile" screen: displays candidate details in a clean
 * card layout (Name, Email, Phone, Qualification, Experience, Skills,
 * Resume Status, Last Updated). Static placeholder data -- replace
 * with a JDBC-backed candidate record once the backend is built.
 */
public class CandidatePanel extends JPanel {

    private static final Color BG = new Color(248, 250, 252);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);
    private static final Color BLUE = new Color(37, 99, 235);
    private static final Color TEAL = new Color(13, 148, 136);

    public CandidatePanel() {
        setLayout(new BorderLayout());
        setBackground(BG);
        setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        add(buildHeading(), BorderLayout.NORTH);
        add(buildProfileCard(), BorderLayout.CENTER);
    }

    private JPanel buildHeading() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));

        JLabel title = new JLabel("Candidate Profile");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(TEXT_DARK);
        title.setAlignmentX(LEFT_ALIGNMENT);

        JLabel sub = new JLabel("Complete overview of the candidate's submitted information.");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        sub.setForeground(TEXT_MUTED);
        sub.setAlignmentX(LEFT_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(4));
        panel.add(sub);
        return panel;
    }

    private JComponent buildProfileCard() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BorderLayout(0, 20));
        card.setBorder(BorderFactory.createEmptyBorder(28, 30, 28, 30));

        card.add(buildProfileHeader(), BorderLayout.NORTH);
        card.add(buildFieldsGrid(), BorderLayout.CENTER);
        card.add(buildFooterActions(), BorderLayout.SOUTH);
        return card;
    }

    private JPanel buildProfileHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 0));
        left.setOpaque(false);

        JLabel avatar = new JLabel("RK", SwingConstants.CENTER) {
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
        avatar.setPreferredSize(new Dimension(56, 56));
        avatar.setForeground(Color.WHITE);
        avatar.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel nameBlock = new JPanel();
        nameBlock.setOpaque(false);
        nameBlock.setLayout(new BoxLayout(nameBlock, BoxLayout.Y_AXIS));

        JLabel name = new JLabel("Ravi Kumar");
        name.setFont(new Font("Segoe UI", Font.BOLD, 18));
        name.setForeground(TEXT_DARK);
        name.setAlignmentX(LEFT_ALIGNMENT);

        JLabel role = new JLabel("Java Backend Developer Candidate");
        role.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        role.setForeground(TEXT_MUTED);
        role.setAlignmentX(LEFT_ALIGNMENT);

        nameBlock.add(name);
        nameBlock.add(Box.createVerticalStrut(2));
        nameBlock.add(role);

        left.add(avatar);
        left.add(nameBlock);

        JLabel statusBadge = new JLabel("Shortlisted", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 253, 244));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.setColor(new Color(187, 247, 208));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        statusBadge.setForeground(new Color(21, 128, 61));
        statusBadge.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusBadge.setPreferredSize(new Dimension(110, 30));

        header.add(left, BorderLayout.WEST);
        header.add(statusBadge, BorderLayout.EAST);
        return header;
    }

    private JComponent buildFieldsGrid() {
        JPanel grid = new JPanel(new GridLayout(3, 2, 30, 18));
        grid.setOpaque(false);
        grid.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(20, 0, 20, 0)));

        grid.add(buildField("Email", "ravi.kumar@example.com"));
        grid.add(buildField("Phone", "9876543210"));
        grid.add(buildField("Highest Qualification", "MCA"));
        grid.add(buildField("Experience", "1-3 Years"));
        grid.add(buildSkillsField());
        grid.add(buildField("Last Updated", "01 Aug 2026"));

        return grid;
    }

    private JPanel buildField(String label, String value) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel l = new JLabel(label);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        l.setForeground(TEXT_MUTED);
        l.setAlignmentX(LEFT_ALIGNMENT);

        JLabel v = new JLabel(value);
        v.setFont(new Font("Segoe UI", Font.BOLD, 14));
        v.setForeground(TEXT_DARK);
        v.setAlignmentX(LEFT_ALIGNMENT);

        panel.add(l);
        panel.add(Box.createVerticalStrut(4));
        panel.add(v);
        return panel;
    }

    private JPanel buildSkillsField() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel l = new JLabel("Skills");
        l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        l.setForeground(TEXT_MUTED);
        l.setAlignmentX(LEFT_ALIGNMENT);

        JPanel tags = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        tags.setOpaque(false);
        tags.setAlignmentX(LEFT_ALIGNMENT);
        for (String s : new String[]{"Java", "JDBC", "SQL", "Swing"}) {
            tags.add(new SkillTag(s));
        }

        panel.add(l);
        panel.add(Box.createVerticalStrut(4));
        panel.add(tags);
        return panel;
    }

    private JPanel buildFooterActions() {
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        actions.setOpaque(false);
        actions.add(new RoundedButton("Generate Report", RoundedButton.Style.PRIMARY));
        actions.add(new RoundedButton("Preview", RoundedButton.Style.SECONDARY));
        actions.add(new RoundedButton("Cancel", RoundedButton.Style.SECONDARY));
        return actions;
    }
}
