package edu.bsu.cs222.todolist;

import edu.bsu.cs222.todolist.AddTaskPopUp;
import javafx.application.Platform;

public class Controller {
    public void handleAddTaskButton() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                AddTaskPopUp popUp = new AddTaskPopUp();
                String[] newTask = popUp.getNewTask();
                //Add this newTask to list/tableView
            }
        });

    }

    public void handleShowCalendarButton() {

    }

    public void handleSearchTasksButton() {

    }

    public void handleDeleteSelectedButton() {

    }
}
