package professionnels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Architecte extends Membre {

    private int heuresTransferees;
    private String cycle;
    private ArrayList<JSONObject> activitesAcceptees;
    private ArrayList<JSONObject> activitesRefusees;

    public Architecte(JSONObject activiteJson) {
        super(activiteJson);
        this.heuresTransferees = activiteJson.getInt("heures_transferees_du_cycle_precedent");
        this.cycle = activiteJson.getString("cycle");
        activitesAcceptees = new ArrayList(1);
        activitesRefusees = new ArrayList(1);
    }

    @Override
    public void ajouterActivitePourMembre(JSONObject activite) {
        if(cycle.equals("2008-2010")) {
            ajouterActivitePourArchitecte08_10(activite);
        } else if (cycle.equals("2010-2012")) {
            ajouterActivitePourArchitecte10_12(activite);
        } else {           // Cycle 2012-2014
            ajouterActivitePourArchitecte12_14(activite);
        }
    }
    
    public void ajouterActivitePourArchitecte(JSONObject activite, String intervalleMin, String intervalleMax) {
        String categorie = activite.getString("categorie");
        int temporaire = regroupementDesCategories(categorie);
        if (dateValideSelonCycle(activite.getString("date"), intervalleMin, intervalleMax) && temporaire != -1) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }

    public boolean dateValidePourMembre(String date) {
        boolean dateValide;
        if (cycle.equals("2008-2010")) {
            dateValide = dateValideSelonCycle(date, "2008-04-01", "2010-07-01");
        } else if (cycle.equals("2010-2012")) {
            dateValide = dateValideSelonCycle(date, "2010-04-01", "2012-04-01");
        } else {    // Cycle 2012-2014
            dateValide = dateValideSelonCycle(date, "2012-04-01", "2014-04-01");
        }
        return dateValide;
    }
    
    public boolean dateValideSelonCycle(String date, String intervalleMin, String intervalleMax) {
        boolean validiteDate; 
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateLue = formatISO8601.parse(date);
            Date min = formatISO8601.parse(intervalleMin);
            Date max = formatISO8601.parse(intervalleMax);
            validiteDate = ((dateLue.compareTo(min) >= 0) && (dateLue.compareTo(max) <= 0));
        } catch (ParseException ex) {
            validiteDate = false;
        }
        return validiteDate;
    }
    
    private boolean dateValidePourCycle2010_2012(String date) {
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLue = null;
        Date min = null;
        Date max = null;
        try {
            dateLue = formatISO8601.parse(date);
            min = formatISO8601.parse("2010-04-01");
            max = formatISO8601.parse("2012-04-01");
        } catch (ParseException ex) {
            ex.getMessage();
        }
        return ((dateLue.compareTo(min) >= 0) && (dateLue.compareTo(max) <= 0));
    }
    
    private boolean dateValidePourCycle2012_2014(String date) {
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLue = null;
        Date min = null;
        Date max = null;
        try {
            dateLue = formatISO8601.parse(date);
            min = formatISO8601.parse("2012-04-01");
            max = formatISO8601.parse("2014-04-01");
        } catch (ParseException ex) {
            ex.getMessage();
        }
        return ((dateLue.compareTo(min) >= 0) && (dateLue.compareTo(max) <= 0));
    }
    
    public int getHeuresTransferees() {
        return this.heuresTransferees;
    }

    @Override
    public String getCycle() {
        return this.cycle;
    }

    public ArrayList getActivitesRefusees() {
        return this.activitesRefusees;
    }

    public ArrayList getActivitesAcceptees() {
        return this.activitesAcceptees;
    }
}
