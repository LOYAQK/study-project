package domain;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;//编号
    private String name;//名称
    private String time;//出版时间/发行时间
    private int number;//数量
    private double price;//价格
    private int amount;//可借阅量
    private int already;//已借阅量

    public Book(String id, String name, String time, int number, double price) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.number = number;
        this.price = price;
        this.amount = number;
    }

    public int getAlready() {
        return already;
    }

    public void setAlready(int already) {
        this.already = already;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "id=" + getId() +
                " \nname=" + getName() +
                " \ntime=" + getTime() +
                " \nnumber=" + getNumber() +
                " \nprice=" + getPrice();
    }
}
