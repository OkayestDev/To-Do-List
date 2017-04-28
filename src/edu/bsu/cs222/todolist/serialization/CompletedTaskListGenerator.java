package edu.bsu.cs222.todolist.serialization;

import edu.bsu.cs222.todolist.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompletedTaskListGenerator {
    private ObservableList<Task> taskList;
    private ObservableList<Task> completedTaskList;

    public CompletedTaskListGenerator(ObservableList<Task> taskList) {
        this.taskList = taskList;
        completedTaskList = FXCollections.observableArrayList();
    }

    public ObservableList<Task> generate() {
        for (Task task : taskList) {
            addSelectedTasks(task);
        }
        return completedTaskList;
    }

    private void addSelectedTasks(Task task) {
        if (task.isSelected()) {
            completedTaskList.add(task);
        }
    }
}
