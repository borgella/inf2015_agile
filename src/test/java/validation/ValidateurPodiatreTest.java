package validation;

import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import professionnels.*;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurPodiatreTest {
    
    int minimumHeuresTotales = 60;
    
    int minimumHeuresCours = 22;
    int minimumHeuresProjet = 3;
    int minimumHeuresGroupe = 1;
    
    ValidateurPodiatre validateur;
    Membre podiatre;
    
    @Before
    public void setUp() {
        podiatre = new Podiatre();
        validateur = new ValidateurPodiatre(podiatre);
    }
    
    @After
    public void tearDown() {
        podiatre = null;
        validateur = null;
    }
    
    private JSONObject creerActiviteDeNHeuresValideSelonCategorie(int heures, String categorie) {
        JSONObject activite = new JSONObject();
        activite.accumulate("description", "Une activité quelconque");
        activite.accumulate("categorie", categorie);
        activite.accumulate("heures", heures);
        activite.accumulate("date", "2015-01-01");
        return activite;
    }

    @Test
    public void testFormationComplete() {
        assertFalse(validateur.formationComplete());
        
        int heuresInsuffantes = (minimumHeuresTotales - 1);
        podiatre.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(heuresInsuffantes, "atelier"));
        assertFalse(validateur.formationComplete());
        
        podiatre.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(1, "atelier"));
        assertFalse(validateur.formationComplete());
        
        podiatre.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresCours, "cours"));
        assertFalse(validateur.formationComplete());
        
        podiatre.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresProjet, "projet de recherche"));
        assertFalse(validateur.formationComplete());
        
        podiatre.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresGroupe, "groupe de discussion"));
        assertTrue(validateur.formationComplete());
    }
    
}
