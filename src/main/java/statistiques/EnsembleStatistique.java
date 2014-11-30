package statistiques;

import net.sf.json.JSONObject;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class EnsembleStatistique {
    
    String etiquette;
    JSONObject champsStatistiques;
    
    public EnsembleStatistique() {
        this("");
    }
    
    public EnsembleStatistique(String etiquette) {
        this.etiquette = etiquette;
        champsStatistiques = new JSONObject();
    }
    
    boolean contientChampsStatistiques(String champs) {
        return champsStatistiques.has(champs);
    }

    void ajouterChampsStatistique(String champs) {
        champsStatistiques.accumulate(champs, 0);
    }

    int obtenirStatistique(String champs) {
        return champsStatistiques.getInt(champs);
    }

    void incrementerStatistique(String champs) {
        incrementerStatistique(champs, 1);
    }

    void incrementerStatistique(String champs, int augmentation) {
        int ancienneValeur = champsStatistiques.getInt(champs);
        champsStatistiques.put(champs, ancienneValeur + augmentation);
    }
    
    String getEtiquette() {
        return etiquette;
    }
}
