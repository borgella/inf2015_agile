package validation;

import java.util.ArrayList;
import professionnels.Membre;
import professionnels.Podiatre;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurPodiatre extends ValidateurGeologue {

    private Podiatre membre;
    private ArrayList<String> messagesErreurs;
    private int heuresTotal;

    public ValidateurPodiatre(Membre podiatre) {
        super(podiatre);
        this.membre = (Podiatre) podiatre;
        messagesErreurs = new ArrayList(1);
        heuresTotal = 0;
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
    public boolean formationComplete() {
        boolean critereCours = heuresBrutesSelonCategorie("cours") >= 22;
        boolean critereRecherche = heuresBrutesSelonCategorie("projet de recherche") >= 3;
        boolean critereDiscussion = heuresBrutesSelonCategorie("groupe de discussion") >= 1;
        boolean critereDHeuresTotales = heuresTotal >= 60;
        return validerLeCycle() && critereCours && critereRecherche
                && critereDiscussion && critereDHeuresTotales;
    }

}
