package com.resumeanalyzer.ui;

import javax.swing.*;
import java.awt.*;

/**
 * DashboardFrame
 * --------------
 * The main application window. Combines:
 *   - HeaderPanel (top)
 *   - SidebarPanel (left navigation)
 *   - A CardLayout content area (center) that swaps between the
 *     Dashboard, Upload Resume, Resume Analysis, Candidate Profile,
 *     Skills Match, Reports, Settings, and Help screens.
 *
 * This class is UI-only and intentionally contains no JDBC, file I/O,
 * or resume-analysis logic. Each child panel is a self-contained
 * Swing component ready to be wired to a service/DAO layer later --
 * e.g. UploadPanel's Save button can later call a
 * com.resumeanalyzer.dao.CandidateDAO once JDBC is implemented.
 */
public class DashboardFrame extends JFrame {

    private static final String CARD_DASHBOARD = "Dashboard";
    private static final String CARD_UPLOAD = "Upload Resume";
    private static final String CARD_ANALYSIS = "Resume Analysis";
    private static final String CARD_CANDIDATE = "Candidate Profile";
    private static final String CARD_SKILLS = "Skills Match";
    private static final String CARD_REPORTS = "Reports";
    private static final String CARD_SETTINGS = "Settings";
    private static final String CARD_HELP = "Help";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);

    public DashboardFrame() {
        super("AI Resume Analyzer");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 700));
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        HeaderPanel header = new HeaderPanel();
        SidebarPanel sidebar = new SidebarPanel(this::navigateTo);

        buildContentPanel();

        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void buildContentPanel() {
        contentPanel.add(new DashboardPanel(), CARD_DASHBOARD);
        contentPanel.add(new UploadPanel(), CARD_UPLOAD);
        contentPanel.add(new AnalysisPanel(), CARD_ANALYSIS);
        contentPanel.add(new CandidatePanel(), CARD_CANDIDATE);
        contentPanel.add(new PlaceholderPanel("\uD83C\uDFAF", "Skills Match",
                "Compare candidate skills against job requirements once matching logic is implemented.",
                "Run Skills Match"), CARD_SKILLS);
        contentPanel.add(new PlaceholderPanel("\uD83D\uDCC8", "Reports",
                "Generate and export candidate screening reports from analyzed resumes.",
                "Generate Report"), CARD_REPORTS);
        contentPanel.add(new PlaceholderPanel("\u2699\uFE0F", "Settings",
                "Manage application preferences, database connection, and account settings here.",
                "Open Settings"), CARD_SETTINGS);
        contentPanel.add(new PlaceholderPanel("\u2753", "Help",
                "Find guidance on using the AI Resume Analyzer, FAQs, and support contact details.",
                "Contact Support"), CARD_HELP);
    }

    private void navigateTo(String key) {
        cardLayout.show(contentPanel, key);
    }
}
