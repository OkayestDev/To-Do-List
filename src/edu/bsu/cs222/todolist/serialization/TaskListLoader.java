package edu.bsu.cs222.todolist.serialization;

import edu.bsu.cs222.todolist.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class TaskListLoader {
    private ObservableList<Task> taskList;
    private ObservableList<Task> completedTaskList;
    private Iterator<Element> taskListIterator;
    private Iterator<Element> completedTaskListIterator;
    private String taskName;
    private String description;
    private LocalDate localDate;
    private Element taskNode;

    public TaskListLoader(String fileName) throws JDOMException, IOException {
        SAXBuilder jdomBuilder = new SAXBuilder();
        Document document = jdomBuilder.build(new File(fileName));
        List<Element> taskListElements = document.getRootElement().getChild("taskList").getChildren();
        List<Element> completedTaskListElements = document.getRootElement().getChild("completedTaskList").getChildren();
        taskList = FXCollections.observableArrayList();
        completedTaskList = FXCollections.observableArrayList();
        taskListIterator = taskListElements.iterator();
        completedTaskListIterator = completedTaskListElements.iterator();
    }

    public ObservableList<Task> loadTaskList() {
        while (taskListIterator.hasNext()) {
            taskNode = taskListIterator.next();
            setTask();
            addTaskToTaskList(taskList);
        }
        return taskList;
    }

    public ObservableList<Task> loadCompletedTaskList() {
        while (completedTaskListIterator.hasNext()) {
            taskNode = completedTaskListIterator.next();
            setTask();
            addTaskToTaskList(completedTaskList);
        }
        return completedTaskList;
    }

    private void setTask() {
        taskName = taskNode.getChildText("name");
        description = taskNode.getChildText("description");
        localDate = LocalDate.parse(taskNode.getChildText("date"));
    }

    private void addTaskToTaskList(ObservableList<Task> addToThisList) {
        Task newTask = Task.withTaskName(taskName)
                .andDescription(description)
                .andDate(localDate);
        addToThisList.add(newTask);
    }
}
