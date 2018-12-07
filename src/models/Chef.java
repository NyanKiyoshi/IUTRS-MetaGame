package models;

import models.Exceptions.SourceMorteException;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * A base {@link Chef chef}'s definition.
 */
public abstract class Chef extends Personnage {
    /**
     * Flag the {@link Personnage character} as being a chef.
     */
    private final boolean isChef = true;  // noqa

    /**
     * The chef's full name prefix.
     */
    private final String qualificatif;

    /**
     * The chef's troop composition.
     */
    protected final ArrayList<Soldat> soldatsSousOrdres = new ArrayList<>();

    /**
     * Initialize the base {@link Chef} object.
     * {@inheritDoc}
     * @param qualificatif The chef's full name prefix.
     */
    public Chef(int id, String nom, String qualificatif, int force) {
        super(id, nom, force);
        this.qualificatif = qualificatif;
    }

    /**
     * Appends a given soldier to the chef's troop.
     *
     * @param soldat The soldier to add to the troop.
     * @throws SourceMorteException Happens if the soldier to add to the troop is dead.
     */
    public void ajouterSoldat(Soldat soldat) throws SourceMorteException {
        // Raise an exception if the soldier is dead
        if (this.estMort()) {
            throw new SourceMorteException();
        }

        // Otherwise, append it to the troop
        this.soldatsSousOrdres.add(soldat);
    }

    /**
     * Clean up the chef's data.
     * Unset the chef of every soldier of their troop.
     *
     * @return Any custom message.
     */
    public String seFaireSupprimer() {
        // Unset the troop's chef
        for (Soldat soldat: soldatsSousOrdres) {
            soldat.unsetChef();
        }

        // Call the base
        return super.seFaireSupprimer();
    }

    /**
     * Makes the chef exhort themselves.
     * Must be implemented by the derived classes with a custom message.
     *
     * @return The chef's exhort message.
     */
    public abstract String exhorter();

    /**
     * Retrieves the chef's full name prefix.
     * @return The chef's full name prefix.
     */
    public String getQualificatif() {
        return qualificatif;
    }

    /**
     * Retrieves the chef's display name ({@link #getQualificatif()} + {@link #getNom()}).
     * @return The chef's display name, with their prefix.
     */
    public String getNomComplet() {
        return this.getQualificatif() + " " + this.getNom();
    }

    /**
     * Generates the comma and tab separated soldier list of the chef's troop.
     * @return The comma-separated list names of the chef's troop.
     */
    public String getNomsSoldats() {
        // Start a tab + comma separated string joiner
        StringJoiner nomsSoldats = new StringJoiner("\t, ");

        // Append every solder's name to it
        this.soldatsSousOrdres.forEach(
            soldat -> nomsSoldats.add(soldat.toString()));

        // Return the joined string
        return nomsSoldats.toString();
    }
}
