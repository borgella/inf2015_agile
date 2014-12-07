package statistiques;

import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import professionnels.*;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class StatistiquesMembresTest {

    int codeSexeHomme = 1;
    int codeSexeFemme = 2;
    int codeSexeInconnu = 0;

    StatistiquesMembres statistiques;

    @Before
    public void setUp() {
        statistiques = new StatistiquesMembres(null);
    }

    @After
    public void tearDown() {
        statistiques = null;
    }
    
    private JSONObject creerActiviteSelonCategoriePourArchitecte2012A2014(String categorie) {
        JSONObject activite = new JSONObject();
        activite.accumulate("description", "activite numéro ");
        activite.accumulate("categorie", categorie);
        activite.accumulate("heures", 3);
        activite.accumulate("date", "2013-01-01");
        return activite;
    }

    @Test
    public void testEnregistrerTraitementsDeDeclarations() {

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
    public void testEnregistrerDeclarationsValidesEtCompletes() {
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
    public void testEnregistrerDeclarationsValidesEtCompletesSelonOrdre() {
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
    public void testEnregistrerDeclarationsValidesEtIncompletes() {
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
    public void testEnregistrerDeclarationsValidesEtIncompletesSelonOrdre() {
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
    public void testEnregistrerDeclarationsInvalides() {
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
    public void testEnregistrerDeclarationsParDesHommes() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsTraiteesParHommes();
        assertEquals(0, resultat1);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsTraiteesParHommes();
        assertEquals(1, resultat2);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsTraiteesParHommes();
        assertEquals(3, resultat3);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat4 = statistiques.obtenirNombreDeDeclarationsTraiteesParHommes();
        assertEquals(3, resultat4);
    }

    @Test
    public void testEnregistrerDeclarationsParDesFemmes() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes();
        assertEquals(0, resultat1);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes();
        assertEquals(1, resultat2);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes();
        assertEquals(3, resultat3);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat4 = statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes();
        assertEquals(3, resultat4);
    }

    @Test
    public void testEnregistrerDeclarationsParDesGensDeSexeInconnu() {
        int resultat1 = statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu();
        assertEquals(0, resultat1);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat2 = statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu();
        assertEquals(1, resultat2);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        int resultat3 = statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu();
        assertEquals(3, resultat3);

        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        int resultat4 = statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu();
        assertEquals(3, resultat4);
    }

    @Test
    public void testEnregistrerNombresTotauxActivitesValides() {
        String cycleValideArchitecte = "2012-2014";
        Membre membre = new Architecte(cycleValideArchitecte);
        
        assertEquals(0, statistiques.obtenirNombreTotalActivitesValides());
        
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("cours"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("cours"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("atelier"));
        
        statistiques.enregistrerNombreTotalActivitesValides(membre);
        assertEquals(3, statistiques.obtenirNombreTotalActivitesValides());
    }

    @Test
    public void testEnregistrerNombresActivitesValidesParCategories() {
        String cycleValideArchitecte = "2012-2014";
        Membre membre = new Architecte(cycleValideArchitecte);
        
        assertEquals(0, statistiques.obtenirActivitesValidesParCategorie("cours"));
        assertEquals(0, statistiques.obtenirActivitesValidesParCategorie("atelier"));
        assertEquals(0, statistiques.obtenirActivitesValidesParCategorie("séminaire"));       
        
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("cours"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("atelier"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("atelier"));
        
        
        statistiques.enregistrerNombreActivitesValidesParCategorieIndividuelle(membre);
        assertEquals(1, statistiques.obtenirActivitesValidesParCategorie("cours"));
        assertEquals(2, statistiques.obtenirActivitesValidesParCategorie("atelier"));
        assertEquals(0, statistiques.obtenirActivitesValidesParCategorie("séminaire"));
    }

    @Test
    public void testEnregistrerDeclarationsAvecNumeroDePermisInvalide() {
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

}
