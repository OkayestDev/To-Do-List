package edu.bsu.cs222.todolist;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.util.Random;

import static java.awt.SystemColor.window;

public class CalendarViewController {
    @FXML
    private VBox verticalBox;
    private YearMonth yearMonthToShow;

    public CalendarViewController() {
        yearMonthToShow = YearMonth.now();
        
    }

    private void rebuildCalendar() {
        verticalBox.getChildren().clear();
        DatePickerSkin doesThisWork = new DatePickerSkin(new DatePicker(LocalDate.now()));
        verticalBox.getChildren().add((doesThisWork.getPopupContent()));
    }
}
