package extensions.v2.controllers;

import extensions.v2.models.EquipmentModel;
import models.Personnage;

public interface BaseV2Gestionnaire {
    String donnerEquipement(Personnage personnage, EquipmentModel equipmentModel);
}
