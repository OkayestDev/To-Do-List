package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Task;
import edu.bsu.cs222.todolist.serialization.MarkAsComplete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;

public class MarkAsCompleteTest {
    private ObservableList<Task> entireTaskList = FXCollections.observableArrayList();
    private ObservableList<Task> completedTaskList = FXCollections.observableArrayList();

    private void setUp() {
        LocalDate localDate1 = LocalDate.of(2011, 11, 11);
        LocalDate localDate2 = LocalDate.of(2011, 11, 12);
        LocalDate localDate3 = LocalDate.of(2011, 11, 11);
        Task taskOne = Task.withTaskName("Dog").andDescription("barking dog").andDate(localDate1);
        taskOne.setSelectStatus(true);
        Task taskTwo = Task.withTaskName("Jog").andDescription("jog for 30 minutes").andDate(localDate2);
        Task taskThree = Task.withTaskName("Cat").andDescription("Cat in the Hat").andDate(localDate3);
        entireTaskList.add(taskOne);
        entireTaskList.add(taskTwo);
        entireTaskList.add(taskThree);
        completedTaskList.add(taskOne);
    }

    @Test
    public void testMarkAsComplete() {
        setUp();
        MarkAsComplete markAsComplete = new MarkAsComplete(entireTaskList);
        ObservableList<Task> testList = markAsComplete.makeMarkAsCompleteList();
        Assert.assertTrue(testList.get(0).getTaskName().equals(completedTaskList.get(0).getTaskName()));
    }
}
