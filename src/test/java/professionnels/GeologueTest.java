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

    private JSONObject creerActiviteValideSelonCategorie(String categorie) {
        JSONObject activite = new JSONObject();
        activite.accumulate("description", "Une activité quelconque");
        activite.accumulate("categorie", categorie);
        activite.accumulate("heures", 1);
        activite.accumulate("date", "2015-01-01");
        return activite;
    }

    /**
     * Test of ajouterActivitePourMembre method, of class Geologue.
     */
    @Test
    public void testAjouterActivitePourMembre() {
        System.out.println("ajouterActivitePourMembre");
        jsongenere.setOrdre("géologues");
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
        jsongenere.setOrdre("géologues");
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
        jsongenere.setOrdre("géologues");
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
        jsongenere.setOrdre("géologues");
        JSONObject declaration_json = jsongenere.retournerUnJSONObject();
        Membre membre = Membre.genererMembre(declaration_json);
        JSONObject declaration = liste_activite.getJSONObject(0);
        String date = declaration.getString("date");
        Geologue instance = (Geologue) membre;
        boolean expResult = false;
        boolean result = instance.dateValidePourMembre(date);
        assertEquals(expResult, result);
    }

    @Test
    public void testDateValidePourMembre2() {
        Geologue geologue = new Geologue();

        assertFalse(geologue.dateValidePourMembre("20012-01-01"));
        assertFalse(geologue.dateValidePourMembre("2013-05-31"));

        String debutCycleGeologue = "2013-06-01";
        String finCycleGeologue = "2016-06-01";

        assertTrue(geologue.dateValidePourMembre(debutCycleGeologue));
        assertTrue(geologue.dateValidePourMembre("2014-04-02"));
        assertTrue(geologue.dateValidePourMembre("2015-02-04"));
        assertTrue(geologue.dateValidePourMembre("2016-05-31"));
        assertTrue(geologue.dateValidePourMembre(finCycleGeologue));

        assertFalse(geologue.dateValidePourMembre("2016-06-02"));
        assertFalse(geologue.dateValidePourMembre("2017-12-13"));
    }

    /**
     * Test of getCycle method, of class Geologue.
     */
    @Test
    public void testGetCycle() {
        System.out.println("getCycle");
        jsongenere.setOrdre("géologues");
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
    public void testGetActivitesRefusees() {
        System.out.println("getActivitesRefusees");
        jsongenere.setOrdre("géologues");
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
        jsongenere.setOrdre("géologues");
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
        jsongenere.setOrdre("géologues");
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
        jsongenere.setOrdre("géologues");
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

    @Test
    public void testObtenirNombreActivitesValidesParCategorie2() {
        Membre geologue = new Geologue();
        
        String categorieValideUn = "cours";
        String categorieValideDeux = "atelier";
        String categorieInvalide = "invalide";
                
        assertEquals(0, geologue.obtenirNombreActivitesValidesParCategorie(categorieValideUn));
        assertEquals(0, geologue.obtenirNombreActivitesValidesParCategorie(categorieValideDeux));
        assertEquals(0, geologue.obtenirNombreActivitesValidesParCategorie(categorieInvalide));
        
        geologue.ajouterActivitePourMembre(creerActiviteValideSelonCategorie(categorieValideUn));
        geologue.ajouterActivitePourMembre(creerActiviteValideSelonCategorie(categorieValideUn));
        geologue.ajouterActivitePourMembre(creerActiviteValideSelonCategorie(categorieValideDeux));
        geologue.ajouterActivitePourMembre(creerActiviteValideSelonCategorie(categorieInvalide));
        
        assertEquals(2, geologue.obtenirNombreActivitesValidesParCategorie(categorieValideUn));
        assertEquals(1, geologue.obtenirNombreActivitesValidesParCategorie(categorieValideDeux));
        assertEquals(0, geologue.obtenirNombreActivitesValidesParCategorie(categorieInvalide));
    }
}
