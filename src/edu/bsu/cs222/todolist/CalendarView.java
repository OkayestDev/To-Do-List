package edu.bsu.cs222.todolist;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.YearMonth;

public class CalendarView {
    private Stage window = new Stage();
    private DateCell dateCell = new DateCell();
    private HBox horizontalBox = new HBox(15);
    private VBox verticalBox = new VBox();
    private Label currentMonth = new Label();
    private Label currentYear = new Label();
    private YearMonth yearMonthToShow;
    private Image rightArrow = new Image("assets/nextMonth.png");
    private Image leftArrow = new Image("assets/prevMonth.png");
    private Button nextMonthButton = new Button("", new ImageView(rightArrow));
    private Button previousMonthButton = new Button("", new ImageView(leftArrow));
    private Button nextYearButton = new Button("", new ImageView(rightArrow));
    private Button previousYearButton = new Button("", new ImageView(leftArrow));
    private GridPane calendar = new GridPane();

    public CalendarView(YearMonth month) {
        yearMonthToShow = month;
        configureWindow();
    }

    public void calendarShowAndWait() {
        nextMonthButton.setOnAction(action -> {
           nextMonth();
           rebuildCalendar();
        });
        previousMonthButton.setOnAction(action -> {
           previousMonth();
           rebuildCalendar();
        });
        nextYearButton.setOnAction(action -> {
            nextYear();
            rebuildCalendar();
        });
        previousYearButton.setOnAction(action -> {
            previousYear();
            rebuildCalendar();
        });
        window.showAndWait();
    }

    private void rebuildCalendar() {

    }

    private void configureWindow() {
        horizontalBox.getChildren().addAll(previousMonthButton, currentMonth, nextMonthButton,
                                            previousYearButton, currentYear, nextYearButton);
        verticalBox.getChildren().addAll(horizontalBox, calendar); //also add calendar here
        window.setTitle("Calendar Task View");
        window.setWidth(800);
        window.setHeight(800);
        currentMonth.setText(yearMonthToShow.getMonth().name());
        currentYear.setText(Integer.toString((yearMonthToShow.getYear())));
        Scene scene = new Scene(verticalBox); //add here
        window.setScene(scene);
    }

    private void nextMonth() {
        yearMonthToShow = yearMonthToShow.plusMonths(1);
        currentMonth.setText(yearMonthToShow.getMonth().name());
    }

    private void previousMonth() {
        yearMonthToShow = yearMonthToShow.minusMonths(1);
        currentMonth.setText(yearMonthToShow.getMonth().name());
    }

    private void nextYear() {
        yearMonthToShow = yearMonthToShow.plusYears(1);
        currentYear.setText(Integer.toString((yearMonthToShow.getYear())));
    }

    private void previousYear() {
        yearMonthToShow = yearMonthToShow.minusYears(1);
        currentYear.setText(Integer.toString((yearMonthToShow.getYear())));
    }
}
