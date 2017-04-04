package edu.bsu.cs222.todolist.controller;

import edu.bsu.cs222.todolist.model.Searcher;
import edu.bsu.cs222.todolist.model.Task;
import edu.bsu.cs222.todolist.builder.NewTaskPopUpBuilder;
import edu.bsu.cs222.todolist.builder.CalendarViewBuilder;
import edu.bsu.cs222.todolist.model.TaskListLoader;
import edu.bsu.cs222.todolist.model.TaskListSaver;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jdom2.JDOMException;

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
    private TableColumn<Task, CheckBox> deleteColumn;
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
        deleteColumn.setCellValueFactory(new edu.bsu.cs222.todolist.builder.CheckBoxBuilder());
        taskTable.setItems(taskList);
    }

    public void handleAddTaskButton() {
        NewTaskPopUpBuilder newTaskPopUpBuilder = new NewTaskPopUpBuilder(taskList);
        newTaskPopUpBuilder.addTask();
    }

    public void handleShowCalendarButton() throws IOException {
        if (taskList.size() > 0) {
            CalendarViewBuilder calendarViewBuilder = new CalendarViewBuilder(taskList);
            calendarViewBuilder.launch();
        }
    }

    public void handleSearchTasksButton() {
        if (isFilteredListView() && isSearchFieldEmpty() && !isTaskListEmpty()) {
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
        return taskList.size() == 0;
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
        searchField.setText("");
    }

    public void handleDeleteSelectedButton() {
        Deleter deleter = new Deleter(taskList);
        deleter.deleteSelectedTask();
    }

    public void handleSaveListButton() {
        Platform.runLater(() -> {
            try {
                if (!isTaskListEmpty()) {
                    SetUpSaver(taskList);
                    setUpAlert("Task list successfully saved", Alert.AlertType.INFORMATION);
                } else {
                    setUpAlert("Cannot save an empty task list", Alert.AlertType.ERROR);
                }
            } catch (Exception e) {
                setUpAlert("Unable to save task list", Alert.AlertType.ERROR);
            }
        });
    }

    private void SetUpSaver(ObservableList<Task> taskList) throws JDOMException, IOException {
        TaskListSaver saver = new TaskListSaver(taskList);
        saver.save();
    }

    public void handleLoadListButton() {
        Platform.runLater(() -> {
            try {
                SetUpLoader();
                setUpAlert("Task list successfully loaded", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                setUpAlert("Couldn't load task list", Alert.AlertType.ERROR);
            }
        });
    }

    private void setUpAlert(String headerText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private void SetUpLoader() throws JDOMException, IOException {
        TaskListLoader loader = new TaskListLoader("./taskList/SavedTaskList.xml");
        taskList = loader.load();
        taskTable.setItems(taskList);
    }
}
