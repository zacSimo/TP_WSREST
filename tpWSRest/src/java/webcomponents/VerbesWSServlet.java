/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcomponents;

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
@WebServlet(name = "VerbesWSServlet", urlPatterns = "/VerbesWSServlet")
public class VerbesWSServlet extends HttpServlet{
    
    final static String METHOD_GET = "GET";
    final static String METHOD_POST = "POST";
    final static String METHOD_PUT = "PUT";
    final static String METHOD_DELETE = "DELETE";
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext sc = config.getServletContext();
        sc.log( "Demarrage servlet TestServlet" );
        System.out.print(sc.toString());

}
    
  public void reportType(String requestType, HttpServletResponse response) throws IOException, ServletException{
    // set the MIME type
      //application/json ; application/xml
    response.setContentType("text/html;charset=UTF-8");

    // use this to print to browser
    PrintWriter out = response.getWriter();

    out.println("\nBONJOUR ET BIENVENUE \n");
    out.println("Your request type: " + requestType);
  
  }     

// doGet() - 
    @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException{
    reportType("doGet", response);
  }
  
  // doPost() 
    @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    reportType("doPost", response);
  }
  
  // doPut() 
    @Override
  public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    reportType("doPut", response);
  }
  
  // doDelete() 
    @Override
  public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    reportType("doDelete", response);
  }
  

    
  

}
