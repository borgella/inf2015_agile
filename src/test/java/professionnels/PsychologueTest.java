/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professionnels;

import java.util.ArrayList;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author QQ1403
 */
public class PsychologueTest {
    
    public PsychologueTest() {
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
     * Test of ajouterActivitePourMembre method, of class Psychologue.
     */
    @Test
    public void testAjouterActivitePourMembre() {
        System.out.println("ajouterActivitePourMembre");
        JSONObject activite = null;
        Psychologue instance = new Psychologue();
        instance.ajouterActivitePourMembre(activite);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateValidePourMembre method, of class Psychologue.
     */
    @Test
    public void testDateValidePourMembre() {
        System.out.println("dateValidePourMembre");
        String date = "";
        Psychologue instance = new Psychologue();
        boolean expResult = false;
        boolean result = instance.dateValidePourMembre(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCycle method, of class Psychologue.
     */
    @Test
    public void testGetCycle() {
        System.out.println("getCycle");
        Psychologue instance = new Psychologue();
        String expResult = "";
        String result = instance.getCycle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of premiereCategorie method, of class Psychologue.
     */
    @Test
    public void testPremiereCategorie() {
        System.out.println("premiereCategorie");
        String categorie = "";
        Psychologue instance = new Psychologue();
        int expResult = 0;
        int result = instance.premiereCategorie(categorie);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deuxiemeCategorie method, of class Psychologue.
     */
    @Test
    public void testDeuxiemeCategorie() {
        System.out.println("deuxiemeCategorie");
        String categorie = "";
        Psychologue instance = new Psychologue();
        int expResult = 0;
        int result = instance.deuxiemeCategorie(categorie);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of troisiemeCategorie method, of class Psychologue.
     */
    @Test
    public void testTroisiemeCategorie() {
        System.out.println("troisiemeCategorie");
        String categorie = "";
        Psychologue instance = new Psychologue();
        int expResult = 0;
        int result = instance.troisiemeCategorie(categorie);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivitesRefusees method, of class Psychologue.
     */
    @Test
    public void testGetActivitesRefusees() {
        System.out.println("getActivitesRefusees");
        Psychologue instance = new Psychologue();
        ArrayList expResult = null;
        ArrayList result = instance.getActivitesRefusees();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivitesAcceptees method, of class Psychologue.
     */
    @Test
    public void testGetActivitesAcceptees() {
        System.out.println("getActivitesAcceptees");
        Psychologue instance = new Psychologue();
        ArrayList expResult = null;
        ArrayList result = instance.getActivitesAcceptees();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenirNombreActivitesValides method, of class Psychologue.
     */
    @Test
    public void testObtenirNombreActivitesValides() {
        System.out.println("obtenirNombreActivitesValides");
        Psychologue instance = new Psychologue();
        int expResult = 0;
        int result = instance.obtenirNombreActivitesValides();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenirNombreActivitesValidesParCategorie method, of class Psychologue.
     */
    @Test
    public void testObtenirNombreActivitesValidesParCategorie() {
        System.out.println("obtenirNombreActivitesValidesParCategorie");
        String categorie = "";
        Psychologue instance = new Psychologue();
        int expResult = 0;
        int result = instance.obtenirNombreActivitesValidesParCategorie(categorie);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
