package edu.bsu.cs222.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public class Searcher {
    ObservableList<Task> listToSearch;

    public Searcher(ObservableList<Task> listToSearch) {
        this.listToSearch = listToSearch;
    }

    public ObservableList<Task> filterList(String keyword) {
        Iterator<Task> iter = listToSearch.iterator();
        keyword = keyword.toLowerCase();
        ObservableList<Task> filteredList = FXCollections.observableArrayList();
        while (iter.hasNext()) {
            Task tempTask = iter.next();
            if (doesTaskContain(tempTask, keyword)) {
                filteredList.add(tempTask);
            }
        }
        return filteredList;
    }

    public boolean doesTaskContain(Task task, String keyword) {
        return task.getTaskName().toLowerCase().contains(keyword) || task.getDescription().toLowerCase().contains(keyword)
                || task.getDate().toLowerCase().contains(keyword);
    }
}
