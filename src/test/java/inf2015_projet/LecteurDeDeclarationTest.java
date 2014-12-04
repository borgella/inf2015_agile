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
        declarationArchitectes.accumulate("prenom", "Chelny");
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
        activitePod.accumulate("description", "desc");
        activitePod.accumulate("", "initiation à la programmation");
        activitePod.accumulate("heures", 0);
        activitePod.accumulate("date", "1899/13/34 ");
        activitesPod = new JSONArray();
        activitesPod.add(activitePod);
        declarationPodiatres.accumulate("activites", activitesPod);
        instancePodiatres = new LecteurDeDeclaration(declarationPodiatres);
        
        declarationPsychologues = new JSONObject();
        declarationPsychologues.accumulate("nom", "Bougeon");
        declarationPsychologues.accumulate("prenom", "Antoine");
        declarationPsychologues.accumulate("sexe", 1);
        declarationPsychologues.accumulate("numero_de_permis", "83723-34");
        declarationPsychologues.accumulate("ordre", "psychologues");
        declarationPsychologues.accumulate("cycle", "2010-2015");
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
    @Test
    public void testErreurDeFormatDetectee() {
        boolean result1 = !instancePsychologues.formatAcceptePourSexe() |
                !instancePsychologues.formatAcceptePourNumeroDePermis() |
                !instancePsychologues.formatAcceptePourOrdre() |
                !instancePsychologues.formatAcceptePourPrenomOuNom("prenom") |
                !instancePsychologues.formatAcceptePourPrenomOuNom("nom") |
                !instancePsychologues.formatAcceptePourCycle() |
                !instancePsychologues.formatAcceptePourHeuresTransfereesSelonOrdre() |
                !instancePsychologues.formatAcceptePourTableauActivites();
        assertFalse(result1);
        
        boolean result2 = !instancePodiatres.formatAcceptePourSexe() |
                !instancePodiatres.formatAcceptePourNumeroDePermis() |
                !instancePodiatres.formatAcceptePourOrdre() |
                !instancePodiatres.formatAcceptePourPrenomOuNom("prenom") |
                !instancePodiatres.formatAcceptePourPrenomOuNom("nom") |
                !instancePodiatres.formatAcceptePourCycle() |
                !instancePodiatres.formatAcceptePourHeuresTransfereesSelonOrdre() |
                !instancePodiatres.formatAcceptePourTableauActivites();
        assertTrue(result2);
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
        boolean result1 = instancePodiatres.champsTexteExiste("cycle");
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
        String champsHeuresTransferees = "heures_transferees_du_cycle_precedent";
        String ordre1 = declarationArchitectes.getString("ordre");
        boolean result1;
        if (ordre1.equals("architectes")) {
            result1 = instanceArchitectes.champsNumeriqueExiste(champsHeuresTransferees);
        } else {
            result1 = true;
        }
        assertFalse(result1);
        
        String ordre2 = declarationPodiatres.getString("ordre");
        boolean result2;
        if (ordre2.equals("architectes")) {
            result2 = instancePodiatres.champsNumeriqueExiste(champsHeuresTransferees);
        } else {
            result2 = true;
        }
        assertTrue(result2);
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
        String champsActivites = "activites";
        boolean result1;
        if (instanceGeologues.champsTableauJSONExiste(champsActivites)) {
            JSONArray activites = declarationGeologues.getJSONArray(champsActivites);
            result1 = instanceGeologues.formatAcceptePourChaqueActivite(activites);
        } else {
            result1 = false;
        }
        assertFalse(result1);
    
        boolean result2;
        if (instancePsychologues.champsTableauJSONExiste(champsActivites)) {
            JSONArray activites = declarationPsychologues.getJSONArray(champsActivites);
            result2 = instancePsychologues.formatAcceptePourChaqueActivite(activites);
        } else {
            result2 = false;
        }
        assertTrue(result2);
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
        boolean result1 = false;
        JSONArray activites1 = declarationPodiatres.getJSONArray("activites");
        for (int i = 0; i < activites1.size(); i++) {
            JSONObject activiteCourante1 = activites1.getJSONObject(i);
            if (!instancePodiatres.formatAcceptePourActivite(activiteCourante1)) {
                result1 = false;
                break;
            }
        }
        assertFalse(result1);
    
        boolean result2 = true;
        JSONArray activites2 = declarationPsychologues.getJSONArray("activites");
        for (int i = 0; i < activites2.size(); i++) {
            JSONObject activiteCourante2 = activites2.getJSONObject(i);
            if (!instancePsychologues.formatAcceptePourActivite(activiteCourante2)) {
                result2 = false;
                break;
            }
        }
        assertTrue(result2);
    }

    /**
     * Test of formatAcceptePourActivite method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourActivite() {
        JSONObject activite1 = activitePod;
        boolean result1 = instancePodiatres.formatAcceptePourDescription(activite1) &&
                instancePodiatres.formatAcceptePourCategorie(activite1) &&
                instancePodiatres.formatAcceptePourHeures(activite1) &&
                instancePodiatres.formatAcceptePourDate(activite1);
        assertFalse(result1);
    
        JSONObject activite2 = activitePsy;
        boolean result2 = instancePsychologues.formatAcceptePourDescription(activite2) &&
                instancePsychologues.formatAcceptePourCategorie(activite2) &&
                instancePsychologues.formatAcceptePourHeures(activite2) &&
                instancePsychologues.formatAcceptePourDate(activite2);
        assertTrue(result2);
    }

    /**
     * Test of formatAcceptePourDescription method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourDescription() {
        JSONObject activite1 = activitePod;
        boolean result1;
        String description1 = activite1.getString("description");
        if (instancePodiatres.champsTexteExistePourActivite("description", activite1)) {
            result1 = instancePodiatres.descriptionReconnu(description1);
        } else {
            result1 = false;
        }
        assertFalse(result1);
                
        JSONObject activite2 = activitePsy;
        boolean result2;
        String description2 = activite2.getString("description");
        if (instancePsychologues.champsTexteExistePourActivite("description", activite2)) {
            result2 = instancePsychologues.descriptionReconnu(description2);
        } else {
            result2 = false;
        }
        assertTrue(result2);
    }

    /**
     * Test of champsTexteExistePourActivite method, of class LecteurDeDeclaration.
     */
    @Ignore
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
    @Ignore
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
        JSONObject activite1 = activitePod;
        boolean result1 = instancePodiatres.champsTexteExistePourActivite("categorie", activite1);
        assertFalse(result1);
        
        JSONObject activite2 = activitePsy;
        boolean result2 = instancePsychologues.champsTexteExistePourActivite("categorie", activite2);
        assertTrue(result2);
    }

    /**
     * Test of formatAcceptePourHeures method, of class LecteurDeDeclaration.
     */
    @Test
    public void testFormatAcceptePourHeures() {
        JSONObject activite1 = activitePod;
        boolean result1;
        if (instancePodiatres.champsNumeriqueExistePourActivite("heures", activite1)) {
            int heures = activite1.getInt("heures");
            result1 = instancePodiatres.heuresValidesPourActivite(heures);
        } else {
            result1 = false;
        }
        assertFalse(result1);
        
        JSONObject activite2 = activitePsy;
        boolean result2;
        if (instancePsychologues.champsNumeriqueExistePourActivite("heures", activite2)) {
            int heures = activite2.getInt("heures");
            result2 = instancePsychologues.heuresValidesPourActivite(heures);
        } else {
            result2 = false;
        }
        assertTrue(result2);
    }

    /**
     * Test of champsNumeriqueExistePourActivite method, of class LecteurDeDeclaration.
     */
    @Ignore
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
    @Ignore
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
        JSONObject activite1 = activitePod;
        boolean result1;
        if (instancePodiatres.champsNumeriqueExistePourActivite("date", activite1)) {
            String date = activite1.getString("date");
            result1 = instancePodiatres.dateEnFormatReconnu(date);
        } else {
            result1 = false;
        }
        assertFalse(result1);
        
        JSONObject activite2 = activitePsy;
        boolean result2;
        if (instancePsychologues.champsNumeriqueExistePourActivite("date", activite2)) {
            String date = activite2.getString("date");
            result2 = instancePsychologues.dateEnFormatReconnu(date);
        } else {
            result2 = false;
        }
        assertTrue(result2);
    }

    /**
     * Test of dateEnFormatReconnu method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateEnFormatReconnu() {
        String date1 = activitePod.getString("date");
        boolean result1 = instancePodiatres.dateALongueurValide(date1) &&
                instancePodiatres.dateAContenuValide(date1);
        assertFalse(result1);
        
        String date2 = activitePsy.getString("date");
        boolean result2 = instancePsychologues.dateALongueurValide(date2) &&
                instancePsychologues.dateAContenuValide(date2);
        assertTrue(result2);
    }

    /**
     * Test of dateALongueurValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateALongueurValide() {
        String date1 = activitePod.getString("date");
        boolean result1 = date1.length() == 10;
        assertFalse(result1);
        
        String date2 = activitePsy.getString("date");
        boolean result2 = date2.length() == 10;
        assertTrue(result2);
    }

    /**
     * Test of dateAContenuValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAContenuValide() {
        String date1 = activitePod.getString("date");
        boolean result1 = instancePodiatres.dateASeperateursValides(date1) &&
                instancePodiatres.dateAComposantesNumeriquesValides(date1);
        assertFalse(result1);
        
        String date2 = activitePsy.getString("date");
        boolean result2 = instancePsychologues.dateASeperateursValides(date2) &&
                instancePsychologues.dateAComposantesNumeriquesValides(date2);
        assertTrue(result2);
    }

    /**
     * Test of dateASeperateursValides method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateASeperateursValides() {
        String date1 = activitePod.getString("date");
        char premierTiret1 = date1.charAt(4);
        char deuxiemeTiret1 = date1.charAt(7);
        boolean result1 = premierTiret1 == '-' && deuxiemeTiret1 == '-';
        assertFalse(result1);
        
        String date2 = activitePsy.getString("date");
        char premierTiret2 = date2.charAt(4);
        char deuxiemeTiret2 = date2.charAt(7);
        boolean result2 = premierTiret2 == '-' && deuxiemeTiret2 == '-';
        assertTrue(result2);
    }

    /**
     * Test of dateAComposantesNumeriquesValides method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAComposantesNumeriquesValides() {
        String date1 = activitePod.getString("date");
        String anneeEnTexte1 = date1.substring(0, 4);
        String moisEnTexte1 = date1.substring(5, 7);
        String jourEnTexte1 = date1.substring(8, 10);
        boolean result1 = instancePodiatres.dateAUneAnneeValide(anneeEnTexte1) && 
                instancePodiatres.dateAUnMoisValide(moisEnTexte1) && 
                instancePodiatres.dateAUnJourValide(jourEnTexte1);
        assertFalse(result1);
        
        String date2 = activitePsy.getString("date");
        String anneeEnTexte2 = date2.substring(0, 4);
        String moisEnTexte2 = date2.substring(5, 7);
        String jourEnTexte2 = date2.substring(8, 10);
        boolean result2 = instancePsychologues.dateAUneAnneeValide(anneeEnTexte2) && 
                instancePsychologues.dateAUnMoisValide(moisEnTexte2) && 
                instancePsychologues.dateAUnJourValide(jourEnTexte2);
        assertTrue(result2);
    }

    /**
     * Test of dateAUneAnneeValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAUneAnneeValide() {
        String anneeEnTexte1 = activitePod.getString("date").substring(0, 4);
        int annee1 = Integer.parseInt(anneeEnTexte1);
        boolean result1 = (annee1 >= 1900);
        assertFalse(result1);
        
        String anneeEnTexte2 = activitePsy.getString("date").substring(0, 4);
        int annee2 = Integer.parseInt(anneeEnTexte2);
        boolean result2 = (annee2 >= 1900);
        assertTrue(result2);
    }

    /**
     * Test of dateAUnMoisValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAUnMoisValide() {
        String moisEnTexte1 = activitePod.getString("date").substring(5, 7);
        int mois1 = Integer.parseInt(moisEnTexte1);
        boolean result1 = (1 <= mois1 && mois1 <= 12);
        assertFalse(result1);
        
        String moisEnTexte2 = activitePsy.getString("date").substring(5, 7);
        int mois2 = Integer.parseInt(moisEnTexte2);
        boolean result2 = (1 <= mois2 && mois2 <= 12);
        assertTrue(result2);
    }

    /**
     * Test of dateAUnJourValide method, of class LecteurDeDeclaration.
     */
    @Test
    public void testDateAUnJourValide() {
        String jourEnTexte1 = activitePod.getString("date").substring(8, 10);
        int jour1 = Integer.parseInt(jourEnTexte1);
        boolean result1 = (1 <= jour1 && jour1 <= 31);
        assertFalse(result1);
        
        String jourEnTexte2 = activitePsy.getString("date").substring(8, 10);
        int jour2 = Integer.parseInt(jourEnTexte2);
        boolean result2 = (1 <= jour2 && jour2 <= 31);
        assertTrue(result2);
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
