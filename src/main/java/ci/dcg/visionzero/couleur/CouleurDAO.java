package ci.dcg.visionzero.couleur;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CouleurDAO implements UICouleurDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Couleur getCouleurByLibelle(String s) {
        Couleur couleur = (Couleur) entityManager.createQuery("SELECT c FROM Couleur c WHERE c.libelleCouleur = :nomCouleur")
                .setParameter("nomCouleur", s)
                .getSingleResult();
        return couleur;
    }
}
