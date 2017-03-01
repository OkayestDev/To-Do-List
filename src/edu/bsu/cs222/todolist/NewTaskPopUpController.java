package edu.bsu.cs222.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class NewTaskPopUpController {
    @FXML
    private DatePicker date;
    @FXML
    private TextField taskName;
    @FXML
    private TextField description;
    @FXML
    private Label errorMessages;
    @FXML
    private Button add;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    private Task newTask = null;

    @FXML
    public void handleAddButtonPress() {
        if (allFieldsAreFilledOut() && dateFilledOutCorrectly()) {
            newTask = new Task(taskName.getText(), description.getText(), dateFormatter.format(date.getValue()));
            Stage popUpStage = (Stage) add.getScene().getWindow();
            popUpStage.close();
        }
    }

    private boolean allFieldsAreFilledOut() {
        if (!(taskName.getText().equals("") && description.getText().equals("") &&
                date.getValue() == null)) {
            return true;
        }
        else {
            errorMessages.setText("Please fill out all fields");
            return false;
        }
    }

    private boolean dateFilledOutCorrectly() {
        try {
            dateFormatter.format(date.getValue());
            return true;
        }
        catch (Exception e) {
            errorMessages.setText("Please fill out the date correctly or use the date picker");
            return false;
        }
    }

    public Task getNewTask() {
        return newTask;
    }
}
