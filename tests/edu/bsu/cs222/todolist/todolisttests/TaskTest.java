package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Task;
import org.junit.Assert;
import org.junit.Test;

public class TaskTest {
    private Task newTask;

    public TaskTest() {
        newTask = Task.withTaskName("School").andDescription("no").andDate("11/11/2017");
    }

    @Test
    public void testTaskGetTaskName() {
        Assert.assertEquals(newTask.getTaskName(), "School");
    }

    @Test
    public void testTaskGetGetDescription() {
        Assert.assertEquals(newTask.getDescription(), "no");
    }

    @Test
    public void testTaskGetDate() {
        Assert.assertEquals(newTask.getDate(), "11/11/2017");
    }
}
