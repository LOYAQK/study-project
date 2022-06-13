package view;

import domain.House;
import service.HouseService;
import utils.Utility;

import java.util.Scanner;

public class HouseView {
    private boolean loop = true;
    Scanner input = new Scanner(System.in);
    HouseService houseService = new HouseService(2);

    public void update(){
        System.out.println("=========修改房屋界面=========");
        System.out.print("请输入要修改的房屋id(-1退出):");
        int id = input.nextInt();
        if(id == -1){
            System.out.println("=========放弃修改房屋=========");
            return;
        }
        House house = houseService.find(id);
        System.out.print("姓名(" + house.getName() + "):");
        String name = Utility.readString(4,"");
        if(!"".equals(name)){
            house.setName(name);
        }

        System.out.print("电话(" + house.getPhone() + "):");
        String phone = Utility.readString(3,"");
        if(!"".equals(phone)){
            house.setPhone(phone);
        }

        System.out.print("地址(" + house.getAddress() + "):");
        String address = Utility.readString(3,"");
        if(!"".equals(address)){
            house.setAddress(address);
        }

        System.out.print("月租(" + house.getRent() + "):");
        int rent = Utility.readInt(-1);
        if(rent != -1){
            house.setRent(rent);
        }

        System.out.print("状态(" + house.getState() + "):");
        String state = Utility.readString(3,"");
        if(!"".equals(state)){
            house.setState(state);
        }
        System.out.println("=========修改成功！=========");
    }

    public void findHouse(){
        System.out.println("=========查找房屋界面=========");
        System.out.print("请输入要查找房屋的id:");
        int id = input.nextInt();
        House house = houseService.find(id);
        if(house == null){
            System.out.println("=========没有找到对应编号=========");
            return;
        }else{
            System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
            System.out.println(house);
        }
    }

    public void delHouse() {
        System.out.println("=========删除房屋界面=========");
        System.out.print("请输入要删除的房屋id(-1退出):");
        int id = input.nextInt();
        if (id == -1) {
            System.out.println("=========取消房屋删除=========");
            return;
        }
        if (houseService.del(id)) {
            System.out.println("=========" + id + "号房屋删除成功！=========");
        } else {
            System.out.println("=========没有" + id + "号房屋=========");
        }
    }

    public void addHouse() {
        System.out.println("=========添加房屋界面=========");
        System.out.print("姓名:");
        String name = Utility.readString(4);
        System.out.print("电话:");
        String phone = Utility.readString(3);
        System.out.print("地址:");
        String address = Utility.readString(3);
        System.out.print("月租:");
        int rent = Utility.readInt(4);
        System.out.print("状态:");
        String state = Utility.readString(3);
        House newHouse = new House(1, name, phone, address, rent, state);
        houseService.add(newHouse);
        System.out.println("=========房屋添加成功=========");
    }

    public void listHouse() {
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
        House[] houses = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] != null) {
                System.out.println(houses[i]);
            }
        }
    }

    public void exitHouse() {
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            loop = false;
        }
    }

    public void mainMenu() {
        do {
            System.out.println("\n=========房屋出租系统菜单=========");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋 信 息");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t6 退      出");
            System.out.print("请输入你的选择(1-6)：");
            int key = input.nextInt();
            switch (key) {
                case 1:
                    addHouse();
                    break;
                case 2:
                    findHouse();
                    break;
                case 3:
                    delHouse();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    listHouse();
                    break;
                case 6:
                    exitHouse();
                    break;
            }
        } while (loop);
    }
}
