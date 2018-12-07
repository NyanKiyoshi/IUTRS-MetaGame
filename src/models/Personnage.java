package models;

import models.Exceptions.*;

/**
 * Defines a base character's data and behaviors.
 */
public abstract class Personnage {
    /**
     * Whether this character type is a chef.
     * A base character is not a chef by default.
     */
    private final boolean isChef = false;

    /**
     * The character's identifier.
     */
    private final int id;

    /**
     * The character' names.
     */
    private final String nom;

    /**
     * The character's hitting force (if any).
     */
    private final int force;

    /**
     * The character's current life (begins with 100).
     */
    private int vie = 100;

    /**
     * Initialize a base character's data.
     *
     * @param id Their identifier.
     * @param nom Their names.
     * @param force Their hitting force.
     */
    protected Personnage(int id, String nom, int force) {
        this.id = id;
        this.nom = nom;
        this.force = force;
    }

    /**
     * The base definition for receiving a hit from a given source.
     * This will reduce the character's life by the source's {@link #force} amount.
     *
     * @param source The character that wants to hit this character.
     * @throws CibleMorteException Happens when this character is dead, they can no longer be hit, once dead.
     */
    protected void seFaireFrapper(Personnage source) throws CibleMorteException {
        // If dead, raise an exception, characters can't hit dead peoples
        if (this.estMort()) {
            throw new CibleMorteException();
        }

        // If the character is still alive, remove the hitting force to the character's life.
        // Disclaimer: this is a requirement to allow a negative life.
        this.vie -= source.getForce();
    }

    /**
     * Instructs the character to kill themselves.
     */
    public void tuer() {
        this.vie = 0;
    }

    /**
     * Whether the character is dead or not.
     * @return {@code True} if the character has no more life points (or is negative).
     */
    public boolean estMort() {
        return this.vie <= 0;
    }

    /**
     * The base introduction of a character. This returns the following base format:
     * {@code {id}:\t{full_name} ma vie est de {remaining_life}}
     *
     * In addition, it shows the character's force, if any (non-zero and positive).
     *
     * @see Chef#getId()
     * @see Chef#getVie()
     * @see Chef#getNom()
     *
     * @return The character's base introduction text.
     */
    public String sePresenter() {
        // The base format: {id}:\t{full_name} ma vie est de {remaining_life}
        String presentationMessage = String.format(
                "%d:\t%s ma vie est de %d", this.getId(), this.getNom(), this.getVie());

        // If the character has a hitting force, show it
        if (this.force > 0) {
            presentationMessage += " ; ma force est de " + this.force;
        }

        return presentationMessage;
    }

    /**
     * A method that clean up the character whenever invoked for proper deletion.
     * @return A custom message, if any.
     */
    public String seFaireSupprimer() {
        // Nothing to do by default
        return "";
    }

    /**
     * Instructs the character to hit a given target.
     *
     * @param cible The target to hit.
     * @throws SourceMorteException Happens when this character is dead, they can't hit when being dead.
     * @throws CibleMorteException Happens when the target to hit is dead, they cannot be hit when dead.
     */
    public void frapper(Personnage cible) throws SourceMorteException, CibleMorteException {
        // If this character is dead, raise an exception, the move is invalid.
        if (this.estMort()) {
            throw new SourceMorteException();
        }

        // Otherwise, attempt to hit the target.
        cible.seFaireFrapper(this);
    }

    /**
     * Retrieves the character's identifier.
     * @return The character's identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the character' names.
     * @return The character' names.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retrieves the character's force.
     * @return The character's force.
     */
    public int getForce() {
        return force;
    }

    /**
     * Retrieves the character's current life.
     * @return The character's current life.
     */
    public int getVie() {
        return vie;
    }

    /**
     * Looks whether the character is a chef or not.
     * @return {@code True} if the character is dead.
     */
    public boolean isChef() {
        return isChef;
    }

    /**
     * String representation of the character, shows their names.
     * @return The character' names.
     */
    public String toString() {
        return this.nom;
    }
}
