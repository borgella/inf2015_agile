package validation;

import inf2015_projet.MockJson;
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
    MockJson jsongenere = new MockJson();
    ValidateurGeologue validateur;
    
    int minimumHeuresTotales = 55;
    
    int minimumHeuresCours = 22;
    int minimumHeuresProjet = 3;
    int minimumHeuresGroupe = 1;
    
    
    Membre geologue;
    
    @Before
    public void setUp() {
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre geologues = Membre.genererMembre(declaration_json);
        validateur = new ValidateurGeologue(geologues);
    }
    
    @After
    public void tearDown() {
        geologue = null;
        validateur = null;
    }
    
   
    /**
     * Test of produireRapport method, of class ValidateurGeologue.
     */
    @Test
    public void testProduireRapport() {
        System.out.println("produireRapport");
        ValidateurGeologue instance = validateur;
        JSONObject expResult = null;
        JSONObject result = instance.produireRapport();
        assertEquals(expResult, result);
    }

    /**
     * Test of construireMessagesDErreur method, of class ValidateurGeologue.
     */
    @Test
    public void testConstruireMessagesDErreur() {
        System.out.println("construireMessagesDErreur");
        ValidateurGeologue instance = validateur;
        instance.construireMessagesDErreur();
        
    }

    /**
     * Test of messageErreurSiLeCycleEstInvalide method, of class ValidateurGeologue.
     */
    @Test
    public void testMessageErreurSiLeCycleEstInvalide() {
        System.out.println("messageErreurSiLeCycleEstInvalide");
        ValidateurGeologue instance = validateur;
        instance.messageErreurSiLeCycleEstInvalide();
    }

    /**
     * Test of ecrireMessageDErreurPourCategoriesNonReconnues method, of class ValidateurGeologue.
     */
    @Test
    public void testEcrireMessageDErreurPourCategoriesNonReconnues() {
        System.out.println("ecrireMessageDErreurPourCategoriesNonReconnues");
        int nombreDActivites = 0;
        String activitesErronees = "";
        ValidateurGeologue instance = validateur;
        instance.ecrireMessageDErreurPourCategoriesNonReconnues(nombreDActivites, activitesErronees);
    }

    /**
     * Test of ecrireMessageDErreurPourDatesInvalides method, of class ValidateurGeologue.
     */
    @Test
    public void testEcrireMessageDErreurPourDatesInvalides() {
        System.out.println("ecrireMessageDErreurPourDatesInvalides");
        int nombreDActivites = 0;
        String activitesErronees = "";
        ValidateurGeologue instance = validateur;
        instance.ecrireMessageDErreurPourDatesInvalides(nombreDActivites, activitesErronees);
    }

    /**
     * Test of ecrireMessageErreurPourHeuresManquantes method, of class ValidateurGeologue.
     */
    @Test
    public void testEcrireMessageErreurPourHeuresManquantes() {
        System.out.println("ecrireMessageErreurPourHeuresManquantes");
        int heuresManquantes = 0;
        ValidateurGeologue instance = validateur;
        instance.ecrireMessageErreurPourHeuresManquantes(heuresManquantes);
    }

    /**
     * Test of messageErreurPourHeuresInsuffisantesParCategorie method, of class ValidateurGeologue.
     */
    @Test
    public void testMessageErreurPourHeuresInsuffisantesParCategorie() {
        System.out.println("messageErreurPourHeuresInsuffisantesParCategorie");
        ValidateurGeologue instance = validateur;
        instance.messageErreurPourHeuresInsuffisantesParCategorie();
    }

    /**
     * Test of messageErreurPourHeuresInsuffisantesSelonCategorie method, of class ValidateurGeologue.
     */
    @Test
    public void testMessageErreurPourHeuresInsuffisantesSelonCategorie() {
        System.out.println("messageErreurPourHeuresInsuffisantesSelonCategorie");
        int heuresRequises = 0;
        String categorie = "";
        ValidateurGeologue instance = validateur;
        instance.messageErreurPourHeuresInsuffisantesSelonCategorie(heuresRequises, categorie);
    }
}
