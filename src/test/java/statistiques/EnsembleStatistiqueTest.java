package statistiques;

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
    public void testObtenirEtiquettePourStatistiques() {
        String etiquette = "Statistiques pour les chapeaux";
        EnsembleStatistique statistiquesAvecEtiquette = new EnsembleStatistique(etiquette);
        
        assertEquals("Statistiques pour les chapeaux", statistiquesAvecEtiquette.getEtiquette()); 
    }

    @Test
    public void testContientChampsStatistique() {
        String champs = "nombre_de_chapeaux";
        assertFalse(statistiques.contientChampsStatistiques(champs)); 
    }
    
    @Test
    public void testChampsStatistiqueExisteApresSonAjout() {
        String champs = "nombre_de_chapeaux";
        statistiques.ajouterChampsStatistique(champs);
        assertTrue(statistiques.contientChampsStatistiques(champs)); 
    }
    
    @Test
    public void testNouveauChampsStatistiqueEstInitialiseAZero() {
        String champs = "nombre_de_chapeaux";
        statistiques.ajouterChampsStatistique(champs);
        assertEquals(0, statistiques.obtenirStatistique(champs)); 
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
