package statistiques;

import net.sf.json.JSONObject;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class MockEcriveurStatistiques implements IEcriveurStatistiques {
    JSONObject donneesStatistiquesExistantes;
    
    public MockEcriveurStatistiques() {
        this(new JSONObject());
    }
    
    public MockEcriveurStatistiques(JSONObject donneesExistantes) {
        donneesStatistiquesExistantes = donneesExistantes;
    }

    @Override
    public JSONObject chargerStatistiquesExistantes() {
        return donneesStatistiquesExistantes;
    }

    @Override
    public void ecrireCumulStatistiques(JSONObject donneesStatistiques) {
      donneesStatistiquesExistantes = donneesStatistiques;
    }

    @Override
    public JSONObject genererStatistiquesVides() {
        return new JSONObject();
    }
}