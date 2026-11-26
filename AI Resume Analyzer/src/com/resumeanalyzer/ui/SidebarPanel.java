package com.resumeanalyzer.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * SidebarPanel
 * ------------
 * Left navigation menu: Dashboard, Upload Resume, Resume Analysis,
 * Candidate Profile, Skills Match, Reports, Settings, Help, Logout.
 *
 * Clean, light theme: white background, thin right border, a soft
 * light-blue highlight for the active item. Fires a callback with the
 * selected menu key so DashboardFrame can switch the center
 * CardLayout panel accordingly.
 */
public class SidebarPanel extends JPanel {

    private static final Color BG = Color.WHITE;
    private static final Color ACTIVE_BG = new Color(239, 246, 255);
    private static final Color ACTIVE_TEXT = new Color(37, 99, 235);
    private static final Color TEXT = new Color(71, 85, 105);
    private static final Color HOVER_BG = new Color(248, 250, 252);

    private final Map<String, JButton> menuButtons = new LinkedHashMap<>();
    private String activeKey = "Dashboard";

    public SidebarPanel(Consumer<String> onNavigate) {
        setLayout(new BorderLayout());
        setBackground(BG);
        setPreferredSize(new Dimension(220, 100));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(16, 0, 16, 0)));

        String[][] items = {
                {"Dashboard", "\uD83D\uDCCA"},
                {"Upload Resume", "\u2B06"},
                {"Resume Analysis", "\uD83D\uDD0D"},
                {"Candidate Profile", "\uD83D\uDC64"},
                {"Skills Match", "\uD83C\uDFAF"},
                {"Reports", "\uD83D\uDCC8"},
                {"Settings", "\u2699"},
                {"Help", "\u2753"}
        };

        JPanel menu = new JPanel();
        menu.setOpaque(false);
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

        for (String[] item : items) {
            JButton btn = createMenuButton(item[1], item[0]);
            btn.addActionListener(e -> {
                setActive(item[0]);
                onNavigate.accept(item[0]);
            });
            menuButtons.put(item[0], btn);
            menu.add(btn);
            menu.add(Box.createVerticalStrut(2));
        }

        JButton logout = createMenuButton("\u23FB", "Logout");
        logout.setForeground(new Color(220, 38, 38));

        setActive("Dashboard");

        add(menu, BorderLayout.NORTH);
        add(wrapBottom(logout), BorderLayout.SOUTH);
    }

    private JPanel wrapBottom(JButton logout) {
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(12, 12, 0, 12)));
        bottom.add(logout, BorderLayout.NORTH);
        return bottom;
    }

    private JButton createMenuButton(String icon, String label) {
        JButton button = new JButton("  " + icon + "   " + label);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setForeground(TEXT);
        button.setBackground(BG);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!label.equals(activeKey)) {
                    button.setBackground(HOVER_BG);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!label.equals(activeKey)) {
                    button.setBackground(BG);
                }
            }
        });

        return button;
    }

    private void setActive(String key) {
        activeKey = key;
        for (Map.Entry<String, JButton> entry : menuButtons.entrySet()) {
            boolean isActive = entry.getKey().equals(key);
            entry.getValue().setBackground(isActive ? ACTIVE_BG : BG);
            entry.getValue().setForeground(isActive ? ACTIVE_TEXT : TEXT);
            entry.getValue().setFont(new Font("Segoe UI", isActive ? Font.BOLD : Font.PLAIN, 13));
        }
    }
}
