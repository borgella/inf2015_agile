package statistiques;

import java.util.TreeMap;
import net.sf.json.JSONArray;
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
        this.ecriveurStatistiques = ecriveurStatistique;
        JSONObject donneesExistantes = ecriveurStatistiques.chargerStatistiquesExistantes();
        donneesStatistiques = new EnsembleStatistique(donneesExistantes);
    }
    
    private static void etablirCategoriesReconnues(TreeMap<String, Integer> activitesValidesParCategorie) {
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            System.out.println(categorie);
            activitesValidesParCategorie.put(categorie, 0);
        }
    }

    public static String[] nomsDesCategoriesReconnues() {
        String categoriesReconnues[] = {"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée",
            "présentation", "groupe de discussion", "projet de recherche", "rédaction professionnelle"};
        return categoriesReconnues;
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

    public void enregistrerCompletudeDeDeclarationValide(boolean formationComplete) {
        if (formationComplete) {
            donneesStatistiques.incrementerStatistique("declarations_completes");
        } else {
            donneesStatistiques.incrementerStatistique("declarations_incompletes_ou_invalides");
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

    private void enregistrerActivitesValidesParCategorie(int nombre, String categorie) {
        System.out.println(categorie);
        if (nombre > 0) {
          incrementerActivitesValidesParCategorie(categorie, nombre);  
        }
    }
    
    private void incrementerActivitesValidesParCategorie(String categorieActivite, int nombre) {
        donneesStatistiques.incrementerStatistiqueSousCategorie
        ("activites_valides_par_categorie", categorieActivite, nombre);
    }

    public void mettreAJourStatistiquesCumulatives() {
        JSONObject donneesASauvegarder = donneesStatistiques.getDonneesStatistiques();
        ecriveurStatistiques.ecrireCumulStatistiques(donneesASauvegarder);
    }

    /*
    private void mettreAJourActivitesValidesDesDeclarations(JSONObject donneesStatistiques) {
        String champsStatistique = "activites_valides_dans_les_declarations";
        int nombreAnterieur = donneesStatistiques.getInt(champsStatistique);
        int activitesValidesPourDeclarationCourante = calculerNombreTotalActivitesValides();
        donneesStatistiques.put(champsStatistique, nombreAnterieur + activitesValidesPourDeclarationCourante);
    }

    private int calculerNombreTotalActivitesValides() {
        int nombreTotalActivitesValides = 0;
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            nombreTotalActivitesValides += obtenirActivitesValidesParCategorie(categorie);
        }
        return nombreTotalActivitesValides;
    }

    private void mettreAJourActivitesValidesParCategorie(JSONObject donneesStatistiques) {
        String champsStatistique = "activites_valides_par_categorie";
        JSONArray compteursActivitesValidesParCategorie = donneesStatistiques.getJSONArray(champsStatistique);
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            mettreAJourActivitesValidesParCategorie(compteursActivitesValidesParCategorie, categorie);
        }
    }

    private void mettreAJourActivitesValidesParCategorie(JSONArray compteursParCategorie, String categorie) {
        JSONObject compteurPourCategorie = trouverCompteurPourCategorie(compteursParCategorie, categorie);
        String champsStatistique = "nombre";
        int nombreAnterieur = compteurPourCategorie.getInt(champsStatistique);
        int activitesValidesCourantesPourCategorie = activitesValidesParCategorie.get(categorie);
        compteurPourCategorie.put(champsStatistique, nombreAnterieur + activitesValidesCourantesPourCategorie);
    }
    */
    private static JSONObject trouverCompteurPourCategorie(JSONArray compteursParCategorie, String categorie) {
        JSONObject compteurPourCategorie = null;
        for (int i = 0; i < compteursParCategorie.size(); i++) {
            JSONObject compteur = compteursParCategorie.getJSONObject(i);
            if (compteurEstDeCategorie(compteur, categorie)) {
                compteurPourCategorie = compteur;
                break;
            }
        }
        return compteurPourCategorie;
    }

    private static boolean compteurEstDeCategorie(JSONObject compteur, String categorie) {
        String categorieCompteur = compteur.getString("categorie");
        return categorie.equals(categorieCompteur);
    }

    private void mettreAJourFichierStatistiques(JSONObject donneesStatistiques) {
        ecriveurStatistiques.ecrireCumulStatistiques(donneesStatistiques);
    }

    public void afficherStatistiques() {
        JSONObject donneesPourAffichage = donneesStatistiques.getDonneesStatistiques();
        afficherChaqueStatistique(donneesPourAffichage);
    }

    private void afficherChaqueStatistique(JSONObject donneesStatistiques) {
        afficherNombreDeclarationsTraitees(donneesStatistiques);
        afficherNombreDeclarationsCompletes(donneesStatistiques);
        afficherNombreDeclarationsIncompletesOuInvalides(donneesStatistiques);
        afficherNombreDeclarationsFaitesParHommes(donneesStatistiques);
        afficherNombreDeclarationsFaitesParFemmes(donneesStatistiques);
        afficherNombreDeclarationsParGensDeSexeInconnu(donneesStatistiques);
        afficherNombreTotalActivitesValidesDeclarees(donneesStatistiques);
        afficherNombreActivitesValidesDeclareesSelonCategories(donneesStatistiques);
    }

    private void afficherNombreDeclarationsTraitees(JSONObject donneesStatistiques) {
        System.out.println("Nombre total de déclarations traitées: "
                + donneesStatistiques.getInt("declarations_traitees"));
    }

    private void afficherNombreDeclarationsCompletes(JSONObject donneesStatistiques) {
        System.out.println("Nombre total de déclarations complètes: "
                + donneesStatistiques.getInt("declarations_completes"));
    }

    private void afficherNombreDeclarationsIncompletesOuInvalides(JSONObject donneesStatistiques) {
        System.out.println("Nombre total de déclarations incomplètes ou invalides: "
                + donneesStatistiques.getInt("declarations_incompletes_ou_invalides"));
    }

    private void afficherNombreDeclarationsFaitesParHommes(JSONObject donneesStatistiques) {
        System.out.println("Nombre total de déclarations faites par des hommes: "
                + donneesStatistiques.getInt("declarations_faites_par_des_hommes"));
    }

    private void afficherNombreDeclarationsFaitesParFemmes(JSONObject donneesStatistiques) {
        System.out.println("Nombre total de déclarations faites par des femmes: "
                + donneesStatistiques.getInt("declarations_faites_par_des_femmes"));
    }

    private void afficherNombreDeclarationsParGensDeSexeInconnu(JSONObject donneesStatistiques) {
        System.out.println("Nombre total de déclarations faites par des gens de sexe inconnu: "
                + donneesStatistiques.getInt("declarations_faites_par_des_gens_de_sexe_inconnu"));
    }

    private void afficherNombreTotalActivitesValidesDeclarees(JSONObject donneesStatistiques) {
        System.out.println("Nombre total d'activités valides dans les déclarations: "
                + donneesStatistiques.getInt("activites_valides_dans_les_declarations"));
    }

    private void afficherNombreActivitesValidesDeclareesSelonCategories(JSONObject donneesStatistiques) {
        System.out.println("Nombre d'activités valides par catégorie:");
        JSONArray compteursPourCategories = donneesStatistiques.getJSONArray("activites_valides_par_categorie");
        afficherCompteurActivitesValidesPourCategorie(compteursPourCategories);
    }

    private void afficherCompteurActivitesValidesPourCategorie(JSONArray compteursPourCategories) {
        String tabulation = " ";
        for (int i = 0; i < compteursPourCategories.size(); i++) {
            JSONObject compteur = compteursPourCategories.getJSONObject(i);
            String categorieCourante = compteur.toString();
            System.out.println(tabulation + categorieCourante);
        }
    }

    public void reinitialiserStatistiques() {
        /* TODO
        donneesStatistiques = ecriveurStatistiques.genererStatistiquesVides();
        mettreAJourFichierStatistiques(donneesStatistiques);
        System.out.println("Statistiques réinitialisées.");
        */
    }


    int obtenirNombreDeDeclarationsTraitees() {
        return donneesStatistiques.obtenirStatistique("declarations_traitees");
    }
    
    int obtenirNombreDeDeclarationsTraiteesParHommes() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_hommes");
    }
    
    int obtenirNombreDeDeclarationsTraiteesParFemmes() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_femmes");
    }
    
    int obtenirNombreDeDeclarationsTraiteesParGensDeSexeInconnu() {
        return donneesStatistiques.obtenirStatistique("declarations_faites_par_des_gens_de_sexe_inconnu");
    }
    
    int obtenirNombreDeDeclarationsValidesEtCompletes() {
        return donneesStatistiques.obtenirStatistique("declarations_completes");
    }
    
    int obtenirNombreDeDeclarationsValidesEtIncompletes() {
        return donneesStatistiques.obtenirStatistique("declarations_incompletes_ou_invalides");
    }
    
    int obtenirNombreDeDeclarationsInvalides() {
        return donneesStatistiques.obtenirStatistique("declarations_incompletes_ou_invalides");
    }

    private int obtenirActivitesValidesParCategorie(String categorieActivite) {
        int nombreActivitesValides = donneesStatistiques.obtenirStatistiqueSousCategorie
            ("activites_valides_par_categorie", categorieActivite);
        return nombreActivitesValides;
    }
}
