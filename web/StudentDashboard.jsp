<%-- 
    Document   : StudentDashboard
    Created on : 29 Oct, 2018, 6:58:32 PM
    Author     : animesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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
                max-width: 700px;
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
        <sql:setDataSource var="OnlineAssignmentDataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/OnlineAssignmentSubmission?useSSL=false" user="root" password="root" scope="page"/>
        <sql:query var="ViewAssignmentQuery" dataSource="${OnlineAssignmentDataSource}" scope="page">
            select Assignment_id,Assignment_topic,Submission_date,FileUpload from Assignment;
        </sql:query>
            <div class="card">
            <table border="1">
                <tr>
                    <th>Assignment Topic</th>
                    <th>Submission Date</th>
                    <th>Question File</th>
                    <th>Upload the Assignment</th>
                </tr>
                <c:forEach var="row" items="${ViewAssignmentQuery.rows}">
                    <tr>
                        <td><c:out value="${row.Assignment_topic}"/></td>
                        <td><c:out value="${row.Submission_date}"/></td>
                      
                        <td>
                            
                            
                            <a href="${pageContext.servletContext.contextPath }/ViewAssignment?Assignment_id=${row.Assignment_id}">Download Assignment File<a/><br>
                            
                                    
                        </td>
                        <td><a href="UploadStudentAssignment.jsp">Upload the Assignment <c:out value="${row.Assignment_topic}"/></a></td>
                   
                    </tr>
                </c:forEach>
            </table>
            </div>
    </body>
</html>
