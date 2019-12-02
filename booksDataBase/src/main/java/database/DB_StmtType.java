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
public enum DB_StmtType {
    GET_AUTHORS_FROM_BOOK("SELECT * FROM bookauthor b INNER JOIN author a WHERE b.book_id=?");
    
    private String sqlString;

    private DB_StmtType(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getSqlString() {
        return sqlString;
    }
    
    
}
