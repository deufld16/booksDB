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
public class Author {
    private int authorid;
    private String firstname;
    private String lastname;
    private String url;

    public Author(int authorid, String firstname, String lastname, String url) {
        this.authorid = authorid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstname, lastname);
    }
    
    
}
