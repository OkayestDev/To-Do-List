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
    private VBox vBox;
    private ObservableList<Task> taskList;
    private DatePicker datePicker;
    private Searcher searcher;
    private StackPane stackPane;
    private DatePickerSkin datePickerSkin;

    public CalendarViewController(){
        datePicker = new DatePicker(LocalDate.now());
    }

    public void buildCalendar() {
        clearVbox();
        createSearcher();
        setUpDateCellFactory();
        setUpDatePickerSkin();
        addCalendarToVbox();
    }

    private void clearVbox() {
        vBox.getChildren().clear();
    }

    private void createSearcher() {
        searcher = new Searcher(taskList);
    }

    private void setUpDateCellFactory() {
        datePicker.setDayCellFactory(param -> setUpDateCell());
    }

    private void setUpDatePickerSkin() {
        datePickerSkin = new DatePickerSkin(datePicker);
    }

    private void addCalendarToVbox() {
        Node calendar = datePickerSkin.getPopupContent();
        vBox.getChildren().add(calendar);
    }

    private DateCell setUpDateCell() {
        return new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                ObservableList<Task> filteredTaskList = getFilteredTaskList(item);
                setUpStackPane(this);
                setUpCell(this, filteredTaskList);
            }
        };
    }

    private  ObservableList<Task> getFilteredTaskList(LocalDate item) {
        return searcher.filterList(setDate(item));
    }

    private String setDate(LocalDate item){
       return item.getMonthValue()
               + "/"
               + item.getDayOfMonth()
               + "/"
               + item.getYear();
    }

    private void setUpStackPane(DateCell dateCell) {
        DateCellPaneBuilder dateCellPaneBuilder = new DateCellPaneBuilder(dateCell);
        stackPane = dateCellPaneBuilder.build();
    }

    private void setUpCell(DateCell dateCell, ObservableList<Task> filteredTaskList) {
        if (isFilteredTaskListEmpty(filteredTaskList)) {
            dateCell.setStyle("-fx-background-color:red");
            dateCell.setOnMouseEntered(action -> dateCell.setTooltip(buildTooltip(filteredTaskList)));
        }
        setGraphicAndText(dateCell);
    }

    private boolean isFilteredTaskListEmpty(ObservableList<Task> currentTask) {
        return currentTask.size() > 0;
    }

    private Tooltip buildTooltip(ObservableList<Task> currentTask) {
        edu.bsu.cs222.builder.TooltipBuilder tooltipBuilder = new edu.bsu.cs222.builder.TooltipBuilder(currentTask);
        return tooltipBuilder.build();
    }

    private void setGraphicAndText(DateCell dateCell) {
        dateCell.setGraphic(stackPane);
        dateCell.setText("");
    }

    public void setTaskList(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }
}
