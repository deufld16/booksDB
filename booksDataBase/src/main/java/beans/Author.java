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
    private int rank;

    public Author(int authorid, String firstname, String lastname, String url, int rank) {
        this.authorid = authorid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.url = url;
        this.rank = rank;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
    

    @Override
    public String toString() {
        return String.format("%s %s", firstname, lastname);
    }
    
    
}
