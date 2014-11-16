/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.util.ArrayList;
import net.sf.json.JSONObject;
import professionnels.*;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public abstract class Validateur {

    public static Validateur genererValidateur(Membre membre) {
        Validateur validateurGenere = fabriqueValidateur(membre);
        return validateurGenere;
    }

    private static Validateur fabriqueValidateur(Membre membre) {
        Validateur validateurGenere;
        if (membre instanceof Architecte) {
            validateurGenere = new ValidateurArchitecte(membre);
        } else if (membre instanceof Geologue) {
            validateurGenere = new ValidateurGeologue(membre);
        } else if (membre instanceof Psychologue) {
            validateurGenere = new ValidateurPsychologue(membre);
        } else {
            validateurGenere = new ValidateurPodiatre(membre);
        }
        return validateurGenere;
    }

    public JSONObject produireRapport(ArrayList<String> messagesErreurs) {
        JSONObject texteDeSortie = new JSONObject();
        construireMessagesDErreur();
        texteDeSortie.accumulate("complet", formationComplete());
        texteDeSortie.accumulate("erreurs", messagesErreurs);
        return texteDeSortie;
    }

    public abstract JSONObject produireRapport();

    public abstract void construireMessagesDErreur();

    public abstract void messageErreurSiLeCycleEstInvalide();

    public abstract boolean validerLeCycle();

    public abstract void messageInvalidePourCategorieNonReconnue();

    public abstract ArrayList<String> descriptionsDActivitesAvecCategorieNonReconnue(ArrayList<JSONObject> liste);

    public String convertirDescriptionsEnPhrase(ArrayList<String> descriptions) {
        int nombreDeDescriptions = descriptions.size();
        String phraseDeRetour = "";
        if (nombreDeDescriptions > 0) {
            phraseDeRetour = construirePhraseAvecDescriptions(descriptions);
        }
        return phraseDeRetour;
    }

    public String construirePhraseAvecDescriptions(ArrayList<String> descriptions) {
        String phraseDeRetour = descriptions.get(0);
        int nombreDeDescriptions = descriptions.size();
        for (int i = 1; i < nombreDeDescriptions - 1; i++) {
            phraseDeRetour += ", " + descriptions.get(i);
        }
        if (nombreDeDescriptions > 1) {
            phraseDeRetour += " et " + descriptions.get(nombreDeDescriptions - 1);
        }
        return phraseDeRetour;
    }

    public abstract void ecrireMessageDErreurPourCategoriesNonReconnues(int nombreDActivites, String activitesErronees);

    public abstract void messageErreurPourDateInvalide();

    public abstract ArrayList<String> descriptionsDActivitesAvecDateInvalide(ArrayList<JSONObject> liste);

    public abstract void ecrireMessageDErreurPourDatesInvalides(int nombreDActivites, String activitesErronees);

    public abstract void messageErreurPourHeuresManquantes();

    public abstract int heuresTotalesFormation();

    public abstract int nombreDHeuresSelonRegroupement(int codeDuRegroupement);

    public abstract int heuresBrutesSelonCategorie(String categorie);

    public abstract boolean formationComplete();

}
