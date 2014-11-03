/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Chelny
 */
public class StatistiquesCD {
    
    private static int compteurNombreTotalDeclarationsTraitees = 0;
    private static int compteurNombreTotalDeclarationsCompletes = 0;
    private static int compteurNombreTotalDeclarationsIncompletes = 0;
    private static int compteurNombreTotalDeclarationsHommes = 0;
    private static int compteurNombreTotalDeclarationsFemmes = 0;
    private static int compteurNombreTotalDeclarationsSexeInconnu = 0;
    private static int compteurNombreTotalActivitesValidesDeclarations = 0;
    private static int compteurNombreActivitesValidesCategories = 0;
    
    public int nombreTotalDeclarationsTraitees() {
        compteurNombreTotalDeclarationsTraitees++;
        
        return compteurNombreTotalDeclarationsTraitees;
    }
    
    public int nombreTotalDeclarationsCompletes() {
        compteurNombreTotalDeclarationsCompletes++;
        
        return compteurNombreTotalDeclarationsCompletes;
    }
    
    public int nombreTotalDeclarationsIncompletes() {
        compteurNombreTotalDeclarationsIncompletes++;
        
        return compteurNombreTotalDeclarationsIncompletes;
    }
    
    public int nombreTotalDeclarationsHommes() {
        compteurNombreTotalDeclarationsHommes++;
        
        return compteurNombreTotalDeclarationsHommes;
    }
    
    public int nombreTotalDeclarationsFemmes() {
        compteurNombreTotalDeclarationsFemmes++;
        
        return compteurNombreTotalDeclarationsFemmes;
    }
    
    public int nombreTotalDeclarationsSexeInconnu() {
        compteurNombreTotalDeclarationsSexeInconnu++;
        
        return compteurNombreTotalDeclarationsSexeInconnu;
    }
    
    public int nombreTotalActivitesValidesDeclarations() {
        compteurNombreTotalActivitesValidesDeclarations++;
        
        return compteurNombreTotalActivitesValidesDeclarations;
    }
    
    public int nombreActivitesValidesCategories() {
        compteurNombreActivitesValidesCategories++;
        
        return compteurNombreActivitesValidesCategories;
    }
    
    @Override
    public String toString() {
        return  "Nombre total de déclarations traitées: " + nombreTotalDeclarationsTraitees() + "\n" +
                "Nombre total de déclarations complètes: " + nombreTotalDeclarationsCompletes() + "\n" +
                "Nombre total de déclarations incomplètes: " + nombreTotalDeclarationsIncompletes() + "\n" +
                "Nombre total de déclarations faites par des hommes: " + nombreTotalDeclarationsHommes() + "\n" +
                "Nombre total de déclarations faites par des femmes: " + nombreTotalDeclarationsFemmes() + "\n" +
                "Nombre total de déclarations faites par des gens de sexe inconnu: " + nombreTotalDeclarationsSexeInconnu() + "\n" +
                "Nombre total d'activités valides dans les déclarations: " + nombreTotalActivitesValidesDeclarations() + "\n" +
                "Nombre d'activités valides par catégorie: " + nombreActivitesValidesCategories();
    }
    
    public void ecritureFichierStatistiques() throws IOException {
        FileWriter ecriture = new FileWriter("INF2015_Statistiques.txt");
        ecriture.write(toString());
        ecriture.close();
    }
    
    public void reinitialiserValeursStatistiques() {
        compteurNombreTotalDeclarationsTraitees = 0;
        compteurNombreTotalDeclarationsCompletes = 0;
        compteurNombreTotalDeclarationsIncompletes = 0;
        compteurNombreTotalDeclarationsHommes = 0;
        compteurNombreTotalDeclarationsFemmes = 0;
        compteurNombreTotalDeclarationsSexeInconnu = 0;
        compteurNombreTotalActivitesValidesDeclarations = 0;
        compteurNombreActivitesValidesCategories = 0;
    }
}
