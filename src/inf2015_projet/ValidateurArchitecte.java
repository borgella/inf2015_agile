package inf2015_projet;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 *
 * @author Jean Mary Borgella
 */

public class ValidateurArchitecte {
    private Architecte architecte;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurArchitecte(Architecte architecte) {
        this.architecte = architecte;
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
    }

    

    public boolean validerLeCycle() {
        return architecte.getCycle().equals("2008-2010")
                || architecte.getCycle().equals("2010-2012")
                || architecte.getCycle().equals("2012-2014");
    }

    public int nombreDHeuresErronees() {
        ArrayList<JSONObject> liste = architecte.getActivitesRefusees();
        int somme = 0;
        for (int i = 0; i < liste.size(); ++i) {
            JSONObject activite = liste.get(i);
            somme += activite.getInt("heures");
        }
        return somme;
    }

    public int nombreDHeuresSelonRegroupement(int codeDuRegroupement) {
        ArrayList<JSONObject> liste = architecte.getActivitesAcceptees();
        int somme = 0;
        for (int i = 0; i < liste.size(); ++i) {
            JSONObject activite = liste.get(i);
            if (architecte.regroupementDesCategories(activite.getString("categorie")) == codeDuRegroupement) {
                System.out.println("addition de..");
                somme += activite.getInt("heures");
            }
        }
        return somme;
    }

    private int heuresTotalesPourRegroupementDesSixCategories() {
        return nombreDHeuresSelonRegroupement(1) + heuresTransfereesEffectives();
    }

    private int heuresTransfereesEffectives() {
        int heuresTransferees = architecte.getHeuresTransferees();
        int heuresEffectives = heuresTransferees;
        if (heuresTransferees < 0) {
            heuresEffectives = 0;
        } else if (heuresTransferees > 7) {
            heuresEffectives = 7;
        }
        return heuresEffectives;
    }

    public int heuresTotalesFormation() {
        int heuresSixCategoriesEtTransferees = heuresTotalesPourRegroupementDesSixCategories();
        System.out.println(heuresSixCategoriesEtTransferees);
        int heuresPresentation = heuresEffectivesSelonCategorie("présentation");
        System.out.println(heuresPresentation);
        int heuresDiscussion = heuresEffectivesSelonCategorie("groupe de discussion");
        System.out.println(heuresDiscussion);
        int heuresRecherche = heuresEffectivesSelonCategorie("projet de recherche");
        System.out.println(heuresRecherche);
        int heuresRedaction = heuresEffectivesSelonCategorie("rédaction professionnelle");
        System.out.println(heuresRedaction);

        return heuresTotal = heuresSixCategoriesEtTransferees
                + heuresPresentation + heuresDiscussion
                + heuresRecherche + heuresRedaction;
    }

    public int heuresEffectivesSelonCategorie(String categorie) {
        int heuresBrutes = heuresBrutesSelonCategorie(categorie);
        int maximumHeures = maximumHeuresSelonCategorie(categorie);
        return (heuresBrutes > maximumHeures? maximumHeures : heuresBrutes); 
    }
    
    private int heuresBrutesSelonCategorie(String categorie) {
        ArrayList<JSONObject> liste = architecte.getActivitesAcceptees();
        int heuresTotales = 0;
        for (JSONObject activiteCourante: liste) {
            if (architecte.estDansCategorie(activiteCourante.getString("categorie"))) {
                heuresTotales += activiteCourante.getInt("heures");
            }
        }
        return heuresTotales;
    }

    private int maximumHeuresSelonCategorie(String categorie) {
        int nombreMaximumHeures = Integer.MAX_VALUE;
        if(categorie.equals("présentation") || categorie.equals("projet de recherche")) {
            nombreMaximumHeures = 23;
        } else if (categorie.equals("groupe de discussion") || categorie.equals("rédaction professionnelle")) {
            nombreMaximumHeures = 17;
        }
        return nombreMaximumHeures;
    }

    public void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. Seul le cycle 2012-2014 est accepté.");
        }

    }

    public void messageErreurPourDateInvalide() {
        ArrayList<JSONObject> liste = architecte.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
         sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                JSONObject activite = liste.get(i);
                if (!architecte.dateValidePourCycle2010_2012(activite.getString("date"))) {
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
        ArrayList<JSONObject> liste = architecte.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                JSONObject activite = liste.get(i);
                if (architecte.regroupementDesCategories(activite.getString("categorie")) == -1) {
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

    public void messageErreurSiHeuresTransferesEstInvalide() {
        if (architecte.getHeuresTransferees() > 7) {
            messagesErreurs.add("Le nombre d'heures transférées est supérieur à 7. Seulement 7 heures seront comptabilisées.");
        } else if (architecte.getHeuresTransferees() < 0) {
            messagesErreurs.add("Le nombre d'heures transférées est inférieur à 0. Ce nombre sera comptabilisé comme 0.");
        }

    }

    public void messageErreurPourHeuresManquantes() {
        String messageHeuresManquantes = "";
        int heuresManquantesEnGeneral = 40 - heuresTotalesFormation();
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesEnGeneral > 0 || heuresManquantesSixCategories > 0) {
            int heuresManquantesPourLeCycle
                    = heuresManquantesEnGeneral > heuresManquantesSixCategories ? heuresManquantesEnGeneral : heuresManquantesSixCategories;
            messageHeuresManquantes += "Il manque un total de " + heuresManquantesPourLeCycle + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public void messageErreurPourHeuresInsuffisantesSixCategories() {
        String messageHeuresManquantes = "";
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesSixCategories > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesSixCategories
                    + " heure(s) de formation à compléter parmi les catégories suivantes: "
                    + "cours, atelier, séminaire, colloque, conférence et lecture dirigée.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public void messageErreurPourHeuresErronees() {
        String messageErrone = "";
        if (nombreDHeuresErronees() > 0) {
            messageErrone += "Il manque " + nombreDHeuresErronees() + " heures de formation pour compléter le cycle.";
            messagesErreurs.add(messageErrone);
        }
    }

    public void messageErreurPourHeuresActivitesNegatif() {
        ArrayList<JSONObject> liste = architecte.getActivitesRefusees();
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
        return heuresTotal >= 40 && validerLeCycle() && (nombreDHeuresSelonRegroupement(1) >= 17);
    }

    public void appelsDesMethodesDesMessagesInvalides() {
        messageErreurSiLeCycleEstInvalide();
        if (validerLeCycle()) {
            messageErreurSiHeuresTransferesEstInvalide();
            messageInvalidePourCategorieNonReconnue();
            messageErreurPourHeuresActivitesNegatif();
            messageErreurPourDateInvalide();
            messageErreurPourHeuresManquantes();
            messageErreurPourHeuresInsuffisantesSixCategories();
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
