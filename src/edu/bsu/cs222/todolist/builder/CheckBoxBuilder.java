package edu.bsu.cs222.todolist.builder;

import edu.bsu.cs222.todolist.model.Task;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CheckBoxBuilder implements Callback<TableColumn.CellDataFeatures<Task, CheckBox>, ObservableValue<CheckBox>> {
    private Task task;
    private CheckBox checkBox;

    @Override
    public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Task, CheckBox> param) {
        setTask(param);
        setUpCheckBox();
        return new SimpleObjectProperty<>(checkBox);
    }

    public void setTask(TableColumn.CellDataFeatures<Task,CheckBox> param) {
        task = param.getValue();
    }

    private void setUpCheckBox() {
        checkBox = new CheckBox();
        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> task.setSelectStatus(new_val));
    }
}
