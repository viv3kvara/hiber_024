<%-- 
    Document   : deluser
    Created on : 3 May, 2023, 8:43:34 PM
    Author     : Denis Ruparel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>delete user </title>
    </head>
    <body>
        <h1>Delete User...</h1>
        <form action="DeleteUser" method="post">  
            ID:<input type="number" name="id"/><br/><br/>
            <input type="submit" value="Delete User"/><br/><br/>
        </form>
        <h3><a href="index.html">Back to Main Menu</a></h3>
    </body>
</html>
