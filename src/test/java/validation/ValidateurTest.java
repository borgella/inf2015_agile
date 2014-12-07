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

    }

    /**
     * Test of convertirDescriptionsEnPhrase method, of class Validateur.
     */
    @Test
    public void testConvertirDescriptionsEnPhrase() {
        ArrayList<String> descriptions = new ArrayList<>();
        assertEquals("", Validateur.convertirDescriptionsEnPhrase(descriptions));
        
        descriptions.add("Première description");
        descriptions.add("Deuxième description");
        descriptions.add("Troisième description");
        
        String phraseATroisDescriptions = "Première description, Deuxième description et Troisième description";
        assertEquals(phraseATroisDescriptions, Validateur.convertirDescriptionsEnPhrase(descriptions));
    }

    /**
     * Test of construirePhraseAvecAuMoinsUneDescription method, of class Validateur.
     */
    @Test
    public void testConstruirePhraseAvecDescriptions() {
        ArrayList<String> descriptions = new ArrayList<>();
        
        descriptions.add("Première description");
        assertEquals("Première description", Validateur.construirePhraseAvecAuMoinsUneDescription(descriptions));
        
        descriptions.add("Deuxième description");
        String phraseADeuxDescriptions = "Première description et Deuxième description";
        assertEquals(phraseADeuxDescriptions, Validateur.construirePhraseAvecAuMoinsUneDescription(descriptions));
        
        descriptions.add("Troisième description");
        descriptions.add("Quatrième description");
        String phraseAQuatreDescriptions = 
                "Première description, Deuxième description, Troisième description et Quatrième description";
        assertEquals(phraseAQuatreDescriptions, Validateur.construirePhraseAvecAuMoinsUneDescription(descriptions));
    }

    /**
     * Test of ecrireMessageDErreurPourCategoriesNonReconnues method, of class Validateur.
     */
    @Test
    public void testEcrireMessageDErreurPourCategoriesNonReconnues() {

    }

    /**
     * Test of messageErreurPourDateInvalide method, of class Validateur.
     */
    @Test
    public void testMessageErreurPourDateInvalide() {

    }

    /**
     * Test of descriptionsDActivitesAvecDateInvalide method, of class Validateur.
     */
    @Test
    public void testDescriptionsDActivitesAvecDateInvalide() {
    }

    /**
     * Test of ecrireMessageDErreurPourDatesInvalides method, of class Validateur.
     */
    @Test
    public void testEcrireMessageDErreurPourDatesInvalides() {

    }

    /**
     * Test of messageErreurPourHeuresManquantes method, of class Validateur.
     */
    @Test
    public void testMessageErreurPourHeuresManquantes() {

    }

    /**
     * Test of heuresTotalesFormation method, of class Validateur.
     */
    @Test
    public void testHeuresTotalesFormation() {

    }

    /**
     * Test of nombreDHeuresSelonRegroupement method, of class Validateur.
     */
    @Test
    public void testNombreDHeuresSelonRegroupement() {

    }

    /**
     * Test of heuresBrutesSelonCategorie method, of class Validateur.
     */
    @Test
    public void testHeuresBrutesSelonCategorie() {

    }

    /**
     * Test of formationComplete method, of class Validateur.
     */
    @Test
    public void testFormationComplete() {

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
