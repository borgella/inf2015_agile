/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professionnels;

import inf2015_projet.MockJson;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author QQ1403
 */
public class ArchitecteTest {
    //variable utilitaire pour tester les methodes de la classe
    
    MockJson jsongenere = new MockJson();
    JSONObject declaration_json = jsongenere.retournerUnJSONObject();
    JSONArray liste_activite = jsongenere.getActivites();
    Membre membre = Membre.genererMembre(declaration_json);
    
    
    public ArchitecteTest() {
        
    }

    /**
     * Test of ajouterActivitePourMembre method, of class Architecte.
     */
    @Test
    public void testAjouterActivitePourMembre() {
        System.out.println("ajouterActivitePourMembre");
        JSONObject activite = liste_activite.getJSONObject(0);
        Architecte instance = (Architecte)membre;
        instance.ajouterActivitePourMembre(activite);
      
    }
    
    /**
     * Test of ajouterActivitePourMembre method, of class Architecte.
     */
    @Test
    public void testAjouterActivitePourMembre1() {
        System.out.println("ajouterActivitePourMembre");
        JSONObject activite = liste_activite.getJSONObject(4);
        Architecte instance = (Architecte)membre;
        instance.ajouterActivitePourMembre(activite);
        
    }
    
    
    /**
     * Test of ajouterActivitePourMembre method, of class Architecte.
     */
    @Test
    public void testAjouterActivitePourMembre2() {
        System.out.println("ajouterActivitePourMembre");
        JSONObject activite = liste_activite.getJSONObject(5);
        Architecte instance = (Architecte)membre;
        instance.ajouterActivitePourMembre(activite);
        
    }

    /**
     * Test of dateValidePourMembre method, of class Architecte.
     */
    @Test
    public void testDateValidePourMembre2() {
        System.out.println("dateValidePourMembre");
        JSONObject json = liste_activite.getJSONObject(0);
        String date = json.getString("date");
        Architecte instance = (Architecte) membre;
       boolean expResult = true;
        boolean result = instance.dateValidePourMembre(date);
        assertEquals(expResult, result);     
    }
    
    
    /**
     * Test of getHeuresTransferees method, of class Architecte.
     */
    @Test
    public void testGetHeuresTransferees() {
        System.out.println("getHeuresTransferees");
        Architecte instance = (Architecte) membre;
        int expResult = 2;
        int result = instance.getHeuresTransferees();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCycle method, of class Architecte.
     */
    @Test
    public void testGetCycle() {
        System.out.println("getCycle");
        Architecte instance = (Architecte)membre;
        String expResult = jsongenere.getCycle();
        String result = instance.getCycle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivitesRefusees method, of class Architecte.
     */
    @Test
    public void testGetActivitesRefusees() {
        System.out.println("getActivitesRefusees");
        JSONObject une_activite = liste_activite.getJSONObject(6);
        Architecte instance = (Architecte)membre;
        instance.ajouterActivitePourMembre(une_activite);
        ArrayList expResult = new ArrayList(1);
        expResult.add(une_activite);
        ArrayList result = instance.getActivitesRefusees();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivitesAcceptees method, of class Architecte.
     */
    @Test
    public void testGetActivitesAcceptees() {
        System.out.println("getActivitesAcceptees");
        JSONObject une_activite = liste_activite.getJSONObject(1);
        Architecte instance = (Architecte) membre;
        instance.ajouterActivitePourMembre(une_activite);
        ArrayList expResult = new ArrayList(1);
        expResult.add(une_activite);
        ArrayList result = instance.getActivitesAcceptees();
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenirNombreActivitesValides method, of class Architecte.
     */
    @Test
    public void testObtenirNombreActivitesValides() {
        System.out.println("obtenirNombreActivitesValides");
        Architecte instance = (Architecte) membre;
        JSONObject une_activite = liste_activite.getJSONObject(4);
        instance.ajouterActivitePourMembre(une_activite);
        int expResult = 0;
        int result = instance.obtenirNombreActivitesValides();
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenirNombreActivitesValidesParCategorie method, of class Architecte.
     */
    @Test
    public void testObtenirNombreActivitesValidesParCategorie() {
        System.out.println("obtenirNombreActivitesValidesParCategorie");
        JSONObject une_activite = liste_activite.getJSONObject(0);
        String categorie = une_activite.getString("categorie");
        Architecte instance = (Architecte) membre;
        instance.ajouterActivitePourMembre(une_activite);
        int expResult = 1;
        int result = instance.obtenirNombreActivitesValidesParCategorie(categorie);
        assertEquals(expResult, result);
    }
    
}
