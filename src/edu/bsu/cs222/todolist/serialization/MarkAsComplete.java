package edu.bsu.cs222.todolist.serialization;

import edu.bsu.cs222.todolist.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MarkAsComplete {
    private ObservableList<Task> taskList;

    public MarkAsComplete(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }

    public ObservableList<Task> makeCompletedTasksList() {
        ObservableList<Task> completedTaskList = FXCollections.observableArrayList();
        for (Task task : taskList) {
            if (task.isSelected()) {
                completedTaskList.add(task);
            }
        }
        return completedTaskList;
    }
}
