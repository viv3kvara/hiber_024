<%-- 
    Document   : upuser
    Created on : 3 May, 2023, 8:51:06 PM
    Author     : Denis Ruparel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User</title>
    </head>
    <body>
        <h1>Update User...!!</h1>
        <form action="UpdateUser" method="post">  
            ID:<input type="number" name="id"/><br/><br/>
            First Name:<input type=text name="fname"/><br/><br/>
            Last Name:<input type=text name="lname"/><br/><br/>
            City:<input type=text name="city"/><br/><br/>
            Branch:<input type="text" name="branch"/><br/><br/>
            <input type="submit" value="Update User"/><br/><br/>
        </form>
        <h3><a href="index.html">Back to Main Menu</a></h3>
    </body>
</html>
