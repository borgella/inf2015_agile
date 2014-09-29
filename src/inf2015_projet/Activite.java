/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Activite extends DeclarationDeFormation {  
    private String description;
    private String categorie;
    private int heures;
    private String date;
      
    public Activite(DeclarationDeFormation membre,String[] listeActivite){
        super(membre.getNumeroDepermis(),membre.getCycle(),membre.getHeuresTransferees());
        this.description = listeActivite[0];
        this.categorie = listeActivite[1];
        this.heures = stringToInt(listeActivite[2]);
        this.date = listeActivite[3];
    }
    
    /**
     *
     * @return
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     *
     * @return
     */
    public String getCategorie() {
        return this.categorie;
    }
    
    /**
     *
     * @return
     */
    public int getHeures() {
        return this.heures;
    }
    
    /**
     *
     * @return
     */
    public String getDate () {
        return this.date;
    }
    
    /**
    *   Cette methode retoune dans quelle categorie se trouve l'activité
    *   @param categorie
    *   @return int 1 si la categorie le nombre d'heures a declarer pour cette
    *   activite est un minimum de 17 heures
    *   sinon elle va retourner 2 si le nombres d'heures ne doit pas depasser 23
    *   heures,elle retournera 3 si le minimum 17 heures et -1 si cette categorie
    *   n'est pas valide
    */
    public static int regroupementDesCategories(String categorie){
        int temporaire;
        switch(categorie){
            case "cours":      
            case "atelier":
            case "séminaire":  
            case "colloque":
            case "conférence": 
            case "lecture dirigée":
                temporaire = 1;
                break;  
            case "présentation": 
            case "projet de recherche":
                temporaire = 2;
                break;
            case "groupe de discussion": 
            case "rédaction professionelle":
                temporaire = 3;
                break;
            default: temporaire = -1;
                break;
        }
        return temporaire;
    }
    
    /**
     * Cette methode valide la date de l'activite
     * @param date
     * @return 
     */          
     public boolean validerLaDate(String date){
        int temporaire ; 
        if((stringToInt(date.substring(5,7))>= 1 && stringToInt(date.substring(5,7))<=12) && (stringToInt(date.substring(8,10))>=1 && stringToInt(date.substring(8,10))<= 31)){
            date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);
        }else{
            return false;
        }
        temporaire = stringToInt(date);
        return temporaire >= 20120430 && temporaire <= 20140430;
    }
    
    /**
     * Cette methode convertit un String en format date
     * @param number
     * @return int temporaire
     */
    private int stringToInt(String number){
        Integer temporaire = new Integer(number);
        return temporaire;
    }
          
}
