package edu.bsu.cs222.todolist.model;

public class Task {
    private String taskName;
    private String description;
    private String date; //will need to change to a Date type, see feedback
    private boolean selected;

    public static Builder withTaskName(String taskName) {
        return new Builder(taskName);
    }

    public static final class Builder {
        private String taskName;
        private String description;
        private String date;

        Builder(String taskName) {
            this.taskName = taskName;
        }

        public Builder andDescription(String description) {
            this.description = description;
            return this;
        }

        public Task andDate(String date){
            this.date = date;
            return new Task(this);
        }
    }

    public Task(Builder builder) {
        this.taskName = builder.taskName;
        this.description = builder.description;
        this.date = builder.date;
        this.selected = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
