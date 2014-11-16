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
    
    public abstract JSONObject produireRapport();
    
    public abstract void construireMessagesDErreur();
    
    public abstract void messageErreurSiLeCycleEstInvalide();
    
    public abstract boolean validerLeCycle();
    
    public abstract void messageInvalidePourCategorieNonReconnue();
    
    public abstract ArrayList<String> descriptionsDActivitesAvecCategorieNonReconnue(ArrayList<JSONObject> liste);
    
    public abstract String convertirDescriptionsEnPhrase(ArrayList<String> descriptions);
    
    public abstract String construirePhraseAvecDescriptions(ArrayList<String> descriptions);
    
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
