package inf2015_projet;

import professionnels.*;
import validation.*;
import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class FormationContinue {
// @param args the command line arguments

    public static void main(String[] args) throws IOException {
        Statistiques statsPourDeclaration = new Statistiques();
        if (args[0].equals("-S")) {
            statsPourDeclaration.afficherStatistiques();
        } else if (args[0].equals("-SR")) {
            statsPourDeclaration.reinitialiserStatistiques();
        } else {
            JSONArray listeActivites = declarationJSON.getJSONArray("activites");

            if (declarationJSON.getString("ordre").equals("architectes")) {
                Membre architecte = new Architecte(declarationJSON);
                ajouterArray(architecte,listeActivites);
                ValidateurArchitecte validateur = new ValidateurArchitecte(architecte);
                sortieJSON = validateur.produireRapport();
            } else if (declarationJSON.getString("ordre").equals("géologues")) {
                Membre geologue = new Geologue(declarationJSON);
                ajouterArray(geologue,listeActivites);
                ValidateurGeologue validateur = new ValidateurGeologue(geologue);
                sortieJSON = validateur.produireRapport();
            } else if (declarationJSON.getString("ordre").equals("psychologues")) {
                Membre psychologue = new Psychologue(declarationJSON);
                ajouterArray(psychologue,listeActivites);
                ValidateurPsychologue validateur = new ValidateurPsychologue(psychologue);
                sortieJSON = validateur.produireRapport();
            }else{
                Membre podiatre = new Podiatre(declarationJSON);
                ajouterArray(podiatre,listeActivites);
                ValidateurPodiatre validateur = new ValidateurPodiatre(podiatre);
                sortieJSON = validateur.produireRapport(); 
            }
            statsPourDeclaration.mettreAJourStatistiquesCumulatives();
            // Écrire le fichier de sortie
            FileWriter sortie = new FileWriter(fichierSortie);
            sortie.write(sortieJSON.toString(2));
            sortie.close();
        }
        // Écrire le fichier de sortie 
        FileWriter sortie = new FileWriter(fichierSortie);
        sortie.write(sortieJSON.toString(2));
        sortie.close();
    }
    
    public static void ajouterArray(Membre membre,JSONArray listeActivites){
        for (int i = 0; i < listeActivites.size(); i++) {
                JSONObject uneActivite = listeActivites.getJSONObject(i);
                membre.ajouterActivitePourMembre(uneActivite);
         }
    }
}
