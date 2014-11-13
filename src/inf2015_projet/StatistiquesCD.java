/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONObject;
import validation.ValidateurArchitecte;
import validation.ValidateurGeologue;
import validation.ValidateurPodiatre;
import validation.ValidateurPsychologue;

/**
 *
 * @author Chelny
 */
public class StatistiquesCD {

    ValidateurArchitecte architecte;
    ValidateurGeologue geologue;
    ValidateurPodiatre podiatre;
    ValidateurPsychologue psychologue;
    private int compteurDeclarationsTraitees;
    private int compteurDeclarationsCompletes;
    private int compteurDeclarationsIncompletes;
    private int compteurDeclarationsHommes;
    private int compteurDeclarationsFemmes;
    private int compteurDeclarationsSexeInconnu;
    private int compteurActivitesValidesDeclarations;
    private int compteurCategorieCours;
    private int compteurCategorieAtelier;
    private int compteurCategorieSeminaire;
    private int compteurCategorieColloque;
    private int compteurCategorieConference;
    private int compteurCategorieLectureDirigee;
    private int compteurCategoriePresentation;
    private int compteurCategorieGroupeDiscussion;
    private int compteurCategorieProjetRecherche;
    private int compteurCategorieRedactionProfessionnelle;

    public StatistiquesCD() {
        compteurDeclarationsTraitees = 0;
        compteurDeclarationsCompletes = 0;
        compteurDeclarationsIncompletes = 0;
        compteurDeclarationsHommes = 0;
        compteurDeclarationsFemmes = 0;
        compteurDeclarationsSexeInconnu = 0;
        compteurActivitesValidesDeclarations = 0;
        compteurCategorieCours = 0;
        compteurCategorieAtelier = 0;
        compteurCategorieSeminaire = 0;
        compteurCategorieColloque = 0;
        compteurCategorieConference = 0;
        compteurCategorieLectureDirigee = 0;
        compteurCategoriePresentation = 0;
        compteurCategorieGroupeDiscussion = 0;
        compteurCategorieProjetRecherche = 0;
        compteurCategorieRedactionProfessionnelle = 0;
    }
    
    public void nombreTotalDeclarationsTraitees(JSONObject declaration) {
        if(declaration != null) {
            compteurDeclarationsTraitees++;
        }
    }
    
    public void nombreTotalDeclarationsCompletes(/*Membre membre*/) {
        if(architecte.produireRapport().containsKey("complet") // membre.produireRapport().containsKey("complet")
                || geologue.produireRapport().containsKey("complet")
                || podiatre.produireRapport().containsKey("complet")
                || psychologue.produireRapport().containsKey("complet")) {
            compteurDeclarationsCompletes++;
        }
    }
    
    public void nombreTotalDeclarationsIncompletes(LecteurDeDeclaration lecteur/*, Membre membre*/) {
        if(lecteur.erreurDeFormatDetectee()
                || architecte.produireRapport().containsKey("erreurs")  // membre.produireRapport().containsKey("complet")
                || geologue.produireRapport().containsKey("erreurs")
                || podiatre.produireRapport().containsKey("erreurs")
                || psychologue.produireRapport().containsKey("erreurs")) {
            compteurDeclarationsIncompletes++;
        }
    }
    
    public void nombreDeclarationsFaitsSelonSexePersonne(JSONObject declaration/*, Membre membre*/) {
        if(declaration.getString("sexe").equals("homme")) { //membre.getSexe().equals("homme")
            compteurDeclarationsHommes++;
        } else if(declaration.getString("sexe").equals("femme")) {  //membre.getSexe().equals("femme")
            compteurDeclarationsFemmes++;
        } else {
            compteurDeclarationsSexeInconnu++;
        }
    }
    
    public void nombreActivitesValidesDeclarations(LecteurDeDeclaration lecteur) {
        if(lecteur.produireRapportPourErreurDeFormat().containsKey("complet")) {
            compteurActivitesValidesDeclarations++;
        }
    }
    
