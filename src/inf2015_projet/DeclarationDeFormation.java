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
    
    private final String cycle;
    private final int heuresTransferees;
    private ArrayList<Activite> activites;
    
    public DeclarationDeFormation(String cycle, int heuresTransferees) {
        this.cycle = cycle;
        this.heuresTransferees = heuresTransferees;
    }
    
    public void ajouterActivite(Activite nouvelleActivite) {
        activites.add(nouvelleActivite);
    }
    
    public String getCycle() {
        return cycle;
    }
    
    public int getHeuresTransferees() {
        return heuresTransferees;
    }
}
