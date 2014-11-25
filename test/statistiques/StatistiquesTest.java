package statistiques;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import professionnels.*;

/**
 *
 * @author User
 */
public class StatistiquesTest {

    Statistiques statistiques;
    IEcriveurStatistiques ecriveurStatistiques;

    public StatistiquesTest() {
    }

    @Before
    public void setUp() {
        ecriveurStatistiques = new MockEcriveurStatistiques();
        statistiques = new Statistiques(ecriveurStatistiques);
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
    public void testEnregistrerNombreDeDeclarationsValidesEtCompletes() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes();
        assertEquals(0, resultat1);
        
        statistiques.enregistrerCompletudeDeDeclarationValide(true);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes();
        assertEquals(1, resultat2);

        statistiques.enregistrerCompletudeDeDeclarationValide(false);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes();
        assertEquals(1, resultat3);
        
        statistiques.enregistrerCompletudeDeDeclarationValide(true);
        statistiques.enregistrerCompletudeDeDeclarationValide(false);
        int resultat4 = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes();
        assertEquals(2, resultat4);
    }
    
    @Test
    public void testEnregistrerNombreDeDeclarationsValidesEtIncompletes() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletes();
        assertEquals(0, resultat1);
        
        statistiques.enregistrerCompletudeDeDeclarationValide(true);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletes();
        assertEquals(0, resultat2);

        statistiques.enregistrerCompletudeDeDeclarationValide(false);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletes();
        assertEquals(1, resultat3);
        
        statistiques.enregistrerCompletudeDeDeclarationValide(true);
        statistiques.enregistrerCompletudeDeDeclarationValide(false);
        int resultat4 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletes();
        assertEquals(2, resultat4);
    }
    
    

}