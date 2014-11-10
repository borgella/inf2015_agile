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
    
    public LecteurDeDeclarationTest() {
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
    @Test
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
     * Test of produireRapportPourErreurDeFormat method, of class LecteurDeDeclaration.
     */
    @Test
    public void testProduireRapportPourErreurDeFormat() {
        System.out.println("produireRapportPourErreurDeFormat");
        LecteurDeDeclaration instance = null;
        JSONObject expResult = null;
        JSONObject result = instance.produireRapportPourErreurDeFormat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
