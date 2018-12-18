package extensions.v2.controllers;

import controllers.GestionnaireUnivers;
import controllers.GestionnairesUnivers.GestionnaireWestern;
import extensions.v2.models.EquipmentModel;
import models.Personnage;

/**
 * The V2 western manager, which manages weapons and protections on characters.
 */
public class GestionnaireWesternV2 extends GestionnaireWestern implements BaseV2Gestionnaire {
    @Override
    public int getDegats(Personnage personnage, Personnage victime) {
        return CharacterManager.getDegats(personnage, victime);
    }

    @Override
    public String donnerEquipement(Personnage personnage, EquipmentModel equipmentModel) {
        if (personnage.isChef()
                && !GestionnaireUnivers.get_universCourrant().isChefsPeuventFrapper()) {
            return "I ain't wanting no equipment.";
        }

        if (personnage.estMort()) {
            return "Ghosts ain't able to carry, no more.";
        }

        CharacterManager.registerEquipment(personnage, equipmentModel);
        return "What a piece of GARBAGE.";
    }
}
