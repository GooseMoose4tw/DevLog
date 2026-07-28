package com.joshuakligman.ui;

import com.joshuakligman.model.CodingSession;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SessionForm {

    private final VBox root;

    private final TextField projectField = new TextField();
    private final ComboBox<String> languageBox = new ComboBox<>();
    private final DatePicker datePicker = new DatePicker();
    private final TextField startTimeField = new TextField();
    private final TextField endTimeField = new TextField();
    private final TextField durationField = new TextField();
    private final TextField featureField = new TextField();
    private final TextArea notesArea = new TextArea();
    private final TextArea bugsEncounteredArea = new TextArea();
    private final TextArea bugsFixedArea = new TextArea();
    private final TextArea bugsRemainingArea = new TextArea();
    private final ComboBox<Integer> productivityBox = new ComboBox<>();

    private final Button addButton = new Button("Add");
    private final Button updateButton = new Button("Update");
    private final Button deleteButton = new Button("Delete");
    private final Button clearButton = new Button("Clear");

    //timer
    private final Button timerButton = new Button("Start Session");
    private final Label timerLabel = new Label("00:00:00");
    private Timeline timeline;
    private LocalDateTime sessionStart;

    public SessionForm() {
        languageBox.getItems().addAll(
                "Java", "Python", "JavaScript", "TypeScript", "C#",
                "C++", "SQL", "HTML/CSS", "Other");
        languageBox.setEditable(true);

        productivityBox.getItems().addAll(1, 2, 3, 4, 5);

        startTimeField.setPromptText("HH:MM");
        endTimeField.setPromptText("HH:MM");
        durationField.setPromptText("minutes");

        notesArea.setPrefRowCount(3);
        bugsEncounteredArea.setPrefRowCount(2);
        bugsFixedArea.setPrefRowCount(2);
        bugsRemainingArea.setPrefRowCount(2);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);

        int r = 0;
        grid.addRow(r++, new Label("Project"), projectField);
        grid.addRow(r++, new Label("Language"), languageBox);
        grid.addRow(r++, new Label("Date"), datePicker);
        grid.addRow(r++, new Label("Start time"), startTimeField);
        grid.addRow(r++, new Label("End time"), endTimeField);
        grid.addRow(r++, new Label("Duration"), durationField);
        grid.addRow(r++, new Label("Feature"), featureField);
        grid.addRow(r++, new Label("Notes"), notesArea);
        grid.addRow(r++, new Label("Bugs found"), bugsEncounteredArea);
        grid.addRow(r++, new Label("Bugs fixed"), bugsFixedArea);
        grid.addRow(r++, new Label("Bugs open"), bugsRemainingArea);
        grid.addRow(r++, new Label("Productivity"), productivityBox);


        HBox buttonRow = new HBox(8, addButton, updateButton, deleteButton, clearButton);
        buttonRow.setPadding(new Insets(12, 0, 0, 0));

        //timer row
        HBox timerBox = new HBox(10, timerButton, timerLabel);
        timerBox.setAlignment(Pos.CENTER_LEFT);
        timerBox.getStyleClass().add("timer-box");
        timerButton.setOnAction(e -> toggleTimer());

        root = new VBox(10, new Label("Session Details"), timerBox, grid, buttonRow);
        root.setPadding(new Insets(15));
    }

    public VBox getRoot() { return root; }

    public TextField getProjectField() { return projectField; }
    public ComboBox<String> getLanguageBox() { return languageBox; }
    public DatePicker getDatePicker() { return datePicker; }
    public TextField getStartTimeField() { return startTimeField; }
    public TextField getEndTimeField() { return endTimeField; }
    public TextField getDurationField() { return durationField; }
    public TextField getFeatureField() { return featureField; }
    public TextArea getNotesArea() { return notesArea; }
    public TextArea getBugsEncounteredArea() { return bugsEncounteredArea; }
    public TextArea getBugsFixedArea() { return bugsFixedArea; }
    public TextArea getBugsRemainingArea() { return bugsRemainingArea; }
    public ComboBox<Integer> getProductivityBox() { return productivityBox; }

    public Button getAddButton() { return addButton; }
    public Button getUpdateButton() { return updateButton; }
    public Button getDeleteButton() { return deleteButton; }
    public Button getClearButton() { return clearButton; }

    public CodingSession readForm(int id) {
        String project = projectField.getText().trim();
        String language = languageBox.getEditor().getText().trim();
        String duration = durationField.getText().trim();

        if (project.isEmpty() || language.isEmpty() || datePicker.getValue() == null) {
            showError("Project, language, and date are required.");
            return null;
        }

        int minutes;
        try {
            minutes = Integer.parseInt(duration);
        } catch (NumberFormatException ex) {
            showError("Duration must be a whole number of minutes.");
            return null;
        }

        if (minutes <= 0) {
            showError("Duration must be greater than zero.");
            return null;
        }

        Integer rating = productivityBox.getValue();
        if (rating == null) {
            showError("Please select a productivity rating.");
            return null;
        }

        return new CodingSession(
                id, project, language,
                datePicker.getValue().toString(),
                startTimeField.getText().trim(),
                endTimeField.getText().trim(),
                minutes,
                featureField.getText().trim(),
                notesArea.getText().trim(),
                bugsEncounteredArea.getText().trim(),
                bugsFixedArea.getText().trim(),
                bugsRemainingArea.getText().trim(),
                rating
        );
    }

    public void clear() {
        projectField.clear();
        languageBox.getEditor().clear();
        languageBox.setValue(null);
        datePicker.setValue(null);
        startTimeField.clear();
        endTimeField.clear();
        durationField.clear();
        featureField.clear();
        notesArea.clear();
        bugsEncounteredArea.clear();
        bugsFixedArea.clear();
        bugsRemainingArea.clear();
        productivityBox.setValue(null);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void populate(CodingSession s) {
        projectField.setText(s.getProject());
        languageBox.getEditor().setText(s.getLanguage());
        datePicker.setValue(LocalDate.parse(s.getDate()));
        startTimeField.setText(s.getStartTime());
        endTimeField.setText(s.getEndTime());
        durationField.setText(String.valueOf(s.getDuration()));
        featureField.setText(s.getFeature());
        notesArea.setText(s.getNotes());
        bugsEncounteredArea.setText(s.getBugsEncountered());
        bugsFixedArea.setText(s.getBugsFixed());
        bugsRemainingArea.setText(s.getBugsRemaining());
        productivityBox.setValue(s.getProductivity());
    }

    //null timeline means no session running
    private void toggleTimer() {
        if (timeline == null) {
            startTimer();
        } else {
            stopTimer();
        }
    }

    private void startTimer() {
        sessionStart = LocalDateTime.now();
        datePicker.setValue(sessionStart.toLocalDate());
        startTimeField.setText(sessionStart.format(DateTimeFormatter.ofPattern("HH:mm")));

        timerButton.setText("Stop Session");

        //ticks once per second and recalculates elapsed time
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            long seconds = ChronoUnit.SECONDS.between(sessionStart, LocalDateTime.now());
            timerLabel.setText(String.format("%02d:%02d:%02d",
                    seconds / 3600, (seconds % 3600) / 60, seconds % 60));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopTimer() {
        timeline.stop();
        timeline = null;

        LocalDateTime end = LocalDateTime.now();
        endTimeField.setText(end.format(DateTimeFormatter.ofPattern("HH:mm")));

        //at least 1 minute so validation doesnt reject short sessions
        long minutes = ChronoUnit.MINUTES.between(sessionStart, end);
        durationField.setText(String.valueOf(Math.max(1, minutes)));

        timerButton.setText("Start Session");
        timerLabel.setText("00:00:00");
    }
}