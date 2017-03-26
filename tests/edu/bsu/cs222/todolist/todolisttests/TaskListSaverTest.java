package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Task;
import edu.bsu.cs222.todolist.model.TaskListSaver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class TaskListSaverTest {
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
    public void testTaskListSaver() throws JDOMException, IOException {
        setUp();
        SAXBuilder jdomBuilder = new SAXBuilder();
        TaskListSaver saver = new TaskListSaver(taskList);
        Document savedDocument = saver.save();
        Document testDocument = jdomBuilder.build(new File("assets/taskListLoadAndSaveTest.xml"));
        Assert.assertTrue(testDocument.toString().equals(savedDocument.toString()));
    }
}
