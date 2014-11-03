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
    
    private int compteurNombreTotalDeclarationsTraitees = 0;
    private int compteurNombreTotalDeclarationsCompletes = 0;
    private int compteurNombreTotalDeclarationsIncompletes = 0;
    private int compteurNombreTotalDeclarationsHommes = 0;
    private int compteurNombreTotalDeclarationsFemmes = 0;
    private int compteurNombreTotalDeclarationsSexeInconnu = 0;
    private int compteurNombreTotalActivitesValidesDeclarations = 0;
    private int compteurNombreActivitesValidesCategories = 0;
    
    /* Declarations Traitees */
    public void incrementeTotalDeclarationsTraitees() {
        compteurNombreTotalDeclarationsTraitees++;
    }
    
    public int nombreTotalDeclarationsTraitees() {
        return compteurNombreTotalDeclarationsTraitees;
    }
    
    /* Declarations Completes */
    public void incrementeTotalDeclarationsCompletes() {
        compteurNombreTotalDeclarationsCompletes++;
    }
    
    public int nombreTotalDeclarationsCompletes() {
        return compteurNombreTotalDeclarationsCompletes;
    }
    
    /* Declarations Incompletes */
    public void incrementeTotalDeclarationsIncompletes() {
        compteurNombreTotalDeclarationsIncompletes++;
    }
    
    public int nombreTotalDeclarationsIncompletes() {
        return compteurNombreTotalDeclarationsIncompletes;
    }
    
    /* Declarations Hommes */
    public void incrementeTotalDeclarationsHommes() {
        compteurNombreTotalDeclarationsHommes++;
    }
    
    public int nombreTotalDeclarationsHommes() {
        return compteurNombreTotalDeclarationsHommes;
    }
    
    /* Declarations Femmes */
    public void incrementeTotalDeclarationsFemmes() {
        compteurNombreTotalDeclarationsFemmes++;
    }
    
    public int nombreTotalDeclarationsFemmes() {
        return compteurNombreTotalDeclarationsFemmes;
    }
    
    /* Declarations Sexe Inconnu */
    public void incrementeTotalDeclarationsSexeInconnu() {
        compteurNombreTotalDeclarationsSexeInconnu++;
    }
    
    public int nombreTotalDeclarationsSexeInconnu() {
        return compteurNombreTotalDeclarationsSexeInconnu;
    }
    
    /* Activites Valides dans les declarations */
    public void incrementeTotalActivitesValidesDeclarations() {
        compteurNombreTotalActivitesValidesDeclarations++;
    }
    
    public int nombreTotalActivitesValidesDeclarations() {
        return compteurNombreTotalActivitesValidesDeclarations;
    }
    
    /* Activites Valides dans les categories */
    public void incrementeActivitesValidesCategories() {
        compteurNombreActivitesValidesCategories++;
    }
    
    public int nombreActivitesValidesCategories() {
        return compteurNombreActivitesValidesCategories;
    }
    
    /* Fichier de sortie */
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
    
    /* Reinitialisation des valeurs */
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
