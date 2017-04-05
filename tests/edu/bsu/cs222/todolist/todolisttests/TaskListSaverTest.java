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
import java.time.LocalDate;

public class TaskListSaverTest {
    private ObservableList<Task> taskList;
    private Task task1;
    private Task task2;
    private Task task3;
    private SAXBuilder jdomBuilder;
    private Document savedDocument;
    private Document documentForTest;

    public TaskListSaverTest() {
        taskList = FXCollections.observableArrayList();
        jdomBuilder = new SAXBuilder();
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
    public void testTaskListSaver() throws JDOMException, IOException {
        saveXml();
        setDocumentForTest();

        Assert.assertTrue(documentForTest.toString().equals(savedDocument.toString()));
    }

    private void setDocumentForTest() throws JDOMException, IOException {
        documentForTest = jdomBuilder.build(new File("./testassets/TaskListLoaderTest.xml"));
    }

    private void saveXml() throws JDOMException, IOException {
        TaskListSaver saver = new TaskListSaver(taskList);
        savedDocument = saver.save();
    }
}
