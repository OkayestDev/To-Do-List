package edu.bsu.cs222.todolisttests;

import edu.bsu.cs222.todolist.Task;
import edu.bsu.cs222.todolist.TaskListEditor;
import javafx.collections.ObservableList;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class testTaskListEditor {
    private TaskListEditor taskListEditorOne;
    private TaskListEditor taskListEditorTwo;

    public testTaskListEditor() throws JDOMException, IOException {
        taskListEditorOne = TaskListEditor.setXmlFileName("./assets/taskList.xml").andFolderName("folder1");
        taskListEditorTwo = TaskListEditor.setXmlFileName("./assets/taskList.xml").andFolderName("folder2");
    }

    @Test
    public void testLoad() throws JDOMException, IOException {
        ObservableList<Task> taskList = taskListEditorOne.load();
        Assert.assertEquals(taskList.get(0).getTaskName(), "homework");
        Assert.assertEquals(taskList.get(0).getDescription(), "don't do it");
        Assert.assertEquals(taskList.get(0).getDate(), "11/11/2017");
    }

    @Test
    public void testLoadAllTasks() throws JDOMException, IOException {
        ObservableList<Task> taskList = taskListEditorOne.load();
        Assert.assertEquals("housework", taskList.get(1).getTaskName());
        Assert.assertEquals("do it", taskList.get(1).getDescription() );
        Assert.assertEquals( "11/12/2017",taskList.get(1).getDate());
    }

    @Test
    public void testLoadDifferenFolder() throws JDOMException, IOException {
        ObservableList<Task> taskList = taskListEditorTwo.load();
        Assert.assertEquals(taskList.get(0).getTaskName(), "game");
        Assert.assertEquals(taskList.get(0).getDescription(), "well");
        Assert.assertEquals(taskList.get(0).getDate(), "11/13/2017");

        Assert.assertEquals(taskList.get(1).getTaskName(), "study");
        Assert.assertEquals(taskList.get(1).getDescription(), "refuse");
        Assert.assertEquals(taskList.get(1).getDate(), "11/15/2017");
    }

    @Test
    public void testAddNode() throws JDOMException, IOException {
        taskListEditorOne.load();
        taskListEditorTwo.add(Task.withTaskName("test").andDescription("testtest").andDate("11/22/2017"));
    }
}
