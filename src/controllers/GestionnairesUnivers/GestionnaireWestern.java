package controllers.GestionnairesUnivers;

import models.*;
import models.Exceptions.*;
import models.WesternPack.*;

/**
 * The western universe controller, which defines custom messages
 * and actions for a western game universe. It wraps {@link Personnage}
 * and get called by {@link controllers.CRUDPersonnage}.
 */
public class GestionnaireWestern extends BaseGestionnaireUnivers {
    @Override
    public Chef creerChef(int id, String nom, String qualificatif, int force) {
        return new ChefWestern(id, nom, qualificatif, force);
    }

    @Override
    public Soldat creerSoldat(int id, String nom, int force) {
        return new SoldatWestern(id, nom, force);
    }

    @Override
    public String frapper(Personnage agresseur, Personnage victime) {
        try {
            agresseur.frapper(victime);
            return "Damned ! sus aux yankees et ralliez-vous à mon étendard !";
        }
        catch (SourceMorteException e) {
            return "I'm dead ! How could I shoot anyone...";
        }
        catch (CibleMorteException e) {
            return "Que puis-je faire d'un soldat mort !?...";
        }
    }
}
