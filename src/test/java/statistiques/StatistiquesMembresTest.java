package statistiques;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

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
        statistiques = new StatistiquesMembres(null);
    }

    @After
    public void tearDown() {
        ecriveurStatistiques = null;
        statistiques = null;
    }

    @Test
    public void testEnregistrerNombreDeDeclarationsTraitees() {

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
    public void testEnregistrerNombreTotalDeDeclarationsValidesEtCompletes() {
        int resultat1 = statistiques.obtenirNombreTotalDeDeclarationsValidesEtCompletes();
        assertEquals(0, resultat1);

        statistiques.enregistrerCompletudeDeDeclarationValide(true, "architectes");
        int resultat2 = statistiques.obtenirNombreTotalDeDeclarationsValidesEtCompletes();
        assertEquals(1, resultat2);

        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");
        int resultat3 = statistiques.obtenirNombreTotalDeDeclarationsValidesEtCompletes();
        assertEquals(3, resultat3);
    }
    
    @Test
    public void testEnregistrerNombreDeDeclarationsValidesEtCompletesSelonOrdre() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes("architectes");
        assertEquals(0, resultat1);

        statistiques.enregistrerCompletudeDeDeclarationValide(true, "architectes");
        int resultat2 = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes("architectes");
        assertEquals(1, resultat2);

        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");
        int resultat3 = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes("podiatres");
        assertEquals(2, resultat3);
    }

    @Test
    public void testEnregistrerNombreTotalDeDeclarationsValidesEtIncompletes() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre("architectes");
        assertEquals(0, resultat1);

        statistiques.enregistrerCompletudeDeDeclarationValide(false, "architectes");
        int resultat2 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre("architectes");
        assertEquals(1, resultat2);

        statistiques.enregistrerCompletudeDeDeclarationValide(true, "architectes");
        int resultat3 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre("architectes");
        assertEquals(1, resultat3);

        statistiques.enregistrerCompletudeDeDeclarationValide(false, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "podiatres");
        int resultat4 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre("podiatres");
        assertEquals(2, resultat4);
    }

    @Test
    public void testEnregistrerNombreDeDeclarationsValidesEtIncompletesSelonOrdre() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre("architectes");
        assertEquals(0, resultat1);

        statistiques.enregistrerCompletudeDeDeclarationValide(false, "architectes");
        int resultat2 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre("architectes");
        assertEquals(1, resultat2);

        statistiques.enregistrerCompletudeDeDeclarationValide(false, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "podiatres");
        int resultat3 = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre("podiatres");
        assertEquals(2, resultat3);
    }
    
    @Test
    public void testEnregistrerNombreDeDeclarationsInvalides() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsInvalidesOuIncompletes();
        assertEquals(0, resultat1);

        statistiques.enregistrerDeclarationInvalide(codeSexeInconnu);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsInvalidesOuIncompletes();
        assertEquals(1, resultat2);

        statistiques.enregistrerDeclarationInvalide(codeSexeInconnu);
        statistiques.enregistrerDeclarationInvalide(codeSexeInconnu);
        statistiques.enregistrerDeclarationInvalide(codeSexeInconnu);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsInvalidesOuIncompletes();
        assertEquals(4, resultat3);
    }
    
    @Test
    public void testEnregistrerDeclarationAyantUnNumeroDePermisInvalide() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsAvecNumeroDePermisInvalide();
        assertEquals(0, resultat1);

        statistiques.enregistrerDeclarationsAvecNumeroDePermisInvalide();
        int resultat2 = statistiques.obtenirNombreDeDeclarationsAvecNumeroDePermisInvalide();
        assertEquals(1, resultat2);

        statistiques.enregistrerDeclarationsAvecNumeroDePermisInvalide();
        statistiques.enregistrerDeclarationsAvecNumeroDePermisInvalide();
        int resultat3 = statistiques.obtenirNombreDeDeclarationsAvecNumeroDePermisInvalide();
        assertEquals(3, resultat3);
    }

    @Test
    public void testEnregistrerDeclarationParUnHomme() {

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
    public void testEnregistrerDeclarationParUneFemme() {

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
    public void testEnregistrerDeclarationParUnePersonneDeSexeInconnu() {

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
