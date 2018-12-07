package models.WesternPack;

import models.*;

/**
 * Defines the custom behavior variations of a western chef.
 */
public class ChefWestern extends Chef {
    /**
     * Calls the base {@link Chef} constructor.
     * {@inheritDoc}
     */
    public ChefWestern(int id, String nom, String qualificatif, int force) {
        super(id, nom, qualificatif, force);
    }

    @Override
    public String exhorter() {
        if (this.estMort()) {
            return "Je ne peux hélas plus exhorter personne (sigh !) ; mettez au moins sur ma tombe mon étendard !";
        }
        return "Damned ! sus aux yankees et ralliez-vous à mon étendard !";
    }

    @Override
    public String sePresenter() {
        String presentationMessage = super.sePresenter();
        String chefCommentsFormat;

        if (this.estMort()) {
            chefCommentsFormat =
                " ; Well... J'ai sous mes ordres une troupe de %d vaillants confédérés : [%s]";
        }
        else {
            chefCommentsFormat =
                " ; Well, I'affraid I'm dead ; J'avais pourtant sous mes ordres une troupe de %d vaillants confédérés : [%s]";
        }

        presentationMessage += String.format(
                chefCommentsFormat, this.soldatsSousOrdres.size(), this.getNomsSoldats());

        return presentationMessage;
    }

    @Override
    public String getNom() {
        return "Capitaine " + super.getNom();
    }
}
