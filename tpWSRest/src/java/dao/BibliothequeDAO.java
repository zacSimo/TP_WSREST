/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Bibliotheque;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zac
 */
@Local
public interface BibliothequeDAO {
     // POST -- insert new Bibliotheque
    public void postBibliotheque(Bibliotheque bibliotheque);
    //GET -- recuperer un Bibliotheque de la base connaissant son id
    public Bibliotheque getBibliotheque(int id);
    //GET -- recuperer tous les Bibliotheques de la base 
    public List<Bibliotheque> getAllBibliotheques();
    //PUT -- MAJ Bibliotheque avec tous les parametres indiqués
    public void putBibliotheque(Bibliotheque bibliotheque);
    //DELETE -- effacement Bibliotheque de la base
    public void deleteBibliotheque(int id);
    //PATCH -- MAJ d'une partie de certaines info de l'objet
    public void patchBibliotheque(Bibliotheque bibliotheque); 
}
