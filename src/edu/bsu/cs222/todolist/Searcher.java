package edu.bsu.cs222.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public class Searcher {
    private String keyword;
    private ObservableList<Task> filteredList = FXCollections.observableArrayList();
    private Iterator<Task> iter;

    public Searcher(ObservableList<Task> listToSearch) {
        keyword = null;
        iter = listToSearch.iterator();
    }

    public ObservableList<Task> search(String keyword) {
        defineKeyword(keyword);
        retriveResult();
        return filteredList;
    }

    private void defineKeyword(String keyword) {
        this.keyword = keyword;
        this.keyword = keyword.toLowerCase();
    }

    private void retriveResult() {
        while (iter.hasNext()) {
            addToFilterList();
        }
    }

    private void addToFilterList() {
        Task targetTask = iter.next();
        if (isContain(targetTask, keyword)) {
            filteredList.add(targetTask);
        }
    }

    private boolean isContain(Task targetTask, String keyword) {
        return targetTask.getTaskName().toLowerCase().contains(keyword) || targetTask.getDescription().toLowerCase().contains(keyword)
                || targetTask.getDate().toLowerCase().contains(keyword);
    }
}
