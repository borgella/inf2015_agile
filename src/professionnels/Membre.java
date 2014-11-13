/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professionnels;

import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public abstract class Membre {
    private String nom;
    private String prenom;
    private int sexe;
    private String numeroDePermis;
    private String ordre;
    
    public Membre(JSONObject activiteJson) {
        this.nom = activiteJson.getString("nom");
        this.prenom = activiteJson.getString("prenom");
        this.sexe = activiteJson.getInt("sexe");
        this.numeroDePermis = activiteJson.getString("numero_de_permis");
        this.ordre = activiteJson.getString("ordre");
    }
    
    public abstract void ajouterActivitePourMembre(JSONObject activite);
    
    public abstract String getCycle();
    
    public int getSexe(){
        return sexe;
    }

   public int regroupementDesCategories(String categorie) {
        int temporaire = -1;
        if (premiereCategorie(categorie) == 1) {
            temporaire = 1;
        } else if (deuxiemeCategorie(categorie) == 2) {
            temporaire = 2;
        } else if (troisiemeCategorie(categorie) == 3) {
            temporaire = 3;
        }
        return temporaire;
    }
   public int premiereCategorie(String categorie) {
        int temporaire = 0;
        switch (categorie) {
            case "cours": case "atelier":
            case "séminaire": case "colloque":
            case "conférence": case "lecture dirigée":
                temporaire = 1;
                break;
        }
        return temporaire;
    }

    public int deuxiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("présentation") || categorie.equals("projet de recherche")) {
            temporaire = 2;
        }
        return temporaire;
    }

    
    public int troisiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("groupe de discussion") || categorie.equals("rédaction professionnelle")) {
            temporaire = 3;
        }
        return temporaire;
    }
}
