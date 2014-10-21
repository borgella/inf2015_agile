/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        int temporaire = activite.regroupementDesCategories(activite.getCategorie());
        int verifierHeures = activite.getDureeEnHeures();
        if (activite.dateActivitesCompleteesValides(activite.getDateCompletee()) && temporaire != -1 && verifierHeures > 0) {
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
