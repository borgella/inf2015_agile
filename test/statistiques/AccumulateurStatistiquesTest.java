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
    public void testObtenirNombreDeDeclarationsTraitees() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsTraitees();
        assertEquals(0, resultat1);
        
        statistiques.enregistrerTraitementDeDeclaration();
        int resultat2 = statistiques.obtenirNombreDeDeclarationsTraitees();
        assertEquals(1, resultat2);
        
        statistiques.enregistrerTraitementDeDeclaration();
        statistiques.enregistrerTraitementDeDeclaration();
        statistiques.enregistrerTraitementDeDeclaration();
        int resultat3 = statistiques.obtenirNombreDeDeclarationsTraitees();
        assertEquals(4, resultat3);
    }
    
    @Test
    public void testObtenirUneStatistiqueParticuliere() {
        
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
