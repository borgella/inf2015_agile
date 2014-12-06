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
public class GeologueTest {
    //variable utilitaire pour tester les methodes de la classe
    MockJson jsongenere = new MockJson();
    JSONArray liste_activite = jsongenere.getActivites();  
    public GeologueTest() {
    }

    /**
     * Test of ajouterActivitePourMembre method, of class Geologue.
     */
    @Test
    public void testAjouterActivitePourMembre() {
        System.out.println("ajouterActivitePourMembre");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject activite = liste_activite.getJSONObject(0);
        Geologue instance = (Geologue) membre;
        instance.ajouterActivitePourMembre(activite);
        
    }

    /**
     * Test of premiereCategorie method, of class Geologue.
     */
    @Test
    public void testPremiereCategorie() {
        System.out.println("premiereCategorie");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject declaration = liste_activite.getJSONObject(0);
        String categorie = declaration.getString("categorie");
        Geologue instance = (Geologue) membre;
        int expResult = 0;
        int result = instance.premiereCategorie(categorie);
        assertEquals(expResult, result);
    }

    /**
     * Test of deuxiemeCategorie method, of class Geologue.
     */
    @Test
    public void testDeuxiemeCategorie() {
        System.out.println("deuxiemeCategorie");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject declaration = liste_activite.getJSONObject(0);
        String categorie = declaration.getString("categorie");
        Geologue instance = (Geologue) membre;
        int expResult = 2;
        int result = instance.deuxiemeCategorie(categorie);
        assertEquals(expResult, result);
    }

    /**
     * Test of dateValidePourMembre method, of class Geologue.
     */
    @Test
    public void testDateValidePourMembre() {
        System.out.println("dateValidePourMembre");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject declaration = liste_activite.getJSONObject(0);
        String date = declaration.getString("date");
        Geologue instance = (Geologue) membre;
        boolean expResult = false;
        boolean result = instance.dateValidePourMembre(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCycle method, of class Geologue.
     */
    @Test
    public void testGetCycle() {
        System.out.println("getCycle");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        Geologue instance = (Geologue) membre;
        String expResult = declaration_json.getString("cycle");
        String result = instance.getCycle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivitesRefusees method, of class Geologue.
     */
    @Test
    public void testGetActivitesRefusees(){
        System.out.println("getActivitesRefusees");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        Geologue instance = (Geologue) membre;
        JSONObject une_activite = liste_activite.getJSONObject(0);
        instance.ajouterActivitePourMembre(une_activite);
        ArrayList expResult = new ArrayList(1);
        expResult.add(une_activite);
        ArrayList result = instance.getActivitesRefusees();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivitesAcceptees method, of class Geologue.
     */
    @Test
    public void testGetActivitesAcceptees() {
        System.out.println("getActivitesAcceptees");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        Geologue instance = (Geologue) membre;
        JSONObject une_activite = liste_activite.getJSONObject(0);
        instance.ajouterActivitePourMembre(une_activite);
        ArrayList expResult = new ArrayList(1);
        expResult.add(une_activite);
        ArrayList result = instance.getActivitesRefusees();
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenirNombreActivitesValides method, of class Geologue.
     */
    @Test
    public void testObtenirNombreActivitesValides() {
        System.out.println("obtenirNombreActivitesValides");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        Geologue instance = (Geologue) membre;
        JSONObject une_activite = liste_activite.getJSONObject(4);
        instance.ajouterActivitePourMembre(une_activite);
        int expResult = 0;
        int result = instance.obtenirNombreActivitesValides();
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenirNombreActivitesValidesParCategorie method, of class Geologue.
     */
    @Test
    public void testObtenirNombreActivitesValidesParCategorie() {
        System.out.println("obtenirNombreActivitesValidesParCategorie");
        jsongenere.setOrdre("geologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject une_activite = liste_activite.getJSONObject(0);
        String categorie = une_activite.getString("categorie");
        Geologue instance = (Geologue) membre;
        instance.ajouterActivitePourMembre(une_activite);
        int expResult = 0;
        int result = instance.obtenirNombreActivitesValidesParCategorie(categorie);
        assertEquals(expResult, result);
    }
    
}
