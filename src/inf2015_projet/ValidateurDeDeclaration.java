/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author df891101
 */
public class ValidateurDeDeclaration {

    private DeclarationDeFormation membre;
    private boolean formationIncomplete;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurDeDeclaration(DeclarationDeFormation membre) {
        this.membre = membre;
        formationIncomplete = false;
        messagesErreurs = new ArrayList();
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
        ActiviteDeFormation act;
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesAcceptees();
        int somme = 0;
        for (int i = 0; i < membre.getActivitesAcceptees().size(); ++i) {
            act = liste.get(i);
            if (act.regroupementDesCategories(act.getCategorie()) == codeDuRegroupement) {
                somme += act.getDureeEnHeures();
            }
        }
        return somme;
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

    public String messageInvalide() {
        ActiviteDeFormation act;
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        String retour = " ";
        String message = " est dans une categorie  non reconnue. Elle sera ignoree. ";
        String message2 = " heure(s) de formation pour completer le cycle .";
        if ((!(formationComplete())) && liste != null) {
            for (int i = 0; i < membre.getActivitesRefusees().size(); ++i) {
                act = liste.get(i);
                retour += act.getDescription() + " ";
            }
        } else {
            return " ";
        }
        return "Le(s) activite(s) " + retour + " " + message + " Il manque " + nombreDHeuresErronees() + " " + message2;
    }

    public boolean formationComplete() {
        return heuresTotal >= 40 && validerLeCycle();
    }

    // Fonction à complétéer
    public void produireMessagesDErreurs() {
        ArrayList<ActiviteDeFormation> activitesInvalides = membre.getActivitesRefusees();

        if (membre.getHeuresTransferees() < 0 || membre.getHeuresTransferees() > 7) {
            messagesErreurs.add("Le nombre d'heures transférées" + membre.getHeuresTransferees() + " n'est pas valide.");
        }

        for (int i = 0; i < activitesInvalides.size(); i++) {
            messagesErreurs.add("L'activité " + activitesInvalides.get(i) + " n'est pas une activité de formation valide.");
        }

        if (!formationComplete()) {
            if (!validerLeCycle()) {
                messagesErreurs.add("Le cycle " + membre.getCycle() + " n'est pas un cycle valide.");
            }
            if (heuresTotal < 40) {
                messagesErreurs.add("Il manque " + (40 - heuresTotalesFormation()) + " heures de formation pour compléter le cycle.");
            }
        }

        if (nombreDHeuresSelonRegroupement(1) < 17) {
            messagesErreurs.add("Il manque " + (17 - nombreDHeuresSelonRegroupement(1))
                    + " heures de formation de catégorie cours, atelier, séminaire, colloque, conférence ou lecture dirigée"
                    + " pour compléter le cycle.");
        }
    }

    public String produireRapport() {
        JSONObject texteDeSortie = new JSONObject();
        texteDeSortie.accumulate("complet", !formationIncomplete);
        texteDeSortie.accumulate("erreurs", messagesErreurs);
        return texteDeSortie.toString(2);
    }

}
