/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class LecteurDeDeclaration {

    private final JSONObject declaration;

    public LecteurDeDeclaration(JSONObject declarationJSON) {
        declaration = declarationJSON;
    }

    public boolean erreurDeFormatDetectee() {
        return !formatAcceptePourNumeroDePermis()
                || !formatAcceptePourOrdre()
                || !formatAcceptePourCycle()
                || !formatAcceptePourHeuresTransfereesSelonOrdre()
                || !formatAcceptePourTableauActivites();
    }

    private boolean formatAcceptePourNumeroDePermis() {
        boolean formatAccepte;
        String champsNumeroDePermis = "numero_de_permis";
        if (champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declaration.getString(champsNumeroDePermis);
            return numeroDePermisReconnu(numeroDePermis);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    private boolean champsTexteExiste(String nomChamps) {
        try {
            declaration.getString(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO: Verifier le format de chacune des 5 caractères
    private boolean numeroDePermisReconnu(String numeroDePermis) {
        return (numeroDePermis.length() == 5);
    }

    private boolean formatAcceptePourOrdre() {
        boolean formatAccepte;
        String champsOrdre = "ordre";
        if (champsTexteExiste(champsOrdre)) {
            String ordre = declaration.getString(champsOrdre);
            formatAccepte = ordreReconnu(ordre);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    private boolean ordreReconnu(String ordre) {
        return ordre.equals("architectes")
                || ordre.equals("géologues")
                || ordre.equals("psychologues");
    }

    private boolean formatAcceptePourCycle() {
        String champsCycle = "cycle";
        return champsTexteExiste(champsCycle);
    }

    private boolean formatAcceptePourHeuresTransfereesSelonOrdre() {
        boolean formatAccepte;
        String champsHeuresTransferees = "heures_transferees_du_cycle_precedent";
        String ordre = declaration.getString("ordre");
        if (ordre.equals("architectes")) {
            formatAccepte = champsNumeriqueExiste(champsHeuresTransferees);
        } else {
            formatAccepte = true;
        }
        return formatAccepte;
    }

    private boolean champsNumeriqueExiste(String nomChamps) {
        try {
            declaration.getInt(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean formatAcceptePourTableauActivites() {
        boolean formatAccepte;
        String champsActivites = "activites";
        if (champsTableauJSONExiste(champsActivites)) {
            JSONArray activites = declaration.getJSONArray(champsActivites);
            formatAccepte = formatAcceptePourChaqueActivite(activites);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    private boolean champsTableauJSONExiste(String nomChamps) {
        try {
            declaration.getJSONArray(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean formatAcceptePourChaqueActivite(JSONArray activites) {
        boolean formatAccepte = true;  
        for (int i = 0; i < activites.size(); i++) {
            JSONObject activiteCourante = activites.getJSONObject(i);
            if (!formatAcceptePourActivite(activiteCourante)) {
                formatAccepte = false;
                break;
            }
        }
        return formatAccepte;
    }

    private boolean formatAcceptePourActivite(JSONObject activite) {
        return formatAcceptePourDescription(activite)
                && formatAcceptePourCategorie(activite)
                && formatAcceptePourHeures(activite)
                && formatAcceptePourDate(activite);
    }

    private boolean formatAcceptePourDescription(JSONObject activite) {
        boolean formatAccepte;
        String champsDescription = "description";
        if (champsTexteExistePourActivite(champsDescription, activite)) {
            String description = activite.getString(champsDescription);
            formatAccepte = descriptionReconnu(description);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    private boolean champsTexteExistePourActivite(String nomChamps, JSONObject activite) {
        try {
            activite.getString(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean descriptionReconnu(String description) {
        return description.length() > 20;
    }

    private boolean formatAcceptePourCategorie(JSONObject activite) {
        String champsCategorie = "categorie";
        return champsTexteExistePourActivite(champsCategorie, activite);
    }

    private boolean formatAcceptePourHeures(JSONObject activite) {
        boolean formatAccepte;
        String champsHeures = "heures";
        if (champsNumeriqueExistePourActivite(champsHeures, activite)) {
            int heures = activite.getInt(champsHeures);
            formatAccepte = heuresValidesPourActivite(heures);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    private boolean champsNumeriqueExistePourActivite(String nomChamps, JSONObject activite) {
        try {
            activite.getInt(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean heuresValidesPourActivite(int heures) {
        return heures > 0;
    }

    private boolean formatAcceptePourDate(JSONObject activite) {
        boolean formatAccepte;
        String champsDate = "date";
        if (champsTexteExistePourActivite(champsDate, activite)) {
            String date = activite.getString(champsDate);
            formatAccepte = dateEnFormatISO8601(date);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    // TODO: Fonctionnalité!
    private boolean dateEnFormatISO8601(String date) {
        return true;
    }

    public JSONObject produireRapportPourErreurDeFormat() {
        JSONObject texteDeSortie = new JSONObject();
        texteDeSortie.accumulate("complet", false);
        texteDeSortie.accumulate("erreurs", messageDErreurPourDeclarationInvalide());
        return texteDeSortie;
    }

    private static String messageDErreurPourDeclarationInvalide() {
        return "Le fichier d'entrée est invalide et donc le cycle de formation est incomplet.";
    }

}
