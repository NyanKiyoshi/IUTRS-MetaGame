package controllers.GestionnairesUnivers;

import models.*;
import models.Exceptions.*;

/**
 * The medieval universe controller, which defines custom messages
 * and actions for a medieval game universe. It wraps {@link Personnage}
 * and get called by {@link controllers.CRUDPersonnage}.
 */
public class GestionnaireMedieval extends BaseGestionnaireUnivers {
    @Override
    public String getNomChef(Chef chef) {
        return "SEIGNEUR " + chef.getNom();
    }

    @Override
    public String getNomSoldat(Soldat soldat) {
        return "CHEVALIER " + soldat.getNom();
    }

    @Override
    public String frapper(Personnage agresseur, Personnage victime) {
        try {
            this.donnerCoup(agresseur, victime);
            return "Ah, maraud ! Tu vas tâter de mon arme !";
        }
        catch (SourceMorteException e) {
            return "Hélas ! J'ai rendu mon âme à Dieu, je ne puis plus frapper personne...";
        }
        catch (CibleMorteException e) {
            return "Que puis-je faire d'un soldat mort !?...";
        }
    }

    @Override
    public String exhorter(Chef chef) {
        if (chef.estMort()) {
            return "Je ne peux hélas plus exhorter personne ; mettez au moins sur ma tombe mon panache blanc !";
        }
        return "Palsambleu ! trucidez-moi tous ces marauds et ralliez-vous à mon panache blanc !";
    }

    @Override
    public String presenterChef(Chef chef) {
        String chefCommentsFormat;

        if (chef.estMort()) {
            chefCommentsFormat =
                " ; Helas ! j'ai rendu mon âme à Dieu ; J'avais pourtant sous mes ordres une troupe de %d valeureux chevaliers : [%s]";
        }
        else {
            chefCommentsFormat =
                " ; J'ai sous mes ordres une troupe de %d valeureux chevaliers, Morbleu ! ils ont pour nom [%s]";
        }

        return String.format(
            chefCommentsFormat, chef.getSoldatsSousOrdres().size(), chef.getNomsSoldats());
    }

    @Override
    public String presenterSoldat(Soldat soldat) {
        Chef chef = soldat.getChef();

        if (chef != null && !chef.estMort()) {
            return " ; J'ai l'honneur de servir, après Dieu, le " + chef.getNomComplet();
        }
        else {
            return " ; Je suis un chevalier libre, je ne sers aucun seigneur !";
        }
    }
}
