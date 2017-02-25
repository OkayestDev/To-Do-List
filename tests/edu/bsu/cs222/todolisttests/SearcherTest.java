package edu.bsu.cs222.todolisttests;

import edu.bsu.cs222.todolist.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;

public class SearcherTest {
    Searcher testSearcher = new Searcher(null);

    @Test
    public void testFilterList() {
        //make a couple tasks
        Task tempTask1 = new Task("Dog", "barking dog", "11/11/2011");
        Task tempTask2 = new Task("Jog", "jog for 30 minutes", "11/12/2011");
        Task tempTask3 = new Task("Cat", "Cat in the Hat", "11/11/2012");
        ObservableList<Task> listToFilter = FXCollections.observableArrayList();
        listToFilter.add(tempTask1);
        listToFilter.add(tempTask2);
        listToFilter.add(tempTask3);
        ObservableList<Task> alreadyFilteredList = FXCollections.observableArrayList();
        alreadyFilteredList.add(tempTask1);
        alreadyFilteredList.add(tempTask2);
        testSearcher = new Searcher(listToFilter);
        listToFilter = testSearcher.search("og");
        Assert.assertTrue(alreadyFilteredList.equals(listToFilter));
    }
}
