package edu.bsu.cs222.todolist.builder;

import edu.bsu.cs222.todolist.model.Task;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CheckBoxBuilder implements Callback<TableColumn.CellDataFeatures<Task, CheckBox>, ObservableValue<CheckBox>> {
    @Override
    public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Task, CheckBox> param) {
        Task task = param.getValue();
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> task.setToDelete(new_val));
        return new SimpleObjectProperty<>(checkBox);
    }
}
