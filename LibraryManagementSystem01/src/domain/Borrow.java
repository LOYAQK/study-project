package domain;

import java.io.Serializable;

public class Borrow implements Serializable {
    private Readers readers;
    private Book book;

    public Borrow(Readers readers, Book book) {
        this.readers = readers;
        this.book = book;
    }

    public Readers getReaders() {
        return readers;
    }

    public void setReaders(Readers readers) {
        this.readers = readers;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
