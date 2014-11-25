package statistiques;

import java.util.TreeMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import professionnels.*;
import validation.Validateur;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class AccumulateurStatistiques {

    private JSONObject donneesStatistiques;

    private boolean declarationTraitee;
    private boolean declarationIncompleteOuInvalide;
    private int sexeDeclaree;
    private TreeMap<String, Integer> activitesValidesParCategorie;
    private IEcriveurStatistiques ecriveurStatistiques;

    public AccumulateurStatistiques() {
        this(new EcriveurStatistiques());
    }

    public AccumulateurStatistiques(IEcriveurStatistiques ecriveurStatistique) {
        this.ecriveurStatistiques = ecriveurStatistique;
        donneesStatistiques = ecriveurStatistiques.chargerStatistiquesExistantes();
        declarationTraitee = false;
        declarationIncompleteOuInvalide = false;
        sexeDeclaree = 0;
        activitesValidesParCategorie = new TreeMap<>();
        etablirCategoriesReconnues(activitesValidesParCategorie);

    }

    private static void etablirCategoriesReconnues(TreeMap<String, Integer> activitesValidesParCategorie) {
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            activitesValidesParCategorie.put(categorie, 0);
        }
    }

    public static String[] nomsDesCategoriesReconnues() {
        String categoriesReconnues[] = {"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée",
            "présentation", "groupe de discussion", "projet de recherche", "rédaction professionnelle"};
        return categoriesReconnues;
    }

    public void enregistrerTraitementDeDeclaration() {
        incrementerStatistique("declarations_traitees");
    }

    public void enregistrerCompletudeDeLaDeclaration(Validateur validateur) {
        declarationIncompleteOuInvalide = !validateur.formationComplete();
    }

    public void enregistrerDeclarationInvalide(int sexeDuDeclarant) {
        sexeDeclaree = sexeDuDeclarant;
        declarationIncompleteOuInvalide = true;
    }

    public void enregistrerDetailsDuDeclarant(Membre membre) {
        enregistrerSexeDeclaree(membre);
        enregistrerActivitesValidesParCategorie(membre);
    }

    public void enregistrerSexeDeclaree(Membre membre) {
        sexeDeclaree = membre.getSexe();
    }

    private void enregistrerActivitesValidesParCategorie(Membre membre) {
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            enregistrerActivitesValidesParCategorie(membre, categorie);
        }
    }

    private void enregistrerActivitesValidesParCategorie(Membre membre, String categorie) {
        int nombreActivitesValides = membre.obtenirNombreActivitesValidesParCategorie(categorie);
        enregistrerActiviteValideParCategorie(nombreActivitesValides, categorie);
    }

    private void enregistrerActiviteValideParCategorie(int nombre, String categorie) {
        activitesValidesParCategorie.put(categorie, nombre);
    }

    public void mettreAJourStatistiquesCumulatives() {
        JSONObject donneesStatistiques = chargerStatistiquesAnterieures();
        comptabiliserStatistiquesDeDeclarationCourante(donneesStatistiques);
        mettreAJourFichierStatistiques(donneesStatistiques);
    }

    public JSONObject chargerStatistiquesAnterieures() {
        JSONObject donneesStatistiques;
        donneesStatistiques = ecriveurStatistiques.chargerStatistiquesExistantes();
        donneesStatistiques = ecriveurStatistiques.genererStatistiquesVides();
        return donneesStatistiques;
    }

    private void comptabiliserStatistiquesDeDeclarationCourante(JSONObject donneesStatistiques) {
        mettreAJourDeclarationsTraitees(donneesStatistiques);
        mettreAJourDeclarationsCompletes(donneesStatistiques);
        mettreAJourDeclarationsIncompletesOuInvalides(donneesStatistiques);
        mettreAJourDeclarationsSelonSexe(donneesStatistiques);
        mettreAJourActivitesValidesDesDeclarations(donneesStatistiques);
        mettreAJourActivitesValidesParCategorie(donneesStatistiques);
    }

    private void mettreAJourDeclarationsTraitees(JSONObject donneesStatistiques) {
        if (declarationTraitee) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_traitees");
        }
    }

    private void incrementerStatistiqueSelonCle(JSONObject donneesStatistiques, String cle) {
        int statistiqueAnterieure = donneesStatistiques.getInt(cle);
        donneesStatistiques.put(cle, ++statistiqueAnterieure);
    }

    private void mettreAJourDeclarationsCompletes(JSONObject donneesStatistiques) {
        if (!declarationIncompleteOuInvalide) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_completes");
        }
    }

    private void mettreAJourDeclarationsIncompletesOuInvalides(JSONObject donneesStatistiques) {
        if (declarationIncompleteOuInvalide) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_incompletes_ou_invalides");
        }
    }

    private void mettreAJourDeclarationsSelonSexe(JSONObject donneesStatistiques) {
        if (sexeDeclaree == 1) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_faites_par_des_hommes");
        } else if (sexeDeclaree == 2) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_faites_par_des_femmes");
        } else {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_faites_par_des_gens_de_sexe_inconnu");
        }
    }

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
            nombreTotalActivitesValides += activitesValidesParCategorie.get(categorie);
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
        JSONObject donneesStatistiques = ecriveurStatistiques.chargerStatistiquesExistantes();
        afficherChaqueStatistique(donneesStatistiques);
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
            String categorieCourante = compteur.getString("categorie");
            int nombre = compteur.getInt("nombre");
            System.out.println(tabulation + '\"' + categorieCourante + '\"' + ": " + nombre);
        }
    }

    public void reinitialiserStatistiques() {
        JSONObject donneesStatistiques = ecriveurStatistiques.genererStatistiquesVides();
        mettreAJourFichierStatistiques(donneesStatistiques);
        System.out.println("Statistiques réinitialisées.");
    }

    int obtenirNombreDeclarationsValidesPourArchitectes() {
        return -1; //TODO: implémentation
    }

    int obtenirNombreDeDeclarationsTraitees() {
        return obtenirStatistique("declarations_traitees");
    }

    private int obtenirStatistique(String champsStatistique) {
        int statistique = 0;
        if (donneesStatistiques.has(champsStatistique)) {
            statistique = donneesStatistiques.getInt(champsStatistique);
        }
        return statistique;
    }

    private void incrementerStatistique(String champsStatistique) {
        int statistique = obtenirStatistique(champsStatistique);
        donneesStatistiques.put(champsStatistique, ++statistique);
    }
}
