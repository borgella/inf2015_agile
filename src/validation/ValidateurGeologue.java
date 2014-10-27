package validation;

import java.util.ArrayList;
import net.sf.json.JSONObject;
import professionnels.Geologue;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurGeologue {

    private Geologue membre;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurGeologue(Geologue geologue) {
        this.membre = geologue;
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
            messageErreurPourHeuresInsuffisantesParCategorie();
        }
    }

    private void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. "
                    + "Seul le cycle 2013-2016 est accepté.");
        }
    }

    private boolean validerLeCycle() {
        return membre.getCycle().equals("2013-2016");
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
        int heuresManquantesEnGeneral = 55 - heuresTotalesFormation();
        int heuresManquantesCours = 22 - heuresBrutesSelonCategorie("cours");
        int heuresManquantesRecherche = 3 - heuresBrutesSelonCategorie("projet de recherche");
        int heuresManquantesDiscussion = 1 - heuresBrutesSelonCategorie("groupe de discussion");
        int grandMaximum = maximumParmisQuatre(heuresManquantesEnGeneral, heuresManquantesCours,
                heuresManquantesRecherche, heuresManquantesDiscussion);
        ecrireMessageErreurPourHeuresManquantesSiApplicable(grandMaximum);
    }

    private int heuresTotalesFormation() {
        int heuresSeptCategories = heuresTotalesPourRegroupementDesSeptCategories();
        int heuresPresentation = heuresBrutesSelonCategorie("cours");
        int heuresRecherche = heuresBrutesSelonCategorie("projet de recherche");
        int heuresDiscussion = heuresBrutesSelonCategorie("groupe de discussion");

        return heuresTotal = heuresSeptCategories
                + heuresPresentation + heuresRecherche + +heuresDiscussion;
    }

    private int heuresTotalesPourRegroupementDesSeptCategories() {
        return nombreDHeuresSelonRegroupement(1);
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

    private int maximumParmisQuatre(int n1, int n2, int n3, int n4) {
        int premierMaximum = max(n1, n2);
        int deuxiemeMaximum = max(premierMaximum, n3);
        int grandMaximum = max(deuxiemeMaximum, n4);
        return grandMaximum;
    }

    private int max(int nombre1, int nombre2) {
        return nombre1 > nombre2 ? nombre1 : nombre2;
    }

    private void ecrireMessageErreurPourHeuresManquantesSiApplicable(int heuresManquantes) {
        if (heuresManquantes > 0) {
            String messageHeuresManquantes = "Il manque un total de " + heuresManquantes
                    + " heure(s) de formation pour compléter le cycle.";
        messagesErreurs.add(messageHeuresManquantes);
        }
    }

    private void messageErreurPourHeuresInsuffisantesParCategorie() {
        messageErreurPourHeuresInsuffisantesCours();
        messageErreurPourHeuresInsuffisantesRecherche();
        messageErreurPourHeuresInsuffisantesDiscussion();
    }

    private void messageErreurPourHeuresInsuffisantesCours() {
        String messageHeuresManquantes = "";
        int heuresManquantesCours = 22 - heuresBrutesSelonCategorie("cours");
        if (heuresManquantesCours > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesCours
                    + " heure(s) de formation à compléter sous la catégorie cours.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    private void messageErreurPourHeuresInsuffisantesRecherche() {
        String messageHeuresManquantes = "";
        int heuresManquantesRecherche = 3 - heuresBrutesSelonCategorie("projet de recherche");
        if (heuresManquantesRecherche > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesRecherche
                    + " heure(s) de formation à compléter sous la catégorie projet de recherche.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    private void messageErreurPourHeuresInsuffisantesDiscussion() {
        String messageHeuresManquantes = "";
        int heuresManquantesDiscussion = 1 - heuresBrutesSelonCategorie("groupe de discussion");
        if (heuresManquantesDiscussion > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesDiscussion
                    + " heure de formation à compléter sous la catégorie groupe de discussion.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    private boolean formationComplete() {
        boolean critereCours = heuresBrutesSelonCategorie("cours") >= 22;
        boolean critereRecherche = heuresBrutesSelonCategorie("projet de recherche") >= 3;
        boolean critereDiscussion = heuresBrutesSelonCategorie("groupe de discussion") >= 1;
        boolean critereDHeuresTotales = heuresTotal >= 55;
        return validerLeCycle() && critereCours && critereRecherche
                && critereDiscussion && critereDHeuresTotales;
    }

}
