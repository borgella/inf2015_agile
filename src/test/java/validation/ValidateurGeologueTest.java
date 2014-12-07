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
public class ValidateurGeologueTest {
    
    int minimumHeuresTotales = 55;
    
    int minimumHeuresCours = 22;
    int minimumHeuresProjet = 3;
    int minimumHeuresGroupe = 1;
    
    ValidateurGeologue validateur;
    Membre geologue;
    
    @Before
    public void setUp() {
        geologue = new Geologue();
        validateur = new ValidateurGeologue(geologue);
    }
    
    @After
    public void tearDown() {
        geologue = null;
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
    public void testProduireRapport() {
    }

    @Test
    public void testConstruireMessagesDErreur() {
    }

    @Test
    public void testValiderLeCycle() {
        String bonCyclePourGeologue = "2013-2016";
        Membre geologueBonCycle = new Geologue("géologues", bonCyclePourGeologue);
        Validateur validateurBonCycle = new ValidateurGeologue(geologueBonCycle);
        
        assertTrue(validateurBonCycle.validerLeCycle());
        
        String mauvaisCyclePourGeologue = "2012-2015";
        Membre geologueMauvaisCycle = new Geologue("géologues", mauvaisCyclePourGeologue);
        Validateur validateurMauvaisCycle = new ValidateurGeologue(geologueMauvaisCycle);
        
        assertFalse(validateurMauvaisCycle.validerLeCycle());
    }

    @Test
    public void testMessageErreurSiLeCycleEstInvalide() {
    }

    @Test
    public void testMessageInvalidePourCategorieNonReconnue() {
    }

    @Test
    public void testDescriptionsDActivitesAvecCategorieNonReconnue() {
    }

    @Test
    public void testEcrireMessageDErreurPourCategoriesNonReconnues() {
    }

    @Test
    public void testMessageErreurPourDateInvalide() {
    }

    @Test
    public void testDescriptionsDActivitesAvecDateInvalide() {
    }

    @Test
    public void testEcrireMessageDErreurPourDatesInvalides() {
    }

    @Test
    public void testCalculerHeuresManquantes() {
        assertEquals(minimumHeuresTotales, validateur.calculerHeuresManquantes());
        
        geologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresTotales, "atelier"));
        assertEquals
            (minimumHeuresCours + minimumHeuresProjet + minimumHeuresGroupe, validateur.calculerHeuresManquantes());
        
        geologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresCours, "cours"));
        assertEquals(minimumHeuresProjet + minimumHeuresGroupe, validateur.calculerHeuresManquantes());
        
        geologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresProjet, "projet de recherche"));
        assertEquals(minimumHeuresGroupe, validateur.calculerHeuresManquantes());
        
        geologue.ajouterActivitePourMembre
            (creerActiviteDeNHeuresValideSelonCategorie(minimumHeuresProjet, "groupe de discussion"));
        assertEquals(0, validateur.calculerHeuresManquantes());
    }

    @Test
    public void testHeuresTotalesFormation() {
    }

    @Test
    public void testHeuresTotalesPourRegroupementDesSeptCategories() {
    }

    @Test
    public void testNombreDHeuresSelonRegroupement() {
        int codeToutSaufCoursProjetEtGroupe = 1;
        int codeCoursProjetOuGroupe = 2;
        
        assertEquals(0, validateur.nombreDHeuresSelonRegroupement(codeToutSaufCoursProjetEtGroupe));
        assertEquals(0, validateur.nombreDHeuresSelonRegroupement(codeCoursProjetOuGroupe));
        
        geologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "atelier"));
        geologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "cours"));
        geologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "projet de recherche"));
        geologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "groupe de discussion"));
        
        assertEquals(3, validateur.nombreDHeuresSelonRegroupement(codeToutSaufCoursProjetEtGroupe));
        assertEquals(9, validateur.nombreDHeuresSelonRegroupement(codeCoursProjetOuGroupe));
    }

    @Test
    public void testHeuresBrutesSelonCategorie() {
        assertEquals(0, validateur.heuresBrutesSelonCategorie("cours"));
        assertEquals(0, validateur.heuresBrutesSelonCategorie("atelier"));
        assertEquals(0, validateur.heuresBrutesSelonCategorie("projet de recherche"));
        
        geologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "cours"));
        geologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "cours"));
        geologue.ajouterActivitePourMembre(creerActiviteDeNHeuresValideSelonCategorie(3, "atelier"));

        assertEquals(6, validateur.heuresBrutesSelonCategorie("cours"));
        assertEquals(3, validateur.heuresBrutesSelonCategorie("atelier"));
        assertEquals(0, validateur.heuresBrutesSelonCategorie("projet de recherche"));
    }

    @Test
    public void testMaximumEntreUnNombreEtUnTripletSomme() {
        int premierNombreSeul = 4;
        int maximumUn = ValidateurGeologue.maximumEntreUnNombreEtUnTripletSomme(premierNombreSeul, 1, 2, 3);
        assertEquals(1 + 2 + 3, maximumUn);
        
        int deuxiemeNombreSeul = 1;
        int maximumDeux = ValidateurGeologue.maximumEntreUnNombreEtUnTripletSomme(deuxiemeNombreSeul, 2, -2, 0);
        assertEquals(2 + 0 + 0, maximumDeux);
    }

    @Test
    public void testRendreEntierNulSiNegatif() {
        int nombreNegatif = -5;
        assertEquals(0, ValidateurGeologue.rendreEntierNulSiNegatif(nombreNegatif));
        
        int nombreZero = 0;
        assertEquals(0, ValidateurGeologue.rendreEntierNulSiNegatif(nombreZero));
        
        int nombrePositif = 6;
        assertEquals(6, ValidateurGeologue.rendreEntierNulSiNegatif(nombrePositif));
    }

    @Test
    public void testEcrireMessageErreurPourHeuresManquantesSiApplicable() {
    }

    @Test
    public void testMessageErreurPourHeuresInsuffisantesParCategorie() {
    }

    @Test
    public void testMessageErreurPourHeuresInsuffisantesCours() {
    }

    @Test
    public void testMessageErreurPourHeuresInsuffisantesRecherche() {
    }

    @Test
    public void testMessageErreurPourHeuresInsuffisantesDiscussion() {
    }

    @Test
    public void testFormationComplete() {
    }
    
}
