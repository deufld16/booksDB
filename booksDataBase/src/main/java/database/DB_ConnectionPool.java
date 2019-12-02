/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Florian
 */
public class DB_ConnectionPool implements DB_Config{

    private static DB_ConnectionPool theInstance = null;
    
    private LinkedList<Connection> conPool = new LinkedList<>();
    private final int MAX_CON = 150;
    private int con_cnt = 0;
    
    public static DB_ConnectionPool getInstance() {
        if (theInstance == null) {
            theInstance = new DB_ConnectionPool();
        }
        return theInstance;
    }

    private DB_ConnectionPool() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Loading DB-driver: " + ex.toString());
        }
    }
    
    public Connection getConnection() throws Exception{
        if(conPool.isEmpty()){
            if(MAX_CON == con_cnt){
                throw new Exception("No connections available - try again later");
            }
            Connection con = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWD);
            con_cnt ++;
            return con;
        }else{
            return conPool.poll();
        }
    }
    
    public void releaseConnection(Connection connection){
       conPool.offer(connection);
    }
}
