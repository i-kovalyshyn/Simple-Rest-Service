package edu.cursor.simple_rest_hw11.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data

public class Author {
    private int id;
    private String fName;
    private String lName;
    private Set<Book>book=new HashSet<>();

    public Author(int id, String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    public void setBook(Book bookX) {
        book.add(bookX);
    }
}
