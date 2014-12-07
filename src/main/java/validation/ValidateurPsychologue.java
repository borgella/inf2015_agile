package validation;

import java.util.ArrayList;
import net.sf.json.JSONObject;
import professionnels.Psychologue;
import professionnels.Membre;
/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurPsychologue extends Validateur {

    private Psychologue membre;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurPsychologue(Membre psychologue) {
        this.membre = (Psychologue) psychologue;
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
    }
    
    @Override
    public JSONObject produireRapport() {
        return produireRapport(messagesErreurs);
    }

    @Override
    public void construireMessagesDErreur() {
        messageErreurSiLeCycleEstInvalide();
        if (validerLeCycle()) {
            messageInvalidePourCategorieNonReconnue(membre);
            messageErreurPourDateInvalide(membre);
            messageErreurPourHeuresManquantes();
            messageErreurPourHeuresInsuffisantesCours();
        }
    }

    @Override
    public void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. "
                    + "Seul le cycle 2010-2015 est accepté.");
        }
    }

    @Override
    public boolean validerLeCycle() {
        return membre.getCycle().equals("2010-2015");
    }

    @Override
    public void ecrireMessageDErreurPourCategoriesNonReconnues(int nombreDActivites, String activitesErronees) {
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

    @Override
    public void ecrireMessageDErreurPourDatesInvalides(int nombreDActivites, String activitesErronees) {
        String messageSortie;
        if (nombreDActivites > 1) {
            messageSortie = "Les dates des activités " + activitesErronees + " sont invalides. "
                    + "Ces activités seront ignorées.";
        } else {
            messageSortie = "La date de l'activité " + activitesErronees + " est invalide. L'activité sera ignorée.";
        }
        messagesErreurs.add(messageSortie);
    }

    @Override
    public void messageErreurPourHeuresManquantes() {
        int heuresManquantesEnGeneral = 90 - heuresTotalesFormation();
        int heuresManquantesCours = 25 - nombreDHeuresSelonRegroupement(1);
        if (heuresManquantesEnGeneral > 0 || heuresManquantesCours > 0) {
            int heuresManquantesPourLeCycle = Integer.max(heuresManquantesEnGeneral, heuresManquantesCours);
            String messageHeuresManquantes = "Il manque un total de " + heuresManquantesPourLeCycle
                    + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    @Override
    public int heuresTotalesFormation() {
        int heuresHuitCategories = nombreDHeuresSelonRegroupement(2);
        int heuresCours = heuresBrutesSelonCategorie("cours");
        int heuresConference = heuresEffectivesSelonCategorie("conférence");

        return heuresTotal = heuresHuitCategories
                + heuresCours + heuresConference;
    }

    @Override
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
    
    private int heuresEffectivesSelonCategorie(String categorie) {
        int heuresBrutes = heuresBrutesSelonCategorie(categorie);
        int maximumHeures = maximumHeuresSelonCategorie(categorie);
        return Integer.min(heuresBrutes, maximumHeures);
    }

    @Override
    public int heuresBrutesSelonCategorie(String categorie) {
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

    @Override
    public boolean formationComplete() {
        boolean critereCours = nombreDHeuresSelonRegroupement(1) >= 25;
        boolean critereDHeuresTotales = heuresTotal >= 90;
        return validerLeCycle() && critereCours && critereDHeuresTotales;
    }

}
