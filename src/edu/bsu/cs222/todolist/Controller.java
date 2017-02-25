package edu.bsu.cs222.todolist;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    @FXML
    public TableColumn<Task, String> dateColumn;
    @FXML
    public TableColumn<Task, String> descriptionColumn;
    @FXML
    public TableColumn<Task, String> taskColumn;
    @FXML
    public TableView<Task> taskTable;
    @FXML
    public TextField searchField;
    ObservableList<Task> taskList = FXCollections.observableArrayList();
    ObservableList<Task> filteredTaskList = FXCollections.observableArrayList();

    public void handleAddTaskButton() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                NewTaskPopUp popUp = new NewTaskPopUp();
                String[] taskAttributes = popUp.getNewTask();
                Task newTask = new Task(taskAttributes[0], taskAttributes[1], taskAttributes[2]);
                taskList.add(newTask);
                setListToTable(taskList);
            }
        });
    }

    private void setListToTable(ObservableList<Task> listToAdd) {
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        taskTable.setItems(listToAdd);
    }

    public void handleShowCalendarButton() {

    }

    public void handleSearchTasksButton() {
        Searcher newSearcher = new Searcher(taskList);
        ObservableList<Task> filteredList = newSearcher.filterList(searchField.getText());
        setListToTable(filteredList);
    }

    public void handleDeleteSelectedButton() {

    }
}
