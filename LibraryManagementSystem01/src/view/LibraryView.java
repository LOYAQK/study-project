package view;



import domain.*;
import service.LibraryService;
import utils.Utility;

import java.util.Scanner;

public class LibraryView {
    private boolean loop = true;
    Scanner input = new Scanner(System.in);
    LibraryService libraryService = new LibraryService();

    public void bookSearch() {
        System.out.println("\t***************图书查询*********************");
        System.out.println("请输入要查询的图书名");
        String bookName = input.next();
        Book book = libraryService.serviceFind_nameBook(bookName);//先查找有没有该图书
        if(book == null){
            System.out.println("没有该图书...请添加");
        }
        Borrow[] borrows = libraryService.serviceSearch(bookName);
        if(borrows.length == 0){
            System.out.println("该图书没有借阅情况");
        }else {
            System.out.println("图书名:" + bookName);
            System.out.println("库存数量:" + book.getNumber());
            System.out.println("可借阅量:" + book.getAmount());
            System.out.println("已借阅量:" + book.getAlready());
            System.out.print("借阅人: ");
            for (int i = 0; i < borrows.length; i++) {
                if(borrows[i] == null){
                    break;
                }
                System.out.print("\t" + borrows[i].getReaders().getName() + "\t");
            }
            System.out.println();
        }
    }

    public void returnBooks() {//图书归还
        System.out.println("\t***************图书归还管理*********************");
        System.out.println("请输入归还人姓名：");
        String name = input.next();
        System.out.println("请输入要归还的书籍名称：");
        String bookName = input.next();
        if(libraryService.serviceReturn(name,bookName)) {
            System.out.println("归还成功！！！");
        } else {
            System.out.println("姓名或书籍名不正确，请重新尝试。。。");
        }

    }

    public void bookBorrowing() {//借阅图书
        System.out.println("\t***************图书借阅管理*********************");
        System.out.println("请输入借阅人名字：");
        String name = input.next();
        Readers readers = libraryService.serviceFind_nameReader(name);
        if(readers == null){
            System.out.println("没有该读者信息，请先登记");
            return;
        }
        System.out.println("请输入要借阅的书籍名称：");
        String bookName = input.next();
        Book book = libraryService.serviceFind_nameBook(bookName);
        if(book == null){
            System.out.println("没有该书籍，请先添加");
            return;
        }
        Borrow borrow = new Borrow(readers,book);
        if(libraryService.serviceBorrow(borrow)){
            System.out.println("借阅成功！！！");
        }else {
            System.out.println("该书都被借出去了，请等一段时间在来");
        }
    }

    public void alterReaders(){
        System.out.print("请输入要修改的读者姓名:");
        String names = input.next();
        Readers readers = libraryService.serviceFind_nameReader(names);
        if(readers == null){
            System.out.println("姓名有误，没有找到该读者...");
            return;
        }
        if(readers instanceof Student){
            System.out.print("编号(" + readers.getId() + "):");
            String id = Utility.readString(10,"");
            if(!"".equals(id)){
                readers.setId(id);
            }

            System.out.print("姓名(" + readers.getName() + "):");
            String name = Utility.readString(10,"");
            if(!"".equals(name)){
                readers.setName(name);
            }

            System.out.print("专业(" + ((Student) readers).getMajor() + "):");
            String major = Utility.readString(10,"");
            if(!"".equals(major)){
                ((Student) readers).setMajor(major);
            }

            System.out.print("班级(" + ((Student) readers).getClasses() + "):");
            String classes = Utility.readString(10,"");
            if(!"".equals(classes)){
                ((Student) readers).setClasses(classes);
            }
        }else if(readers instanceof Teacher){
            System.out.print("编号(" + readers.getId() + "):");
            String id = Utility.readString(10,"");
            if(!"".equals(id)){
                readers.setId(id);
            }

            System.out.print("姓名(" + readers.getName() + "):");
            String name = Utility.readString(10,"");
            if(!"".equals(name)){
                readers.setName(name);
            }

            System.out.print("单位(" + ((Teacher) readers).getUnit() + "):");
            String unit = Utility.readString(10,"");
            if(!"".equals(unit)){
                ((Teacher) readers).setUnit(unit);
            }
        }
    }

