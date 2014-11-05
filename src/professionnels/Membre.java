/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professionnels;

import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Membre {
    protected String nom;
    protected String prenom;
    protected int sexe;
    protected String numeroDePermis;
    protected String ordre;
    protected String cycle;
    protected ArrayList<JSONObject> activitesAcceptees;
    protected ArrayList<JSONObject> activitesRefusees;
    
    public Membre(JSONObject activiteJson) {
        this.nom = activiteJson.getString("nom");
        this.prenom = activiteJson.getString("prenom");
        this.sexe = activiteJson.getInt("sexe");
        this.numeroDePermis = activiteJson.getString("numero_de_permis");
        this.ordre = activiteJson.getString("ordre");
        this.cycle = activiteJson.getString("cycle");
        activitesAcceptees = new ArrayList<>(1);
        activitesRefusees = new ArrayList<>(1);
    }
   
    public String getCycle() {
        return this.cycle;
    }
    
    public ArrayList getActivitesRefusees() {
        return this.activitesRefusees;
    }

    public ArrayList getActivitesAcceptees() {
        return this.activitesAcceptees;
    }
}
