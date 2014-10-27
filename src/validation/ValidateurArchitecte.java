package validation;

import professionnels.Architecte;
import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurArchitecte {

    private Architecte membre;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurArchitecte(Architecte architecte) {
        this.membre = architecte;
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
            messageErreurSiHeuresTransferesEstInvalide();
            messageInvalidePourCategorieNonReconnue();
            messageErreurPourDateInvalide();
            messageErreurPourHeuresManquantes();
            messageErreurPourHeuresInsuffisantesSixCategories();
        }
    }

    private void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. "
                    + "Seuls les cycles 2008-2010, 2010-2012 et 2012-2014 sont acceptés.");
        }
    }

    private boolean validerLeCycle() {
        return membre.getCycle().equals("2008-2010")
                || membre.getCycle().equals("2010-2012")
                || membre.getCycle().equals("2012-2014");
    }

    private void messageErreurSiHeuresTransferesEstInvalide() {
        if (membre.getHeuresTransferees() > 7) {
            messagesErreurs.add("Le nombre d'heures transférées est supérieur à 7. "
                    + "Seulement 7 heures seront comptabilisées.");
        } else if (membre.getHeuresTransferees() < 0) {
            messagesErreurs.add("Le nombre d'heures transférées est inférieur à 0. "
                    + "Ce nombre sera comptabilisé comme 0.");
        }
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
        int heuresManquantesEnGeneral = nombreDHeuresRequisParCycle() - heuresTotalesFormation();
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesEnGeneral > 0 || heuresManquantesSixCategories > 0) {
            int heuresManquantesPourLeCycle = max(heuresManquantesEnGeneral, heuresManquantesSixCategories);
            String messageHeuresManquantes =  "Il manque un total de " + heuresManquantesPourLeCycle
                    + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    private int nombreDHeuresRequisParCycle() {
        String cycle = membre.getCycle();
        int nombreDHeuresRequis;
        if (cycle.equals("2012-2014")) {
            nombreDHeuresRequis = 40;
        } else {
            nombreDHeuresRequis = 42;
        }
        return nombreDHeuresRequis;
    }

    private int heuresTotalesFormation() {
        int heuresSixCategoriesEtTransferees = heuresTotalesPourRegroupementDesSixCategories();
        int heuresPresentation = heuresEffectivesSelonCategorie("présentation");
        int heuresDiscussion = heuresEffectivesSelonCategorie("groupe de discussion");
        int heuresRecherche = heuresEffectivesSelonCategorie("projet de recherche");
        int heuresRedaction = heuresEffectivesSelonCategorie("rédaction professionnelle");

        return heuresTotal = heuresSixCategoriesEtTransferees
                + heuresPresentation + heuresDiscussion
                + heuresRecherche + heuresRedaction;
    }

    private int heuresTotalesPourRegroupementDesSixCategories() {
        return nombreDHeuresSelonRegroupement(1) + heuresTransfereesEffectives();
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

    private int heuresTransfereesEffectives() {
        int heuresTransferees = membre.getHeuresTransferees();
        int heuresEffectives = heuresTransferees;
        if (heuresTransferees < 0) {
            heuresEffectives = 0;
        } else if (heuresTransferees > 7) {
            heuresEffectives = 7;
        }
        return heuresEffectives;
    }

    private int heuresEffectivesSelonCategorie(String categorie) {
        int heuresBrutes = heuresBrutesSelonCategorie(categorie);
        int maximumHeures = maximumHeuresSelonCategorie(categorie);
        return min(heuresBrutes, maximumHeures);
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

    private int maximumHeuresSelonCategorie(String categorie) {
        int nombreMaximumHeures = Integer.MAX_VALUE;
        if (categorie.equals("présentation") || categorie.equals("projet de recherche")) {
            nombreMaximumHeures = 23;
        } else if (categorie.equals("groupe de discussion") || categorie.equals("rédaction professionnelle")) {
            nombreMaximumHeures = 17;
        }
        return nombreMaximumHeures;
    }

    private int min(int nombre1, int nombre2) {
        return nombre1 < nombre2 ? nombre1 : nombre2;
    }

    private int max(int nombre1, int nombre2) {
        return nombre1 > nombre2 ? nombre1 : nombre2;
    }

    private void messageErreurPourHeuresInsuffisantesSixCategories() {
        String messageHeuresManquantes = "";
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesSixCategories > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesSixCategories
                    + " heure(s) de formation à compléter parmi les catégories suivantes: "
                    + "cours, atelier, séminaire, colloque, conférence et lecture dirigée.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    private boolean formationComplete() {
        boolean critereSixCategories = nombreDHeuresSelonRegroupement(1) >= 17;
        boolean critereDHeuresTotales;
        String cycle = membre.getCycle();
        if (cycle.equals("2012-2014")) {
            critereDHeuresTotales = heuresTotal >= 40;
        } else {
            critereDHeuresTotales = heuresTotal >= 42;
        }
        return validerLeCycle() && critereSixCategories && critereDHeuresTotales;
    }

}
