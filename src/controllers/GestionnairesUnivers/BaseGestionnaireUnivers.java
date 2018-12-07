package controllers.GestionnairesUnivers;

import models.*;
import models.Exceptions.*;

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
    public abstract Chef creerChef(int id, String nom, String qualificatif, int force);

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
    public abstract Soldat creerSoldat(int id, String nom, int force);

    /**
     * Wraps a {@link Personnage#frapper(Personnage)} handling any invalid moves (exceptions)
     * that could occur. Customizing the messages depending on the derived {@link BaseGestionnaireUnivers}
     * set by the {@link BaseUnivers current universe}.
     *
     * @param agresseur The character that wants to hit someone.
     * @param victime The character that is or may be going to get hit, if the move is possible.
     *
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
}
