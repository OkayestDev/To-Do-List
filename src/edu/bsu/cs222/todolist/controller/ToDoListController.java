package edu.bsu.cs222.todolist.controller;

import edu.bsu.cs222.todolist.serialization.*;
import edu.bsu.cs222.todolist.model.Task;
import edu.bsu.cs222.todolist.builder.NewTaskPopUpBuilder;
import edu.bsu.cs222.todolist.builder.CalendarViewBuilder;
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
    private TableColumn<Task, CheckBox> selectColumn;
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private MenuButton viewMenu;
    private boolean filteredStatus;
    private boolean incompleteTaskViewStatus;
    private ObservableList<Task> taskList = FXCollections.observableArrayList();
    private ObservableList<Task> completedTaskList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filteredStatus = false;
        incompleteTaskViewStatus = false;
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        selectColumn.setCellValueFactory(new edu.bsu.cs222.todolist.builder.CheckBoxBuilder());
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
        if (isFilteredListView() && isSearchFieldEmpty() && !isTaskListEmpty()) {
            switchToFilteredListView(getFilteredList());
        } else if (isSearchFieldEmpty()) {
            resetToDoListView();
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
        Searcher searcher;
        searcher = constructSearcher();
        return searcher.filterList(searchField.getText());
    }

    private Searcher constructSearcher() {
        if (incompleteTaskViewStatus) {
            return new Searcher(taskList);
        } else {
            return new Searcher(completedTaskList);
        }
    }

    private void resetToDoListView() {
        filteredStatus = false;
        resetTaskView();
        resetSearchFieldAndButton();
    }

    private void resetSearchFieldAndButton() {
        searchButton.setText("Search Tasks");
        searchField.setText("");
    }

    private void resetTaskView() {
        if (incompleteTaskViewStatus) {
            taskTable.setItems(taskList);
        } else {
            taskTable.setItems(completedTaskList);
        }
    }

    public void handleDeleteSelectedButton() {
        Deleter deleter;
        deleter = constructDeleter();
        deleter.deleteSelectedTasks();
    }

    private Deleter constructDeleter() {
        if (incompleteTaskViewStatus) {
            return new Deleter(taskList);
        } else {
            return new Deleter(completedTaskList);
        }
    }

    public void handleSaveListButton() {
        Platform.runLater(() -> {
            try {
                SetUpSaver(taskList);
                setUpAlert("Task list successfully saved", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                setUpAlert("Unable to save task list", Alert.AlertType.ERROR);
            }
        });
    }

    private void SetUpSaver(ObservableList<Task> taskList) throws JDOMException, IOException {
        TaskListSaver saver = new TaskListSaver(taskList, completedTaskList);
        saver.saveTo("./xmlfiles/SavedTaskList.xml");
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

    public void handleCompleteSelected() {
        setUpCompletedTaskList();
        removeCompletedTasksFromTaskList();
    }

    private void setUpCompletedTaskList() {
        CompletedTaskListGenerator completedTaskListGenerator = new CompletedTaskListGenerator(taskList);
        ObservableList<Task> completedTaskList = completedTaskListGenerator.generate();
        this.completedTaskList.addAll(completedTaskList);
    }

    private void removeCompletedTasksFromTaskList() {
        taskList.removeAll(completedTaskList);
    }

    public void handleShowCompletedTaskList() {
        resetToDoListView();
        setCompletedTaskListView();
    }

    private void setCompletedTaskListView() {
        incompleteTaskViewStatus = false;
        viewMenu.setText("Completed Tasks");
        taskTable.setItems(completedTaskList);
    }

    public void handleShowTaskList() {
        resetToDoListView();
        setUpIncompleteTasksView();
    }

    private void setUpIncompleteTasksView() {
        incompleteTaskViewStatus = true;
        viewMenu.setText("Incomplete Tasks");
        taskTable.setItems(taskList);
    }

    private void SetUpLoader() throws JDOMException, IOException {
        TaskListLoader loader = new TaskListLoader("./xmlfiles/SavedTaskList.xml");
        taskList = loader.loadTaskList();
        completedTaskList = loader.loadCompletedTaskList();
        taskTable.setItems(taskList);
    }

    private void setUpAlert(String headerText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void setTaskList(ObservableList<Task> taskList) {
        this.taskList = taskList;
        taskTable.setItems(taskList);
    }

    public void setCompletedTaskList(ObservableList<Task> completedTaskList) {
        this.completedTaskList = completedTaskList;
    }
}
