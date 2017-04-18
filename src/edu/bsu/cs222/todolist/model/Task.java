package edu.bsu.cs222.todolist.model;

import java.time.LocalDate;

public class Task {
    private String taskName;
    private String description;
    private LocalDate date;
    private boolean selectStatus;
    public static Builder withTaskName(String taskName) {
        return new Builder(taskName);
    }

    public static final class Builder {
        private String taskName;
        private String description;
        private LocalDate date;

        Builder(String taskName) {
            this.taskName = taskName;
        }

        public Builder andDescription(String description) {
            this.description = description;
            return this;
        }

        public Task andDate(LocalDate date){
            this.date = date;
            return new Task(this);
        }
    }

    public Task(Builder builder) {
        this.taskName = builder.taskName;
        this.description = builder.description;
        this.date = builder.date;
        this.selectStatus = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isSelected() {
        return selectStatus;
    }

    public void setSelectStatus(boolean selected) {
        this.selectStatus = selected;
    }
}
