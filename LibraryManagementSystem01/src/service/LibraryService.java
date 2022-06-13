package service;

import domain.*;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("all")
public class LibraryService {
    private Book[] books;//定义图书数组，存储图书和期刊
    private int bookNum;//用来记录books数组里，有多少个数
    private Readers[] readers;
    private int readersNum;
    ArrayList list = new ArrayList();

    public void savaList() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("src\\Borrow.txt"));
            oos.writeInt(list.size());
            int length = list.size();
            for (int i = 0; i < length; i++) {
                oos.writeObject(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void renewList() {
        ObjectInputStream ois = null;
        File file = new File("src\\Borrow.txt");
        if (file.exists()) {
            try {
                ois = new ObjectInputStream(new FileInputStream("src\\Borrow.txt"));
                int length = ois.readInt();
                for (int i = 0; i < length; i++) {
                    list.add((Borrow) ois.readObject());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void savaReader() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("src\\Reader.txt"));
            oos.writeInt(readersNum);
            for (int i = 0; i < readersNum; i++) {
                oos.writeObject(readers[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void renewReader() {
        ObjectInputStream ois = null;
        File file = new File("src\\Reader.txt");
        if (file.exists()) {
            try {
                ois = new ObjectInputStream(new FileInputStream("src\\Reader.txt"));
                readersNum = ois.readInt();
                for (int i = 0; i < readersNum; i++) {
                    readers[i] = (Readers) ois.readObject();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void savaBook() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("src\\Book.txt"));
            oos.writeInt(bookNum);
            for (int i = 0; i < bookNum; i++) {
                oos.writeObject(books[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void renewBook() {
        ObjectInputStream ois = null;
        File file = new File("src\\Book.txt");
        if (file.exists()) {
            try {
                ois = new ObjectInputStream(new FileInputStream("src\\Book.txt"));
                bookNum = ois.readInt();
                for (int i = 0; i < bookNum; i++) {
                    books[i] = (Book) ois.readObject();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public LibraryService() {//无参构造器，创建数组大小
        books = new Book[10];
        readers = new Readers[10];
    }

    public Borrow[] serviceSearch(String bookName) {
        Borrow[] borrows = new Borrow[10];
        int i = 0;
        for (Object o : list) {
            Borrow borrow = (Borrow) o;
            if(((Borrow) o).getBook().getName().equals(bookName)){
                borrows[i] = borrow;
                i++;
            }
        }
        return borrows;
    }

    public boolean serviceReturn(String name,String bookName) {//根据输入的借阅人和图书名，归还图书
        for (Object o : list) {
            Borrow borrow = (Borrow) o;
            if(borrow.getReaders().getName().equals(name) && borrow.getBook().getName().equals(bookName)) {
                list.remove(o);
                borrow.getBook().setAmount(borrow.getBook().getAmount() + 1);//可借阅数量加1
                borrow.getBook().setAlready(borrow.getBook().getAlready() - 1);//已借阅量减1
                return true;
            }
        }

        return false;
    }

    public boolean serviceBorrow(Borrow borrow) {//借阅图书
        if(borrow.getBook().getAmount() == 0){//如果要借阅的书数量等于0，则借阅不了
            return false;
        }
        list.add(borrow);
        borrow.getBook().setAmount(borrow.getBook().getAmount() - 1);//可借阅数量减1
        borrow.getBook().setAlready(borrow.getBook().getAlready() + 1);//已借阅量加1
        return true;
    }

    public int serviceAddReaders(Readers reader){
        for (int i = 0; i < readersNum; i++) {//如果待添加的读者与添加好的读者编号相同，返回-1，否则返回1
            if(readers[i].getId().equals(reader.getId())){
                return -1;
            }
        }
        if(readersNum >= readers.length){//数组长度不够则扩容
            Readers[] newReaders = new Readers[readers.length+1];
            for (int i = 0; i < readers.length; i++) {
                newReaders[i] = readers[i];
            }
            readers = newReaders;
        }
        readers[readersNum] = reader;
        readersNum++;
        return 1;
    }

    public int  serviceAddBook(Book book){
        for (int i = 0; i < bookNum; i++) {//如果待添加的书与添加好的书编号相同，返回-1，否则返回1
            if(books[i].getId().equals(book.getId())){
                return -1;
            }
        }

        if(bookNum >= books.length) {//如果添加书时，数组长度不够则扩容
            Book[] newbooks = new Book[books.length+1];
            for (int i = 0; i < books.length; i++) {
                newbooks[i] = books[i];
            }
            books = newbooks;
        }
        books[bookNum] = book;
        bookNum++;
        return 1;
    }

    public Book serviceFind_idBook (String id){//按编号查询-图书
        for (int i = 0; i < bookNum; i++) {
            if(books[i].getId().equals(id)){
                return books[i];
            }
        }
        return null;
    }

    public Book serviceFind_nameBook (String name){//按名称查询-图书
        for (int i = 0; i < bookNum; i++) {
            if(books[i].getName().equals(name)){
                return books[i];
            }
        }
        return null;
    }

    public Readers serviceFind_idReader (String id){//按编号查询-读者
        for (int i = 0; i < readersNum; i++) {
            if(readers[i].getId().equals(id)){
                return readers[i];
            }
        }
        return null;
    }

    public Readers serviceFind_nameReader (String name){//按姓名查询-读者
        for (int i = 0; i < readersNum; i++) {
            if(readers[i].getName().equals(name)){
                return readers[i];
            }
        }
        return null;
    }

    public void serviceShow(Book book) {//打印图书信息的格式
        if(book instanceof Library){
            //System.out.println("编号\t\t名称\t\t出版时间\t\t数量\t\t价格\t\t出版社");
            System.out.println(book);
        }else if(book instanceof Periodical){
            //System.out.println("编号\t\t名称\t\t出版时间\t\t数量\t\t价格\t\t期号");
            System.out.println(book);
        }
    }

    public void serviceShow (Readers readers){
        if(readers instanceof Student){
            //System.out.println("编号\t\t姓名\t\t专业\t\t班级");
            System.out.println(readers);
        }else if(readers instanceof Teacher){
            //System.out.println("编号\t\t姓名\t\t单位");
            System.out.println(readers);
        }
    }
}
