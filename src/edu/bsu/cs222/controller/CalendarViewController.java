package edu.bsu.cs222.controller;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import edu.bsu.cs222.todolist.Searcher;
import edu.bsu.cs222.todolist.Task;
import edu.bsu.cs222.builder.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDate;

public class CalendarViewController {
    @FXML
    private VBox verticalBox;
    private ObservableList<Task> taskList;
    private DatePicker datePicker;
    private Searcher tasksWithDate;
    private StackPane currentCell;
    private Node calendar;
    private DatePickerSkin datePickerSkin;

    public CalendarViewController(){
        datePicker = new DatePicker(LocalDate.now());
    }

    public void buildCalendar() {
        clearVbox();
        createResearcher();
        setDateCellFactory();
        setDatePickerSkin();
        setCalendarToVBox();
    }

    private void clearVbox() {
        verticalBox.getChildren().clear();
    }

    private void createResearcher() {
        tasksWithDate = new Searcher(taskList);
    }

    private void setDateCellFactory() {
        datePicker.setDayCellFactory(param -> formatCurrentDateCell());
    }

    private void setDatePickerSkin() {
        datePickerSkin = new DatePickerSkin(datePicker);
    }

    private void setCalendarToVBox() {
        calendar = datePickerSkin.getPopupContent();
        verticalBox.getChildren().add(calendar);
    }

    private DateCell formatCurrentDateCell() {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                ObservableList<Task> currentTask = getCurrentDate(item);
                configureStackPane(this);
                setUpCellWithTask(this,currentTask);
                setUpCellWithoutTask(this);
            }
        };
    }

    private  ObservableList<Task> getCurrentDate (LocalDate item) {
        return tasksWithDate.filterList(setCurrentDate(item));
    }

    private String setCurrentDate(LocalDate item){
       return item.getMonthValue() + "/" + item.getDayOfMonth() + "/" + item.getYear();
    }


    private void configureStackPane(DateCell dateCell) {
        DateCellPaneBuilder dateCellPaneBuilder = new DateCellPaneBuilder(dateCell);
        currentCell = dateCellPaneBuilder.build();
    }

    private void setUpCellWithTask(DateCell dateCell, ObservableList<Task> currentTask) {
        if (isCurrentTaskEmpty(currentTask)) {
            dateCell.setStyle("-fx-background-color:red");
            dateCell.setOnMouseEntered(action -> {
                dateCell.setTooltip(buildTooltip(currentTask));
            });
        }
    }

    private boolean isCurrentTaskEmpty(ObservableList<Task> currentTask) {
        return currentTask.size() > 0;
    }

    private void setUpCellWithoutTask(DateCell dateCell) {
        dateCell.setGraphic(currentCell);
        dateCell.setText("");
    }

    private Tooltip buildTooltip(ObservableList<Task> currentTask) {
        edu.bsu.cs222.builder.TooltipBuilder tooltipBuilder = new edu.bsu.cs222.builder.TooltipBuilder(currentTask);
        return tooltipBuilder.build();
    }

    public void setTaskList(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }

}
