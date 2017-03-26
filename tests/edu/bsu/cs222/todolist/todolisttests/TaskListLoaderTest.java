package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Task;
import edu.bsu.cs222.todolist.model.TaskListLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TaskListLoaderTest {
    private ObservableList<Task> taskList;

    private void setUp() {
        taskList = FXCollections.observableArrayList();
        Task taskOne = Task.withTaskName("Homework")
                .andDescription("CS222 Homework")
                .andDate("4/17/2017");
        Task taskTwo = Task.withTaskName("Dishes")
                .andDescription("Do the dishes you bum")
                .andDate("4/18/2017");
        Task taskThree = Task.withTaskName("CS222 Group Project")
                .andDescription("Code this test case")
                .andDate("4/19/2017");
        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);
    }

    @Test
    public void testLoad() throws JDOMException, IOException {
        setUp();
        TaskListLoader loader = new TaskListLoader("./assets/taskListLoadAndSaveTest.xml");
        ObservableList<Task> test = loader.load();
        Assert.assertTrue(taskList.get(0).getTaskName().equals(test.get(0).getTaskName()));
        Assert.assertTrue(taskList.get(1).getTaskName().equals(test.get(1).getTaskName()));
        Assert.assertTrue(taskList.get(2).getTaskName().equals(test.get(2).getTaskName()));
    }
}
