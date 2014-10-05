/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import java.util.ArrayList;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 *
 * @author df891101
 */
public class ValidateurDeDeclaration {

    private DeclarationDeFormation membre;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurDeDeclaration(DeclarationDeFormation membre) {
        this.membre = membre;
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
    }

    /**
     * Cycle 2012-2014 Autre cycle => MESSAGE D'ERREUR
     *
     * @return
     */
    public boolean validerLeCycle() {
        return membre.getCycle().equals("2012-2014");
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
    
    private int heuresTotalesPourRegroupementDesSixCategories() {
        return (nombreDHeuresSelonRegroupement(1) + heuresTransfereesEffectives()); 
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
    
    public int heuresTotalesFormation() {
        int somme1 = nombreDHeuresSelonRegroupement(1);
        int somme2 = nombreDHeuresSelonRegroupement(2);
        int somme3 = nombreDHeuresSelonRegroupement(3);
        if (somme1 < 17 && somme1 != 0) {
            somme1 += membre.getHeuresTransferees();
        }
        if (somme2 <= 23 && somme2 != 0) {
            somme2 += membre.getHeuresTransferees();
        }
        if (somme3 <= 17 && somme3 != 0) {
            somme3 += membre.getHeuresTransferees();
        }
        return heuresTotal = somme1 + somme2 + somme3;
    }

    public void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et vos heures ne seront comptabilisees. ");
        }

    }

    public void messageErreurPourDateInvalide() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        retour = sortie = "";
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                ActiviteDeFormation activite = liste.get(i);
                if (activite.regroupementDesCategories(activite.getCategorie()) == 1) {
                    retour += activite.getDescription() + " ";
                    sommation += 1;
                }
            }
            if (sommation > 0 && !(retour.equals(""))) {
                sortie += "La date de la categorie " + retour + "est invalide . Elle sera ignoree des calculs. ";
                messagesErreurs.add(sortie);
            } else if (!(retour.equals(""))) {
                sortie += "Les dates des categories" + retour + "sont invalides, elles seront ignorees des calculs. ";
                messagesErreurs.add(sortie);
            }
        }

    }

    /**
     * Ajoute a l'arraylist messageErreurs un message personalise si la
     * categorie n est pas reconnue
     */
    public void messageInvalidePourCategorieNonReconnue() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        retour = sortie = "";
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                ActiviteDeFormation activite = liste.get(i);
                if (activite.regroupementDesCategories(activite.getCategorie()) == -1) {
                    retour += activite.getDescription() + " ";
                    sommation += 1;
                }
            }
            if (sommation > 0 && !(retour.equals(""))) {
                sortie += "L'activite " + retour + "est dans une categorie  non reconnue. Elle sera ignoree. ";
                messagesErreurs.add(sortie);
            } else if (!(retour.equals(""))) {
                sortie += "Les activites " + retour + "sont dans des categories  non reconnues. Elle sont ignorees. ";
                messagesErreurs.add(sortie);
            }
        }

    }

    public void messageErreurSiHeuresTransferesEstInvalide() {
        if (membre.getHeuresTransferees() > 7) {
            messagesErreurs.add("Les Heures transferees ont depasse 7 heures, seulement 7 heures seront comptabilises. ");
        } else if (membre.getHeuresTransferees() < 0) {
            messagesErreurs.add("Les Heures transferees ne doivent pas etre negatives,elles seront ignorees des calculs. ");
        }

    }

    public void messageErreurPourHeuresManquantes() {
        String messageHeuresManquantes = "";
        int heuresManquantesEnGeneral = 40 - heuresTotalesFormation();
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesEnGeneral > 0 || heuresManquantesSixCategories > 0) {
            int heuresManquantesPourLeCycle = 
                    heuresManquantesEnGeneral > heuresManquantesSixCategories ? heuresManquantesEnGeneral : heuresManquantesSixCategories;
            messageHeuresManquantes += "Il manque un total de " + heuresManquantesPourLeCycle + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }
    
    public void messageErreurPourHeuresInsuffisantesSixCategories () {
        String messageHeuresManquantes = "";
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesSixCategories > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesSixCategories 
                    + " heure(s) de formation à compléter parmi les catégories suivantes: "
                    + "cours, atelier, séminaire, colloque, conférence ou lecture dirigée.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }
    
    public void messageErreurPourHeuresErronees() {
        String messageErrone = "";
        if (nombreDHeuresErronees() > 0) {
            messageErrone += "Il manque " + nombreDHeuresErronees() + " heures de formation pour completer le cycle. ";
            messagesErreurs.add(messageErrone);
        }
    }
    
    public JSONArray leMessageInvalide(ArrayList message) {
        JSONArray tab = new JSONArray();
        for (int i = 0; i < message.size(); ++i) {
            tab.add(message.get(i));
        }
        return tab;
    }

    // La formation est complète ssi le cycle est valide et si les heures totales sont au moins 40, 
    // dont au moins 17 dans le regroupement #1 dees catégories (groupe des 6 catégories).
    public boolean formationComplete() {
        return heuresTotal >= 40 && validerLeCycle() && (nombreDHeuresSelonRegroupement(1) >= 17);
    }
    
    /*
     public boolean formationComplete() {
     return heuresTotal >= 40 && validerLeCycle();
     }
     */

    public void appelsDesMethodesDesMessagesInvalides() {
        messageErreurSiLeCycleEstInvalide();
        messageErreurPourDateInvalide();
        messageInvalidePourCategorieNonReconnue();
        messageErreurSiHeuresTransferesEstInvalide();
        messageErreurPourHeuresManquantes();
        messageErreurPourHeuresInsuffisantesSixCategories();
        //messageErreurPourHeuresErronees();
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
