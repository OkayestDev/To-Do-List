package edu.bsu.cs222.todolist.todolisttests;

import edu.bsu.cs222.todolist.model.Searcher;
import edu.bsu.cs222.todolist.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class SearcherTest {
    private ObservableList<Task> listToFilter;
    private ObservableList<Task> alreadyFilteredList;
    private Task task1;
    private Task task2;
    private Task task3;

    @Before
    public void addTaskToLists(){
        addTaskToListToFilter();
        addTaskToAlreadyFilteredList();
    }

    public SearcherTest() {
        LocalDate localDate1 = LocalDate.of(2011, 11, 11);
        LocalDate localDate2 = LocalDate.of(2011, 11, 12);
        LocalDate localDate3 = LocalDate.of(2011, 11, 11);
        task1 = Task.withTaskName("Dog").andDescription("barking dog").andDate(localDate1);
        task2 = Task.withTaskName("Jog").andDescription("jog for 30 minutes").andDate(localDate2);
        task3 = Task.withTaskName("Cat").andDescription("Cat in the Hat").andDate(localDate3);
        listToFilter = FXCollections.observableArrayList();
        alreadyFilteredList = FXCollections.observableArrayList();
    }

    @Test
    public void testFilterListWithSubstring() {
        addTaskToLists();
        Searcher searcher = new Searcher(listToFilter);
        listToFilter = searcher.filterList("og");
        Assert.assertTrue(alreadyFilteredList.equals(listToFilter));
    }

    @Test
    public void testFilterListWithFullString(){
        Searcher searcher = new Searcher(listToFilter);
        listToFilter = searcher.filterList("Jog");
        Assert.assertEquals(task2,listToFilter.get(0));
    }

    private void addTaskToListToFilter(){
        listToFilter.add(task1);
        listToFilter.add(task2);
        listToFilter.add(task3);
    }

    private void addTaskToAlreadyFilteredList(){
        alreadyFilteredList.add(task1);
        alreadyFilteredList.add(task2);
    }
}
