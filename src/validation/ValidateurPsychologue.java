package validation;

import java.util.ArrayList;
import net.sf.json.JSONObject;
import professionnels.Psychologue;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurPsychologue {

    private Psychologue membre;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurPsychologue(Psychologue psychologue) {
        this.membre = psychologue;
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
    }

    public JSONObject produireRapport() {
        JSONObject texteDeSortie = new JSONObject();
        construireMessagesDErreur();
        texteDeSortie.accumulate("complet", formationComplete());
        texteDeSortie.accumulate("erreurs", messagesErreurs);
        return texteDeSortie;
    }

    private void construireMessagesDErreur() {
        messageErreurSiLeCycleEstInvalide();
        if (validerLeCycle()) {
            messageInvalidePourCategorieNonReconnue();
            messageErreurPourDateInvalide();
            messageErreurPourHeuresManquantes();
            messageErreurPourHeuresInsuffisantesCours();
        }
    }

    private void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. "
                    + "Seul le cycle 2010-2015 est accepté.");
        }
    }

    private boolean validerLeCycle() {
        return membre.getCycle().equals("2010-2015");
    }

    private void messageInvalidePourCategorieNonReconnue() {
        ArrayList<JSONObject> liste = membre.getActivitesRefusees();
        ArrayList<String> descriptionsDesActivites = descriptionsDActivitesAvecCategorieNonReconnue(liste);
        int nombreDActivitesNonReconnues = descriptionsDesActivites.size();
        String activitesErronees = convertirDescriptionsEnPhrase(descriptionsDesActivites);
        if (nombreDActivitesNonReconnues > 0) {
            ecrireMessageDErreurPourCategoriesNonReconnues(nombreDActivitesNonReconnues, activitesErronees);
        }
    }

    private ArrayList<String> descriptionsDActivitesAvecCategorieNonReconnue(ArrayList<JSONObject> liste) {
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        for (int i = 0; i < liste.size(); ++i) {
            JSONObject activite = liste.get(i);
            int codeRegroupement = membre.regroupementDesCategories(activite.getString("categorie"));
            if (codeRegroupement == -1) {
                descriptionsDesActivites.add(activite.getString("description"));
            }
        }
        return descriptionsDesActivites;
    }

    private String convertirDescriptionsEnPhrase(ArrayList<String> descriptions) {
        int nombreDeDescriptions = descriptions.size();
        String phraseDeRetour = "";
        if (nombreDeDescriptions > 0) {
            phraseDeRetour = construirePhraseAvecDescriptions(descriptions);
        }
        return phraseDeRetour;
    }

    private String construirePhraseAvecDescriptions(ArrayList<String> descriptions) {
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

    private void ecrireMessageDErreurPourCategoriesNonReconnues(int nombreDActivites, String activitesErronees) {
        String messageSortie;
        if (nombreDActivites > 1) {
            messageSortie = "Les activités " + activitesErronees + " sont dans des catégories non reconnues. "
                    + "Elles seront ignorées.";
        } else {
            messageSortie = "L'activité " + activitesErronees + " est dans une catégorie non reconnue. "
                    + "Elle sera ignorée.";
        }
        messagesErreurs.add(messageSortie);
    }

    private void messageErreurPourDateInvalide() {
        ArrayList<JSONObject> liste = membre.getActivitesRefusees();
        ArrayList<String> descriptionsDesActivites = descriptionsDActivitesAvecDateInvalide(liste);
        int nombreDActivitesNonReconnues = descriptionsDesActivites.size();
        String activitesErronees = convertirDescriptionsEnPhrase(descriptionsDesActivites);
        if (nombreDActivitesNonReconnues > 0) {
            ecrireMessageDErreurPourDatesInvalides(nombreDActivitesNonReconnues, activitesErronees);
        }
    }

    private ArrayList<String> descriptionsDActivitesAvecDateInvalide(ArrayList<JSONObject> liste) {
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        for (int i = 0; i < liste.size(); ++i) {
            JSONObject activite = liste.get(i);
            if (!membre.dateValidePourMembre(activite.getString("date"))) {
                descriptionsDesActivites.add(activite.getString("description"));
            }
        }
        return descriptionsDesActivites;
    }

    private void ecrireMessageDErreurPourDatesInvalides(int nombreDActivites, String activitesErronees) {
        String messageSortie;
        if (nombreDActivites > 1) {
            messageSortie = "Les dates des activités " + activitesErronees + " sont invalides. "
                    + "Ces activités seront ignorées.";
        } else {
            messageSortie = "La date de l'activité " + activitesErronees + " est invalide. L'activité sera ignorée.";
        }
        messagesErreurs.add(messageSortie);
    }

    private void messageErreurPourHeuresManquantes() {
        int heuresManquantesEnGeneral = 90 - heuresTotalesFormation();
        int heuresManquantesCours = 25 - nombreDHeuresSelonRegroupement(1);
        if (heuresManquantesEnGeneral > 0 || heuresManquantesCours > 0) {
            int heuresManquantesPourLeCycle = max(heuresManquantesEnGeneral, heuresManquantesCours);
            String messageHeuresManquantes = "Il manque un total de " + heuresManquantesPourLeCycle
                    + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    private int heuresTotalesFormation() {
        int heuresHuitCategories = nombreDHeuresSelonRegroupement(2);
        int heuresCours = heuresBrutesSelonCategorie("cours");
        int heuresConference = heuresEffectivesSelonCategorie("conférence");

        return heuresTotal = heuresHuitCategories
                + heuresCours + heuresConference;
    }

    private int nombreDHeuresSelonRegroupement(int codeDuRegroupement) {
        ArrayList<JSONObject> liste = membre.getActivitesAcceptees();
        int somme = 0;
        for (int i = 0; i < liste.size(); ++i) {
            JSONObject activite = liste.get(i);
            if (membre.regroupementDesCategories(activite.getString("categorie")) == codeDuRegroupement) {
                somme += activite.getInt("heures");
            }
        }
        return somme;
    }

    private int heuresBrutesSelonCategorie(String categorie) {
        ArrayList<JSONObject> liste = membre.getActivitesAcceptees();
        int heuresTotales = 0;
        for (JSONObject activiteCourante : liste) {
            String categorieCourante = activiteCourante.getString("categorie");
            if (categorieCourante.equals(categorie)) {
                heuresTotales += activiteCourante.getInt("heures");
            }
        }
        return heuresTotales;
    }

    private int heuresEffectivesSelonCategorie(String categorie) {
        int heuresBrutes = heuresBrutesSelonCategorie(categorie);
        int maximumHeures = maximumHeuresSelonCategorie(categorie);
        return min(heuresBrutes, maximumHeures);
    }

    private int max(int nombre1, int nombre2) {
        return nombre1 > nombre2 ? nombre1 : nombre2;
    }

    private int min(int nombre1, int nombre2) {
        return nombre1 < nombre2 ? nombre1 : nombre2;
    }

    private int maximumHeuresSelonCategorie(String categorie) {
        int nombreMaximumHeures = Integer.MAX_VALUE;
        if (categorie.equals("conférence")) {
            nombreMaximumHeures = 15;
        }
        return nombreMaximumHeures;
    }

    private void messageErreurPourHeuresInsuffisantesCours() {
        String messageHeuresManquantes = "";
        int heuresManquantesCours = 25 - nombreDHeuresSelonRegroupement(1);
        if (heuresManquantesCours > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesCours
                    + " heure(s) de formation à compléter sous la catégorie cours.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    private boolean formationComplete() {
        boolean critereCours = nombreDHeuresSelonRegroupement(1) >= 25;
        boolean critereCycle = validerLeCycle();
        boolean critereDHeuresTotales = heuresTotal >= 90;
        return critereCours && critereCycle && critereDHeuresTotales;
    }

}
