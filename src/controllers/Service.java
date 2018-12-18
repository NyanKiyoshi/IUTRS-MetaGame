package controllers;

import models.*;
import models.Exceptions.*;
import models.Univers.BaseUnivers;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The {@code MetaGame}'s main controller service, dispatching around the other controllers.
 */
public class Service {
    /**
     * Creates a {@link Soldat soldier} with given parameters, and returns it.
     *
     * @param nom The soldier's name.
     * @param force The soldier's hitting force.
     * @return The created soldier's identifier.
     */
    public static int creerSoldat(String nom, int force) {
        return CRUDPersonnage.creerSoldat(nom, force).getId();
    }

    /**
     * Creates a {@link Chef chef} with given parameters, and returns it.
     *
     * @param nom The chef' names.
     * @param qualificatif The chef's full name prefix.
     * @param force The chef's hitting force (if any).
     * @return The created chef's identifier.
     */
    public static int creerChef(String nom, String qualificatif, int force) {
        return CRUDPersonnage.creerChef(nom, qualificatif, force).getId();
    }

    /**
     * Creates a base {@link Chef chef} that has a default force or none
     * (see {@link #creerChef(String, String, int)}).
     *
     * @param nom The chef' names.
     * @param qualificatif The chef's full name prefix.
     * @return The created chef's identifier.
     */
    public static int creerChef(String nom, String qualificatif) {
        return creerChef(
            nom, qualificatif, GestionnaireUnivers.get_universCourrant().getForceParDefaultChefs());
    }

    /**
     * Assigns a new {@link Chef chef} to a given {@link Soldat soldier}.
     *
     * @param idSoldat The soldier to whom to assign their new chef.
     * @param idChef The chef that will get the new soldier in their troop.
     * @throws PersonneInexistanteException Happens if one of the given identifier was non-existent.
     * @return The assignment message, if any.
     */
    public static String confier(int idSoldat, int idChef) throws PersonneInexistanteException {
        // Attempt to retrieve the targets
        Chef chef = (Chef) CRUDPersonnage.get(idChef);
        Soldat soldat = (Soldat) CRUDPersonnage.get(idSoldat);

        // Attempt to assign the chef and return the message (if any)
        return GestionnaireUnivers.get_gestionnaireUnivers().confier(chef, soldat);
    }

    /**
     * Tells a given {@link Personnage character} to introduce themselves.
     *
     * @param idPersonnage The targeted character's identifier.
     * @throws PersonneInexistanteException Happens if one of the given identifier was non-existent.
     * @return The character's introduction message.
     */
    public static String presentation(int idPersonnage) throws PersonneInexistanteException {
        return GestionnaireUnivers.get_gestionnaireUnivers()
            .sePresenter(CRUDPersonnage.get(idPersonnage));
    }

    /**
     * Invokes every managed character to introduce themselves.
     * @return The introduction of every character.
     */
    public static Collection<String> presentationGenerale() {
        Collection<String> returnedPresentations = new ArrayList<>();

        CRUDPersonnage.getPersonnages().forEach(
            personnage -> returnedPresentations.add(
                GestionnaireUnivers.get_gestionnaireUnivers().sePresenter(personnage)
            )
        );

        return returnedPresentations;
    }

    /**
     * Tells a given {@link Chef chef} to exhort themselves.
     *
     * @param idChef The {@link Chef chef} to exhort.
     * @throws PersonneInexistanteException Happens if one of the given identifier was non-existent.
     * @return The exhortation of the given {@link Chef chef}.
     */
    public static String exhorter(int idChef) throws PersonneInexistanteException {
        return GestionnaireUnivers.get_gestionnaireUnivers()
            .exhorter((Chef) CRUDPersonnage.get(idChef));
    }

    /**
     * Instructs to delete a given character to hit a given other.
     *
     * @param idAgresseur The character that must give the punch (or else).
     * @param idVictime The character that must receive the punch.
     * @throws OperationNotSupportedException Happens when the user attempt to make
     *      a chef hit a {@link Personnage character} but the {@link BaseUnivers universe} does not allow it.
     * @throws PersonneInexistanteException Happens if one of the given identifier was non-existent.
     * @return The hit message, if any.
     */
    public static String frapper(int idAgresseur, int idVictime)
            throws OperationNotSupportedException, PersonneInexistanteException {
        Personnage agresseur = CRUDPersonnage.get(idAgresseur);
        Personnage victime = CRUDPersonnage.get(idVictime);

        // Check if a chef can hit or not in the current universe
        if (agresseur.isChef()
                && GestionnaireUnivers.get_universCourrant().isChefsPeuventFrapper()) {
            throw new OperationNotSupportedException("Ce chef ne sait pas frapper.");
        }

        return GestionnaireUnivers.get_gestionnaireUnivers().frapper(agresseur, victime);
    }

    /**
     * Instructs to delete a given character.
     *
     * @param idPers The character's identifier to get deleted.
     * @throws PersonneInexistanteException Happens if one of the given identifier was non-existent.
     * @return The character deletion message, if any.
     */
    public static String supprimerPers(int idPers) throws PersonneInexistanteException {
        return CRUDPersonnage.supprimer(CRUDPersonnage.get(idPers));
    }
}
