package validation;

import inf2015_projet.MockJson;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import professionnels.*;

/**
 *
 * @author QQ1403
 */
public class ValidateurTest {

    MockJson jsongenere = new MockJson();
    JSONObject declaration_json = jsongenere.retournerUnJSONObject();
    JSONArray liste_activite = jsongenere.getActivites();
    Membre membre = Membre.genererMembre(declaration_json);

    private JSONObject creerActiviteADescriptionEn2013ValideSelonCategorie(String description, String categorie) {
        JSONObject activite = new JSONObject();
        activite.accumulate("description", description);
        activite.accumulate("categorie", categorie);
        activite.accumulate("heures", 3);
        activite.accumulate("date", "2013-01-01");
        return activite;
    }

    private JSONObject creerActiviteADescriptionValideSelonDate(String description, String date) {
        JSONObject activite = new JSONObject();
        activite.accumulate("description", description);
        activite.accumulate("categorie", "cours");
        activite.accumulate("heures", 3);
        activite.accumulate("date", date);
        return activite;
    }

    @Test
    public void testGenererValidateur() {
        System.out.println("genererValidateur");
        Validateur expResult = Validateur.genererValidateur(membre);
        Validateur result = expResult;
        assertEquals(expResult, result);
    }

    @Test
    public void testFabriqueValidateur() {
        Validateur unValidateur;

        unValidateur = Validateur.genererValidateur(new Architecte("cycle quelconque"));
        assertTrue(unValidateur instanceof ValidateurArchitecte);

        unValidateur = Validateur.genererValidateur(new Geologue());
        assertTrue(unValidateur instanceof ValidateurGeologue);

        unValidateur = Validateur.genererValidateur(new Psychologue());
        assertTrue(unValidateur instanceof ValidateurPsychologue);

        unValidateur = Validateur.genererValidateur(new Podiatre());
        assertTrue(unValidateur instanceof ValidateurPodiatre);
    }

    @Test
    public void testProduireRapport_ArrayList() {
        System.out.println("produireRapport");
        ArrayList<String> messagesErreurs = new ArrayList(1);
        messagesErreurs.add("Pas d'erreur");
        Validateur instance = Validateur.genererValidateur(membre);
        JSONObject expResult = new JSONObject();
        expResult.accumulate("complet", false);
        expResult.accumulate("erreurs", messagesErreurs);
        JSONObject result = instance.produireRapport(messagesErreurs);
        assertEquals(expResult, result);

    }

    @Test
    public void testProduireRapport_0args() {
        System.out.println("produireRapport");
        Validateur instance = Validateur.genererValidateur(membre);
        JSONObject expResult = new JSONObject();
        expResult.accumulate("complet", false);
        expResult.accumulate("erreurs", "Pas d'erreur");
        JSONObject result = instance.produireRapport();
        assertEquals(expResult, result);
    }

    @Test
    public void testDescriptionsDActivitesAvecCategorieNonReconnue() {
        Membre unMembre = new Architecte("2012-2014");
        Validateur validateur = Validateur.genererValidateur(unMembre);

        String categorieValide = "cours";
        String categorieInvalide = "sieste";
        
        String activiteUn = "Activité avec catégorie invalide";

        assertNotNull(creerActiviteADescriptionEn2013ValideSelonCategorie(activiteUn, categorieInvalide));
        
        unMembre.ajouterActivitePourMembre
            (creerActiviteADescriptionEn2013ValideSelonCategorie(activiteUn, categorieInvalide));
        ArrayList<String> activitesCategorieInconnue = new ArrayList();
        activitesCategorieInconnue.add(activiteUn);

        assertEquals(activitesCategorieInconnue, validateur.descriptionsDActivitesAvecCategorieNonReconnue(unMembre));
        assertNotNull(unMembre);
        
        String activiteDeux = "Activité avec catégorie invalide";
        unMembre.ajouterActivitePourMembre
            (creerActiviteADescriptionEn2013ValideSelonCategorie(activiteDeux, categorieInvalide));
        String activiteTrois = "Activité avec catégorie VALIDE";
        unMembre.ajouterActivitePourMembre
            (creerActiviteADescriptionEn2013ValideSelonCategorie(activiteTrois, categorieValide));
        String activiteQuatre = "Activité avec catégorie invalide";
        unMembre.ajouterActivitePourMembre
            (creerActiviteADescriptionEn2013ValideSelonCategorie(activiteQuatre, categorieInvalide));
        
        activitesCategorieInconnue.add(activiteDeux);
        activitesCategorieInconnue.add(activiteQuatre);
        assertEquals(activitesCategorieInconnue, validateur.descriptionsDActivitesAvecCategorieNonReconnue(unMembre));
    }

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
        String phraseAQuatreDescriptions
                = "Première description, Deuxième description, Troisième description et Quatrième description";
        assertEquals(phraseAQuatreDescriptions, Validateur.construirePhraseAvecAuMoinsUneDescription(descriptions));
    }

    @Test
    public void testDescriptionsDActivitesAvecDateInvalide() {
        Membre unMembre = new Architecte("2012-2014");
        Validateur validateur = Validateur.genererValidateur(unMembre);

        String activiteUn = "Activité avec date invalide ";
        unMembre.ajouterActivitePourMembre(creerActiviteADescriptionValideSelonDate(activiteUn, "1999-01-01"));
        ArrayList<String> activitesAvecDatesInvalides = new ArrayList();
        activitesAvecDatesInvalides.add(activiteUn);

        assertEquals(activitesAvecDatesInvalides, validateur.descriptionsDActivitesAvecDateInvalide(unMembre));

        String activiteDeux = "Activité avec date invalide";
        unMembre.ajouterActivitePourMembre(creerActiviteADescriptionValideSelonDate(activiteDeux, "1999-01-01"));
        String activiteTrois = "Activité avec date VALIDE";
        unMembre.ajouterActivitePourMembre(creerActiviteADescriptionValideSelonDate(activiteTrois, "2013-01-01"));
        String activiteQuatre = "Activité avec date invalide";
        unMembre.ajouterActivitePourMembre(creerActiviteADescriptionValideSelonDate(activiteQuatre, "1999-01-01"));

        activitesAvecDatesInvalides.add(activiteDeux);
        activitesAvecDatesInvalides.add(activiteQuatre);
        assertEquals(activitesAvecDatesInvalides, validateur.descriptionsDActivitesAvecDateInvalide(unMembre));
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
            if (nombreDActivites > 1) {
                message = "Les Activites" + activitesErronees + "sont dans des categories non reconnues.";
            } else {
                message = "L'activite " + activitesErronees + "est dans une categorie non reconnue.";
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

        @Override
        public int calculerHeuresManquantes() {
            return 0;
        }

        @Override
        public void ecrireMessageErreurPourHeuresManquantes(int heuresManquantes) {
            // Ne rien faire
        }
    }

}