    public void nombreActivitesValidesCategories(LecteurDeDeclaration lecteur, String categorie) {
        if(lecteur.produireRapportPourErreurDeFormat().containsKey("complet")) {
            switch(categorie) {
                case "cours":   
                    compteurCategorieCours++;
                    break;
                case "atelier": 
                    compteurCategorieAtelier++;
                    break;
                case "séminaire":   
                    compteurCategorieSeminaire++;
                    break;
                case "colloque": 
                    compteurCategorieColloque++;
                    break;
                case "conférence":   
                    compteurCategorieConference++;
                    break;
                case "lecture dirigée": 
                    compteurCategorieLectureDirigee++;
                    break;
                case "présentation":   
                    compteurCategoriePresentation++;
                    break;
                case "groupe de discussion": 
                    compteurCategorieGroupeDiscussion++;
                    break;
                case "projet de recherche": 
                    compteurCategorieProjetRecherche++;
                    break;
                case "rédaction professionnelle": 
                    compteurCategorieRedactionProfessionnelle++;
                    break;
                default:        
                    break;
            }
        }
    }

    /* Fichier de sortie */
    @Override
    public String toString() {
        return "Nombre total de déclarations traitées: " + compteurDeclarationsTraitees + "\n"
                + "Nombre total de déclarations complètes: " + compteurDeclarationsCompletes + "\n"
                + "Nombre total de déclarations incomplètes: " + compteurDeclarationsIncompletes + "\n"
                + "Nombre total de déclarations faites par des hommes: " + compteurDeclarationsHommes + "\n"
                + "Nombre total de déclarations faites par des femmes: " + compteurDeclarationsFemmes + "\n"
                + "Nombre total de déclarations faites par des gens de sexe inconnu: " + compteurDeclarationsSexeInconnu + "\n"
                + "Nombre total d'activités valides dans les déclarations: " + compteurActivitesValidesDeclarations + "\n"
                + "Nombre d'activités valides par catégorie:\n"
                + "\tCours: " + compteurCategorieCours + "\n"
                + "\tAtelier: " + compteurCategorieAtelier + "\n"
                + "\tSéminaire: " + compteurCategorieSeminaire + "\n"
                + "\tColloque: " + compteurCategorieColloque + "\n"
                + "\tConference: " + compteurCategorieConference + "\n"
                + "\tLecture Dirigée: " + compteurCategorieLectureDirigee + "\n"
                + "\tPrésentation: " + compteurCategoriePresentation + "\n"
                + "\tGroupe de discussion: " + compteurCategorieGroupeDiscussion + "\n"
                + "\tProjet de recherche: " + compteurCategorieProjetRecherche + "\n"
                + "\tRédaction professionnelle: " + compteurCategorieRedactionProfessionnelle + "\n";
    }

    public void ecritureFichierStatistiques() {
        try {
            FileWriter fichierStatistiques = new FileWriter("INF2015_Statistiques.txt");
            fichierStatistiques.write(toString());
            fichierStatistiques.close();
        } catch(IOException e) {
            e.getMessage();
        }
    }

    /* Reinitialisation des valeurs */
    public void reinitialiserValeursStatistiques() {
        compteurDeclarationsTraitees = 0;
        compteurDeclarationsCompletes = 0;
        compteurDeclarationsIncompletes = 0;
        compteurDeclarationsHommes = 0;
        compteurDeclarationsFemmes = 0;
        compteurDeclarationsSexeInconnu = 0;
        compteurActivitesValidesDeclarations = 0;
        compteurCategorieCours = 0;
        compteurCategorieAtelier = 0;
        compteurCategorieSeminaire = 0;
        compteurCategorieColloque = 0;
        compteurCategorieConference = 0;
        compteurCategorieLectureDirigee = 0;
        compteurCategoriePresentation = 0;
        compteurCategorieGroupeDiscussion = 0;
        compteurCategorieProjetRecherche = 0;
        compteurCategorieRedactionProfessionnelle = 0;
        
        System.out.println("Statistiques: valeurs réinitialisées!");
    }
}
