package professionnels;

import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author Jean Mary
 */
public class Geologue{
    private final String numeroDePermis;
    private final String ordre;
    private final String cycle; 
    private ArrayList <JSONObject> activitesAcceptees;
    private ArrayList<JSONObject> activitesRefusees;
    

    public Geologue(JSONObject activiteJson) {
        this.numeroDePermis = activiteJson.getString("numero_de_permis");
        this.cycle = activiteJson.getString("cycle");
        this.ordre = activiteJson.getString("ordre");
        activitesAcceptees = new ArrayList<>(1);
        activitesRefusees = new ArrayList<>(1); 
    }

    
    
    public void ajouterActivitePourGeologue(JSONObject activite) {
        int temporaire = regroupementDesCategories(activite.getString("categorie"));
        int heures = activite.getInt("heures");
        if (dateValidePourGeologues(activite.getString("date")) && temporaire != -1 && heures > 0) {
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
        }
        return temporaire;
    }

    public int premiereCategorie(String categorie){
        int temporaire = 0;
        switch (categorie) {
            case "atelier": case "séminaire":
            case "colloque": case "conférence":
            case "lecture dirigée": case "présentation": case "rédaction professionnelle":
                temporaire = 1;
            break;    
        }
        return temporaire;
    }
    
    public int deuxiemeCategorie(String categorie){
        int temporaire = 0;
            if(categorie.equals("cours") || categorie.equals("projet de recherche") 
                    || categorie.equals("groupe de discussion") ){
                temporaire = 2;
            }
        return temporaire;
    }
    
    public boolean dateValidePourGeologues(String date){
        int temporaire ; 
        if((toInt(date.substring(5,7))>=1 && toInt(date.substring(5,7))<=12)&&(toInt(date.substring(8,10))>=1 && toInt(date.substring(8,10))<= 31)){
            date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);
        }else{
            return false;
        }
        temporaire = toInt(date);
        return temporaire >= 20130601 && temporaire <= 20160601;
    }
    
  
    private int toInt(String number){
        Integer temporaire = new Integer(number);
        return temporaire;
    }
    
    
    public String getCycle(){
        return this.cycle;
    }
    
    public ArrayList getActivitesRefusees(){
        return this.activitesRefusees;
    }
    public ArrayList getActivitesAcceptees(){
        return this.activitesAcceptees;
    }
}
