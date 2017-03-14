package edu.bsu.cs222.controller;

import edu.bsu.cs222.todolist.Searcher;
import edu.bsu.cs222.todolist.Task;
import edu.bsu.cs222.builder.TaskAdder;
import edu.bsu.cs222.builder.CalendarViewLauncher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable{
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        taskTable.setItems(taskList);
    }

    public void handleAddTaskButton() {
        TaskAdder taskAdder = new TaskAdder(taskList);
        taskAdder.addTask();
    }

    public void handleShowCalendarButton() throws IOException {
        CalendarViewLauncher calendarViewLauncher = new CalendarViewLauncher(taskList);
        calendarViewLauncher.launch();
    }

    public void handleSearchTasksButton() {
        if (isResultListView()&& isKeyWordAndSearchTextFieldEmpty()) {
            switchToResultListView(getSearchResult());
        } else if (isSearchTextFieldEmpty()) {
            switchToTableView();
        }
    }

    private boolean isResultListView(){
        return !alreadyFiltered;
    }

    private boolean isKeyWordAndSearchTextFieldEmpty() {
        return !searchField.getText().equals("") && taskList.size() > 0;
    }

    private boolean isSearchTextFieldEmpty(){
        return !searchField.getText().equals("");
    }

    private ObservableList<Task> getSearchResult() {
        Searcher newSearcher = new Searcher(taskList);
        return newSearcher.filterList(searchField.getText());
    }

    private void switchToResultListView(ObservableList<Task> filteredList) {
        taskTable.setItems(filteredList);
        searchButton.setText("Remove Filter");
        alreadyFiltered = !alreadyFiltered;
    }

    private void switchToTableView() {
        alreadyFiltered = !alreadyFiltered;
        taskTable.setItems(taskList);
        searchButton.setText("Search Tasks");
    }

    public void handleDeleteSelectedButton() {
        //add tick column to todolist, after delete selected has been pressed remove all tasks that have been ticked
    }


}
