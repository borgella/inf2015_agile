package inf2015_projet;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import professionnels.*;
import validation.Validateur;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class Statistiques {

    private boolean declarationTraitee;
    private boolean declarationIncompleteOuInvalide;
    private int sexeDeclaree;
    private TreeMap<String, Integer> activitesValidesParCategorie;
    private String fichierStatistiques;

    public Statistiques() {
        this("cumulStatistiques/donneesStatistiques.json");
    }

    public Statistiques(String fichierStatistiques) {
        declarationTraitee = false;
        declarationIncompleteOuInvalide = false;
        sexeDeclaree = 0;
        activitesValidesParCategorie = new TreeMap<>();
        etablirCategoriesReconnues(activitesValidesParCategorie);
        this.fichierStatistiques = fichierStatistiques;
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
        setDeclarationTraitee(true);
    }

    public void enregistrerCompletudeDeLaDeclaration(Validateur validateur) {
        setDeclarationIncompleteOuInvalide(!validateur.formationComplete());
    }

    public void enregistrerDeclarationInvalideOuIncomplete() {
        setDeclarationIncompleteOuInvalide(true);
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
        JSONObject donneesStatistiques;
        donneesStatistiques = chargerStatistiquesAnterieures();
        comptabiliserStatistiquesDeDeclarationCourante(donneesStatistiques);
        mettreAJourFichierStatistiques(donneesStatistiques);
    }

    public JSONObject chargerStatistiquesAnterieures() {
        JSONObject donneesStatistiques;
        try {
            donneesStatistiques = chargerFichierStatistiques();
        } catch (IOException e) {
            System.out.println("Fichier " + fichierStatistiques + "introuvable, il sera créé.");
            donneesStatistiques = construireFichierStatistiques();
        }
        return donneesStatistiques;
    }

    public JSONObject chargerFichierStatistiques() throws IOException {
        String statistiquesAnterieures = FileReader.loadFileIntoString(getFichierStatistiques(), "UTF-8");
        JSONObject donneesStatistiques = JSONObject.fromObject(statistiquesAnterieures);
        return donneesStatistiques;
    }

    private static JSONObject construireFichierStatistiques() {
        JSONObject fichierStatistiques = new JSONObject();
        ajouterChampsStatistiquesPourDeclarations(fichierStatistiques);
        ajouterChampsStatistiquesPourActivitesValides(fichierStatistiques);
        return fichierStatistiques;
    }

    private static void ajouterChampsStatistiquesPourDeclarations(JSONObject fichierStatistiques) {
        fichierStatistiques.accumulate("declarations_traitees", 0);
        fichierStatistiques.accumulate("declarations_completes", 0);
        fichierStatistiques.accumulate("declarations_incompletes_ou_invalides", 0);
        fichierStatistiques.accumulate("declarations_faites_par_des_hommes", 0);
        fichierStatistiques.accumulate("declarations_faites_par_des_femmes", 0);
        fichierStatistiques.accumulate("declarations_faites_par_des_gens_de_sexe_inconnu", 0);
    }

    private static void ajouterChampsStatistiquesPourActivitesValides(JSONObject fichierStatistiques) {
        ajouterChampsStatistiquePourNombreTotalActivitesValides(fichierStatistiques);
        ajouterChampsStatistiquesPourActivitesValidesParCategorie(fichierStatistiques);
    }

    private static void ajouterChampsStatistiquePourNombreTotalActivitesValides(JSONObject fichierStatistiques) {
        fichierStatistiques.accumulate("activites_valides_dans_les_declarations", 0);
    }

    private static void ajouterChampsStatistiquesPourActivitesValidesParCategorie(JSONObject fichierStatistiques) {
        JSONArray compteursPourCategories = new JSONArray();
        String[] categoriesReconnues = nomsDesCategoriesReconnues();
        for (String categorie : categoriesReconnues) {
            JSONObject compteurPourCategorie = champsStatistiqueActivitesValidesParCategorie(categorie);
            compteursPourCategories.add(compteurPourCategorie);
        }
        fichierStatistiques.accumulate("activites_valides_par_categorie", compteursPourCategories);
    }

    private static JSONObject champsStatistiqueActivitesValidesParCategorie(String categoriesReconnue) {
        JSONObject compteurPourCategorie = new JSONObject();
        compteurPourCategorie.accumulate("categorie", categoriesReconnue);
        compteurPourCategorie.accumulate("nombre", 0);
        return compteurPourCategorie;
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
        if (isDeclarationTraitee()) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_traitees");
        }
    }

    private void incrementerStatistiqueSelonCle(JSONObject donneesStatistiques, String cle) {
        int statistiqueAnterieure = donneesStatistiques.getInt(cle);
        donneesStatistiques.put(cle, ++statistiqueAnterieure);
    }

    private void mettreAJourDeclarationsCompletes(JSONObject donneesStatistiques) {
        if (!isDeclarationIncompleteOuInvalide()) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_completes");
        }
    }

    private void mettreAJourDeclarationsIncompletesOuInvalides(JSONObject donneesStatistiques) {
        if (isDeclarationIncompleteOuInvalide()) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_incompletes_ou_invalides");
        }
    }

    private void mettreAJourDeclarationsSelonSexe(JSONObject donneesStatistiques) {
        if (getSexeDeclaree() == 1) {
            incrementerStatistiqueSelonCle(donneesStatistiques, "declarations_faites_par_des_hommes");
        } else if (getSexeDeclaree() == 2) {
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
        try {
            FileWriter miseAJourFichierStatistiques = new FileWriter(getFichierStatistiques());
            miseAJourFichierStatistiques.write(donneesStatistiques.toString(2));
            miseAJourFichierStatistiques.close();
        } catch (IOException e) {
            System.out.println("Erreur d'écriture du fichier de statistiques: Aucune mise à jour des statistiques.");
        }
    }

    public void afficherStatistiques() {
        JSONObject donneesStatistiques;
        try {
            donneesStatistiques = chargerFichierStatistiques();
            afficherChaqueStatistique(donneesStatistiques);
        } catch (IOException e) {
            System.out.println("Aucune statistique existante.");
        }
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
        JSONObject donneesStatistiques;
        try {
            donneesStatistiques = chargerFichierStatistiques();
            reinitialiserStatistiques(donneesStatistiques);
        } catch (IOException e) {
            System.out.println("Aucun fichier de statistiques trouvé; un nouveau fichier sera créé.");
            donneesStatistiques = construireFichierStatistiques();
        }
        mettreAJourFichierStatistiques(donneesStatistiques);
        System.out.println("Statistiques réinitialisées.");
    }

    public void reinitialiserStatistiques(JSONObject donneesStatistiques) {
        reinitialiserStatistiquesPourDeclarations(donneesStatistiques);
        reinitialiserStatistiquesPourActivites(donneesStatistiques);
    }

    private void reinitialiserStatistiquesPourDeclarations(JSONObject donneesStatistiques) {
        donneesStatistiques.put("declarations_traitees", 0);
        donneesStatistiques.put("declarations_completes", 0);
        donneesStatistiques.put("declarations_incompletes_ou_invalides", 0);
        donneesStatistiques.put("declarations_faites_par_des_hommes", 0);
        donneesStatistiques.put("declarations_faites_par_des_femmes", 0);
        donneesStatistiques.put("declarations_faites_par_des_gens_de_sexe_inconnu", 0);
    }

    private void reinitialiserStatistiquesPourActivites(JSONObject donneesStatistiques) {
        donneesStatistiques.put("activites_valides_dans_les_declarations", 0);
        JSONArray statsParCategorie = donneesStatistiques.getJSONArray("activites_valides_par_categorie");
        for (Object statsCourantes : statsParCategorie) {
            JSONObject statistiqueCategorie = (JSONObject) statsCourantes;
            statistiqueCategorie.put("nombre", 0);
        }
    }

    public boolean isDeclarationTraitee() {
        return declarationTraitee;
    }

    public void setDeclarationTraitee(boolean declarationTraitee) {
        this.declarationTraitee = declarationTraitee;
    }

    public boolean isDeclarationIncompleteOuInvalide() {
        return declarationIncompleteOuInvalide;
    }

    public void setDeclarationIncompleteOuInvalide(boolean declarationIncompleteOuInvalide) {
        this.declarationIncompleteOuInvalide = declarationIncompleteOuInvalide;
    }

    public int getSexeDeclaree() {
        return sexeDeclaree;
    }

    public void setSexeDeclaree(int sexeDeclaree) {
        this.sexeDeclaree = sexeDeclaree;
    }

    public void setActivitesValidesParCategorie(TreeMap<String, Integer> activitesValidesParCategorie) {
        this.activitesValidesParCategorie = activitesValidesParCategorie;
    }

    public String getFichierStatistiques() {
        return fichierStatistiques;
    }

    public void setFichierStatistiques(String fichierStatistiques) {
        this.fichierStatistiques = fichierStatistiques;
    }
}
