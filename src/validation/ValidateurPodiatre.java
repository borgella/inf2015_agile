package validation;

import java.util.ArrayList;
import net.sf.json.JSONObject;
import professionnels.Podiatre;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurPodiatre extends ValidateurGeologue {
    
    private Podiatre membre;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;
    
    public ValidateurPodiatre(Podiatre podiatre) {
        super(podiatre);
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
    }

    @Override
    public JSONObject produireRapport() {
        return super.produireRapport();
    }
    
    @Override
    public void construireMessagesDErreur() {
        super.construireMessagesDErreur();
    }

    @Override
    public boolean validerLeCycle() {
        return super.validerLeCycle();
    }
    
    @Override
    public void messageErreurSiLeCycleEstInvalide() {
        super.messageErreurSiLeCycleEstInvalide();
    }

    @Override
    public void messageInvalidePourCategorieNonReconnue() {
        super.messageInvalidePourCategorieNonReconnue();
    }

    @Override
    public ArrayList<String> descriptionsDActivitesAvecCategorieNonReconnue(ArrayList<JSONObject> liste) {
        return super.descriptionsDActivitesAvecCategorieNonReconnue(liste);
    }

    @Override
    public String convertirDescriptionsEnPhrase(ArrayList<String> descriptions) {
        return super.convertirDescriptionsEnPhrase(descriptions);
    }

    @Override
    public String construirePhraseAvecDescriptions(ArrayList<String> descriptions) {
        return super.construirePhraseAvecDescriptions(descriptions);
    }

    @Override
    public void ecrireMessageDErreurPourCategoriesNonReconnues(int nombreDActivites, String activitesErronees) {
        super.ecrireMessageDErreurPourCategoriesNonReconnues(nombreDActivites, activitesErronees);
    }

    @Override
    public void messageErreurPourDateInvalide() {
        super.messageErreurPourDateInvalide();
    }

    @Override
    public ArrayList<String> descriptionsDActivitesAvecDateInvalide(ArrayList<JSONObject> liste) {
        return super.descriptionsDActivitesAvecDateInvalide(liste);
    }

    @Override
    public void ecrireMessageDErreurPourDatesInvalides(int nombreDActivites, String activitesErronees) {
        super.ecrireMessageDErreurPourCategoriesNonReconnues(nombreDActivites, activitesErronees);
    }

    @Override
    public void messageErreurPourHeuresManquantes() {
        int heuresManquantesEnGeneral = 60 - heuresTotalesFormation();
        int heuresManquantesCours = 22 - heuresBrutesSelonCategorie("cours");
        int heuresManquantesRecherche = 3 - heuresBrutesSelonCategorie("projet de recherche");
        int heuresManquantesDiscussion = 1 - heuresBrutesSelonCategorie("groupe de discussion");
        // Le nombre d'heures manquantes est la somme des heures manquantes par catégorie, si supérieure au total brut
        int grandMaximum = maximumParmisQuatreSansNuls(heuresManquantesEnGeneral, heuresManquantesCours,
                 heuresManquantesRecherche, heuresManquantesDiscussion);
        ecrireMessageErreurPourHeuresManquantesSiApplicable(grandMaximum);
    }

    @Override
    public int heuresTotalesFormation() {
        return super.heuresTotalesFormation();
    }

    @Override
    public int heuresTotalesPourRegroupementDesSeptCategories() {
        return super.heuresTotalesPourRegroupementDesSeptCategories();
    }

    @Override
    public int nombreDHeuresSelonRegroupement(int codeDuRegroupement) {
        return super.nombreDHeuresSelonRegroupement(codeDuRegroupement);
    }

    @Override
    public int heuresBrutesSelonCategorie(String categorie) {
        return super.heuresBrutesSelonCategorie(categorie);
    }
    
    // Donne le maximum entre un "grand nombre" et trois autres nombres non négatifs
    @Override
    public int maximumParmisQuatreSansNuls(int grandNombre, int nombreUn, int nombreDeux, int nombreTrois) {
        return super.maximumParmisQuatreSansNuls(grandNombre, nombreUn, nombreDeux, nombreTrois);
    }
    
    @Override
    public int rendrePositifOuNul(int nombre) {
        return super.rendrePositifOuNul(nombre);
    }

    @Override
    public int max(int nombre1, int nombre2) {
        return super.max(nombre1, nombre2);
    }

    @Override
    public void ecrireMessageErreurPourHeuresManquantesSiApplicable(int heuresManquantes) {
        super.ecrireMessageErreurPourHeuresManquantesSiApplicable(heuresManquantes);
    }

    @Override
    public void messageErreurPourHeuresInsuffisantesParCategorie() {
        super.messageErreurPourHeuresInsuffisantesParCategorie();
    }

    @Override
    public void messageErreurPourHeuresInsuffisantesCours() {
        super.messageErreurPourHeuresInsuffisantesCours();
    }

    @Override
    public void messageErreurPourHeuresInsuffisantesRecherche() {
        super.messageErreurPourHeuresInsuffisantesRecherche();
    }

    @Override
    public void messageErreurPourHeuresInsuffisantesDiscussion() {
        super.messageErreurPourHeuresInsuffisantesDiscussion();
    }

    @Override
    public boolean formationComplete() {
        boolean critereCours = heuresBrutesSelonCategorie("cours") >= 22;
        boolean critereRecherche = heuresBrutesSelonCategorie("projet de recherche") >= 3;
        boolean critereDiscussion = heuresBrutesSelonCategorie("groupe de discussion") >= 1;
        boolean critereDHeuresTotales = heuresTotal >= 60;
        return validerLeCycle() && critereCours && critereRecherche
                && critereDiscussion && critereDHeuresTotales;
    }

}
