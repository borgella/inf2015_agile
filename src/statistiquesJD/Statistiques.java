/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistiquesJD;

import inf2015_projet.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author User
 */
public class Statistiques {
    boolean declarationTraitee;
    boolean declarationIncompleteOuInvalide;
    int sexeDeclaree;
    TreeMap<String, Integer> activitesValidesParCategorie;
    
    public Statistiques() {
        declarationTraitee = false;
        declarationIncompleteOuInvalide = false;
        activitesValidesParCategorie = new TreeMap<>();
        etablirCategoriesReconnues(activitesValidesParCategorie);
    }

    private void etablirCategoriesReconnues(TreeMap<String, Integer> activitesValidesParCategorie) {
        ArrayList<String> nomDeCategories = nomsDesCategoriesReconnues();
        for (int i = 0; i < nomDeCategories.size(); i++) {
            String categorie = nomDeCategories.get(i);
            activitesValidesParCategorie.put(categorie, 0);
        }
    }

    private ArrayList<String> nomsDesCategoriesReconnues() {
        String nomDeCategories[] = {"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée",
            "présentation", "groupe de discussion", "projet de recherche", "rédaction professionnelle" };
        int nombreDeCategories = nomDeCategories.length;
        ArrayList<String> categories = new ArrayList<>(nombreDeCategories);
        for (int i = 0; i < nombreDeCategories; i++) {
            categories.add(nomDeCategories[i]);
        }
       return categories;
    }
    
    public void enregistrerDeclarationTraitee() {
        declarationTraitee = true;
    }
    
    public void enregistrerDeclarationInvalideOuIncomplete() {
        declarationIncompleteOuInvalide = true;
    }
    
    public void enregistrerSexeDeclaree(int codeSexe) {
        sexeDeclaree = codeSexe;  
    }
    
    public void enregistrerActiviteValideParCategorie(String categorie) {
        Integer activitesValideSelonCategorie = activitesValidesParCategorie.get(categorie);
        activitesValideSelonCategorie++;
    }
    
    public void mettreAJourStatistiquesCumulatives() {
        JSONObject donneesStatistiques = chargerStatistiquesDisponibles();
        ajouterStatistiquesDeLaDeclarationCourante(donneesStatistiques);
        ecrireNouvellesDonneesStatistiques(donneesStatistiques);
    }

    private static JSONObject chargerStatistiquesDisponibles() {
        JSONObject donneesStatistiques;
        try {
            donneesStatistiques = chargerStatistiquesAnterieures();
        } catch (IOException e) {
            donneesStatistiques = construireFichierStatistiques();
        }
        return donneesStatistiques;
    }
    
    private static JSONObject chargerStatistiquesAnterieures() throws IOException {
        String fichierDesStatistiques = "cumulStatistiquesJD/donneesStatistiques.json";
        String statistiquesAnterieures = FileReader.loadFileIntoString(fichierDesStatistiques, "UTF-8");
        return JSONObject.fromObject(statistiquesAnterieures);
    }

    private static JSONObject construireFichierStatistiques() {
        return null; //TODO: Implémenter fonction
    }

    private void ajouterStatistiquesDeLaDeclarationCourante(JSONObject donneesStatistiques) {
        mettreAJourDeclarationsTraitees(donneesStatistiques);
        mettreAJourDeclarationsCompletes(donneesStatistiques);
        mettreAJourDeclarationsIncompletesOuInvalides(donneesStatistiques);
        mettreAJourDeclarationsSelonSexe(donneesStatistiques);
        mettreAJourActivitesValidesDesDeclarations(donneesStatistiques);
        mettreAJourActivitesValidesParCategorie(donneesStatistiques);
    }

    private void mettreAJourDeclarationsTraitees(JSONObject donneesStatistiques) {
        if (declarationTraitee) {
            String champsStatistique = "declarations_traitees";
            int declarationsTraitees = donneesStatistiques.getInt(champsStatistique);
            donneesStatistiques.put(champsStatistique, ++declarationsTraitees);
        }
    }

