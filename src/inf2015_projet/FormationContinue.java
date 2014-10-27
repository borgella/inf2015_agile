package inf2015_projet;

import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * * * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class FormationContinue {

    // @param args the command line arguments
    public static void main(String[] args) throws IOException {

        String fichierEntree = args[0];
        String fichierSortie = args[1];

        String texteEntree;
        texteEntree = FileReader.loadFileIntoString(fichierEntree, "UTF-8");

        JSONObject declarationJSON = JSONObject.fromObject(texteEntree);

        LecteurDeDeclaration lecteur = new LecteurDeDeclaration(declarationJSON);

        //TODO: ajouter les fonctions aux 'ifs' pour pouvoir enlever null
        JSONObject sortieJSON = null;

        if (lecteur.erreurDeFormatDetectee()) {
            // DEBOGGAGE
            System.out.println("ERREUR DE FORMAT");
            sortieJSON = lecteur.produireRapportPourErreurDeFormat();
        } else {
            JSONArray listeActivites = declarationJSON.getJSONArray("activites");

            if (declarationJSON.getString("ordre").equals("architectes")) {
                Architecte architecte = new Architecte(declarationJSON);
                for (int i = 0; i < listeActivites.size(); i++) {
                    JSONObject uneActivite = listeActivites.getJSONObject(i);
                    architecte.ajouterActivite(uneActivite);
                }
                ValidateurArchitecte validateur = new ValidateurArchitecte(architecte);
                sortieJSON = validateur.produireRapport();
            } else if (declarationJSON.getString("ordre").equals("géologues")) {
                Geologue geologue = new Geologue(declarationJSON);
                for (int i = 0; i < listeActivites.size(); i++) {
                    JSONObject uneActivite = listeActivites.getJSONObject(i);
                    geologue.ajouterActivitePourGeologue(uneActivite);
                }
            } else {
                Psychologue psychologue = new Psychologue(declarationJSON);
                for (int i = 0; i < listeActivites.size(); i++) {
                    JSONObject uneActivite = listeActivites.getJSONObject(i);
                    psychologue.ajouterActivitePourPsychologue(uneActivite);
                }
            }
        }

        System.out.println(sortieJSON);
        
        // Écrire le fichier de sortie 
        FileWriter sortie = new FileWriter(fichierSortie);
        sortie.write(sortieJSON.toString(2));
        sortie.close();
    }
}
