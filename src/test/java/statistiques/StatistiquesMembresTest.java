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
public class StatistiquesMembresTest {

    int codeHomme = 1;
    int codeFemme = 2;
    int codeSexeInconnu = 0;
    
    StatistiquesMembres statistiques;
    IEcriveurStatistiques ecriveurStatistiques;

    @Before
    public void setUp() {
        ecriveurStatistiques = new MockEcriveurStatistiques();
        statistiques = new StatistiquesMembres(ecriveurStatistiques);
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

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsTraitees();
        assertEquals(1, resultat2);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
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

    @Test
    public void testObtenirNombreDeDeclarationsInvalides() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsInvalides();
        assertEquals(0, resultat1);

        statistiques.enregistrerDeclarationInvalide();
        int resultat2 = statistiques.obtenirNombreDeDeclarationsInvalides();
        assertEquals(1, resultat2);

        statistiques.enregistrerDeclarationInvalide();
        statistiques.enregistrerDeclarationInvalide();
        statistiques.enregistrerDeclarationInvalide();
        int resultat3 = statistiques.obtenirNombreDeDeclarationsInvalides();
        assertEquals(4, resultat3);
    }

    @Test
    public void testObtenirNombreDeDeclarationsParHommes() {

        int resultat1 = statistiques.obtenirNombreDeDeclarationsTraiteesParHommes();
        assertEquals(0, resultat1);

        statistiques.enregistrerTraitementDeDeclaration(codeHomme);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsTraiteesParHommes();
        assertEquals(1, resultat2);

        statistiques.enregistrerTraitementDeDeclaration(codeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeHomme);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsTraiteesParHommes();
        assertEquals(3, resultat3);

        statistiques.enregistrerTraitementDeDeclaration(codeFemme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat4 = statistiques.obtenirNombreDeDeclarationsTraiteesParHommes();
        assertEquals(3, resultat4);
    }

    @Test
    public void testObtenirNombreDeDeclarationsParFemmes() {
        int codeHomme = 1;
        int codeFemme = 2;
        int codeSexeInconnu = 0;

        int resultat1 = statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes();
        assertEquals(0, resultat1);

        statistiques.enregistrerTraitementDeDeclaration(codeFemme);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes();
        assertEquals(1, resultat2);

        statistiques.enregistrerTraitementDeDeclaration(codeFemme);
        statistiques.enregistrerTraitementDeDeclaration(codeFemme);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes();
        assertEquals(3, resultat3);

        statistiques.enregistrerTraitementDeDeclaration(codeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat4 = statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes();
        assertEquals(3, resultat4);
    }

    @Test
    public void testObtenirNombreDeDeclarationsParGensDeSexeInconnu() {
        int codeHomme = 1;
        int codeFemme = 2;
        int codeSexeInconnu = 0;

        int resultat1 = statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu();
        assertEquals(0, resultat1);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu();
        assertEquals(1, resultat2);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu();
        assertEquals(3, resultat3);

        statistiques.enregistrerTraitementDeDeclaration(codeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeFemme);
        int resultat4 = statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu();
        assertEquals(3, resultat4);
    }

}
