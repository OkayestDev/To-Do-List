package edu.bsu.cs222.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.List;

public class TaskListLoader {
    private String fileName;
    private ObservableList<Task> taskList;
    private SAXBuilder jdomBuilder;
    private Document jdomDocument;

    public TaskListLoader(String fileName) throws JDOMException, IOException {
        taskList = FXCollections.observableArrayList();
        this.fileName = fileName;
        jdomBuilder = new SAXBuilder();
        jdomDocument = jdomBuilder.build(this.fileName);
    }

    public ObservableList<Task> load() throws JDOMException, IOException {
        Element rootElement = jdomDocument.getRootElement();
        Element folder1 = rootElement.getChild("folder1");
        Element nameNode = folder1.getChild("task");
        String name = nameNode.getChild("name").getText();

        taskList.add(Task.withTaskName(name).andDescription("test").andDate("11/11/2017"));
        return taskList;
    }
}
