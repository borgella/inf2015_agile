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
public class Psychologue extends Membre {
    protected String cycle;
    protected ArrayList<JSONObject> activitesAcceptees;
    protected ArrayList<JSONObject> activitesRefusees;

    public Psychologue(JSONObject activiteJson) {
        super(activiteJson);
        this.cycle = activiteJson.getString("cycle");
        activitesAcceptees = new ArrayList(1);
        activitesRefusees = new ArrayList(1);
    }

    @Override
    public void ajouterActivitePourMembre(JSONObject activite) {
        String categorie = activite.getString("categorie");
        int temporaire = regroupementDesCategories(categorie);
        int heures = activite.getInt("heures");
        if (dateValidePourMembre(activite.getString("date")) && temporaire != -1 && heures > 0) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }

    
    public boolean dateValidePourMembre(String date) {
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLue = null;
        Date min = null;
        Date max = null;
        try {
            dateLue = formatISO8601.parse(date);
            min = formatISO8601.parse("2010-01-01");
            max = formatISO8601.parse("2015-01-01");
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
    public int premiereCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("cours")) {
            temporaire = 1;
        }
        return temporaire;
    }

    @Override
    public int deuxiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("atelier") || categorie.equals("séminaire")
                || categorie.equals("colloque") || categorie.equals("lecture dirigée")
                || categorie.equals("présentation") || categorie.equals("groupe de discussion")
                || categorie.equals("projet de recherche")
                || categorie.equals("rédaction professionnelle")) {
            temporaire = 2;
        }
        return temporaire;
    }

    @Override
    public int troisiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("conférence")) {
            temporaire = 3;
        }
        return temporaire;
    }
    public ArrayList getActivitesRefusees() {
        return this.activitesRefusees;
    }

    public ArrayList getActivitesAcceptees() {
        return this.activitesAcceptees;
    }
}
