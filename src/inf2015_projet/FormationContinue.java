/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

//import java.io.FileWriter;
import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//import java.io.IOException;
//import net.sf.json.JSONArray;
/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class FormationContinue {

    // @param args the command line arguments
    public static void main(String[] args) throws IOException {

        String fichierEntree = args[0];
        String fichierSortie = args[1];

        // MÉTHODE DE TEST: Choisir un numéro de fichier entre 0 et 99
        //fichierEntree = forcerFichierEntreePourTesterCategorieIndividuelle(0);
        
        // MÉTHODE DE TEST: Le seul argument qui fonctionne est 0
        //fichierEntree = forcerFichierEntreePourTesterActivitesInvalides(0);

        // Pour pouvoir se référer à un seul fichier de sortie
        fichierSortie = "json/sortie.json";

        // Charger un fichier JSON et l'obtenir sous forme d'objet
        String texteEntree = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
        JSONObject declarationJSON = JSONObject.fromObject(texteEntree);

        String numeroDePermis = declarationJSON.getString("numero_de_permis");
        String cycle = declarationJSON.getString("cycle");
        int heuresTransferees = declarationJSON.getInt("heures_transferees_du_cycle_precedent");

        DeclarationDeFormation declarationDuMembre = new DeclarationDeFormation(numeroDePermis, cycle, heuresTransferees);

        // obtenir le JSONArray qui contient les details des activités
        JSONArray listeActivites = declarationJSON.getJSONArray("activites");

        for (int i = 0; i < listeActivites.size(); i++) {
            // créer un objet ActiviteDeFormation à partir du JSONObject courant
            ActiviteDeFormation uneActivite = new ActiviteDeFormation(declarationDuMembre, listeActivites.getJSONObject(i));
            // ajouter l'activite courante dans la declaration

            //String message = validerActivite(activite)
            declarationDuMembre.ajouterActivite(uneActivite);
        }

        // création et utilisation du validateur...
        ValidateurDeDeclaration validateur = new ValidateurDeDeclaration(declarationDuMembre);
        declarationJSON = validateur.produireRapport();
        System.out.println(declarationJSON);
       
        /* Affichage du fichier de sortie */
        FileWriter sortie = new FileWriter(fichierSortie);
        sortie.write(declarationJSON.toString(2));
        sortie.close();
   }

   public static String forcerFichierEntreePourTesterCategorieIndividuelle(int numeroDeTest) {
        String nomDuFichierVoulu = Integer.toString(numeroDeTest);
        String fichierEntree = "json/testerCategoriesIndividuelles/" + nomDuFichierVoulu + ".json";
        System.out.println(fichierEntree);
        return fichierEntree;
   
        }

   // Le seul argument qui fonctionne pour l'instant est 0
   public static String forcerFichierEntreePourTesterActivitesInvalides(int numeroDeTest) {
        String nomDuFichierVoulu = Integer.toString(numeroDeTest);
        String fichierEntree = "json/testerActivitesInvalides/" + nomDuFichierVoulu + ".json";
        System.out.println(fichierEntree);
        return fichierEntree;
    }
}
