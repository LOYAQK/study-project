package domain;

import java.io.Serializable;

public class Periodical extends Book implements Serializable {
    private String lssueNumber;//期号

    public Periodical(String id, String name, String time, int number, double price, String lssueNumber) {
        super(id, name, time, number, price);
        this.lssueNumber = lssueNumber;
    }

    public String getLssueNumber() {
        return lssueNumber;
    }

    public void setLssueNumber(String lssueNumber) {
        this.lssueNumber = lssueNumber;
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                " \nname=" + getName() +
                " \ntime=" + getTime() +
                " \nnumber=" + getNumber() +
                " \nprice=" + getPrice() +
                " \nlssueNumber=" + getLssueNumber();
    }
}
