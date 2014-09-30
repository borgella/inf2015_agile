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
public class Validateur {

    private DeclarationDeFormation membre;
    private boolean formationIncomplete;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public Validateur(DeclarationDeFormation membre) {
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
        Activite act;
        ArrayList<Activite> liste = membre.getActiviteErronee();
        int somme = 0;
        for (int i = 0; i < membre.getActiviteErronee().size(); ++i) {
            act = liste.get(i);
            somme += act.getHeures();
        }
        return somme;
    }

    public int nombreDHeuresCategorie1() {
        Activite act;
        ArrayList<Activite> liste = membre.getActivites();
        int somme = 0;
        for (int i = 0; i < membre.getActivites().size(); ++i) {
            act = liste.get(i);
            if (act.regroupementDesCategories(act.getCategorie()) == 1) {
                somme += act.getHeures();
            }
        }
        return somme;
    }

    public int nombreDHeuresCategorie2() {
        Activite act;
        ArrayList<Activite> liste = membre.getActivites();
        int somme = 0;
        for (int i = 0; i < membre.getActivites().size(); ++i) {
            act = liste.get(i);
            if (act.regroupementDesCategories(act.getCategorie()) == 2) {
                somme += act.getHeures();
            }
        }
        return somme;
    }

    public int nombreDHeuresCategorie3() {
        Activite act;
        ArrayList<Activite> liste = membre.getActivites();
        int somme = 0;
        for (int i = 0; i < membre.getActivites().size(); ++i) {
            act = liste.get(i);
            if (act.regroupementDesCategories(act.getCategorie()) == 3) {
                somme += act.getHeures();
            }
        }
        return somme;
    }

    public int heuresTotalesFormation() {
        int somme1 = nombreDHeuresCategorie1();
        int somme2 = nombreDHeuresCategorie2();
        int somme3 = nombreDHeuresCategorie3();
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
        Activite act;
        ArrayList<Activite> liste = membre.getActiviteErronee();
        String retour = " ";
        String message = " est dans une categorie  non reconnue. Elle sera ignoree. ";
        String message2 = " heure(s) de formation pour completer le cycle .";
        if ((!(formationComplete())) && liste != null) {
            for (int i = 0; i < membre.getActiviteErronee().size(); ++i) {
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

    public String produireRapport() {
        JSONObject texteDeSortie = new JSONObject();
        texteDeSortie.accumulate("complet", !formationIncomplete);
        texteDeSortie.accumulate("erreurs", messagesErreurs);
        return texteDeSortie.toString(2);
    }
}
