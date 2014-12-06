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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author QQ1403
 */
public class PodiatreTest {
    //variable utilitaire pour tester les methodes de la classe
    MockJson jsongenere = new MockJson();
    JSONArray liste_activite = jsongenere.getActivites();
    
    public PodiatreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ajouterActivitePourMembre method, of class Podiatre.
     */
    @Test
    public void testAjouterActivitePourMembre() {
        System.out.println("ajouterActivitePourMembre");
        jsongenere.setOrdre("podiatres");
        JSONObject une_declaration = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(une_declaration);
        JSONObject activite = liste_activite.getJSONObject(2);
        Podiatre instance = (Podiatre) membre;
        instance.ajouterActivitePourMembre(activite);
    }

    /**
     * Test of dateValidePourMembre method, of class Podiatre.
     */
    @Test
    public void testDateValidePourMembre() {
        System.out.println("dateValidePourMembre");
        jsongenere.setOrdre("podiatres");
        JSONObject une_declaration = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(une_declaration);
        JSONObject une_activite = liste_activite.getJSONObject(0);
        String date = une_activite.getString("date");
        Podiatre instance = (Podiatre) membre;
        boolean expResult = false;
        boolean result = instance.dateValidePourMembre(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCycle method, of class Podiatre.
     */
    @Test
    public void testGetCycle() {
        System.out.println("getCycle");
        jsongenere.setOrdre("podiatres");
        JSONObject une_declaration = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(une_declaration);
        Podiatre instance = (Podiatre) membre;
        String expResult = jsongenere.getCycle();
        String result = instance.getCycle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivitesRefusees method, of class Podiatre.
     */
    @Test
    public void testGetActivitesRefusees() {
        System.out.println("getActivitesRefusees");
        jsongenere.setOrdre("podiatres");
        JSONObject une_declaration = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(une_declaration);
        JSONObject une_activite = liste_activite.getJSONObject(4);
        Podiatre instance = (Podiatre) membre;
        ArrayList expResult = new ArrayList(1);
        instance.ajouterActivitePourMembre(une_activite);
        expResult.add(une_activite);
        ArrayList result = instance.getActivitesRefusees();
        assertEquals(expResult, result);
   }

    /**
     * Test of getActivitesAcceptees method, of class Podiatre.
     */
    @Test
    public void testGetActivitesAcceptees() {
        System.out.println("getActivitesAcceptees");
        jsongenere.setOrdre("podiatres");
        JSONObject une_declaration = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(une_declaration);
        JSONObject une_activite = liste_activite.getJSONObject(3);
        Podiatre instance = (Podiatre) membre;
        instance.ajouterActivitePourMembre(une_activite);
        ArrayList expResult = new ArrayList(1);
        expResult.add(une_activite);
        ArrayList result = instance.getActivitesAcceptees();
        assertEquals(expResult, result);
    }
    
}
