/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistiquesJD;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author User
 */
public class Statistiques {
    boolean declarationTraitee;
    boolean declarationInvalideOuIncomplete;
    int sexeDeclaree;
    TreeMap<String, Integer> activitesValidesParCategorie;
    
    public Statistiques() {
        declarationTraitee = false;
        declarationInvalideOuIncomplete = false;
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
    
    private void enregistrerDeclarationTraitee() {
        declarationTraitee = true;
    }
    
    private void enregistrerDeclarationInvalideOuIncomplete() {
        declarationInvalideOuIncomplete = true;
    }
    
    private void enregistrerSexeDeclaree(int codeSexe) {
        sexeDeclaree = codeSexe;  
    }
    
    private void enregistrerActiviteValideParCategorie(String categorie) {
        Integer activitesValideSelonCategorie = activitesValidesParCategorie.get(categorie);
        activitesValideSelonCategorie++;
    }
    
  
}
