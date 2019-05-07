/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author animesh
 */
public class ViewAssignment extends HttpServlet {
    

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
//        PrintWriter out2 = response.getWriter();
        Blob image = null;
        Blob blob=null;
        Integer id;
        HttpSession session=request.getSession();
        ServletOutputStream out1 = response.getOutputStream();//Step1:OutStream()
        
        try {
            //ViewStudent
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/OnlineAssignmentSubmission ?useSSL=false", "root", "root");
            PreparedStatement stmt = con.prepareStatement("select FileUpload,Assignment_id from Assignment where Assignment_id=?");
            //stmt.setInt(1, Integer.parseInt(request.getParameter("Assignment_id")));
            stmt.setLong(1, Long.valueOf(request.getParameter("Assignment_id")));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                image = rs.getBlob(1);
                id=rs.getInt(2);
                System.out.println(id);
                //Bug its is like Assignment_id we are taking from the Java Null Pointer
                session.setAttribute("Assigment_id",id);
                
                
                //Step2:getBlob();
            } else {

            }

            //Main LOgic
//            response.setContentType("image/jpeg");
            response.setContentType("application/pdf");
            InputStream in = image.getBinaryStream();
            int length = (int) image.length();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize]; //imp Step3: byteArrray
            while ((length = in.read(buffer)) != -1) {
                out1.write(buffer, 0, length);
            }
            in.close();
             
           

        } catch (Exception e) {
               e.printStackTrace();
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewAssignment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAssignment at " + request.getContextPath() + "</h1>");
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
