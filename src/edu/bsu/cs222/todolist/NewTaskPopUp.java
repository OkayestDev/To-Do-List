package edu.bsu.cs222.todolist;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class NewTaskPopUp {
    private Stage window = new Stage();
    private DatePicker date = new DatePicker();
    private TextField taskName = new TextField();
    private TextField description = new TextField();
    private Button add = new Button();
    private HBox horizontalBox = new HBox(5);
    private VBox verticalBox = new VBox();
    private Label errorMessages = new Label();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    static String[] newTask = new String[3];

    public String[] getNewTask() {
        configureWindow();
        add.setOnAction(action -> {
            //Will need error handling, for example: task with no name or invalid date input
            if (allFieldsAreFilledOut() && dateFilledOutCorrectly()) {
                newTask[0] = taskName.getText();
                newTask[1] = description.getText();
                newTask[2] = dateFormatter.format(date.getValue());
                window.close();
            }
        });
        window.showAndWait();
        return newTask;
    }


    private void configureWindow() {
        description.setPromptText("Enter Description");
        taskName.setPromptText("Enter Task Name");
        date.setPromptText("Enter Date");
        add.setText("Add");
        horizontalBox.getChildren().addAll(taskName, description, date, add);
        verticalBox.getChildren().addAll(errorMessages, horizontalBox);
        Scene scene = new Scene(verticalBox);
        window.setWidth(540);
        window.setTitle("Add a New Task");
        window.setScene(scene);
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
}
