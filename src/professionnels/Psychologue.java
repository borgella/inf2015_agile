package professionnels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Psychologue extends Membre {

    public Psychologue(JSONObject activiteJson) {
        super(activiteJson);
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
        boolean validiteDate;
        SimpleDateFormat formatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateLue = formatISO8601.parse(date);
            Date min = formatISO8601.parse("2010-01-01");
            Date max = formatISO8601.parse("2015-01-01");
            validiteDate = ((dateLue.compareTo(min) >= 0) && (dateLue.compareTo(max) <= 0));
        } catch (ParseException ex) {
            validiteDate = false;
        }
        return validiteDate;
    }
}
