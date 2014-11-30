package statistiques;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class EnsembleStatistique {
    
    JSONObject donneesStatistiques;
    
    public EnsembleStatistique() {
        donneesStatistiques = new JSONObject();
    }

    EnsembleStatistique(JSONObject donneesStatistiques) {
       this.donneesStatistiques = donneesStatistiques;
    }
    
    boolean contientChampsOuCategorieStatistique(String champs) {
        return donneesStatistiques.has(champs);
    }
    
    boolean contientChampsStatistiqueSousCategorie(String categorie, String champs) {
        boolean champsTrouvee = false;
        if (donneesStatistiques.has(categorie)) {
            JSONArray champsSousCategorie = donneesStatistiques.getJSONArray(categorie);
            champsTrouvee = (indiceDuChampsSousCategorie(champsSousCategorie, champs) != -1);
        }
        return champsTrouvee;
    }
    
    private int indiceDuChampsSousCategorie(JSONArray categorie, String champs) {
        int indice = -1;
        for (int i = 0; i < categorie.size(); i++) {
            JSONObject statistiqueCandidate = categorie.getJSONObject(i);
            if (statistiqueCandidate.has(champs)) {
                indice = i;
                break;
            }
        }
        return indice;
    }
    
    void ajouterChampsStatistique(String champs) {
        donneesStatistiques.accumulate(champs, 0);
    }
    
    void ajouterCategorieDeChampsStatistiques(String categorie) {
        donneesStatistiques.accumulate(categorie, new JSONArray());
    }
    
    void ajouterChampsStatistiqueSousCategorie(String categorie, String champs) {
       JSONArray champsSousCategorie = donneesStatistiques.getJSONArray(categorie);
       JSONObject nouvelleStatistique = new JSONObject();
       nouvelleStatistique.accumulate(champs, 0);
       champsSousCategorie.add(nouvelleStatistique);
    }

    int obtenirStatistique(String champs) {
        return donneesStatistiques.getInt(champs);
    }
    
    int obtenirStatistiqueSousCategorie(String categorie, String champs) {
        JSONArray champsSousCategorie = donneesStatistiques.getJSONArray(categorie);
        int indiceDeStatistique = indiceDuChampsSousCategorie(champsSousCategorie, champs);
        JSONObject statistiqueRecherchee = champsSousCategorie.getJSONObject(indiceDeStatistique);
        return statistiqueRecherchee.getInt(champs);
    }

    void incrementerStatistique(String champs) {
        incrementerStatistique(champs, 1);
    }

    void incrementerStatistique(String champs, int augmentation) {
        int ancienneValeur = donneesStatistiques.getInt(champs);
        donneesStatistiques.put(champs, ancienneValeur + augmentation);
    }
}
