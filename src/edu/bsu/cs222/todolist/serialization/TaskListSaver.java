package edu.bsu.cs222.todolist.serialization;

import edu.bsu.cs222.todolist.model.Task;
import javafx.collections.ObservableList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

public class TaskListSaver {
    private ObservableList<Task> taskList;
    private ObservableList<Task> completedTaskList;
    private Document document;
    private Element taskListParentNode;
    private Element completedTaskListParentNode;
    private Element taskNode;
    private Element nameNode;
    private Element descriptionNode;
    private Element dateNode;
    private int count;

    public TaskListSaver(ObservableList<Task> taskList, ObservableList<Task> completedTaskList) throws JDOMException, IOException {
        this.taskList = taskList;
        this.completedTaskList = completedTaskList;
        document = new Document();
        taskListParentNode = new Element("taskList");
        completedTaskListParentNode = new Element("completedTaskList");
        setUpRootElement();
        count = 1;
    }

    private void setUpRootElement() {
        Element rootElement = new Element("savedTaskList");
        rootElement.addContent(taskListParentNode);
        rootElement.addContent(completedTaskListParentNode);
        document.setRootElement(rootElement);
    }

    public Document saveTo(String filePath) throws JDOMException, IOException {
        for (Task task : taskList) {
            addToDocument(task, taskListParentNode);
        }
        for (Task task : completedTaskList) {
            addToDocument(task, completedTaskListParentNode);
        }
        OutputDocumentToXml(filePath);
        return document;
    }

    private void OutputDocumentToXml(String filePath) throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        xmlOutputter.output(document, fileOutputStream);
    }

    private void addToDocument(Task task, Element currentList) {
        setUpNodes(task);
        setNodesToList(currentList);
    }

    private void setUpNodes(Task task) {
        createNodes();
        setNodes(task);
    }

    private void createNodes() {
        taskNode = new Element("task_" + count);
        count++;
        nameNode = new Element("name");
        descriptionNode = new Element("description");
        dateNode = new Element("date");
    }

    private void setNodes(Task task) {
        String taskName = task.getTaskName();
        String description = task.getDescription();
        String date = task.getDate().toString();
        nameNode.setText(taskName);
        descriptionNode.setText(description);
        dateNode.setText(date);
    }

    private void setNodesToList(Element currentList) {
        taskNode.addContent(nameNode);
        taskNode.addContent(descriptionNode);
        taskNode.addContent(dateNode);
        currentList.addContent(taskNode);
    }
}
