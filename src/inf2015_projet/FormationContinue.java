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
        String texteEntree = " ";
        // Charger un fichier JSON et l'obtenir sous forme d'objetif
        texteEntree   = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
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

        // Écrire le fichier de sortie
        FileWriter sortie = new FileWriter(fichierSortie);
        sortie.write(declarationJSON.toString(2));
        sortie.close();

    }
}
