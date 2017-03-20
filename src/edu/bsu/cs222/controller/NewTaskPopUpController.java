package edu.bsu.cs222.controller;

import edu.bsu.cs222.todolist.Task;
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
    private TextField taskNameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button addButton;
    private DateTimeFormatter dateTimeFormatter;
    private Task newTask;

    public NewTaskPopUpController(){
        dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        newTask = null;
    }

    @FXML
    public void handleAddButtonPress() {
        if (isAllFieldsAreFilledOut() && isDateFilledOutCorrectly()) {
            setNewTask();
            closeStage();
        }
    }

    private boolean isAllFieldsAreFilledOut() {
        if (!(taskNameField.getText().equals("") && descriptionField.getText().equals("") &&
                date.getValue() == null)) {
            return true;
        }
        else {
            errorLabel.setText("Please fill out all fields");
            return false;
        }
    }

    private boolean isDateFilledOutCorrectly() {
        try {
            dateTimeFormatter.format(date.getValue());
            return true;
        }
        catch (Exception e) {
            errorLabel.setText("Please fill out the date correctly or use the date picker");
            return false;
        }
    }

    private void setNewTask() {
        newTask = Task.withTaskName(taskNameField.getText())
                        .andDescription(descriptionField.getText())
                      .andDate(dateTimeFormatter.format(date.getValue()));
    }

    private void closeStage() {
        Stage popUpStage = (Stage) addButton.getScene().getWindow();
        popUpStage.close();
    }

    @SuppressWarnings("WeakerAccess but I need to get the newTask variable")
    public Task getNewTask() {
        return newTask;
    }
}
