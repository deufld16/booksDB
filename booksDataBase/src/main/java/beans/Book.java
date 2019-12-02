/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Florian
 */
public class Book {
   private int book_id;
   private String title;
   private String url;
   private double price;
   private int publisher_id;
   private String isbn;

    public Book(int book_id, String title, String url, double price, int publisher_id, String isbn) {
        this.book_id = book_id;
        this.title = title;
        this.url = url;
        this.price = price;
        this.publisher_id = publisher_id;
        this.isbn = isbn;
    }
   
   
}
