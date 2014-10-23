/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import declaration.ActiviteDeFormation;
import declaration.DeclarationDeFormation;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ReglesPourGeologues extends ValidateurDeDeclaration {

    public ReglesPourGeologues(DeclarationDeFormation membre) {
        super(membre);
    }

    public boolean validerLeCycle() {
        return membre.getCycle().equals("2013-2016");
    }

    public int nombreDHeuresErronees() {
        ActiviteDeFormation act;
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int somme = 0;
        for (int i = 0; i < membre.getActivitesRefusees().size(); ++i) {
            act = liste.get(i);
            somme += act.getDureeEnHeures();
        }
        return somme;
    }

    public int nombreDHeuresSelonRegroupement(int codeDuRegroupement) {
        ActiviteDeFormation activite;
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesAcceptees();
        int somme = 0;
        for (int i = 0; i < liste.size(); ++i) {
            activite = liste.get(i);
            if (activite.regroupementDesCategories(activite.getCategorie()) == codeDuRegroupement) {
                somme += activite.getDureeEnHeures();
            }
        }
        return somme;
    }

    protected int heuresTotalesPourRegroupementDesSixCategories() {
        return (nombreDHeuresSelonRegroupement(1)/* + heuresTransfereesEffectives()*/);
    }

    /*protected int heuresTransfereesEffectives() {
     int heuresTransferees = membre.getHeuresTransferees();
     int heuresEffectives = heuresTransferees;
     if (heuresTransferees < 0) {
     heuresEffectives = 0;
     } else if (heuresTransferees > 7) {
     heuresEffectives = 7;
     }
     return heuresEffectives;
     }*/
    public int heuresTotalesFormation() {
        int heuresSixCategoriesEtTransferees = heuresTotalesPourRegroupementDesSixCategories();
        int heuresPresentation = heuresEffectivesSelonCategorie("présentation");
        int heuresDiscussion = heuresEffectivesSelonCategorie("groupe de discussion");
        int heuresRecherche = heuresEffectivesSelonCategorie("projet de recherche");
        int heuresRedaction = heuresEffectivesSelonCategorie("rédaction professionnelle");

        return heuresTotal = heuresSixCategoriesEtTransferees
                + heuresPresentation + heuresDiscussion
                + heuresRecherche + heuresRedaction;
    }

    public int heuresEffectivesSelonCategorie(String categorie) {
        int heuresBrutes = heuresBrutesSelonCategorie(categorie);
        int maximumHeures = maximumHeuresSelonCategorie(categorie);
        return (heuresBrutes > maximumHeures ? maximumHeures : heuresBrutes);
    }

    protected int heuresBrutesSelonCategorie(String categorie) {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesAcceptees();
        int heuresTotales = 0;
        for (ActiviteDeFormation activiteCourante : liste) {
            if (activiteCourante.estDansCategorie(categorie)) {
                heuresTotales += activiteCourante.getDureeEnHeures();
            }
        }
        return heuresTotales;
    }

    protected int minimumHeuresSelonCategorie(String categorie) {
        int nombreMinimumHeures = Integer.MIN_VALUE;
        if (categorie.equals("cours")) {
            nombreMinimumHeures = 22;
        } else if (categorie.equals("projet de recherche")) {
            nombreMinimumHeures = 3;
        } else if (categorie.equals("groupe de discussion")) {
            nombreMinimumHeures = 1;
        }
        return nombreMinimumHeures;
    }

    /*protected int maximumHeuresSelonCategorie(String categorie) {
     int nombreMaximumHeures = Integer.MAX_VALUE;
     if (categorie.equals("présentation") || categorie.equals("projet de recherche")) {
     nombreMaximumHeures = 23;
     } else if (categorie.equals("groupe de discussion") || categorie.equals("rédaction professionnelle")) {
     nombreMaximumHeures = 17;
     }
     return nombreMaximumHeures;
     }*/
    public void ecrireMessageDErreurPourCycleInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. Seul le cycle 2013-2016 est accepté.");
        }

    }

    public void ecrireMessageDErreurPourDateInvalide() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                ActiviteDeFormation activite = liste.get(i);
                if (!activite.dateActivitesCompleteesValides()) {
                    descriptionsDesActivites.add(activite.getDescription());
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

    protected String convertirDescriptionsEnPhrase(ArrayList<String> descriptions) {
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
    public void ecrireMessageDErreurPourCategorieNonReconnue() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        retour = sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                ActiviteDeFormation activite = liste.get(i);
                if (activite.regroupementDesCategories(activite.getCategorie()) == -1) {
                    descriptionsDesActivites.add(activite.getDescription());
                    //retour += activite.getDescription() + " ";
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

    /*public void ecrireMessageDErreurPourHeuresTransfereesInvalides() {
     if (membre.getHeuresTransferees() > 7) {
     messagesErreurs.add("Le nombre d'heures transférées est supérieur à 7. Seulement 7 heures seront comptabilisées.");
     } else if (membre.getHeuresTransferees() < 0) {
     messagesErreurs.add("Le nombre d'heures transférées est inférieur à 0. Ce nombre sera comptabilisé comme 0.");
     }

     }*/
    public void ecrireMessageDErreurPourHeuresManquantesTotales() {
        String messageHeuresManquantes = "";
        int heuresManquantesEnGeneral = 55 - heuresTotalesFormation();
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesEnGeneral > 0 || heuresManquantesSixCategories > 0) {
            int heuresManquantesPourLeCycle
                    = heuresManquantesEnGeneral > heuresManquantesSixCategories ? heuresManquantesEnGeneral : heuresManquantesSixCategories;
            messageHeuresManquantes += "Il manque un total de " + heuresManquantesPourLeCycle + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public void ecrireMessageDErreurPourHeuresManquantesSixCategories() {
        String messageHeuresManquantes = "";
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesSixCategories > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesSixCategories
                    + " heure(s) de formation à compléter parmi les catégories suivantes: "
                    + "cours, atelier, séminaire, colloque, conférence et lecture dirigée.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public void ecrireMessageDErreurPourHeuresErronees() {
        String messageErrone = "";
        if (nombreDHeuresErronees() > 0) {
            messageErrone += "Il manque " + nombreDHeuresErronees() + " heures de formation pour compléter le cycle.";
            messagesErreurs.add(messageErrone);
        }
    }

    public void ecrireMessageDErreurPourHeuresInvalidesDesActivites() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        retour = sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                ActiviteDeFormation activite = liste.get(i);
                if (activite.getDureeEnHeures() < 1) {
                    descriptionsDesActivites.add(activite.getDescription());
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

    // La formation est complète si le cycle est valide et si les heures totales sont au moins 55, 
    // dont au moins 17 dans le regroupement #1 dees catégories (groupe des 6 catégories).
    public boolean formationComplete() {
        return heuresTotal >= 55 && validerLeCycle() && (nombreDHeuresSelonRegroupement(1) >= 17);
    }

    public void ecrireMessagesDErreurs() {
        ecrireMessageDErreurPourCycleInvalide();
        if (validerLeCycle()) {
            //messageErreurSiHeuresTransferesEstInvalide();
            ecrireMessageDErreurPourCategorieNonReconnue();
            ecrireMessageDErreurPourHeuresInvalidesDesActivites();
            ecrireMessageDErreurPourDateInvalide();
            ecrireMessageDErreurPourHeuresManquantesTotales();
            ecrireMessageDErreurPourHeuresManquantesSixCategories();
        }

    }

    public JSONObject produireRapport() {
        JSONObject texteDeSortie = new JSONObject();
        JSONObject messageErrones = new JSONObject();
        ecrireMessagesDErreurs();
        JSONArray tableauJson = leMessageInvalide(messagesErreurs);
        texteDeSortie.accumulate("complet", formationComplete());
        texteDeSortie.accumulate("erreurs", tableauJson);
        return texteDeSortie;
    }
}
