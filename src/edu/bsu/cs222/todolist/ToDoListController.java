package edu.bsu.cs222.todolist;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.YearMonth;

public class ToDoListController {
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
            try {
                Stage window = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewTaskPopUp.fxml"));
                Parent root = fxmlLoader.load();
                window.setTitle("Add New Task");
                window.initModality(Modality.APPLICATION_MODAL);
                window.setScene(new Scene(root));
                NewTaskPopUpController popUp = fxmlLoader.getController();
                window.showAndWait();
                taskList.add(popUp.getNewTask());
                setListToTable(taskList);
                window.close();
            }
            catch(Exception e) {
                //Handle this error somehow
                e.printStackTrace();
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
        Platform.runLater(() -> {
            try {
                Stage window = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CalendarView.fxml"));
                Parent root = fxmlLoader.load();
                window.setTitle("Calendar View");
                window.initModality(Modality.APPLICATION_MODAL);
                window.setScene(new Scene(root));
                CalendarViewController calendar = fxmlLoader.getController();
                calendar.setTaskList(taskList);
                calendar.buildCalendar();
                window.showAndWait();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void handleSearchTasksButton() {
        if (!alreadyFiltered && !searchField.getText().equals("") && !taskList.isEmpty()) {
            Searcher newSearcher = new Searcher(taskList);
            ObservableList<Task> filteredList = newSearcher.filterList(searchField.getText());
            setListToTable(filteredList);
            searchButton.setText("Remove Filter");
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
