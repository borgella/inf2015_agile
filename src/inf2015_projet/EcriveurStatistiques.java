package inf2015_projet;

import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class EcriveurStatistiques {

    private String fichierStatistiques;

    EcriveurStatistiques() {
        fichierStatistiques = "cumulStatistiques/donneesStatistiques.json";
    }

    EcriveurStatistiques(String fichierStatistiques) {
        this.fichierStatistiques = fichierStatistiques;
    }

    public JSONObject chargerStatistiquesExistantes() throws IOException {
        String donneesBrutesStatistiques = FileReader.loadFileIntoString(fichierStatistiques, "UTF-8");
        JSONObject donneesStatistiques = JSONObject.fromObject(donneesBrutesStatistiques);
        return donneesStatistiques;
    }

    public JSONObject initialiserStatistiques() {
        JSONObject donneesStatistiques = new JSONObject();
        ajouterChampsStatistiquesPourDeclarations(donneesStatistiques);
        ajouterChampsStatistiquesPourActivitesValides(donneesStatistiques);
        return donneesStatistiques;
    }

    public void sauvegarderStatistiques(JSONObject donneesStatistiques) throws IOException {
        FileWriter accesFichierStatistiques = new FileWriter(fichierStatistiques);
        accesFichierStatistiques.write(donneesStatistiques.toString(2));
        accesFichierStatistiques.close();
    }

    private static void ajouterChampsStatistiquesPourDeclarations(JSONObject fichierStatistiques) {
        fichierStatistiques.accumulate("declarations_traitees", 0);
        fichierStatistiques.accumulate("declarations_completes", 0);
        fichierStatistiques.accumulate("declarations_incompletes_ou_invalides", 0);
        fichierStatistiques.accumulate("declarations_faites_par_des_hommes", 0);
        fichierStatistiques.accumulate("declarations_faites_par_des_femmes", 0);
        fichierStatistiques.accumulate("declarations_faites_par_des_gens_de_sexe_inconnu", 0);
    }

    private static void ajouterChampsStatistiquesPourActivitesValides(JSONObject fichierStatistiques) {
        ajouterChampsStatistiquePourNombreTotalActivitesValides(fichierStatistiques);
        ajouterChampsStatistiquesPourActivitesValidesParCategorie(fichierStatistiques);
    }

    private static void ajouterChampsStatistiquePourNombreTotalActivitesValides(JSONObject fichierStatistiques) {
        fichierStatistiques.accumulate("activites_valides_dans_les_declarations", 0);
    }

    private static void ajouterChampsStatistiquesPourActivitesValidesParCategorie(JSONObject fichierStatistiques) {
        JSONArray compteursPourCategories = new JSONArray();
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            JSONObject compteurPourCategorie = champsStatistiqueActivitesValidesParCategorie(categorie);
            compteursPourCategories.add(compteurPourCategorie);
        }
        fichierStatistiques.accumulate("activites_valides_par_categorie", compteursPourCategories);
    }

    public static String[] nomsDesCategoriesReconnues() {
        String categoriesReconnues[] = {"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée",
            "présentation", "groupe de discussion", "projet de recherche", "rédaction professionnelle"};
        return categoriesReconnues;
    }

    private static JSONObject champsStatistiqueActivitesValidesParCategorie(String categoriesReconnue) {
        JSONObject compteurPourCategorie = new JSONObject();
        compteurPourCategorie.accumulate("categorie", categoriesReconnue);
        compteurPourCategorie.accumulate("nombre", 0);
        return compteurPourCategorie;
    }

    public void reinitialiserStatistiques(JSONObject donneesStatistiques) {
        reinitialiserStatistiquesPourDeclarations(donneesStatistiques);
        reinitialiserStatistiquesPourActivites(donneesStatistiques);
    }

    private void reinitialiserStatistiquesPourDeclarations(JSONObject donneesStatistiques) {
        donneesStatistiques.put("declarations_traitees", 0);
        donneesStatistiques.put("declarations_completes", 0);
        donneesStatistiques.put("declarations_incompletes_ou_invalides", 0);
        donneesStatistiques.put("declarations_faites_par_des_hommes", 0);
        donneesStatistiques.put("declarations_faites_par_des_femmes", 0);
        donneesStatistiques.put("declarations_faites_par_des_gens_de_sexe_inconnu", 0);
    }

    private void reinitialiserStatistiquesPourActivites(JSONObject donneesStatistiques) {
        donneesStatistiques.put("activites_valides_dans_les_declarations", 0);
        JSONArray statsParCategorie = donneesStatistiques.getJSONArray("activites_valides_par_categorie");
        for (Object statsCourantes : statsParCategorie) {
            JSONObject statistiqueCategorie = (JSONObject) statsCourantes;
            statistiqueCategorie.put("nombre", 0);
        }
    }
}
