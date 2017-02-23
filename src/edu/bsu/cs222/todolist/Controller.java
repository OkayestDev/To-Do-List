package edu.bsu.cs222.todolist;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    public TableColumn<Task, String> dateColumn;
    @FXML
    public TableColumn<Task, String> descriptionColumn;
    @FXML
    public TableColumn<Task, String> taskColumn;
    @FXML
    public TableView<Task> taskTable;
    List<Task> taskList = new ArrayList<>();

    public void handleAddTaskButton() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                AddTaskPopUp popUp = new AddTaskPopUp();
                String[] taskAttributes = popUp.getNewTask();
                Task newTask = new Task(taskAttributes[0], taskAttributes[1], taskAttributes[2]);
                taskList.add(newTask);
                addTaskToTableView(newTask);
            }
        });
    }

    private void addTaskToTableView(Task task) {
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        taskTable.getItems().add(task);
    }

    public void handleShowCalendarButton() {

    }

    public void handleSearchTasksButton() {

    }

    public void handleDeleteSelectedButton() {

    }
}
