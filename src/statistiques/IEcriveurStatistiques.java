package statistiques;

import net.sf.json.JSONObject;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public interface IEcriveurStatistiques {

    JSONObject chargerStatistiquesExistantes();

    void ecrireCumulStatistiques(JSONObject donneesStatistiques);

    JSONObject genererStatistiquesVides();
    
}
