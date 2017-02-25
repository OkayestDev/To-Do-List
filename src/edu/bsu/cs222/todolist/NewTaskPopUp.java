package edu.bsu.cs222.todolist;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

public class NewTaskPopUp {
    private Stage window = new Stage();
    private DatePicker date = new DatePicker();
    private TextField taskName = new TextField();
    private TextField description = new TextField();
    private Button add = new Button();
    private HBox layout = new HBox(5);
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    static String[] newTask = new String[3];
    //commit
    public String[] getNewTask() {
        configureWindow();
        add.setOnAction(action -> {
            //Will need error handling, for example: task with no name or invalid date input
            if (allFieldsAreFilledOut()) {
                newTask[0] = taskName.getText();
                newTask[1] = description.getText();
                newTask[2] = dateFormatter.format(date.getValue());
                window.close();
            }
            else {
                System.out.println("Add this to GUI: please fill out all fields");
            }
        });
        window.showAndWait();
        return newTask;
    }

    private void configureWindow() {
        description.setPromptText("Enter Description");
        taskName.setPromptText("Enter Task Name");
        date.setPromptText("ex: 11/11/2011");
        add.setText("Add");
        layout.getChildren().addAll(taskName, description, date, add);
        Scene scene = new Scene(layout);
        window.setWidth(540);
        window.setTitle("Add a New Task");
        window.setScene(scene);
    }

    private boolean allFieldsAreFilledOut() {
        return !(taskName.getText().equals("") && description.getText().equals("") &&
                dateFormatter.format(date.getValue()).equals(""));
    }
}
