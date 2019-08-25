package edu.cursor.simple_rest_hw11.object;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data


public class Book {
    private int id;
    private String title;
    private String genre;
    private String description;
    private int rate;
}
