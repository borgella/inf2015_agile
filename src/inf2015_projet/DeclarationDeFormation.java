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
    private final String numeroDePermis;
    private final String cycle;
    private final int heuresTransferees;
    private ArrayList <Activite> activites;
    
    public DeclarationDeFormation(String numeroDePermis, String cycle, int heuresTransferees) {
        this.numeroDePermis = numeroDePermis;
        this.cycle = cycle.equals("2012-2014") ? cycle : " ";
        this.heuresTransferees = (heuresTransferees <= 7 && heuresTransferees >= 0) ? heuresTransferees : 7;
    }
    
    public void ajouterActivite(Activite nouvelleActivite) {
        activites.add(nouvelleActivite);
    }
    
    public String getNumeroDepermis(){
        return numeroDePermis;
    }
    
    public String getCycle() {
        return cycle;
    }
    
    public int getHeuresTransferees() {
        return heuresTransferees;
    }
}
