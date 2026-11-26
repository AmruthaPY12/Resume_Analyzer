package com.resumeanalyzer.ui;

import com.resumeanalyzer.components.DashboardCard;
import com.resumeanalyzer.components.RoundedPanel;

import javax.swing.*;
import java.awt.*;

/**
 * DashboardPanel
 * --------------
 * The main "Dashboard" landing view: welcome banner + four stat cards
 * (Total Resumes, Analyzed Resumes, Shortlisted Candidates, Average
 * Resume Score). Values shown are static placeholders -- wire them to
 * live JDBC query results once the database layer exists.
 */
public class DashboardPanel extends JPanel {

    private static final Color BG = new Color(248, 250, 252);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(BG);
        setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        add(buildWelcome(), BorderLayout.NORTH);
        add(buildCardsSection(), BorderLayout.CENTER);
    }

    private JPanel buildWelcome() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 24, 0));

        JLabel welcome = new JLabel("Welcome back, HR Team \uD83D\uDC4B");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcome.setForeground(TEXT_DARK);
        welcome.setAlignmentX(LEFT_ALIGNMENT);

        JLabel sub = new JLabel("Here's what's happening with your candidate pipeline today.");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sub.setForeground(TEXT_MUTED);
        sub.setAlignmentX(LEFT_ALIGNMENT);

        panel.add(welcome);
        panel.add(Box.createVerticalStrut(4));
        panel.add(sub);
        return panel;
    }

    private JPanel buildCardsSection() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);

        JPanel cards = new JPanel(new GridLayout(1, 4, 20, 0));
        cards.setOpaque(false);

        cards.add(new DashboardCard("\uD83D\uDCC1", "Total Resumes", "128", new Color(37, 99, 235)));
        cards.add(new DashboardCard("\u2705", "Analyzed Resumes", "94", new Color(13, 148, 136)));
        cards.add(new DashboardCard("\u2B50", "Shortlisted Candidates", "27", new Color(234, 179, 8)));
        cards.add(new DashboardCard("\uD83D\uDCC8", "Average Resume Score", "78%", new Color(139, 92, 246)));

        JPanel cardsHolder = new JPanel(new BorderLayout());
        cardsHolder.setOpaque(false);
        cardsHolder.add(cards, BorderLayout.NORTH);

        wrapper.add(cardsHolder, BorderLayout.NORTH);
        wrapper.add(buildRecentActivity(), BorderLayout.CENTER);
        return wrapper;
    }

    private JComponent buildRecentActivity() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        card.setPreferredSize(new Dimension(100, 260));

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Recent Activity");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(TEXT_DARK);
        title.setAlignmentX(LEFT_ALIGNMENT);
        content.add(title);
        content.add(Box.createVerticalStrut(14));

        String[] activity = {
                "Ravi Kumar's resume was analyzed \u2014 Score: 82%",
                "New resume uploaded by Ananya Sharma",
                "Candidate Priya Menon shortlisted for Java Developer",
                "Report generated for Backend Engineering batch"
        };

        for (String line : activity) {
            JLabel item = new JLabel("\u2022  " + line);
            item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            item.setForeground(TEXT_MUTED);
            item.setAlignmentX(LEFT_ALIGNMENT);
            item.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
            content.add(item);
        }

        JPanel outer = new JPanel(new BorderLayout());
        outer.setOpaque(false);
        outer.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        outer.add(card, BorderLayout.CENTER);
        card.add(content, BorderLayout.NORTH);
        return outer;
    }
}
