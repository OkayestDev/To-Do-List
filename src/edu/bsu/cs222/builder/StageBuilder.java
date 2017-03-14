package edu.bsu.cs222.builder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StageBuilder {
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private Parent root;

    public StageBuilder(String fileName) throws IOException {
        stage = new Stage();
        fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        root = fxmlLoader.load();
        stage.setScene(new Scene(root));
    }

    public void setTitleAndModality(String title) {
        stage.setTitle("title");
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    public void loadStage(){
        stage.showAndWait();
    }

    public Object getController(){
        return fxmlLoader.getController();
    }


}
