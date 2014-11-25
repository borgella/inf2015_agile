package statistiques;

import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import professionnels.*;
import validation.*;

/**
 *
 * @author User
 */
public class AccumulateurStatistiquesTest {
    AccumulateurStatistiques statistiques;
    IEcriveurStatistiques ecriveurStatistiques;
    
    public AccumulateurStatistiquesTest() {
    }
    
    @Before
    public void setUp() {
        ecriveurStatistiques = new MockEcriveurStatistiques();
        statistiques = new AccumulateurStatistiques(ecriveurStatistiques);
    }
    
    @After
    public void tearDown() {
        ecriveurStatistiques = null;
        statistiques = null;
    }

    @Test
    public void testEnregistrerStatistiquesPourDeclarationsValidesParArchitectes() {
        
        int resultat1 = statistiques.obtenirNombreDeclarationsValidesPourArchitectes();
        assertEquals(0, resultat1);
        
        statistiques.enregistrerDetailsDuDeclarant(new Architecte());
        int resultat2 = statistiques.obtenirNombreDeclarationsValidesPourArchitectes();
        assertEquals(1, resultat2);
        
        statistiques.enregistrerDetailsDuDeclarant(new Architecte());
        statistiques.enregistrerDetailsDuDeclarant(new Architecte());
        statistiques.enregistrerDetailsDuDeclarant(new Architecte());
        int resultat3 = statistiques.obtenirNombreDeclarationsValidesPourArchitectes();
        assertEquals(3, resultat3);
    }
}
