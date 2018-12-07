package models.WesternPack;

import models.*;

/**
 * Defines the custom behavior variations of a western soldier.
 */
public class SoldatWestern extends Soldat {
    /**
     * Calls the base {@link SoldatWestern} constructor.
     * {@inheritDoc}
     */
    public SoldatWestern(int id, String nom, int force) {
        super(id, nom, force);
    }

    @Override
    public String sePresenter() {
        String presentationMessage = super.sePresenter();

        if (this.chef != null && !this.chef.estMort()) {
            presentationMessage += " ; je me bats sous les ordres du " + this.chef.getNomComplet();
        }
        else {
            presentationMessage += " ; I'm freelance , no chief !";
        }

        return presentationMessage;
    }

    @Override
    public String getNom() {
        return "Confedere " + super.getNom();
    }
}
