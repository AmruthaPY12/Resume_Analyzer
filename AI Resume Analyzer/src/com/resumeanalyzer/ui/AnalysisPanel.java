package com.resumeanalyzer.ui;

import com.resumeanalyzer.components.ProgressCard;
import com.resumeanalyzer.components.RoundedButton;
import com.resumeanalyzer.components.RoundedPanel;
import com.resumeanalyzer.components.SkillTag;

import javax.swing.*;
import java.awt.*;

/**
 * AnalysisPanel
 * -------------
 * "Resume Analysis" screen: progress metrics (Resume Score, Skills
 * Match, ATS Compatibility, Experience Match, Education Match),
 * extracted skill tags, an analysis summary card, and recommendation
 * cards. All values are static placeholders for the UI-only build --
 * wire them to real analysis output once the AI/backend logic exists.
 */
public class AnalysisPanel extends JPanel {

    private static final Color BG = new Color(248, 250, 252);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);
    private static final Color BLUE = new Color(37, 99, 235);
    private static final Color TEAL = new Color(13, 148, 136);

    public AnalysisPanel() {
        setLayout(new BorderLayout());
        setBackground(BG);
        setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        add(buildHeading(), BorderLayout.NORTH);

        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));

        body.add(buildProgressSection());
        body.add(Box.createVerticalStrut(20));

        JPanel twoCol = new JPanel(new GridLayout(1, 2, 20, 0));
        twoCol.setOpaque(false);
        twoCol.add(buildSkillsCard());
        twoCol.add(buildSummaryCard());
        twoCol.setAlignmentX(LEFT_ALIGNMENT);
        body.add(twoCol);

        body.add(Box.createVerticalStrut(20));
        body.add(buildRecommendationsCard());

        JScrollPane scroll = new JScrollPane(body);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel buildHeading() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));

        JPanel textBlock = new JPanel();
        textBlock.setOpaque(false);
        textBlock.setLayout(new BoxLayout(textBlock, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Resume Analysis");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(TEXT_DARK);
        title.setAlignmentX(LEFT_ALIGNMENT);

        JLabel sub = new JLabel("AI-generated insights for: Ravi_Kumar_Resume.pdf");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        sub.setForeground(TEXT_MUTED);
        sub.setAlignmentX(LEFT_ALIGNMENT);

        textBlock.add(title);
        textBlock.add(Box.createVerticalStrut(4));
        textBlock.add(sub);

        JButton analyze = new RoundedButton("Analyze Resume", RoundedButton.Style.PRIMARY);

        panel.add(textBlock, BorderLayout.WEST);
        panel.add(analyze, BorderLayout.EAST);
        return panel;
    }

    private JComponent buildProgressSection() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new GridLayout(1, 5, 24, 0));
        card.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));
        card.setPreferredSize(new Dimension(100, 110));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        card.add(new ProgressCard("Resume Score", 82, BLUE));
        card.add(new ProgressCard("Skills Match", 76, TEAL));
        card.add(new ProgressCard("ATS Compatibility", 88, new Color(139, 92, 246)));
        card.add(new ProgressCard("Experience Match", 65, new Color(234, 179, 8)));
        card.add(new ProgressCard("Education Match", 91, new Color(236, 72, 153)));

        card.setAlignmentX(LEFT_ALIGNMENT);
        return card;
    }

    private JComponent buildSkillsCard() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel title = new JLabel("Extracted Skills");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(TEXT_DARK);

        JPanel tags = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        tags.setOpaque(false);
        String[] skills = {"Java", "JDBC", "SQL", "Swing", "OOP", "HTML", "CSS", "JavaScript"};
        for (String s : skills) {
            tags.add(new SkillTag(s));
        }

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        top.add(title, BorderLayout.WEST);

        card.add(top, BorderLayout.NORTH);
        card.add(tags, BorderLayout.CENTER);
        return card;
    }

    private JComponent buildSummaryCard() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));

        JLabel title = new JLabel("Analysis Summary");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(TEXT_DARK);

        JTextArea summary = new JTextArea(
                "The candidate demonstrates strong proficiency in core Java and " +
                "database connectivity concepts. The resume is well structured " +
                "with clear sections for education, skills, and projects. Overall " +
                "presentation aligns well with typical ATS parsing requirements."
        );
        summary.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        summary.setForeground(new Color(51, 65, 85));
        summary.setLineWrap(true);
        summary.setWrapStyleWord(true);
        summary.setEditable(false);
        summary.setOpaque(false);
        summary.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        card.add(title, BorderLayout.NORTH);
        card.add(summary, BorderLayout.CENTER);
        return card;
    }

    private JComponent buildRecommendationsCard() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(22, 24, 22, 24));
        card.setAlignmentX(LEFT_ALIGNMENT);

        JLabel title = new JLabel("Recommendations");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(TEXT_DARK);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));

        JPanel list = new JPanel(new GridLayout(2, 2, 14, 14));
        list.setOpaque(false);

        String[] recs = {
                "Add more technical skills relevant to the target role",
                "Improve project descriptions with measurable outcomes",
                "Include relevant certifications to strengthen credibility",
                "Optimize resume with ATS-friendly keywords"
        };

        for (String r : recs) {
            list.add(buildRecommendationItem(r));
        }

        card.add(title, BorderLayout.NORTH);
        card.add(list, BorderLayout.CENTER);
        return card;
    }

    private JComponent buildRecommendationItem(String text) {
        JPanel item = new JPanel(new BorderLayout(10, 0));
        item.setOpaque(true);
        item.setBackground(Color.WHITE);
        item.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)));

        JLabel bullet = new JLabel("\uD83D\uDCA1");
        bullet.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));

        JLabel label = new JLabel("<html><body style='width: 220px'>" + text + "</body></html>");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(new Color(51, 65, 85));

        item.add(bullet, BorderLayout.WEST);
        item.add(label, BorderLayout.CENTER);
        return item;
    }
}
