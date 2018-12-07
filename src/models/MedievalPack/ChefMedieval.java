package models.MedievalPack;

import models.*;

/**
 * Defines the custom behavior variations of a medieval chef.
 */
public class ChefMedieval extends Chef {
    /**
     * Calls the base {@link Chef} constructor.
     * {@inheritDoc}
     */
    public ChefMedieval(int id, String nom, String qualificatif, int force) {
        super(id, nom, qualificatif, force);
    }

    @Override
    public String exhorter() {
        if (this.estMort()) {
            return "Je ne peux hélas plus exhorter personne ; mettez au moins sur ma tombe mon panache blanc !";
        }
        return "Palsambleu ! trucidez-moi tous ces marauds et ralliez-vous à mon panache blanc !";
    }

    @Override
    public String sePresenter() {
        String presentationMessage = super.sePresenter();
        String chefCommentsFormat;

        if (this.estMort()) {
            chefCommentsFormat =
                " ; Helas ! j'ai rendu mon âme à Dieu ; J'avais pourtant sous mes ordres une troupe de %d valeureux chevaliers : [%s]";
        }
        else {
            chefCommentsFormat =
                " ; J'ai sous mes ordres une troupe de %d valeureux chevaliers, Morbleu ! ils ont pour nom [%s]";
        }

        presentationMessage += String.format(
            chefCommentsFormat, this.soldatsSousOrdres.size(), this.getNomsSoldats());

        return presentationMessage;
    }

    @Override
    public String getNom() {
        return "SEIGNEUR " + super.getNom();
    }
}
