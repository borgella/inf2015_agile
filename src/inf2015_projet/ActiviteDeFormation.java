/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ActiviteDeFormation extends DeclarationDeFormation {

    private String description;
    private String categorie;
    private int dureeEnHeures;
    private String dateCompletee;

    public ActiviteDeFormation(DeclarationDeFormation membre, JSONObject activiteJSON) {
        super(membre.getNumeroDePermis(), membre.getCycle(), membre.getHeuresTransferees());
        this.description = activiteJSON.getString("description");
        this.categorie = activiteJSON.getString("categorie");
        this.dureeEnHeures = activiteJSON.getInt("heures");
        this.dateCompletee = activiteJSON.getString("date");
    }

    public String getDescription() {
        return this.description;
    }

    public String getCategorie() {
        return this.categorie;
    }
    
    public boolean estDansCategorie(String categorie) {
        return categorie.equals(this.categorie);
    }

    public int getDureeEnHeures() {
        return this.dureeEnHeures;
    }

    public String getDateCompletee() {
        return this.dateCompletee;
    }

    /**
     * Cette methode retoune dans quelle categorie se trouve l'activité
     *
     * @param categorie
     * @return int 1 si la categorie le nombre d'heures a declarer pour cette
     * activite est un minimum de 17 heures sinon elle va retourner 2 si le
     * nombres d'heures ne doit pas depasser 23 heures,elle retournera 3 si le
     * minimum 17 heures et -1 si cette categorie n'est pas valide
     */
    public int regroupementDesCategories(String categorie) {
        int temporaire;
        switch (categorie) {
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
            case "rédaction professionnelle":
                temporaire = 3;
                break;
            default:
                temporaire = -1;
                break;
        }
        return temporaire;
    }

    /**
     * Activité complétée entre 1er avril 2012 et le 1er avril 2014
     * inclusivement À l'extérieur des intervalles => MESSAGE D'ERREUR +
     * activité ignorée des calculs Les dates sont indiquées en format ISO-8601
     *
     * @param date
     * @return
     */       
     public boolean aDateCompleteeValide(String date){
        int temporaire ; 
        if((toInt(date.substring(5,7))>=1 && toInt(date.substring(5,7))<=12)&&(toInt(date.substring(8,10))>=1 && toInt(date.substring(8,10))<= 31)){
            date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);
        }else{
            return false;
        }
        temporaire = toInt(date);
        return temporaire >= 20120401 && temporaire <= 20140401;
    }
    
    /**
     * Cette methode convertit un String en format date
     * @param number
     * @return int temporaire
     */
    private int toInt(String number){
        Integer temporaire = new Integer(number);
        return temporaire;
    }

}
