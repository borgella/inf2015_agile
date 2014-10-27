package declaration;

import java.util.ArrayList;

/**
 *
 * @author Chelny Duplan, Jason Drake, Jean Mary Borgella
 */
public class DeclarationDeFormation {

    protected final String numeroDePermis;
    protected final String ordre;
    protected final String cycle;
    protected int heuresTransferees;
    protected ArrayList<ActiviteDeFormation> activitesAcceptees;
    protected ArrayList<ActiviteDeFormation> activitesRefusees;

    public DeclarationDeFormation(String numeroDepermis, String ordre, String cycle) {
        this.numeroDePermis = numeroDepermis;
        this.ordre = ordre;
        this.cycle = cycle;
        activitesAcceptees = new ArrayList(1);
        activitesRefusees = new ArrayList(1);
    }

    public DeclarationDeFormation(String numeroDepermis, String ordre, String cycle, int heuresTransferees) {
        this.numeroDePermis = numeroDepermis;
        this.ordre = ordre;
        this.cycle = cycle;
        this.heuresTransferees = heuresTransferees;
        activitesAcceptees = new ArrayList(1);
        activitesRefusees = new ArrayList(1);
    }

    public void ajouterActivite(ActiviteDeFormation activite) {
        if (activite.dateActivitesCompleteesValides() && activite.aCategorieValide()) {
            activitesAcceptees.add(activite);
        } else {
            activitesRefusees.add(activite);
        }
    }

    public String getNumeroDePermis() {
        return numeroDePermis;
    }

    public String getOrdre() {
        return ordre;
    }

    public String getCycle() {
        return cycle;
    }

    public int getHeuresTransferees() {
        return heuresTransferees;
    }

    public ArrayList getActivitesAcceptees() {
        return activitesAcceptees;
    }

    public ArrayList getActivitesRefusees() {
        return activitesRefusees;
    }

}
