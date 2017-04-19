package edu.bsu.cs222.todolist.serialization;

import edu.bsu.cs222.todolist.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompletedTaskListGenerator {
    private ObservableList<Task> taskList;

    public CompletedTaskListGenerator(ObservableList<Task> taskList) {
        this.taskList = taskList;
    }

    public ObservableList<Task> generate() {
        ObservableList<Task> completedTaskList = FXCollections.observableArrayList();
        for (Task task : taskList) {
            if (task.isSelected()) {
                completedTaskList.add(task);
            }
        }
        return completedTaskList;
    }
}
