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
public class Activite {
    private final String description;
    private final String categorie;
    private final int dureeEnHeures;
    private final String dateCompletee;
    
    public Activite(String description, String categorie, int dureeEnHeures, String dateCompletee) {
        this.description = description;
        this.categorie = categorie;
        this.dureeEnHeures = dureeEnHeures;
        this.dateCompletee = dateCompletee;
    }
    
    public String getDescription () {
        return description;
    }
    
    public String getCategorie () {
        return categorie;
    }
    
    public int getDureeEnHeures () {
        return dureeEnHeures;
    }
    
    public String getDateCompletee () {
        return dateCompletee;
    }
    
}
