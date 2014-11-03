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

    private int compteurNombreTotalDeclarationsTraitees;
    private int compteurNombreTotalDeclarationsCompletes;
    private int compteurNombreTotalDeclarationsIncompletes;
    private int compteurNombreTotalDeclarationsHommes;
    private int compteurNombreTotalDeclarationsFemmes;
    private int compteurNombreTotalDeclarationsSexeInconnu;
    private int compteurNombreTotalActivitesValidesDeclarations;
    private int compteurNombreActivitesValidesCategories;

    public StatistiquesCD() {
        this.compteurNombreTotalDeclarationsTraitees = 0;
        this.compteurNombreTotalDeclarationsCompletes = 0;
        this.compteurNombreTotalDeclarationsIncompletes = 0;
        this.compteurNombreTotalDeclarationsHommes = 0;
        this.compteurNombreTotalDeclarationsFemmes = 0;
        this.compteurNombreTotalDeclarationsSexeInconnu = 0;
        this.compteurNombreTotalActivitesValidesDeclarations = 0;
        this.compteurNombreActivitesValidesCategories = 0;
    }

    public void incrementeTotalDeclarationsTraitees() {
        this.compteurNombreTotalDeclarationsTraitees++;
    }
    
    public void incrementeTotalDeclarationsCompletes() {
        this.compteurNombreTotalDeclarationsCompletes++;
    }

    public void incrementeTotalDeclarationsIncompletes() {
        this.compteurNombreTotalDeclarationsIncompletes++;
    }
    
    public void incrementeTotalDeclarationsHommes() {
        this.compteurNombreTotalDeclarationsHommes++;
    }
    
    public void incrementeTotalDeclarationsFemmes() {
        this.compteurNombreTotalDeclarationsFemmes++;
    }
    
    public void incrementeTotalDeclarationsSexeInconnu() {
        this.compteurNombreTotalDeclarationsSexeInconnu++;
    }
    
    public void incrementeTotalActivitesValidesDeclarations() {
        this.compteurNombreTotalActivitesValidesDeclarations++;
    }
    
    public void incrementeActivitesValidesCategories() {
        this.compteurNombreActivitesValidesCategories++;
    }
    
    /* Getters */
    public int getNombreTotalDeclarationsTraitees() {
        return this.compteurNombreTotalDeclarationsTraitees;
    }

    public int getNombreTotalDeclarationsCompletes() {
        return this.compteurNombreTotalDeclarationsCompletes;
    }

    public int getNombreTotalDeclarationsIncompletes() {
        return this.compteurNombreTotalDeclarationsIncompletes;
    }

    public int getNombreTotalDeclarationsHommes() {
        return this.compteurNombreTotalDeclarationsHommes;
    }
    
    public int getNombreTotalDeclarationsFemmes() {
        return this.compteurNombreTotalDeclarationsFemmes;
    }

    public int getNombreTotalDeclarationsSexeInconnu() {
        return this.compteurNombreTotalDeclarationsSexeInconnu;
    }
    
    public int getNombreTotalActivitesValidesDeclarations() {
        return this.compteurNombreTotalActivitesValidesDeclarations;
    }
    
    public int getNombreActivitesValidesCategories() {
        return this.compteurNombreActivitesValidesCategories;
    }

    /* Fichier de sortie */
    @Override
    public String toString() {
        return "Nombre total de déclarations traitées: " + getNombreTotalDeclarationsTraitees() + "\n"
                + "Nombre total de déclarations complètes: " + getNombreTotalDeclarationsCompletes() + "\n"
                + "Nombre total de déclarations incomplètes: " + getNombreTotalDeclarationsIncompletes() + "\n"
                + "Nombre total de déclarations faites par des hommes: " + getNombreTotalDeclarationsHommes() + "\n"
                + "Nombre total de déclarations faites par des femmes: " + getNombreTotalDeclarationsFemmes() + "\n"
                + "Nombre total de déclarations faites par des gens de sexe inconnu: " + getNombreTotalDeclarationsSexeInconnu() + "\n"
                + "Nombre total d'activités valides dans les déclarations: " + getNombreTotalActivitesValidesDeclarations() + "\n"
                + "Nombre d'activités valides par catégorie: " + getNombreActivitesValidesCategories();
    }

    public void ecritureFichierStatistiques() throws IOException {
        FileWriter ecriture = new FileWriter("INF2015_Statistiques.txt");
        ecriture.write(toString());
        ecriture.close();
    }

    /* Reinitialisation des valeurs */
    public void reinitialiserValeursStatistiques() {
        this.compteurNombreTotalDeclarationsTraitees = 0;
        this.compteurNombreTotalDeclarationsCompletes = 0;
        this.compteurNombreTotalDeclarationsIncompletes = 0;
        this.compteurNombreTotalDeclarationsHommes = 0;
        this.compteurNombreTotalDeclarationsFemmes = 0;
        this.compteurNombreTotalDeclarationsSexeInconnu = 0;
        this.compteurNombreTotalActivitesValidesDeclarations = 0;
        this.compteurNombreActivitesValidesCategories = 0;
    }
}
