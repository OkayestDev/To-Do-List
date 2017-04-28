package edu.bsu.cs222.todolist.uibuilder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


@SuppressWarnings("WeakerAccess I don't need to make the class private")
public class StageBuilder {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    StageBuilder(URL url) throws IOException {
        stage = new Stage();
        fxmlLoader = new FXMLLoader(url);
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