    private void mettreAJourDeclarationsCompletes(JSONObject donneesStatistiques) {
        if (!declarationIncompleteOuInvalide) {
            String champsStatistique = "declarations_completes";
            int nombreAnterieur = donneesStatistiques.getInt(champsStatistique);
            donneesStatistiques.put(champsStatistique, ++nombreAnterieur);
        }
    }

    private void mettreAJourDeclarationsIncompletesOuInvalides(JSONObject donneesStatistiques) {
        if (declarationIncompleteOuInvalide) {
            String champsStatistique = "declarations_incompletes_ou_invalides";
            int nombreAnterieur = donneesStatistiques.getInt(champsStatistique);
            donneesStatistiques.put(champsStatistique, ++nombreAnterieur);
        }
    }

    private void mettreAJourDeclarationsSelonSexe(JSONObject donneesStatistiques) {
        String champsStatistique;
        if (sexeDeclaree == 1) {
            champsStatistique = "declarations_faites_par_des_hommes";
        } else if (sexeDeclaree == 2) {
            champsStatistique = "declarations_faites_par_des_femmes"; 
        } else {
            champsStatistique = "declarations_faites_par_des_gens_de_sexe_inconnu";
        }
        int nombreAnterieur = donneesStatistiques.getInt(champsStatistique);
        donneesStatistiques.put(champsStatistique, ++nombreAnterieur);
    }

    private void mettreAJourActivitesValidesDesDeclarations(JSONObject donneesStatistiques) {
        String champsStatistique = "activites_valides_dans_les_declarations";
        int nombreAnterieur = donneesStatistiques.getInt(champsStatistique);
        int activitesValidesPourDeclarationCourante = calculerNombreTotalActivitesValides();
        donneesStatistiques.put(champsStatistique, nombreAnterieur + activitesValidesPourDeclarationCourante);
    }

    private void mettreAJourActivitesValidesParCategorie(JSONObject donneesStatistiques) {
        String champsStatistique = "activites_valides_par_categorie";
        JSONArray compteursActivitesValidesParCategorie = donneesStatistiques.getJSONArray(champsStatistique);
        ArrayList<String> nomDesCategories = nomsDesCategoriesReconnues();
        for (String categorie: nomDesCategories) {
                mettreAJourActivitesValidesParCategorie(compteursActivitesValidesParCategorie, categorie);
        }
    }
    
    private void mettreAJourActivitesValidesParCategorie(JSONArray compteursParCategorie, String categorie) {
        JSONObject compteurPourCategorie = trouverCompteurPourCategorie(compteursParCategorie, categorie);
        String champsStatistique = "nombre";
        int nombreAnterieur = compteurPourCategorie.getInt(champsStatistique);
        int activitesValidesCourantesPourCategorie = activitesValidesParCategorie.get(categorie);
        compteurPourCategorie.put(champsStatistique, nombreAnterieur + activitesValidesCourantesPourCategorie);
    }

    private static JSONObject trouverCompteurPourCategorie(JSONArray compteursParCategorie, String categorie) {
        JSONObject compteurPourCategorie = null;
        for (int i = 0; i < compteursParCategorie.size(); i++) {
            JSONObject compteur = compteursParCategorie.getJSONObject(i);
            String categorieCompteur = compteur.getString("categorie");
            if (categorie.equals(categorieCompteur)) {
                compteurPourCategorie = compteur;
                break;
            }
        }
        return compteurPourCategorie;
    }
    
    private int calculerNombreTotalActivitesValides() {
        int nombreTotalActivitesValides = 0;
        ArrayList<String> nomDesCategories = nomsDesCategoriesReconnues();
        for (String categorie: nomDesCategories) {
                nombreTotalActivitesValides += activitesValidesParCategorie.get(categorie);
        }
        return nombreTotalActivitesValides;
    }

    private void ecrireNouvellesDonneesStatistiques(JSONObject donneesStatistiques) {
        try {
        FileWriter fichierDesStatistiques = new FileWriter("cumulStatistiquesJD/donneesStatistiques.json");
        fichierDesStatistiques.write(donneesStatistiques.toString(2));
        fichierDesStatistiques.close();
        } catch (IOException e) {
            // N'est pas atteingnable?
        }
    }

    
}
