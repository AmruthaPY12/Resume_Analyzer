package com.resumeanalyzer.ui;

import com.resumeanalyzer.components.RoundedButton;
import com.resumeanalyzer.components.RoundedPanel;
import com.resumeanalyzer.components.RoundedTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * UploadPanel
 * -----------
 * "Upload Resume" screen: candidate detail form on the left, and a
 * drag & drop / browse file upload area on the right.
 *
 * NOTE: This is UI ONLY. No file parsing, JDBC, or validation logic is
 * implemented -- the small red error JLabels created in addField() are
 * hidden by default and are ready to be shown by future backend
 * validation code (e.g. call label.setVisible(true) after a failed check).
 */
public class UploadPanel extends JPanel {

    private static final Color BG = new Color(248, 250, 252);
    private static final Color TEXT_DARK = new Color(15, 23, 42);
    private static final Color TEXT_MUTED = new Color(100, 116, 139);
    private static final Color ERROR = new Color(220, 38, 38);
    private static final Color BORDER = new Color(203, 213, 225);

    private JLabel fileSelectedLabel;

    public UploadPanel() {
        setLayout(new BorderLayout());
        setBackground(BG);
        setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));

        add(buildHeading(), BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2, 24, 0));
        content.setOpaque(false);
        content.add(buildFormCard());
        content.add(buildUploadCard());

        add(content, BorderLayout.CENTER);
    }

    private JPanel buildHeading() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));

        JLabel title = new JLabel("Upload Resume");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(TEXT_DARK);
        title.setAlignmentX(LEFT_ALIGNMENT);

        JLabel sub = new JLabel("Add candidate details and upload their resume for AI analysis.");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        sub.setForeground(TEXT_MUTED);
        sub.setAlignmentX(LEFT_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(4));
        panel.add(sub);
        return panel;
    }

    private JComponent buildFormCard() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new GridBagLayout());
        card.setBorder(BorderFactory.createEmptyBorder(26, 28, 26, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 4, 0);

        JLabel formTitle = new JLabel("Candidate Details");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formTitle.setForeground(TEXT_DARK);
        card.add(formTitle, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 16, 0);
        card.add(Box.createVerticalStrut(6), gbc);

        gbc.gridy++;
        addField(card, gbc, "Candidate Name", new RoundedTextField("Enter full name"), null);

        gbc.gridy++;
        addField(card, gbc, "Email", new RoundedTextField("name@example.com"), "Invalid email format");

        gbc.gridy++;
        addField(card, gbc, "Phone Number", new RoundedTextField("10-digit mobile number"), "Phone must be 10 digits");

        gbc.gridy++;
        JComboBox<String> qualification = new JComboBox<>(new String[]{
                "Select Qualification", "B.Tech / B.E.", "MCA", "M.Tech", "BCA", "MBA", "Other"});
        styleComboBox(qualification);
        addField(card, gbc, "Highest Qualification", qualification, null);

        gbc.gridy++;
        JComboBox<String> experience = new JComboBox<>(new String[]{
                "Select Experience", "Fresher", "0-1 Years", "1-3 Years", "3-5 Years", "5+ Years"});
        styleComboBox(experience);
        addField(card, gbc, "Experience", experience, null);

        gbc.gridy++;
        JTextArea skills = new JTextArea(3, 20);
        skills.setLineWrap(true);
        skills.setWrapStyleWord(true);
        skills.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        skills.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        addField(card, gbc, "Skills (comma separated)", skills, "Skills cannot be empty");

        gbc.gridy++;
        addField(card, gbc, "Resume Title", new RoundedTextField("e.g. Java Backend Developer Resume"), null);

        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 0, 0);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        buttons.setOpaque(false);
        buttons.add(new RoundedButton("Save", RoundedButton.Style.PRIMARY));
        buttons.add(new RoundedButton("Reset", RoundedButton.Style.SECONDARY));
        card.add(buttons, gbc);

        JScrollPane scroll = new JScrollPane(card);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        return scroll;
    }

    private void addField(RoundedPanel card, GridBagConstraints gbc, String labelText, JComponent field, String errorText) {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(TEXT_MUTED);
        label.setAlignmentX(LEFT_ALIGNMENT);

        field.setAlignmentX(LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height > 0
                ? Math.max(field.getPreferredSize().height, 40) : 40));

        wrapper.add(label);
        wrapper.add(Box.createVerticalStrut(6));
        wrapper.add(field);

        if (errorText != null) {
            JLabel error = new JLabel(errorText);
            error.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            error.setForeground(ERROR);
            error.setAlignmentX(LEFT_ALIGNMENT);
            error.setVisible(false); // shown later by validation logic
            wrapper.add(Box.createVerticalStrut(4));
            wrapper.add(error);
        }

        card.add(wrapper, gbc);
    }

    private void styleComboBox(JComboBox<String> box) {
        box.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createLineBorder(BORDER, 1, true));
    }

    private JComponent buildUploadCard() {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BorderLayout(0, 16));
        card.setBorder(BorderFactory.createEmptyBorder(26, 28, 26, 28));

        JLabel formTitle = new JLabel("Resume File");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formTitle.setForeground(TEXT_DARK);

        JPanel dropZone = buildDropZone();

        JPanel buttonsRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        buttonsRow.setOpaque(false);
        RoundedButton browseBtn = new RoundedButton("Browse File", RoundedButton.Style.SECONDARY);
        RoundedButton uploadBtn = new RoundedButton("Upload Resume", RoundedButton.Style.PRIMARY);
        buttonsRow.add(browseBtn);
        buttonsRow.add(uploadBtn);

        fileSelectedLabel = new JLabel("No file selected");
        fileSelectedLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        fileSelectedLabel.setForeground(TEXT_MUTED);
        fileSelectedLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        browseBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select Resume File");
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                fileSelectedLabel.setText("Selected: " + f.getName());
                fileSelectedLabel.setForeground(new Color(13, 148, 136));
            }
            // NOTE: actual file reading/parsing is intentionally NOT implemented (UI only).
        });

        JPanel south = new JPanel();
        south.setOpaque(false);
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
        buttonsRow.setAlignmentX(LEFT_ALIGNMENT);
        fileSelectedLabel.setAlignmentX(LEFT_ALIGNMENT);
        south.add(buttonsRow);
        south.add(fileSelectedLabel);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(formTitle, BorderLayout.NORTH);

        card.add(top, BorderLayout.NORTH);
        card.add(dropZone, BorderLayout.CENTER);
        card.add(south, BorderLayout.SOUTH);
        return card;
    }

    private JPanel buildDropZone() {
        JPanel zone = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(241, 245, 249));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.setColor(new Color(148, 163, 184));
                float[] dash = {6f, 6f};
                g2.setStroke(new BasicStroke(1.6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, dash, 0));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 16, 16);
                g2.dispose();
            }
        };
        zone.setOpaque(false);
        zone.setPreferredSize(new Dimension(100, 220));

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("\u2601\uFE0F", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        icon.setAlignmentX(CENTER_ALIGNMENT);

        JLabel text1 = new JLabel("Drag & Drop resume here");
        text1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        text1.setForeground(TEXT_DARK);
        text1.setAlignmentX(CENTER_ALIGNMENT);

        JLabel text2 = new JLabel("Supports PDF, DOC, DOCX (max 5MB)");
        text2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        text2.setForeground(TEXT_MUTED);
        text2.setAlignmentX(CENTER_ALIGNMENT);

        inner.add(icon);
        inner.add(Box.createVerticalStrut(10));
        inner.add(text1);
        inner.add(Box.createVerticalStrut(4));
        inner.add(text2);

        zone.add(inner);
        return zone;
    }
}
