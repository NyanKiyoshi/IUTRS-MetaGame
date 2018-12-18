package extensions.v2.controllers;

import controllers.GestionnaireUnivers;
import controllers.GestionnairesUnivers.GestionnaireMedieval;
import extensions.v2.models.EquipmentModel;
import models.Personnage;

/**
 * The V2 medieval manager, which manages weapons and protections on characters.
 */
public class GestionnaireMedievalV2 extends GestionnaireMedieval implements BaseV2Gestionnaire {
    @Override
    public int getDegats(Personnage personnage, Personnage victime) {
        return CharacterManager.getDegats(personnage, victime);
    }

    @Override
    public String donnerEquipement(Personnage personnage, EquipmentModel equipmentModel) {
        if (personnage.isChef()
                && !GestionnaireUnivers.get_universCourrant().isChefsPeuventFrapper()) {
            return "Non merci. Mes mains et mon corps nus me suffisent.";
        }

        if (personnage.estMort()) {
            return "Je suis mort. Que puis-je faire d'un tel équipement ?!";
        }

        CharacterManager.registerEquipment(personnage, equipmentModel);
        return "Très belle pièce. Je saurais en faire bon usage.";
    }
}
