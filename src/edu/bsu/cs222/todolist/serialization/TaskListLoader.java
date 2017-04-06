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
    private Iterator<Element> iterator;
    private String taskName;
    private String description;
    private LocalDate localDate;
    private Element taskNode;

    public TaskListLoader(String fileName) throws JDOMException, IOException {
        SAXBuilder jdomBuilder = new SAXBuilder();
        Document document = jdomBuilder.build(new File(fileName));
        List<Element> taskNodeList = document.getRootElement().getChildren();
        taskList = FXCollections.observableArrayList();
        iterator = taskNodeList.iterator();
    }

    public ObservableList<Task> load() {
        while (iterator.hasNext()) {
            taskNode = iterator.next();
            setTask();
            addTaskToTaskList();
        }
        return taskList;
    }

    private void setTask() {
        taskName = taskNode.getChildText("name");
        description = taskNode.getChildText("description");
        localDate = LocalDate.parse(taskNode.getChildText("date"));
    }

    private void addTaskToTaskList() {
        Task newTask = Task.withTaskName(taskName)
                .andDescription(description)
                .andDate(localDate);
        taskList.add(newTask);
    }
}
