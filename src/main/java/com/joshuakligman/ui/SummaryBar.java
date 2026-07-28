package com.joshuakligman.ui;

import com.joshuakligman.model.CodingSession;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SummaryBar {

    private final HBox root;
    private final Label totalHoursLabel = new Label();
    private final Label projectCountLabel = new Label();
    private final Label favoriteLanguageLabel = new Label();
    private final Label openBugsLabel = new Label();

    public SummaryBar() {
        root = new HBox(30,
                totalHoursLabel, projectCountLabel,
                favoriteLanguageLabel, openBugsLabel);
        root.setPadding(new Insets(12, 15, 12, 15));
        root.getStyleClass().add("summary-bar");
        update(List.of());
    }

    public void update(List<CodingSession> sessions) {
        int totalMinutes = 0;
        Set<String> projects = new HashSet<>();
        Map<String, Integer> languageCounts = new HashMap<>();
        int openBugs = 0;

        for (CodingSession s : sessions) {
            totalMinutes += s.getDuration();

            if (s.getProject() != null && !s.getProject().isBlank()) {
                projects.add(s.getProject());
            }

            String lang = s.getLanguage();
            if (lang != null && !lang.isBlank()) {
                languageCounts.put(lang, languageCounts.getOrDefault(lang, 0) + 1);
            }

            String bugs = s.getBugsRemaining();
            if (bugs != null && !bugs.isBlank()) {
                openBugs += bugs.split("\n").length;
            }
        }

        String favorite = "—";
        int best = 0;
        for (Map.Entry<String, Integer> entry : languageCounts.entrySet()) {
            if (entry.getValue() > best) {
                best = entry.getValue();
                favorite = entry.getKey();
            }
        }

        double hours = totalMinutes / 60.0;

        totalHoursLabel.setText(String.format("Total hours: %.1f", hours));
        projectCountLabel.setText("Projects worked on: " + projects.size());
        favoriteLanguageLabel.setText("Most used language: " + favorite);
        openBugsLabel.setText("Open bugs logged: " + openBugs);
    }

    public HBox getRoot() { return root; }
}