/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import net.sf.json.JSONArray;
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
        JSONObject activitePod;
        JSONObject activitePsy;
        JSONArray activitesPod;
        JSONArray activitesPsy;
        LecteurDeDeclaration instanceArchitectes;
        LecteurDeDeclaration instanceGeologues;
        LecteurDeDeclaration instancePodiatres;
        LecteurDeDeclaration instancePsychologues;
        String champsNumeroDePermis = "numero_de_permis";
    
    public LecteurDeDeclarationTest() {
        declarationArchitectes = new JSONObject();
        declarationArchitectes.accumulate("nom", "");
        declarationArchitectes.accumulate("prenom", "Duplan");
        declarationArchitectes.accumulate("sexe", 22);
        declarationArchitectes.accumulate("numero_de_permis", "T3443");
        declarationArchitectes.accumulate("ordre", "architectes");
        declarationArchitectes.accumulate("cycle", "2008-2010");
        //declarationArchitectes.accumulate("heures_transferees_du_cycle_precedent", 22);
        instanceArchitectes = new LecteurDeDeclaration(declarationArchitectes);
        
        declarationGeologues = new JSONObject();
        declarationGeologues.accumulate("nom", "Berger");
        declarationGeologues.accumulate("prenom", "Jacques");
        declarationGeologues.accumulate("numero_de_permis", "BJ3822");
        declarationGeologues.accumulate("ordre", "géologues");
        instanceGeologues = new LecteurDeDeclaration(declarationGeologues);
        
        declarationPodiatres = new JSONObject();
        declarationPodiatres.accumulate("numero_de_permis", "83453");
        declarationPodiatres.accumulate("sexe", 2);
        declarationPodiatres.accumulate("ordre", "podiatres");
        activitePod = new JSONObject();
        activitePod.accumulate("description", "Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans");
        activitePod.accumulate("categorie", "cours");
        activitePod.accumulate("heures", 25);
        activitePod.accumulate("date", "2013-04-01");
        activitesPod = new JSONArray();
        activitesPod.add(activitePod);
        declarationPodiatres.accumulate("activites", activitesPod);
        instancePodiatres = new LecteurDeDeclaration(declarationPodiatres);
        
        declarationPsychologues = new JSONObject();
        declarationPsychologues.accumulate("numero_de_permis", "83723-34");
        declarationPsychologues.accumulate("ordre", "psychologues");
        activitePsy = new JSONObject();
        activitePsy.accumulate("description", "Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans");
        activitePsy.accumulate("categorie", "cours");
        activitePsy.accumulate("heures", 25);
        activitePsy.accumulate("date", "2013-04-01");
        activitesPsy = new JSONArray();
        activitesPsy.add(activitePsy);
        declarationPsychologues.accumulate("activites", activitesPsy);
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
    @Test
    public void testFormatAcceptePourNumeroDePermis() {
        //Architectes
        boolean result1;
        if (instanceArchitectes.champsTexteExiste(champsNumeroDePermis)) {
            result1 = instanceArchitectes.numerosDePermisValides("TR443");
        } else {
            result1 = false;
        }
        assertFalse(result1);
        
        boolean result2;
        if (instanceArchitectes.champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declarationArchitectes.getString(champsNumeroDePermis);
            result2 = instanceArchitectes.numerosDePermisValides(numeroDePermis);
        } else {
            result2 = false;
        }
        assertTrue(result2);
    
        // Geologues
        boolean result3;
        if (instanceGeologues.champsTexteExiste(champsNumeroDePermis)) {
            result3 = instanceGeologues.numerosDePermisValides("JB1280");
        } else {
            result3 = false;
        }
        assertFalse(result3);
        
        boolean result4;
        if (instanceGeologues.champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declarationGeologues.getString(champsNumeroDePermis);
            result4 = instanceGeologues.numerosDePermisValides(numeroDePermis);
        } else {
            result4 = false;
        }
        assertTrue(result4);
        
        //Podiatres
        boolean result5;
        if (instancePodiatres.champsTexteExiste(champsNumeroDePermis)) {
            result5 = instancePodiatres.numerosDePermisValides("324983");
        } else {
            result5 = false;
        }
        assertFalse(result5);
        
        boolean result6;
        if (instancePodiatres.champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declarationPodiatres.getString(champsNumeroDePermis);
            result6 = instancePodiatres.numerosDePermisValides(numeroDePermis);
        } else {
            result6 = false;
        }
        assertTrue(result6);
        
        // Psychologues
        boolean result7;
        if (instancePsychologues.champsTexteExiste(champsNumeroDePermis)) {
            result7 = instancePsychologues.numerosDePermisValides("3463E");
        } else {
            result7 = false;
        }
        assertFalse(result7);

        boolean result8;
        if (instancePsychologues.champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declarationPsychologues.getString(champsNumeroDePermis);
            result8 = instancePsychologues.numerosDePermisValides(numeroDePermis);
        } else {
            result8 = false;
        }
        assertTrue(result8);
    }
    
    /**
     * Test of champsTexteExiste method, of class LecteurDeDeclaration.
     */
    @Ignore
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
     * Test of numerosDePermisValides method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumerosDePermisValides() {
        String numeroPermisArchitectes = "([A|T]{1}[0-9]{4})";
        String numeroPermisPsychologues = "([0-9]{5}[-][0-9]{2})";
        String numeroPermisGeologues = "([A-Z]{2}[0-9]{4})";
        String numeroPermisPodiatres = "([0-9]{5})";
        
        // Tests qui échouent
        boolean result1 = instanceArchitectes.numerosDePermisValidesSelonLOrdre("Z3451", numeroPermisArchitectes);
        boolean result2 = instancePsychologues.numerosDePermisValidesSelonLOrdre("56122_03", numeroPermisPsychologues);
        boolean result3 = instanceGeologues.numeroDePermisValideGeologues("JB7867", numeroPermisGeologues);
        boolean result4 = instancePodiatres.numerosDePermisValidesSelonLOrdre("560890", numeroPermisPodiatres);
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    
        // Tests qui réussissent
        String numeroDePermis5 = declarationArchitectes.getString("numero_de_permis");
        String numeroDePermis6 = declarationPsychologues.getString("numero_de_permis");
        String numeroDePermis7 = declarationGeologues.getString("numero_de_permis");
        String numeroDePermis8 = declarationPodiatres.getString("numero_de_permis");
        boolean result5 = instanceArchitectes.numerosDePermisValidesSelonLOrdre(numeroDePermis5, numeroPermisArchitectes);
        boolean result6 = instancePsychologues.numerosDePermisValidesSelonLOrdre(numeroDePermis6, numeroPermisPsychologues);
        boolean result7 = instanceGeologues.numeroDePermisValideGeologues(numeroDePermis7, numeroPermisGeologues);
        boolean result8 = instancePodiatres.numerosDePermisValidesSelonLOrdre(numeroDePermis8, numeroPermisPodiatres);
        assertTrue(result5);
        assertTrue(result6);
        assertTrue(result7);
        assertTrue(result8);
    }
    
    /**
     * Test of numerosDePermisValidesSelonLOrdre method, of class LecteurDeDeclaration.
     */
    public void testNumerosDePermisValidesSelonLOrdre() {
        // Architectes
        String numeroDePermisLu1 = "X4573";
        String formatNumeroPermisValide1 = "([A|T]{1}[0-9]{4})";
        boolean result1 = instanceArchitectes.numerosDePermisValidesSelonLOrdre(numeroDePermisLu1, formatNumeroPermisValide1);
        assertFalse(result1);
        
        String numeroDePermisLu2 = "T3443";
        boolean result2 = instanceArchitectes.numerosDePermisValidesSelonLOrdre(numeroDePermisLu2, formatNumeroPermisValide1);
        assertTrue(result2);
    
        // Podiatres
        String numeroDePermisLu3 = "56X87";
        String formatNumeroPermisValide3 = "([0-9]{5})";
        boolean result3 = instancePodiatres.numerosDePermisValidesSelonLOrdre(numeroDePermisLu3, formatNumeroPermisValide3);
        assertFalse(result3);
        
        String numeroDePermisLu4 = "83453";
        boolean result4 = instancePodiatres.numerosDePermisValidesSelonLOrdre(numeroDePermisLu4, formatNumeroPermisValide3);
        assertTrue(result4);
    
        // Psychologues
        String numeroDePermisLu5 = "83723!34";
        String formatNumeroPermisValide5 = "([0-9]{5}[-][0-9]{2})";
        boolean result5 = instancePsychologues.numerosDePermisValidesSelonLOrdre(numeroDePermisLu5, formatNumeroPermisValide5);
        assertFalse(result5);
        
        String numeroDePermisLu6 = "83723-34";
        boolean result6 = instancePsychologues.numerosDePermisValidesSelonLOrdre(numeroDePermisLu6, formatNumeroPermisValide5);
        assertTrue(result6);
    }

    /**
     * Test of numeroDePermisValideGeologues method, of class LecteurDeDeclaration.
     */
    @Test
    public void testNumeroDePermisValideGeologues() {
        String numeroDePermisLu = "XY6734";
        String formatNumeroPermisValide = "([A-Z]{2}[0-9]{4})";
        boolean result1 = instanceGeologues.numeroDePermisValideGeologues(numeroDePermisLu, formatNumeroPermisValide);
        assertFalse(result1);
    
        String numeroDePermisLu2 = "BJ6734";
        boolean result2 = instanceGeologues.numeroDePermisValideGeologues(numeroDePermisLu2, formatNumeroPermisValide);
        assertTrue(result2);
    }

    /**
     * Test of formatAcceptePourOrdre method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourOrdre() {
        boolean result1 = instanceGeologues.ordreReconnu("geologues");   // sans accent = false
        assertFalse(result1);
        
        boolean result2 = instanceGeologues.ordreReconnu("");   // vide = false
        assertFalse(result2);
    
        String numeroDePermis = declarationGeologues.getString("ordre");
        boolean result3 = instanceGeologues.ordreReconnu(numeroDePermis);
        assertTrue(result3);
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
        String champs1 = declarationArchitectes.getString("nom");
        boolean result1 = !champs1.equals("");  // champ "nom" Architectes == ""
        assertFalse(result1);
    
        String champs2 = declarationGeologues.getString("nom");
        boolean result2 = !champs2.equals("");  // champ "nom" == Berger
        assertTrue(result2);
        
        String champs3 = declarationGeologues.getString("prenom");
        boolean result3 = !champs3.equals("");  // champ "nom" == Jacques
        assertTrue(result3);
    }

    /**
     * Test of formatAcceptePourSexe method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourSexe() {
        int sexe1 = declarationArchitectes.getInt("sexe");
        boolean result1 = instancePodiatres.sexeReconnu(sexe1);
        assertFalse(result1);
    
        int sexe2 = declarationPodiatres.getInt("sexe");
        boolean result2 = instancePodiatres.sexeReconnu(sexe2);
        assertTrue(result2);
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
        boolean result1 = instancePsychologues.champsTexteExiste("cycle");
        assertFalse(result1);
    
        boolean result2 = instanceArchitectes.champsTexteExiste("cycle");
        assertTrue(result2);
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
     * Test of formatAcceptePourTableauActivites method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourTableauActivites() {
        System.out.println("formatAcceptePourTableauActivites");
        boolean result;
        String champsActivites = "activites";
        if (instancePodiatres.champsTableauJSONExiste(champsActivites)) {
            JSONArray activites = declarationPodiatres.getJSONArray(champsActivites);
            result = instancePodiatres.formatAcceptePourChaqueActivite(activites);
        } else {
            result = false;
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourTableauActivites2() {
        System.out.println("formatAcceptePourTableauActivites");
        boolean result;
        String champsActivites = "activites";
        if (instancePsychologues.champsTableauJSONExiste(champsActivites)) {
            JSONArray activites = declarationPsychologues.getJSONArray(champsActivites);
            result = instancePsychologues.formatAcceptePourChaqueActivite(activites);
        } else {
            result = false;
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of champsTableauJSONExiste method, of class LecteurDeDeclaration.
     */
    @Ignore
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
        boolean result = false;
        JSONArray activites = declarationPodiatres.getJSONArray("activites");
        for (int i = 0; i < activites.size(); i++) {
            JSONObject activiteCourante = activites.getJSONObject(i);
            if (!instancePodiatres.formatAcceptePourActivite(activiteCourante)) {
                result = false;
                break;
            }
        }
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFormatAcceptePourChaqueActivite2() {
        System.out.println("formatAcceptePourChaqueActivite");
        boolean result = true;
        JSONArray activites = declarationPsychologues.getJSONArray("activites");
        for (int i = 0; i < activites.size(); i++) {
            JSONObject activiteCourante = activites.getJSONObject(i);
            if (!instancePsychologues.formatAcceptePourActivite(activiteCourante)) {
                result = false;
                break;
            }
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of formatAcceptePourActivite method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourActivite() {
        System.out.println("formatAcceptePourActivite");
        JSONObject activite = declarationPodiatres;
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.formatAcceptePourActivite(activite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testFormatAcceptePourActivite2() {
        System.out.println("formatAcceptePourActivite");
        JSONObject activite = null;
        LecteurDeDeclaration instance = null;
        boolean expResult = true;
        boolean result = instance.formatAcceptePourActivite(activite);
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
        LecteurDeDeclaration instance = null;
        boolean expResult = false;
        boolean result = instance.formatAcceptePourDescription(activite);
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
