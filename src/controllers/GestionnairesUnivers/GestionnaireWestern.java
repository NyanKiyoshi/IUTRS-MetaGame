package controllers.GestionnairesUnivers;

import models.*;
import models.Exceptions.*;

/**
 * The western universe controller, which defines custom messages
 * and actions for a western game universe. It wraps {@link Personnage}
 * and get called by {@link controllers.CRUDPersonnage}.
 */
public class GestionnaireWestern extends BaseGestionnaireUnivers {
    @Override
    public String getNomChef(Chef chef) {
        return "Capitaine " + chef.getNom();
    }

    @Override
    public String getNomSoldat(Soldat soldat) {
        return "Confedere " + soldat.getNom();
    }

    @Override
    public String frapper(Personnage agresseur, Personnage victime) {
        try {
            this.donnerCoup(agresseur, victime);
            return "Damned ! sus aux yankees et ralliez-vous à mon étendard !";
        }
        catch (SourceMorteException e) {
            return "I'm dead ! How could I shoot anyone...";
        }
        catch (CibleMorteException e) {
            return "Que puis-je faire d'un soldat mort !?...";
        }
    }

    @Override
    public String exhorter(Chef chef) {
        if (chef.estMort()) {
            return "Je ne peux hélas plus exhorter personne (sigh !) ; mettez au moins sur ma tombe mon étendard !";
        }
        return "Damned ! sus aux yankees et ralliez-vous à mon étendard !";
    }

    @Override
    public String presenterChef(Chef chef) {
        String chefCommentsFormat;

        if (chef.estMort()) {
            chefCommentsFormat =
                " ; Well... J'ai sous mes ordres une troupe de %d vaillants confédérés : [%s]";
        }
        else {
            chefCommentsFormat =
                " ; Well, I'affraid I'm dead ; J'avais pourtant sous mes ordres une troupe de %d vaillants confédérés : [%s]";
        }

        return String.format(
            chefCommentsFormat, chef.getSoldatsSousOrdres().size(), chef.getNomsSoldats());
    }

    @Override
    public String presenterSoldat(Soldat soldat) {
        Chef chef = soldat.getChef();

        if (chef != null && !soldat.getChef().estMort()) {
            return " ; je me bats sous les ordres du " + chef.getNomComplet();
        }
        else {
            return  " ; I'm freelance , no chief !";
        }
    }
}
