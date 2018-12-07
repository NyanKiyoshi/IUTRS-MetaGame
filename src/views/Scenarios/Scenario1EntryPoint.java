package views.Scenarios;

import controllers.Service;
import models.Exceptions.PersonneInexistanteException;

import javax.naming.OperationNotSupportedException;

/**
 * The first scenario.
 */
public class Scenario1EntryPoint {
    public static Void scenario() throws PersonneInexistanteException, OperationNotSupportedException {
        System.out.println("=================SCENARIO 1 ====================");
        System.out.println("================= les cas standards ===============");

        int p1 = Service.creerSoldat("Alex    ", 10);
        int p2 = Service.creerSoldat("Bertrand", 20);
        int p3 = Service.creerSoldat("Carolus ", 30);
        int p4 = Service.creerSoldat("Denis   ", 20);
        int p5 = Service.creerSoldat("Ezechiel", 10);
        int p6 = Service.creerChef("Henri", " celebre ");
        int p7 = Service.creerChef("Louis", "terrible");
        int p8 = Service.creerSoldat("LeFort", 50);
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
        Integer p9 = Service.creerSoldat("Gael", 10);
        System.out.println(Service.confier(p9, p6));

        return null;
    }

    public static void main(String[] args) throws PersonneInexistanteException, OperationNotSupportedException {
        scenario();
    }
}
