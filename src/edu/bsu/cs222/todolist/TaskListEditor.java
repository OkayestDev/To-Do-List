package edu.bsu.cs222.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TaskListEditor {
    private String fileName;
    private ObservableList<Task> taskList;
    private SAXBuilder jdomBuilder;
    private Document jdomDocument;
    private String folder;
    private Element folderNode;
    private Element rootElement;

    public static Builder setXmlFileName(String fileName) {
        return new Builder(fileName);
    }

    public static final class Builder {
        private String fileName;
        private String folder;

        public Builder(String fileName) {
            this.fileName = fileName;
        }

        public TaskListEditor andFolderName(String folderName) throws JDOMException, IOException {
            this.folder = folderName;
            return new TaskListEditor(this);
        }
    }

    public TaskListEditor(Builder builder) throws JDOMException, IOException {
        taskList = FXCollections.observableArrayList();
        this.fileName = builder.fileName;
        this.folder = builder.folder;
        jdomBuilder = new SAXBuilder();
        jdomDocument = jdomBuilder.build(this.fileName);

        rootElement = jdomDocument.getRootElement();
        List<Element> folders = rootElement.getChildren(folder);
        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equals(folder)) {
                folderNode = folders.get(i);
            }
        }
    }

    public ObservableList<Task> load() throws JDOMException, IOException {

        List<Element> taskNode = folderNode.getChildren();
        for (int i = 0; i < taskNode.size(); i++) {
            taskList.add(Task.withTaskName(taskNode.get(i).getChild("name").getText()).andDescription(taskNode.get(i).getChild("description").getText()).andDate(taskNode.get(i).getChild("date").getText()));
        }
        return taskList;
    }

    public void add(Task task) throws IOException {
        Element taskNode = new Element("task");
        Element nameNode = new Element("name");
        Element descriptionNode = new Element("description");
        Element dateNode = new Element("date");

        nameNode.setText(task.getTaskName());
        descriptionNode.setText(task.getDescription());
        dateNode.setText(task.getDate());

        taskNode.addContent(nameNode);
        taskNode.addContent(descriptionNode);
        taskNode.addContent(dateNode);
        folderNode.addContent(taskNode);

        XMLOutputter xmlOutputter = new XMLOutputter();
        FileOutputStream fileOutputStream = new FileOutputStream("./assets/taskListAdd.xml");
        xmlOutputter.output(jdomDocument,fileOutputStream);
    }
}
