package edu.bsu.cs222.todolist;

public class Task {
    private String taskName;
    private String description;
    private String date; //will need to change to a Date type, see feedback
    private boolean toDelete;

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
        this.toDelete = false;
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

    public boolean isToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }
}
