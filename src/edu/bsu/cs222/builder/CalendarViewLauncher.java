package edu.bsu.cs222.builder;

import edu.bsu.cs222.controller.CalendarViewController;
import edu.bsu.cs222.todolist.Task;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;

public class CalendarViewLauncher {
    private StageBuilder calendarStage;
    private ObservableList<Task> taskList;
    private CalendarViewController calendar;

    public CalendarViewLauncher(ObservableList<Task> taskList) throws IOException {
        calendarStage = new StageBuilder("../fxml/CalendarView.fxml");
        calendar = (CalendarViewController) calendarStage.getController();
        this.taskList = taskList;
        calendar.setTaskList(this.taskList);
    }

    public void launch() {
        Platform.runLater(() ->
        {
            try {
                setTitleAndModality();
                buidCalendarAndLoadStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setTitleAndModality(){
        calendarStage.setTitleAndModality("Calendar View");
    }

    private void buidCalendarAndLoadStage(){
        calendar.buildCalendar();
        calendarStage.loadStage();
    }
}
