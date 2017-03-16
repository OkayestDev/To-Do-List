package edu.bsu.cs222.builder;

import edu.bsu.cs222.controller.CalendarViewController;
import edu.bsu.cs222.todolist.Task;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;

public class CalendarViewBuilder {
    private StageBuilder stageBuilder;
    private CalendarViewController calendarViewController;

    public CalendarViewBuilder(ObservableList<Task> taskList) throws IOException {
        stageBuilder = new StageBuilder("../fxml/CalendarView.fxml");
        calendarViewController = (CalendarViewController) stageBuilder.getController();
        calendarViewController.setTaskList(taskList);
    }

    public void launch() {
        Platform.runLater(() ->
        {
            try {
                setTitleAndModality();
                buildCalendarViewAndLauchStage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setTitleAndModality(){
        stageBuilder.setTitleAndModality("Calendar View");
    }

    private void buildCalendarViewAndLauchStage(){
        calendarViewController.buildCalendar();
        stageBuilder.launchStage();
    }
}
