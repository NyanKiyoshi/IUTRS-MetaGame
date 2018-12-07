package models.MedievalPack;

import models.*;

/**
 * Defines the custom behavior variations of a medieval soldier.
 */
public class SoldatMedieval extends Soldat {
    /**
     * Calls the base {@link SoldatMedieval} constructor.
     * {@inheritDoc}
     */
    public SoldatMedieval(int id, String nom, int force) {
        super(id, nom, force);
    }

    @Override
    public String sePresenter() {
        String presentationMessage = super.sePresenter();

        if (this.chef != null && !this.chef.estMort()) {
            presentationMessage += " ; J'ai l'honneur de servir, après Dieu, le " + this.chef.getNomComplet();
        }
        else {
            presentationMessage += " ; Je suis un chevalier libre, je ne sers aucun seigneur !";
        }

        return presentationMessage;
    }

    @Override
    public String getNom() {
        return "CHEVALIER " + super.getNom();
    }
}
