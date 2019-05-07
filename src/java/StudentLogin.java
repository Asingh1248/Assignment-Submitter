/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author animesh
 */
public class StudentLogin extends HttpServlet {
    Integer Studentid;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out1 = response.getWriter(); 
        
        HttpSession session=request.getSession();
        String username=request.getParameter("Student_Username");
        String password=request.getParameter("Student_Password");
        
        try{    
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost/OnlineAssignmentSubmission ?useSSL=false","root","root");
            
           String query="Select * from student where student_name=? and password=?";
            
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next())
            {
                  Studentid=rs.getInt(1);
                  session.setAttribute("Studentid",Studentid);
                  response.sendRedirect("StudentDashboard.jsp");
                  return;
                
            }
            
            else{
            
                out1.println("Username and Password did not matched<br>Sorry Try Again");  
            }
            
        }
        catch (Exception e) {
            out1.print(e);
            
        }
            
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1></h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
