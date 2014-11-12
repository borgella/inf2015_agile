package professionnels;

import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Podiatre extends Geologue {

    public Podiatre(JSONObject activiteJson) {
        super(activiteJson);
    }
    
    @Override
    public void ajouterActivitePourMembre(JSONObject activite) {
        super.ajouterActivitePourMembre(activite);
    }

    @Override
    public int regroupementDesCategories(String categorie) {
        return super.regroupementDesCategories(categorie);
    }

    @Override
    public int premiereCategorie(String categorie) {
        return super.premiereCategorie(categorie);
    }

    @Override
    public int deuxiemeCategorie(String categorie) {
        return super.deuxiemeCategorie(categorie);
    }
    
    @Override
    public boolean dateValidePourMembre(String date) {
        return super.dateValidePourMembre(date);
    }
}
