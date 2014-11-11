/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

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
    
        JSONObject declarationA;
        JSONObject declarationG;
        JSONObject declarationPo;
        JSONObject declarationPs;
        String numeroDePermisAT = "T3443";
        String numeroDePermisAF = "Z3451";
        String numeroDePermisGT = "BJ3822";
        String numeroDePermisGF = "56122.03";
        String numeroDePermisPoT = "83453";
        String numeroDePermisPoF = "7867YS";
        String numeroDePermisPsT = "83723-34";
        String numeroDePermisPsF = "091235";
        LecteurDeDeclaration instanceA;
        LecteurDeDeclaration instanceG;
        LecteurDeDeclaration instancePo;
        LecteurDeDeclaration instancePs;
    
    public LecteurDeDeclarationTest() {
        declarationA = new JSONObject();
        declarationA.put("ordre", "architectes");
        instanceA = new LecteurDeDeclaration(declarationA);
        
        declarationG = new JSONObject();
        declarationG.put("ordre", "geologues");
        instanceG = new LecteurDeDeclaration(declarationG);
        
        declarationPo = new JSONObject();
        declarationPo.put("ordre", "podiatres");
        instancePo = new LecteurDeDeclaration(declarationPo);
        
        declarationPs = new JSONObject();
        declarationPs.put("ordre", "psychologues");
        instancePs = new LecteurDeDeclaration(declarationPs);
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
        instanceA = null;
        instanceG = null;
        instancePo = null;
        instancePs = null;
    }

    /**
     * Test of numeroDePermisAPremierCaractereValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisAPremierCaractereValideAT() {
        System.out.println("numeroDePermisAPremierCaractereValide Architectes Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instanceA.numeroDePermisAPremierCaractereValide(numeroDePermisAT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideAF() {
        System.out.println("numeroDePermisAPremierCaractereValide Architectes False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instanceA.numeroDePermisAPremierCaractereValide(numeroDePermisAF);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideGT() {
        System.out.println("numeroDePermisAPremierCaractereValide Geologues Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instanceG.numeroDePermisAPremierCaractereValide(numeroDePermisGT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideGF() {
        System.out.println("numeroDePermisAPremierCaractereValide Geologues False");
        //LecteurDeDeclaration instance = null;
        //numeroDePermis = numeroDePermisGF;
        boolean expResult = false;
        boolean result = instanceG.numeroDePermisAPremierCaractereValide(numeroDePermisGF);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePoT() {
        System.out.println("numeroDePermisAPremierCaractereValide Podiatre Valide");
        //LecteurDeDeclaration instance = null;
        //numeroDePermis = numeroDePermisPoT;
        boolean expResult = true;
        boolean result = instancePo.numeroDePermisAPremierCaractereValide(numeroDePermisPoT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePoF() {
        System.out.println("numeroDePermisAPremierCaractereValide Podiatre False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instancePo.numeroDePermisAPremierCaractereValide(numeroDePermisPoF);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePsT() {
        System.out.println("numeroDePermisAPremierCaractereValide Psychologues Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instancePs.numeroDePermisAPremierCaractereValide(numeroDePermisPsT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePsF() {
        System.out.println("numeroDePermisAPremierCaractereValide Psychologues False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instancePs.numeroDePermisAPremierCaractereValide(numeroDePermisPsF);
        assertEquals(expResult, result);
    }
    
}
