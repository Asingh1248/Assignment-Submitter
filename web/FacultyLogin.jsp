<%-- 
    Document   : FacultyLogin
    Created on : 27 Oct, 2018, 12:25:17 PM
    Author     : animesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .card {
                float:right;
                z-index: 1;
                background: #FFFFFF;
                max-width: 360px;
                margin: 0 auto 100px;
                margin-top: 100px;
                margin-right: 100px;
                padding: 45px;
                text-align: center;
                box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
            }
            body{
                height: 600px;
                background-image: url("Images/Blackboard-teacher-vanish-feat.jpg");
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
                 
            }
        </style>
    </head>
    <%@include file="header.html" %>
    <body>
        
        <div class="card">
        <form action="FacultyLogin" method="POST">
            <h2>Faculty Login </h2><br><br>
                Enter the username:<input type="text" name="Faculty_Username" required/><br><br>
                Enter the password:<input type="password" name="Faculty_Password" required/><br><br>
            <input type="submit" value="submit"/>
        </form>
        </div>
           <%@include file="footer.html" %>
    </body>
    
    
</html>
