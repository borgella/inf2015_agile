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
    }

    /**
     * Test of numeroDePermisAPremierCaractereValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisAPremierCaractereValideAT() {
        System.out.println("numeroDePermisAPremierCaractereValide Architectes Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instanceA.numeroDePermisAPremierCaractereValide("T3443");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideAF() {
        System.out.println("numeroDePermisAPremierCaractereValide Architectes False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instanceA.numeroDePermisAPremierCaractereValide("Z3451");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideGT() {
        System.out.println("numeroDePermisAPremierCaractereValide Geologues Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instanceG.numeroDePermisAPremierCaractereValide("BJ3822");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideGF() {
        System.out.println("numeroDePermisAPremierCaractereValide Geologues False");
        //LecteurDeDeclaration instance = null;
        //numeroDePermis = numeroDePermisGF;
        boolean expResult = false;
        boolean result = instanceG.numeroDePermisAPremierCaractereValide("7867YS");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePoT() {
        System.out.println("numeroDePermisAPremierCaractereValide Podiatre Valide");
        //LecteurDeDeclaration instance = null;
        //numeroDePermis = numeroDePermisPoT;
        boolean expResult = true;
        boolean result = instancePo.numeroDePermisAPremierCaractereValide("83453");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePoF() {
        System.out.println("numeroDePermisAPremierCaractereValide Podiatre False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instancePo.numeroDePermisAPremierCaractereValide("560890");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePsT() {
        System.out.println("numeroDePermisAPremierCaractereValide Psychologues Valide");
        //LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instancePs.numeroDePermisAPremierCaractereValide("83723-34");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePsF() {
        System.out.println("numeroDePermisAPremierCaractereValide Psychologues False");
        //LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instancePs.numeroDePermisAPremierCaractereValide("56122_03");
        assertEquals(expResult, result);
    }
    
}
