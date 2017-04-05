package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Task;
import edu.bsu.cs222.todolist.model.TaskListLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

public class TaskListLoaderTest {
    private ObservableList<Task> taskList;

    public TaskListLoaderTest() {
        taskList = FXCollections.observableArrayList();
        LocalDate localDate1 = LocalDate.of(2017, 04, 17);
        Task task1 = Task.withTaskName("Homework")
                .andDescription("CS222 Homework")
                .andDate(localDate1);
        LocalDate localDate2 = LocalDate.of(2017, 04, 18);
        Task task2 = Task.withTaskName("Dishes")
                .andDescription("Do the dishes you bum")
                .andDate(localDate2);
        LocalDate localDate3 = LocalDate.of(2017, 04, 19);
        Task task3 = Task.withTaskName("CS222 Group Project")
                .andDescription("Code this test case")
                .andDate(localDate3);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
    }

    @Test
    public void testLoad() throws JDOMException, IOException {
        TaskListLoader loader = new TaskListLoader("./testassets/TaskListLoaderTest.xml");
        ObservableList<Task> test = loader.load();
        Assert.assertTrue(taskList.get(0).getTaskName().equals(test.get(0).getTaskName()));
        Assert.assertTrue(taskList.get(1).getTaskName().equals(test.get(1).getTaskName()));
        Assert.assertTrue(taskList.get(2).getTaskName().equals(test.get(2).getTaskName()));
    }
}
