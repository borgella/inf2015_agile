package statistiques;

/**
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class AfficheurStatistiquesMembres {

    StatistiquesMembres statistiques;

    public AfficheurStatistiquesMembres(StatistiquesMembres statistiques) {
        this.statistiques = statistiques;
    }

    public void afficher() {
        afficherNombreDeclarationsTraitees();
        afficherNombreDeclarationsCompletes();
        afficherNombreDeclarationsIncompletesOuInvalides();
        afficherNombreDeclarationsFaitesParHommes();
        afficherNombreDeclarationsFaitesParFemmes();
        afficherNombreDeclarationsParGensDeSexeInconnu();
        afficherNombreTotalActivitesValidesDeclarees();
        afficherNombreActivitesValidesDeclareesSelonCategorie();
        afficherNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre();
        afficherNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre();
    }

    private void afficherNombreDeclarationsTraitees() {
        System.out.println("Nombre total de déclarations traitées: "
                + statistiques.obtenirNombreDeDeclarationsTraitees());
    }

    private void afficherNombreDeclarationsCompletes() {
        System.out.println("Nombre total de déclarations complètes: "
                + statistiques.obtenirNombreDeDeclarationsCompletes());
    }

    private void afficherNombreDeclarationsIncompletesOuInvalides() {
        System.out.println("Nombre total de déclarations incomplètes ou invalides: "
                + statistiques.obtenirNombreDeDeclarationsInvalidesOuIncompletes());
    }

    private void afficherNombreDeclarationsFaitesParHommes() {
        System.out.println("Nombre total de déclarations faites par des hommes: "
                + statistiques.obtenirNombreDeDeclarationsTraiteesParHommes());
    }

    private void afficherNombreDeclarationsFaitesParFemmes() {
        System.out.println("Nombre total de déclarations faites par des femmes: "
                + statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes());
    }

    private void afficherNombreDeclarationsParGensDeSexeInconnu() {
        System.out.println("Nombre total de déclarations faites par des gens de sexe inconnu: "
                + statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu());
    }

    private void afficherNombreTotalActivitesValidesDeclarees() {
        System.out.println("Nombre total d'activités valides dans les déclarations: "
                + statistiques.obtenirNombreTotalActivitesValides());
    }

    private void afficherNombreActivitesValidesDeclareesSelonCategorie() {
        System.out.println("Nombre d'activités valides par catégorie: ");
        String[] categoriesReconnues = StatistiquesMembres.nomsDesCategoriesReconnues();
        String tabulation = "    ";
        for (String categorieActivites : categoriesReconnues) {
            int activitesValidesPourCategorie = statistiques.obtenirActivitesValidesParCategorie(categorieActivites);
            System.out.println(tabulation + '\"' + categorieActivites + '\"' + ": " + activitesValidesPourCategorie);
        }
    }

    private void afficherNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre() {
        System.out.println("Nombre de déclarations complètes par ordre: ");
        String[] ordresReconnus = StatistiquesMembres.nomsDesOrdresReconnus();
        String tabulation = "    ";
        for (String ordre : ordresReconnus) {
            int declarationsCompletesPourOrdre = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes(ordre);
            System.out.println(tabulation + '\"' + ordre + '\"' + ": " + declarationsCompletesPourOrdre);
        }
    }

    private void afficherNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre() {
        System.out.println("Nombre de déclarations incomplètes par ordre: ");
        String[] ordresReconnus = StatistiquesMembres.nomsDesOrdresReconnus();
        String tabulation = "    ";
        for (String ordre : ordresReconnus) {
            int nombDeclarations = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre(ordre);
            System.out.println(tabulation + '\"' + ordre + '\"' + ": " + nombDeclarations);
        }
    }
}
