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
    
    String numeroPermisA1;
    String ordreA1;
    String cycleA1;
    
    public LecteurDeDeclarationTest() {
        numeroPermisA1 = "3456F";
        ordreA1 = "Architectes";
        cycleA1 = "2013-2016";
        
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
        
        /*JSONObject declaration = new JSONObject();
        declaration.put("numero_de_permis", numeroPermisA1);
        declaration.put("ordre", ordreA1);
        declaration.put("cycle", cycleA1);
        declaration.put("heures_transferees_du_cycle_precedent", 2);
        JSONArray activites = new JSONArray();
        activites.add(new JSONObject().put("description", "Visite d'établissements architecturaux"));
        activites.add(new JSONObject().put("categorie", "cours"));
        activites.add(new JSONObject().put("heures", 17));
        activites.add(new JSONObject().put("date", "2013-06-09"));
        declaration.put("activites", activites);
        LecteurDeDeclaration instance = new LecteurDeDeclaration(declaration);*/
        
        LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.erreurDeFormatDetectee();
        assertEquals("Valeurs correctes: ", expResult, result);
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

    /**
     * Test of formatAcceptePourNumeroDePermis method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourNumeroDePermis() {
        System.out.println("formatAcceptePourNumeroDePermis");
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.formatAcceptePourNumeroDePermis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of champsTexteExiste method, of class LecteurDeDeclaration.
     */
    @Test
    public void testChampsTexteExiste() {
        System.out.println("champsTexteExiste");
        String nomChamps = "";
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.champsTexteExiste(nomChamps);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numeroDePermisReconnu method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisReconnu() {
        System.out.println("numeroDePermisReconnu");
        String numeroDePermis = "";
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.numeroDePermisReconnu(numeroDePermis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numeroDePermisALongueurValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisALongueurValide() {
        System.out.println("numeroDePermisALongueurValide");
        String numeroDePermis = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.numeroDePermisALongueurValide(numeroDePermis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numeroDePermisAContenuValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisAContenuValide() {
        System.out.println("numeroDePermisAContenuValide");
        String numeroDePermis = "";
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.numeroDePermisAContenuValide(numeroDePermis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numeroDePermisAPremierCaractereValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisAPremierCaractereValide() {
        System.out.println("numeroDePermisAPremierCaractereValide");
        String numeroDePermis = "";
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.numeroDePermisAPremierCaractereValide(numeroDePermis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numeroDePermisTermineParQuatreChiffres method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisTermineParQuatreChiffres() {
        System.out.println("numeroDePermisTermineParQuatreChiffres");
        String numeroDePermis = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.numeroDePermisTermineParQuatreChiffres(numeroDePermis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of texteEstNumerique method, of class LecteurDeDeclaration.
     */
    @Test
    public void testTexteEstNumerique() {
        System.out.println("texteEstNumerique");
        String texte = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.texteEstNumerique(texte);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourOrdre method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourOrdre() {
        System.out.println("formatAcceptePourOrdre");
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.formatAcceptePourOrdre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ordreReconnu method, of class LecteurDeDeclaration.
     */
    @Test
    public void testOrdreReconnu() {
        System.out.println("ordreReconnu");
        String ordre = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.ordreReconnu(ordre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourCycle method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourCycle() {
        System.out.println("formatAcceptePourCycle");
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.formatAcceptePourCycle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourHeuresTransfereesSelonOrdre method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourHeuresTransfereesSelonOrdre() {
        System.out.println("formatAcceptePourHeuresTransfereesSelonOrdre");
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.formatAcceptePourHeuresTransfereesSelonOrdre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of champsNumeriqueExiste method, of class LecteurDeDeclaration.
     */
    @Test
    public void testChampsNumeriqueExiste() {
        System.out.println("champsNumeriqueExiste");
        String nomChamps = "";
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.champsNumeriqueExiste(nomChamps);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourTableauActivites method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourTableauActivites() {
        System.out.println("formatAcceptePourTableauActivites");
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.formatAcceptePourTableauActivites();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of champsTableauJSONExiste method, of class LecteurDeDeclaration.
     */
    @Test
    public void testChampsTableauJSONExiste() {
        System.out.println("champsTableauJSONExiste");
        String nomChamps = "";
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.champsTableauJSONExiste(nomChamps);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourChaqueActivite method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourChaqueActivite() {
        System.out.println("formatAcceptePourChaqueActivite");
        JSONArray activites = null;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.formatAcceptePourChaqueActivite(activites);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourActivite method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourActivite() {
        System.out.println("formatAcceptePourActivite");
        JSONObject activite = null;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.formatAcceptePourActivite(activite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourDescription method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourDescription() {
        System.out.println("formatAcceptePourDescription");
        JSONObject activite = null;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.formatAcceptePourDescription(activite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of champsTexteExistePourActivite method, of class LecteurDeDeclaration.
     */
    @Test
    public void testChampsTexteExistePourActivite() {
        System.out.println("champsTexteExistePourActivite");
        String nomChamps = "";
        JSONObject activite = null;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.champsTexteExistePourActivite(nomChamps, activite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of descriptionReconnu method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDescriptionReconnu() {
        System.out.println("descriptionReconnu");
        String description = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.descriptionReconnu(description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourCategorie method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourCategorie() {
        System.out.println("formatAcceptePourCategorie");
        JSONObject activite = null;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.formatAcceptePourCategorie(activite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourHeures method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourHeures() {
        System.out.println("formatAcceptePourHeures");
        JSONObject activite = null;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.formatAcceptePourHeures(activite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of champsNumeriqueExistePourActivite method, of class LecteurDeDeclaration.
     */
    @Test
    public void testChampsNumeriqueExistePourActivite() {
        System.out.println("champsNumeriqueExistePourActivite");
        String nomChamps = "";
        JSONObject activite = null;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.champsNumeriqueExistePourActivite(nomChamps, activite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of heuresValidesPourActivite method, of class LecteurDeDeclaration.
     */
    @Test
    public void testHeuresValidesPourActivite() {
        System.out.println("heuresValidesPourActivite");
        int heures = 0;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.heuresValidesPourActivite(heures);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatAcceptePourDate method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourDate() {
        System.out.println("formatAcceptePourDate");
        JSONObject activite = null;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.formatAcceptePourDate(activite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateEnFormatReconnu method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateEnFormatReconnu() {
        System.out.println("dateEnFormatReconnu");
        String date = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.dateEnFormatReconnu(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateALongueurValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateALongueurValide() {
        System.out.println("dateALongueurValide");
        String date = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.dateALongueurValide(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateAContenuValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAContenuValide() {
        System.out.println("dateAContenuValide");
        String date = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.dateAContenuValide(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateASeperateursValides method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateASeperateursValides() {
        System.out.println("dateASeperateursValides");
        String date = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.dateASeperateursValides(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateAComposantesNumeriquesValides method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAComposantesNumeriquesValides() {
        System.out.println("dateAComposantesNumeriquesValides");
        String date = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.dateAComposantesNumeriquesValides(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateAUneAnneeValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAUneAnneeValide() {
        System.out.println("dateAUneAnneeValide");
        String anneeEnTexte = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.dateAUneAnneeValide(anneeEnTexte);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateAUnMoisValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAUnMoisValide() {
        System.out.println("dateAUnMoisValide");
        String moisEnTexte = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.dateAUnMoisValide(moisEnTexte);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateAUnJourValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAUnJourValide() {
        System.out.println("dateAUnJourValide");
        String jourEnTexte = "";
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.dateAUnJourValide(jourEnTexte);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of messageDErreurPourDeclarationInvalide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testMessageDErreurPourDeclarationInvalide() {
        System.out.println("messageDErreurPourDeclarationInvalide");
        String expResult = "";
        String result = LecteurDeDeclaration.messageDErreurPourDeclarationInvalide();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
