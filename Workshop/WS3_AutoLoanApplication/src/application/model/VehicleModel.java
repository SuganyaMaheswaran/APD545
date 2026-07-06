package application.model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class VehicleModel {
    private final ObjectProperty<VehicleType> type = new SimpleObjectProperty<>(VehicleType.CAR);
    private final ObjectProperty<VehicleAge> age = new SimpleObjectProperty<>(VehicleAge.NEW);
    private final DoubleProperty price = new SimpleDoubleProperty(0.0);

    public ObjectProperty<VehicleType> typeProperty() { return type; }
    public VehicleType getType() { return type.get(); }
    public void setType(VehicleType v) { this.type.set(v); }

    public ObjectProperty<VehicleAge> ageProperty() { return age; }
    public VehicleAge getAge() { return age.get(); }
    public void setAge(VehicleAge v) { this.age.set(v); }

    public DoubleProperty priceProperty() { return price; }
    public double getPrice() { return price.get(); }
    public void setPrice(double v) { this.price.set(v); }
    @Override
    public String toString() { return this.type.get().toString(); }
}
