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
        LecteurDeDeclaration instanceArchitectes;
        LecteurDeDeclaration instanceGeologues;
        LecteurDeDeclaration instancePodiatres;
        LecteurDeDeclaration instancePsychologues;
        String champsNumeroDePermis = "numero_de_permis";
        String numeroPermisArchitectes = "([A|T]{1}[0-9]{4})";
        String numeroPermisPsychologues = "([0-9]{5}[-][0-9]{2})";
        String numeroPermisGeologues = "([A-Z]{2}[0-9]{4})";
        String numeroPermisPodiatres = "([0-9]{5})";
    
    public LecteurDeDeclarationTest() {
        declarationArchitectes = new JSONObject();
        declarationArchitectes.put("numero_de_permis", "T3443");
        declarationArchitectes.put("ordre", "architectes");
        declarationArchitectes.put("cycle", "2008-2010");
        //declarationArchitectes.put("heures_transferees_du_cycle_precedent", 22);
        instanceArchitectes = new LecteurDeDeclaration(declarationArchitectes);
        
        declarationGeologues = new JSONObject();
        declarationGeologues.put("nom", "Berger");
        declarationGeologues.put("prenom", "Jacques");
        declarationGeologues.put("numero_de_permis", "BJ3822");
        declarationGeologues.put("ordre", "géologues");
        instanceGeologues = new LecteurDeDeclaration(declarationGeologues);
        
        declarationPodiatres = new JSONObject();
        declarationPodiatres.put("numero_de_permis", "83453");
        declarationPodiatres.put("sexe", 2);
        declarationPodiatres.put("ordre", "podiatres");
        instancePodiatres = new LecteurDeDeclaration(declarationPodiatres);
        
        declarationPsychologues = new JSONObject();
        declarationPsychologues.put("numero_de_permis", "83723-34");
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
     * Test of formatAcceptePourNumeroDePermis method, of class LecteurDeDeclaration.
     */
    // Tests qui échouent
    @Test
    public void testFormatAcceptePourNumeroDePermis() {
        System.out.println("formatAcceptePourNumeroDePermis");
        boolean result;
        if (instanceArchitectes.champsTexteExiste(champsNumeroDePermis)) {
            result = instanceArchitectes.numerosDePermisValides("TR443");
        } else {
            result = false;
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourNumeroDePermis3() {
        System.out.println("formatAcceptePourNumeroDePermis");
        boolean result;
        if (instanceGeologues.champsTexteExiste(champsNumeroDePermis)) {
            result = instanceGeologues.numerosDePermisValides("JB1280");
        } else {
            result = false;
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourNumeroDePermis5() {
        System.out.println("formatAcceptePourNumeroDePermis");
        boolean result;
        if (instancePodiatres.champsTexteExiste(champsNumeroDePermis)) {
            result = instancePodiatres.numerosDePermisValides("324983");
        } else {
            result = false;
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourNumeroDePermis7() {
        System.out.println("formatAcceptePourNumeroDePermis");
        boolean result;
        if (instancePsychologues.champsTexteExiste(champsNumeroDePermis)) {
            result = instancePsychologues.numerosDePermisValides("3463E");
        } else {
            result = false;
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    // Tests qui réussissent
    @Test
    public void testFormatAcceptePourNumeroDePermis2() {
        System.out.println("formatAcceptePourNumeroDePermis");
        boolean result;
        if (instanceArchitectes.champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declarationArchitectes.getString(champsNumeroDePermis);
            result = instanceArchitectes.numerosDePermisValides(numeroDePermis);
        } else {
            result = false;
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourNumeroDePermis4() {
        System.out.println("formatAcceptePourNumeroDePermis");
        boolean result;
        if (instanceGeologues.champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declarationGeologues.getString(champsNumeroDePermis);
            result = instanceGeologues.numerosDePermisValides(numeroDePermis);
        } else {
            result = false;
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourNumeroDePermis6() {
        System.out.println("formatAcceptePourNumeroDePermis");
        boolean result;
        if (instancePodiatres.champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declarationPodiatres.getString(champsNumeroDePermis);
            result = instancePodiatres.numerosDePermisValides(numeroDePermis);
        } else {
            result = false;
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourNumeroDePermis8() {
        System.out.println("formatAcceptePourNumeroDePermis");
        boolean result;
        if (instancePsychologues.champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declarationPsychologues.getString(champsNumeroDePermis);
            result = instancePsychologues.numerosDePermisValides(numeroDePermis);
        } else {
            result = false;
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of numerosDePermisValides method, of class LecteurDeDeclaration.
     */
    // Tests qui échouent
    @Test
    public void testNumerosDePermisValides() {
        System.out.println("Architectes: No. permis invalide");
        boolean result;
        if(declarationArchitectes.getString("ordre").equals("architectes")) {
            result = instanceArchitectes.numerosDePermisValidesSelonLOrdre("Z3451", numeroPermisArchitectes);
        } else if (declarationArchitectes.getString("ordre").equals("psychologues")) {
            result = instanceArchitectes.numerosDePermisValidesSelonLOrdre("Z3451", numeroPermisPsychologues);
        } else if (declarationArchitectes.getString("ordre").equals("géologues")) {
            result = instanceArchitectes.numeroDePermisValideGeologues("Z3451", numeroPermisGeologues);
        } else {
            result = instanceArchitectes.numerosDePermisValidesSelonLOrdre("Z3451", numeroPermisPodiatres);
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides3() {
        System.out.println("Geologues: No. permis invalide");
        boolean result;
        if(declarationGeologues.getString("ordre").equals("architectes")) {
            result = instanceGeologues.numerosDePermisValidesSelonLOrdre("JB7867", numeroPermisArchitectes);
        } else if (declarationGeologues.getString("ordre").equals("psychologues")) {
            result = instanceGeologues.numerosDePermisValidesSelonLOrdre("JB7867", numeroPermisPsychologues);
        } else if (declarationGeologues.getString("ordre").equals("géologues")) {
            result = instanceGeologues.numeroDePermisValideGeologues("JB7867", numeroPermisGeologues);
        } else {
            result = instanceGeologues.numerosDePermisValidesSelonLOrdre("JB7867", numeroPermisPodiatres);
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides5() {
        System.out.println("Podiatre: No. permis invalide");
        boolean result;
        if(declarationPodiatres.getString("ordre").equals("architectes")) {
            result = instancePodiatres.numerosDePermisValidesSelonLOrdre("560890", numeroPermisArchitectes);
        } else if (declarationPodiatres.getString("ordre").equals("psychologues")) {
            result = instancePodiatres.numerosDePermisValidesSelonLOrdre("560890", numeroPermisPsychologues);
        } else if (declarationPodiatres.getString("ordre").equals("géologues")) {
            result = instancePodiatres.numeroDePermisValideGeologues("560890", numeroPermisGeologues);
        } else {
            result = instancePodiatres.numerosDePermisValidesSelonLOrdre("560890", numeroPermisPodiatres);
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides7() {
        System.out.println("Psychologues: No. permis invalide");
        boolean result;
        if(declarationPsychologues.getString("ordre").equals("architectes")) {
            result = instancePsychologues.numerosDePermisValidesSelonLOrdre("56122_03", numeroPermisArchitectes);
        } else if (declarationPsychologues.getString("ordre").equals("psychologues")) {
            result = instancePsychologues.numerosDePermisValidesSelonLOrdre("56122_03", numeroPermisPsychologues);
        } else if (declarationPsychologues.getString("ordre").equals("géologues")) {
            result = instancePsychologues.numeroDePermisValideGeologues("56122_03", numeroPermisGeologues);
        } else {
            result = instancePsychologues.numerosDePermisValidesSelonLOrdre("56122_03", numeroPermisPodiatres);
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    
    // Tests qui réussissent
    @Test
    public void testNumerosDePermisValides2() {
        System.out.println("Architectes: No. permis valide");
        String numeroDePermis = declarationArchitectes.getString("numero_de_permis");
        boolean result;
        if(declarationArchitectes.getString("ordre").equals("architectes")) {
            result = instanceArchitectes.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisArchitectes);
        } else if (declarationArchitectes.getString("ordre").equals("psychologues")) {
            result = instanceArchitectes.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPsychologues);
        } else if (declarationArchitectes.getString("ordre").equals("géologues")) {
            result = instanceArchitectes.numeroDePermisValideGeologues(numeroDePermis, numeroPermisGeologues);
        } else {
            result = instanceArchitectes.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPodiatres);
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides4() {
        System.out.println("Geologues: No. permis valide");
        String numeroDePermis = declarationGeologues.getString("numero_de_permis");
        boolean result;
        if(declarationGeologues.getString("ordre").equals("architectes")) {
            result = instanceGeologues.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisArchitectes);
        } else if (declarationGeologues.getString("ordre").equals("psychologues")) {
            result = instanceGeologues.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPsychologues);
        } else if (declarationGeologues.getString("ordre").equals("géologues")) {
            result = instanceGeologues.numeroDePermisValideGeologues(numeroDePermis, numeroPermisGeologues);
        } else {
            result = instanceGeologues.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPodiatres);
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    @Test
    public void testNumerosDePermisValides6() {
        System.out.println("Podiatre: No. permis valide");
        String numeroDePermis = declarationPodiatres.getString("numero_de_permis");
        boolean result;
        if(declarationPodiatres.getString("ordre").equals("architectes")) {
            result = instancePodiatres.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisArchitectes);
        } else if (declarationPodiatres.getString("ordre").equals("psychologues")) {
            result = instancePodiatres.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPsychologues);
        } else if (declarationPodiatres.getString("ordre").equals("géologues")) {
            result = instancePodiatres.numeroDePermisValideGeologues(numeroDePermis, numeroPermisGeologues);
        } else {
            result = instancePodiatres.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPodiatres);
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNumerosDePermisValides8() {
        System.out.println("Psychologues: No. permis valide");
        String numeroDePermis = declarationPsychologues.getString("numero_de_permis");
        boolean result;
        if(declarationPsychologues.getString("ordre").equals("architectes")) {
            result = instancePsychologues.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisArchitectes);
        } else if (declarationPsychologues.getString("ordre").equals("psychologues")) {
            result = instancePsychologues.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPsychologues);
        } else if (declarationPsychologues.getString("ordre").equals("géologues")) {
            result = instancePsychologues.numeroDePermisValideGeologues(numeroDePermis, numeroPermisGeologues);
        } else {
            result = instancePsychologues.numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPodiatres);
        }
        boolean expResult = true;
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
     * Test of formatAcceptePourOrdre method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourOrdre() {
        System.out.println("formatAcceptePourOrdre");
        boolean expResult = false;
        boolean result = instanceGeologues.ordreReconnu("geologues");   // sans accent = false
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourOrdre2() {
        System.out.println("formatAcceptePourOrdre");
        String numeroDePermis = declarationGeologues.getString("ordre");
        boolean expResult = true;
        boolean result = instanceGeologues.ordreReconnu(numeroDePermis);
        assertEquals(expResult, result);
    }

    /**
     * Test of ordreReconnu method, of class LecteurDeDeclaration.
     */
    @Ignore
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
     * Test of formatAcceptePourPrenomOuNom method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourPrenomOuNom() {
        System.out.println("formatAcceptePourPrenomOuNom");
        String nomChamps = "";
        boolean expResult = false;
        boolean result = instanceGeologues.formatAcceptePourPrenomOuNom(nomChamps);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatAcceptePourPrenomOuNom method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourPrenomOuNom2() {
        System.out.println("formatAcceptePourPrenomOuNom");
        String nomChamps = "nom";
        boolean expResult = true;
        boolean result = instanceGeologues.formatAcceptePourPrenomOuNom(nomChamps);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatAcceptePourSexe method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourSexe() {
        System.out.println("formatAcceptePourSexe");
        boolean expResult = false;
        boolean result = instancePodiatres.sexeReconnu(3);
        assertEquals(expResult, result);
    }

    @Test
    public void testFormatAcceptePourSexe2() {
        System.out.println("formatAcceptePourSexe");
        int sexe = declarationPodiatres.getInt("sexe");
        boolean expResult = true;
        boolean result = instancePodiatres.sexeReconnu(sexe);
        assertEquals(expResult, result);
    }

    /**
     * Test of sexeReconnu method, of class LecteurDeDeclaration.
     */
    @Ignore
    public void testSexeReconnu() {
        System.out.println("sexeReconnu");
        int sexe = 0;
        boolean expResult = false;
        boolean result = LecteurDeDeclaration.sexeReconnu(sexe);
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
        boolean expResult = false;
        boolean result = instancePsychologues.champsTexteExiste("cycle");
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourCycle2() {
        System.out.println("formatAcceptePourCycle");
        boolean expResult = true;
        boolean result = instanceArchitectes.champsTexteExiste("cycle");
        assertEquals(expResult, result);
    }

    /**
     * Test of formatAcceptePourHeuresTransfereesSelonOrdre method, of class LecteurDeDeclaration.
     */
    // Test qui échoue
    @Test
    public void testFormatAcceptePourHeuresTransfereesSelonOrdre() {
        System.out.println("formatAcceptePourHeuresTransfereesSelonOrdre");
        String champsHeuresTransferees = "heures_transferees_du_cycle_precedent";
        String ordre = declarationArchitectes.getString("ordre");
        boolean result;
        if (ordre.equals("architectes")) {
            result = instanceArchitectes.champsNumeriqueExiste(champsHeuresTransferees);
        } else {
            result = true;
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    // Tests qui réussissent
    @Ignore
    public void testFormatAcceptePourHeuresTransfereesSelonOrdre2() {
        System.out.println("formatAcceptePourHeuresTransfereesSelonOrdre");
        String champsHeuresTransferees = "heures_transferees_du_cycle_precedent";
        String ordre = declarationArchitectes.getString("ordre");
        boolean result;
        if (ordre.equals("architectes")) {
            result = instanceArchitectes.champsNumeriqueExiste(champsHeuresTransferees);
        } else {
            result = true;
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourHeuresTransfereesSelonOrdre4() {
        System.out.println("formatAcceptePourHeuresTransfereesSelonOrdre");
        String champsHeuresTransferees = "heures_transferees_du_cycle_precedent";
        String ordre = declarationPodiatres.getString("ordre");
        boolean result;
        if (ordre.equals("architectes")) {
            result = instancePodiatres.champsNumeriqueExiste(champsHeuresTransferees);
        } else {
            result = true;
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of champsNumeriqueExiste method, of class LecteurDeDeclaration.
     */
    @Ignore
    public void testChampsNumeriqueExiste() {
        System.out.println("champsNumeriqueExiste");
        String nomChamps = "";
        LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.champsNumeriqueExiste(nomChamps);
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
