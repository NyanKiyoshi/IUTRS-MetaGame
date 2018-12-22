package controllers;

import controllers.GestionnairesUnivers.*;
import models.*;
import models.Univers.BaseUnivers;
import models.Univers.UniversMedieval;
import models.Univers.UniversWestern;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Manages the game's context by retrieving (implicitly) the targeted or default universe
 * (its {@link BaseUnivers parameters}, and its {@link BaseGestionnaireUnivers character CRUD}).
 */
public class GestionnaireUnivers {
    /**
     * The current universe's context (hard-coded parameters).
     */
    private BaseUnivers _parametresUnivers;

    /**
     * The current universe's custom CRUD {@link Personnage character}
     * and miscellaneous wrapped methods.
     */
    private BaseGestionnaireUnivers _gestionnaireUnivers;

    /**
     * The global and unique instance of this controllers.
     */
    private static GestionnaireUnivers c_instance = new GestionnaireUnivers();

    /**
     * Initialize the universe context manager by reading a {@code univers.txt} file
     * in the current working directory. It defaults to the {@link UniversMedieval}.
     */
    private GestionnaireUnivers() {
        String universNameToLoad = "";

        try {
            // Attempt to read the current directory's `univers.txt` file, if any
            BufferedReader universFile = new BufferedReader(new FileReader("./univers.txt"));
            universNameToLoad = universFile.readLine().trim().toUpperCase();
        } catch (IOException ignored) {
            // Ignore file missing or the program doesn't have the proper permissions.
            // Use the default universe instead.
        }

        // Compare the string to the known universe names,
        // and initialize the found (or default) universe.
        switch (universNameToLoad) {
            case UniversWestern.NOM_UNIVERS:
                this._parametresUnivers = new UniversWestern();
                this._gestionnaireUnivers = new GestionnaireWestern();
                break;
            default:
            case UniversMedieval.NOM_UNIVERS:
                this._parametresUnivers = new UniversMedieval();
                this._gestionnaireUnivers = new GestionnaireMedieval();
                break;
        }
    }

    /**
     * Retrieves the current universe's context from the instance.
     * @return The current universe's context.
     */
    public static BaseUnivers get_universCourrant() {
        return c_instance._parametresUnivers;
    }

    /**
     * Retrieves the current universe's custom character CRUD from the instance.
     * @return The current universe's character CRUD.
     */
    public static BaseGestionnaireUnivers get_gestionnaireUnivers() {
        return c_instance._gestionnaireUnivers;
    }

    /**
     * Sets the current universe's custom character CRUD from the instance.
     *
     * @param gestionnaireUnivers The new universe manager to install.
     */
    public static void set_gestionnaireUnivers(BaseGestionnaireUnivers gestionnaireUnivers) {
        c_instance._gestionnaireUnivers = gestionnaireUnivers;
    }
}
