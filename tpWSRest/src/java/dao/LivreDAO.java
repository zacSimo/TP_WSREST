/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Livre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zac
 */
@Local
public interface LivreDAO {
    // POST -- insert new livre
    public void postLivre(Livre livre);
    //GET -- recuperer un livre de la base connaissant son id
    public Livre getLivre(int id);
    //GET -- recuperer tous les livres de la base 
    public List<Livre> getAllLivres();
    //PUT -- MAJ livre avec tous les parametres indiqués
    public void putLivre(Livre livre);
    //DELETE -- effacement livre de la base
    public void deleteLivre(int id);
    //PATCH -- MAJ d'une partie de certaines info de l'objet
    public void patchLivre(Livre livre); 
}
