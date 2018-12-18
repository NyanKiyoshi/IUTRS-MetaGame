package views.Scenarios;

import controllers.CRUDPersonnage;
import controllers.Service;
import extensions.v2.controllers.ServiceV2;
import extensions.v2.controllers.V2Loader;
import models.Exceptions.PersonneInexistanteException;

import javax.naming.OperationNotSupportedException;

/**
 * The second scenario.
 */
public class Scenario2EntryPoint {
    public static Void scenario() throws PersonneInexistanteException, OperationNotSupportedException {
        System.out.println("=================SCENARIO 2 ====================");
        System.out.println("================= Chargement de l'extension (V2) ===============");
        new V2Loader().Load();

        System.out.println("================= les cas standards ===============");

        int p1 = Service.creerSoldat("Alex    ", 10);
        int p2 = Service.creerSoldat("Bertrand", 20);
        int p3 = Service.creerSoldat("Carolus ", 30);
        int p4 = Service.creerSoldat("Denis   ", 20);
        int p5 = Service.creerSoldat("Ezechiel", 10);
        int p6 = Service.creerChef("Henri", " celebre ");
        int p7 = Service.creerChef("Louis", "terrible");
        int p8 = Service.creerSoldat("LeFort", 50);

        System.out.println("================= Ajout d'armes ===============");
        System.out.println(ServiceV2.armer(p3, "Glave", 500));
        System.out.println(ServiceV2.proteger(p1, "Bouclier", 5));
        System.out.println(ServiceV2.proteger(p4, "Bouclier", 7));
        System.out.println(ServiceV2.armer(p6, "blah", 10));

        System.out.println("=================Presentation individuelle ====================");
        System.out.println(Service.presentation(p1));
        System.out.println(Service.presentation(p6));
        System.out.println("================= enrolement ====================");
        System.out.println(Service.confier(p1, p6));
        System.out.println(Service.confier(p2, p6));
        System.out.println(Service.confier(p3, p7));
        System.out.println(Service.confier(p4, p7));
        System.out.println(Service.confier(p8, p7));
        System.out.println();
        System.out.println("===========Presentation generale avant combats ===============");
        for (String pres : Service.presentationGenerale())
            System.out.println(pres);
        System.out.println("===========  Exhortation ===============");
        System.out.println(Service.exhorter(p6));
        System.out.println("================bagarres======================");
        System.out.println(Service.frapper(p1, p4));
        System.out.println(Service.frapper(p2, p4));
        System.out.println(Service.frapper(p4, p1));
        System.out.println(Service.frapper(p4, p6));
        System.out.println(Service.frapper(p3, p6));
        System.out.println(Service.frapper(p3, p6));
        System.out.println(Service.frapper(p3, p6));
        System.out.println(Service.frapper(p2, p4));
        System.out.println(Service.frapper(p2, p4));
        System.out.println(Service.frapper(p2, p4));
        System.out.println(Service.frapper(p1, p4));
        System.out.println(Service.frapper(p8, p5));
        System.out.println(Service.frapper(p8, p5));
        System.out.println("==============Presentation generale après les combats ====================");
        for (String pres : Service.presentationGenerale())
            System.out.println(pres);
        System.out.println("================= solliciter les morts ==================");
        System.out.println(Service.exhorter(p6));
        System.out.println(Service.frapper(p4, p1));
        System.out.println(Service.confier(p5, p7));
        int p9 = Service.creerSoldat("Gael", 10);
        System.out.println(Service.confier(p9, p6));
        System.out.println(ServiceV2.armer(p6, "blah", 10));

        // Delete a dead chef
        Service.supprimerPers(p6);

        // Give a new chef to the loner
        Service.confier(p1, p7);

        try {
            // Attempt to delete a solder alive
            Service.supprimerPers(p1);
            throw new AssertionError("Managed to delete someone alive.");
        }
        catch (UnsupportedOperationException exc) {
            System.err.println(exc.getMessage());
        }

        // Print the presentation of a solder without a chef
        System.out.println(Service.presentation(p1));

        // Print the presentation of a delete chef
        try {
            System.out.println(Service.presentation(p6));
            throw new AssertionError("The character was not deleted.");
        }
        catch (PersonneInexistanteException exc) {
            System.err.println(exc.getMessage());
        }

        // Attempt to do a full wipe
        CRUDPersonnage.tuerEtToutSupprimer();
        return null;
    }

    public static void main(String[] args) throws PersonneInexistanteException, OperationNotSupportedException {
        scenario();
    }
}
