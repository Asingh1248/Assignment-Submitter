/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author animesh
 */
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class UploadStudentAssignment extends HttpServlet {

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
        String message=null;
        HttpSession session=request.getSession();
        int Assigment_id=Integer.parseInt(session.getAttribute("Assigment_id").toString());
        out1.println("Assigment_id"+Assigment_id);
        //Error:from ViewAssignment.java Current Session Assignment ID is Taking always at tha start hence
        
        int Student_id=Integer.parseInt(session.getAttribute("Studentid").toString());
        out1.println("Studentid"+Student_id);
        
        String FileName=request.getParameter("Filename");
        
        
        Date date=new Date();
        java.sql.Date sqlDate= new java.sql.Date(date.getTime());
        
        InputStream input=null;
        Part filePart=request.getPart("fileupload");
        
        if (filePart != null) {
            input = filePart.getInputStream();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost/OnlineAssignmentSubmission ?useSSL=false","root","root");
            String query1="select AssignmentFileUpload from StudentUploadAssignment where Student_id='"+Student_id+"' and AssignmentGiven_id='"+Assigment_id+"'";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query1);
            out1.println("First Part Executed");
            
            if(rs!=null){
              String query2="delete from StudentUploadAssignment where Student_id='"+Student_id+"' and AssignmentGiven_id='"+Assigment_id+"' ";
              Statement stmt1=con.createStatement();
              int r=stmt1.executeUpdate(query2);
              out1.println("No of Rows Deleted"+r);
            }
            
           
            String query="Insert into  StudentUploadAssignment(AssignmentGiven_id,SubmittedDate,AssignmentFileUpload,Student_id,Filename) values(?,?,?,?,?)";
            
            PreparedStatement ps=con.prepareStatement(query);
            out1.println("Second Part");
            ps.setInt(1,Assigment_id);
            ps.setDate(2, sqlDate);
            
            //Bug 2:Always Inserting the Same File At the Start fisrt File
            if(input!=null){
             ps.setBlob(3,input);
                
            }
            ps.setInt(4,Student_id);
            ps.setString(5,FileName);
            
            int row = ps.executeUpdate();
            if (row > 0) {
                 message = "File uploaded and saved into database";
            }
//            session.invalidate();
           
//            String query3="select StudentUploadAssignment_id from StudentUploadAssignment where Student_id='"+Student_id+"'";
//            Statement stmt3=con.createStatement();
//            ResultSet rs3=stmt3.executeQuery(query3);
            
//            if(rs3.next())
//            {
//                int i=rs3.getInt("StudentUploadAssignment_id");
//                out1.println("StudentUploadAssignment_id"+i);
//                session.setAttribute("StudentUploadAssignment_id",i);
//               
//            }    
            
         
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadStudentAssignment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + message + "</h1>");
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
