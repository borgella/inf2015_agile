package validation;

import declaration.ActiviteDeFormation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import professionnels.Geologue;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurGeologues {

    private Geologue membre;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurGeologues(Geologue geologue) {
        this.membre = geologue;
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
    }

    public boolean validerLeCycle() {
        return membre.getCycle().equals("2013-2016");
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

    private int heuresTotalesPourRegroupementDesSeptCategories() {
        return nombreDHeuresSelonRegroupement(1);
    }

    public int heuresTotalesFormation() {
        int heuresSeptCategories = heuresTotalesPourRegroupementDesSeptCategories();
        int heuresPresentation = heuresBrutesSelonCategorie("cours");
        int heuresRecherche = heuresBrutesSelonCategorie("projet de recherche");
        int heuresDiscussion = heuresBrutesSelonCategorie("groupe de discussion");

        return heuresTotal = heuresSeptCategories
                + heuresPresentation + heuresRecherche + +heuresDiscussion;
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

    /**
     * Intervalles permises d'une activité selon son cycle
     *
     * @param activite
     */
    public void intervallesActivitesCompleteesValides(ActiviteDeFormation activite) {
        String date = activite.getDateCompletee();

        if (membre.getCycle().equals("2013-2016")) {
            dateActivitesCompleteesValides(date, "2013-06-01", "2016-06-01");
        }
    }

    /**
     * Le cycle "2013-2016" pourra contenir des activités effectuées du 1er juin
     * 2013 et le 1er juin 2016 inclusivement
     *
     * Date invalide => MESSAGE D'ERREUR + activité ignorée des calculs Les
     * dates sont indiquées en format ISO-8601
     *
     * @param date
     * @param intervalleMinimum
     * @param intervalleMaximum
     * @return true si la date se trouve dans les intervalles
     */
    public boolean dateActivitesCompleteesValides(String date, String intervalleMinimum, String intervalleMaximum) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dateIntervalleMinimum = null;
        Date dateIntervalleMaximum = null;
        Date dateLue = null;

        try {
            dateIntervalleMinimum = df.parse(intervalleMinimum);
            dateIntervalleMaximum = df.parse(intervalleMaximum);
            dateLue = df.parse(date);
        } catch (ParseException e) {
            e.getMessage();
        }

        return (dateLue.after(dateIntervalleMinimum) || (dateLue.equals(dateIntervalleMinimum))
                && (dateLue.before(dateIntervalleMaximum) || dateLue.equals(dateIntervalleMaximum)));
    }

    /**
     * Les géologues doivent effectuer un minimum de 55 heures de formation
     * continue dans un cycle.
     *
     * @param nombreDHeures
     * @return
     */
    public boolean minimumHeuresFormationCycle(int nombreDHeures) {
        return nombreDHeures >= 55;
    }

    /**
     * Un minimum de 22 heures par cycle sont nécessaires dans la catégorie
     * "cours" Un minimum de 3 heures par cycle sont nécessaires dans la
     * catégorie "projet de recherche" Un minimum d'une heure par cycle est
     * nécessaire dans la catégorie "groupe de discussion"
     *
     * @param categorie
     * @return
     */
    protected int minimumHeuresSelonCategorie(String categorie) {
        int nombreMinimumHeures = 0;

        if (categorie.equals("cours")) {
            nombreMinimumHeures = 22;
        } else if (categorie.equals("projet de recherche")) {
            nombreMinimumHeures = 3;
        } else if (categorie.equals("groupe de discussion")) {
            nombreMinimumHeures = 1;
        }

        return nombreMinimumHeures;
    }

    public void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. Seul le cycle 2013-2016 est accepté.");
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
                if (!membre.dateValidePourGeologues(activite.getString("date"))) {
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
     * Ajoute a l'arraylist messageErreurs un message personalise si la
     * categorie n est pas reconnue
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
        int heuresManquantesEnGeneral = 55 - heuresTotalesFormation();
        int heuresManquantesCours = 22 - heuresBrutesSelonCategorie("cours");
        int heuresManquantesRecherche = 3 - heuresBrutesSelonCategorie("projet de recherche");
        int heuresManquantesDiscussion = 1 - heuresBrutesSelonCategorie("groupe de discussion");
        
        int maximum1 = max(heuresManquantesEnGeneral, heuresManquantesCours);
        int maximum2 = max(maximum1, heuresManquantesRecherche);
        int maximum3 = max(maximum2, heuresManquantesDiscussion);
        
        if (heuresManquantesEnGeneral > 0 || heuresManquantesCours > 0 || heuresManquantesRecherche > 0 || heuresManquantesDiscussion > 0) {
            int heuresManquantesPourLeCycle = maximum3;
            messageHeuresManquantes += "Il manque un total de " + heuresManquantesPourLeCycle + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public void messageErreurPourHeuresInsuffisantesCours() {
        String messageHeuresManquantes = "";
        int heuresManquantesCours = 22 - heuresBrutesSelonCategorie("cours");
        if (heuresManquantesCours > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesCours
                    + " heure(s) de formation à compléter sous la catégorie cours.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }
    
    public void messageErreurPourHeuresInsuffisantesRecherche() {
        String messageHeuresManquantes = "";
        int heuresManquantesRecherche = 3 - heuresBrutesSelonCategorie("projet de recherche");
        if (heuresManquantesRecherche > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesRecherche
                    + " heure(s) de formation à compléter sous la catégorie projet de recherche.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }
    
    public void messageErreurPourHeuresInsuffisantesDiscussion() {
        String messageHeuresManquantes = "";
        int heuresManquantesDiscussion = 1 - heuresBrutesSelonCategorie("groupe de discussion");
        if (heuresManquantesDiscussion > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesDiscussion
                    + " heure de formation à compléter sous la catégorie groupe de discussion.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public void messageErreurPourHeuresActivitesNegatif() {
        ArrayList<JSONObject> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        retour = sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                JSONObject activite = liste.get(i);
                if (activite.getInt("heures") < 1) {
                    descriptionsDesActivites.add(activite.getString("description"));
                    sommation += 1;
                }
            }
            retour = convertirDescriptionsEnPhrase(descriptionsDesActivites);
        }
        if (sommation > 1 && !(retour.equals(""))) {
            sortie += "Les heures des activités " + retour + " sont invalides. Ces activités seront ignorées.";
            messagesErreurs.add(sortie);
        } else if (!(retour.equals(""))) {
            sortie += "Les heures de l'activité " + retour + " sont invalides. Cette activité sera ignorée.";
            messagesErreurs.add(sortie);
        }
    }

    public JSONArray leMessageInvalide(ArrayList message) {
        JSONArray tab = new JSONArray();
        for (int i = 0; i < message.size(); ++i) {
            tab.add(message.get(i));
        }
        return tab;
    }

    public boolean formationComplete() {
        boolean critereCycle = validerLeCycle();
        boolean critereCours = heuresBrutesSelonCategorie("cours") >= 22;
        boolean critereRecherche = heuresBrutesSelonCategorie("projet de recherche") >= 3;
        boolean critereDiscussion = heuresBrutesSelonCategorie("groupe de discussion") >= 1;
        boolean critereDHeuresTotales = heuresTotal >= 55;
        return critereCycle && critereCours && critereRecherche
                && critereDiscussion && critereDHeuresTotales;
    }

    public void appelsDesMethodesDesMessagesInvalides() {
        messageErreurSiLeCycleEstInvalide();
        if (validerLeCycle()) {
            messageInvalidePourCategorieNonReconnue();
            messageErreurPourDateInvalide();
            messageErreurPourHeuresManquantes();
            messageErreurPourHeuresInsuffisantesCours();
            messageErreurPourHeuresInsuffisantesRecherche();
            messageErreurPourHeuresInsuffisantesDiscussion();
        }

    }

    public JSONObject produireRapport() {
        JSONObject texteDeSortie = new JSONObject();
        JSONObject messageErrones = new JSONObject();
        appelsDesMethodesDesMessagesInvalides();
        JSONArray tableauJson = leMessageInvalide(messagesErreurs);
        texteDeSortie.accumulate("complet", formationComplete());
        texteDeSortie.accumulate("erreurs", tableauJson);
        return texteDeSortie;
    }

}
