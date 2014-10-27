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

    public boolean validerLeCycle() {
        return membre.getCycle().equals("2010-2015");
    }

    /**
     * Les psychologues doivent effectuer un minimum de 90 heures de formation continue dans un cycle.
     *
     * @param nombreDHeures
     * @return
     */
    public boolean minimumHeuresFormationCycle(int nombreDHeures) {
        return nombreDHeures >= 90;
    }

    /**
     * Un minimum de 25 heures par cycle sont nécessaires dans la catégorie "cours"
     *
     * @param categorie
     * @return
     */
    protected int minimumHeuresSelonCategorie(String categorie) {
        int nombreMinimumHeures = Integer.MIN_VALUE;

        if (categorie.equals("cours")) {
            nombreMinimumHeures = 25;
        }

        return nombreMinimumHeures;
    }

    public int nombreDHeuresSelonRegroupement(int codeDuRegroupement) {
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

    public int heuresTotalesFormation() {
        int heuresHuitCategories = nombreDHeuresSelonRegroupement(2);
        int heuresCours = heuresBrutesSelonCategorie("cours");
        int heuresConference = heuresEffectivesSelonCategorie("conférence");

        return heuresTotal = heuresHuitCategories
                + heuresCours + heuresConference;
    }

    public int heuresEffectivesSelonCategorie(String categorie) {
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

    public void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. "
                    + "Seul le cycle 2010-2015 est accepté.");
        }

    }

    public void messageErreurPourDateInvalide() {
        String cycle = membre.getCycle();
        ArrayList<JSONObject> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                JSONObject activite = liste.get(i);
                if (!membre.dateValidePourMembre(activite.getString("date"))) {
                    descriptionsDesActivites.add(activite.getString("description"));
                    //retour += activite.getDescription() + " ";
                    sommation += 1;
                }
            }

            retour = convertirDescriptionsEnPhrase(descriptionsDesActivites);

            if (sommation > 1 && !(retour.equals(""))) {
                sortie += "Les dates des activités " + retour + " sont invalides. Ces activités seront ignorées.";
                messagesErreurs.add(sortie);
            } else if (!(retour.equals(""))) {
                sortie += "La date de l'activité " + retour + " est invalide. L'activité sera ignorée.";
                messagesErreurs.add(sortie);
            }
        }

    }

    private String convertirDescriptionsEnPhrase(ArrayList<String> descriptions) {
        int nombreDeDescriptions = descriptions.size();
        String phraseDeRetour = "";
        if (nombreDeDescriptions > 0) {
            phraseDeRetour += descriptions.get(0);
            for (int i = 1; i < nombreDeDescriptions - 1; i++) {
                phraseDeRetour += ", " + descriptions.get(i);
            }

            if (nombreDeDescriptions > 1) {
                phraseDeRetour += " et " + descriptions.get(nombreDeDescriptions - 1);
            }
        }
        return phraseDeRetour;
    }

    /**
     * Ajoute a l'arraylist messageErreurs un message personalise si la categorie n est pas reconnue
     */
    public void messageInvalidePourCategorieNonReconnue() {
        ArrayList<JSONObject> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                JSONObject activite = liste.get(i);
                if (membre.regroupementDesCategories(activite.getString("categorie")) == -1) {
                    descriptionsDesActivites.add(activite.getString("description"));
                    sommation += 1;
                }
            }

            retour = convertirDescriptionsEnPhrase(descriptionsDesActivites);

            if (sommation > 1 && !(retour.equals(""))) {
                sortie += "Les activités " + retour + " sont dans des catégories non reconnues. Elles seront ignorées.";
                messagesErreurs.add(sortie);
            } else if (!(retour.equals(""))) {
                sortie += "L'activité " + retour + " est dans une catégorie non reconnue. Elle sera ignorée.";
                messagesErreurs.add(sortie);
            }
        }

    }

    public void messageErreurPourHeuresManquantes() {
        String messageHeuresManquantes = "";
        int heuresManquantesEnGeneral = 90 - heuresTotalesFormation();
        int heuresManquantesCours = 25 - nombreDHeuresSelonRegroupement(1);
        if (heuresManquantesEnGeneral > 0 || heuresManquantesCours > 0) {
            int heuresManquantesPourLeCycle = max(heuresManquantesEnGeneral, heuresManquantesCours);
            messageHeuresManquantes += "Il manque un total de " + heuresManquantesPourLeCycle
                    + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public void messageErreurPourHeuresInsuffisantesCours() {
        String messageHeuresManquantes = "";
        int heuresManquantesCours = 25 - nombreDHeuresSelonRegroupement(1);
        if (heuresManquantesCours > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesCours
                    + " heure(s) de formation à compléter sous la catégorie cours.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public boolean formationComplete() {
        boolean critereCours = nombreDHeuresSelonRegroupement(1) >= 25;
        boolean critereCycle = validerLeCycle();
        boolean critereDHeuresTotales = heuresTotal >= 90;
        return critereCours && critereCycle && critereDHeuresTotales;
    }

    public void appelsDesMethodesDesMessagesInvalides() {
        messageErreurSiLeCycleEstInvalide();
        if (validerLeCycle()) {
            messageInvalidePourCategorieNonReconnue();
            messageErreurPourDateInvalide();
            messageErreurPourHeuresManquantes();
            messageErreurPourHeuresInsuffisantesCours();
        }

    }

    public JSONObject produireRapport() {
        JSONObject texteDeSortie = new JSONObject();
        appelsDesMethodesDesMessagesInvalides();
        texteDeSortie.accumulate("complet", formationComplete());
        texteDeSortie.accumulate("erreurs", messagesErreurs);
        return texteDeSortie;
    }

    protected int maximumHeuresSelonCategorie(String categorie) {
        int nombreMaximumHeures = Integer.MAX_VALUE;

        if (categorie.equals("conférence")) {
            nombreMaximumHeures = 15;
        }

        return nombreMaximumHeures;
    }
}
