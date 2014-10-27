package validation;

import declaration.ActiviteDeFormation;
import declaration.DeclarationDeFormation;
import java.util.ArrayList;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurMembreProfessionnel {

    protected DeclarationDeFormation membre;
    protected ArrayList<String> messagesErreurs;
    protected int heuresTotal;

    public ValidateurMembreProfessionnel(DeclarationDeFormation membre) {
        this.membre = membre;
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
    }
    
    public JSONObject produireRapport() {
        JSONObject texteDeSortie = new JSONObject();
        validerFormation();
        texteDeSortie.accumulate("complet", formationComplete());
        texteDeSortie.accumulate("erreurs", messagesErreurs);
        return texteDeSortie;
    }
    
    public void validerFormation() {
        validerCycle();
        if (cycleEstValide()) {
            validerCategoriesDesActivites();
            validerDatesDesActivites();
            validerHeuresDeFormation();
            validerHeuresDeFormationPourSixCategories();
        }
    }
    
    public void validerCycle() {
        if (!cycleEstValide()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. "
                    + "Seul le cycle 2012-2014 est accepté.");
        }
    }

    public boolean cycleEstValide() {
        return membre.getCycle().equals("2012-2014");
    }

    public void validerCategoriesDesActivites() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        ArrayList<String> descriptionsDesActivites = descriptionsDActivitesAvecCategorieNonReconnue(liste);
        int nombreDActivitesNonReconnues = descriptionsDesActivites.size();
        String activitesErronees = convertirDescriptionsEnPhrase(descriptionsDesActivites);
        if (nombreDActivitesNonReconnues > 0) {
            ecrireMessageDErreurPourCategoriesNonReconnues(nombreDActivitesNonReconnues, activitesErronees);
        }  
    }   
    
    private ArrayList<String> descriptionsDActivitesAvecCategorieNonReconnue(ArrayList<ActiviteDeFormation> liste) {
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        for (int i = 0; i < liste.size(); ++i) {
            ActiviteDeFormation activite = liste.get(i);
            if (activite.regroupementDesCategories(activite.getCategorie()) == -1) {
                descriptionsDesActivites.add(activite.getDescription());
            }
        }
        return descriptionsDesActivites;
    }
    
    protected String convertirDescriptionsEnPhrase(ArrayList<String> descriptions) {
        int nombreDeDescriptions = descriptions.size();
        String phraseDeRetour = "";
        if (nombreDeDescriptions > 0) {
            phraseDeRetour = construirePhraseAvecDescriptions(descriptions);
        }
        return phraseDeRetour;
    }

    private String construirePhraseAvecDescriptions(ArrayList<String> descriptions) {
        String phraseDeRetour = descriptions.get(0);
        int nombreDeDescriptions = descriptions.size();
        for (int i = 1; i < nombreDeDescriptions - 1; i++) {
            phraseDeRetour += ", " + descriptions.get(i);
        }
        if (nombreDeDescriptions > 1) {
            phraseDeRetour += " et " + descriptions.get(nombreDeDescriptions - 1);
        }
        return phraseDeRetour;
    }
    
    private void ecrireMessageDErreurPourCategoriesNonReconnues(int nombreDActivites, String activitesErronees) {
        String messageSortie;
        if (nombreDActivites > 1) {
            messageSortie = "Les activités " + activitesErronees + " sont dans des catégories non reconnues. "
                    + "Elles seront ignorées.";
        } else {messageSortie = "L'activité " + activitesErronees + " est dans une catégorie non reconnue. "
                + "Elle sera ignorée.";
        }
        messagesErreurs.add(messageSortie);
    }

    public void validerDatesDesActivites() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        ArrayList<String> descriptionsDesActivites = descriptionsDActivitesAvecDateInvalide(liste);
        int nombreDActivitesNonReconnues = descriptionsDesActivites.size();
        String activitesErronees = convertirDescriptionsEnPhrase(descriptionsDesActivites);
        if (nombreDActivitesNonReconnues > 0) {
            ecrireMessageDErreurPourDatesInvalides(nombreDActivitesNonReconnues, activitesErronees);
        } 
    }
    
    private ArrayList<String> descriptionsDActivitesAvecDateInvalide(ArrayList<ActiviteDeFormation> liste) {
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        for (int i = 0; i < liste.size(); ++i) {
            ActiviteDeFormation activite = liste.get(i);
            if (!activite.dateActivitesCompleteesValides()) {
                descriptionsDesActivites.add(activite.getDescription());
            }
        }
        return descriptionsDesActivites;
    }
    
    private void ecrireMessageDErreurPourDatesInvalides(int nombreDActivites, String activitesErronees) {
        String messageSortie;
        if (nombreDActivites > 1) {
            messageSortie = "Les dates des activités " + activitesErronees + " sont invalides. "
                    + "Ces activités seront ignorées.";
        } else {
            messageSortie = "La date de l'activité " + activitesErronees + " est invalide. L'activité sera ignorée.";
        }
        messagesErreurs.add(messageSortie);
    }

    public void validerHeuresDeFormation() {
        String messageHeuresManquantes = "";
        int heuresTotalesManquantes = 40 - heuresTotalesFormation();
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresTotalesManquantes > 0 || heuresManquantesSixCategories > 0) {
            int heuresManquantesPourLeCycle = max(heuresTotalesManquantes, heuresManquantesSixCategories);
            messageHeuresManquantes += "Il manque un total de " + heuresManquantesPourLeCycle
                    + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }
    
    protected int heuresTotalesPourRegroupementDesSixCategories() {
        return (nombreDHeuresSelonRegroupement(1) + heuresTransfereesEffectives());
    }
    
    protected int heuresTransfereesEffectives() {
        int heuresTransferees = membre.getHeuresTransferees();
        int heuresEffectives = heuresTransferees;
        if (heuresTransferees < 0) {
            heuresEffectives = 0;
        } else if (heuresTransferees > 7) {
            heuresEffectives = 7;
        }
        return heuresEffectives;
    }

    public void validerHeuresDeFormationPourSixCategories() {
        String messageHeuresManquantes = "";
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesSixCategories > 0) {
            messageHeuresManquantes += "En particulier, il manque " + heuresManquantesSixCategories
                    + " heure(s) de formation à compléter parmi les catégories suivantes: "
                    + "cours, atelier, séminaire, colloque, conférence et lecture dirigée.";
            messagesErreurs.add(messageHeuresManquantes);
        }
    }

    public int nombreDHeuresSelonRegroupement(int codeDuRegroupement) {
        ActiviteDeFormation activite;
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesAcceptees();
        int somme = 0;
        for (int i = 0; i < liste.size(); ++i) {
            activite = liste.get(i);
            if (activite.regroupementDesCategories(activite.getCategorie()) == codeDuRegroupement) {
                somme += activite.getDureeEnHeures();
            }
        }
        return somme;
    }

    public int heuresTotalesFormation() {
        int heuresPresentation = heuresEffectivesSelonCategorie("présentation");
        int heuresDiscussion = heuresEffectivesSelonCategorie("groupe de discussion");
        int heuresRecherche = heuresEffectivesSelonCategorie("projet de recherche");
        int heuresRedaction = heuresEffectivesSelonCategorie("rédaction professionnelle");

        return heuresTotal = heuresPresentation + heuresDiscussion
                + heuresRecherche + heuresRedaction;
    }

    public int heuresEffectivesSelonCategorie(String categorie) {
        int heuresBrutes = heuresBrutesSelonCategorie(categorie);
        int maximumHeures = maximumHeuresSelonCategorie(categorie);
        return min(heuresBrutes,maximumHeures);
    }

    protected int heuresBrutesSelonCategorie(String categorie) {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesAcceptees();
        int heuresTotales = 0;
        for (ActiviteDeFormation activiteCourante : liste) {
            if (activiteCourante.estDansCategorie(categorie)) {
                heuresTotales += activiteCourante.getDureeEnHeures();
            }
        }
        return heuresTotales;
    }

    protected int maximumHeuresSelonCategorie(String categorie) {
        return Integer.MAX_VALUE;
    }

    // La formation est complète ssi le cycle est valide et si les heures totales sont au moins 40, 
    // dont au moins 17 dans le regroupement #1 dees catégories (groupe des 6 catégories).
    public boolean formationComplete() {
        return heuresTotal >= 40 && cycleEstValide() && (nombreDHeuresSelonRegroupement(1) >= 17);
    }

    private int max(int nombre1, int nombre2) {
        return nombre1 > nombre2 ? nombre1 : nombre2;
    }

    private int min(int nombre1, int nombre2) {
        return nombre1 < nombre2 ? nombre1 : nombre2;
    }

}
