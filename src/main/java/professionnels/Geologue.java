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
public class Geologue extends Membre {

    protected String cycle;
    protected ArrayList<JSONObject> activitesAcceptees;
    protected ArrayList<JSONObject> activitesRefusees;

    public Geologue() {
        super("géologues");
        cycle = "2013-2016";
        activitesAcceptees = new ArrayList(1);
        activitesRefusees = new ArrayList(1);
    }
    
    public Geologue(String ordre, String cycle) {
        super(ordre);
        this.cycle = cycle;
        activitesAcceptees = new ArrayList(1);
        activitesRefusees = new ArrayList(1);
    }

    public Geologue(JSONObject activiteJson) {
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

    @Override
    public int premiereCategorie(String categorie) {
        int temporaire = 0;
        switch (categorie) {
            case "atelier":
            case "séminaire":
            case "colloque":
            case "conférence":
            case "lecture dirigée":
            case "présentation":
            case "rédaction professionnelle":
                temporaire = 1;
                break;
        }
        return temporaire;
    }

    @Override
    public int deuxiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("cours") || categorie.equals("projet de recherche")
                || categorie.equals("groupe de discussion")) {
            temporaire = 2;
        }
        return temporaire;
    }

    @Override
    public boolean dateValidePourMembre(String date) {
        boolean validiteDate;
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateLue = formatISO8601.parse(date);
            Date min = formatISO8601.parse("2013-06-01");
            Date max = formatISO8601.parse("2016-06-01");
            validiteDate = ((dateLue.compareTo(min) >= 0) && (dateLue.compareTo(max) <= 0));
        } catch (ParseException ex) {
            validiteDate = false;
        }
        return validiteDate;
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

    @Override
    public int obtenirNombreActivitesValides() {
        return activitesAcceptees.size();
    }

    @Override
    public int obtenirNombreActivitesValidesParCategorie(String categorie) {
        int nombreActivitesValides = 0;
        for (int i = 0; i < activitesAcceptees.size(); i++) {
            JSONObject activiteValide = activitesAcceptees.get(i);
            String categorieCourante = activiteValide.getString("categorie");
            if (categorie.equals(categorieCourante)) {
                nombreActivitesValides++;
            }
        }
        return nombreActivitesValides;
    }
}
