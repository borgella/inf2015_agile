package professionnels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Architecte extends Membre {

    private int heuresTransferees;

    public Architecte(JSONObject activiteJson) {
        super(activiteJson);
        this.heuresTransferees = activiteJson.getInt("heures_transferees_du_cycle_precedent");
    }

    public void ajouterActivitePourMembre(JSONObject activite) {
        if (cycle.equals("2008-2010")) {
            ajouterActivitePourArchitecte(activite, "2008-04-01", "2010-07-01");
        } else if (cycle.equals("2010-2012")) {
            ajouterActivitePourArchitecte(activite, "2010-04-01", "2012-04-01");
        } else {    // Cycle 2012-2014
            ajouterActivitePourArchitecte(activite, "2012-04-01", "2014-04-01");
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

    /*private void ajouterActivitePourArchitecte12_14(JSONObject activite) {
        String categorie = activite.getString("categorie");
        int temporaire = regroupementDesCategories(categorie);
        if (dateValidePourCycle2012_2014(activite.getString("date")) && temporaire != -1) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }

    private void ajouterActivitePourArchitecte10_12(JSONObject activite) {
        String categorie = activite.getString("categorie");
        int temporaire = regroupementDesCategories(categorie);
        if (dateValidePourCycle2010_2012(activite.getString("date")) && temporaire != -1) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }

    private void ajouterActivitePourArchitecte08_10(JSONObject activite) {
        String categorie = activite.getString("categorie");
        int temporaire = regroupementDesCategories(categorie);
        if (dateValidePourCycle2008_2010(activite.getString("date")) && temporaire != -1) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }*/

    public int regroupementDesCategories(String categorie) {
        int temporaire = -1;
        if (premiereCategorie(categorie) == 1) {
            temporaire = 1;
        } else if (deuxiemeCategorie(categorie) == 2) {
            temporaire = 2;
        } else if (troisiemeCategorie(categorie) == 3) {
            temporaire = 3;
        }
        return temporaire;
    }

    private int premiereCategorie(String categorie) {
        int temporaire = 0;
        switch (categorie) {
            case "cours": case "atelier":
            case "séminaire": case "colloque":
            case "conférence": case "lecture dirigée":
                temporaire = 1;
                break;
        }
        return temporaire;
    }

    private int deuxiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("présentation") || categorie.equals("projet de recherche")) {
            temporaire = 2;
        }
        return temporaire;
    }

    private int troisiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("groupe de discussion") || categorie.equals("rédaction professionnelle")) {
            temporaire = 3;
        }
        return temporaire;
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
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLue = null;
        Date min = null;
        Date max = null;
        try {
            dateLue = formatISO8601.parse(date);
            min = formatISO8601.parse(intervalleMin);
            max = formatISO8601.parse(intervalleMax);
        } catch (ParseException ex) {
            ex.getMessage();
        }
        return ((dateLue.compareTo(min) >= 0) && (dateLue.compareTo(max) <= 0));
    }
    
    /*public boolean dateValidePourMembre(String date) {
        boolean dateValide;
        if (cycle.equals("2008-2010")) {
            dateValide = dateValidePourCycle2008_2010(date);
        } else if (cycle.equals("2010-2012")) {
            dateValide = dateValidePourCycle2010_2012(date);
        } else {    // Cycle 2012-2014
            dateValide = dateValidePourCycle2012_2014(date);
        }
        return dateValide;
    }
    
    private boolean dateValidePourCycle2008_2010(String date) {
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLue = null;
        Date min = null;
        Date max = null;
        try {
            dateLue = formatISO8601.parse(date);
            min = formatISO8601.parse("2008-04-01");
            max = formatISO8601.parse("2010-07-01");
        } catch (ParseException ex) {
            ex.getMessage();
        }
        return ((dateLue.compareTo(min) >= 0) && (dateLue.compareTo(max) <= 0));
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
    }*/

    public int getHeuresTransferees() {
        return this.heuresTransferees;
    }
}
