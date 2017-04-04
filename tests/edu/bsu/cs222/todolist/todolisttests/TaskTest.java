package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TaskTest {
    private Task newTask;

    public TaskTest() {
        newTask = Task.withTaskName("School").andDescription("no").andDate("11/11/2017");
    }

    @Test
    public void testGetTaskName() {
        Assert.assertEquals(newTask.getTaskName(), "School");
    }

    @Test
    public void testGetGetDescription() {
        Assert.assertEquals(newTask.getDescription(), "no");
    }

    @Test
    public void testGetDate() {
        Assert.assertEquals(newTask.getDate(), "11/11/2017");
    }

    @Test
    public void testUseLocalDate(){
        LocalDate localDate = new LocalDate(11/11/2017);
    }
}
