package edu.bsu.cs222.todolist.builder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

class StageBuilder {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    StageBuilder(String fileName) throws IOException {
        stage = new Stage();
        fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
    }

    void setTitleAndModality(String title) {
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    void launchStage(){
        stage.showAndWait();
    }

    Object getController(){
        return fxmlLoader.getController();
    }
}
