/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import java.io.IOException;
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
 * @author Chelny
 */
public class LecteurDeDeclarationTest {
    
        JSONObject declaration;
        JSONArray activites;
        String numeroDePermisAT = "T5600";
        String numeroDePermisAF = "Z3451";
        String numeroDePermisGT = "35288-03";
        String numeroDePermisGF = "56122.03";
        String numeroDePermisPoT = "RR5460";
        String numeroDePermisPoF = "7867YS";
        String numeroDePermisPsT = "67002";
        String numeroDePermisPsF = "091235";
        LecteurDeDeclaration instance;
    
    public LecteurDeDeclarationTest() {
        declaration = new JSONObject();
        declaration.put("numero_de_permis", numeroDePermisAT);
        declaration.put("ordre", "Architectes");
        declaration.put("cycle", "2012-2014");
        declaration.put("heures_transferees_du_cycle_precedent", 2);
        activites = new JSONArray();
        activites.add(new JSONObject().put("description", "Visite d'établissements architecturaux"));
        activites.add(new JSONObject().put("categorie", "cours"));
        activites.add(new JSONObject().put("heures", 17));
        activites.add(new JSONObject().put("date", "2013-06-09"));
        declaration.put("activites", activites);
        instance = new LecteurDeDeclaration(declaration);
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
     * Test of numeroDePermisAPremierCaractereValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisAPremierCaractereValideAT() {
        System.out.println("numeroDePermisAPremierCaractereValide Architectes Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermisAT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideAF() {
        System.out.println("numeroDePermisAPremierCaractereValide Architectes False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermisAF);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideGT() {
        System.out.println("numeroDePermisAPremierCaractereValide Geologues Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermisGT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideGF() {
        System.out.println("numeroDePermisAPremierCaractereValide Geologues False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermisGF);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePoT() {
        System.out.println("numeroDePermisAPremierCaractereValide Podiatre Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermisPoT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePoF() {
        System.out.println("numeroDePermisAPremierCaractereValide Podiatre False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermisPoF);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePsT() {
        System.out.println("numeroDePermisAPremierCaractereValide Psychologues Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermisPsT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePsF() {
        System.out.println("numeroDePermisAPremierCaractereValide Psychologues False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermisPsF);
        assertEquals(expResult, result);
    }
    
}
