package edu.bsu.cs222.builder;

import edu.bsu.cs222.todolist.Task;
import javafx.collections.ObservableList;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

public class TooltipBuilder {
    private int count;
    private ObservableList<Task> currentTasks;
    private Tooltip taskInfo;
    private String contentText;

    public TooltipBuilder(ObservableList<Task> currentTasks) {
        count = 1;
        this.currentTasks = currentTasks;
        taskInfo = new Tooltip();
    }

    public Tooltip build() {
        setFontAndContentText();
        return taskInfo;
    }

    private void setFontAndContentText() {
        setFont();
        setContentText();
    }

    private void setFont() {
        taskInfo.setFont(new Font("Times New Roman", 16));
    }

    private void setContentText() {
        setContentHeadText();
        setTaskSequenceNumber();
        setContentTextToTaskInfo();
    }

    private void setContentHeadText() {
        contentText = "Task(s):\n";
    }

    private void setTaskSequenceNumber() {
        for (Task task : currentTasks) {
            contentText += "Task " + count + ": " + task.getTaskName() + "," + task.getDescription() + "\n";
            count++;
        }
    }

    private void setContentTextToTaskInfo() {
       taskInfo.setText(contentText);
    }
}
