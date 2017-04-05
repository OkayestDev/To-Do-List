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
    private Task task1;
    private Task task2;
    private Task task3;
    private ObservableList<Task> taskListForVerify;

    public TaskListLoaderTest() {
        taskList = FXCollections.observableArrayList();
        setTasks();
        addTasksToTaskList();
    }

    @SuppressWarnings("OctalInteger, 04 is the time format of LocalDate")
    private void setTasks() {
        LocalDate localDate1 = LocalDate.of(2017, 04, 17);
        task1 = Task.withTaskName("Homework")
                .andDescription("CS222 Homework")
                .andDate(localDate1);
        LocalDate localDate2 = LocalDate.of(2017, 04, 18);
        task2 = Task.withTaskName("Dishes")
                .andDescription("Do the dishes you bum")
                .andDate(localDate2);
        LocalDate localDate3 = LocalDate.of(2017, 04, 19);
        task3 = Task.withTaskName("CS222 Group Project")
                .andDescription("Code this test case")
                .andDate(localDate3);
    }

    private void addTasksToTaskList() {
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
    }

    @Test
    public void testLoad() throws JDOMException, IOException {
        loadXml();
        Assert.assertTrue(taskList.get(0).getTaskName().equals(taskListForVerify.get(0).getTaskName()));
        Assert.assertTrue(taskList.get(1).getTaskName().equals(taskListForVerify.get(1).getTaskName()));
        Assert.assertTrue(taskList.get(2).getTaskName().equals(taskListForVerify.get(2).getTaskName()));
    }

    private void loadXml() throws JDOMException, IOException {
        TaskListLoader loader = new TaskListLoader("./testassets/TaskListLoaderTest.xml");
        taskListForVerify = loader.load();
    }
}
