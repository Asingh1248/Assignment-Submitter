<%-- 
    Document   : UploadStudentAssignment
    Created on : 30 Oct, 2018, 9:06:19 AM
    Author     : animesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <form action="UploadStudentAssignment" method="POST" enctype="multipart/form-data">
           <center>
            <h2>Kindly Submit Your Assignment</h2><br><br>
            Enter the File Name:<input type="text" name="Filename"><br><br>
             <input type="file" name="fileupload" required/><br><br>
             <input type="submit" value="submit">
           </center>
       </form>
   </body>
   <!-- <a href="FacultyLogin.jsp">FacultyLogin</a>-->
</div>  
</html>
