package statistiques;

import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import professionnels.Architecte;
import professionnels.Membre;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class AfficheurStatistiquesMembresTest {

    int codeSexeHomme = 1;
    int codeSexeFemme = 2;
    int codeSexeInconnu = 0;

    AfficheurStatistiquesMembres afficheur;
    StatistiquesMembres statistiques;

    @Before
    public void setUp() {
        statistiques = new StatistiquesMembres(null);
        afficheur = new AfficheurStatistiquesMembres(statistiques);
    }

    @After
    public void tearDown() {
        afficheur = null;
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
    public void testMessageNombreDeclarationsTraitees() {
        assertEquals("Nombre total de déclarations traitées: 0", afficheur.messageNombreDeclarationsTraitees());

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        assertEquals("Nombre total de déclarations traitées: 1", afficheur.messageNombreDeclarationsTraitees());

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        assertEquals("Nombre total de déclarations traitées: 3", afficheur.messageNombreDeclarationsTraitees());
    }

    @Test
    public void testMessageNombreDeclarationsIncompletesOuInvalides() {
        assertEquals("Nombre total de déclarations incomplètes ou invalides: 0",
                afficheur.messageNombreDeclarationsIncompletesOuInvalides());

        statistiques.enregistrerDeclarationInvalide(codeSexeInconnu);
        assertEquals("Nombre total de déclarations incomplètes ou invalides: 1",
                afficheur.messageNombreDeclarationsIncompletesOuInvalides());

        statistiques.enregistrerDeclarationInvalide(codeSexeInconnu);
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "architectes");
        assertEquals("Nombre total de déclarations incomplètes ou invalides: 3",
                afficheur.messageNombreDeclarationsIncompletesOuInvalides());
    }

    @Test
    public void testMessageNombreDeclarationsValidesEtCompletes() {
        assertEquals("Nombre total de déclarations complètes: 0",
                afficheur.messageNombreDeclarationsValidesEtCompletes());

        statistiques.enregistrerCompletudeDeDeclarationValide(true, "architectes");
        assertEquals("Nombre total de déclarations complètes: 1",
                afficheur.messageNombreDeclarationsValidesEtCompletes());

        statistiques.enregistrerCompletudeDeDeclarationValide(true, "architectes");
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");
        assertEquals("Nombre total de déclarations complètes: 3",
                afficheur.messageNombreDeclarationsValidesEtCompletes());
    }

    @Test
    public void testMessageNombreDeclarationsFaitesParHommes() {
        assertEquals("Nombre total de déclarations faites par des hommes: 0",
                afficheur.messageNombreDeclarationsFaitesParHommes());

        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        assertEquals("Nombre total de déclarations faites par des hommes: 1",
                afficheur.messageNombreDeclarationsFaitesParHommes());

        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        assertEquals("Nombre total de déclarations faites par des hommes: 3",
                afficheur.messageNombreDeclarationsFaitesParHommes());
    }

    @Test
    public void testMessageNombreDeclarationsFaitesParFemmes() {
        assertEquals("Nombre total de déclarations faites par des femmes: 0",
                afficheur.messageNombreDeclarationsFaitesParFemmes());

        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        assertEquals("Nombre total de déclarations faites par des femmes: 1",
                afficheur.messageNombreDeclarationsFaitesParFemmes());

        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeFemme);
        assertEquals("Nombre total de déclarations faites par des femmes: 3",
                afficheur.messageNombreDeclarationsFaitesParFemmes());
    }

    @Test
    public void testMessageNombreDeclarationsParGensDeSexeInconnu() {
        assertEquals("Nombre total de déclarations faites par des gens de sexe inconnu: 0",
                afficheur.messageNombreDeclarationsParGensDeSexeInconnu());

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        assertEquals("Nombre total de déclarations faites par des gens de sexe inconnu: 1",
                afficheur.messageNombreDeclarationsParGensDeSexeInconnu());

        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeHomme);
        statistiques.enregistrerTraitementDeDeclaration(codeSexeInconnu);
        assertEquals("Nombre total de déclarations faites par des gens de sexe inconnu: 3",
                afficheur.messageNombreDeclarationsParGensDeSexeInconnu());
    }

    @Test
    public void testMessageNombreTotalActivitesValidesDeclarees() {
        String cycleValideArchitecte = "2012-2014";
        Membre membre = new Architecte(cycleValideArchitecte);
        
        assertEquals("Nombre total d'activités valides dans les déclarations: 0", 
                afficheur.messageNombreTotalActivitesValidesDeclarees());
        
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("cours"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("cours"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("atelier"));
        
        statistiques.enregistrerNombreTotalActivitesValides(membre);
        assertEquals("Nombre total d'activités valides dans les déclarations: 3", 
                afficheur.messageNombreTotalActivitesValidesDeclarees());
    }

    @Test
    public void testMessageNombreActivitesValidesDeclareesSelonCategorie() {
        String cycleValideArchitecte = "2012-2014";
        Membre membre = new Architecte(cycleValideArchitecte);
        
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("cours"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("atelier"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("atelier"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("présentation"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("présentation"));
        membre.ajouterActivitePourMembre(creerActiviteSelonCategoriePourArchitecte2012A2014("présentation"));
        
        statistiques.enregistrerNombreActivitesValidesParCategorieIndividuelle(membre);

        String message = "Nombre d'activités valides par catégorie: "
                + '\n' + "    " + '\"' + "cours" + '\"' + ": 1"
                + '\n' + "    " + '\"' + "atelier" + '\"' + ": 2"
                + '\n' + "    " + '\"' + "séminaire" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "colloque" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "conférence" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "lecture dirigée" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "présentation" + '\"' + ": 3"
                + '\n' + "    " + '\"' + "groupe de discussion" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "projet de recherche" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "rédaction professionnelle" + '\"' + ": 0";
        
        assertEquals(message, afficheur.messageNombreActivitesValidesDeclareesSelonCategorie());
    }

    @Test
    public void testMessageNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre() {
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "architectes");
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "architectes");
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");

        String message = "Nombre de déclarations valides et complètes par ordre: "
                + '\n' + "    " + '\"' + "architectes" + '\"' + ": 2"
                + '\n' + "    " + '\"' + "géologues" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "psychologues" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "podiatres" + '\"' + ": 1";

        assertEquals(message, afficheur.messageNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre());
    }

    @Test
    public void testMessageNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre() {
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "architectes");
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "architectes");
        statistiques.enregistrerCompletudeDeDeclarationValide(true, "podiatres");
        statistiques.enregistrerCompletudeDeDeclarationValide(false, "podiatres");

        String message = "Nombre de déclarations valides et incomplètes par ordre: "
                + '\n' + "    " + '\"' + "architectes" + '\"' + ": 2"
                + '\n' + "    " + '\"' + "géologues" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "psychologues" + '\"' + ": 0"
                + '\n' + "    " + '\"' + "podiatres" + '\"' + ": 1";

        assertEquals(message, afficheur.messageNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre());
    }

    @Test
    public void testMessageNombreDeDeclarationsAvecNumeroDePermisInvalide() {
        assertEquals("Nombre de déclarations soumises avec un numéro de permis invalide: 0",
                afficheur.messageNombreDeDeclarationsAvecNumeroDePermisInvalide());

        statistiques.enregistrerDeclarationAvecNumeroDePermisInvalide();
        assertEquals("Nombre de déclarations soumises avec un numéro de permis invalide: 1",
                afficheur.messageNombreDeDeclarationsAvecNumeroDePermisInvalide());

        statistiques.enregistrerDeclarationAvecNumeroDePermisInvalide();
        statistiques.enregistrerDeclarationAvecNumeroDePermisInvalide();
        assertEquals("Nombre de déclarations soumises avec un numéro de permis invalide: 3",
                afficheur.messageNombreDeDeclarationsAvecNumeroDePermisInvalide());
    }
}
