/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Author;
import beans.Book;
import database.DB_Access;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Lukas
 */
@WebServlet(name = "Bookscontroller", urlPatterns = {"/Bookscontroller"})
public class Bookscontroller extends HttpServlet {

    private Map<Book, List<Author>> authorsFromBooks = new HashMap<>();
    private List<Book> allBooks = new LinkedList<>();
    private List<Book> filteredBooks = new LinkedList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("sortCriteria") != null) {
            String sortType = request.getParameter("sortCriteria");
            filter(sortType);
            request.getSession().setAttribute("sort", sortType);
        } else if (request.getParameter("filter") != null) {
            DB_Access dba = new DB_Access();
            if (request.getParameter("filterBtn").equalsIgnoreCase("entfernen")) {
                authorsFromBooks.clear();
                System.out.println(allBooks);
                filteredBooks = new LinkedList<>(allBooks);
                for (Book allBook : filteredBooks) {
                    try {
                        authorsFromBooks.put(allBook, dba.getAuthorFromBook(allBook));
                    } catch (Exception ex) {
                        Logger.getLogger(Bookscontroller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               request.getSession().setAttribute("books", filteredBooks);
            } else {
                String sortedBy = request.getParameter("filterCriteria");
                String filterString = request.getParameter("filter");
                if (sortedBy.equalsIgnoreCase("title")) {
                    filteredBooks.clear();
                    filteredBooks = allBooks
                            .stream()
                            .filter(s -> s.getTitle().toUpperCase().contains(filterString.toUpperCase()))
                            .collect(Collectors.toList());
                    filter(request.getSession().getAttribute("sort") == null ? "Title" : request.getSession().getAttribute("sort").toString());
                    authorsFromBooks.clear();
                    for (Book filteredBook : filteredBooks) {
                        try {
                            authorsFromBooks.put(filteredBook, dba.getAuthorFromBook(filteredBook));
                        } catch (Exception ex) {
                            throw new RuntimeException("error");
                        }
                    }
                    request.getSession().setAttribute("books", filteredBooks);
                } else {
                    filteredBooks.clear();
                    authorsFromBooks.clear();
                    for (Book allBook : allBooks) {
                        try {
                            authorsFromBooks.put(allBook, dba.getAuthorFromBook(allBook));
                        } catch (Exception ex) {
                            Logger.getLogger(Bookscontroller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    for (Book book : authorsFromBooks.keySet()) {
                        for (Author author : authorsFromBooks.get(book)) {
                            if ((author.getLastname() + " " + author.getFirstname()).toUpperCase().contains(filterString.toUpperCase())) {
                                filteredBooks.add(book);
                                break;
                            }
                        }
                    }
                    filter(request.getSession().getAttribute("sort") == null ? "Title" : request.getSession().getAttribute("sort").toString());
                    authorsFromBooks.clear();
                    for (Book filteredBook : filteredBooks) {
                        try {
                            authorsFromBooks.put(filteredBook, dba.getAuthorFromBook(filteredBook));
                        } catch (Exception ex) {
                            throw new RuntimeException("error");
                        }
                    }
                    request.getSession().setAttribute("radioButton", sortedBy);
                    request.getSession().setAttribute("books", filteredBooks);
                }
            }
        }

        request.getRequestDispatcher("jsp/Bookview.jsp").forward(request, response);

    }

    private void filter(String type) {
        if (type.equalsIgnoreCase("Title")) {
            filteredBooks.sort(Comparator.comparing(Book::getTitle));
        } else if (type.equalsIgnoreCase("Author")) {
            for (Book book : authorsFromBooks.keySet()) {

            }
        } else {
            filteredBooks.sort(Comparator.comparing(Book::getPrice).reversed());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext sc = this.getServletContext();
        DB_Access dba = new DB_Access();
        try {
            allBooks = dba.getAllBooks();
            for (Book allBook : allBooks) {
                authorsFromBooks.put(allBook, dba.getAuthorFromBook(allBook));
            }
            filteredBooks = new LinkedList<>(allBooks);
            sc.setAttribute("bookMap", authorsFromBooks);
            sc.setAttribute("books", filteredBooks);
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured: Message:" + ex.toString());
        }
    }

}
