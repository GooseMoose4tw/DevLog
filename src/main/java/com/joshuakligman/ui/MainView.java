package com.joshuakligman.ui;

import com.joshuakligman.dao.SessionDAO;
import com.joshuakligman.model.CodingSession;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class MainView {

    //the whole window layout, five regions
    private final BorderPane root;

    //DAO
    private final SessionDAO dao = new SessionDAO();
    private final SessionForm form = new SessionForm();
    private final SessionTable table = new SessionTable();

    //Summary Bar
    private final SummaryBar summary = new SummaryBar();

    public MainView() {
        root = new BorderPane();

        //left = form, center = table, bottom = summary
        root.setLeft(form.getRoot());
        root.setCenter(table.getRoot());
        root.setBottom(summary.getRoot());

        //connect the buttons, then load whatever is already saved
        wireButtons();
        refreshTable();
    }

    //reads every session from the database and updates both the table and summary
    private void refreshTable() {
        List<CodingSession> all = dao.getAll();
        table.setSessions(all);
        summary.update(all);
    }

    //popup used when update or delete is clicked with no row selected
    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void wireButtons() {

        //CREATE - build a session from the form and save it
        form.getAddButton().setOnAction(e -> {
            //readForm returns null if validation failed
            CodingSession session = form.readForm(0);
            if (session == null) return;
            dao.insert(session);
            refreshTable();
            form.clear();
        });

        //clicking a row loads it into the form for editing
        table.getTableView().getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSel, newSel) -> {
                    if (newSel != null) form.populate(newSel);
                });

        //UPDATE - overwrite the selected row using its existing id
        form.getUpdateButton().setOnAction(e -> {
            CodingSession selected = table.getSelected();
            if (selected == null) {
                showWarning("Select a session to update.");
                return;
            }
            //pass the real id so the database knows which row to change
            CodingSession edited = form.readForm(selected.getId());
            if (edited == null) return;
            dao.update(edited);
            refreshTable();
            form.clear();
        });

        //DELETE - confirm first since it cant be undone
        form.getDeleteButton().setOnAction(e -> {
            CodingSession selected = table.getSelected();
            if (selected == null) {
                showWarning("Select a session to delete.");
                return;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "Delete this session? This cannot be undone.",
                    ButtonType.OK, ButtonType.CANCEL);
            confirm.setHeaderText(null);
            //orElse means closing the dialog counts as cancel
            if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                dao.delete(selected.getId());
                refreshTable();
                form.clear();
            }
        });

        //empties every field without touching the database
        form.getClearButton().setOnAction(e -> form.clear());
    }

    //Main needs this to put in the Scene
    public BorderPane getRoot() {
        return root;
    }
}