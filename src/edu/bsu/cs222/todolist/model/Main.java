package edu.bsu.cs222.todolist.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/ToDoList.fxml"));
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(new Scene(root, 850, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
