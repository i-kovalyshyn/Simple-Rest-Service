package edu.cursor.simple_rest_hw11.database;

import edu.cursor.simple_rest_hw11.exception.NotFoundExceptions;
import edu.cursor.simple_rest_hw11.object.Author;
import edu.cursor.simple_rest_hw11.object.Book;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service

public class Service implements IService {

    private List<Book> bookList = new ArrayList<>(Arrays.asList(
            new Book(1,"some Book one","Classic",
                    "some descriptions about firs book",9),
            new Book(2,"second some book","Comic Novel",
                    "some funny stories",5),
            new Book(3,"third book","Drama",
                    "some stories with life",7),
            new Book(4,"fourth book","Fairy Tale",
                    "some magic stories",4),
            new Book(5,"fifth book","Classic",
                    "some classic stories",8)
    ));
    private List<Author> authorList = new ArrayList<>(Arrays.asList(
            new Author(1,"name_1","surname_1",
                    new HashSet<Book>(bookList.subList(0,1))),
            new Author(2,"name_2","surname_2",
                    new HashSet<Book>(bookList.subList(1,2))),
            new Author(3,"name_3","surname_3",
                    new HashSet<Book>(bookList.subList(2,3))),
            new Author(4,"name_4","surname_4",
                    new HashSet<Book>(bookList.subList(3,4))),
            new Author(5,"name_5","surname_5",
                    new HashSet<Book>(bookList.subList(4,5)))
    ));

    @Override
    public List<Author> addAuthor(int id, String fName, String lName) {
        Author author =new Author(id,fName,lName);
        authorList.add(author);
        return authorList;
    }

    @Override
    public List<Author> deleteAuthor(int id) {
        authorList.removeIf(n->n.getId()==id);
        return authorList;
    }

    @Override
    public List<Book> addBook(int id, String title, String genre, String description, int rate) {
        Book book=new Book(id,title, genre,description,rate);
        bookList.add(book);
        return null;
    }

    @Override
    public List<Book> deleteBook(int id) {
        bookList.removeIf(book -> book.getId()==id);
        return bookList;
    }

    @Override
    public void addBookToAuthor(int authorId, Book book) {
        authorList.stream()
                .filter(n->n.getId()==authorId)
                .findAny()
                .orElseThrow(()->new RuntimeException("Item not found"))
                .setBook(book);

    }

    @Override
    public List<Book> sortedBookByAuthor(int authorId) {
        return authorList.stream()
                .filter(n->n.getId()==authorId)
                .flatMap(n->n.getBook().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> sortedBookByGenre(String genre) {
        return bookList.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public Book updateBook(int bookId, int updateBookId) {
        bookList.stream()
                .filter(book -> book.getId()==bookId)
                .forEach(book -> book.setId(updateBookId));
        return bookList.stream().filter(book -> book.getId()==updateBookId)
                .findAny().orElseThrow(NotFoundExceptions::new);
    }

    @Override
    public Author updateAuthor(int authorId, int updateAuthorId) {
        authorList.stream()
                .filter(n->n.getId()==authorId)
                .forEach(a->a.setId(updateAuthorId));
        return authorList.stream().filter(a->a.getId()==updateAuthorId)
                .findAny().orElseThrow(NotFoundExceptions::new);
    }
}
