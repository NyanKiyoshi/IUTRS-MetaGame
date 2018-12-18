package controllers.GestionnairesUnivers;

import models.*;
import models.Exceptions.*;
import models.Univers.BaseUnivers;

/**
 * The universe controller, which defines methods wrapping {@link Personnage}
 * that will then get called by {@link controllers.CRUDPersonnage}.
 */
public abstract class BaseGestionnaireUnivers {
    /**
     * Wraps a inherited {@link Chef} constructor, allowing to define which derived {@link Chef} to use.
     * Instead uncleanly using and passing a {@link Class}'s reflection around.
     *
     * @param id The chef's identifier.
     * @param nom The chef's names.
     * @param qualificatif The chef's full name prefix.
     * @param force The chef's hitting force, if any.
     *             May not be supported by the {@link BaseUnivers current universe}.
     *
     * @return The created chef's instance.
     */
    public Chef creerChef(int id, String nom, String qualificatif, int force) {
        return new Chef(id, nom, qualificatif, force);
    }

    /**
     * Wraps a inherited {@link Soldat} constructor, allowing to define which derived {@link Soldat} to use.
     * Instead uncleanly using and passing a {@link Class}'s reflection around.
     *
     * @param id The soldier's identifier.
     * @param nom The soldier's names.
     * @param force The soldier's hitting force.
     *
     * @return The created soldier's instance.
     */
    public Soldat creerSoldat(int id, String nom, int force) {
        return new Soldat(id, nom, force);
    }

    /**
     * Retrieves the descriptive display name of a chef.
     * @param chef The chef to get the name from.
     * @return The descriptive display name of a chef.
     */
    public abstract String getNomChef(Chef chef);

    /**
     * Retrieves the descriptive display name of a soldier.
     * @param soldat The soldier to get the name from.
     * @return The descriptive display name of a soldier.
     */
    public abstract String getNomSoldat(Soldat soldat);

    /**
     * Retrieves the character's total damage force.
     * @return The character's total damage force.
     */
    public int getDegats(Personnage personnage, Personnage victime) {
        return personnage.getForce();
    }

    /**
     * The base definition for receiving a hit from a given source.
     * This will reduce the character's life by the source's damage count.
     *
     * @param victime The character that is getting hit.
     * @param degats The total life to remove from the target.
     * @throws CibleMorteException Happens when this character is dead, they can no longer be hit, once dead.
     */
    public void seFaireFrapper(Personnage victime, int degats) throws CibleMorteException {
        // If dead, raise an exception, characters can't hit dead peoples
        if (victime.estMort()) {
            throw new CibleMorteException();
        }

        // If the character is still alive, remove the hitting force to the character's life.
        // Disclaimer: this is a requirement to allow a negative life.
        victime.donnerDegats(degats);
    }

    /**
     * Throws any invalid moves (exceptions) that could occur.
     * Then, the message and throws can get customize depending by {@link BaseGestionnaireUnivers#frapper}
     * depending on the {@link BaseUnivers current universe}.
     *
     * @param agresseur The character that wants to hit someone.
     * @param victime The character that is or may be going to get hit, if the move is possible.
     */
    void donnerCoup(Personnage agresseur, Personnage victime)
            throws SourceMorteException, CibleMorteException {

        // If this character is dead, raise an exception, the move is invalid.
        if (agresseur.estMort()) {
            throw new SourceMorteException();
        }

        // If the character is still alive, remove the hitting force to the character's life.
        // Disclaimer: this is a requirement to allow a negative life.
        this.seFaireFrapper(victime, this.getDegats(agresseur, victime));
    }

    /**
     * Wraps and handles exceptions from {@link BaseGestionnaireUnivers#donnerCoup}
     * @return Whatever message to show if that's an invalid move or a success.
     */
    public abstract String frapper(Personnage agresseur, Personnage victime);

    /**
     * Associates a {@code chef} to a given soldier (using {@link Soldat#setChef(Chef)}).
     * And append the soldier to the chef's subordinated soldiers list (see {@link Chef#ajouterSoldat(Soldat)}).
     *
     * The association can fail (death of one of the targets),
     * which triggers a custom message (if any).
     *
     * @param chef The chief that will receive a new soldier under their orders.
     * @param soldat The soldier to associate the chef to.
     * @return The universe's custom message, if any.
     *      Depending on the success or the failure from an invalid move.
     */
    public String confier(Chef chef, Soldat soldat) {
        soldat.unsetChef();

        try {
            // Assign the chef to the solder, and add the solder to the chef
            soldat.setChef(chef);
            chef.ajouterSoldat(soldat);
        }
        catch (CibleMorteException e) {
            return "Ne me confiez plus personne, je suis mort...";
        }
        catch (SourceMorteException e) {
            return "Que puis-je faire d'un soldat mort !?...";
        }

        return "";
    }

    /**
     * Makes the chef exhort themselves.
     * Must be implemented by the derived classes with a custom message.
     *
     * @return The chef's exhort message.
     */
    public abstract String exhorter(Chef chef);

    /**
     * A suffix presentation for a chef.
     * @param chef The chef to present.
     * @return the presentation suffix of the chef.
     */
    public abstract String presenterChef(Chef chef);

    /**
     * A suffix presentation for a soldier.
     * @param soldat The soldier to present.
     * @return the presentation suffix of the soldier.
     */
    public abstract String presenterSoldat(Soldat soldat);

    /**
     * The base introduction of a character. This returns the following base format:
     * {@code {id}:\t{full_name} ma vie est de {remaining_life}}
     *
     * In addition, it shows the character's force, if any (non-zero and positive).
     *
     * @see Chef#getId()
     * @see Chef#getVie()
     * @see Chef#getNom()
     * @see BaseGestionnaireUnivers#getNomChef(Chef)
     * @see BaseGestionnaireUnivers#getNomSoldat(Soldat)
     *
     * @return The character's base introduction text.
     */
    public String sePresenter(Personnage personnage) {
        String suffixMessage, displayName;

        if (personnage instanceof Chef) {
            Chef chef = (Chef) personnage;
            suffixMessage = this.presenterChef(chef);
            displayName = this.getNomChef(chef);
        }
        else if (personnage instanceof Soldat) {
            Soldat soldat = (Soldat) personnage;
            suffixMessage = this.presenterSoldat(soldat);
            displayName = this.getNomSoldat(soldat);
        }
        else {
            displayName = personnage.getNom();
            suffixMessage = "";
        }

        // The base format: {id}:\t{full_name} ma vie est de {remaining_life}
        String presentationMessage = String.format(
            "%d:\t%s ma vie est de %d",
            personnage.getId(), displayName, personnage.getVie());

        // If the character has a hitting force, show it
        if (personnage.getForce() > 0) {
            presentationMessage += " ; ma force est de " + personnage.getForce();
        }

        return presentationMessage + suffixMessage;
    }
}
