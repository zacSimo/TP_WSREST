/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import dao.BibliothequeDAO;
import dao.LivreDAO;
import entities.Bibliotheque;
import entities.Livre;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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
    
    @EJB
    private LivreDAO livreDao;
    
    @EJB
    private BibliothequeDAO bibliothequeDao;
    
    final static String METHOD_GET_LIVRE = "GET (LIVRE)";
    final static String METHOD_POST_LIVRE = "POST (LIVRE)";
    final static String METHOD_PUT_LIVRE = "PUT (LIVRE)";
    final static String METHOD_DELETE_LIVRE = "DELETE (LIVRE)";
    final static String METHOD_GET_ALL_LIVRE = "GET ALL (LIVRE)";
    
    final static String METHOD_GET_BIBLIO = "GET (BIBLIO)";
    final static String METHOD_POST_BIBLIO  = "POST (BIBLIO)";
    final static String METHOD_PUT_BIBLIO  = "PUT (BIBLIO)";
    final static String METHOD_DELETE_BIBLIO  = "DELETE (BIBLIO)";
    final static String METHOD_GET_ALL_BIBLIO  = "GET ALL (BIBLIO)";
    
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
            String titre, auteurs, isbn, nomBiblio, adresse;
            Date  dateParution, dateCreation;
            int idLivre, idBiblio;
            Bibliotheque bib;
            Livre livre;
            
            String action = request.getParameter("action");
            
            switch (action.toUpperCase()) {
                
            case METHOD_GET_LIVRE:
                idLivre = new Integer(request.getParameter("idLivre"));
                livre = livreDao.getLivre(idLivre);
                if(livre != null){
                    out.println("Servlet BiblioServelet at " + request.getContextPath()+" LIVRE demandé : ID "+livre.getId()+" Titre :"+ livre.getTitre());
                }else{
                out.println("Servlet BiblioServelet at " + request.getContextPath()+" LIVRE demandé INTROUVABLE ...");
                }
                break;
            
            case METHOD_GET_ALL_LIVRE:
                List<Livre> lstL = livreDao.getAllLivres();
                for (Livre livr : lstL){
                    out.println("\n id :"+ livr.getId()+" titre : "+livr.getTitre()+" auteurs : "+livr.getAuteurs()+ " isbn : "+livr.getIsbn());
                }
                
                break;
            case METHOD_POST_LIVRE://creation nouveau livre
                titre = request.getParameter("titre");
                auteurs = request.getParameter("auteurs");
                isbn = request.getParameter("isbn");
                dateParution = new Date(request.getParameter("dateParution"));
                idBiblio = new Integer(request.getParameter("idBibliotheque"));
                
                if ((bib=bibliothequeDao.getBibliotheque(idBiblio))!=null) {
                    livre = new Livre(titre, auteurs, isbn, dateParution, bib);
                    livreDao.postLivre(livre);
                     out.println("Servlet BiblioServelet at " + request.getContextPath()+" LIVRE CREER ID : "+livre.getId()+" Titre : "+livre.getTitre());
                }else{
                    // IDENTIFIANT bibliotheque inconnue... veuillez reessayez
                    // return to newLivre.xhtml
                    out.println("Servlet BiblioServelet at " + request.getContextPath()+" IDENTIFIANT bibliotheque inconnue... : "+idBiblio);
                }
                break;
                
            case METHOD_PUT_LIVRE: //MAJ livre
                idLivre = new Integer(request.getParameter("idLivre"));
                titre = request.getParameter("titre");
                auteurs = request.getParameter("auteurs");
                dateParution = new Date(request.getParameter("dateParution"));
                isbn = request.getParameter("isbn");
                //on desire changer de bibliotheque
                if (request.getParameter("idBibliotheque")!=null) {
                    //on controlle l'existance de la biblio
                    
                    if ((bib=bibliothequeDao.getBibliotheque(new Integer(request.getParameter("idBibliotheque"))))!=null) {
                        //si elle existe on insere les nouvelles MAJ
                        livre = new Livre(titre, auteurs, isbn, dateParution, bib);
                        livre.setId(idLivre);
                        livreDao.putLivre(livre);
                    }
                    
                } else{
                            //on fait la MAJ avec les nouveaux parametres
                        livre = new Livre();
                        livre.setId(idLivre);
                        livre.setTitre(titre);
                        livre.setAuteurs(auteurs);
                        livre.setDateParution(dateParution);
                        livre.setIsbn(isbn);
                        livreDao.putLivre(livre);
                        out.println("Servlet BiblioServelet at " + request.getContextPath()+" TITRE : "+titre);
                    }
                
                break;
            case METHOD_DELETE_LIVRE: //suppression livre
                idLivre = new Integer(request.getParameter("idLivre"));
                livreDao.deleteLivre(idLivre);
                out.println("Servlet BiblioServelet at " + request.getContextPath()+" LIVRE "+idLivre+"SUPPRIME  ");
                break;
                
            case METHOD_GET_BIBLIO://get biblio
                idBiblio = new Integer(request.getParameter("idBibliotheque"));
                bib = bibliothequeDao.getBibliotheque(idBiblio);
                if(bib != null){
                    out.println("Servlet BiblioServelet at " + request.getContextPath()+" BIBILOTHEQUE demandee  "+ bib.getNom());
                } else{
                    out.println("Servlet BiblioServelet at " + request.getContextPath()+" BIBILOTHEQUE demandee INTROUVABLE");
                }
                break;
            case METHOD_GET_ALL_BIBLIO: //get all biblio
                List<Bibliotheque> lstB = bibliothequeDao.getAllBibliotheques();
                out.println("Servlet BiblioServelet at " + request.getContextPath()+" BIBILOTHEQUES  ");
                for(Bibliotheque b : lstB){
                    out.write("\nid bib: "+b.getId()+" nom : "+b.getNom()+" adr : "+b.getAdresse()+" date c: "+b.getDateCreation().toString());
                }    
                break;
            case METHOD_POST_BIBLIO: //creation nouvelle bibliotheqe
                nomBiblio = request.getParameter("nomBiblio");
                adresse = request.getParameter("adresse");
                dateCreation = new Date(request.getParameter("dateCreation"));
                bib = new Bibliotheque(nomBiblio, adresse, dateCreation);
                bibliothequeDao.postBibliotheque(bib);
              
//                    request.setAttribute("biblio", bib);
//                    request.setAttribute("allbiblio", bibliothequeDao.getAllBibliotheques());
//                    request.getRequestDispatcher("./newBibliotheque.xhtml").forward(request, response);
                //}catch(Exception e){}
                
                out.println("Servlet BiblioServelet at " + request.getContextPath()+" Bibliotheque créée : "+bib.getNom());
                break;
                
            case METHOD_PUT_BIBLIO:// MAJ bibliotheque
                idBiblio = new Integer(request.getParameter("idBibliotheque"));
                nomBiblio = request.getParameter("nomBiblio");
                adresse = request.getParameter("adresse");
                String date = request.getParameter("dateCreation");
                bib = bibliothequeDao.getBibliotheque(idBiblio);
                if(bib != null){  
                    
                   if(!request.getParameter("nomBiblio").isEmpty())
                       bib.setNom(nomBiblio);     
                   if(!request.getParameter("adresse").isEmpty())
                       bib.setAdresse(adresse);
                   if(!request.getParameter("dateCreation").isEmpty()){ 
                       try{
                           bib.setDateCreation(new Date(date));
                       }catch(IllegalArgumentException ille){
                           out.println(ille.getMessage());
                       }
                   }
                   bibliothequeDao.putBibliotheque(bib);
                }else{
                    out.println("Servlet BiblioServelet at " + request.getContextPath()+" Bibliotheque INNEXHISTANTE : "+bib.getNom());
                }
                
//                else if((date==null) && (adresse==null)){
//                    bib = new Bibliotheque(nomBiblio);
//                }else if(date==null) bib = new Bibliotheque(nomBiblio, adresse);
//                dateCreation = new Date(request.getParameter("dateCreation"));
//                bib = new Bibliotheque(nomBiblio, adresse, dateCreation);
//                bib.setId(idBiblio);
//                if(bibliothequeDao.getBibliotheque(idBiblio)!=null){
//                    bibliothequeDao.putBibliotheque(bib);
//                    out.println("Servlet BiblioServelet at " + request.getContextPath()+" Bibliotheque MAJ : "+bib.getNom());
//                
//                }
//                    out.println("Servlet BiblioServelet at " + request.getContextPath()+" Bibliotheque INNEXHISTANTE : "+bib.getNom());

                break;
            case METHOD_DELETE_BIBLIO://suppression bibliotheque
                idBiblio = new Integer(request.getParameter("idBibliotheque"));
                try{
                    bibliothequeDao.deleteBibliotheque(idBiblio);
                }catch(Exception e){}
                
                out.println("Servlet BiblioServelet at " + request.getContextPath()+" BIBLIO "+idBiblio+" SUPPRIMEE ");
                break;    
            default:
                // Servlet doesn't currently support 
                // other types of request.
                String errMsg = "Method Not Supported";
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg); 
                break;
        }
            
      
            
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
        //pw.println("Je suis dans POST ");
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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String method = request.getMethod(); 
        
        String act = request.getParameter("action");
        PrintWriter pw = response.getWriter();
        //pw.println("Je suis dans SERVICE, Methode recue : "+method+" ACTION : "+act);
        System.out.println("Je suis dans SERVICE, Methode recue : "+method+" ACTION : "+act);
        switch (act.toUpperCase()) {
            case METHOD_GET_LIVRE:
                doGet(request, response);
                break;
            case METHOD_POST_LIVRE:
                doPost(request, response);
                break;
            case METHOD_PUT_LIVRE:
                doPut(request, response);
                break;
            case METHOD_DELETE_LIVRE:
                doDelete(request, response);
                break;
            case METHOD_GET_BIBLIO:
                doGet(request, response);
                break;
            case METHOD_POST_BIBLIO:
                doPost(request, response);
                break;
            case METHOD_PUT_BIBLIO:
                doPut(request, response);
                break;
            case METHOD_DELETE_BIBLIO:
                doDelete(request, response); 
                break; 
            case METHOD_GET_ALL_BIBLIO:
                doGet(request, response); 
                break; 
            case METHOD_GET_ALL_LIVRE:
                doGet(request, response); 
                break;
            default:
                // Servlet doesn't currently support 
                // other types of request.
                String errMsg = "Method Not Supported";
                response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg); 
                break;
        }
        
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
