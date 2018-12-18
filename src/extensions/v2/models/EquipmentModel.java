package extensions.v2.models;

/**
 * A model that describes an equipment for characters.
 */
public class EquipmentModel {
    /**
     * The name of the equipment.
     */
    public final String nom;

    /**
     * The hit force of the equipment.
     */
    public final int valeurDegats;

    /**
     * The hit protection value of the equipment.
     */
    public final int valueProtection;

    /**
     * Initialize an equipment from given values.
     *
     * @param nom The name of the equipment.
     * @param valeurDegats The hit force.
     * @param valueProtection The protection value.
     */
    public EquipmentModel(String nom, int valeurDegats, int valueProtection) {
        this.nom = nom;
        this.valeurDegats = valeurDegats;
        this.valueProtection = valueProtection;
    }
}
