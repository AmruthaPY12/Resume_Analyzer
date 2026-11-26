package com.resumeanalyzer;

import com.resumeanalyzer.ui.DashboardFrame;

import javax.swing.*;

/**
 * Main
 * ----
 * Application entry point for the AI Resume Analyzer Swing frontend.
 *
 * This class only launches the UI. Database connectivity, resume
 * parsing, and AI/analysis logic are intentionally out of scope for
 * this build (see the assignment's frontend-only constraint) and
 * should be added in dedicated packages later, e.g.:
 *   com.resumeanalyzer.dao      -> JDBC data-access objects
 *   com.resumeanalyzer.service  -> business logic / validation
 *   com.resumeanalyzer.model    -> Candidate, Resume, AnalysisResult, etc.
 */
public class Main {

    public static void main(String[] args) {
        // Use the system look and feel as a base; custom-painted
        // components (RoundedButton, DashboardCard, etc.) provide the
        // premium visual styling on top of it.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Fall back to the default cross-platform look and feel.
        }

        SwingUtilities.invokeLater(() -> {
            DashboardFrame frame = new DashboardFrame();
            frame.setVisible(true);
        });
    }
}