    public void findReaders() {
        System.out.println("请输入查询方式（“编号”或“姓名”）：");
        String myInput = input.next();
        if("编号".equals(myInput)){
            System.out.print("请输入要查询的读者编号:");
            String id = input.next();
            Readers readers = libraryService.serviceFind_idReader(id);//根据输入id 查找返回读者
            if(readers == null){
                System.out.println("没有找到该id...");
            }else {
//                libraryService.serviceShow(readers);//显示按编号找到的读者情况
                System.out.println(readers);
            }
        }else if ("姓名".equals(myInput)){
            System.out.println("请输入要查询的读者姓名:");
            String name = input.next();
            Readers readers = libraryService.serviceFind_nameReader(name);
            if(readers == null){
                System.out.println("没有找到该姓名...");
            }else {
                libraryService.serviceShow(readers);
            }
        }else {
            System.out.println("查询方式输入不正确...");
        }
    }

    public void addTeacher() {
        System.out.print("编号:");
        String id = input.next();
        System.out.print("姓名:");
        String name = input.next();
        System.out.print("单位:");
        String unit = input.next();
        Teacher teacher = new Teacher(id,name,unit);
        if(libraryService.serviceAddReaders(teacher) == -1){
            System.out.println("\t************教师信息添加未成功,该编号已被使用****************");
        }else {
            System.out.println("\t***************教师信息添加成功*********************");
        }
    }

    public void addStudent() {
        System.out.print("编号:");
        String id = input.next();
        System.out.print("姓名:");
        String name = input.next();
        System.out.print("专业:");
        String major = input.next();
        System.out.print("班级:");
        String classes = input.next();
        Student student = new Student(id,name,major,classes);
        if(libraryService.serviceAddReaders(student) == -1){
            System.out.println("\t************学生信息添加未成功,该编号已被使用****************");
        }else {
            System.out.println("\t***************学生信息添加成功*********************");
        }
    }

    public void addReaders() {
        System.out.println("\t***************新增读者信息*********************");
        System.out.println("是要新增“教师” 还是“学生”");
        String inputReader = input.next();
        if("教师".equals(inputReader)){
            addTeacher();
        }else if ("学生".equals(inputReader)){
            addStudent();
        }else {
            System.out.println("输入的信息不对,只能输入“教师” 或“学生”");
        }
    }

    public void alterBook() {//修改图书信息
        System.out.print("请输入要修改的书籍名称：");
        String names = input.next();
        Book book = libraryService.serviceFind_nameBook(names);
        if(book == null){
            System.out.println("图书名称有误，没有找到该书籍");
            return;
        }
        if(book instanceof Library){//判断book 的允许类型是不是Library
            System.out.print("编号(" + book.getId() + "):");
            String id = Utility.readString(10,"");
            if(!"".equals(id)){
                book.setId(id);
            }

            System.out.print("名称(" + book.getName() + "):");
            String name = Utility.readString(10,"");
            if (!"".equals(name)){
                book.setName(name);
            }

            System.out.print("出版时间(" + book.getTime() + "):");
            String time = Utility.readString(10,"");
            if(!"".equals(time)){
                book.setTime(time);
            }

            System.out.print("出版社(" + ((Library) book).getPress() + "):");
            String press = Utility.readString(10,"");
            if(!"".equals(press)){
                ((Library) book).setPress(press);
            }

            System.out.print("数量(" + book.getNumber() + "):");
            int number = Utility.readInt(-1);
            if(number != -1){
                book.setNumber(number);
                book.setAmount(number - book.getAlready());
            }

            System.out.print("价格(" + book.getPrice() + "):");
            double price = Utility.readDouble(-1);
            if(price != -1){
                book.setPrice(price);
            }

        }else if(book instanceof Periodical){
            System.out.print("编号(" + book.getId() + "):");
            String id = Utility.readString(10,"");
            if(!"".equals(id)){
                book.setId(id);
            }

            System.out.print("名称(" + book.getName() + "):");
            String name = Utility.readString(10,"");
            if (!"".equals(name)){
                book.setName(name);
            }

            System.out.print("发行时间(" + book.getTime() + "):");
            String time = Utility.readString(10,"");
            if(!"".equals(time)){
                book.setTime(time);
            }

            System.out.println("期号(" + ((Periodical) book).getLssueNumber() + "):");
            String lssueNumber = Utility.readString(10,"");
            if(!"".equals(lssueNumber)){
                ((Periodical) book).setLssueNumber(lssueNumber);
            }

            System.out.print("数量(" + book.getNumber() + "):");
            int number = Utility.readInt(-1);
            if(number != -1){
                book.setNumber(number);
            }

            System.out.print("价格(" + book.getPrice() + "):");
            double price = Utility.readDouble(-1);
            if(price != -1){
                book.setPrice(price);
            }
        }
    }

