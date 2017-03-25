package edu.bsu.cs222.todolist.builder;

import javafx.geometry.Pos;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class DateCellPaneBuilder {
    private StackPane StackPane;
    private Label dayOfStackPane;

    public DateCellPaneBuilder(DateCell dateCell) {
        StackPane = new StackPane();
        setUpLabel(dateCell.getText());
    }

    public StackPane build() {
        setUpCurrentCell();
        addLabelToCurrentStackPane();
        return StackPane;
    }

    private void setUpCurrentCell() {
        StackPane.setMinSize(60, 60);
        StackPane.setAlignment(Pos.CENTER);
    }

    private void setUpLabel(String dayOfTheMonth) {
        dayOfStackPane = new Label(dayOfTheMonth);
        dayOfStackPane.setFont(new Font("Times New Roman", 20));
    }

    private void addLabelToCurrentStackPane() {
        StackPane.getChildren().add(dayOfStackPane);
    }
}
