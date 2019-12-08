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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </head>
    <body id="bodyStyle">
        <div class="jumbotron">
            <h1>Book View</h1>
        </div>
        <div class="form-container">
            <form action="Bookscontroller" method="post">
                <div class="form-group">
                    <label for="sortCriteria">Sort by:</label>
                    <select class="form-control" id="sort" name="sortCriteria" onchange="this.form.submit()">
                        <option>Title</option>
                        <option>Author</option>
                        <option>Price</option>
                    </select>
                    <script>
                        document.getElementById("sort").value = "${sessionScope.sort}";
                    </script>
                </div>
                <div class="row">
                    <legend class="col-form-label col-sm-2 pt-0">Order</legend>
                    <div class="col-sm-10">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="filterOrder" id="radAufsteigend" value="up" onclick="document.getElementById('sort').form.submit()">
                            <label class="form-check-label" for="radAufsteigend">Up</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="radAbsteigend" name="filterOrder" value="down" onclick="document.getElementById('sort').form.submit()">
                            <label class="form-check-label" for="radAbsteigend">Down</label>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="sort" value="sort">
            </form>
        </div>
        <div class="form-container">
            <form method="Post" action="Bookscontroller">
                <div class="form-group">
                    <div class="row">
                        <legend class="col-form-label col-sm-2 pt-0">Filter By:</legend>
                        <div class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" id="radAuthor" name="filterCriteria" value="author" >
                                <label class="form-check-label" for="radAuthor">Author</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="filterCriteria" id="radTitle" value="title">
                                <label class="form-check-label" for="radTitle">Title</label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="filterText" class="col-sm-2 col-form-label">Filter Criteria:</label>
                        <input class="form-control" id="filterText" type="text" name="filter">

                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary" value="übernehmen" name="filterBtn">Filter</button>
                        <button type="submit" class="btn btn-primary" value="entfernen" name="filterBtn" onclick="document.getElementById('filterText').value = ''; this.form.submit()">Remove</button>
                    </div>
                    <script>
                        if ("${sessionScope.radioButton}" == 'title') {
                            document.getElementById("radTitle").checked = true;
                        } else {
                            document.getElementById("radAuthor").checked = true;
                        }
                        if ("${sessionScope.order}" == 'down') {
                            document.getElementById("radAbsteigend").checked = true;
                        } else {
                            document.getElementById("radAufsteigend").checked = true;
                        }
                        document.getElementById("filterText").value = "${sessionScope.filterString}";
                    </script>
            </form>
        </div>
        <div class="container">
            <table class="table  table-striped">
                <thead  class="thead-dark">
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Author(s)</th>
                        <th scope="col">Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${sessionScope.books}">
                        <tr>
                            <td scope="col">${book.title}</td>
                            <td scope="col">
                                ${applicationScope.bookMap.get(book).toString().substring(1,applicationScope.bookMap.get(book).toString().length()-1)}
                            </td>
                            <td scope="col">${book.price} €</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
