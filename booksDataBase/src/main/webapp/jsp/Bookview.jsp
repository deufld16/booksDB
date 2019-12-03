<%-- 
    Document   : Bookview
    Created on : 02.12.2019, 08:45:45
    Author     : Lukas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book View</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <script src="javascript/database.js" type="text/javascript"></script>
    </head>
    <body id="bodyStyle">
        <h1>Book View</h1>
        <form action="Bookscontroller" method="post">
            <input type="hidden" name="sort" value="sort">
            Sort by:
            <select id="comboBox" name="sortCriteria" onchange="this.form.submit()">
                <c:choose>
                    <c:when test="${sessionScope.sort == 'Title'}">
                        <option selected="true">Title</option>
                        <option>Author</option>
                        <option>Price</option>
                    </c:when>
                    <c:when test="${sessionScope.sort == 'Author'}">
                        <option>Title</option>
                        <option selected="true">Author</option>
                        <option>Price</option>
                    </c:when>
                    <c:when test="${sessionScope.sort == 'Price'}">
                        <option>Title</option>
                        <option>Author</option>
                        <option selected="true">Price</option>
                    </c:when>
                    <c:otherwise>
                        <option>Title</option>
                        <option>Author</option>
                        <option>Price</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </form>
        <br style="margin-bottom: 5px">
        <form method="Post" action="Bookscontroller">
            <c:choose>
                <c:when test="${sessionScope.radioButton == 'author'}">
                    <input type="radio" checked="true" id="author"name="filterCriteria" value="author" >
                    <label for="author">Author</label>
                    <input type="radio" name="filterCriteria" id="title" value="title"  onselect="">
                    <label for="title">Title</label>
                </c:when>
                <c:otherwise>
                    <input type="radio" id="author"name="filterCriteria" value="author" >
                    <label for="author">Author</label>
                    <input type="radio" checked="true" name="filterCriteria" id="title" value="title"  onselect="">
                    <label for="title">Title</label>
                </c:otherwise>
            </c:choose>
            <input id="filterText" type="text" id="filter" name="filter" value="" />
            <input type="submit" value="übernehmen" name="filterBtn"/>
            <input type="submit" value="entfernen" name="filterBtn"/>
        </form>
        <table>
            <th width="30%">Title</th>
            <th width="30%">Author(s)</th>
            <th width="30%">Price</th>
                <c:forEach var="book" items="${sessionScope.books}">
                <tr>
                    <td style="width: 30%">${book.title}</td>
                    <td style="width: 30%">
                        <c:forEach var="author" items="${applicationScope.bookMap.get(book)}">
                            <p>${author}</p>
                        </c:forEach>
                    </td>
                    <td style="width: 30%">${book.price} €</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
