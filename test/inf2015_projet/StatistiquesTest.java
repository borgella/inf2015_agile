/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf2015_projet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import professionnels.*;
import validation.*;

/**
 *
 * @author User
 */
public class StatistiquesTest {
    
    Statistiques statistiques;
    JSONObject declaration;
    JSONObject donneesStatistiquesPleines;
    Membre membre;
    Validateur validateur;
    
    public StatistiquesTest() {
    }
    
    @Before
    public void setUp() {
        statistiques = new Statistiques("cumulStatistiques/faussesDonneesDeTests.json");
        declaration = new JSONObject();
        
        donneesStatistiquesPleines = new JSONObject();
        
        donneesStatistiquesPleines.accumulate("declarations_traitees", 9);
        donneesStatistiquesPleines.accumulate("declarations_completes", 3);
        donneesStatistiquesPleines.accumulate("declarations_incompletes_ou_invalides", 6);
        donneesStatistiquesPleines.accumulate("declarations_faites_par_des_hommes", 2);
        donneesStatistiquesPleines.accumulate("declarations_faites_par_des_femmes", 3);
        donneesStatistiquesPleines.accumulate("declarations_faites_par_des_gens_de_sexe_inconnu", 4);
        donneesStatistiquesPleines.accumulate("activites_valides_dans_les_declarations", 1045);
        
        JSONArray statistiquesParCategorie = new JSONArray();
        String[] categoriesReconnues = Statistiques.nomsDesCategoriesReconnues();
        int statistiqueBidon = 10;
        for (String categorie : categoriesReconnues) {
            JSONObject compteurPourCategorie = new JSONObject();
            compteurPourCategorie.accumulate("categorie", categorie);
            compteurPourCategorie.accumulate("nombre", statistiqueBidon++);
            statistiquesParCategorie.add(compteurPourCategorie);
        }
        donneesStatistiquesPleines.accumulate("activites_valides_par_categorie", statistiquesParCategorie);
    }
    
    
    
    @After
    public void tearDown() {
        statistiques = null;
        declaration = null;
        donneesStatistiquesPleines = null;
        membre = null;
        validateur = null;
    }

    @Test
    public void testReinitialiserStatistiquesAvecJSONObject() {
        
        assertNotSame(0, donneesStatistiquesPleines.getInt("declarations_traitees"));     
        assertNotSame(0, donneesStatistiquesPleines.getInt("declarations_traitees")); 
        assertNotSame(0, donneesStatistiquesPleines.getInt("declarations_completes")); 
        assertNotSame(0, donneesStatistiquesPleines.getInt("declarations_incompletes_ou_invalides")); 
        assertNotSame(0, donneesStatistiquesPleines.getInt("declarations_faites_par_des_hommes")); 
        assertNotSame(0, donneesStatistiquesPleines.getInt("declarations_faites_par_des_femmes")); 
        assertNotSame(0, donneesStatistiquesPleines.getInt("declarations_faites_par_des_gens_de_sexe_inconnu")); 
        assertNotSame(0, donneesStatistiquesPleines.getInt("activites_valides_dans_les_declarations")); 

        JSONArray statsParCategorie = donneesStatistiquesPleines.getJSONArray("activites_valides_par_categorie");
       
        for (Object statsCourantes : statsParCategorie) {
            JSONObject statistiqueCategorie = (JSONObject) statsCourantes;
            assertNotSame(0, statistiqueCategorie.getInt("nombre"));
        }
       
        statistiques.reinitialiserStatistiques(donneesStatistiquesPleines);
        
        assertEquals(0, donneesStatistiquesPleines.getInt("declarations_traitees"));     
        assertEquals(0, donneesStatistiquesPleines.getInt("declarations_traitees")); 
        assertEquals(0, donneesStatistiquesPleines.getInt("declarations_completes")); 
        assertEquals(0, donneesStatistiquesPleines.getInt("declarations_incompletes_ou_invalides")); 
        assertEquals(0, donneesStatistiquesPleines.getInt("declarations_faites_par_des_hommes")); 
        assertEquals(0, donneesStatistiquesPleines.getInt("declarations_faites_par_des_femmes")); 
        assertEquals(0, donneesStatistiquesPleines.getInt("declarations_faites_par_des_gens_de_sexe_inconnu")); 
        assertEquals(0, donneesStatistiquesPleines.getInt("activites_valides_dans_les_declarations"));
        
        for (Object statsCourantes : statsParCategorie) {
            JSONObject statistiqueCategorie = (JSONObject) statsCourantes;
            assertEquals(0, statistiqueCategorie.getInt("nombre"));
        }
    }

    @Ignore
    @Test
    public void testEnregistrerCompletudeDeLaDeclaration() {
        System.out.println("enregistrerCompletudeDeLaDeclaration");
        ValidateurArchitecte validateur = null;
        Statistiques statistiques = new Statistiques();
        statistiques.enregistrerCompletudeDeLaDeclaration(validateur);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testEnregistrerDeclarationInvalideOuIncomplete() {
        System.out.println("enregistrerDeclarationInvalideOuIncomplete");
        Statistiques statistiques = new Statistiques();
        statistiques.enregistrerDeclarationInvalideOuIncomplete();
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testEnregistrerDetailsDuDeclarant() {
        System.out.println("enregistrerDetailsDuDeclarant");
        Membre membre = null;
        Statistiques statistiques = new Statistiques();
        statistiques.enregistrerDetailsDuDeclarant(membre);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testEnregistrerSexeDeclaree() {
        System.out.println("enregistrerSexeDeclaree");
        Membre membre = null;
        Statistiques statistiques = new Statistiques();
        statistiques.enregistrerSexeDeclaree(membre);
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testMettreAJourStatistiquesCumulatives() {
        System.out.println("mettreAJourStatistiquesCumulatives");
        Statistiques statistiques = new Statistiques();
        statistiques.mettreAJourStatistiquesCumulatives();
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testAfficherStatistiques() {
        System.out.println("afficherStatistiques");
        Statistiques statistiques = new Statistiques();
        statistiques.afficherStatistiques();
        fail("The test case is a prototype.");
    }
    
}
