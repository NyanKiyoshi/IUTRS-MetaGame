package views;

import views.Scenarios.*;

import java.util.concurrent.Callable;

/**
 * The base (CLI) selection dialog entry point for {@code MetaGame}.
 * This manages internally the different available scenarios and allow
 * the user to select one of many (dynamically).
 */
public class BaseSelectionDialogProgram {
    /**
     * The available scenarios (callable returning nothing ({@link Void}).
     */
    private static Callable[] scenarios = new Callable[] {
        Scenario1EntryPoint::scenario,
        Scenario2EntryPoint::scenario,
        Scenario3EntryPoint::scenario
    };

    /**
     * The maximal (human readable) scenario number.
     */
    static final int maxScenarioNumber = scenarios.length;

    /**
     * Retrieve and call the given scenario index
     * (not to be confused with the scenario number).
     *
     * @param scenarioIndex the index of the scenario to run (not number).
     * @throws Exception the exception occurred during in the call or the index was invalid.
     */
    static void getScenario(int scenarioIndex) throws Exception {
        if (scenarioIndex < 0 || scenarioIndex >= maxScenarioNumber) {
            throw new IndexOutOfBoundsException("Numéro de scénario inconnu.");
        }
        scenarios[scenarioIndex].call();
    }

    /**
     * The entry point prompting the user for a scenario number,
     * that will then be run.
     *
     * @param args the passed command line arguments (unused).
     * @throws Exception a unhandled exception from a user-selected scenario.
     */
    public static void main(String[] args) throws Exception {
        // Prompt the user to input a number (in a given range)
        System.out.print("Numéro du scenario (1 à " + maxScenarioNumber + ") : ");

        // Parse the given value to a scenario index
        int scenarioNumber = Character.getNumericValue(System.in.read()) - 1;

        try {
            // Retrieve and run the scenario
            getScenario(scenarioNumber);
        }
        catch (IndexOutOfBoundsException exc) {
            System.err.println("Numéro de scénario inconnu.");
        }
    }
}
