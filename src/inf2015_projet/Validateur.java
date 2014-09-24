/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inf2015_projet;

import java.util.ArrayList;

/**
 *
 * @author df891101
 */
public class Validateur {
    private boolean declarationComplete;
    private ArrayList <Activite> listeActivite;
  
        
    public Validateur(){
        declarationComplete = false;
        listeActivite = new ArrayList<Activite>(5);
    }
    
    
    public boolean validerLeCycle(String cycle){
        return cycle.equals("");
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
