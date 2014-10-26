
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

    private static boolean numeroDePermisReconnu(String numeroDePermis) {
        return numeroDePermisALongueurValide(numeroDePermis)
                && numeroDePermisAContenuValide(numeroDePermis);
    }
    
    private static boolean numeroDePermisALongueurValide(String numeroDePermis) {
        return numeroDePermis.length() == 5;
    }
    
    private static boolean numeroDePermisAContenuValide(String numeroDePermis) {
        return numeroDePermisAPremierCaractereValide(numeroDePermis)
                && numeroDePermisTermineParQuatreChiffres(numeroDePermis);
    }
    
    private static boolean numeroDePermisAPremierCaractereValide(String numeroDePermis) {
        char premierCaractere = numeroDePermis.charAt(0);
        return premierCaractere == 'A' || premierCaractere == 'R' 
                || premierCaractere == 'S' || premierCaractere == 'Z';
    }

    private static boolean numeroDePermisTermineParQuatreChiffres(String numeroDePermis) {
        String finDeNumeroDePermis = numeroDePermis.substring(1,5);
        return texteEstNumerique(finDeNumeroDePermis);
    }

    private static boolean texteEstNumerique(String texte) {
        try {
            Integer.parseInt(texte);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
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

    private static boolean ordreReconnu(String ordre) {
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

    private static boolean formatAcceptePourChaqueActivite(JSONArray activites) {
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

    private static boolean formatAcceptePourActivite(JSONObject activite) {
        return formatAcceptePourDescription(activite)
                && formatAcceptePourCategorie(activite)
                && formatAcceptePourHeures(activite)
                && formatAcceptePourDate(activite);
    }

    private static boolean formatAcceptePourDescription(JSONObject activite) {
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

    private static boolean champsTexteExistePourActivite(String nomChamps, JSONObject activite) {
        try {
            activite.getString(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean descriptionReconnu(String description) {
        return description.length() > 20;
    }

    private static boolean formatAcceptePourCategorie(JSONObject activite) {
        String champsCategorie = "categorie";
        return champsTexteExistePourActivite(champsCategorie, activite);
    }

    private static boolean formatAcceptePourHeures(JSONObject activite) {
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

    private static boolean champsNumeriqueExistePourActivite(String nomChamps, JSONObject activite) {
        try {
            activite.getInt(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean heuresValidesPourActivite(int heures) {
        return heures > 0;
    }

    private static boolean formatAcceptePourDate(JSONObject activite) {
        boolean formatAccepte;
        String champsDate = "date";
        if (champsTexteExistePourActivite(champsDate, activite)) {
            String date = activite.getString(champsDate);
            formatAccepte = dateEnFormatReconnu(date);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    // Le format de date reconnu est ISO-8601: AAAA-MM-JJ
    private static boolean dateEnFormatReconnu(String date) {
        return dateALongueurValide(date) && dateAContenuValide(date);
    }
    
    private static boolean dateALongueurValide(String date) {
        return date.length() == 10;
    }
    
    private static boolean dateAContenuValide(String date) {
        return dateASeperateursValides(date) && dateAComposantesNumeriquesValides(date);
    }
    
    private static boolean dateASeperateursValides(String date) {
        char premierTiret = date.charAt(4);
        char deuxiemeTiret = date.charAt(7);
        return premierTiret == '-' && deuxiemeTiret == '-';
    } 
    
    private static boolean dateAComposantesNumeriquesValides(String date) {
        String anneeEnTexte = date.substring(0, 4);
        String moisEnTexte = date.substring(5, 7);
        String jourEnTexte = date.substring(8,10);
        return dateAUneAnneeValide(anneeEnTexte) 
                && dateAUnMoisValide(moisEnTexte) 
                && dateAUnJourValide(jourEnTexte);
    } 
    
    private static boolean dateAUneAnneeValide(String anneeEnTexte) {
        boolean anneeValide;
        if (texteEstNumerique(anneeEnTexte)) {
            int annee = Integer.parseInt(anneeEnTexte);
            anneeValide = (annee >= 1900);   // Une limite inférieure fonctionnelle
        } else {
            anneeValide = false;
        }
        return anneeValide;
    } 
    
    private static boolean dateAUnMoisValide(String moisEnTexte) {
        boolean moisValide;
        if (texteEstNumerique(moisEnTexte)) {
            int mois = Integer.parseInt(moisEnTexte);
            moisValide = (1 <= mois && mois <= 12);
        } else {
            moisValide = false;
        }
        return moisValide;
    } 
    
    private static boolean dateAUnJourValide(String jourEnTexte) {
        boolean jourValide;
        if (texteEstNumerique(jourEnTexte)) {
            int jour = Integer.parseInt(jourEnTexte);
            jourValide = (1 <= jour && jour <= 31);
        } else {
            jourValide = false;
        }
        return jourValide;
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
