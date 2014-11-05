package professionnels;

import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Podiatre extends Membre {

    public Podiatre(JSONObject activiteJson) {
        super(activiteJson);
    }

    public void ajouterActivitePourMembre(JSONObject activite) {
        int temporaire = regroupementDesCategories(activite.getString("categorie"));
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
        }
        return temporaire;
    }

    private int premiereCategorie(String categorie) {
        int temporaire = 0;
        switch (categorie) {
            case "atelier": case "séminaire": case "colloque": 
            case "conférence": case "lecture dirigée":
            case "présentation": case "rédaction professionnelle":
                temporaire = 1;
                break;
        }
        return temporaire;
    }

    private int deuxiemeCategorie(String categorie) {
        int temporaire = 0;
        if (categorie.equals("cours") || categorie.equals("projet de recherche")
                || categorie.equals("groupe de discussion")) {
            temporaire = 2;
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
        return temporaire >= 20130601 && temporaire <= 20160601;
    }

    private int toInt(String number) {
        Integer temporaire = new Integer(number);
        return temporaire;
    }
}
