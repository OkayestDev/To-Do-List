package edu.bsu.cs222.todolisttests;

import edu.bsu.cs222.todolist.Searcher;
import edu.bsu.cs222.todolist.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SearcherTest {
    private ObservableList<Task> listToFilter;
    private ObservableList<Task> alreadyFilteredList;
    private Task task1;
    private Task task2;
    private Task task3;

    public SearcherTest() {
        task1 = Task.withTaskName("Dog").andDescription("barking dog").andDate("11/11/2011");
        task2 = Task.withTaskName("Jog").andDescription("jog for 30 minutes").andDate("11/12/2011");
        task3 = Task.withTaskName("Cat").andDescription("Cat in the Hat").andDate("11/11/2012");
        listToFilter = FXCollections.observableArrayList();
        alreadyFilteredList = FXCollections.observableArrayList();
    }
    @Before
    public void addTaskToLists(){
        addTaskToListToFilter();
        addTaskToalreadyFilteredList();
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

    private void addTaskToalreadyFilteredList(){
        alreadyFilteredList.add(task1);
        alreadyFilteredList.add(task2);
    }
}
