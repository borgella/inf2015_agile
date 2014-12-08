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
public class PsychologueTest {
    MockJson jsongenere = new MockJson();
    JSONArray liste_activite = jsongenere.getActivites();
       
    
    public PsychologueTest() {
    }

    /**
     * Test of ajouterActivitePourMembre method, of class Psychologue.
     */
    @Test
    public void testAjouterActivitePourMembre() {
        System.out.println("ajouterActivitePourMembre");
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject activite = liste_activite.getJSONObject(0);
        Psychologue instance = (Psychologue)membre;
        instance.ajouterActivitePourMembre(activite);
    }

    /**
     * Test of dateValidePourMembre method, of class Psychologue.
     */
    @Test
    public void testDateValidePourMembre() {
        System.out.println("dateValidePourMembre");
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject declaration = liste_activite.getJSONObject(0);
        String date = declaration.getString("date");
        Psychologue instance = (Psychologue)membre;
        boolean expResult = true;
        boolean result = instance.dateValidePourMembre(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCycle method, of class Psychologue.
     */
    @Test
    public void testGetCycle() {
        System.out.println("getCycle");
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        Psychologue instance = (Psychologue)membre;
        String expResult = jsongenere.getCycle();
        String result = instance.getCycle();
        assertEquals(expResult, result);
    }

    /**
     * Test of premiereCategorie method, of class Psychologue.
     */
    @Test
    public void testPremiereCategorie() {
        System.out.println("premiereCategorie");   
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject declaration = liste_activite.getJSONObject(0);
        String categorie = declaration.getString("categorie");
        Psychologue instance = (Psychologue)membre;
        int expResult = 1;
        int result = instance.premiereCategorie(categorie);
        assertEquals(expResult, result);
    }

    /**
     * Test of deuxiemeCategorie method, of class Psychologue.
     */
    @Test
    public void testDeuxiemeCategorie() {
        System.out.println("deuxiemeCategorie");   
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject declaration = liste_activite.getJSONObject(3);
        String categorie = declaration.getString("categorie");
        Psychologue instance = (Psychologue)membre;
        int expResult = 0;
        int result = instance.premiereCategorie(categorie);
        assertEquals(expResult, result);
    }

    /**
     * Test of troisiemeCategorie method, of class Psychologue.
     */
    @Test
    public void testTroisiemeCategorie() {
        System.out.println("troisiemeCategorie");
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject declaration = liste_activite.getJSONObject(3);
        String categorie = declaration.getString("categorie");
        Psychologue instance = (Psychologue)membre;
        int expResult = 0;
        int result = instance.premiereCategorie(categorie);
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivitesRefusees method, of class Psychologue.
     */
    @Test
    public void testGetActivitesRefusees() {
        System.out.println("getActivitesRefusees");
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        Psychologue instance = (Psychologue) membre;
        JSONObject une_activite = liste_activite.getJSONObject(6);
        instance.ajouterActivitePourMembre(une_activite);
        ArrayList expResult = new ArrayList(1);
        expResult.add(une_activite);
        ArrayList result = instance.getActivitesRefusees();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivitesAcceptees method, of class Psychologue.
     */
    @Test
    public void testGetActivitesAcceptees() {
        System.out.println("getActivitesAcceptees");
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        Psychologue instance = (Psychologue) membre;
        JSONObject une_activite = liste_activite.getJSONObject(1);
        instance.ajouterActivitePourMembre(une_activite);
        ArrayList expResult = new ArrayList(1);
        expResult.add(une_activite);
        ArrayList result = instance.getActivitesAcceptees();
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenirNombreActivitesValides method, of class Psychologue.
     */
    @Test
    public void testObtenirNombreActivitesValides() {
        System.out.println("obtenirNombreActivitesValides");
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        Psychologue instance = (Psychologue) membre;
        JSONObject une_activite = liste_activite.getJSONObject(0);
        instance.ajouterActivitePourMembre(une_activite);
        int expResult = 1;
        int result = instance.obtenirNombreActivitesValides();
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenirNombreActivitesValidesParCategorie method, of class Psychologue.
     */
    @Test
    public void testObtenirNombreActivitesValidesParCategorie() {
        System.out.println("obtenirNombreActivitesValidesParCategorie");
        jsongenere.setOrdre("psychologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject une_activite = liste_activite.getJSONObject(0);
        String categorie = une_activite.getString("categorie");
        Psychologue instance = (Psychologue) membre;
        instance.ajouterActivitePourMembre(une_activite);
        int expResult = 1;
        int result = instance.obtenirNombreActivitesValidesParCategorie(categorie);
        assertEquals(expResult, result);
    }
    
}
