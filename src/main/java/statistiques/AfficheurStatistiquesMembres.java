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
        afficherStatistiquesGeneralesSurDeclarationsEtActivites();
        afficherStatistiquesParticulieresSurDeclarationsEtNumeroDePermis();
    }
    
    public void afficherStatistiquesGeneralesSurDeclarationsEtActivites() {
        System.out.println(messageNombreDeclarationsTraitees());
        System.out.println(messageNombreDeclarationsValidesEtCompletes());
        System.out.println(messageNombreDeclarationsIncompletesOuInvalides());
        System.out.println(messageNombreDeclarationsFaitesParHommes());
        System.out.println(messageNombreDeclarationsFaitesParFemmes());
        System.out.println(messageNombreDeclarationsParGensDeSexeInconnu());
        System.out.println(messageNombreTotalActivitesValidesDeclarees());
        System.out.println(messageNombreActivitesValidesDeclareesSelonCategorie());
    }
    
    public void afficherStatistiquesParticulieresSurDeclarationsEtNumeroDePermis() {
        System.out.println(messageNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre());
        System.out.println(messageNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre());
        System.out.println(messageNombreDeDeclarationsAvecNumeroDePermisInvalide());
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

    public String messageNombreTotalActivitesValidesDeclarees() {
        return ("Nombre total d'activités valides dans les déclarations: "
                + statistiques.obtenirNombreTotalActivitesValides());
    }

    public String messageNombreActivitesValidesDeclareesSelonCategorie() {
        String message = "Nombre d'activités valides par catégorie: ";
        for (String categorieActivites : StatistiquesMembres.nomsDesCategoriesReconnues()) {
            int activitesValidesPourCategorie = statistiques.obtenirActivitesValidesParCategorie(categorieActivites);
            message += '\n' + "    " + '\"' + categorieActivites + '\"' + ": " + activitesValidesPourCategorie;
        }
        return message;
    }

    public String messageNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre() {
        String message = "Nombre de déclarations valides et complètes par ordre: ";
        for (String ordre : StatistiquesMembres.nomsDesOrdresReconnus()) {
            int declarationsCompletesPourOrdre = statistiques.obtenirNombreDeDeclarationsValidesEtCompletes(ordre);
            message += '\n' + "    " + '\"' + ordre + '\"' + ": " + declarationsCompletesPourOrdre;
        }
        return message;
    }

    public String messageNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre() {
        String message = "Nombre de déclarations valides et incomplètes par ordre: ";
        for (String ordre : StatistiquesMembres.nomsDesOrdresReconnus()) {
            int nombDeclarations = statistiques.obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre(ordre);
            message += '\n' + "    " + '\"' + ordre + '\"' + ": " + nombDeclarations;
        }
        return message;
    }
    
    public String messageNombreDeDeclarationsAvecNumeroDePermisInvalide() {
        return ("Nombre de déclarations soumises avec un numéro de permis invalide: "
                + statistiques.obtenirNombreDeDeclarationsAvecNumeroDePermisInvalide());
    }
}
