/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONArray;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class FormationContinue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String fichierEntree = args[0];
        String fichierDestination = args[1];
        JSONArray tabEntree;
        int nombreEntrees;
        int nombreActivites;
        String numeroDePermis;
        String cycle;
        int heuresTransferees;
        String description;
        
        // I- Charger un fichier JSON et l'obtenir sous forme de String
        String entree = FileReader.loadFileIntoString("json/entree.json", "ISO-8601");
        JSONArray tabEntree = JSONArray.fromObject(entree);
        
        // ici on va extraire les donnees du json.
        
        // II- Vérifications
        int heuresTransferees = 0;
        int tailleEntree = tabEntree.size();
        int totalHeures = 0;
        String categorie;
        int heures;
        String date;
        String[] regroupementActivites;
        DeclarationDeFormation declaration;
        Validateur validateur = new Validateur();
        Activite activite;
        /*int totalHeures;
        String[] categoriesReconnues = {"cours", 
                                        "atelier", 
                                        "séminaire",
                                        "colloque", 
                                        "conférence",
                                        "lecture dirigée", 
                                        "présentation", 
                                        "groupe de discussion", 
                                        "projet de recherche", 
                                        "rédaction professionnelle"};
        String messageErreur;*/
        String sortie;
        
        
        
        /*** I- Charger un fichier JSON et l'obtenir sous forme de String ***/
        fichierEntree = FileReader.loadFileIntoString("json/entree.json", "ISO-8601");
        tabEntree = JSONArray.fromObject(fichierEntree);
        
        nombreEntrees = tabEntree.size();    // obtenir le nombre de déclarations d'activités de formation continue
        
        // Obtenir chaque information de la chaine de caracteres JSON
        for(int i = 0; i < nombreEntrees; i++) {
            nombreActivites = tabEntree.getJSONObject(i).getJSONArray("activites").size();  // obtenir le nombre d'activités par membre
            
            numeroDePermis = tabEntree.getJSONObject(i).getString("numero_de_permis");
            cycle = tabEntree.getJSONObject(i).getString("cycle");
            heuresTransferees = tabEntree.getJSONObject(i).getInt("heures_transferees_du_cycle_precedent");
            
            // Appel a la DeclarationDeFormation
            declaration = new DeclarationDeFormation(numeroDePermis, cycle, heuresTransferees);
            
            for(int j = 0; j < nombreActivites; j++) {
                description = tabEntree.getJSONObject(i).getJSONArray("activites").getJSONObject(j).getString("description");
                categorie = tabEntree.getJSONObject(i).getJSONArray("activites").getJSONObject(j).getString("categorie");
                heures = tabEntree.getJSONObject(i).getJSONArray("activites").getJSONObject(j).getInt("heures");
                date = tabEntree.getJSONObject(i).getJSONArray("activites").getJSONObject(j).getString("date");
                
                // Regrouper tous les information de l'activite dans un tableau
                regroupementActivites = new String[] {description, categorie, Integer.toString(heures), date};
               
                // Appel a l'Activite
                activite = new Activite(numeroDePermis, cycle, heuresTransferees, regroupementActivites);
            }
        }
        
        
        /***  II- Validations ***/
        /*heures = 0;
        totalHeures = 0;*/
        
        /* 1)   Cycle 2012-2014
                Autre cycle => MESSAGE D'ERREUR */
        
        /*for(int i = 0; i < tailleEntree; i++) {
            if(tabEntree.getJSONObject(i).get("cycle") == "2012-2014") {
                //autres validations (2 à 11) ici!
            } else {
                //message d'erreur
            }
        }*/
        
        /* 2)   Activité complétée entre 1er avril 2012 et le 1er avril 2014 inclusivement
                À l'extérieur des intervalles => MESSAGE D'ERREUR + activité ignorée des calculs
                Les dates sont indiquées en format ISO-8601 */
        
        /* 3)   Activité appartenant à une des catégories reconnues
                Activité non reconnue => MESSAGE D'ERREUR + activité ignorée des calculs */
        
        /*for(int i = 0; i < tailleEntree; i++) {
            for(int j = 0; j < tabEntree.getJSONObject(i).getJSONArray("activites").size(); j++) {  //taille du tableau «activités»
                for(int k = 0; k < categoriesReconnues.length; k++) {    //taille du tableau des catégories
                    categorie = tabEntree.getJSONObject(i).getJSONArray("activites").getJSONObject(j).getString("categorie");
                    
                    if(categorie.equals(categoriesReconnues[k])) {

                    } else {
                        //message d'erreur + ignoré
                    }
                }
            }   
        }*/
        
        /* 4)   « heures_transferees_du_cycle_precedent » à utiliser dans le calcul d'heures courrant
                Nombre positif et inférieur à 7
                Si nombre supérieur à 7 => MESSAGE D'ERREUR + 7 heures seulement seront calculés */   
        
        /*for(int i = 0; i < tailleEntree; i++) {
            heuresTransferees = tabEntree.getJSONObject(i).getInt("heures_transferees_du_cycle_precedent");
            
            if(heuresTransferees > 0) {
                if(heuresTransferees < 7) {
                    totalHeures += heuresTransferees;
                } else {
                    //message d'erreur
                    totalHeures += 7;
                }
            }
        }*/
        
        /* 5)   Minimum 40 heures déclarées dans le cycle, aucun max
                Inférieur à 40 heures => MESSAGE D'ERREUR */
        
        /* 6)   Minimum 17 heures décalrées dans les catégories suivantes:
                cours, atelier, séminaire, colloque, conférence, lecture dirigée
                En dessous de 17 heures pour l'ensemble des catégories => MESSAGE D'ERREUR + 
                heures transférées du cycle précédent sont comptabilisées dans cette somme */
        
        /* 7)   Maximum 23 heures décalrées dans la catégories présentation
                Au-delà de 23 heures, les heures suppl. seront ignorées mais, aucun message d'erreur */
        
        /* 8)   Maximum de 17 heures déclarées dans la catégorie groupe de discussion
                Au-delà de 17 heures, les heures suppl. seront ignorées mais, aucun message d'erreur */
        
        /* 9)   Maximum de 23 heures déclarées dans la catégorie projet de recherche
                Au-delà de 23 heures, les heures suppl. seront ignorées mais, aucun message d'erreur */
        
        /* 10)  Maximum de 17 heures déclarées dans la catégorie rédaction
                Au-delà de 17 heures, les heures suppl. seront ignorées mais, aucun message d'erreur */
        
        /* 11)  Les heures d'une activité doivent être supérieures ou égales à 1 */
        
        /*for(int i = 0; i < tailleEntree; i++) {
            for(int j = 0; j < tabEntree.getJSONObject(i).getJSONArray("activites").size(); j++) {  //taille du tableau «activités»
                if(tabEntree.getJSONObject(i).getJSONArray("activites").getJSONObject(j).getInt("heures") >= 1) {
                
                }
            }   
        }*/
        
        
        
        /***  III- Affichage du fichier de sortie ***/
        
        FileWriter fichierSortie = new FileWriter(fichierDestination);
        fichierSortie.write(validateur.produireRapport());
        fichierSortie.close();
    }
    
}
