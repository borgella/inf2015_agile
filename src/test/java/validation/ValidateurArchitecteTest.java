/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import inf2015_projet.MockJson;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import professionnels.Membre;

/**
 *
 * @author QQ1403
 */
public class ValidateurArchitecteTest {
    
    MockJson jsongenere = new MockJson();
    JSONObject declaration_json = jsongenere.retournerUnJSONObject();
    JSONArray liste_activite = jsongenere.getActivites();
    Membre architecte = Membre.genererMembre(declaration_json);
    ValidateurArchitecte validateur;
    
    public ValidateurArchitecteTest() {
    }
        
    @Before
    public void setUp() {
        validateur = new ValidateurArchitecte(architecte);
    }
    
    @After
    public void tearDown() {
        validateur = null;
        architecte = null;
    }

    /**
     * Test of produireRapport method, of class ValidateurArchitecte.
     */
    @Test
    public void testProduireRapport() {
        System.out.println("produireRapport");
        ValidateurArchitecte instance = validateur;
        JSONObject expResult = new JSONObject();
        JSONObject result = instance.produireRapport();
        assertEquals(expResult, result);
    }

    /**
     * Test of construireMessagesDErreur method, of class ValidateurArchitecte.
     */
    @Test
    public void testConstruireMessagesDErreur() {
        System.out.println("construireMessagesDErreur");
        ValidateurArchitecte instance = validateur;
        instance.construireMessagesDErreur();
    }

    /**
     * Test of messageErreurSiLeCycleEstInvalide method, of class ValidateurArchitecte.
     */
    @Test
    public void testMessageErreurSiLeCycleEstInvalide() {
        System.out.println("messageErreurSiLeCycleEstInvalide");
        ValidateurArchitecte instance = validateur;
        instance.messageErreurSiLeCycleEstInvalide();
    }

    /**
     * Test of validerLeCycle method, of class ValidateurArchitecte.
     */
    @Test
    public void testValiderLeCycle() {
        System.out.println("validerLeCycle");
        ValidateurArchitecte instance = validateur;
        boolean expResult = true;
        boolean result = instance.validerLeCycle();
        assertEquals(expResult, result);
    }

    /**
     * Test of messageInvalidePourCategorieNonReconnue method, of class ValidateurArchitecte.
     */
    @Test
    public void testMessageInvalidePourCategorieNonReconnue() {
        System.out.println("messageInvalidePourCategorieNonReconnue");
        ValidateurArchitecte instance = validateur;
        instance.messageInvalidePourCategorieNonReconnue(architecte);
    }

    /**
     * Test of descriptionsDActivitesAvecCategorieNonReconnue method, of class ValidateurArchitecte.
     */
    @Test
    public void testDescriptionsDActivitesAvecCategorieNonReconnue() {
        System.out.println("descriptionsDActivitesAvecCategorieNonReconnue");
        ValidateurArchitecte instance = validateur;
        ArrayList<String> expResult = new ArrayList(1);
        ArrayList<String> result = instance.descriptionsDActivitesAvecCategorieNonReconnue(architecte);
        assertEquals(expResult, result);
    }

    /**
     * Test of ecrireMessageDErreurPourCategoriesNonReconnues method, of class ValidateurArchitecte.
     */
    @Test
    public void testEcrireMessageDErreurPourCategoriesNonReconnues() {
        System.out.println("ecrireMessageDErreurPourCategoriesNonReconnues");
        int nombreDActivites = 0;
        String activitesErronees = "";
        ValidateurArchitecte instance = validateur;
        instance.ecrireMessageDErreurPourCategoriesNonReconnues(nombreDActivites, activitesErronees);
    }

    /**
     * Test of messageErreurPourDateInvalide method, of class ValidateurArchitecte.
     */
    @Test
    public void testMessageErreurPourDateInvalide() {
        System.out.println("messageErreurPourDateInvalide");
        ValidateurArchitecte instance = validateur;
        instance.messageErreurPourDateInvalide(architecte);
        
    }

    /**
     * Test of descriptionsDActivitesAvecDateInvalide method, of class ValidateurArchitecte.
     */
    @Test
    public void testDescriptionsDActivitesAvecDateInvalide() {
        System.out.println("descriptionsDActivitesAvecDateInvalide");
        ValidateurArchitecte instance = validateur;
        ArrayList<String> expResult = new ArrayList();
        ArrayList<String> result = instance.descriptionsDActivitesAvecDateInvalide(architecte);
        assertEquals(expResult, result);
    }

    /**
     * Test of ecrireMessageDErreurPourDatesInvalides method, of class ValidateurArchitecte.
     */
    @Test
    public void testEcrireMessageDErreurPourDatesInvalides() {
        System.out.println("ecrireMessageDErreurPourDatesInvalides");
        int nombreDActivites = 0;
        String activitesErronees = "";
        ValidateurArchitecte instance = validateur;
        instance.ecrireMessageDErreurPourDatesInvalides(nombreDActivites, activitesErronees);
     }

    /**
     * Test of messageErreurPourHeuresManquantes method, of class ValidateurArchitecte.
     */
    @Test
    public void testMessageErreurPourHeuresManquantes() {
        System.out.println("messageErreurPourHeuresManquantes");
        ValidateurArchitecte instance = validateur;
        instance.messageErreurPourHeuresManquantes();
    }

    /**
     * Test of heuresTotalesFormation method, of class ValidateurArchitecte.
     */
    @Test
    public void testHeuresTotalesFormation() {
        System.out.println("heuresTotalesFormation");
        ValidateurArchitecte instance = validateur;
        int expResult = 2;
        int result = instance.heuresTotalesFormation();
        assertEquals(expResult, result);
    }

    /**
     * Test of nombreDHeuresSelonRegroupement method, of class ValidateurArchitecte.
     */
    @Test
    public void testNombreDHeuresSelonRegroupement() {
        System.out.println("nombreDHeuresSelonRegroupement");
        int codeDuRegroupement = 0;
        ValidateurArchitecte instance = validateur;
        int expResult = 0;
        int result = instance.nombreDHeuresSelonRegroupement(codeDuRegroupement);
        assertEquals(expResult, result);
    }

    /**
     * Test of heuresBrutesSelonCategorie method, of class ValidateurArchitecte.
     */
    @Test
    public void testHeuresBrutesSelonCategorie() {
        System.out.println("heuresBrutesSelonCategorie");
        String categorie = "";
        ValidateurArchitecte instance = validateur;
        int expResult = 0;
        int result = instance.heuresBrutesSelonCategorie(categorie);
        assertEquals(expResult, result);
    }

    /**
     * Test of formationComplete method, of class ValidateurArchitecte.
     */
    @Test
    public void testFormationComplete() {
        System.out.println("formationComplete");
        ValidateurArchitecte instance = validateur;
        boolean expResult = false;
        boolean result = instance.formationComplete();
        assertEquals(expResult, result);
    }
    
}
