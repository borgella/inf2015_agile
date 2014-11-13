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

    public static void main(String[] args) throws IOException {

        String fichierEntree = args[0];
        String fichierSortie = args[1];

        String texteEntree;
        texteEntree = FileReader.loadFileIntoString(fichierEntree, "UTF-8");

        JSONObject declarationJSON = JSONObject.fromObject(texteEntree);

        LecteurDeDeclaration lecteur = new LecteurDeDeclaration(declarationJSON);

        JSONObject sortieJSON;

        if (lecteur.erreurDeFormatDetectee()) {
            System.out.println("Erreur: Éxecution términée, car le fichier contient des données invalides.");
            sortieJSON = lecteur.produireRapportPourErreurDeFormat();
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

        }

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
