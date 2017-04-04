package edu.bsu.cs222.todolist.model;

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
    private Document savedTaskList;

    public TaskListLoader(String filename) throws JDOMException, IOException{
        SAXBuilder jdomBuilder = new SAXBuilder();
        savedTaskList = jdomBuilder.build(new File(filename));
    }

    public ObservableList<Task> load() {
                List<Element> taskNodeChildren = savedTaskList.getRootElement().getChildren();
                Iterator<Element> iterator = taskNodeChildren.iterator();
                ObservableList<Task> taskList = FXCollections.observableArrayList();
                while(iterator.hasNext()) {
                    Element element = iterator.next();
                    String taskName = element.getChildText("name");
                    String description = element.getChildText("description");
                    element.getChildText("date");
//                    Task newTask = Task.withTaskName(taskName)
//                            .andDescription(description)
//                            .andDate(date);
//            taskList.add(newTask);
        }
        return taskList;
    }
}
