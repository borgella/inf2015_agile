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
public class ValidateurPsychologueTest {
    
    int minimumHeuresTotales = 90;
    
    int minimumHeuresCours = 25;
    int maximumHeuresConference = 15;
    
    ValidateurPsychologue validateur;
    Membre psychologue;
    
    @Before
    public void setUp() {
        psychologue = new Psychologue("2010-2015");
        validateur = new ValidateurPsychologue(psychologue);
    }
    
    @After
    public void tearDown() {
        psychologue = null;
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
    public void testValiderLeCycle() {
        String bonCyclePourPsychologue = "2010-2015";
        Membre psychologueBonCycle = new Psychologue(bonCyclePourPsychologue);
        Validateur validateurBonCycle = new ValidateurPsychologue(psychologueBonCycle);
        
        assertTrue(validateurBonCycle.validerLeCycle());
        
        String mauvaisCyclePourPsychologue = "2011-2016";
        Membre psychologueMauvaisCycle = new Psychologue(mauvaisCyclePourPsychologue);
        Validateur validateurMauvaisCycle = new ValidateurPsychologue(psychologueMauvaisCycle);
        
        assertFalse(validateurMauvaisCycle.validerLeCycle());
    }
    
     @Test
    public void testCalculerHeuresManquantes() {
        System.out.println(validateur.calculerHeuresManquantes());
        assertEquals(minimumHeuresTotales, validateur.calculerHeuresManquantes());
        
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresTotales, "conférence"));
        assertEquals(minimumHeuresTotales - maximumHeuresConference, validateur.calculerHeuresManquantes());
        
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresTotales, "atelier"));
        assertEquals(minimumHeuresCours, validateur.calculerHeuresManquantes());
        
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresCours, "cours"));
        assertEquals(0, validateur.calculerHeuresManquantes());
    }

    @Test
    public void testHeuresTotalesFormation() {
        assertEquals(0, validateur.heuresTotalesFormation());
        
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(maximumHeuresConference, "conférence"));
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(1, "conférence"));
        
        assertEquals(maximumHeuresConference, validateur.heuresTotalesFormation());
        
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(3, "cours"));
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(6, "atelier"));
        assertEquals(maximumHeuresConference + 3 + 6, validateur.heuresTotalesFormation());
        
        int heuresInvalides = 99;
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(heuresInvalides, "invalide"));
        
        assertEquals(maximumHeuresConference + 3 + 6, validateur.heuresTotalesFormation());
    }

    @Test
    public void testNombreDHeuresSelonRegroupement() {
        int codeCours = 1;
        int codeToutSaufCoursEtConference = 2;
        int codeConference = 3;
        
        assertEquals(0, validateur.nombreDHeuresSelonRegroupement(codeCours));
        assertEquals(0, validateur.nombreDHeuresSelonRegroupement(codeToutSaufCoursEtConference));
        assertEquals(0, validateur.nombreDHeuresSelonRegroupement(codeConference));
        
        psychologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(1, "cours"));
        psychologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(2, "atelier"));
        psychologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(4, "colloque"));
        psychologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(8, "conférence"));
        
        assertEquals(1, validateur.nombreDHeuresSelonRegroupement(codeCours));
        assertEquals(2 + 4, validateur.nombreDHeuresSelonRegroupement(codeToutSaufCoursEtConference));
        assertEquals(8, validateur.nombreDHeuresSelonRegroupement(codeConference));
    }

    @Test
    public void testHeuresBrutesSelonCategorie() {
        assertEquals(0, validateur.heuresBrutesSelonCategorie("cours"));
        assertEquals(0, validateur.heuresBrutesSelonCategorie("conférence"));
        assertEquals(0, validateur.heuresBrutesSelonCategorie("projet de recherche"));
        
        psychologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "cours"));
        psychologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "cours"));
        psychologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "conférence"));

        assertEquals(6, validateur.heuresBrutesSelonCategorie("cours"));
        assertEquals(3, validateur.heuresBrutesSelonCategorie("conférence"));
        assertEquals(0, validateur.heuresBrutesSelonCategorie("projet de recherche"));
    }

    @Test
    public void testFormationComplete() {
        assertFalse(validateur.formationComplete());
        
        int heuresConferenceBornees = minimumHeuresTotales;
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(heuresConferenceBornees, "conférence"));
        // Note: heuresConferenceBornees bornée par maximumHeuresConference 
        assertFalse(validateur.formationComplete());
        
        int heuresInsuffisantes = (minimumHeuresTotales - maximumHeuresConference - 1);
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(heuresInsuffisantes, "atelier"));
        assertFalse(validateur.formationComplete());
        
        psychologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(1, "atelier"));
        assertFalse(validateur.formationComplete());
        
        psychologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresCours, "cours"));
        assertTrue(validateur.formationComplete());
    } 
}
