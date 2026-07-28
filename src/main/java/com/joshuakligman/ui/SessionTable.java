package com.joshuakligman.ui;

import com.joshuakligman.model.CodingSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

public class SessionTable {

    private final VBox root;
    private final TableView<CodingSession> tableView = new TableView<>();
    private final ObservableList<CodingSession> sessions = FXCollections.observableArrayList();

    public SessionTable() {
        tableView.setItems(sessions);
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(
                column("Project", "project", 110),
                column("Language", "language", 90),
                column("Date", "date", 95),
                column("Start", "startTime", 60),
                column("End", "endTime", 60),
                column("Min", "duration", 55),
                column("Feature", "feature", 140),
                column("Notes", "notes", 160),
                column("Bugs found", "bugsEncountered", 140),
                column("Bugs fixed", "bugsFixed", 140),
                column("Bugs open", "bugsRemaining", 140),
                column("Rating", "productivity", 60)
        );

        tableView.setPlaceholder(new Label("No sessions logged yet"));

        root = new VBox(10, new Label("All Sessions"), tableView);
        root.setPadding(new Insets(15));
        VBox.setVgrow(tableView, javafx.scene.layout.Priority.ALWAYS);
    }

    private TableColumn<CodingSession, ?> column(String header, String property, double width) {
        TableColumn<CodingSession, Object> col = new TableColumn<>(header);
        col.setCellValueFactory(new PropertyValueFactory<>(property));
        col.setPrefWidth(width);
        return col;
    }

    public void setSessions(List<CodingSession> list) {
        sessions.setAll(list);
    }

    public VBox getRoot() { return root; }
    public TableView<CodingSession> getTableView() { return tableView; }
    public CodingSession getSelected() {
        return tableView.getSelectionModel().getSelectedItem();
    }
}