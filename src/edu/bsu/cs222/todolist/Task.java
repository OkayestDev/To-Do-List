package edu.bsu.cs222.todolist;

import java.time.Instant;

public class Task {
    private String task;
    private String description;
    private Instant date; //may need to use date or something similar

    public Task(String task, String description, Instant date) {
        this.task = task;
        this.description = description;
        this.date = date;
    }
}
