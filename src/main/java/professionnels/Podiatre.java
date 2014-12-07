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
public class Podiatre extends Geologue {
    
    public Podiatre() {
        super("podiatres", "2013-2016");
    }
    
    public Podiatre(JSONObject activiteJson) {
        super(activiteJson);
    }

    @Override
    public void ajouterActivitePourMembre(JSONObject activite) {
        super.ajouterActivitePourMembre(activite);
    }
  
    @Override
    public boolean dateValidePourMembre(String date) {
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLue = null;
        Date min = null;
        Date max = null;
        try {
            dateLue = formatISO8601.parse(date);
            min = formatISO8601.parse("2013-06-01");
            max = formatISO8601.parse("2016-06-01");
        } catch (ParseException ex) {
            ex.getMessage();
        }
        return ((dateLue.compareTo(min) >= 0) && (dateLue.compareTo(max) <= 0));
    }

    
    @Override
    public String getCycle() {
        return this.cycle;
    }

    @Override
    public ArrayList getActivitesRefusees() {
        return this.activitesRefusees;
    }

    @Override
    public ArrayList getActivitesAcceptees() {
        return this.activitesAcceptees;
    }
}
