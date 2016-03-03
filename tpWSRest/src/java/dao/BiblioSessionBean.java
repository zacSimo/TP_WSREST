/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Bibliotheque;
import entities.Livre;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Zac
 */
@Stateless

public class BiblioSessionBean implements LivreDAO, BibliothequeDAO{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void postLivre(Livre livre) {
        em.persist(livre);
    }

    @Override
    public Livre getLivre(int id) {
        return em.find(Livre.class, id);
    }

    @Override
    public List<Livre> getAllLivres() {
        Query q = em.createNamedQuery("SELECT l FROM Livre l;");
        return q.getResultList();
    }

    @Override
    public void putLivre(Livre livre) {
        em.merge(livre);
    }

    @Override
    public void deleteLivre(int id) {
        em.remove(id);
    }

    @Override
    public void patchLivre(Livre livre) {
        em.merge(livre);
    }

    @Override
    public void postBibliotheque(Bibliotheque bibliotheque) {
        em.persist(bibliotheque);
    }

    @Override
    public Bibliotheque getBibliotheque(int id) {
        return em.find(Bibliotheque.class, id);
    }

    @Override
    public List<Bibliotheque> getAllBibliotheques() {
        Query q = em.createNamedQuery("SELECT b FROM Bibliotheque b");
        return q.getResultList();
    }

    @Override
    public void putBibliotheque(Bibliotheque bibliotheque) {
        em.merge(bibliotheque);
    }

    @Override
    public void deleteBibliotheque(int id) {
        em.remove(id);
    }

    @Override
    public void patchBibliotheque(Bibliotheque bibliotheque) {
        em.merge(bibliotheque);
    }
    
}
