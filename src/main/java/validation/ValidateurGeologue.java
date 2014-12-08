package validation;

import java.util.ArrayList;
import net.sf.json.JSONObject;
import professionnels.Geologue;
import professionnels.Membre;
/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurGeologue extends Validateur {

    protected Geologue membre;
    protected ArrayList<String> messagesErreurs;
    protected int heuresTotal;

    public ValidateurGeologue(Membre geologue) {
        this.membre = (Geologue) geologue;
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
    }
    public ValidateurGeologue(){}
    
    @Override
    public JSONObject produireRapport() {
        return produireRapport(messagesErreurs);
    }

    @Override
    public void construireMessagesDErreur() {
        messageErreurSiLeCycleEstInvalide();
        if (validerLeCycle()) {
            messageInvalidePourCategorieNonReconnue(membre);
            messageErreurPourDateInvalide(membre);
            messageErreurPourHeuresManquantes();
            messageErreurPourHeuresInsuffisantesParCategorie();
        }
    }

    @Override
    public boolean validerLeCycle() {
        return membre.getCycle().equals("2013-2016");
    }
    
    @Override
    public void messageErreurSiLeCycleEstInvalide() {
        if (!validerLeCycle()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. "
                    + "Seul le cycle 2013-2016 est accepté.");
        }
    }

    @Override
    public void ecrireMessageDErreurPourCategoriesNonReconnues(int nombreDActivites, String activitesErronees) {
        String messageSortie;
        if (nombreDActivites > 1) {
            messageSortie = "Les activités " + activitesErronees + " sont dans des catégories non reconnues. "
                    + "Elles seront ignorées.";
        } else {
            messageSortie = "L'activité " + activitesErronees + " est dans une catégorie non reconnue. "
                    + "Elle sera ignorée.";
        }
        messagesErreurs.add(messageSortie);
    }

    @Override
    public void ecrireMessageDErreurPourDatesInvalides(int nombreDActivites, String activitesErronees) {
        String messageSortie;
        if (nombreDActivites > 1) {
            messageSortie = "Les dates des activités " + activitesErronees + " sont invalides. "
                    + "Ces activités seront ignorées.";
        } else {
            messageSortie = "La date de l'activité " + activitesErronees + " est invalide. L'activité sera ignorée.";
        }
        messagesErreurs.add(messageSortie);
    }

    @Override
    public int calculerHeuresManquantes() {
        int heuresManquantesEnGeneral = 55 - heuresTotalesFormation();
        int heuresManquantesCours = 22 - heuresBrutesSelonCategorie("cours");
        int heuresManquantesRecherche = 3 - heuresBrutesSelonCategorie("projet de recherche");
        int heuresManquantesDiscussion = 1 - heuresBrutesSelonCategorie("groupe de discussion");
        // Le nombre d'heures manquantes est la somme des heures manquantes par catégorie, si supérieure au total brut
        int grandMaximum = maximumEntreUnNombreEtUnTripletSomme(heuresManquantesEnGeneral, heuresManquantesCours,
                heuresManquantesRecherche, heuresManquantesDiscussion);
        return grandMaximum;
    }

    @Override
    public int heuresTotalesFormation() {
        int heuresSeptCategories = nombreDHeuresSelonRegroupement(1);
        int heuresPresentation = heuresBrutesSelonCategorie("cours");
        int heuresRecherche = heuresBrutesSelonCategorie("projet de recherche");
        int heuresDiscussion = heuresBrutesSelonCategorie("groupe de discussion");

        return heuresTotal = heuresSeptCategories
                + heuresPresentation + heuresRecherche + heuresDiscussion;
    }

    @Override
    public int nombreDHeuresSelonRegroupement(int codeDuRegroupement) {
        ArrayList<JSONObject> liste = membre.getActivitesAcceptees();
        int somme = 0;
        for (int i = 0; i < liste.size(); ++i) {
            JSONObject activite = liste.get(i);
            if (membre.regroupementDesCategories(activite.getString("categorie")) == codeDuRegroupement) {
                somme += activite.getInt("heures");
            }
        }
        return somme;
    }

    @Override
    public int heuresBrutesSelonCategorie(String categorie) {
        ArrayList<JSONObject> liste = membre.getActivitesAcceptees();
        int heuresTotales = 0;
        for (JSONObject activiteCourante : liste) {
            String categorieCourante = activiteCourante.getString("categorie");
            if (categorieCourante.equals(categorie)) {
                heuresTotales += activiteCourante.getInt("heures");
            }
        }
        return heuresTotales;
    }
    
    // Donne le maximum entre un "grand nombre" et trois autres nombres non négatifs
    public static int 
        maximumEntreUnNombreEtUnTripletSomme(int grandNombre, int nombreUn, int nombreDeux, int nombreTrois) {
        int grandNombreNonNegatif = rendreEntierNulSiNegatif(grandNombre);
        int nombreUnNonNegatif = rendreEntierNulSiNegatif(nombreUn);
        int nombreDeuxNonNegatif = rendreEntierNulSiNegatif(nombreDeux);
        int nombreTroisNonNegatif = rendreEntierNulSiNegatif(nombreTrois);
        return Integer.max(grandNombreNonNegatif, nombreUnNonNegatif + nombreDeuxNonNegatif + nombreTroisNonNegatif);
    }
    
    public static int rendreEntierNulSiNegatif(int nombre) {
        int nombrePositifOuNul = nombre;
        if (nombre < 0) {
            nombrePositifOuNul = 0;
        }
        return nombrePositifOuNul;
    }

    @Override
    public void ecrireMessageErreurPourHeuresManquantes(int heuresManquantes) {
        String messageHeuresManquantes = "Il manque un total de " + heuresManquantes 
                + " heure(s) de formation pour compléter le cycle.";
        messagesErreurs.add(messageHeuresManquantes);
    }

    public void messageErreurPourHeuresInsuffisantesParCategorie() {
        messageErreurPourHeuresInsuffisantesSelonCategorie(22, "cours");
        messageErreurPourHeuresInsuffisantesSelonCategorie(3, "projet de recherche");
        messageErreurPourHeuresInsuffisantesSelonCategorie(1, "groupe de discussion");
    }
    
    public void messageErreurPourHeuresInsuffisantesSelonCategorie(int heuresRequises, String categorie) {
        String messageHeuresManquantes = "";
        int heuresManquantesRecherche = heuresRequises - heuresBrutesSelonCategorie(categorie);
        if (heuresManquantesRecherche > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesRecherche
                    + " heure(s) de formation à compléter sous la catégorie " + categorie + ".";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }
    
    @Override
    public boolean formationComplete() {
        boolean critereCours = heuresBrutesSelonCategorie("cours") >= 22;
        boolean critereRecherche = heuresBrutesSelonCategorie("projet de recherche") >= 3;
        boolean critereDiscussion = heuresBrutesSelonCategorie("groupe de discussion") >= 1;
        boolean critereDHeuresTotales = 55 <= heuresTotalesFormation();
        return validerLeCycle() && critereCours && critereRecherche
                && critereDiscussion && critereDHeuresTotales;
    }

}
