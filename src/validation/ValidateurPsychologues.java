package validation;

import declaration.ActiviteDeFormation;
import declaration.DeclarationDeFormation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;  

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurPsychologues extends ValidateurMembreProfessionnel {
    public ValidateurPsychologues(DeclarationDeFormation membre) {
        super(membre);
    }
    
    public boolean cycleEstValide() {
        return membre.getCycle().equals("2010-2015");
    }
    
    /**
     * Intervalles permises d'une activité selon son cycle
     */
    public void intervallesActivitesCompleteesValides(ActiviteDeFormation activite) {
        String date = activite.getDateCompletee();
        
        if(membre.getCycle().equals("2010-2015")) {
            dateActivitesCompleteesValides(date, "2010-01-01", "2015-01-01");
        }
    }
    
    /**
     * Le cycle "2010-2015" pourra contenir des activités effectuées du 
     * 1er janvier 2010 et le 1er janvier 2015 inclusivement
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
     * Les psychologues doivent effectuer un minimum de 90 heures 
     * de formation continue dans un cycle.
     * 
     * @param nombreDHeures
     * @return 
     */
    public boolean minimumHeuresFormationCycle(int nombreDHeures) {
        return nombreDHeures >= 90;
    }
    
    /**
     * Un minimum de 25 heures par cycle sont nécessaires dans la catégorie "cours"
     * 
     * @param categorie
     * @return 
     */
    protected int minimumHeuresSelonCategorie(String categorie) {
        int nombreMinimumHeures = Integer.MIN_VALUE;
        
        if (categorie.equals("cours")) {
            nombreMinimumHeures = 25;
        }
        
        return nombreMinimumHeures;
    }
    
    /**
     * 
     * @param categorie
     * @return 
     */
    protected int maximumHeuresSelonCategorie(String categorie) {
        int nombreMaximumHeures = Integer.MAX_VALUE;
        
        if (categorie.equals("conférence")) {
            nombreMaximumHeures = 15;
        } 
        
        return nombreMaximumHeures;
    }
}
