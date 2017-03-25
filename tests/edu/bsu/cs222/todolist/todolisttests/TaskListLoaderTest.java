package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Task;
import edu.bsu.cs222.todolist.model.TaskListLoader;
import javafx.collections.ObservableList;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TaskListLoaderTest {
    private TaskListLoader taskListLoaderOne;
    private TaskListLoader taskListLoaderTwo;

    public TaskListLoaderTest() throws JDOMException, IOException {
        taskListLoaderOne = TaskListLoader.setXmlFileName("./assets/taskListLoaderTest.xml").andFolderName("folder1");
        taskListLoaderTwo = TaskListLoader.setXmlFileName("./assets/taskListLoaderTest.xml").andFolderName("folder2");
    }

    @Test
    public void testLoad() throws JDOMException, IOException {
        ObservableList<Task> taskList = taskListLoaderOne.load();
        Assert.assertEquals(taskList.get(0).getTaskName(), "homework");
        Assert.assertEquals(taskList.get(0).getDescription(), "don't do it");
        Assert.assertEquals(taskList.get(0).getDate(), "11/11/2017");
    }

    @Test
    public void testLoadAllTasks() throws JDOMException, IOException {
        ObservableList<Task> taskList = taskListLoaderOne.load();
        Assert.assertEquals("housework", taskList.get(1).getTaskName());
        Assert.assertEquals("do it", taskList.get(1).getDescription() );
        Assert.assertEquals( "11/12/2017",taskList.get(1).getDate());
    }

    @Test
    public void testLoadDifferenFolder() throws JDOMException, IOException {
        ObservableList<Task> taskList = taskListLoaderTwo.load();
        Assert.assertEquals(taskList.get(0).getTaskName(), "game");
        Assert.assertEquals(taskList.get(0).getDescription(), "well");
        Assert.assertEquals(taskList.get(0).getDate(), "11/13/2017");

        Assert.assertEquals(taskList.get(1).getTaskName(), "study");
        Assert.assertEquals(taskList.get(1).getDescription(), "refuse");
        Assert.assertEquals(taskList.get(1).getDate(), "11/15/2017");
    }

    @Test
    public void testAddNode() throws JDOMException, IOException {
        taskListLoaderOne.load();
        taskListLoaderTwo.add(Task.withTaskName("test").andDescription("testtest").andDate("11/22/2017"));
    }
}
