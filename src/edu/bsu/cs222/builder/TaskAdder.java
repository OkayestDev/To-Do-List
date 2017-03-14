package edu.bsu.cs222.builder;

import edu.bsu.cs222.builder.StageBuilder;
import edu.bsu.cs222.controller.NewTaskPopUpController;
import edu.bsu.cs222.todolist.Task;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;

public class TaskAdder {
    private StageBuilder newNewTaskPopUpWindow;
    private ObservableList<Task> taskList;

    public TaskAdder(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask() {
        Platform.runLater(() -> {
            setUpNewTaskPopUpWindow();
            try {
                loadWindow(newNewTaskPopUpWindow);
            } catch (IOException e) {
                e.printStackTrace();
            }
            taskList.add(getNewTask());
        });
    }

    private void setUpNewTaskPopUpWindow() {
        try {
            newNewTaskPopUpWindow = new StageBuilder("../fxml/NewTaskPopUp.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        newNewTaskPopUpWindow.setTitleAndModality("Add New Task");
    }

    private void loadWindow(StageBuilder NewWindowBuilder) throws IOException {
        newNewTaskPopUpWindow.loadStage();
    }

    private Task getNewTask() {
        NewTaskPopUpController newTaskPopUpController = (NewTaskPopUpController) newNewTaskPopUpWindow.getController();
        return newTaskPopUpController.getNewTask();
    }
}
