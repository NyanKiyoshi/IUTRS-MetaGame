package models;

import models.Exceptions.CibleMorteException;
import models.Exceptions.SourceMorteException;

/**
 * A base {@link Soldat soldier}'s definition.
 */
public abstract class Soldat extends Personnage {
    /**
     * The soldier's associated current chef (nullable).
     */
    protected Chef chef;

    /**
     * Initialize the base {@link Soldat} object.
     *
     * @param id Their identifier.
     * @param nom Their names.
     * @param force Their hitting force.
     */
    public Soldat(int id, String nom, int force) {
        super(id, nom, force);
    }

    /**
     * Sets the soldier's chef.
     *
     * @param chef The soldier's new chef (nullable).
     *
     * @throws SourceMorteException Happens when this character is dead,
     *                              they refuse to receive a chef when dead.
     * @throws CibleMorteException Happens when the given chef is dead (if any given),
     *                              they refuse to receive a dead chef.
     */
    public void setChef(Chef chef) throws SourceMorteException, CibleMorteException {
        if (this.estMort()) {
            throw new SourceMorteException();
        }
        if (chef != null && chef.estMort()) {
            throw new CibleMorteException();
        }
        this.chef = chef;
    }

    /**
     * Drops the soldier's current chef (if any).
     * And removes themselves from chef's troop list.
     */
    public void unsetChef() {
        if (this.chef != null) {
            this.chef.soldatsSousOrdres.remove(this);
        }
        this.chef = null;
    }
}
