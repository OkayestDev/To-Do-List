package edu.bsu.cs222.todolist.builder;

import edu.bsu.cs222.todolist.model.Task;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class CheckBoxBuilder implements Callback<TableColumn.CellDataFeatures<Task, CheckBox>, ObservableValue<CheckBox>> {
    private CheckBox checkBox;
    TableView<Task> taskTable;
    @Override
    public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Task, CheckBox> param) {
        setTask(param);
        return new SimpleObjectProperty<>(checkBox);
    }

    public void setTask(TableColumn.CellDataFeatures<Task, CheckBox> param) {
       Task task = param.getValue();
       setUpCheckBox(task);
    }

    private void setUpCheckBox(Task task) {
        checkBox = new CheckBox();
        checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
            task.setSelectStatus(new_val);
        });
    }
}
