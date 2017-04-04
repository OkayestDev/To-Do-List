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
    private Document document;

    public TaskListLoader(String fileName) throws JDOMException, IOException{
        SAXBuilder jdomBuilder = new SAXBuilder();
        document = jdomBuilder.build(new File(fileName));
    }

    public ObservableList<Task> load() {
                List<Element> taskNodeChildrenList = document.getRootElement().getChildren();
                Iterator<Element> iterator = taskNodeChildrenList.iterator();
                ObservableList<Task> taskList = FXCollections.observableArrayList();
                while(iterator.hasNext()) {
                    Element element = iterator.next();
                    String taskName = element.getChildText("name");
                    String description = element.getChildText("description");
                    String date = element.getChildText("date");
                    LocalDate localDate = LocalDate.parse(date);
                    Task newTask = Task.withTaskName(taskName)
                            .andDescription(description)
                            .andDate(localDate);
            taskList.add(newTask);
        }
        return taskList;
    }
}
