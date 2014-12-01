package statistiques;

import inf2015_projet.FileReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import net.sf.json.JSONObject;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class EcriveurStatistiques implements IEcriveurStatistiques {

    private String fichierStatistiques;

    EcriveurStatistiques() {
        fichierStatistiques = "cumulStatistiques/donneesStatistiques.json";
    }

    EcriveurStatistiques(String fichierStatistiques) {
        this.fichierStatistiques = fichierStatistiques;
    }

    @Override
    public JSONObject chargerStatistiquesExistantes() {
        JSONObject donneesStatistiques;
        try {
           String donneesBrutesPourStatistiques = FileReader.loadFileIntoString(fichierStatistiques, "UTF-8");
           donneesStatistiques = JSONObject.fromObject(donneesBrutesPourStatistiques);
        } catch (IOException e) {
            System.out.println("Le fichier des statistiques n'existe pas ou est inaccessible; "
                    + "un nouveau fichier sera généré.");
            donneesStatistiques = new JSONObject();
        }
        return donneesStatistiques;
    }

   
    @Override
    public void ecrireCumulStatistiques(JSONObject donneesStatistiques) {
        try {
            Writer ecritureAuFichierStatistiques = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(fichierStatistiques), "UTF-8"));
            ecritureAuFichierStatistiques.write(donneesStatistiques.toString(2));
            ecritureAuFichierStatistiques.close();
        } catch (IOException e) {
            System.out.println("Erreur: les nouvelles statistiques n'ont pas pu être sauvegardées.");
        }  
    }

}