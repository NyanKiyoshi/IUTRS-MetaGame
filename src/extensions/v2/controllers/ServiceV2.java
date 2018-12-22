package extensions.v2.controllers;

import controllers.CRUDPersonnage;
import controllers.GestionnaireUnivers;
import controllers.GestionnairesUnivers.BaseGestionnaireUnivers;
import extensions.v2.models.EquipmentModel;
import models.Exceptions.PersonneInexistanteException;
import models.Personnage;

/**
 * A service that gives equipments to characters.
 */
public class ServiceV2 {
    /**
     * Registers a new equipment with given force and protection to a given character.
     *
     * @param idSoldat The equipment receiver.
     * @param nomEquip The name of the equipment.
     * @param puissance The hit force of the equipment.
     * @param protection The hit protection of the equipment.
     *
     * @return The soldier/ character response message, if any.
     * @throws PersonneInexistanteException Whenever the given character is non existent.
     */
    private static String ajouterEquipement(int idSoldat, String nomEquip, int puissance, int protection)
            throws PersonneInexistanteException {

        Personnage personnage = CRUDPersonnage.get(idSoldat);
        EquipmentModel equipmentModel
            = new EquipmentModel(nomEquip, puissance, protection);

        BaseGestionnaireUnivers gestionnaireUnivers = GestionnaireUnivers.get_gestionnaireUnivers();

        // Check the universe manager was correctly changed before casting
        if ( !(gestionnaireUnivers instanceof BaseV2Gestionnaire) ) {
            throw new RuntimeException(String.format(
                "Expected a `%s` universe manager, but got `%s` instead",
                BaseV2Gestionnaire.class, gestionnaireUnivers.getClass()
            ));
        }

        return ((BaseV2Gestionnaire) gestionnaireUnivers)
            .donnerEquipement(personnage, equipmentModel);
    }

    /**
     * Donne au soldat identifie idSoldat une arme avec son nom et sa puissance offensive ;
     *
     * @param idSoldat The soldier's identifer (or character ID if the universe allows this behavior).
     * @param nomArme The name of the weapon.
     * @param puiss The weapon force.

     * @return Un message eventuel.
     * @throws PersonneInexistanteException Whenever the given character is non existent.
     */
    public static String armer(int idSoldat, String nomArme, Integer puiss)
            throws PersonneInexistanteException {

        return ajouterEquipement(idSoldat, nomArme, puiss, 0);
    }

    /**
     * Donne au soldat identifie @link{idSoldat} une protection
     * avec son @link{nomProtec nom} et son @link{efficacite} ;
     *
     * @param idSoldat The soldier's identifer (or character ID if the universe allows this behavior).
     * @param nomProtec The name of the protection.
     * @param efficacite The protection value.
     *
     * @return Un message eventuel.
     * @throws PersonneInexistanteException Whenever the given character is non existent.
     */
    public static String proteger(int idSoldat, String nomProtec, Integer efficacite)
            throws PersonneInexistanteException {

        return ajouterEquipement(idSoldat, nomProtec, 0, efficacite);
    }
}
