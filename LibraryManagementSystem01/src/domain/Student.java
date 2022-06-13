package domain;

import java.io.Serializable;

public class Student extends Readers implements Serializable {
    private String major;//专业
    private String classes;//班级

    public Student(String id, String name, String major, String classes) {
        super(id, name);
        this.major = major;
        this.classes = classes;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                " \nname=" + getName() +
                " \nmajor=" + getMajor() +
                " \nclasses=" + getClasses();
    }
}
