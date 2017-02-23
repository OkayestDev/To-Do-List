package edu.bsu.cs222.todolist;

import java.util.Date;

public class Task {
    private String task;
    private String description;
    private String date; //may need to use date or something similar

    public Task(String task, String description, String date) {
        this.task = task;
        this.description = description;
        this.date = date;
    }

    //Test purposes
    @Override
    public String toString() {
        return task + " " + description + " " + date.toString();
    }
}
