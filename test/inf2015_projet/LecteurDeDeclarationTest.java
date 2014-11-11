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
    
        JSONObject declarationArchitectes;
        JSONObject declarationGeologues;
        JSONObject declarationPodiatres;
        JSONObject declarationPsychologues;
        LecteurDeDeclaration instanceArchitectes;
        LecteurDeDeclaration instanceGeologues;
        LecteurDeDeclaration instancePodiatres;
        LecteurDeDeclaration instancePsychologues;
    
    public LecteurDeDeclarationTest() {
        declarationArchitectes = new JSONObject();
        declarationArchitectes.put("ordre", "architectes");
        instanceArchitectes = new LecteurDeDeclaration(declarationArchitectes);
        
        declarationGeologues = new JSONObject();
        declarationGeologues.put("ordre", "geologues");
        instanceGeologues = new LecteurDeDeclaration(declarationGeologues);
        
        declarationPodiatres = new JSONObject();
        declarationPodiatres.put("ordre", "podiatres");
        instancePodiatres = new LecteurDeDeclaration(declarationPodiatres);
        
        declarationPsychologues = new JSONObject();
        declarationPsychologues.put("ordre", "psychologues");
        instancePsychologues = new LecteurDeDeclaration(declarationPsychologues);
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
        boolean expResult = true;
        boolean result = instanceArchitectes.numeroDePermisAPremierCaractereValide("T3443");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideAF() {
        System.out.println("numeroDePermisAPremierCaractereValide Architectes False");
        boolean expResult = false;
        boolean result = instanceArchitectes.numeroDePermisAPremierCaractereValide("Z3451");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideGT() {
        System.out.println("numeroDePermisAPremierCaractereValide Geologues Valide");
        boolean expResult = true;
        boolean result = instanceGeologues.numeroDePermisAPremierCaractereValide("BJ3822");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValideGF() {
        System.out.println("numeroDePermisAPremierCaractereValide Geologues False");
        boolean expResult = false;
        boolean result = instanceGeologues.numeroDePermisAPremierCaractereValide("7867YS");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePoT() {
        System.out.println("numeroDePermisAPremierCaractereValide Podiatre Valide");
        boolean expResult = true;
        boolean result = instancePodiatres.numeroDePermisAPremierCaractereValide("83453");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePoF() {
        System.out.println("numeroDePermisAPremierCaractereValide Podiatre False");
        boolean expResult = false;
        boolean result = instancePodiatres.numeroDePermisAPremierCaractereValide("560890");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePsT() {
        System.out.println("numeroDePermisAPremierCaractereValide Psychologues Valide");
        boolean expResult = true;
        boolean result = instancePsychologues.numeroDePermisAPremierCaractereValide("83723-34");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisAPremierCaractereValidePsF() {
        System.out.println("numeroDePermisAPremierCaractereValide Psychologues False");
        boolean expResult = false;
        boolean result = instancePsychologues.numeroDePermisAPremierCaractereValide("56122_03");
        assertEquals(expResult, result);
    }
    
}
