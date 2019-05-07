<%-- 
    Document   : AddStudent
    Created on : 27 Oct, 2018, 2:07:26 PM
    Author     : animesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <style>
        .card {
                position: relative;
                z-index: 1;
                background: #FFFFFF;
                max-width: 360px;
                margin: 0 auto 100px;
                padding: 45px;
                margin-top: 130px;
                text-align: center;
                box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
            }
            body{
                background: -webkit-gradient(linear, left bottom, right top, from(#fc2c77), to(#6c4079));
                background: -webkit-linear-gradient(bottom left, #fc2c77 0%, #6c4079 100%);
                background: -moz-linear-gradient(bottom left, #fc2c77 0%, #6c4079 100%);
                background: -o-linear-gradient(bottom left, #fc2c77 0%, #6c4079 100%);
                background: linear-gradient(to top right, #fc2c77 0%, #6c4079 100%);
              }
    </style>
    </head>
    <body>
        <div class="card">
        <form action="AddStudent" method="POST">
           
                <h2>Add Student</h2>     
            Enter the Student name:<input type="text" name="studentname" required/><br><br>
            Enter the Student mail:<input type="text" name="studentmail" required/><br><br>
            Enter the Student Rollno:<input type="text" name="studentrollno" required/><br><br>
            <input type="submit" value="Add">
            
        </form>
        </div>
    </body>
</html>