    public void findBook() {//查询图书信息
        System.out.println("请输入查询方式（“编号”或“名称”）：");
        String myInput = input.next();
        if("编号".equals(myInput)){
            System.out.print("请输入要查询的图书编号：");
            String id = input.next();
            Book book = libraryService.serviceFind_idBook(id);//根据输入id 查找返回书籍
            if(book == null){
                System.out.println("没有找到该id...");
            }else {
                libraryService.serviceShow(book);//显示按编号找到的图书情况
            }
        }else if("名称".equals(myInput)){
            System.out.print("请输入要查询的图书名称：");
            String name = input.next();
            Book book = libraryService.serviceFind_nameBook(name);
            if(book == null){
                System.out.println("没有找到该name...");
            }else {
                libraryService.serviceShow(book);//显示按名称找到的图书情况
            }
        }else {
            System.out.println("查询方式输入不正确...");
        }
    }

    public void addPeriodical() {//添加期刊信息
        System.out.print("编号：");
        String id = input.next();
        System.out.print("名称：");
        String name = input.next();
        System.out.print("期号：");
        String lssueNumber = input.next();
        System.out.print("发行时间：");
        String time = input.next();
        System.out.print("数量：");
        int number = input.nextInt();
        System.out.print("价格：");
        double price = input.nextDouble();
        Periodical book = new Periodical(id,name,time,number,price,lssueNumber);
        if(libraryService.serviceAddBook(book) == -1){
            System.out.println("\t************期刊添加未成功,该编号已被使用****************");
        }else {
            System.out.println("\t***************期刊添加成功*********************");
        }
    }

    public void addLibrary() {//添加图书信息
        System.out.print("编号：");
        String id = input.next();
        System.out.print("名称：");
        String name = input.next();
        System.out.print("出版社：");
        String press = input.next();
        System.out.print("出版时间：");
        String time = input.next();
        System.out.print("数量：");
        int number = input.nextInt();
        System.out.print("价格：");
        double price = input.nextDouble();
        Library book = new Library(id,name,time,number,price,press);
        if(libraryService.serviceAddBook(book) == -1){
            System.out.println("\t************图书添加未成功,该编号已被使用****************");
        }else {
            System.out.println("\t***************图书添加成功*********************");
        }
    }

    public void addBook() {//添加图书信息主菜单
        System.out.println("\t***************新增图书信息*********************");
        System.out.println("是要新增“图书” 还是“期刊”");
        String inputBook = input.next();
        if ("图书".equals(inputBook)) {
            addLibrary();
        } else if ("期刊".equals(inputBook)) {
            addPeriodical();
        } else {
            System.out.println("输入的信息不对,只能输入“图书” 或“期刊”");
        }
    }

    public void mainMenu() {
        libraryService.renewBook();
        libraryService.renewReader();
        libraryService.renewList();
        do {
            System.out.println("\t\t\t\t图书资料管理系统\n");
            System.out.println("\t****************************************");
            System.out.println("\t\t1.新增图书信息\t\t6.查询读者信息");
            System.out.println("\t\t2.查询图书信息\t\t7.图书借阅管理");
            System.out.println("\t\t3.修改图书信息\t\t8.图书归还管理");
            System.out.println("\t\t4.新增读者信息\t\t9.图书查询");
            System.out.println("\t\t5，修改读者信息\t\t10.退出系统");
            System.out.println("\t****************************************");
            System.out.print("请输入选择编号:");
            String choice = input.next();
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    findBook();
                    break;
                case "3":
                    alterBook();
                    break;
                case "4":
                    addReaders();
                    break;
                case "5":
                    alterReaders();
                    break;
                case "6":
                    findReaders();
                    break;
                case "7":
                    bookBorrowing();
                    break;
                case "8":
                    returnBooks();
                    break;
                case "9":
                    bookSearch();
                    break;
                case "10":
                    char etc = Utility.readConfirmSelection();
                    if (etc == 'Y') {
                        loop = false;
                        libraryService.savaBook();
                        libraryService.savaReader();
                        libraryService.savaList();
                    }
                    break;
                default:
                    System.out.println("有效数字1~10");
                    break;
            }
            System.out.print("按任意键继续...");
            Scanner in = new Scanner(System.in);
            in.nextLine();
        } while (loop);
    }
}
