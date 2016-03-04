<%-- 
    Document   : index
    Created on : 2 mars 2016, 19:52:36
    Author     : Zac
--%>
<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TP Web Service</title>
    </head>
    <% Date date = new Date(); %>
    <body>
        <div id="menuimage" >
            <img src="./resources/images/rect5630.png" />
            
        </div>
        <div id="livremethods">
            <table >
                <tr>
                    <form method="POST" action="BiblioServelet">
                            <td><input type="submit" name="action" value="Post (Livre)"></td>
                            <td><input type="submit" name="action" value="Put (Livre)"></td>
                            <td><input type="submit" name="action" value="Delete (Livre)"></td>
                        </form> 
                        <form method="GET" action="BiblioServelet" >
                            <td></td><td></td>
                            <td><input type="submit" name="action" value="get (Livre)"></td>
                            <td><input type="text" name="action" size="25" maxlength="30" value="getLivreById"></td>
                        </form> 
                        <td></td><td></td><td></td><td></td><td></td>
                        <form method="POST" action="BiblioServelet">
                            <td><input type="submit" name="action" value="Post (biblio)"></td>
                            <td><input type="submit" name="action" value="Put (biblio)"></td>
                            <td><input type="submit" name="action" value="Delete (biblio)"/></td>
                        </form> 
                        <form method="GET" action="BiblioServelet" >
                            <td></td><td></td>
                            <td><input type="submit" name="action" value="get (biblio)"></td>
                            <td><input type="text" name="action" size="25" maxlength="30" value="getbiblioById"/></td>
                        </form> 
                </tr>
            </table>
        </div>
      
        <div id="">
            
        </div>
        <div id=""></div>
        
    </body>
</html>

