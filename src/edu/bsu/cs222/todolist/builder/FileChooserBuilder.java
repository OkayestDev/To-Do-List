package edu.bsu.cs222.todolist.builder;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChooserBuilder {
    private FileChooser fileChooser;
    private File selectedFile;

    public FileChooserBuilder() {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public void showOpenDialog() {
        fileChooser.setTitle("Choose File to Load");
        selectedFile = fileChooser.showOpenDialog(new Stage());
    }

    public void showSaveDialog() {
        fileChooser.setTitle("Choose File to Save");
        selectedFile = fileChooser.showSaveDialog(new Stage());
    }

    public String getFilePath() {
        if (selectedFile == null) {
            return null;
        }
        else {
            return selectedFile.getAbsolutePath();
        }
    }
}
