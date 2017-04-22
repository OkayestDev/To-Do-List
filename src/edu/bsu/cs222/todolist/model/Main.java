package edu.bsu.cs222.todolist.model;

import edu.bsu.cs222.todolist.controller.ToDoListController;
import edu.bsu.cs222.todolist.serialization.TaskListLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private ToDoListController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ToDoList.fxml"));
        Parent root = fxmlLoader.load();
        controller  = fxmlLoader.getController();
        autoLoad();
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(new Scene(root, 850, 800));
        primaryStage.show();
    }

    private void autoLoad() {
        try {
            TaskListLoader loader = new TaskListLoader("./xmlfiles/SavedTaskList.xml");
            controller.setTaskList(loader.loadTaskList());
            controller.setCompletedTaskList(loader.loadCompletedTaskList());
        }
        catch(Exception e) {}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
