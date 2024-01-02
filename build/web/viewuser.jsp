<%-- 
    Document   : viewuser
    Created on : 3 May, 2023, 8:27:11 PM
    Author     : Denis Ruparel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View User</title>
    </head>
    <body>
         <h1>View User...!!</h1>
        <form action="ViewUser" method="post">  
            ID:<input type="number" name="id"/><br/><br/>
            <input type="submit" value="View user"/><br/><br/>
        </form>
        <form action="ViewAllUser" method="post">  
            <input type="submit" value="View All  User"/><br/><br/>
        </form>
        <h3><a href="index.html">Back to Main Menu</a></h3>
    </body>
</html>
