/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professionnels;

import net.sf.json.JSONObject;

/**
 *
 * @author User
 */
public class Membre {
    private String nom;
    private String prenom;
    private int sexe;
    
    public Membre(JSONObject activiteJson) {
        this.nom = activiteJson.getString("nom");
        this.prenom = activiteJson.getString("prenom");
        this.sexe = activiteJson.getInt("sexe");
    }
   
}
