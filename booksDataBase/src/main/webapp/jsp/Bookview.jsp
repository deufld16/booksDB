<%-- 
    Document   : Bookview
    Created on : 02.12.2019, 08:45:45
    Author     : Lukas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book View</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Book View</h1>
        <form method="post">
            <input type="hidden" name="sort" value="sort">
            Sort by:
            <select>
                <option>Title</option>
                <option>Author</option>
                <option>Price</option>
            </select>
        </form>
        <table>
            <th width="30%">Title</th>
            <th width="30%">Author(s)</th>
            <th width="30%">Price</th>
        </table>
    </body>
</html>
