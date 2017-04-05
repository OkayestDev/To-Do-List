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
    private Element taskNode;
    private Document document;
    private Element nameNode;
    private Element descriptionNode;
    private Element dateNode;
    private int count;

    public TaskListSaver(ObservableList<Task> taskList) throws JDOMException, IOException{
        this.taskList = taskList;
        document = new Document();
        document.setRootElement(new Element("taskList"));
        count = 1;
    }

    public Document save() throws JDOMException, IOException{
        for(Task task : taskList) {
            addToDocument(task);
        }
        OutputDocumentToXml();
        return document;
    }

    private void OutputDocumentToXml() throws IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        FileOutputStream fileOutputStream = new FileOutputStream("./testassets/TaskListSaverTest.xml");
        xmlOutputter.output(document, fileOutputStream);
    }

    private void addToDocument(Task task) {
        setUpNodes(task);
        setNodesToDocument();
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

    private void setNodesToDocument() {
        taskNode.addContent(nameNode);
        taskNode.addContent(descriptionNode);
        taskNode.addContent(dateNode);
        document.getRootElement().addContent(taskNode);
    }
}
