package edu.bsu.cs222.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public class Searcher {
    private ObservableList<Task> listToSearch;
    private Iterator<Task> iter;
    private ObservableList<Task> filteredList;
    private String keyWord;

    public Searcher(ObservableList<Task> listToSearch) {
        this.listToSearch = listToSearch;
    }

    public ObservableList<Task> filterList(String keyword) {
        setIterator();
        setFilteredList();
        defineKeyword(keyword);
        retriveResult();
        return filteredList;
    }
    private void setIterator() {
        iter = listToSearch.iterator();
    }

    private void setFilteredList() {
        filteredList = FXCollections.observableArrayList();
    }

    private void defineKeyword(String keyword) {
        this.keyWord = keyword;
        this.keyWord = keyword.toLowerCase();
    }

    private void retriveResult() {
        while (iter.hasNext()) {
            addToFilterList();
        }
    }

    private void addToFilterList() {
        Task targetTask = iter.next();
        if (isContain(targetTask, keyWord)) {
            filteredList.add(targetTask);
        }
    }

    private boolean isContain(Task targetTask, String keyword) {
        return targetTask.getTaskName().toLowerCase().contains(keyword) || targetTask.getDescription().toLowerCase().contains(keyword)
                || targetTask.getDate().toLowerCase().contains(keyword);
    }

}