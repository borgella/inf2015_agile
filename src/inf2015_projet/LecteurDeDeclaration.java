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

    public boolean formatAcceptePourNumeroDePermis() {
        boolean formatAccepte;
        String champsNumeroDePermis = "numero_de_permis";
        if (champsTexteExiste(champsNumeroDePermis)) {
            String numeroDePermis = declaration.getString(champsNumeroDePermis);
            formatAccepte = numerosDePermisValides(numeroDePermis);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    public boolean champsTexteExiste(String nomChamps) {
        try {
            declaration.getString(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean numerosDePermisValides(String numeroDePermis) {
        boolean validiteNumeroDePermis = false;
        String numeroPermisArchitectes = "^[A|T][0-9]{4}$";
        String numeroPermisPsychologues = "^[0-9]{5}[-][0-9]{2}$";
        String numeroPermisGeologues = "^[A-Z]{2}[0-9]{4}$";
        String numeroPermisPodiatres = "^[0-9]{5}$";
        
        if(declaration.getString("ordre").equals("architectes")) {
            if(numeroDePermis.matches(numeroPermisArchitectes)) {
                validiteNumeroDePermis = true;
            }
        } else if (declaration.getString("ordre").equals("psychologues")) {
            if(numeroDePermis.matches(numeroPermisPsychologues)) {
                validiteNumeroDePermis = true;
            }
        } else if (declaration.getString("ordre").equals("geologues")) {
            String premiereLettreNom = declaration.getString("nom").substring(0, 1);
            String premiereLettrePrenom = declaration.getString("prenom").substring(0, 1);
            if( numeroDePermis.matches(numeroPermisGeologues) && 
                numeroDePermis.substring(0, 1).equals(premiereLettreNom) && 
                numeroDePermis.substring(1, 2).equals(premiereLettrePrenom) ) {
                validiteNumeroDePermis = true;
            }
        } else {
            if(numeroDePermis.matches(numeroPermisPodiatres)) {
                validiteNumeroDePermis = true;
            }
        }
        
        return validiteNumeroDePermis;
    }
    
    /*public boolean numerosDePermisValides(String numeroDePermis) {
        boolean validiteNumeroDePermis = false;
        String numeroPermisArchitectes = "^[A|T][0-9]{4}$";
        String numeroPermisPsychologues = "^[0-9]{5}[-][0-9]{2}$";
        String numeroPermisGeologues = "^[A-Z]{2}[0-9]{4}$";
        String numeroPermisPodiatres = "^[0-9]{5}$";
        
        if(declaration.getString("ordre").equals("architectes")) {
            validiteNumeroDePermis = validiteNumeroDePermis(numeroDePermis, numeroPermisArchitectes);
        } else if (declaration.getString("ordre").equals("psychologues")) {
            validiteNumeroDePermis = validiteNumeroDePermis(numeroDePermis, numeroPermisPsychologues);
        } else if (declaration.getString("ordre").equals("geologues")) {
            validiteNumeroDePermis = validiteNumeroDePermisGeologues(numeroDePermis, numeroPermisGeologues);
        } else {
            validiteNumeroDePermis = validiteNumeroDePermis(numeroDePermis, numeroPermisPodiatres);
        }
        
        return validiteNumeroDePermis;
    }
    
    public boolean validiteNumeroDePermis(String numeroDePermisLu, String formatNumeroDePermis) {
        boolean numeroDePermisValide = false;
        
        if(numeroDePermisLu.matches(formatNumeroDePermis)) {
            numeroDePermisValide = true;
        }
        
        return numeroDePermisValide;
    }
    
    public boolean validiteNumeroDePermisGeologues(String numeroDePermisLu, String formatNumeroDePermis) {
        boolean numeroDePermisValide = false;
        
        String premiereLettreNom = declaration.getString("nom").substring(0, 1);
        String premiereLettrePrenom = declaration.getString("prenom").substring(0, 1);
        
        if( numeroDePermisLu.matches(formatNumeroDePermis) && 
            numeroDePermisLu.substring(0, 1).equals(premiereLettreNom) && 
            numeroDePermisLu.substring(1, 2).equals(premiereLettrePrenom) ) {
            numeroDePermisValide = true;
        }
        
        return numeroDePermisValide;
    }*/

    public static boolean texteEstNumerique(String texte) {
        try {
            Integer.parseInt(texte);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean formatAcceptePourOrdre() {
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

    public static boolean ordreReconnu(String ordre) {
        return ordre.equals("architectes")
                || ordre.equals("géologues")
                || ordre.equals("psychologues")
                || ordre.equals("podiatres");
    }

    public boolean formatAcceptePourCycle() {
        String champsCycle = "cycle";
        return champsTexteExiste(champsCycle);
    }

    public boolean formatAcceptePourHeuresTransfereesSelonOrdre() {
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

    public boolean champsNumeriqueExiste(String nomChamps) {
        try {
            declaration.getInt(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean formatAcceptePourTableauActivites() {
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

    public boolean champsTableauJSONExiste(String nomChamps) {
        try {
            declaration.getJSONArray(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean formatAcceptePourChaqueActivite(JSONArray activites) {
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

    public static boolean formatAcceptePourActivite(JSONObject activite) {
        return formatAcceptePourDescription(activite)
                && formatAcceptePourCategorie(activite)
                && formatAcceptePourHeures(activite)
                && formatAcceptePourDate(activite);
    }

    public static boolean formatAcceptePourDescription(JSONObject activite) {
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

    public static boolean champsTexteExistePourActivite(String nomChamps, JSONObject activite) {
        try {
            activite.getString(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean descriptionReconnu(String description) {
        return description.length() > 20;
    }

    public static boolean formatAcceptePourCategorie(JSONObject activite) {
        String champsCategorie = "categorie";
        return champsTexteExistePourActivite(champsCategorie, activite);
    }

    public static boolean formatAcceptePourHeures(JSONObject activite) {
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

    public static boolean champsNumeriqueExistePourActivite(String nomChamps, JSONObject activite) {
        try {
            activite.getInt(nomChamps);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean heuresValidesPourActivite(int heures) {
        return heures > 0;
    }

    public static boolean formatAcceptePourDate(JSONObject activite) {
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
    public static boolean dateEnFormatReconnu(String date) {
        return dateALongueurValide(date) && dateAContenuValide(date);
    }

    public static boolean dateALongueurValide(String date) {
        return date.length() == 10;
    }

    public static boolean dateAContenuValide(String date) {
        return dateASeperateursValides(date) && dateAComposantesNumeriquesValides(date);
    }

    public static boolean dateASeperateursValides(String date) {
        char premierTiret = date.charAt(4);
        char deuxiemeTiret = date.charAt(7);
        return premierTiret == '-' && deuxiemeTiret == '-';
    }

    public static boolean dateAComposantesNumeriquesValides(String date) {
        String anneeEnTexte = date.substring(0, 4);
        String moisEnTexte = date.substring(5, 7);
        String jourEnTexte = date.substring(8, 10);
        return dateAUneAnneeValide(anneeEnTexte)
                && dateAUnMoisValide(moisEnTexte)
                && dateAUnJourValide(jourEnTexte);
    }

    public static boolean dateAUneAnneeValide(String anneeEnTexte) {
        boolean anneeValide;
        if (texteEstNumerique(anneeEnTexte)) {
            int annee = Integer.parseInt(anneeEnTexte);
            anneeValide = (annee >= 1900);   // Une limite inférieure fonctionnelle
        } else {
            anneeValide = false;
        }
        return anneeValide;
    }

    public static boolean dateAUnMoisValide(String moisEnTexte) {
        boolean moisValide;
        if (texteEstNumerique(moisEnTexte)) {
            int mois = Integer.parseInt(moisEnTexte);
            moisValide = (1 <= mois && mois <= 12);
        } else {
            moisValide = false;
        }
        return moisValide;
    }

    public static boolean dateAUnJourValide(String jourEnTexte) {
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

    public static String messageDErreurPourDeclarationInvalide() {
        return "Le fichier d'entrée est invalide et donc le cycle de formation est incomplet.";
    }

}
