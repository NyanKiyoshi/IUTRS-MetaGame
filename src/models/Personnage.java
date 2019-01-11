package models;

/**
 * Defines a base character's data and behaviors.
 */
public abstract class Personnage {
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
     * Removes a given life amount to the character's current life.
     *
     * The value can be negative if the damages are higher than the character's current life.
     *
     * @param damages The life to remove (can be greater than the character's remaining life).
     * @return The total damages made.
     */
    public int donnerDegats(int damages) {
        this.vie -= damages;
        return damages;
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
     * A method that clean up the character whenever invoked for proper deletion.
     * @return A custom message, if any.
     */
    public String seFaireSupprimer() {
        // Nothing to do by default
        return "Je suis désormais supprimé...";
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
     * Whether this character type is a chef.
     * A base character is not a chef by default.
     *
     * @return True if the character is a chef;
     */
    public boolean isChef() {
        return this instanceof Chef;
    }

    /**
     * String representation of the character, shows their names.
     * @return The character' names.
     */
    public String toString() {
        return this.nom;
    }
}
