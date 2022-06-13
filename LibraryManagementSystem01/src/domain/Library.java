package domain;

import java.io.Serializable;

public class Library extends Book implements Serializable {
    private String press;//出版社

    public Library(String id, String name, String time, int number, double price, String press) {
        super(id, name, time, number, price);
        this.press = press;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                " \nname=" + getName() +
                " \ntime=" + getTime() +
                " \nnumber=" + getNumber() +
                " \nprice=" + getPrice() +
                " \npress=" + getPress();
    }
}
