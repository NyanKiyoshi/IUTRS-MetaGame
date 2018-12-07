package controllers;

import controllers.GestionnairesUnivers.BaseGestionnaireUnivers;
import models.*;
import models.Exceptions.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * Manages a game' {@link Personnage personnages} and their different derivations
 * ({@link Soldat soldier}, {@link Chef chef}) but while ensuring they are create
 * from the correct current universe's associated sub-class.
 *
 * This class only contains CRUD method. Creation, retrieval, deletion of chefs,
 * and soldiers (no update methods supported).
 */
public class CRUDPersonnage {
    /**
     * The next identifier to assign to any newly created {@link Personnage character}.
     */
    private static int nextID = 1;

    /**
     * The managed and known game' current {@link Personnage characters},
     * this dictionary assigns as key-pair values: {@code {characterId => character}}.
     */
    private static HashMap<Integer, Personnage> personnages = new HashMap<>();

    /**
     * Creates a new managed chef in the currently set game's universe.
     *
     * @param nom The chef' names.
     * @param qualificatif The chef's full name prefix.
     * @param force The chef's force (if any).
     * @return The created chef.
     */
    public static Chef creerChef(String nom, String qualificatif, int force) {
        // Retrieves the current universe's controller
        BaseGestionnaireUnivers gestionnaireUnivers = GestionnaireUnivers.get_gestionnaireUnivers();

        // Create a chef of the current universe
        int personnageID = nextID++;
        Chef createdPersonnage = gestionnaireUnivers.creerChef(personnageID, nom, qualificatif, force);

        // Register the character and return it
        personnages.put(personnageID, createdPersonnage);
        return createdPersonnage;
    }

    /**
     * Creates a new managed soldier in the currently set game's universe.
     *
     * @param nom The soldier' names.
     * @param force The soldier's force (if any).
     * @return The created soldier.
     */
    public static Soldat creerSoldat(String nom, int force) {
        // Retrieves the current universe's controller
        BaseGestionnaireUnivers gestionnaireUnivers = GestionnaireUnivers.get_gestionnaireUnivers();

        // Create a chef of the current universe
        int personnageID = nextID++;
        Soldat createdPersonnage = gestionnaireUnivers.creerSoldat(personnageID, nom, force);

        // Register the character and return it
        personnages.put(personnageID, createdPersonnage);
        return createdPersonnage;
    }

    /**
     * Retrieves a given {@link Personnage character} from a given identifier.
     *
     * @param id The {@link Personnage character}'s identifier.
     * @throws PersonneInexistanteException Thrown whenever a non-existing {@link Personnage character} identifier
     *                                      was passed (e.g.: deleted character).
     * @return The found character.
     */
    public static Personnage get(int id) throws PersonneInexistanteException {
        // Attempt to find the given character (null if not found)
        Personnage foundPersonnage = personnages.get(id);

        // Throw an error if the character was not found
        if (foundPersonnage == null) {
            throw new PersonneInexistanteException("Pas de personnage #" + id);
        }

        // Return the character that we found
        return foundPersonnage;
    }

    /**
     * Cleanly deletes a given {@link Personnage character}.
     * Invoking {@link Personnage#seFaireSupprimer()}.
     *
     * @param personnage The character to delete.
     * @throws UnsupportedOperationException
     *      Thrown if the user is attempting to delete a character that is still alive.
     * @return The deletion message, if any.
     */
    public static String supprimer(Personnage personnage) throws UnsupportedOperationException {
        // If the character is still alive, refuse to delete it
        if (!personnage.estMort()) {
            throw new UnsupportedOperationException(
                "Impossible de supprimer un personnage vivant");
        }

        // Remove the character from the managed list
        // and instruct the character to cleanly delete itself
        personnages.remove(personnage.getId());
        return personnage.seFaireSupprimer();
    }

    /**
     * Kills every existing (managed) {@link Personnage characters}, and then, deletes them.
     *
     * This allows to clean up the battle field upon God's order.
     */
    public static void tuerEtToutSupprimer() {
        // 1. Instruct every character to kill themselves (without cleaning them up);
        // 2. Then, remove every managed character from the list.
        personnages.values().forEach(Personnage::tuer);
        personnages.clear();
    }

    /**
     * Retrieves the list of managed characters.
     * @return The {@link Personnage character} list;
     */
    public static Collection<Personnage> getPersonnages() {
        return personnages.values();
    }
}
