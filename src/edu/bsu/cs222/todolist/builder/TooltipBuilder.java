package edu.bsu.cs222.todolist.builder;

import edu.bsu.cs222.todolist.model.Task;
import javafx.collections.ObservableList;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

public class TooltipBuilder {
    private int count;
    private ObservableList<Task> taskList;
    private Tooltip taskListToolTip;
    private String taskInformation;

    public TooltipBuilder(ObservableList<Task> TaskList) {
        count = 1;
        this.taskList = TaskList;
        taskListToolTip = new Tooltip();
        taskInformation = "";
    }

    public Tooltip build() {
        setFontAndTaskInformation();
        return taskListToolTip;
    }

    private void setFontAndTaskInformation() {
        setFont();
        setTaskInformation();
    }

    private void setFont() {
        taskListToolTip.setFont(new Font("Times New Roman", 16));
    }

    private void setTaskInformation() {
        setTaskSequenceNumber();
        setTaskInformationToTaskListTooltip();
    }

    private void setTaskSequenceNumber() {
        for (Task task : taskList) {
            taskInformation += "Task "
                    + count + ": "
                    + task.getTaskName() + ", "
                    + task.getDescription() + "\n";
            count++;
        }
    }

    private void setTaskInformationToTaskListTooltip() {
       taskListToolTip.setText(taskInformation);
    }
}
