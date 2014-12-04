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
import org.junit.Ignore;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class LecteurDeDeclarationTest {
    
        JSONObject declarationArchitectes;
        JSONObject declarationGeologues;
        JSONObject declarationPodiatres;
        JSONObject declarationPsychologues;
        LecteurDeDeclaration instance;
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
        declarationGeologues.put("ordre", "géologues");
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
     * Test of erreurDeFormatDetectee method, of class LecteurDeDeclaration.
     */
    @Ignore
    public void testErreurDeFormatDetectee() {
        System.out.println("erreurDeFormatDetectee");
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.erreurDeFormatDetectee();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of numerosDePermisValides method, of class LecteurDeDeclaration.
     */
    // Tests qui échouent
    @Test
    public void testNumerosDePermisValides() {
        System.out.println("Architectes: No. permis invalide");
        boolean expResult = false;
        boolean result = instanceArchitectes.numerosDePermisValides("Z3451");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides3() {
        System.out.println("Geologues: No. permis invalide");
        boolean expResult = false;
        boolean result = instanceGeologues.numerosDePermisValides("JB7867");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides5() {
        System.out.println("Podiatre: No. permis invalide");
        boolean expResult = false;
        boolean result = instancePodiatres.numerosDePermisValides("560890");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides7() {
        System.out.println("Psychologues: No. permis invalide");
        boolean expResult = false;
        boolean result = instancePsychologues.numerosDePermisValides("56122_03");
        assertEquals(expResult, result);
    }
    
    
    // Tests qui réussissent
    @Test
    public void testNumerosDePermisValides2() {
        System.out.println("Architectes: No. permis valide");
        boolean expResult = true;
        boolean result = instanceArchitectes.numerosDePermisValides("T3443");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides4() {
        System.out.println("Geologues: No. permis valide");
        boolean expResult = true;
        boolean result = instanceGeologues.numerosDePermisValides("BJ3822");
        assertEquals(expResult, result);
    }

    @Test
    public void testNumerosDePermisValides6() {
        System.out.println("Podiatre: No. permis valide");
        boolean expResult = true;
        boolean result = instancePodiatres.numerosDePermisValides("83453");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides8() {
        System.out.println("Psychologues: No. permis valide");
        boolean expResult = true;
        boolean result = instancePsychologues.numerosDePermisValides("83723-34");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of numerosDePermisValidesSelonLOrdre method, of class LecteurDeDeclaration.
     */
    // Tests qui échouent
    public void testNumerosDePermisValidesSelonLOrdre() {
        System.out.println("Architectes invalide");
        String numeroDePermisLu = "X4573";
        String formatNumeroPermisValide = "([A|T]{1}[0-9]{4})";
        boolean expResult = false;
        boolean result = instanceArchitectes.numerosDePermisValidesSelonLOrdre(numeroDePermisLu, formatNumeroPermisValide);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValidesSelonLOrdre3() {
        System.out.println("Podiatres invalide");
        String numeroDePermisLu = "56X87";
        String formatNumeroPermisValide = "([0-9]{5})";
        boolean expResult = false;
        boolean result = instancePodiatres.numerosDePermisValidesSelonLOrdre(numeroDePermisLu, formatNumeroPermisValide);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValidesSelonLOrdre5() {
        System.out.println("Psychologues invalide");
        String numeroDePermisLu = "83723!34";
        String formatNumeroPermisValide = "([0-9]{5}[-][0-9]{2})";
        boolean expResult = false;
        boolean result = instancePsychologues.numerosDePermisValidesSelonLOrdre(numeroDePermisLu, formatNumeroPermisValide);
        assertEquals(expResult, result);
    }
    
    // Tests qui réussissent
    @Test
    public void testNumerosDePermisValidesSelonLOrdre2() {
        System.out.println("Architectes valide");
        String numeroDePermisLu = "T3443";
        String formatNumeroPermisValide = "([A|T]{1}[0-9]{4})";
        boolean expResult = true;
        boolean result = instanceArchitectes.numerosDePermisValidesSelonLOrdre(numeroDePermisLu, formatNumeroPermisValide);
        assertEquals(expResult, result);
    }

    @Test
    public void testNumerosDePermisValidesSelonLOrdre4() {
        System.out.println("Podiatres valide");
        String numeroDePermisLu = "83453";
        String formatNumeroPermisValide = "([0-9]{5})";
        boolean expResult = true;
        boolean result = instancePodiatres.numerosDePermisValidesSelonLOrdre(numeroDePermisLu, formatNumeroPermisValide);
        assertEquals(expResult, result);
    }
 
    @Test
    public void testNumerosDePermisValidesSelonLOrdre6() {
        System.out.println("Psychologues valide");
        String numeroDePermisLu = "83723-34";
        String formatNumeroPermisValide = "([0-9]{5}[-][0-9]{2})";
        boolean expResult = true;
        boolean result = instancePsychologues.numerosDePermisValidesSelonLOrdre(numeroDePermisLu, formatNumeroPermisValide);
        assertEquals(expResult, result);
    }

    /**
     * Test of numeroDePermisValideGeologues method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisValideGeologues() {
        String numeroDePermisLu = "XY6734";
        String formatNumeroPermisValide = "([A-Z]{2}[0-9]{4})";
        boolean expResult = false;
        boolean result = instanceGeologues.numeroDePermisValideGeologues(numeroDePermisLu, formatNumeroPermisValide);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumeroDePermisValideGeologues2() {
        String numeroDePermisLu = "BJ6734";
        String formatNumeroPermisValide = "([A-Z]{2}[0-9]{4})";
        boolean expResult = true;
        boolean result = instanceGeologues.numeroDePermisValideGeologues(numeroDePermisLu, formatNumeroPermisValide);
        assertEquals(expResult, result);
    }

    /**
     * Test of produireRapportPourErreurDeFormat method, of class LecteurDeDeclaration.
     */
    @Ignore
    public void testProduireRapportPourErreurDeFormat() {
        System.out.println("produireRapportPourErreurDeFormat");
        LecteurDeDeclaration instance = null;
        JSONObject expResult = null;
        JSONObject result = instance.produireRapportPourErreurDeFormat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of extraireSexe method, of class LecteurDeDeclaration.
     */
    @Ignore
    public void testExtraireSexe() {
        System.out.println("extraireSexe");
        LecteurDeDeclaration instance = null;
        int expResult = 0;
        int result = instance.extraireSexe();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
