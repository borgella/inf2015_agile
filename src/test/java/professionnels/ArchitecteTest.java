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

    private JSONObject creerActiviteValideSelonCategorieEtDate(String categorie, String date) {
        JSONObject activite = new JSONObject();
        activite.accumulate("description", "Une activité quelconque");
        activite.accumulate("categorie", categorie);
        activite.accumulate("heures", 1);
        activite.accumulate("date", date);
        return activite;
    }

    /**
     * Test of ajouterActivitePourMembre method, of class Architecte.
     */
    @Test
    public void testAjouterActivitePourMembre() {
        System.out.println("ajouterActivitePourMembre");
        JSONObject activite = liste_activite.getJSONObject(0);
        Architecte instance = (Architecte) membre;
        instance.ajouterActivitePourMembre(activite);

    }

    /**
     * Test of ajouterActivitePourMembre method, of class Architecte.
     */
    @Test
    public void testAjouterActivitePourMembre1() {
        System.out.println("ajouterActivitePourMembre");
        JSONObject activite = liste_activite.getJSONObject(4);
        Architecte instance = (Architecte) membre;
        instance.ajouterActivitePourMembre(activite);

    }

    /**
     * Test of ajouterActivitePourMembre method, of class Architecte.
     */
    @Test
    public void testAjouterActivitePourMembre2() {
        System.out.println("ajouterActivitePourMembre");
        JSONObject activite = liste_activite.getJSONObject(5);
        Architecte instance = (Architecte) membre;
        instance.ajouterActivitePourMembre(activite);

    }

    @Test
    public void testAjouterActivitePourArchitecte10_12() {
        Architecte architecte = new Architecte("2010-2012");

        String categorieActiviteValideUn = "cours";
        String categorieActiviteValideDeux = "groupe de discussion";
        String categorieActiviteValideTrois = "rédaction professionnelle";

        architecte.ajouterActivitePourArchitecte10_12
            (creerActiviteValideSelonCategorieEtDate(categorieActiviteValideUn, "2011-01-01"));
        architecte.ajouterActivitePourArchitecte10_12
            (creerActiviteValideSelonCategorieEtDate(categorieActiviteValideDeux, "2011-01-02"));
        architecte.ajouterActivitePourArchitecte10_12
            (creerActiviteValideSelonCategorieEtDate(categorieActiviteValideTrois, "2011-01-03"));

        String categorieActiviteInvalideUn = "invalide";
        String dateActiviteInvalideDeux = "2009-10-11";

        architecte.ajouterActivitePourArchitecte10_12
            (creerActiviteValideSelonCategorieEtDate(categorieActiviteInvalideUn, "2011-01-04"));
        architecte.ajouterActivitePourArchitecte10_12
            (creerActiviteValideSelonCategorieEtDate("cours", dateActiviteInvalideDeux));

        assertEquals(3, architecte.obtenirNombreActivitesValides());
    }

    @Test
    public void testAjouterActivitePourArchitecte08_10() {
        Architecte architecte = new Architecte("2008-2010");

        String categorieActiviteValideUn = "cours";
        String categorieActiviteValideDeux = "groupe de discussion";
        String categorieActiviteValideTrois = "rédaction professionnelle";

        architecte.ajouterActivitePourArchitecte08_10
            (creerActiviteValideSelonCategorieEtDate(categorieActiviteValideUn, "2009-01-01"));
        architecte.ajouterActivitePourArchitecte08_10
            (creerActiviteValideSelonCategorieEtDate(categorieActiviteValideDeux, "2009-01-02"));
        architecte.ajouterActivitePourArchitecte08_10
            (creerActiviteValideSelonCategorieEtDate(categorieActiviteValideTrois, "2009-01-03"));

        String categorieActiviteInvalideUn = "invalide";
        String dateActiviteInvalideDeux = "2007-08-09";

        architecte.ajouterActivitePourArchitecte08_10
            (creerActiviteValideSelonCategorieEtDate(categorieActiviteInvalideUn, "2009-01-04"));
        architecte.ajouterActivitePourArchitecte08_10
            (creerActiviteValideSelonCategorieEtDate("cours", dateActiviteInvalideDeux));

        assertEquals(3, architecte.obtenirNombreActivitesValides());
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

    @Test
    public void testDateValidePourCycle2012_2014() {
        assertFalse(Architecte.dateValidePourCycle2012_2014("2011-01-01"));
        assertFalse(Architecte.dateValidePourCycle2012_2014("2012-03-31"));

        String debutCycle2012A2014 = "2012-04-01";
        String finCycle2012A2014 = "2014-04-01";

        assertTrue(Architecte.dateValidePourCycle2012_2014(debutCycle2012A2014));
        assertTrue(Architecte.dateValidePourCycle2012_2014("2012-04-02"));
        assertTrue(Architecte.dateValidePourCycle2012_2014("2013-12-13"));
        assertTrue(Architecte.dateValidePourCycle2012_2014("2014-03-31"));
        assertTrue(Architecte.dateValidePourCycle2012_2014(finCycle2012A2014));

        assertFalse(Architecte.dateValidePourCycle2012_2014("2014-04-02"));
        assertFalse(Architecte.dateValidePourCycle2012_2014("2015-01-02"));
    }

    @Test
    public void testDateValidePourCycle2010_2012() {
        assertFalse(Architecte.dateValidePourCycle2010_2012("2009-01-01"));
        assertFalse(Architecte.dateValidePourCycle2010_2012("2010-03-31"));

        String debutCycle2010A2012 = "2010-04-01";
        String finCycle2010A2012 = "2012-04-01";

        assertTrue(Architecte.dateValidePourCycle2010_2012(debutCycle2010A2012));
        assertTrue(Architecte.dateValidePourCycle2010_2012("2010-04-02"));
        assertTrue(Architecte.dateValidePourCycle2010_2012("2011-12-13"));
        assertTrue(Architecte.dateValidePourCycle2010_2012("2012-03-31"));
        assertTrue(Architecte.dateValidePourCycle2010_2012(finCycle2010A2012));

        assertFalse(Architecte.dateValidePourCycle2010_2012("2012-04-02"));
        assertFalse(Architecte.dateValidePourCycle2010_2012("2013-01-02"));
    }

    @Test
    public void testDateValidePourCycle2008_2010() {
        assertFalse(Architecte.dateValidePourCycle2008_2010("2007-01-01"));
        assertFalse(Architecte.dateValidePourCycle2008_2010("2008-03-31"));

        String debutCycle2008A2010 = "2008-04-01";
        String finCycle2008A2010 = "2008-07-01";

        assertTrue(Architecte.dateValidePourCycle2008_2010(debutCycle2008A2010));
        assertTrue(Architecte.dateValidePourCycle2008_2010("2008-04-02"));
        assertTrue(Architecte.dateValidePourCycle2008_2010("2009-10-11"));
        assertTrue(Architecte.dateValidePourCycle2008_2010("2010-06-30"));
        assertTrue(Architecte.dateValidePourCycle2008_2010(finCycle2008A2010));

        assertFalse(Architecte.dateValidePourCycle2008_2010("2010-07-02"));
        assertFalse(Architecte.dateValidePourCycle2008_2010("2011-12-13"));
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
        Architecte instance = (Architecte) membre;
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
        Architecte instance = (Architecte) membre;
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
