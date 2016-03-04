/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zac
 */
@WebServlet(name = "BiblioServelet", urlPatterns = {"/BiblioServelet"})
public class BiblioServelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    final static String METHOD_GET_LIVRE = "GET (LIVRE)";
    final static String METHOD_POST_LIVRE = "POST (LIVRE)";
    final static String METHOD_PUT_LIVRE = "PUT (LIVRE)";
    final static String METHOD_DELETE_LIVRE = "DELETE (LIVRE)";
    
    final static String METHOD_GET_BIBLIO = "GET (BIBLIO)";
    final static String METHOD_POST_BIBLIO  = "POST (BIBLIO)";
    final static String METHOD_PUT_BIBLIO  = "PUT (BIBLIO)";
    final static String METHOD_DELETE_BIBLIO  = "DELETE (BIBLIO)";
    
     @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();
        sc.log( "Demarrage servlet TestServlet" );
        System.out.print(sc.toString());

    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("Servlet BiblioServelet at " + request.getContextPath());
            
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
    //@Override
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("Je suis dans GET ");
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
    //@Override
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("Je suis dans POST ");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    
    //@Override
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("Je suis dans PUT ");
        processRequest(request, response);
    }
    
    //@Override
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter pw = response.getWriter();
        pw.println("Je suis dans DELETE ");
        processRequest(request, response);
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String method = req.getMethod(); 
        
        String act = req.getParameter("action");
        PrintWriter pw = resp.getWriter();
        pw.println("Je suis dans SERVICE, Methode recue : "+method+" ACTION : "+act);
        System.out.println("Je suis dans SERVICE, Methode recue : "+method+" ACTION : "+act);
        switch (act.toUpperCase()) {
            case METHOD_GET_LIVRE:
                doGet(req, resp);
                break;
            case METHOD_POST_LIVRE:
                doPost(req, resp);
                break;
            case METHOD_PUT_LIVRE:
                doPut(req, resp);
                break;
            case METHOD_DELETE_LIVRE:
                doDelete(req, resp); 
                break;
            case METHOD_GET_BIBLIO:
                doGet(req, resp);
                break;
            case METHOD_POST_BIBLIO:
                doPost(req, resp);
                break;
            case METHOD_PUT_BIBLIO:
                doPut(req, resp);
                break;
            case METHOD_DELETE_BIBLIO:
                doDelete(req, resp); 
                break;    
            default:
                // Servlet doesn't currently support 
                // other types of request.
                String errMsg = "Method Not Supported";
                resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg); 
                break;
        }
        
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
