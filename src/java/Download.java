/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
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
public class Download extends HttpServlet {
    // size of byte buffer to send file
    private static final int BUFFER_SIZE = 4096; 

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
//        PrintWriter out1 = response.getWriter();
        InputStream inputStream =null;
        String mimeType=null;
        int FileLength=0;
        String fileName=null;
        ServletOutputStream outStream =response.getOutputStream();
        try {
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost/OnlineAssignmentSubmission ?useSSL=false","root","root");
            
            String query3="select StudentUploadAssignment_id from StudentUploadAssignment";
            Statement stmt3=con.createStatement();
            ResultSet rs3=stmt3.executeQuery(query3);
            while (rs3.next()) {
//                int StudentUploadid = rs3.getInt("StudentUploadAssignment_id");
                String sql = "select * from StudentUploadAssignment where StudentUploadAssignment_id =?";
                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setInt(1, StudentUploadid);
                //Very Imp which studentuploadAssignemnt wala to display
                ps.setLong(1, Long.valueOf(request.getParameter("StudentUploadAssignment_id")));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    //gets the File Name and File  blob data
                    fileName = rs.getString("FileName");
                    Blob blob = rs.getBlob("AssignmentFileUpload");
                    inputStream = blob.getBinaryStream();
                    FileLength = inputStream.available();
                
//                out1.println("fileLength ="+FileLength);
//                ServletContext context=getServletContext();
                
                //set the MIME type for the Downloaded File
                
//                 mimeType=context.getMimeType(fileName);
//                if(mimeType==null){
//                    mimeType="application/octect-stream";
//                    
//                }
//            }   
//            

                
                // set content properties and header attributes for the response
                
                response.setContentType("application/pdf");
//                response.setContentLength(FileLength);
//                
//                String headerKey="Content-Disposition";
//                String headerValue=String.format("attachment; filename=\"%s\"", fileName);
//                response.setHeader(headerKey,headerValue);
                
                //writes the file to client
                //Some Error:SQL Error: getWriter() has already been called for this response<!DOCTYPE html>
                
                
                byte[]buffer=new byte[BUFFER_SIZE];
                int byteRead=-1;
                
                while((byteRead=inputStream.read(buffer))!=-1){
                   outStream.write(buffer,0,byteRead);
                }
                
                inputStream.close();
                outStream.close();
         }       
                
            }
            
        } catch (Exception e) {
           //e.printStackTrace();
            response.getWriter().print("SQL Error: " + e.getMessage());
        }
        
        
        try(PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Download</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Download at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e){
            e.printStackTrace();
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
