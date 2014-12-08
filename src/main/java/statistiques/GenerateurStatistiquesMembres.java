package statistiques;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class GenerateurStatistiquesMembres {
    
    public static EnsembleStatistique initialiserStatistiques() {
        EnsembleStatistique statistiques = new EnsembleStatistique();
        initialiserStatistiquesGeneralesPourDeclarations(statistiques);
        initialiserStatistiquesPourActivitesValides(statistiques);
        initialiserStatistiquesPourDeclarationsCompletesSelonOrdre(statistiques);
        initialiserStatistiquesPourDeclarationsIncompletesSelonOrdre(statistiques);
        initialiserStatistiquesPourDeclarationsAvecNumerosDePermisInvalides(statistiques);
        return statistiques;
    }

    private static void initialiserStatistiquesGeneralesPourDeclarations(EnsembleStatistique statistiques) {
        statistiques.ajouterChampsStatistique("declarations_traitees");
        statistiques.ajouterChampsStatistique("declarations_completes");
        statistiques.ajouterChampsStatistique("declarations_incompletes_ou_invalides");
        statistiques.ajouterChampsStatistique("declarations_faites_par_des_hommes");
        statistiques.ajouterChampsStatistique("declarations_faites_par_des_femmes");
        statistiques.ajouterChampsStatistique("declarations_faites_par_des_gens_de_sexe_inconnu");
    }

    private static void initialiserStatistiquesPourActivitesValides(EnsembleStatistique statistiques) {
        initialiserStatistiquePourNombreTotalActivitesValides(statistiques);
        initialiserStatistiquePourActivitesValidesParCategorie(statistiques);
    }
    
    private static void initialiserStatistiquePourNombreTotalActivitesValides(EnsembleStatistique statistiques) {
        statistiques.ajouterChampsStatistique("activites_valides_dans_les_declarations");
    }

    private static void initialiserStatistiquePourActivitesValidesParCategorie(EnsembleStatistique statistiques) {
        String categorieStatistique = "activites_valides_par_categorie";
        statistiques.ajouterCategorieDeChampsStatistiques(categorieStatistique);
        String[] categoriesReconnues = StatistiquesMembres.nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            statistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, categorie);
        }
    }
  
    private static void 
        initialiserStatistiquesPourDeclarationsCompletesSelonOrdre(EnsembleStatistique statistiques) {
        String categorieStatistique = "declarations_valides_et_completes_par_ordre";
        statistiques.ajouterCategorieDeChampsStatistiques(categorieStatistique);
        for (String ordre: StatistiquesMembres.nomsDesOrdresReconnus()) {
            statistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, ordre);
        }
    }

    private static void 
        initialiserStatistiquesPourDeclarationsIncompletesSelonOrdre(EnsembleStatistique statistiques) {
        String categorieStatistique = "declarations_valides_et_incompletes_par_ordre";
        statistiques.ajouterCategorieDeChampsStatistiques(categorieStatistique);
        for (String ordre: StatistiquesMembres.nomsDesOrdresReconnus()) {
            statistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, ordre);
        }
    }

    private static void 
        initialiserStatistiquesPourDeclarationsAvecNumerosDePermisInvalides(EnsembleStatistique statistiques) {
        statistiques.ajouterChampsStatistique("declarations_avec_numero_de_permis_invalide");
    }
}