package edu.bsu.cs222.controller;

import edu.bsu.cs222.todolist.Searcher;
import edu.bsu.cs222.todolist.Task;
import edu.bsu.cs222.builder.NewTaskPopUpBuilder;
import edu.bsu.cs222.builder.CalendarViewBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {
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
    private boolean filteredStatus;
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filteredStatus = false;
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        taskTable.setItems(taskList);
    }

    public void handleAddTaskButton() {
        NewTaskPopUpBuilder newTaskPopUpBuilder = new NewTaskPopUpBuilder(taskList);
        newTaskPopUpBuilder.addTask();
    }

    public void handleShowCalendarButton() throws IOException {
        CalendarViewBuilder calendarViewBuilder = new CalendarViewBuilder(taskList);
        calendarViewBuilder.launch();
    }

    public void handleSearchTasksButton() {
        if (isFilteredListView() && isSearchFieldEmpty() && isTaskListEmpty()) {
            switchToFilteredListView(getFilteredList());
        } else if (isSearchFieldEmpty()) {
            switchToTaskListView();
        }
    }

    private boolean isFilteredListView() {
        return !filteredStatus;
    }

    private boolean isSearchFieldEmpty() {
        return !searchField.getText().equals("");
    }

    private boolean isTaskListEmpty() {
        return taskList.size() > 0;
    }

    private void switchToFilteredListView(ObservableList<Task> filteredList) {
        taskTable.setItems(filteredList);
        searchButton.setText("Remove Filter");
        filteredStatus = !filteredStatus;
    }

    private ObservableList<Task> getFilteredList() {
        Searcher newSearcher = new Searcher(taskList);
        return newSearcher.filterList(searchField.getText());
    }

    private void switchToTaskListView() {
        filteredStatus = !filteredStatus;
        taskTable.setItems(taskList);
        searchButton.setText("Search Tasks");
    }

    public void handleDeleteSelectedButton() {
        //add tick column to todolist, after delete selected has been pressed remove all tasks that have been ticked
    }
}
