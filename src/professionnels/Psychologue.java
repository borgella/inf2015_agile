package professionnels;

import java.util.ArrayList;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Psychologue {

    private final String numeroDePermis;
    private final String ordre;
    private final String cycle;
    private ArrayList<JSONObject> activitesAcceptees;
    private ArrayList<JSONObject> activitesRefusees;

    public Psychologue(JSONObject activiteJson) {
        this.numeroDePermis = activiteJson.getString("numero_de_permis");
        this.cycle = activiteJson.getString("cycle");
        this.ordre = activiteJson.getString("ordre");
        activitesAcceptees = new ArrayList<JSONObject>(1);
        activitesRefusees = new ArrayList<JSONObject>(1);
    }

    public void ajouterActivitePourPsychologue(JSONObject activite) {
        String categorie = activite.getString("categorie");
        int temporaire = regroupementDesCategories(categorie);
        int heures = activite.getInt("heures");
        if (dateValidePourMembre(activite.getString("date")) && temporaire != -1 && heures > 0) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }

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
        if (categorie.equals("cours")) {
            temporaire = 1;
        }
        return temporaire;
    }

    private int deuxiemeCategorie(String categorie) {
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

    public int troisiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("conférence")) {
            temporaire = 3;
        }
        return temporaire;
    }

    public boolean dateValidePourMembre(String date) {
        int temporaire;
        if ((toInt(date.substring(5, 7)) >= 1 && toInt(date.substring(5, 7)) <= 12) 
                && (toInt(date.substring(8, 10)) >= 1 && toInt(date.substring(8, 10)) <= 31)) {
            date = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
        } else {
            return false;
        }
        temporaire = toInt(date);
        return temporaire >= 20100101 && temporaire <= 20150101;
    }

    private int toInt(String number) {
        Integer temporaire = new Integer(number);
        return temporaire;
    }

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
