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
        List<Element> taskNode = folder1.getChildren("task");

        for(int i = 0; i< taskNode.size(); i++) {
            taskList.add(Task.withTaskName(taskNode.get(0).getChild("name").getText()).andDescription(taskNode.get(0).getChild("description").getText()).andDate(taskNode.get(0).getChild("date").getText()));
        }
        return taskList;
    }

}
