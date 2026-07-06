package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty phone = new SimpleStringProperty("");
    private final StringProperty city = new SimpleStringProperty("");
    private final StringProperty province = new SimpleStringProperty("");

    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String v) { this.name.set(v); }

    public StringProperty phoneProperty() { return phone; }
    public String getPhone() { return phone.get(); }
    public void setPhone(String v) { this.phone.set(v); }

    public StringProperty cityProperty() { return city; }
    public String getCity() { return city.get(); }
    public void setCity(String v) { this.city.set(v); }

    public StringProperty provinceProperty() { return province; }
    public String getProvince() { return province.get(); }
    public void setProvince(String v) { this.province.set(v); }
}