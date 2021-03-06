package inf2015_projet;

import statistiques.StatistiquesMembres;
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

        StatistiquesMembres statsPourDeclaration = new StatistiquesMembres();
        if (args[0].equals("-S")) {
            statsPourDeclaration.afficherStatistiques();
        } else if (args[0].equals("-SR")) {
            statsPourDeclaration.reinitialiserStatistiques();
        } else {
            String fichierEntree = args[0];
            String fichierSortie = args[1];

            String texteEntree = FileReader.loadFileIntoString(fichierEntree, "UTF-8");
            JSONObject declarationJSON = JSONObject.fromObject(texteEntree);

            LecteurDeDeclaration lecteur = new LecteurDeDeclaration(declarationJSON);
            int sexeDuDeclarant = lecteur.extraireSexe();
            statsPourDeclaration.enregistrerTraitementDeDeclaration(sexeDuDeclarant);

            JSONObject sortieJSON;

            if (lecteur.erreurDeFormatDetectee()) {
                System.out.println("Erreur: Exécution terminée, car le fichier contient des données invalides.");
                sortieJSON = lecteur.produireRapportPourErreurDeFormat();
                
                statsPourDeclaration.enregistrerDeclarationInvalide(sexeDuDeclarant);
                if (!lecteur.formatAcceptePourNumeroDePermis()) {
                    statsPourDeclaration.enregistrerDeclarationAvecNumeroDePermisInvalide();
                }
                
            } else {
                JSONArray listeActivites = declarationJSON.getJSONArray("activites");
                
                Membre membre = Membre.genererMembre(declarationJSON);
                ajouterArray(membre, listeActivites);
                
                statsPourDeclaration.enregistrerActivitesValidesParCategorie(membre);
                
                Validateur validateur = Validateur.genererValidateur(membre);
                sortieJSON = validateur.produireRapport();
                
                statsPourDeclaration.enregistrerCompletudeDeDeclarationValide(validateur.formationComplete(), membre.getOrdre());
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
