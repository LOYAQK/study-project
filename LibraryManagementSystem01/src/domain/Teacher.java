package domain;

import java.io.Serializable;

public class Teacher extends Readers implements Serializable {
    private String unit;//单位

    public Teacher(String id, String name, String unit) {
        super(id, name);
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                " \nname=" + getName() +
                " \nunit=" + getUnit();
    }
}
