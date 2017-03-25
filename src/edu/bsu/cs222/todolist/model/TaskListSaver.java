package edu.bsu.cs222.todolist.model;

import javafx.collections.ObservableList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;
import java.io.FileOutputStream;
import java.io.IOException;

public class TaskListSaver {
    private ObservableList<Task> taskList;
    private Document savedTaskList;
    private Element nameNode;
    private Element descriptionNode;
    private Element dateNode;
    private Element taskNode;
    private int count;

    public TaskListSaver(ObservableList<Task> taskList) throws JDOMException, IOException{
        this.taskList = taskList;
        savedTaskList = new Document();
        savedTaskList.setRootElement(new Element("taskList"));
        count = 1;
    }

    public Document save() throws JDOMException, IOException{
        for(Task task : taskList) {
            addToDocument(task);
        }
        XMLOutputter xmlOutputter = new XMLOutputter();
        FileOutputStream fileOutputStream = new FileOutputStream("./tasklist/SavedTaskList.xml");
        xmlOutputter.output(savedTaskList, fileOutputStream);
        return savedTaskList;
    }

    public void newElements() {
        taskNode = new Element("task_" + count);
        count++;
        nameNode = new Element("name");
        descriptionNode = new Element("description");
        dateNode = new Element("date");
    }

    public void setUpElements(Task task) {
        newElements();
        String taskName = task.getTaskName();
        String description = task.getDescription();
        String date = task.getDate();
        nameNode.setText(taskName);
        descriptionNode.setText(description);
        dateNode.setText(date);
    }

    private void addToDocument(Task task) {
        setUpElements(task);
        taskNode.addContent(nameNode);
        taskNode.addContent(descriptionNode);
        taskNode.addContent(dateNode);
        savedTaskList.getRootElement().addContent(taskNode);
    }
}
