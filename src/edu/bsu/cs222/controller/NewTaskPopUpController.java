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
    private TextField taskName;
    @FXML
    private TextField description;
    @FXML
    private Label errorMessages;
    @FXML
    private Button add;
    private DateTimeFormatter dateFormatter;
    private Task newTask;

    public NewTaskPopUpController(){
        dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
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
        if (!(taskName.getText().equals("") && description.getText().equals("") &&
                date.getValue() == null)) {
            return true;
        }
        else {
            errorMessages.setText("Please fill out all fields");
            return false;
        }
    }

    private boolean isDateFilledOutCorrectly() {
        try {
            dateFormatter.format(date.getValue());
            return true;
        }
        catch (Exception e) {
            errorMessages.setText("Please fill out the date correctly or use the date picker");
            return false;
        }
    }

    private void setNewTask() {
        newTask = Task.withTaskName(taskName.getText())
                .andDescription(description.getText())
                .andDate(dateFormatter.format(date.getValue()));
    }

    private void closeStage() {
        Stage popUpStage = (Stage) add.getScene().getWindow();
        popUpStage.close();
    }

    @SuppressWarnings("WeakerAccess but I need to get the newTask variable")
    public Task getNewTask() {
        return newTask;
    }
}
