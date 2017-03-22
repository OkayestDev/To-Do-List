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
    private String folder;
    private Element folderNode;
    public static Builder setXmlFileName(String fileName) {
        return new Builder(fileName);
    }

    public static final class Builder {
        private String fileName;
        private String folder;

        public Builder(String fileName) {
            this.fileName = fileName;
        }

        public TaskListLoader andFolderName(String folderName) throws JDOMException, IOException {
            this.folder = folderName;
            return new TaskListLoader(this);
        }
    }

    public TaskListLoader(Builder builder) throws JDOMException, IOException {
        taskList = FXCollections.observableArrayList();
        this.fileName = builder.fileName;
        this.folder = builder.folder;
        jdomBuilder = new SAXBuilder();
        jdomDocument = jdomBuilder.build(this.fileName);
    }

    public ObservableList<Task> load() throws JDOMException, IOException {
        Element rootElement = jdomDocument.getRootElement();
        List<Element> folders = rootElement.getChildren(folder);
        for(int i = 0; i <folders.size();i++ ){
            if(folders.get(i).getName().equals(folder)){
                folderNode = folders.get(i);
            }
        }
        List<Element> taskNode = folderNode.getChildren();
        for (int i = 0; i < taskNode.size(); i++) {
            taskList.add(Task.withTaskName(taskNode.get(i).getChild("name").getText()).andDescription(taskNode.get(i).getChild("description").getText()).andDate(taskNode.get(i).getChild("date").getText()));
        }
        return taskList;
    }

}