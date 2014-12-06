/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import inf2015_projet.MockJson;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import professionnels.Membre;

/**
 *
 * @author QQ1403
 */
public class ValidateurTest {
    
    MockJson jsongenere = new MockJson();
    JSONObject declaration_json = jsongenere.retournerUnJSONObject();
    JSONArray liste_activite = jsongenere.getActivites();
    Membre membre = Membre.genererMembre(declaration_json);
    public ValidateurTest() {
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
     * Test of genererValidateur method, of class Validateur.
     */
    @Test
    public void testGenererValidateur() {
        System.out.println("genererValidateur");
        Validateur expResult = Validateur.genererValidateur(membre);
        Validateur result = expResult;
        assertEquals(expResult, result);
    }

    /**
     * Test of produireRapport method, of class Validateur.
     */
    @Test
    public void testProduireRapport_ArrayList() {
        System.out.println("produireRapport");
        ArrayList<String> messagesErreurs = new ArrayList(1);
        messagesErreurs.add("Pas d'erreur");
        Validateur instance = Validateur.genererValidateur(membre);
        JSONObject expResult = new JSONObject();
        expResult.accumulate("complet",false);
        expResult.accumulate("erreurs",messagesErreurs);
        JSONObject result = instance.produireRapport(messagesErreurs);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of produireRapport method, of class Validateur.
     */
    @Test
    public void testProduireRapport_0args() {
        System.out.println("produireRapport");
        Validateur instance = Validateur.genererValidateur(membre);
        JSONObject expResult = new JSONObject();
        expResult.accumulate("complet",false);
        expResult.accumulate("erreurs","Pas d'erreur");
        JSONObject result = instance.produireRapport();
        assertEquals(expResult, result);
    }

    /**
     * Test of construireMessagesDErreur method, of class Validateur.
     */
    @Test
    public void testConstruireMessagesDErreur() {
        System.out.println("construireMessagesDErreur");
        Validateur instance = new ValidateurImpl();
        instance.construireMessagesDErreur();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of messageErreurSiLeCycleEstInvalide method, of class Validateur.
     */
    @Test
    public void testMessageErreurSiLeCycleEstInvalide() {
        System.out.println("messageErreurSiLeCycleEstInvalide");
        Validateur instance = new ValidateurImpl();
        instance.messageErreurSiLeCycleEstInvalide();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validerLeCycle method, of class Validateur.
     */
    @Test
    public void testValiderLeCycle() {
        System.out.println("validerLeCycle");
        Validateur instance = new ValidateurImpl();
        boolean expResult = false;
        boolean result = instance.validerLeCycle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of messageInvalidePourCategorieNonReconnue method, of class Validateur.
     */
    @Test
    public void testMessageInvalidePourCategorieNonReconnue() {
        System.out.println("messageInvalidePourCategorieNonReconnue");
        Validateur instance = new ValidateurImpl();
        instance.messageInvalidePourCategorieNonReconnue();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of descriptionsDActivitesAvecCategorieNonReconnue method, of class Validateur.
     */
    @Test
    public void testDescriptionsDActivitesAvecCategorieNonReconnue() {
        System.out.println("descriptionsDActivitesAvecCategorieNonReconnue");
        ArrayList<JSONObject> liste = null;
        Validateur instance = new ValidateurImpl();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.descriptionsDActivitesAvecCategorieNonReconnue(liste);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertirDescriptionsEnPhrase method, of class Validateur.
     */
    @Test
    public void testConvertirDescriptionsEnPhrase() {
        System.out.println("convertirDescriptionsEnPhrase");
        ArrayList<String> descriptions = null;
        Validateur instance = new ValidateurImpl();
        String expResult = "";
        String result = instance.convertirDescriptionsEnPhrase(descriptions);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of construirePhraseAvecDescriptions method, of class Validateur.
     */
    @Test
    public void testConstruirePhraseAvecDescriptions() {
        System.out.println("construirePhraseAvecDescriptions");
        ArrayList<String> descriptions = null;
        Validateur instance = new ValidateurImpl();
        String expResult = "";
        String result = instance.construirePhraseAvecDescriptions(descriptions);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ecrireMessageDErreurPourCategoriesNonReconnues method, of class Validateur.
     */
    @Test
    public void testEcrireMessageDErreurPourCategoriesNonReconnues() {
        System.out.println("ecrireMessageDErreurPourCategoriesNonReconnues");
        int nombreDActivites = 0;
        String activitesErronees = "";
        Validateur instance = new ValidateurImpl();
        instance.ecrireMessageDErreurPourCategoriesNonReconnues(nombreDActivites, activitesErronees);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of messageErreurPourDateInvalide method, of class Validateur.
     */
    @Test
    public void testMessageErreurPourDateInvalide() {
        System.out.println("messageErreurPourDateInvalide");
        Validateur instance = new ValidateurImpl();
        instance.messageErreurPourDateInvalide();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of descriptionsDActivitesAvecDateInvalide method, of class Validateur.
     */
    @Test
    public void testDescriptionsDActivitesAvecDateInvalide() {
        System.out.println("descriptionsDActivitesAvecDateInvalide");
        ArrayList<JSONObject> liste = null;
        Validateur instance = new ValidateurImpl();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.descriptionsDActivitesAvecDateInvalide(liste);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ecrireMessageDErreurPourDatesInvalides method, of class Validateur.
     */
    @Test
    public void testEcrireMessageDErreurPourDatesInvalides() {
        System.out.println("ecrireMessageDErreurPourDatesInvalides");
        int nombreDActivites = 0;
        String activitesErronees = "";
        Validateur instance = new ValidateurImpl();
        instance.ecrireMessageDErreurPourDatesInvalides(nombreDActivites, activitesErronees);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of messageErreurPourHeuresManquantes method, of class Validateur.
     */
    @Test
    public void testMessageErreurPourHeuresManquantes() {
        System.out.println("messageErreurPourHeuresManquantes");
        Validateur instance = new ValidateurImpl();
        instance.messageErreurPourHeuresManquantes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of heuresTotalesFormation method, of class Validateur.
     */
    @Test
    public void testHeuresTotalesFormation() {
        System.out.println("heuresTotalesFormation");
        Validateur instance = new ValidateurImpl();
        int expResult = 0;
        int result = instance.heuresTotalesFormation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nombreDHeuresSelonRegroupement method, of class Validateur.
     */
    @Test
    public void testNombreDHeuresSelonRegroupement() {
        System.out.println("nombreDHeuresSelonRegroupement");
        int codeDuRegroupement = 0;
        Validateur instance = new ValidateurImpl();
        int expResult = 0;
        int result = instance.nombreDHeuresSelonRegroupement(codeDuRegroupement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of heuresBrutesSelonCategorie method, of class Validateur.
     */
    @Test
    public void testHeuresBrutesSelonCategorie() {
        System.out.println("heuresBrutesSelonCategorie");
        String categorie = "";
        Validateur instance = new ValidateurImpl();
        int expResult = 0;
        int result = instance.heuresBrutesSelonCategorie(categorie);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formationComplete method, of class Validateur.
     */
    @Test
    public void testFormationComplete() {
        System.out.println("formationComplete");
        Validateur instance = new ValidateurImpl();
        boolean expResult = false;
        boolean result = instance.formationComplete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ValidateurImpl extends Validateur {

        public JSONObject produireRapport() {
            JSONObject json = new JSONObject(); 
            json.accumulate("complet", false);
            json.accumulate("erreurs", "Pas d'erreur TOTO");
            return json;
        }

        public void construireMessagesDErreur() {
        }

        public void messageErreurSiLeCycleEstInvalide() {
        }

        public boolean validerLeCycle() {
            return false;
        }

        public void messageInvalidePourCategorieNonReconnue() {
        }

        public ArrayList<String> descriptionsDActivitesAvecCategorieNonReconnue(ArrayList<JSONObject> liste) {
            return null;
        }

        /**
         *
         * @param nombreDActivites
         * @param activitesErronees
         */
        @Override
        public void ecrireMessageDErreurPourCategoriesNonReconnues(int nombreDActivites, String activitesErronees) {
            String message;
            if(nombreDActivites > 1){
                message = "Les Activites" +  activitesErronees + "sont dans des categories non reconnues.";
            }else{
                message = "L'activite "+ activitesErronees + "est dans une categorie non reconnue.";
            }
        }

        public void messageErreurPourDateInvalide() {
        }

        public ArrayList<String> descriptionsDActivitesAvecDateInvalide(ArrayList<JSONObject> liste) {
            return null;
        }

        public void ecrireMessageDErreurPourDatesInvalides(int nombreDActivites, String activitesErronees) {
        }

        public void messageErreurPourHeuresManquantes() {
        }

        public int heuresTotalesFormation() {
            return 0;
        }

        public int nombreDHeuresSelonRegroupement(int codeDuRegroupement) {
            return 0;
        }

        public int heuresBrutesSelonCategorie(String categorie) {
            return 0;
        }

        public boolean formationComplete() {
            return false;
        }
    }
    
}
