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
            formatAccepte = numerosDePermisValides(numeroDePermis);
        } else {
            formatAccepte = false;
        }
        return formatAccepte;
    }

    private boolean champsTexteExiste(String nomChamps) {
        boolean champsTexteExiste;
        try {
            declaration.getString(nomChamps);
            champsTexteExiste = true;
        } catch (Exception e) {
            champsTexteExiste = false;
        }
        return champsTexteExiste;
    }

    public boolean numerosDePermisValides(String numeroDePermis) {
        boolean validiteNumeroDePermis;
        String numeroPermisArchitectes = "([A|T]{1}[0-9]{4})";
        String numeroPermisPsychologues = "([0-9]{5}[-][0-9]{2})";
        String numeroPermisGeologues = "([A-Z]{2}[0-9]{4})";
        String numeroPermisPodiatres = "([0-9]{5})";
        
        if(declaration.getString("ordre").equals("architectes")) {
            validiteNumeroDePermis = numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisArchitectes);
        } else if (declaration.getString("ordre").equals("psychologues")) {
            validiteNumeroDePermis = numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPsychologues);
        } else if (declaration.getString("ordre").equals("géologues")) {
            validiteNumeroDePermis = numeroDePermisValideGeologues(numeroDePermis, numeroPermisGeologues);
        } else {
            validiteNumeroDePermis = numerosDePermisValidesSelonLOrdre(numeroDePermis, numeroPermisPodiatres);
        }
        return validiteNumeroDePermis;
    }
    
    public boolean numerosDePermisValidesSelonLOrdre(String numeroDePermisLu, String formatNumeroPermisValide) {
        boolean validiteFormatNumeroDePermis = false;
        if(numeroDePermisLu.matches(formatNumeroPermisValide)) {
            validiteFormatNumeroDePermis = true;
        }
        return validiteFormatNumeroDePermis;
    }

    public boolean numeroDePermisValideGeologues(String numeroDePermisLu, String formatNumeroPermisValide) {
        boolean validiteFormatNumeroDePermis = false;
        String premiereLettreNom = declaration.getString("nom").substring(0, 1);
        String premiereLettrePrenom = declaration.getString("prenom").substring(0, 1);
        if(numeroDePermisLu.matches(formatNumeroPermisValide) && 
                numeroDePermisLu.substring(0, 1).equals(premiereLettreNom) && 
                numeroDePermisLu.substring(1, 2).equals(premiereLettrePrenom)) {
            validiteFormatNumeroDePermis = true;
        }
        return validiteFormatNumeroDePermis;
    }

    private static boolean texteEstNumerique(String texte) {
        boolean texteEstNumerique;
        try {
            Integer.parseInt(texte);
            texteEstNumerique = true;
        } catch (NumberFormatException e) {
            texteEstNumerique = false;
        }
        return texteEstNumerique;
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
                || ordre.equals("psychologues")
                || ordre.equals("podiatres");
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
        boolean champsNumeriqueExiste;
        try {
            declaration.getInt(nomChamps);
            champsNumeriqueExiste = true;
        } catch (Exception e) {
            champsNumeriqueExiste = false;
        }
        return champsNumeriqueExiste;
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
        boolean champsTableauJSONExiste;
        try {
            declaration.getJSONArray(nomChamps);
            champsTableauJSONExiste = true;
        } catch (Exception e) {
            champsTableauJSONExiste = false;
        }
        return champsTableauJSONExiste;
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
        boolean champsTexteExistePourActivite;
        try {
            activite.getString(nomChamps);
            champsTexteExistePourActivite = true;
        } catch (Exception e) {
            champsTexteExistePourActivite = false;
        }
        return champsTexteExistePourActivite;
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
        boolean champsNumeriqueExistePourActivite;
        try {
            activite.getInt(nomChamps);
            champsNumeriqueExistePourActivite = true;
        } catch (Exception e) {
            champsNumeriqueExistePourActivite = false;
        }
        return champsNumeriqueExistePourActivite;
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
        String jourEnTexte = date.substring(8, 10);
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
