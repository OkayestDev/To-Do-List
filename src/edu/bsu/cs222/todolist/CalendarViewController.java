package edu.bsu.cs222.todolist;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Callback;

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
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        String dateToLookFor = item.getMonthValue() + "/" + item.getDayOfMonth() + "/" + item.getYear();
                        configureStackPane(this.getText());
                        if (tasksWithDate.filterList(dateToLookFor).size() > 0) {
                            this.setStyle("-fx-background-color:red");
                        }
                        this.setGraphic(currentCell);
                        this.setText("");
                    }
                };
            }
        });
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node calendar = datePickerSkin.getPopupContent();
        verticalBox.getChildren().add(calendar);
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
