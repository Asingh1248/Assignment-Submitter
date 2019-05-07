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
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author animesh
 */
public class FacultyLogin extends HttpServlet {
    Integer Facultyid;
    

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
        String username=request.getParameter("Faculty_Username");
        String password=request.getParameter("Faculty_Password");
//        out1.print(username);
//        out1.println(password);
        
        try 
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost/OnlineAssignmentSubmission ?useSSL=false","root","root");
            
           String query="Select * from Faculty where Faculty_name=? and password=?";
            
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
         

            
            //Problem is the Copy Paste url and driver
            //Going in if only and rs.next()
            if(rs.next())
            {
                  Facultyid=rs.getInt(1);
//                  out1.println(Facultyid);
                  session.setAttribute("Facultyid",Facultyid);
//                  out1.println("Username and Password matched");
                  response.sendRedirect("FacultyDashboard.jsp");
                  return;
                
            }
            
            else{
            
                out1.println("Username and Password did not matched<br>Sorry Try Again");  
            }
               
            
            
        } 
        catch (Exception e) {
            out1.print(e);
            
        }
        
        
        
        
        
        
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FacultyLogin</title>");            
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
