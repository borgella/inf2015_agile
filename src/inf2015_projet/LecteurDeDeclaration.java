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
        erreurDeFormatDetectee = false;
    }
    
    public boolean erreurDeFormatDetectee() {
        return erreurDeFormatDetectee;
    }
    
    public String extraireChampsTexte(String nomChamps) {
        try {
           String champsTexte = declaration.getString(nomChamps);
           System.out.println("champsTexte String");
           return champsTexte;
        } catch (Exception e) {
            erreurDeFormatDetectee = true;
            return "Invalide";
        }
    }
    
    public int extraireChampsNumerique(String nomChamps) {
        try {
           int champsNumerique = declaration.getInt(nomChamps);
           System.out.println("champsTexte int");
           return champsNumerique;
        } catch (Exception e) {
            erreurDeFormatDetectee = true;
            return 0;
        }
    }
    
    public JSONArray extraireChampsTableau(String nomTableau) {
        try {
           JSONArray champsTableau = declaration.getJSONArray(nomTableau);
           System.out.println("champsTexte Tableau");
           return champsTableau;
        } catch (Exception e) {
            erreurDeFormatDetectee = true;
            return new JSONArray();
        }
    }
    
    public JSONObject produireRapportEnCasDErreur() {
        JSONObject texteDeSortie = new JSONObject();
        texteDeSortie.accumulate("complet", false);
        texteDeSortie.accumulate("erreurs", messageDErreurPourDeclarationInvalide());
        return texteDeSortie;
    }
    
    private static String messageDErreurPourDeclarationInvalide() {
        return "Le fichier d'entrée est invalide et donc le cycle de formation est incomplet.";
    }
    
}
