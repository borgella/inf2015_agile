/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import declaration.DeclarationDeFormation;
import declaration.ActiviteDeFormation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurGeologues extends ValidateurDeDeclaration {
    
    public ValidateurGeologues(DeclarationDeFormation membre) {
        super(membre);
    }

    public boolean validerLeCycle() {
        return membre.getCycle().equals("2013-2016");
    }
    
    /**
     * Intervalles permises d'une activité selon son cycle
     */
    public void intervallesActivitesCompleteesValides(ActiviteDeFormation activite) {
        String date = activite.getDateCompletee();
        
        if(membre.getCycle().equals("2013-2016")) {
            dateActivitesCompleteesValides(date, "2013-06-01", "2016-06-01");
        }
    }
    
    /**
     * Le cycle "2013-2016" pourra contenir des activités effectuées du 
     * 1er juin 2013 et le 1er juin 2016 inclusivement
     * 
     * Date invalide => MESSAGE D'ERREUR + activité ignorée des calculs 
     * Les dates sont indiquées en format ISO-8601
     *
     * @param date
     * @param intervalleMinimum
     * @param intervalleMaximum
     * @return true si la date se trouve dans les intervalles
     */
    public boolean dateActivitesCompleteesValides(String date, String intervalleMinimum, String intervalleMaximum) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Date dateIntervalleMinimum = null;
        Date dateIntervalleMaximum = null;
        Date dateLue = null;
        
        try {
            dateIntervalleMinimum = df.parse(intervalleMinimum);
            dateIntervalleMaximum = df.parse(intervalleMaximum);
            dateLue = df.parse(date);
        } catch(ParseException e) {
            e.getMessage();
        }
        
        return (dateLue.after(dateIntervalleMinimum) || (dateLue.equals(dateIntervalleMinimum)) 
                && (dateLue.before(dateIntervalleMaximum) || dateLue.equals(dateIntervalleMaximum)));
    }
    
    /**
     * Les géologues doivent effectuer un minimum de 55 heures 
     * de formation continue dans un cycle.
     * @param nombreDHeures
     * @return 
     */
    public boolean minimumHeuresFormationCycle(int nombreDHeures) {
        return nombreDHeures >= 55;
    }
    
    /**
     * Un minimum de 22 heures par cycle sont nécessaires dans la catégorie "cours"
     * Un minimum de 3 heures par cycle sont nécessaires dans la catégorie "projet de recherche"
     * Un minimum d'une heure par cycle est nécessaire dans la catégorie "groupe de discussion"
     * @param categorie
     * @return 
     */
    protected int minimumHeuresSelonCategorie(String categorie) {
        int nombreMinimumHeures = Integer.MIN_VALUE;
        
        if (categorie.equals("cours")) {
            nombreMinimumHeures = 22;
        } else if (categorie.equals("projet de recherche")) {
            nombreMinimumHeures = 3;
        } else if (categorie.equals("groupe de discussion")) {
            nombreMinimumHeures = 1;
        }
        
        return nombreMinimumHeures;
    }
    
    
}
