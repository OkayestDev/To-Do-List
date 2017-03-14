package edu.bsu.cs222.builder;

import javafx.geometry.Pos;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class DateCellPaneBuilder {
    private StackPane currentCell;
    private Label day;
    private String dayOfTheMonth;

    public DateCellPaneBuilder(DateCell dateCell) {
        currentCell = new StackPane();
        dayOfTheMonth = dateCell.getText();
    }

    public StackPane build() {
        setUpCurrentCell();
        setUpLabel();
        setUpLabelToCurrentCell();
        return currentCell;
    }

    private void setUpCurrentCell() {
        currentCell.setMinSize(60, 60);
        currentCell.setAlignment(Pos.CENTER);
    }

    private void setUpLabel() {
        day = new Label(dayOfTheMonth);
        day.setFont(new Font("Times New Roman", 20));
    }

    private void setUpLabelToCurrentCell() {
        currentCell.getChildren().add(day);
    }
}
