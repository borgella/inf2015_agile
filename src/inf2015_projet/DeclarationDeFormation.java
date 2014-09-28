/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import java.util.ArrayList;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class DeclarationDeFormation {
    private final String numeroDepermis;
    private final String cycle;
    private static int heuresTransferees;
    private ArrayList <Activite> activites;
    private ArrayList <Activite> activiteErronee;
    public DeclarationDeFormation(String numeroDepermis,String cycle, int heuresTransferees) {
        this.numeroDepermis = numeroDepermis;
        this.cycle = cycle;
        this.heuresTransferees = heuresTransferees;           
        activites = new ArrayList(1);  
        activiteErronee = new ArrayList(1);
    }

    public void ajouterActivite(Activite activite) {
        int temporaire = activite.regroupementDesCategories(activite.getCategorie());
        if(activite.validerLaDate(activite.getDate())&& temporaire != -1){
            activites.add(activite);
        }else{
            activiteErronee.add(activite);
        }

    }

    public String getNumeroDepermis(){
        return numeroDepermis;
    }

    public String getCycle() {
        return cycle;
    }

    public int getHeuresTransferees() {
        return heuresTransferees;
    }

    public ArrayList getActivites(){
        return activites;
    }
    
    public ArrayList getActiviteErronee(){
        return activiteErronee;
    }
    
}
