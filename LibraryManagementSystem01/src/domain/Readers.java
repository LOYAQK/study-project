package domain;

import java.io.Serializable;

public class Readers implements Serializable {
    private String id;//编号
    private String name;//姓名

    public Readers(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                " \nname=" + getName();
    }
}
