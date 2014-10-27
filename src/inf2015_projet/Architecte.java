
 package inf2015_projet;

import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */

public class Architecte{
    private String numeroDePermis;
    private String cycle;
    //private String ordre;
    private String categorie; // pour eviter un bogue car je ne comprends pas encore son utilite
    private int   heuresTransferees;
    private ArrayList <JSONObject> activitesAcceptees;
    private ArrayList<JSONObject> activitesRefusees;
    
    public Architecte(JSONObject activiteJson) {
        this.numeroDePermis = activiteJson.getString("numero_de_permis");
        this.cycle = activiteJson.getString("cycle");
        this.heuresTransferees = activiteJson.getInt("heures_transferees_du_cycle_precedent");
        activitesAcceptees = new ArrayList<>(1);
        activitesRefusees = new ArrayList<>(1);       
    }
    
    public void ajouterActivite(JSONObject activite) {
        if (cycle.equals("2008-2010")) {
            ajouterActivitePourArchitecte08_10(activite);
        } else if (cycle.equals("2010-2014")) {
            ajouterActivitePourArchitecte10_12(activite);
        } else {    // Cycle 2012-2014
            ajouterActivitePourArchitecte12_14(activite);
        }
    }
    
    public void ajouterActivitePourArchitecte12_14(JSONObject activite) {
        this.categorie = activite.getString("categorie"); // a enlever soon
        int temporaire = regroupementDesCategories(activite.getString("categorie"));
        int heures = activite.getInt("heures");
        if (dateValidePourCycle2012_2014(activite.getString("date")) && temporaire != -1 && heures > 0) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }

    public void ajouterActivitePourArchitecte10_12(JSONObject activite) {
        this.categorie = activite.getString("categorie"); // a enlever soon
        int temporaire = regroupementDesCategories(activite.getString("categorie"));
        int heures = activite.getInt("heures");
        if (dateValidePourCycle2010_2012(activite.getString("date")) && temporaire != -1 && heures > 0) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }

   public void ajouterActivitePourArchitecte08_10(JSONObject activite) {
       this.categorie = activite.getString("categorie"); // a enlever soon
        int temporaire = regroupementDesCategories(activite.getString("categorie"));
        int heures = activite.getInt("heures");
        if (dateValidePourCycle2008_2010(activite.getString("date")) && temporaire != -1 && heures > 0) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }
    public int regroupementDesCategories(String categorie) {
        int temporaire = -1;
        if(premiereCategorie(categorie) == 1){
            temporaire = 1;
        }else if(deuxiemeCategorie(categorie) == 2){
            temporaire = 2;
        }else if(troisiemeCategorie(categorie) == 3){
            temporaire = 3;
        }
        System.out.println("REGROUPEMENT DES CATEGORIES: " + temporaire);
        return temporaire;
    }

    public int premiereCategorie(String categorie){
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
    
    public int deuxiemeCategorie(String categorie){
        int temporaire = 0;
            if(categorie.equals("présentation") || categorie.equals("projet de recherche")){
                temporaire = 2;
            }
        return temporaire;
    }
     public int troisiemeCategorie(String categorie){
         int temporaire = 0;
            if(categorie.equals("groupe de discussion") || categorie.equals("rédaction professionnelle")){
                temporaire = 3;
            }
         return temporaire;
     }
    
    public boolean estDansCategorie(String categorie) {
        return categorie.equals(this.categorie);
    }
    
    public boolean dateValidePourCycle2012_2014(String date){
        int temporaire ; 
        if((toInt(date.substring(5,7))>=1 && toInt(date.substring(5,7))<=12)&&(toInt(date.substring(8,10))>=1 && toInt(date.substring(8,10))<= 31)){
            date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);
        }else{
            return false;
        }
        temporaire = toInt(date);
        return temporaire >= 20120401 && temporaire <= 20140401;
    }
    
     
     public boolean dateValidePourCycle2010_2012(String date){
        int temporaire ; 
        if((toInt(date.substring(5,7))>=1 && toInt(date.substring(5,7))<=12)&&(toInt(date.substring(8,10))>=1 && toInt(date.substring(8,10))<= 31)){
            date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);
        }else{
            return false;
        }
        temporaire = toInt(date);
        return temporaire >= 20100401 && temporaire <= 20120401;
    }
     
    public boolean dateValidePourCycle2008_2010(String date){
        int temporaire ; 
        if((toInt(date.substring(5,7))>=1 && toInt(date.substring(5,7))<=12)&&(toInt(date.substring(8,10))>=1 && toInt(date.substring(8,10))<= 31)){
            date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);
        }else{
            return false;
        }
        temporaire = toInt(date);
        return temporaire >= 20080401 && temporaire <= 20100701;
    } 
    

    private int toInt(String number){
        Integer temporaire = new Integer(number);
        return temporaire;
    }
    
    public String getCycle(){
        return this.cycle;
    }
    
    public int getHeuresTransferees(){
        return this.heuresTransferees;
    }
    
    public ArrayList getActivitesRefusees(){
        return this.activitesRefusees;
    }
    public ArrayList getActivitesAcceptees(){
        return this.activitesAcceptees;
    }
}

