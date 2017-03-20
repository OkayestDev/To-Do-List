package edu.bsu.cs222.builder;

import edu.bsu.cs222.controller.NewTaskPopUpController;
import edu.bsu.cs222.todolist.Task;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;

public class NewTaskPopUpBuilder {
    private StageBuilder stageBuilder;
    private ObservableList<Task> taskList;
    private Task newTask;

    public NewTaskPopUpBuilder(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask() {
        Platform.runLater(() -> {
            setUpNewTaskPopUpStage();
            try {
                launchStage(stageBuilder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setNewTask();
            if (newTask != null) {
                taskList.add(newTask);
            }
        });
    }

    private void setUpNewTaskPopUpStage() {
        try {
            stageBuilder = new StageBuilder("../fxml/NewTaskPopUp.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        stageBuilder.setTitleAndModality("Add New Task");
    }

    private void launchStage(StageBuilder stageBuilder) throws IOException {
        stageBuilder.launchStage();
    }

    private void setNewTask() {
        NewTaskPopUpController newTaskPopUpController = (NewTaskPopUpController) stageBuilder.getController();
        newTask = newTaskPopUpController.getNewTask();
    }
}
