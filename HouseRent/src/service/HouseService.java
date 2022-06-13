package service;

import domain.House;

public class HouseService {
    private House[] houses;
    private int houseNum = 1;
    private int houseId = 1;

    public HouseService(int size){
        houses = new House[size];
        houses[0] = new House(1,"king","111","开福区",1000,"出租");
    }

    public House find(int id){
        for (int i = 0; i < houseNum; i++) {
            if(id == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }

    public boolean del(int id){
        int index = -1;
        for (int i = 0; i < houseNum; i++) {
            if(id == houses[i].getId()){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }
        for (int i = index; i < houseNum-1; i++) {
            houses[i] = houses[i+1];
        }
        houses[houseNum-1] = null;
        houseNum--;
        return true;
    }

    public boolean add(House newhouse){
        if(houseNum == houses.length){
            House[] newArr = new House[houses.length+1];
            for (int i = 0; i < houses.length; i++) {
                newArr[i] = houses[i];
            }
            houses = newArr;
        }
        houses[houseNum++] = newhouse;
        newhouse.setId(++houseId);
        return true;
    }

    public House[] list(){
        return houses;
    }
}
