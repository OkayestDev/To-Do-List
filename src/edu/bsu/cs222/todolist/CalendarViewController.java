package edu.bsu.cs222.todolist;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class CalendarViewController {
    @FXML
    private VBox verticalBox;
    private ObservableList<Task> taskList;
    private DatePicker datePicker = new DatePicker(LocalDate.now());
    private Searcher tasksWithDate;
    private StackPane currentCell;

    public void buildCalendar() {
        verticalBox.getChildren().clear();
        tasksWithDate = new Searcher(taskList);
        datePicker.setDayCellFactory(param -> formatCurrentDateCell());
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node calendar = datePickerSkin.getPopupContent();
        verticalBox.getChildren().add(calendar);
    }

    private DateCell formatCurrentDateCell() {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                String dateToLookFor = item.getMonthValue() + "/" + item.getDayOfMonth() + "/" + item.getYear();
                configureStackPane(this.getText());
                ObservableList<Task> currentTasks = tasksWithDate.filterList(dateToLookFor);
                if (currentTasks.size() > 0) {
                    this.setStyle("-fx-background-color:red");
                    this.setOnMouseEntered(action -> {
                        this.setTooltip(buildTooltip(currentTasks));
                    });
                }
                this.setGraphic(currentCell);
                this.setText("");
            }
        };
    }

    private Tooltip buildTooltip(ObservableList<Task> currentTasks) {
        Tooltip taskInfo = new Tooltip();
        taskInfo.setFont(new Font("Times New Roman", 16));
        String contentText = "Task(s):\n";
        int count = 1;
        for (Task task : currentTasks) {
            contentText += "Task " + count + ": " + task.toString() + "\n";
            count++;
        }
        taskInfo.setText(contentText);
        return taskInfo;
    }

    private void configureStackPane(String dayOfTheMonth) {
        currentCell = new StackPane();
        currentCell.setMinSize(60, 60);
        currentCell.setAlignment(Pos.CENTER);
        Label day = new Label(dayOfTheMonth);
        day.setFont(new Font("Times New Roman", 20));
        currentCell.getChildren().add(day);
    }

    public void setTaskList(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }
}
