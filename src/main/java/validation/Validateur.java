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
        } else if (membre instanceof Podiatre) {
            validateurGenere = new ValidateurPodiatre(membre);
        } else if (membre instanceof Psychologue) {
            validateurGenere = new ValidateurPsychologue(membre);
        } else {
            validateurGenere = new ValidateurGeologue(membre);
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
    
     public void messageInvalidePourCategorieNonReconnue(Membre membre) {
        ArrayList<String> descriptionsDesActivites = descriptionsDActivitesAvecCategorieNonReconnue(membre);
        int nombreDActivitesNonReconnues = descriptionsDesActivites.size();
        String activitesErronees = convertirDescriptionsEnPhrase(descriptionsDesActivites);
        if (nombreDActivitesNonReconnues > 0) {
            ecrireMessageDErreurPourCategoriesNonReconnues(nombreDActivitesNonReconnues, activitesErronees);
        }
    }

    public ArrayList<String> descriptionsDActivitesAvecCategorieNonReconnue(Membre membre) {
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        ArrayList<JSONObject> liste = membre.getActivitesRefusees();
        for (int i = 0; i < liste.size(); ++i) {
            JSONObject activite = liste.get(i);
            int codeRegroupement = membre.regroupementDesCategories(activite.getString("categorie"));
            if (codeRegroupement == -1) {
                descriptionsDesActivites.add(activite.getString("description"));
            }
        }
        return descriptionsDesActivites;
    }

    public static String convertirDescriptionsEnPhrase(ArrayList<String> descriptions) {
        int nombreDeDescriptions = descriptions.size();
        String phraseDeRetour = "";
        if (nombreDeDescriptions > 0) {
            phraseDeRetour = construirePhraseAvecAuMoinsUneDescription(descriptions);
        }
        return phraseDeRetour;
    }

    public static String construirePhraseAvecAuMoinsUneDescription(ArrayList<String> descriptions) {
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
    
    public void messageErreurPourDateInvalide(Membre membre) {
        ArrayList<String> descriptionsDesActivites = descriptionsDActivitesAvecDateInvalide(membre);
        int nombreDActivitesNonReconnues = descriptionsDesActivites.size();
        String activitesErronees = convertirDescriptionsEnPhrase(descriptionsDesActivites);
        if (nombreDActivitesNonReconnues > 0) {
            ecrireMessageDErreurPourDatesInvalides(nombreDActivitesNonReconnues, activitesErronees);
        }
    }

    public ArrayList<String> descriptionsDActivitesAvecDateInvalide(Membre membre) {
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        ArrayList<JSONObject> liste = membre.getActivitesRefusees();
        for (int i = 0; i < liste.size(); ++i) {
            JSONObject activite = liste.get(i);
            if (!membre.dateValidePourMembre(activite.getString("date"))) {
                descriptionsDesActivites.add(activite.getString("description"));
            }
        }
        return descriptionsDesActivites;
    }

    public abstract void ecrireMessageDErreurPourDatesInvalides(int nombreDActivites, String activitesErronees);
    
    public void messageErreurPourHeuresManquantes() {
        int heuresManquantes = calculerHeuresManquantes();
        if (heuresManquantes > 0) {
            ecrireMessageErreurPourHeuresManquantes(heuresManquantes);
        }
    }
    
    public abstract int calculerHeuresManquantes();

    public abstract void ecrireMessageErreurPourHeuresManquantes(int heuresManquantes);
    
    public abstract int heuresTotalesFormation();

    public abstract int nombreDHeuresSelonRegroupement(int codeDuRegroupement);

    public abstract int heuresBrutesSelonCategorie(String categorie);

    public abstract boolean formationComplete();

    

}
