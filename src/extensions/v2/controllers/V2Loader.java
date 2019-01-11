package extensions.v2.controllers;

import controllers.GestionnaireUnivers;
import controllers.GestionnairesUnivers.BaseGestionnaireUnivers;
import controllers.GestionnairesUnivers.GestionnaireMedieval;
import controllers.GestionnairesUnivers.GestionnaireWestern;
import extensions.IExtensionDeGestionnaire;

/**
 * The V2 extension's loader, need to get called to activate it
 * for the extension to wrap the universes.
 */
public class V2Loader implements IExtensionDeGestionnaire {
    @Override
    public void Load() {
        BaseGestionnaireUnivers gestionnaireUniversCourant
            = GestionnaireUnivers.get_gestionnaireUnivers();

        if (gestionnaireUniversCourant instanceof GestionnaireMedieval) {
            GestionnaireUnivers.set_gestionnaireUnivers(new GestionnaireMedievalV2());
        }
        else if (gestionnaireUniversCourant instanceof GestionnaireWestern) {
            GestionnaireUnivers.set_gestionnaireUnivers(new GestionnaireWesternV2());
        }
        else {
            throw new UnsupportedOperationException(String.format(
                "%s is an unsupported universe for the %s extension",
                gestionnaireUniversCourant,
                V2Loader.class.getName()
            ));
        }
    }
}
