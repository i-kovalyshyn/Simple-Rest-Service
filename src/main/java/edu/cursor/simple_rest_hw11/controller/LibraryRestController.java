package edu.cursor.simple_rest_hw11.controller;

import edu.cursor.simple_rest_hw11.database.IService;

import edu.cursor.simple_rest_hw11.object.Author;
import edu.cursor.simple_rest_hw11.object.Book;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
//@AllArgsConstructor

public class LibraryRestController {

    private final IService iService;

    public LibraryRestController(IService iService) {
        this.iService = iService;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public List<Book> sortedBookByAuthor(@PathVariable("id") int id) {
        return iService.sortedBookByAuthor(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{genre}", method = RequestMethod.GET)
    public List<Book> sortedBookByGenre(@PathVariable("genre") String genre) {
        return iService.sortedBookByGenre(genre);
    }

    @ResponseBody
    @PostMapping("/author")
    public List<Author> addAuthor(
            @RequestParam int authorId,
            @RequestParam String authorFName,
            @RequestParam String authorLName) {
        return iService.addAuthor(authorId, authorFName, authorLName);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/book")
    public List<Book> addBook(
            @RequestParam int bookId,
            @RequestParam String bookTitle,
            @RequestParam String bookGenre,
            @RequestParam String bookDescription,
            @RequestParam int bookRate) {
        return iService.addBook(bookId, bookTitle, bookGenre, bookDescription, bookRate);
    }

    @ResponseBody
    @PostMapping("/author/book")
    public Map<Integer, Book> addBookToAuthor(
            @RequestParam int authorId,
            @RequestParam int bookId,
            @RequestParam String bookTitle,
            @RequestParam String bookGenre,
            @RequestParam String bookDescription,
            @RequestParam int bookRate
    ) {
        Map<Integer, Book> map = new LinkedHashMap<>();
        Book bookX = new Book(bookId, bookTitle, bookGenre, bookDescription, bookRate);
        iService.addBookToAuthor(authorId, bookX);
        map.put(authorId, bookX);
        return map;

    }

    @ResponseBody
    @DeleteMapping("/author/{id}")
    public List<Author> deleteAuthor(@PathVariable("id") int authotId) {
        return iService.deleteAuthor(authotId);
    }

    @ResponseBody
    @DeleteMapping("/book/{id}")
    public List<Book> deleteBook(@PathVariable("id") int bookId) {
        return iService.deleteBook(bookId);
    }

    @ResponseBody
    @PatchMapping("author/id")
    public Author updateAuthor(@RequestParam int updateId, @PathVariable("id") int id) {
        return iService.updateAuthor(id, updateId);
    }

    @ResponseBody
    @PatchMapping("book/{id}")
    public Book upadateBook(@RequestParam ("id")int updateId, @PathVariable("id")int id){
        return iService.updateBook(id,updateId);
    }
}
