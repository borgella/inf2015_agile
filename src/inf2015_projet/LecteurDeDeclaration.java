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
    private boolean erreurDeFormatDetectee;

    public LecteurDeDeclaration(JSONObject declarationJSON) {
        declaration = declarationJSON;
    }

    public boolean erreurDeFormatDetectee() {
       
        return formatAcceptePourNumeroDePermis()
                && formatAcceptePourOrdre()
                && formatAcceptePourCycle();
    }

    public boolean formatAcceptePourNumeroDePermis() {
        try {
            String numeroDePermis = declaration.getString("numero_de_permis");
            return numeroDePermisReconnu(numeroDePermis);
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean numeroDePermisReconnu(String numeroDePermis) {
        // AJOUTER FONCTION PREMIER CARACTERE
        return (numeroDePermis.length() >= 5);
    }
    
    public boolean formatAcceptePourOrdre() {
        try {
            String ordre = declaration.getString("ordre");
            return ordreReconnu(ordre);
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean ordreReconnu(String ordre) {
        return ordre.equals("architectes")
               || ordre.equals("géologues")
               || ordre.equals("psychologues");
    }
    
    public boolean formatAcceptePourCycle() {
        try {
            String cycle = declaration.getString("cycle");
            return cycleReconnu(cycle);
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean cycleReconnu(String cycle) {
        // AJOUTER FONCTION PREMIER CARACTERE
        return (cycle.length() >= 9);
    }
    
    public boolean formatAcceptePourHeuresTransferees() {
        try {
           int heuresTransferees = declaration.getInt("heures_transferees_du_cycle_precedent");
           return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean champsCycleExiste() {
        return !extraireChampsTexte("cycle").equals("invalide");
    }

    public boolean champsOrdreExiste() {
        return !extraireChampsTexte("ordre").equals("invalide");
    }

    // A revoir, bogue potentiel avec les heures transferees = -1 sur la declaration
    public boolean champsHeuresTransfereesExisteSiArchitecte() {
        String ordre = extraireChampsTexte("ordre");
        return !ordre.equals("architecte") || (extraireChampsNumerique("heuresTransferees") != -1);
    }

    public boolean champsActivitesExiste() {
        JSONArray tableauJSON = extraireChampsTableau("activites");
        return !tableauJSON.isEmpty();
    }
    
    public boolean champsPropresAuxActivitesExistent() {
        boolean champsManquant = false;
        JSONArray tableauJSON = extraireChampsTableau("activites");
        for (int i = 0; i < tableauJSON.size(); i++) {
            if (true) {
                break;
            }
        }
        return !champsManquant;
    }
    
    public boolean champsPropresAuxActivitesExistent(int indice) {
        return champsDescriptionPourActiviteExiste(indice)
                && champsCategoriePourActiviteExiste(indice)
                && champsHeuresPourActiviteExiste(indice)
                && champsDatePourActiviteExiste(indice);
    }
    
    public boolean champsDescriptionPourActiviteExiste(int indice) {
        
        return !extraireChampsTexte("description").equals("invalide");
    }
    
    public boolean champsCategoriePourActiviteExiste(int indice) {
        return !extraireChampsTexte("categorie").equals("invalide");
    }
    
    public boolean champsHeuresPourActiviteExiste(int indice) {
        return !extraireChampsTexte("heures").equals("invalide");
    }
    
    public boolean champsDatePourActiviteExiste(int indice) {
        return !extraireChampsTexte("date").equals("invalide");
    }

    public String extraireChampsTexte(String nomChamps) {
        try {
            String champsTexte = declaration.getString(nomChamps);
            return champsTexte;
        } catch (Exception e) {
            return "Invalide";
        }
    }

    // A revoir, utilise un code de retour...
    public int extraireChampsNumerique(String nomChamps) {
        try {
            int champsNumerique = declaration.getInt(nomChamps);
            return champsNumerique;
        } catch (Exception e) {
            return 0;
        }
    }

    public JSONArray extraireChampsTableau(String nomTableau) {
        try {
            JSONArray champsTableau = declaration.getJSONArray(nomTableau);
            return champsTableau;
        } catch (Exception e) {
            return new JSONArray();
        }
    }
    
    public String extraireChampsTexteObjectJSON(String nomChamps, JSONObject objetJSON) {
        try {
            String champsTexte = objetJSON.getString(nomChamps);
            return champsTexte;
        } catch (Exception e) {
            return "Invalide";
        }
    }
    
     public int extraireChampsNumeriqueObjectJSON(String nomChamps, JSONObject objetJSON) {
        try {
            int champsNumerique = objetJSON.getInt(nomChamps);
            return champsNumerique;
        } catch (Exception e) {
            return -1;
        }
    }

    public JSONObject produireRapportFormatInvalide() {
        JSONObject texteDeSortie = new JSONObject();
        texteDeSortie.accumulate("complet", false);
        texteDeSortie.accumulate("erreurs", messageDErreurPourDeclarationInvalide());
        return texteDeSortie;
    }

    private static String messageDErreurPourDeclarationInvalide() {
        return "Le fichier d'entrée est invalide et donc le cycle de formation est incomplet.";
    }

}
