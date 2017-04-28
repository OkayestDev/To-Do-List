package edu.bsu.cs222.todolist.uibuilder;

import edu.bsu.cs222.todolist.controller.CalendarViewController;
import edu.bsu.cs222.todolist.model.Task;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;

public class CalendarViewBuilder {
    private StageBuilder stageBuilder;
    private CalendarViewController calendarViewController;

    public CalendarViewBuilder(ObservableList<Task> taskList) throws IOException {
        stageBuilder = new StageBuilder(getClass().getResource("../fxml/CalendarView.fxml"));
        calendarViewController = (CalendarViewController) stageBuilder.getController();
        calendarViewController.setTaskList(taskList);
    }

    public void launch() {
        Platform.runLater(() ->
        {
            try {
                setTitleAndModality();
                buildCalendarViewAndLaunchStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setTitleAndModality(){
        stageBuilder.setTitleAndModality("Calendar View");
    }

    private void buildCalendarViewAndLaunchStage(){
        calendarViewController.buildCalendar();
        stageBuilder.launchStage();
    }
}
