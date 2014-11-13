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

        Statistiques statsPourDeclaration = new Statistiques();
        if (args[0].equals("-S")) {
            statsPourDeclaration.afficherStatistiques();
        } else if (args[0].equals("-SR")) {
            statsPourDeclaration.reinitialiserStatistiques();
        } else {
            String fichierEntree = args[0];
            String fichierSortie = args[1];

            String texteEntree = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
            JSONObject declarationJSON = JSONObject.fromObject(texteEntree);

            statsPourDeclaration.enregistrerTraitementDeDeclaration();
            LecteurDeDeclaration lecteur = new LecteurDeDeclaration(declarationJSON);

            JSONObject sortieJSON;

            if (lecteur.erreurDeFormatDetectee()) {
                System.out.println("Erreur: Éxecution términée, car le fichier contient des données invalides.");
                statsPourDeclaration.enregistrerDeclarationInvalideOuIncomplete();
                sortieJSON = lecteur.produireRapportPourErreurDeFormat();
            } else {
                JSONArray listeActivites = declarationJSON.getJSONArray("activites");

                if (declarationJSON.getString("ordre").equals("architectes")) {
                    Membre architecte = new Architecte(declarationJSON);
                    ajouterArray(architecte, listeActivites);
                    statsPourDeclaration.enregistrerDetailsDuDeclarant(architecte);
                    ValidateurArchitecte validateur = new ValidateurArchitecte(architecte);
                    sortieJSON = validateur.produireRapport();
                    statsPourDeclaration.enregistrerCompletudeDeLaDeclaration(validateur);
                } else if (declarationJSON.getString("ordre").equals("géologues")) {
                    Membre geologue = new Geologue(declarationJSON);
                    ajouterArray(geologue, listeActivites);
                    statsPourDeclaration.enregistrerDetailsDuDeclarant(geologue);
                    ValidateurGeologue validateur = new ValidateurGeologue(geologue);
                    sortieJSON = validateur.produireRapport();
                    statsPourDeclaration.enregistrerCompletudeDeLaDeclaration(validateur);
                } else if (declarationJSON.getString("ordre").equals("psychologues")) {
                    Membre psychologue = new Psychologue(declarationJSON);
                    ajouterArray(psychologue, listeActivites);
                    statsPourDeclaration.enregistrerDetailsDuDeclarant(psychologue);
                    ValidateurPsychologue validateur = new ValidateurPsychologue(psychologue);
                    sortieJSON = validateur.produireRapport();
                    statsPourDeclaration.enregistrerCompletudeDeLaDeclaration(validateur);
                } else {
                    Membre podiatre = new Podiatre(declarationJSON);
                    ajouterArray(podiatre, listeActivites);
                    statsPourDeclaration.enregistrerDetailsDuDeclarant(podiatre);
                    ValidateurPodiatre validateur = new ValidateurPodiatre(podiatre);
                    sortieJSON = validateur.produireRapport();
                    statsPourDeclaration.enregistrerCompletudeDeLaDeclaration(validateur);
                }
            }

            statsPourDeclaration.mettreAJourStatistiquesCumulatives();
            
            FileWriter sortie = new FileWriter(fichierSortie);
            sortie.write(sortieJSON.toString(2));
            sortie.close();
        }
    }

    public static void ajouterArray(Membre membre, JSONArray listeActivites) {
        for (int i = 0; i < listeActivites.size(); i++) {
            JSONObject uneActivite = listeActivites.getJSONObject(i);
            membre.ajouterActivitePourMembre(uneActivite);
        }
    }
}
