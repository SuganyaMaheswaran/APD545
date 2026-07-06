package utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import models.Vehicle;

public class ComboBoxUtils {

    public static void setupVehicleComboBox(ComboBox<Vehicle> comboBox) {

        comboBox.setCellFactory(listView -> new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle v, boolean empty) {
                super.updateItem(v, empty);
                setText(empty || v == null ? null
                        : v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")");
            }
        });

        comboBox.setButtonCell(new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle v, boolean empty) {
                super.updateItem(v, empty);
                setText(empty || v == null ? null
                        : v.getMake() + " " + v.getModel() + " (" + v.getYear() + ")");
            }
        });
    }
}