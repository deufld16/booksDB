/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Florian
 */
public interface DB_Config {
    public static final String DB_NAME = "booksdb"; 
    public static final String DB_USER = "postgres"; 
    public static final String DB_PASSWD = "postgres"; 
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/"; 
    public static final String DB_DRIVER = "org.postgresql.Driver"; 
}
