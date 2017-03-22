package edu.bsu.cs222.todolisttests;

import edu.bsu.cs222.todolist.Task;
import edu.bsu.cs222.todolist.TaskListLoader;
import javafx.collections.ObservableList;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class testTaskListLoader {
    private  TaskListLoader taskListLoader;
    public testTaskListLoader() throws JDOMException, IOException {
        taskListLoader = new TaskListLoader("./assets/taskList.xml");
    }

    @Test
    public void testLoad() throws JDOMException, IOException {
        ObservableList<Task> taskList =  taskListLoader.load();
        Assert.assertEquals(taskList.get(0).getTaskName(),"homework");
    }

    @Test
    public void taskgetNodeName() throws JDOMException, IOException {
        ObservableList<Task> taskList =  taskListLoader.load();
        Assert.assertEquals(taskList.get(0).getTaskName(),"taskList");
    }
}
