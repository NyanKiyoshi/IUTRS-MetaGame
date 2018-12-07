package controllers.GestionnairesUnivers;

import models.*;
import models.Exceptions.*;
import models.MedievalPack.*;

/**
 * The medieval universe controller, which defines custom messages
 * and actions for a medieval game universe. It wraps {@link Personnage}
 * and get called by {@link controllers.CRUDPersonnage}.
 */
public class GestionnaireMedieval extends BaseGestionnaireUnivers {
    @Override
    public Chef creerChef(int id, String nom, String qualificatif, int force) {
        return new ChefMedieval(id, nom, qualificatif, force);
    }

    @Override
    public Soldat creerSoldat(int id, String nom, int force) {
        return new SoldatMedieval(id, nom, force);
    }

    @Override
    public String frapper(Personnage agresseur, Personnage victime) {
        try {
            agresseur.frapper(victime);
            return "Ah, maraud ! Tu vas tâter de mon arme !";
        }
        catch (SourceMorteException e) {
            return "Hélas ! J'ai rendu mon âme à Dieu, je ne puis plus frapper personne...";
        }
        catch (CibleMorteException e) {
            return "Que puis-je faire d'un soldat mort !?...";
        }
    }
}
