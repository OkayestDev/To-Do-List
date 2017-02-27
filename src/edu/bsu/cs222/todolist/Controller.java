package edu.bsu.cs222.todolist;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.YearMonth;

public class Controller {
    @FXML
    private TableColumn<Task, String> dateColumn;
    @FXML
    private TableColumn<Task, String> descriptionColumn;
    @FXML
    private TableColumn<Task, String> taskColumn;
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    private boolean alreadyFiltered = false;
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    public void handleAddTaskButton() {
        Platform.runLater(() -> {
            NewTaskPopUp popUp = new NewTaskPopUp();
            String[] taskAttributes = popUp.getNewTask();
            Task newTask = new Task(taskAttributes[0], taskAttributes[1], taskAttributes[2]);
            taskList.add(newTask);
            setListToTable(taskList);
        });
    }

    private void setListToTable(ObservableList<Task> listToAdd) {
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        taskTable.setItems(listToAdd);
    }

    public void handleShowCalendarButton() {
        Platform.runLater(() -> {
            CalendarView calendarView = new CalendarView(YearMonth.now());
            calendarView.calendarShowAndWait();
        });
    }

    public void handleSearchTasksButton() {
        if (!alreadyFiltered && !searchField.getText().equals("") && !taskList.isEmpty()) {
            Searcher newSearcher = new Searcher(taskList);
            ObservableList<Task> filteredList = newSearcher.search(searchField.getText());
            setListToTable(filteredList);
            searchButton.setText("Unfilter List");
            alreadyFiltered = !alreadyFiltered;
        }
        else if (!searchField.getText().equals("")){
            alreadyFiltered = !alreadyFiltered;
            setListToTable(taskList);
            searchButton.setText("Search Tasks");
        }
    }

    public void handleDeleteSelectedButton() {

    }
}
