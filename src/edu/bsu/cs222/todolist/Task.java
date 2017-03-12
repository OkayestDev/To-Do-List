package edu.bsu.cs222.todolist;

import java.util.Date;

public class Task {
    private String taskName;
    private String description;

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    private String date; //may need to use date or something similar

    public Task(String taskName, String description, String date) {
        this.taskName = taskName;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return taskName + " " + description;
    }
}
