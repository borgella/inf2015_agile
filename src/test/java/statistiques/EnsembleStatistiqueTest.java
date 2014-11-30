package statistiques;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class EnsembleStatistiqueTest {
    
    EnsembleStatistique statistiques;
    
    public EnsembleStatistiqueTest() {
    }
    
    @Before
    public void setUp() {
        statistiques = new EnsembleStatistique();
    }
    
    @After
    public void tearDown() {
        statistiques = null;
    }
    
    @Test
    public void testContientChampsStatistique() {
        String champs = "nombre_de_chapeaux";
        assertFalse(statistiques.contientChampsOuCategorieStatistique(champs)); 
    }
    
    @Test
    public void testInitiliserAvecDesDonneesExistantes() {
        String champsUnique = "nombre_d'ordinateurs";
        String categorie = "statistiques_pour_les_vetements";
        String champsUnSousCategorie = "nombre_de_chapeaux";
        String champsDeuxSousCategorie = "nombre_de_bottes";
        
        JSONObject donneesExistantes = new JSONObject();
        donneesExistantes.accumulate(champsUnique, 0);
        
        JSONArray champsSousCategorie = new JSONArray();
        
        JSONObject statistiqueUn = new JSONObject();
        statistiqueUn.accumulate(champsUnSousCategorie, 0);
        champsSousCategorie.add(statistiqueUn);
        
        JSONObject statistiqueDeux = new JSONObject();
        statistiqueDeux.accumulate(champsDeuxSousCategorie, 0);
        champsSousCategorie.add(statistiqueDeux);

        donneesExistantes.accumulate(categorie, champsSousCategorie);
        
        EnsembleStatistique statsInitialisees = new EnsembleStatistique(donneesExistantes);
        
        assertTrue(statsInitialisees.contientChampsOuCategorieStatistique(champsUnique));
        assertTrue(statsInitialisees.contientChampsOuCategorieStatistique(categorie));
        assertTrue(statsInitialisees.contientChampsStatistiqueSousCategorie(categorie, champsUnSousCategorie));
        assertTrue(statsInitialisees.contientChampsStatistiqueSousCategorie(categorie, champsDeuxSousCategorie));
    }
    
    @Test
    public void testStatistiquesInitialiseesAuxBonneValeurs() {
        String champsUnique = "nombre_d'ordinateurs";
        String categorie = "statistiques_pour_les_vetements";
        String champsUnSousCategorie = "nombre_de_chapeaux";
        String champsDeuxSousCategorie = "nombre_de_bottes";
        
        JSONObject donneesExistantes = new JSONObject();
        donneesExistantes.accumulate(champsUnique, 1);
        
        JSONArray champsSousCategorie = new JSONArray();
        
        JSONObject statistiqueUn = new JSONObject();
        statistiqueUn.accumulate(champsUnSousCategorie, 2);
        champsSousCategorie.add(statistiqueUn);
        
        JSONObject statistiqueDeux = new JSONObject();
        statistiqueDeux.accumulate(champsDeuxSousCategorie, 3);
        champsSousCategorie.add(statistiqueDeux);

        donneesExistantes.accumulate(categorie, champsSousCategorie);
        
        EnsembleStatistique statsInitialisees = new EnsembleStatistique(donneesExistantes);
        
        assertEquals(1, statsInitialisees.obtenirStatistique(champsUnique));
        assertEquals(2, statsInitialisees.obtenirStatistiqueSousCategorie(categorie, champsUnSousCategorie));
        assertEquals(3, statsInitialisees.obtenirStatistiqueSousCategorie(categorie, champsDeuxSousCategorie));
    }
    
    @Test
    public void testChampsStatistiqueExisteApresSonAjout() {
        String champs = "nombre_de_chapeaux";
        statistiques.ajouterChampsStatistique(champs);
        assertTrue(statistiques.contientChampsOuCategorieStatistique(champs)); 
    }
    
    @Test
    public void testCategorieDeChampsStatistiquesExisteApresSonAjout() {
        String categorie = "statistiques_pour_les_vetements";
        statistiques.ajouterCategorieDeChampsStatistiques(categorie);
        assertTrue(statistiques.contientChampsOuCategorieStatistique(categorie)); 
    }
    
    @Test
    public void testChampsStatistiqueSousCategorieExisteApresSonAjout() {
        String categorie = "statistiques_pour_les_vetements";
        String champs = "nombre_de_chapeaux";
        
        statistiques.ajouterCategorieDeChampsStatistiques(categorie);
        statistiques.ajouterChampsStatistiqueSousCategorie(categorie, champs);
        
        assertTrue(statistiques.contientChampsStatistiqueSousCategorie(categorie, champs)); 
    }

    @Test
    public void testNouveauChampsStatistiqueEstInitialiseAZero() {
        String champs = "nombre_de_chapeaux";
        statistiques.ajouterChampsStatistique(champs);
        assertEquals(0, statistiques.obtenirStatistique(champs)); 
    }
    
    @Test
    public void testNouveauChampsStatistiqueSousCategorieEstInitialiseAZero() {
        String categorie = "statistiques_pour_les_vetements";
        String champs = "nombre_de_chapeaux";
        
        statistiques.ajouterCategorieDeChampsStatistiques(categorie);
        statistiques.ajouterChampsStatistiqueSousCategorie(categorie, champs);
        
        assertEquals(0, statistiques.obtenirStatistiqueSousCategorie(categorie, champs)); 
    }
    
    @Test
    public void testIncrementerValeurStatistique() {
        String champs = "nombre_de_chapeaux";
        statistiques.ajouterChampsStatistique(champs);
        
        statistiques.incrementerStatistique(champs);
        assertEquals(1, statistiques.obtenirStatistique(champs)); 
        
        statistiques.incrementerStatistique(champs);
        statistiques.incrementerStatistique(champs);
        assertEquals(3, statistiques.obtenirStatistique(champs)); 
    }
    
    /*
    @Test
    public void testIncrementerValeurStatistiqueSousUneCategorie() {
        String categorieStatistiques = "statistiques_sur_les";
        String champs = "nombre_de_chapeaux";
        statistiques.ajouterChampsStatistique(champs);
        
        statistiques.incrementerStatistique(champs);
        assertEquals(1, statistiques.obtenirStatistique(champs)); 
        
        statistiques.incrementerStatistique(champs);
        statistiques.incrementerStatistique(champs);
        assertEquals(3, statistiques.obtenirStatistique(champs)); 
    }*/
    
    @Test
    public void testIncrementerValeurStatistiqueParUnNombre() {
        String champs = "nombre_de_chapeaux";
        statistiques.ajouterChampsStatistique(champs);
        
        statistiques.incrementerStatistique(champs, 3);
        assertEquals(3, statistiques.obtenirStatistique(champs)); 
        
        statistiques.incrementerStatistique(champs, 5);
        assertEquals(8, statistiques.obtenirStatistique(champs)); 
    }
    
}
