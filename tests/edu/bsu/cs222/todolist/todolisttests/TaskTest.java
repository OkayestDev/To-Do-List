package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TaskTest {
    private Task newTask;

    public TaskTest() {
        LocalDate localDate = LocalDate.of(2017, 11, 11);
        newTask = Task.withTaskName("School").andDescription("no").andDate(localDate);
    }

    @Test
    public void testGetTaskName() {
        Assert.assertEquals(newTask.getTaskName(), "School");
    }

    @Test
    public void testGetDescription() {
        Assert.assertEquals(newTask.getDescription(), "no");
    }

    @Test
    public void testGetDate() {
        Assert.assertEquals(newTask.getDate().toString(), "2017-11-11");
    }
}
