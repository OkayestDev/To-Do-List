package edu.bsu.cs222.todolist.serialization;

import edu.bsu.cs222.todolist.model.Task;
import javafx.collections.ObservableList;

public class Deleter {
    private Task taskReference;
    private ObservableList<Task> taskList;
    private int index;

    public Deleter(ObservableList<Task> taskList) {
        this.taskList = taskList;
        index = taskList.size() - 1;
    }

    public void deleteSelectedTasks() {
        for (; index > -1; index--) {
            setTaskReference(taskList);
            deleteTask();
        }
    }

    private void setTaskReference(ObservableList<Task> taskList) {
        this.taskReference = taskList.get(index);
    }

    private void deleteTask() {
        if (taskReference.isSelected()) {
            taskList.remove(taskReference);
        }
    }
}
