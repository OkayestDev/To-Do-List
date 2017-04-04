package edu.bsu.cs222.todolist.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Iterator;

public class Searcher {
    private ObservableList<Task> listToSearch;
    private Iterator<Task> iterator;
    private ObservableList<Task> filteredList;
    private String keyword;

    public Searcher(ObservableList<Task> listToSearch) {
        this.listToSearch = listToSearch;
    }

    public ObservableList<Task> filterList(String keyword) {
        setIterator();
        setFilteredList();
        setUpKeyword(keyword);
        retrieveResult();
        return filteredList;
    }
    private void setIterator() {
        iterator = listToSearch.iterator();
    }

    private void setFilteredList() {
        filteredList = FXCollections.observableArrayList();
    }

    private void setUpKeyword(String keyword) {
        this.keyword = keyword;
        this.keyword = keyword.toLowerCase();
    }

    private void retrieveResult() {
        while (iterator.hasNext()) {
            addTargetTaskToFilteredList();
        }
    }

    private void addTargetTaskToFilteredList() {
        Task targetTask = iterator.next();
        if (isContain(targetTask, keyword)) {
            filteredList.add(targetTask);
        }
    }

    private boolean isContain(Task targetTask, String keyword) {
        return targetTask.getTaskName().toLowerCase().contains(keyword)
                || targetTask.getDescription().toLowerCase().contains(keyword)
                || targetTask.getDate().toString().toLowerCase().contains(keyword);
    }
}