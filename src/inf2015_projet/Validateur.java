/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inf2015_projet;

import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author df891101
 */
public class Validateur {
    private DeclarationDeFormation membre;
    private int heuresTotal;
        
    public Validateur(DeclarationDeFormation membre){
        this.membre = membre;
        heuresTotal = 0;
    }
    
    public boolean validerLeCycle(){
        return membre.getCycle().equals("2012-2014");
    }   
    
    public int nombreDHeuresErronee(){
        Activite act ;
        ArrayList <Activite>liste = membre.getActiviteErronee();
        int somme = 0;
        for(int i = 0 ; i < membre.getActiviteErronee().size(); ++i){
            act = liste.get(i);
            somme += act.getHeures();
        }
        return somme;
    }
    
    
    public int nombreDHeuresCategorie1(){
        Activite act ;
        ArrayList <Activite>liste = membre.getActivites();
        int somme = 0;
        for(int i = 0 ; i < membre.getActivites().size(); ++i){
            act = liste.get(i);
            if(act.regroupementDesCategories(act.getCategorie()) == 1){
                somme += act.getHeures();
            }                                   
        }
        return somme;
    }
    
}
