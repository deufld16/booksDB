/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Author;
import beans.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class DB_Access {

    private DB_PStatPool pStatPool = DB_PStatPool.getInstance();
    private DB_ConnectionPool connection = DB_ConnectionPool.getInstance();
    public List<Book> getAllBooks() throws Exception {
        Statement stm =  connection.getConnection().createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM book");
        List<Book> bookList = new LinkedList<>();
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String url = rs.getString("url");
            double price = rs.getDouble("price");
            int publisher_id = rs.getInt("publisher_id");
            String isbn = rs.getString("isbn");

            Book b = new Book(book_id, title, url, price, publisher_id, isbn);
            bookList.add(b);
        }
        return bookList;
    }
    
    public List<Author> getAuthorFromBook(Book b) throws Exception{
        PreparedStatement prepStatement = pStatPool.getPStat(DB_StmtType.GET_AUTHORS_FROM_BOOK);
        prepStatement.setInt(1, b.getBook_id());
        ResultSet rs = prepStatement.executeQuery();
        List<Author> allAuthor = new LinkedList<>();
        while(rs.next()){
            int authorid = rs.getInt("author_id");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String url = rs.getString("url");
            allAuthor.add(new Author(authorid, firstname, lastname, url));
        }
        return allAuthor;
    }
    
    public static void main(String[] args) {
        DB_Access dba = new DB_Access();
        try {
            List<Book> books = dba.getAllBooks();
            for (Book book : books) {
                System.out.println(dba.getAuthorFromBook(book));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
        
    }
}


