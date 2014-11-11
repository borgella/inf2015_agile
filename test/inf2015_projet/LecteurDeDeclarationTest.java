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
        declarationGeologues.put("nom", "Berger");
        declarationGeologues.put("prenom", "Jacques");
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
    public void testNumerosDePermisValides1a() {
        System.out.println("Architectes: No. permis valide");
        boolean expResult = true;
        boolean result = instanceArchitectes.numerosDePermisValides("T3443");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides1b() {
        System.out.println("Architectes: No. permis invalide");
        boolean expResult = false;
        boolean result = instanceArchitectes.numerosDePermisValides("Z3451");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides2a() {
        System.out.println("Geologues: No. permis valide");
        boolean expResult = true;
        boolean result = instanceGeologues.numerosDePermisValides("BJ3822");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides2b() {
        System.out.println("Geologues: No. permis invalide");
        boolean expResult = false;
        boolean result = instanceGeologues.numerosDePermisValides("JB7867");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides3a() {
        System.out.println("Podiatre: No. permis valide");
        boolean expResult = true;
        boolean result = instancePodiatres.numerosDePermisValides("83453");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides3b() {
        System.out.println("Podiatre: No. permis invalide");
        boolean expResult = false;
        boolean result = instancePodiatres.numerosDePermisValides("560890");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides4a() {
        System.out.println("Psychologues: No. permis valide");
        boolean expResult = true;
        boolean result = instancePsychologues.numerosDePermisValides("83723-34");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides4b() {
        System.out.println("Psychologues: No. permis invalide");
        boolean expResult = false;
        boolean result = instancePsychologues.numerosDePermisValides("56122_03");
        assertEquals(expResult, result);
    }
    
}
