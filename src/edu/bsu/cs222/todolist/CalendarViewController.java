package edu.bsu.cs222.todolist;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.*;
import javafx.util.Callback;

import java.awt.*;
import java.time.LocalDate;;

public class CalendarViewController {
    @FXML
    private VBox verticalBox;
    ObservableList<Task> taskList;
    DatePicker datePicker = new DatePicker(LocalDate.now());
    Searcher tasksWithDate; //use this to get list of tasks for that day after pressing a current day highlighted on the calendar

    public void buildCalendar() {
        verticalBox.getChildren().clear();
        tasksWithDate = new Searcher(taskList);
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        String dateToLookFor = item.getMonthValue() + "/" + item.getDayOfMonth() + "/" + item.getYear();
                        System.out.println(dateToLookFor);
                        if (tasksWithDate.filterList(dateToLookFor).size() > 0) {
                            this.setStyle("-fx-background-color:red");
                        }
                    }
                };
            }
        });
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node calendar = datePickerSkin.getPopupContent();
        verticalBox.getChildren().add(calendar);
    }

    public void setTaskList(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }
}
