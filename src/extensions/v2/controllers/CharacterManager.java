package extensions.v2.controllers;

import extensions.v2.models.EquipmentModel;
import models.Personnage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Manages the game characters' equipments (static class).
 */
public class CharacterManager {
    private static HashMap<Personnage, ArrayList<EquipmentModel>> _equipmentCharacters
        = new HashMap<>();

    /**
     * Retrieves all the equipments of a given character. Defaulting on an empty array.
     * @param personnage The character to look for equipments.
     * @return The equipments of the given character.
     */
    public static ArrayList<EquipmentModel> getEquipments(Personnage personnage) {
        return _equipmentCharacters.computeIfAbsent(
            personnage, k -> new ArrayList<>());
    }

    /**
     * Lists the equipments of the characters.
     * @param personnage The character to list the equipment from.
     * @return The list of the equipments description.
     */
    public static String listEquipments(Personnage personnage) {
        ArrayList<EquipmentModel> listEquipments = getEquipments(personnage);

        // If the character has no equipment, do nothing
        if (listEquipments.isEmpty()) {
            return "";
        }

        // Start a comma separated string joiner
        StringJoiner descriptionEquipments = new StringJoiner(", ");

        // Append every solder's name to it
        getEquipments(personnage).forEach(
            equipmentModel -> descriptionEquipments.add(equipmentModel.toString()));

        return " ; Mes équipements : " + descriptionEquipments.toString();
    }

    /**
     * Add a given equipment to a given character.
     * @param personnage The character that should receive the given equipment.
     * @param equipment The equipment to give.
     */
    public static void registerEquipment(Personnage personnage, EquipmentModel equipment) {
        ArrayList<EquipmentModel> equipments = getEquipments(personnage);
        equipments.add(equipment);
    }

    /**
     * Get the sum of all the protection values of the equipments.
     * @param personnage The character to get the equipments from.
     * @return The protection sum of the given character.
     */
    public static int getTotalProtection(Personnage personnage) {
        int totalProtection = 0;

        for (EquipmentModel equipment : getEquipments(personnage)) {
            totalProtection += equipment.valueProtection;
        }

        return totalProtection;
    }

    /**
     * Get the sum of all the damage values of the equipments,
     * in addition the to character's base force.
     *
     * @param personnage The character to get the equipments from.
     * @return The force (damage) sum of the given character.
     */
    public static int getTotalForce(Personnage personnage) {
        int totalDamages = personnage.getForce();

        for (EquipmentModel equipment : getEquipments(personnage)) {
            totalDamages += equipment.valeurDegats;
        }

        return totalDamages;
    }

    /**
     * Calculates the total damages that a given target should get from
     * an attacker. Which is: {@code Sum(forces) - Sum(Protections)}.
     *
     * The returned value is always positive.
     *
     * @param attacker The character that is attacking, from which we retrieve the forces.
     * @param victim The character is getting attacked, from which we retrieve the protections.
     *
     * @return The total damages that the victim should take, always a positive value.
     */
    public static int getDegats(Personnage attacker, Personnage victim) {
        return Math.max(
            0,
            getTotalForce(attacker) - getTotalProtection(victim)
        );
    }
}
