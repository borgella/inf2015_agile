/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import declaration.ActiviteDeFormation;
import declaration.DeclarationDeFormation;
import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import validation.ValidateurDeDeclaration;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class FormationContinue {

    // @param args the command line arguments
    public static void main(String[] args) throws IOException {

        String fichierEntree = args[0];
        String fichierSortie = args[1];

        // Charger un fichier JSON et l'obtenir sous forme d'objetif
        String texteEntree = " ";
        texteEntree = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
        JSONObject declarationJSON = JSONObject.fromObject(texteEntree);

        LecteurDeDeclaration lecteur = new LecteurDeDeclaration(declarationJSON);

        JSONObject sortieJSON;

        if (lecteur.erreurDeFormatDetectee()) {
            sortieJSON = lecteur.produireRapportFormatInvalide();
        } else {

            String numeroDePermis = declarationJSON.getString("numero_de_permis");
            String ordre = declarationJSON.getString("ordre");
            String cycle = declarationJSON.getString("cycle");
            int heuresTransferees;
            JSONArray listeActivites = declarationJSON.getJSONArray("activites");

            //DeclarationDeFormation declarationDuMembre = new DeclarationDeFormation(numeroDePermis, cycle, heuresTransferees);
            DeclarationDeFormation declarationDuMembre;

            if (!ordre.equals("architectes")) {
                declarationDuMembre = new DeclarationDeFormation(numeroDePermis, ordre, cycle);
            } else {
                heuresTransferees = declarationJSON.getInt("heures_transferees_du_cycle_precedent");
                declarationDuMembre = new DeclarationDeFormation(numeroDePermis, ordre, cycle, heuresTransferees);
            }

            // obtenir le JSONArray qui contient les details des activités
            

            for (int i = 0; i < listeActivites.size(); i++) {
                ActiviteDeFormation uneActivite = new ActiviteDeFormation(declarationDuMembre, listeActivites.getJSONObject(i));
                //String message = validerActivite(activite)
                declarationDuMembre.ajouterActivite(uneActivite);
            }

            // Valider les données reçues
            ValidateurDeDeclaration validateur = new ValidateurDeDeclaration(declarationDuMembre);
            sortieJSON = validateur.produireRapport();

        }

        System.out.println(sortieJSON);

        // Écrire le fichier de sortie
        FileWriter sortie = new FileWriter(fichierSortie);
        sortie.write(sortieJSON.toString(2));
        sortie.close();

    }
}