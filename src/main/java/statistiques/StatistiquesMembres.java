package statistiques;

import net.sf.json.JSONObject;
import professionnels.*;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class StatistiquesMembres {

    private EnsembleStatistique donneesStatistiques;
    private IEcriveurStatistiques ecriveurStatistiques;

    public StatistiquesMembres() {
        this(new EcriveurStatistiques());
    }

    public StatistiquesMembres(IEcriveurStatistiques ecriveurStatistique) {
        JSONObject donneesExistantes = null;
        if (ecriveurStatistique != null) {
            this.ecriveurStatistiques = ecriveurStatistique;
            donneesExistantes = ecriveurStatistiques.chargerStatistiquesExistantes();
        }
        donneesStatistiques = new EnsembleStatistique(donneesExistantes);
        if (donneesExistantes == null) {
            donneesStatistiques = GenerateurStatistiquesMembres.initialiserStatistiques();
        }
    }

    public static String[] nomsDesCategoriesReconnues() {
        String categoriesReconnues[]
                = {"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée",
                    "présentation", "groupe de discussion", "projet de recherche", "rédaction professionnelle"};
        return categoriesReconnues;
    }

    public static String[] nomsDesOrdresReconnus() {
        String ordresReconnus[] = {"architectes", "géologues", "psychologues", "podiatres"};
        return ordresReconnus;
    }

    public void enregistrerTraitementDeDeclaration(int sexeDuDeclarant) {
        donneesStatistiques.incrementerStatistique("declarations_traitees");
        if (sexeDuDeclarant == 1) {
            donneesStatistiques.incrementerStatistique("declarations_faites_par_des_hommes");
        } else if (sexeDuDeclarant == 2) {
            donneesStatistiques.incrementerStatistique("declarations_faites_par_des_femmes");
        } else {
            donneesStatistiques.incrementerStatistique("declarations_faites_par_des_gens_de_sexe_inconnu");
        }
    }

    public void enregistrerDeclarationInvalide(int codeSexe) {
        donneesStatistiques.incrementerStatistique("declarations_incompletes_ou_invalides");
    }

    public void enregistrerActivitesValidesParCategorie(Membre membre) {
        enregistrerNombreTotalActivitesValides(membre);
        enregistrerNombreActivitesValidesParCategorieIndividuelle(membre);
    }

    public void enregistrerNombreTotalActivitesValides(Membre membre) {
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        int nombreTotalActivitesValides = 0;
        for (String categorie : categoriesReconnues) {
            int nombreActivitesValides = membre.obtenirNombreActivitesValidesParCategorie(categorie);
            nombreTotalActivitesValides += nombreActivitesValides;
        }
        donneesStatistiques.incrementerStatistique
            ("activites_valides_dans_les_declarations", nombreTotalActivitesValides);
    }

    public void enregistrerNombreActivitesValidesParCategorieIndividuelle(Membre membre) {
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        String categorieStatistique = "activites_valides_par_categorie";
        for (String categorie : categoriesReconnues) {
            int nombreActivitesValides = membre.obtenirNombreActivitesValidesParCategorie(categorie);
            donneesStatistiques.incrementerStatistiqueSousCategorie
                (categorieStatistique, categorie, nombreActivitesValides);
        }
    }

    public void enregistrerCompletudeDeDeclarationValide(boolean formationComplete, String ordre) {
        if (formationComplete) {
            donneesStatistiques.incrementerStatistique("declarations_completes");
            donneesStatistiques.incrementerStatistiqueSousCategorie("declarations_valides_et_completes_par_ordre", ordre);
        } else {
            donneesStatistiques.incrementerStatistique("declarations_incompletes_ou_invalides");
            donneesStatistiques.incrementerStatistiqueSousCategorie("declarations_valides_et_incompletes_par_ordre", ordre);
        }
    }

    public void enregistrerDeclarationsAvecNumeroDePermisInvalide() {
        donneesStatistiques.incrementerStatistique("declarations_avec_numero_de_permis_invalide");
    }

    public void mettreAJourStatistiquesCumulatives() {
        JSONObject donneesASauvegarder = donneesStatistiques.getDonneesStatistiques();
        ecriveurStatistiques.ecrireCumulStatistiques(donneesASauvegarder);
    }

    public void afficherStatistiques() {
        AfficheurStatistiquesMembres afficheur = new AfficheurStatistiquesMembres(this);
        afficheur.afficher();
    }

    public void reinitialiserStatistiques() {
        donneesStatistiques = GenerateurStatistiquesMembres.initialiserStatistiques();
        mettreAJourStatistiquesCumulatives();
        System.out.println("Statistiques réinitialisées.");
    }

    public int obtenirNombreDeDeclarationsTraitees() {
        return donneesStatistiques.obtenirStatistique("declarations_traitees");
    }

    public int obtenirNombreDeDeclarationsCompletes() {
        return donneesStatistiques.obtenirStatistique("declarations_completes");
    }

    public int obtenirNombreDeDeclarationsTraiteesParHommes() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_hommes");
    }

    public int obtenirNombreDeDeclarationsTraiteesParFemmes() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_femmes");
    }

    public int obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_gens_de_sexe_inconnu");
    }

    public int obtenirNombreTotalDeDeclarationsValidesEtCompletes() {
        return donneesStatistiques.obtenirStatistique("declarations_completes");
    }

    public int obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre(String ordre) {
        String categorieStatistique = "declarations_valides_et_incompletes_par_ordre";
        return donneesStatistiques.obtenirStatistiqueSousCategorie(categorieStatistique, ordre);
    }

    public int obtenirNombreDeDeclarationsInvalidesOuIncompletes() {
        return donneesStatistiques.obtenirStatistique("declarations_incompletes_ou_invalides");
    }
    
    public int obtenirNombreTotalActivitesValides() { 
        return donneesStatistiques.obtenirStatistique("activites_valides_dans_les_declarations");
    }

    public int obtenirActivitesValidesParCategorie(String categorieActivite) {
        String categorieStatistique = "activites_valides_par_categorie";
        return donneesStatistiques.obtenirStatistiqueSousCategorie(categorieStatistique, categorieActivite);
    }

    public int obtenirNombreDeDeclarationsValidesEtCompletes(String ordre) {
        String categorieStatistique = "declarations_valides_et_completes_par_ordre";
        return donneesStatistiques.obtenirStatistiqueSousCategorie(categorieStatistique, ordre);
    }

    public int obtenirNombreDeDeclarationsAvecNumeroDePermisInvalide() {
        return donneesStatistiques.obtenirStatistique("declarations_avec_numero_de_permis_invalide");
    }
}