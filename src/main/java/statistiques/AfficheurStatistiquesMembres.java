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
        afficherStatistiquesGeneralesSurLesDeclarations();
        afficherStatistiquesSurLesActivitesValidesParCategorie();
        afficherStatistiquesSurLesDeclarationsValideEtCompletesSelonOrdre();
        afficherStatistiquesSurLesDeclarationsValideEtIncompletesSelonOrdre();
        afficherStatistiquesSurLesDeclarationsAvecNumeroDePermisInvalide();
    }

    public void afficherStatistiquesGeneralesSurLesDeclarations() {
        System.out.println(messageNombreDeclarationsTraitees());
        System.out.println(messageNombreDeclarationsValidesEtCompletes());
        System.out.println(messageNombreDeclarationsIncompletesOuInvalides());
        System.out.println(messageNombreDeclarationsFaitesParHommes());
        System.out.println(messageNombreDeclarationsFaitesParFemmes());
        System.out.println(messageNombreDeclarationsParGensDeSexeInconnu());
    }

    public String messageNombreDeclarationsTraitees() {
        return ("Nombre total de déclarations traitées: "
                + statistiques.obtenirNombreDeDeclarationsTraitees());
    }

    public String messageNombreDeclarationsValidesEtCompletes() {
        return ("Nombre total de déclarations complètes: "
                + statistiques.obtenirNombreTotalDeDeclarationsValidesEtCompletes());
    }

    public String messageNombreDeclarationsIncompletesOuInvalides() {
        return ("Nombre total de déclarations incomplètes ou invalides: "
                + statistiques.obtenirNombreDeDeclarationsInvalidesOuIncompletes());
    }

    public String messageNombreDeclarationsFaitesParHommes() {
        return ("Nombre total de déclarations faites par des hommes: "
                + statistiques.obtenirNombreDeDeclarationsTraiteesParHommes());
    }

    public String messageNombreDeclarationsFaitesParFemmes() {
        return ("Nombre total de déclarations faites par des femmes: "
                + statistiques.obtenirNombreDeDeclarationsTraiteesParFemmes());
    }

    public String messageNombreDeclarationsParGensDeSexeInconnu() {
        return ("Nombre total de déclarations faites par des gens de sexe inconnu: "
                + statistiques.obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu());
    }

    public void afficherStatistiquesSurLesActivitesValidesParCategorie() {
        System.out.println(messageNombreTotalActivitesValidesDeclarees());
        System.out.println(messageNombreActivitesValidesDeclareesSelonCategorie());
    }

    public String messageNombreTotalActivitesValidesDeclarees() {
        return ("Nombre total d'activités valides dans les déclarations: "
                + statistiques.obtenirNombreTotalActivitesValides());
    }

    public String messageNombreActivitesValidesDeclareesSelonCategorie() {
        String message = "Nombre d'activités valides par catégorie: ";
        String[] categoriesReconnues = StatistiquesMembres.nomsDesCategoriesReconnues();
        String tabulation = "    ";
        for (String categorieActivites : categoriesReconnues) {
            int activitesValidesPourCategorie = statistiques.obtenirActivitesValidesParCategorie(categorieActivites);
            message += '\n' + tabulation + '\"' + categorieActivites + '\"' + ": " + activitesValidesPourCategorie;
        }
        return message;
    }

    public void afficherStatistiquesSurLesDeclarationsValideEtCompletesSelonOrdre() {
        System.out.println(messageNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre());
    }

    public String messageNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre() {
        String message = "Nombre de déclarations valides et complètes par ordre: ";
        String[] ordresReconnus = StatistiquesMembres.nomsDesOrdresReconnus();
        String tabulation = "    ";
        for (String ordre : ordresReconnus) {
            int declarationsCompletesPourOrdre = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes(ordre);
            message += '\n' + tabulation + '\"' + ordre + '\"' + ": " + declarationsCompletesPourOrdre;
        }
        return message;
    }

    public void afficherStatistiquesSurLesDeclarationsValideEtIncompletesSelonOrdre() {
        System.out.println(messageNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre());
    }

    public String messageNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre() {
        String message = "Nombre de déclarations valides et incomplètes par ordre: ";
        String[] ordresReconnus = StatistiquesMembres.nomsDesOrdresReconnus();
        String tabulation = "    ";
        for (String ordre : ordresReconnus) {
            int nombDeclarations = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre(ordre);
            message += '\n' + tabulation + '\"' + ordre + '\"' + ": " + nombDeclarations;
        }
        return message;
    }

    public void afficherStatistiquesSurLesDeclarationsAvecNumeroDePermisInvalide() {
        System.out.println(messageNombreDeDeclarationsAvecNumeroDePermisInvalide());
    }  
    
    public String messageNombreDeDeclarationsAvecNumeroDePermisInvalide() {
        return ("Nombre de déclarations soumises avec un numéro de permis invalide: "
                + statistiques.obtenirNombreDeDeclarationsAvecNumeroDePermisInvalide());
    }
}
