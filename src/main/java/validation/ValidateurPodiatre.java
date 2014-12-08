package validation;

import professionnels.Membre;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class ValidateurPodiatre extends ValidateurGeologue {

    public ValidateurPodiatre(Membre podiatre) {
        super(podiatre);
    }

    @Override
    public void messageErreurPourHeuresManquantes() {
        int heuresManquantesEnGeneral = 60 - heuresTotalesFormation();
        int heuresManquantesCours = 22 - heuresBrutesSelonCategorie("cours");
        int heuresManquantesRecherche = 3 - heuresBrutesSelonCategorie("projet de recherche");
        int heuresManquantesDiscussion = 1 - heuresBrutesSelonCategorie("groupe de discussion");
        // Le nombre d'heures manquantes est la somme des heures manquantes par catégorie, si supérieure au total brut
        int grandMaximum = maximumEntreUnNombreEtUnTripletSomme(heuresManquantesEnGeneral, heuresManquantesCours,
                heuresManquantesRecherche, heuresManquantesDiscussion);
        ecrireMessageErreurPourHeuresManquantes(grandMaximum);
    }

    @Override
    public boolean formationComplete() {
        boolean critereCours = heuresBrutesSelonCategorie("cours") >= 22;
        boolean critereRecherche = heuresBrutesSelonCategorie("projet de recherche") >= 3;
        boolean critereDiscussion = heuresBrutesSelonCategorie("groupe de discussion") >= 1;
        boolean critereDHeuresTotales = 60 <= heuresTotalesFormation();
        return validerLeCycle() && critereCours && critereRecherche
                && critereDiscussion && critereDHeuresTotales;
    }

}
