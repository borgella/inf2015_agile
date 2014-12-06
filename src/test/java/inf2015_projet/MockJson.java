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
 * @author QQ1403
 */
public class MockJson {
    String numero_de_permis;
    String ordre ;
    String prenom ;
    String nom ;  
    int sexe = 1;
    String cycle ;
    int heures_transferees_du_cycle_precedent ;
    JSONArray activites;
    
    public MockJson(){
        numero_de_permis = "A1234";
        ordre = "architectes";
        prenom = "Jean Mary";
        nom = "Borgella";  
        sexe = 1;
        cycle = "2012-2014";
        heures_transferees_du_cycle_precedent = 2;
        activites = activiteArchitecte();
    }
    
   /**
     *
     * @return 
    */
    public JSONObject retournerUnJSONObject(){
        JSONObject architecte = new JSONObject();
        architecte.accumulate("numero_de_permis",numero_de_permis);
        architecte.accumulate("ordre", ordre);
        architecte.accumulate("prenom",prenom);
        architecte.accumulate("nom",nom);
        architecte.accumulate("sexe",sexe);
        architecte.accumulate("cycle","2012-2014");
        architecte.accumulate("heures_transferees_du_cycle_precedent",2);
        architecte.accumulate("activites",activites);
        System.out.println(architecte.get("cycle"));
        return architecte;
    }

    /**
     *
     * @return
     */
    public JSONArray activiteArchitecte(){
        JSONObject activite = new JSONObject();
        JSONArray activites_json = new JSONArray();
        
        activite.accumulate("description", "Cours sur la déontologie");
        activite.accumulate("categorie", "cours");
        activite.accumulate("heures", 14);
        activite.accumulate("date", "2013-03-20");
        activites_json.add(activite);
        
        activite =  new JSONObject();
        activite.accumulate("description", "Cours sur la déontologie");
        activite.accumulate("categorie", "cours");
        activite.accumulate("heures", 14);
        activite.accumulate("date", "2013-03-20");
        activites_json.add(activite);
        
        activite =  new JSONObject();
        activite.accumulate("description", "Rédaction pour le magazine Architecture moderne");
        activite.accumulate("categorie", "rédaction professionnelle");
        activite.accumulate("heures", 6);
        activite.accumulate("date","2012-10-22");
        activites_json.add(activite);
        
        activite =  new JSONObject();
        activite.accumulate("description", "projet");
        activite.accumulate("categorie", "projet de recherche");
        activite.accumulate("heures", 6);
        activite.accumulate("date","2012-10-22");
        activites_json.add(activite);
        
        activite =  new JSONObject();
        activite.accumulate("description","Participation à un groupe de discussion");
        activite.accumulate("categorie","voyage");
        activite.accumulate("heures",6);
        activite.accumulate("date","2013-04-01");
        activites_json.add(activite);
        
        return activites_json;
    }
    
    public void setNumero_de_permis(String numero_de_permis){
        this.numero_de_permis = numero_de_permis;
    }
   
    public JSONArray getActivites(){
        return activites;
    }
    
    public String getCycle(){
        return this.cycle;
    }
    
    public void setActivites(JSONArray activites){
        this.activites = activites;
    }
    
    public void setOrdre(String ordre){
        this.ordre = ordre;
    }
    
    public void setCycle(String cycle){
        this.cycle = cycle;
    }
}
