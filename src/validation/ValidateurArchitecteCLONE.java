package validation;

import declaration.ActiviteDeFormation;
import declaration.DeclarationDeFormation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurArchitecteCLONE extends ValidateurMembreProfessionnel {

    public ValidateurArchitecteCLONE(DeclarationDeFormation membre) {
        super(membre);
    }
    
    public void validerFormation() {
        validerCycle();
        if (cycleEstValide()) {
            validerHeuresTransferees();
            validerCategoriesDesActivites();
            validerDatesDesActivites();
            validerHeuresDeFormation();
            validerHeuresDeFormationPourSixCategories();
        }
    }

    public boolean cycleEstValide() {
        return membre.getCycle().equals("2008-2010")
                || membre.getCycle().equals("2010-2012")
                || membre.getCycle().equals("2012-2014");
    }
    
    public int heuresTotalesFormation() {
        int heuresSixCategoriesEtTransferees = heuresTotalesPourRegroupementDesSixCategories();
        int heuresPresentation = heuresEffectivesSelonCategorie("présentation");
        int heuresDiscussion = heuresEffectivesSelonCategorie("groupe de discussion");
        int heuresRecherche = heuresEffectivesSelonCategorie("projet de recherche");
        int heuresRedaction = heuresEffectivesSelonCategorie("rédaction professionnelle");

        return heuresTotal = heuresSixCategoriesEtTransferees
                + heuresPresentation + heuresDiscussion
                + heuresRecherche + heuresRedaction;
    }
    
    protected int maximumHeuresSelonCategorie(String categorie) {
        int nombreMaximumHeures = Integer.MAX_VALUE;
        if (categorie.equals("présentation") || categorie.equals("projet de recherche")) {
            nombreMaximumHeures = 23;
        } else if (categorie.equals("groupe de discussion") || categorie.equals("rédaction professionnelle")) {
            nombreMaximumHeures = 17;
        }
        return nombreMaximumHeures;
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

    public int heuresEffectivesSelonCategorie(String categorie) {
        int heuresBrutes = heuresBrutesSelonCategorie(categorie);
        int maximumHeures = maximumHeuresSelonCategorie(categorie);
        return (heuresBrutes > maximumHeures ? maximumHeures : heuresBrutes);
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

    /**
     * Intervalles permises d'une activité selon son cycle
     */
    public void intervallesActivitesCompleteesValides(ActiviteDeFormation activite) {
        String date = activite.getDateCompletee();
        
        if(membre.getCycle().equals("2008-2010")) {
            dateActivitesCompleteesValides(date, "2008-04-01", "2010-07-01");
        } else if (membre.getCycle().equals("2010-2012")) {
            dateActivitesCompleteesValides(date, "2010-04-01", "2012-04-01");
        } else {
            dateActivitesCompleteesValides(date, "2012-04-01", "2014-04-01");
        }
    }
    
    /**
     * Le cycle "2008-2010" pourra contenir des activités effectuées du 
     * 1er avril 2008 au 1er juillet 2010 inclusivement 
     * Le cycle "2010-2012" pourra contenir des activités effectuées du 
     * 1er avril 2010 au 1er avril 2012 inclusivement
     * Le cycle "2012-2014" pourra contenir des activités effectuées du 
     * 1er avril 2012 au 1er avril 2014 inclusivement 
     * 
     * Date invalide => MESSAGE D'ERREUR + activité ignorée des calculs 
     * Les dates sont indiquées en format ISO-8601
     *
     * @param date
     * @param intervalleMinimum
     * @param intervalleMaximum
     * @return
     */
    public boolean dateActivitesCompleteesValides(String date, String intervalleMinimum, String intervalleMaximum) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Date dateIntervalleMinimum = null;
        Date dateIntervalleMaximum = null;
        Date dateLue = null;
        
        try {
            dateIntervalleMinimum = df.parse(intervalleMinimum);
            dateIntervalleMaximum = df.parse(intervalleMaximum);
            dateLue = df.parse(date);
        } catch(ParseException e) {
            e.getMessage();
        }
        
        return (dateLue.after(dateIntervalleMinimum) || (dateLue.equals(dateIntervalleMinimum)) 
                && (dateLue.before(dateIntervalleMaximum) || dateLue.equals(dateIntervalleMaximum)));
    }

    public void validerCycle() {
        if (!cycleEstValide()) {
            messagesErreurs.add("Le cycle n'est pas valide et donc vos heures ne seront pas comptabilisées. Seul les cycles 2008-2010, 2010-2012 et 2012-2014 sont acceptés.");
        }

    }

    public void validerDatesDesActivites() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                ActiviteDeFormation activite = liste.get(i);
                if (!activite.dateActivitesCompleteesValides()) {
                    descriptionsDesActivites.add(activite.getDescription());
                    //retour += activite.getDescription() + " ";
                    sommation += 1;
                }
            }

            retour = convertirDescriptionsEnPhrase(descriptionsDesActivites);

            if (sommation > 1 && !(retour.equals(""))) {
                sortie += "Les dates des activités " + retour + " sont invalides. Ces activités seront ignorées.";
                messagesErreurs.add(sortie);
            } else if (!(retour.equals(""))) {
                sortie += "La date de l'activité " + retour + " est invalide. L'activité sera ignorée.";
                messagesErreurs.add(sortie);
            }
        }
    }

    /**
     * Ajoute a l'arraylist messageErreurs un message personalise si la
     * categorie n est pas reconnue
     */
    public void validerCategoriesDesActivites() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        retour = sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                ActiviteDeFormation activite = liste.get(i);
                if (activite.regroupementDesCategories(activite.getCategorie()) == -1) {
                    descriptionsDesActivites.add(activite.getDescription());
                    //retour += activite.getDescription() + " ";
                    sommation += 1;
                }
            }

            retour = convertirDescriptionsEnPhrase(descriptionsDesActivites);

            if (sommation > 1 && !(retour.equals(""))) {
                sortie += "Les activités " + retour + " sont dans des catégories non reconnues. Elles seront ignorées.";
                messagesErreurs.add(sortie);
            } else if (!(retour.equals(""))) {
                sortie += "L'activité " + retour + " est dans une catégorie non reconnue. Elle sera ignorée.";
                messagesErreurs.add(sortie);
            }
        }

    }

    public void validerHeuresTransferees() {
        if (membre.getHeuresTransferees() > 7) {
            messagesErreurs.add("Le nombre d'heures transférées est supérieur à 7. "
                    + "Seulement 7 heures seront comptabilisées.");
        } else if (membre.getHeuresTransferees() < 0) {
            messagesErreurs.add("Le nombre d'heures transférées est inférieur à 0. "
                    + "Ce nombre sera comptabilisé comme 0.");
        }
    }

    public void validerHeuresDeFormation() {
        String messageHeuresManquantes = "";
        int heuresManquantesEnGeneral;
        
        if(membre.getCycle().equals("2008-2010") || membre.getCycle().equals("2010-2012")) {
            heuresManquantesEnGeneral = 42 - heuresTotalesFormation();
        } else {
            heuresManquantesEnGeneral = 40 - heuresTotalesFormation();
        }
        
        int heuresManquantesSixCategories = 17 - heuresTotalesPourRegroupementDesSixCategories();
        if (heuresManquantesEnGeneral > 0 || heuresManquantesSixCategories > 0) {
            int heuresManquantesPourLeCycle
                    = heuresManquantesEnGeneral > heuresManquantesSixCategories ? heuresManquantesEnGeneral : heuresManquantesSixCategories;
            messageHeuresManquantes += "Il manque un total de " + heuresManquantesPourLeCycle + " heure(s) de formation pour compléter le cycle.";
            messagesErreurs.add(messageHeuresManquantes);
        }
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

    public void ecrireMessageDErreurPourHeuresInvalidesDesActivites() {
        ArrayList<ActiviteDeFormation> liste = membre.getActivitesRefusees();
        int sommation = 0;
        String retour, sortie;
        retour = sortie = "";
        ArrayList<String> descriptionsDesActivites = new ArrayList(1);
        if (liste != null) {
            for (int i = 0; i < liste.size(); ++i) {
                ActiviteDeFormation activite = liste.get(i);
                if (activite.getDureeEnHeures() < 1) {
                    descriptionsDesActivites.add(activite.getDescription());
                    sommation += 1;
                }
            }
            retour = convertirDescriptionsEnPhrase(descriptionsDesActivites);
        }
        if (sommation > 1 && !(retour.equals(""))) {
            sortie += "Les heures des activités " + retour + " sont invalides. Ces activités seront ignorées.";
            messagesErreurs.add(sortie);
        } else if (!(retour.equals(""))) {
            sortie += "Les heures de l'activité " + retour + " sont invalides. Cette activité sera ignorée.";
            messagesErreurs.add(sortie);
        }
    }

    /**
     * La formation est complète si le cycle est valide et si les heures totales sont au moins 40 
     * (42 heures pour les cycles 2008-2010 et 2010-2012), 
     * dont au moins 17 dans le regroupement #1 dees catégories (groupe des 6 catégories).
     * 
     * @return
     */
    public boolean formationComplete() {
        boolean heuresTotauxValides;    // heures valides selon le cycle
        
        if(membre.getCycle().equals("2008-2010") || membre.getCycle().equals("2010-2012")) {
            heuresTotauxValides = (heuresTotal >= 42 && cycleEstValide() && (nombreDHeuresSelonRegroupement(1) >= 17));
        } else {
            heuresTotauxValides = (heuresTotal >= 40 && cycleEstValide() && (nombreDHeuresSelonRegroupement(1) >= 17));
        }
        
        return heuresTotauxValides;
    }

}
