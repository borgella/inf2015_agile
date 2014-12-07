/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professionnels;

import inf2015_projet.MockJson;
import java.util.ArrayList;
import net.sf.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import net.sf.json.JSONArray;
/**
 *
 * @author QQ1403
 */
public class MembreTest {
    
    MockJson jsongenere = new MockJson();
    JSONObject declaration_json = jsongenere.retournerUnJSONObject();
    JSONArray liste_activite = jsongenere.getActivites();
    
    public MembreTest() {
    }

    /**
     * Test of ajouterActivitePourMembre method, of class Membre.
     */
    @Test
    public void testAjouterActivitePourMembre() {
    }

    /**
     * Test of genererMembre method, of class Membre.
     */
    @Test
    public void testGenererMembre() {
        System.out.println("genererMembre");
        JSONObject declaration = declaration_json;
        Membre expResult = Membre.genererMembre(declaration);
        Membre result = expResult ;
        assertEquals(expResult, result);
    }

    /**
     * Test of getCycle method, of class Membre.
     */
    @Test
    public void testGetCycle() {
        System.out.println("getOrdre");
        Membre instance = Membre.genererMembre(declaration_json);
        String expResult = instance.getOrdre();
        String result = declaration_json.getString("ordre");
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrdre method, of class Membre.
     */
    @Test
    public void testGetOrdre() {
        System.out.println("getOrdre");
        Membre instance = Membre.genererMembre(declaration_json);
        String expResult = declaration_json.getString("ordre");
        String result = instance.getOrdre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSexe method, of class Membre.
     */
    @Test
    public void testGetSexe() {
        System.out.println("getSexe");
        Membre instance = Membre.genererMembre(declaration_json);
        int expResult = declaration_json.getInt("sexe");
        int result = instance.getSexe();
        assertEquals(expResult, result);
    }

    /**
     * Test of regroupementDesCategories method, of class Membre.
     */
    @Test
    public void testRegroupementDesCategories() {
        System.out.println("regroupementDesCategories");
        Membre instance = Membre.genererMembre(declaration_json);
        JSONObject jsonObject_du_tableau_array = liste_activite.getJSONObject(0);
        String categorie = jsonObject_du_tableau_array.getString("categorie");
        int expResult = 1;
        int result = instance.regroupementDesCategories(categorie);
        assertEquals(expResult, result);
    }

    /**
     * Test of premiereCategorie method, of class Membre.
     */
    @Test
    public void testPremiereCategorie() {
        System.out.println("premiereCategorie");
        JSONObject jsonobject_array = liste_activite.getJSONObject(0);
        String categorie = jsonobject_array.getString("categorie");
        Membre instance = Membre.genererMembre(declaration_json);
        int expResult = 1;
        int result = instance.premiereCategorie(categorie);
        assertEquals(expResult, result);        
    }

    /**
     * Test of deuxiemeCategorie method, of class Membre.
     */
    @Test
    public void testDeuxiemeCategorie() {
        System.out.println("deuxiemeCategorie");
        JSONObject jsonobject_array = liste_activite.getJSONObject(3);
        String categorie = jsonobject_array.getString("categorie");
        Membre instance = Membre.genererMembre(declaration_json);
        int expResult = 2;
        int result = instance.deuxiemeCategorie(categorie);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of troisiemeCategorie method, of class Membre.
     */
    @Test
    public void testTroisiemeCategorie() {
        System.out.println("troisiemeCategorie");
        JSONObject jsonobject_array = liste_activite.getJSONObject(2);
        String categorie = jsonobject_array.getString("categorie");
        Membre instance = Membre.genererMembre(declaration_json);
        int expResult = 3;
        int result = instance.troisiemeCategorie(categorie);
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenirNombreActivitesValides method, of class Membre.
     */
    @Test
    public void testObtenirNombreActivitesValides() {   
    }

    /**
     * Test of obtenirNombreActivitesValidesParCategorie method, of class Membre.
     */
    @Test
    public void testObtenirNombreActivitesValidesParCategorie() {
    }

    public class MembreImpl extends Membre {

        public MembreImpl() {
            super("");
        }

        
        public void ajouterActivitePourMembre(JSONObject activite) {
        }

        public String getCycle() {
            return "";
        }

        public int obtenirNombreActivitesValides() {
            return 0;
        }

        public int obtenirNombreActivitesValidesParCategorie(String categorie) {
            return 0;
        }

        public boolean dateValidePourMembre(String date) {
            return false;
        }

        @Override
        public ArrayList getActivitesRefusees() {
            return null;
        }
    }
    
}
