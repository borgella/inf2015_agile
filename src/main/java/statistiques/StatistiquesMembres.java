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
            initialiserStatistiques();
        }
    }

    private static String[] nomsDesCategoriesReconnues() {
        String categoriesReconnues[] = 
        {"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée",
            "présentation", "groupe de discussion", "projet de recherche", "rédaction professionnelle"};
        return categoriesReconnues;
    }
    
    private static String[] nomsDesOrdresReconnus() {
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

    public void enregistrerDeclarationInvalide() {
        donneesStatistiques.incrementerStatistique("declarations_incompletes_ou_invalides");
    }

    public void enregistrerActivitesValidesParCategorie(Membre membre) {
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            enregistrerActivitesValidesParCategorie(membre, categorie);
        }
    }
   
    private void enregistrerActivitesValidesParCategorie(Membre membre, String categorie) {
        int nombreActivitesValides = membre.obtenirNombreActivitesValidesParCategorie(categorie);
        enregistrerActivitesValidesParCategorie(nombreActivitesValides, categorie);
    }

    private void enregistrerActivitesValidesParCategorie(int nombre, String categorieActivite) {
        if (nombre > 0) {
            String categorieStatistique = "activites_valides_par_categorie";
            donneesStatistiques.incrementerStatistiqueSousCategorie
            (categorieStatistique, categorieActivite, nombre);
        }
    }

    public void enregistrerCompletudeDeDeclarationValide(boolean formationComplete, String ordre) {
        if (formationComplete) {
            donneesStatistiques.incrementerStatistique("declarations_completes");
            donneesStatistiques.incrementerStatistiqueSousCategorie
                ("declarations_valides_et_completes_par_ordre", ordre);
        } else {
            donneesStatistiques.incrementerStatistique("declarations_incompletes_ou_invalides");
            donneesStatistiques.incrementerStatistiqueSousCategorie
                ("declarations_valides_et_incompletes_par_ordre", ordre);
        }
    }

    public void mettreAJourStatistiquesCumulatives() {
        JSONObject donneesASauvegarder = donneesStatistiques.getDonneesStatistiques();
        ecriveurStatistiques.ecrireCumulStatistiques(donneesASauvegarder);
    }

    public void afficherStatistiques() {
        JSONObject donneesPourAffichage = donneesStatistiques.getDonneesStatistiques();
        afficherChaqueStatistique(donneesPourAffichage);
    }

    private void afficherChaqueStatistique(JSONObject donneesStatistiques) {
        afficherNombreDeclarationsTraitees();
        afficherNombreDeclarationsCompletes();
        afficherNombreDeclarationsIncompletesOuInvalides(donneesStatistiques);
        afficherNombreDeclarationsFaitesParHommes();
        afficherNombreDeclarationsFaitesParFemmes();
        afficherNombreDeclarationsParGensDeSexeInconnu();
        afficherNombreTotalActivitesValidesDeclarees(donneesStatistiques);
        afficherNombreActivitesValidesDeclareesSelonCategorie();
        afficherNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre();
        afficherNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre();
    }

    private void afficherNombreDeclarationsTraitees() {
        System.out.println("Nombre total de déclarations traitées: "
                + obtenirNombreDeDeclarationsTraitees());
    }

    int obtenirNombreDeDeclarationsTraitees() {
        return donneesStatistiques.obtenirStatistique("declarations_traitees");
    }

    private void afficherNombreDeclarationsCompletes() {
        System.out.println("Nombre total de déclarations complètes: "
                + obtenirNombreDeDeclarationsCompletes());
    }

    int obtenirNombreDeDeclarationsCompletes() {
        return donneesStatistiques.obtenirStatistique("declarations_completes");
    }

    private void afficherNombreDeclarationsIncompletesOuInvalides(JSONObject donneesStatistiques) {
        System.out.println("Nombre total de déclarations incomplètes ou invalides: "
                + donneesStatistiques.getInt("declarations_incompletes_ou_invalides"));
    }

    private void afficherNombreDeclarationsFaitesParHommes() {
        System.out.println("Nombre total de déclarations faites par des hommes: "
                + obtenirNombreDeDeclarationsTraiteesParHommes());
    }

    int obtenirNombreDeDeclarationsTraiteesParHommes() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_hommes");
    }

    private void afficherNombreDeclarationsFaitesParFemmes() {
        System.out.println("Nombre total de déclarations faites par des femmes: "
                + obtenirNombreDeDeclarationsTraiteesParFemmes());
    }

    int obtenirNombreDeDeclarationsTraiteesParFemmes() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_femmes");
    }

    private void afficherNombreDeclarationsParGensDeSexeInconnu() {
        System.out.println("Nombre total de déclarations faites par des gens de sexe inconnu: "
                + obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu());
    }

    int obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_gens_de_sexe_inconnu");
    }

    private void afficherNombreTotalActivitesValidesDeclarees(JSONObject donneesStatistiques) {
        System.out.println("Nombre total d'activités valides dans les déclarations: "
                + donneesStatistiques.getInt("activites_valides_dans_les_declarations"));
    }

    private void afficherNombreActivitesValidesDeclareesSelonCategorie() {
        System.out.println("Nombre d'activités valides par catégorie: ");
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        String tabulation = "    ";
        for (String categorieActivites: categoriesReconnues) {
            int activitesValidesPourCategorie = obtenirActivitesValidesParCategorie(categorieActivites);
            System.out.println(tabulation + '\"' + categorieActivites + '\"' + ": " + activitesValidesPourCategorie);
        }
    }
    
    private void afficherNombreDeclarationsValidesEtCompletesDeclareesSelonOrdre() {
        System.out.println("Nombre de déclarations complètes par ordre: ");
        String[] ordresReconnus = nomsDesOrdresReconnus();
        String tabulation = "    ";
        for (String ordre: ordresReconnus) {
            int declarationsCompletesPourOrdre = obtenirNombreDeDeclarationsValidesEtCompletes(ordre);
            System.out.println(tabulation + '\"' + ordre + '\"' + ": " + declarationsCompletesPourOrdre);
        }
    }
    
    private void afficherNombreDeclarationsValidesEtIncompletesDeclareesSelonOrdre() {
        System.out.println("Nombre de déclarations incomplètes par ordre: ");
        String[] ordresReconnus = nomsDesOrdresReconnus();
        String tabulation = "    ";
        for (String ordre: ordresReconnus) {
            int declarationsInompletesPourOrdre = obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre(ordre);
            System.out.println(tabulation + '\"' + ordre + '\"' + ": " + declarationsInompletesPourOrdre);
        }
    }
    
    int obtenirNombreTotalDeDeclarationsValidesEtCompletes() {
        return donneesStatistiques.obtenirStatistique("declarations_completes");
    }

    int obtenirNombreDeDeclarationsValidesEtIncompletesSelonOrdre(String ordre) {
        String categorieStatistique = "declarations_valides_et_incompletes_par_ordre";
        return donneesStatistiques.obtenirStatistiqueSousCategorie(categorieStatistique, ordre);
    }

    int obtenirNombreDeDeclarationsInvalides() {
        return donneesStatistiques.obtenirStatistique("declarations_incompletes_ou_invalides");
    }

    private int obtenirActivitesValidesParCategorie(String categorieActivite) {
        String categorieStatistique = "activites_valides_par_categorie";
        return donneesStatistiques.obtenirStatistiqueSousCategorie(categorieStatistique, categorieActivite);
    }

    int obtenirNombreDeDeclarationsValidesEtCompletes(String ordre) {
        String categorieStatistique = "declarations_valides_et_completes_par_ordre";
        return donneesStatistiques.obtenirStatistiqueSousCategorie(categorieStatistique, ordre);
    }

    public void reinitialiserStatistiques() {
        donneesStatistiques = new EnsembleStatistique();
        initialiserStatistiques();
        mettreAJourStatistiquesCumulatives();
        System.out.println("Statistiques réinitialisées.");
    }

    private void initialiserStatistiques() {
        initialiserStatistiquesGeneralesPourDeclarations();
        initialiserStatistiquesPourActivitesValides();
        initialiserStatistiquesPourDeclarationsCompletesSelonOrdre();
        initialiserStatistiquesPourDeclarationsIncompletesSelonOrdre();
    }

    private void initialiserStatistiquesGeneralesPourDeclarations() {
        donneesStatistiques.ajouterChampsStatistique("declarations_traitees");
        donneesStatistiques.ajouterChampsStatistique("declarations_completes");
        donneesStatistiques.ajouterChampsStatistique("declarations_incompletes_ou_invalides");
        donneesStatistiques.ajouterChampsStatistique("declarations_faites_par_des_hommes");
        donneesStatistiques.ajouterChampsStatistique("declarations_faites_par_des_femmes");
        donneesStatistiques.ajouterChampsStatistique("declarations_faites_par_des_gens_de_sexe_inconnu");
    }

    private void initialiserStatistiquesPourActivitesValides() {
        initialiserStatistiquePourNombreTotalActivitesValides();
        initialiserStatistiquePourActivitesValidesParCategorie();
    }
    
    private void initialiserStatistiquePourNombreTotalActivitesValides() {
        donneesStatistiques.ajouterChampsStatistique("activites_valides_dans_les_declarations");
    }

    private void initialiserStatistiquePourActivitesValidesParCategorie() {
        String categorieStatistique = "activites_valides_par_categorie";
        donneesStatistiques.ajouterCategorieDeChampsStatistiques(categorieStatistique);
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, categorie);
        }
    }
  
    private void initialiserStatistiquesPourDeclarationsCompletesSelonOrdre() {
        String categorieStatistique = "declarations_valides_et_completes_par_ordre";
        donneesStatistiques.ajouterCategorieDeChampsStatistiques(categorieStatistique);
        donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, "architectes");
        donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, "géologues");
        donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, "psychologues");
        donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, "podiatres");
    }

    private void initialiserStatistiquesPourDeclarationsIncompletesSelonOrdre() {
        String categorieStatistique = "declarations_valides_et_incompletes_par_ordre";
        donneesStatistiques.ajouterCategorieDeChampsStatistiques(categorieStatistique);
        donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, "architectes");
        donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, "géologues");
        donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, "psychologues");
        donneesStatistiques.ajouterChampsStatistiqueSousCategorie(categorieStatistique, "podiatres");
    }
}
