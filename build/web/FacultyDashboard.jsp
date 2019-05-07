<%-- 
    Document   : FacultyDashboard
    Created on : 27 Oct, 2018, 12:34:23 PM
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
                max-width: 500px;
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
            <center>
        <h2>Faculty DashBoard</h2>
        <table border="1" width="50%">
            <tr>
                <td>AddStudent</td>
                <td><a href="AddStudent.jsp">AddStudent</a></td>
            </tr>
            <tr>
                <td>GiveAssignment</td>
                <td><a href="AssignmentGenerate.jsp">AssignmentGenerate</a></td>
            </tr>
            
            <tr>
                <td>ViewStudentAssignment</td>
                <td><a href="ViewStudent.jsp">ViewStudentAssignment</a></td>
            </tr>
        </table>
            </center>
        </div>
    </body>
</html>
