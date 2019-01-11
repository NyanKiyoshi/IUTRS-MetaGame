package views.Scenarios;

import controllers.Service;
import extensions.v2.controllers.ServiceV2;
import extensions.v2.controllers.V2Loader;
import models.Exceptions.PersonneInexistanteException;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

/**
 * The third scenario.
 */
public class Scenario3EntryPoint {
    private static void prompt(String message) throws IOException {
        System.out.print(message);
        System.in.read(new byte[2]);
        System.out.println();
    }

    public static Void scenario() throws PersonneInexistanteException, OperationNotSupportedException, IOException {
        new V2Loader().Load();

        System.out.println("==================Scénario de démonstration==================");
        System.out.println();
        prompt("// *** [1] Création de personnages (8)...");
        System.out.println();

        int soldatAlex = Service.creerSoldat("Alex", 10);    // non armé et protéger, confier a Bob
        int soldatMartin = Service.creerSoldat("Martin", 2);  //armer et proteger, confier a Bob
        int soldatJeanne = Service.creerSoldat("Jeanne", 20);   // loner, armer, confier a Dye
        int soldatHenri = Service.creerSoldat("Henri", 10);     // proteger
        int soldatCarolus = Service.creerSoldat("Carolus", 5);  // proteger et proteger
        int soldatBernard = Service.creerSoldat("Bernard", 15);  // sera mort à la fin des combats
        int soldatEntrainement = Service.creerSoldat("Mannequin en bois", 0); //efficacite des protections
        int soldatLeFort = Service.creerSoldat("Le Fort", 105); //armer et proteger
        int soldatLeopold = Service.creerSoldat("Leopold", 10);    //armer et armer

        System.out.println(Service.presentationGenerale());
        System.out.println("===============================================================");

        prompt("// *** [2] Création des chefs");
        System.out.println();
        int chefBob = Service.creerChef("Bob", "spongieux");   // restera en vie
        int chefDye = Service.creerChef("Dye", "le fragile");  // sera mort à la fin des combats

        System.out.println(Service.presentation(10));
        System.out.println(Service.presentation(11));
        System.out.println("===============================================================");

        prompt("// *** [3] Presentation d'un chef qui n'a pas de soldat...");
        System.out.println();
        System.out.println(Service.presentation(chefBob) + '\n');
        System.out.println("===============================================================");

        prompt("// *** [4] Présentation d'un soldat sans chef");
        System.out.println();
        System.out.println(Service.presentation(soldatAlex) + '\n');
        System.out.println("===============================================================");

        prompt("// *** [5] Confier des soldats à un chef : Alex et Martin au chef Bob, le soldat Jeanne au chef Dye");
        System.out.println();
        System.out.println(Service.confier(soldatAlex, chefBob));
        System.out.println(Service.confier(soldatMartin, chefBob));
        System.out.println(Service.confier(soldatJeanne, chefDye) + '\n');

        System.out.println(Service.presentation(10));
        System.out.println(Service.presentation(11));
        System.out.println("===============================================================");
        System.out.println();

        prompt("// *** [6] Confier des armes et des protections");
        System.out.println("\tPrésentation du soldat Jeanne avant l'attribution");
        System.out.println(Service.presentation(soldatJeanne));
        System.out.println("\tAttribution d'un Baton de puissance 10 au soldat Jeanne");
        System.out.println("\t\t" + ServiceV2.armer(soldatJeanne, "Baton", 10));
        System.out.println("\tPrésentation du soldat Jeanne après l'attribution");
        System.out.println("\t\t" + Service.presentation(soldatJeanne) + '\n');

        System.out.println();
        System.out.println("===============================================================");
        System.out.println();

        System.out.println("\tPrésentation du soldat Martin avant l'attribution");
        System.out.println("\t\t" + Service.presentation(soldatMartin) + '\n');
        System.out.println("\tAttribution d'une Epee de puissance 100 au soldat Martin");
        System.out.println("\t\t" + ServiceV2.armer(soldatMartin, "Epée", 100));
        System.out.println("\tPrésentation du soldat Martin après l'attribution.");
        System.out.println("\t\t" + Service.presentation(soldatMartin) + '\n');

        System.out.println();
        System.out.println("===============================================================");
        System.out.println();

        System.out.println("\tPrésentation du soldat Leopold avant l'attribution");
        System.out.println("\t\t" + Service.presentation(soldatLeopold) + '\n');
        System.out.println("\tAttribution d'une Epee de puissance 100 au soldat Leopold");
        System.out.println("\t\t" + ServiceV2.armer(soldatLeopold, "Epée", 100));
        System.out.println("\tPrésentation du soldat Leopold après l'attribution.");
        System.out.println("\t\t" + Service.presentation(soldatLeopold) + '\n');
        System.out.println("===============================================================");

        System.out.println("Attribution des protections aux soldats");

        System.out.println("===============================================================");
        System.out.println();

        System.out.println("\tAttribution d'un Bouclier anti-émeute de protection 200 au Soldat Henri");
        System.out.println("\tPrésentation du soldat Henri avant l'attribution.");
        System.out.println("\t\t" + Service.presentation(soldatHenri));
        System.out.println("\t\t" + ServiceV2.proteger(soldatHenri, "Bouclier", 200));
        System.out.println("\tPrésentation du soldat Henri après l'attribution.");
        System.out.println("\t\t" + Service.presentation(soldatHenri) + '\n');

        System.out.println();
        System.out.println("===============================================================");
        System.out.println();

        System.out.println("\tAttribution d'un Armure de protection 400 au Soldat Carolus");
        System.out.println("\tPrésentation du soldat Carolus avant l'attribution.");
        System.out.println("\t\t" + Service.presentation(soldatCarolus) + '\n');
        System.out.println("\t\t" + ServiceV2.proteger(soldatCarolus, "Armure", 400));
        System.out.println("\tPrésentation du soldat Carolus après l'attribution.");
        System.out.println("\t\t" + Service.presentation(soldatCarolus) + '\n');

        System.out.println();
        System.out.println("===============================================================");
        System.out.println();

        System.out.println("\tAttribution d'un Armure de protection 400 au Soldat LeFort");
        System.out.println("\tPrésentation du soldat LeFort avant l'attribution.");
        System.out.println("\t\t" + Service.presentation(soldatLeFort));
        System.out.println("\t\t" + ServiceV2.proteger(soldatCarolus, "Armure", 400));
        System.out.println("\tPrésentation du soldat LeFort après l'attribution.");
        System.out.println("\t\t" + Service.presentation(soldatLeFort) + '\n');

        System.out.println();
        System.out.println("===============================================================");
        System.out.println();

        // *** [6a] Confier un arme et une protection à un soldat qui a déjà été armé ou protégé
        System.out.println("\tPresentation du soldat Leopold avant l'attribution d'une arme.");
        System.out.println(Service.presentation(soldatLeopold));
        System.out.println("Attribution d'une épée avec une force de 100 au Soldat Leopold");
        System.out.println(ServiceV2.armer(soldatLeopold, "Epée", 100));
        System.out.println("\tPrésentation du soldat LeFort après l'attribution d'une arme.");
        System.out.println(Service.presentation(soldatLeopold) + '\n');

        System.out.println();
        System.out.println("===============================================================");
        System.out.println();

        System.out.println("\tPresentation du soldat Carolus avant l'attribution d'une protection.");
        System.out.println(Service.presentation(soldatCarolus));
        System.out.println("Attribution d'un Baton de puissance 10 au Soldat Carolus");
        System.out.println(ServiceV2.proteger(soldatCarolus, "Baton", 10));
        System.out.println("\tPresentation du soldat Carolus après l'attribution d'une protection.");
        System.out.println(Service.presentation(soldatCarolus) + '\n');

        System.out.println();
        System.out.println("===============================================================");
        System.out.println();

        prompt("// *** [6b] Confier une arme à un soldat protéger");
        System.out.println("\tPrésentation du soldat LeFort avant l'attribution d'une arme.");
        System.out.println(Service.presentation(soldatLeFort));
        System.out.println("Attribution d'une Armure de protection 200 au Soldat LeFort");
        System.out.println(ServiceV2.armer(soldatLeFort, "Epée", 100));
        System.out.println("\tPrésentation du soldat LeFort après l'attribution d'une arme.");
        System.out.println(Service.presentation(soldatLeFort) + '\n');

        System.out.println();
        System.out.println("===============================================================");
        System.out.println();

        prompt("// *** [6c] Confier une protection à un soldat armé");
        System.out.println("\tPrésentation du soldat Martin avant l'attribution d'une protection.");
        System.out.println(Service.presentation(soldatMartin));
        System.out.println("Attribution d'une Armure de protection 200 au Soldat Martin");
        System.out.println(ServiceV2.proteger(soldatMartin, "Armure", 200));
        System.out.println("\tPrésentation du soldat Martin après l'attribution d'une protection.");
        System.out.println(Service.presentation(soldatMartin) + '\n');

        System.out.println();
        System.out.println("===============================================================");

        prompt("// *** [7] Montrer qu'un chef ne peut avoir d'arme ou de protection");
        System.out.println("Attribution d'une arme à un chef");
        System.out.println();

        System.out.println("Attribution d'une Epee de puissance 100 au Chef Bob");
        System.out.println(ServiceV2.armer(chefBob, "Epée", 100));
        System.out.println(Service.presentation(chefBob) + '\n');

        System.out.println("===============================================================");

        System.out.println("Attribution d'une protection a un chef");
        System.out.println();

        System.out.println("-> Attribution d'un Armure de protection 400 au Chef Bob");
        System.out.println(ServiceV2.proteger(chefBob, "Armure", 400));
        System.out.println(Service.presentation(chefBob) + '\n');
        System.out.println("===============================================================");

        prompt("// *** [8] Présentation générale ");
        //     (note: Alex a désormais un chef sur sa présentation)
        System.out.println("Presentation generale (equipements et personnages confies inclus)");
        for (String presLigne : Service.presentationGenerale()) {
            System.out.println(presLigne);
        }
        System.out.println("===============================================================");
        System.out.println("Combats");

        prompt("// *** [9] Faire exhorter les chefs avant combat");
        System.out.println("Exhortation des chefs avant le combat");
        System.out.println(Service.exhorter(chefBob));
        System.out.println(Service.exhorter(chefDye) + '\n');

        prompt("// *** [10] Un soldat sans protection frappe un autre soldat jusqu'à la mort");
        System.out.println("Presentation des soldats Alex et Bernard avant le combat");
        System.out.println(Service.presentation(soldatAlex));
        System.out.println(Service.presentation(soldatBernard));
        System.out.println("Le soldat Alex frappe 10 fois le soldat Bernard...");
        for (int i = 0; i< 10; i++){
            System.out.println(Service.frapper(soldatAlex, soldatBernard));
        }
        System.out.println("Presentation des soldats Alex et Bernard après le combat.");
        System.out.println(Service.presentation(soldatAlex));
        System.out.println(Service.presentation(soldatBernard) + '\n');

        prompt("// *** [11] Tuer un chef ;");
        System.out.println("LeFort tue le chef Dye");
        System.out.println(Service.frapper(soldatLeFort, chefDye));
        System.out.println(Service.presentation(chefDye) + '\n');

        prompt("// *** [12] Un soldat mort ne peut pas se faire frapper");
        System.out.println("Presentation des soldats Martin et Bernard avant le combat");
        System.out.println(Service.presentation(soldatMartin));
        System.out.println(Service.presentation(soldatBernard));
        System.out.println("Le soldat Martin frappe le soldat Bernard");
        System.out.println(Service.frapper(soldatMartin, soldatBernard));
        System.out.println(Service.presentation(soldatMartin));
        System.out.println(Service.presentation(soldatBernard) + '\n');

        prompt("// *** [13] Confier un soldat à un chef mort");
        System.out.println("Confier le soldat Henri à feu chef Dye");
        System.out.println(Service.confier(soldatHenri, chefDye) + '\n');

        prompt("// *** [14] Confier un soldat mort à un chef");
        System.out.println("Confier feu soldat Bernard au chef Bob");
        System.out.println(Service.confier(soldatBernard, chefBob) + '\n');

        prompt("// *** [15] Confier un équipement à un mort");
        System.out.println("Attribution d'une Epee de puissance 100 à feu soldat Bernard");
        System.out.println(ServiceV2.armer(soldatBernard, "Epée", 100));
        System.out.println("Attribution d'un Armure de protection 400 à feu soldat Bernard");
        System.out.println(ServiceV2.proteger(soldatBernard, "Armure", 400) + '\n');

        prompt("// *** [16] Montrer qu'un chef mort change sa réplique vis à vis de ses soldats ;");
        System.out.println("Présentation d'un chef mort");
        System.out.println(Service.presentation(chefDye) + '\n');

        prompt("// *** [17] Montrer qu’un soldat avec un chef mort, dit qu’il n’a pas de chef");
        System.out.println("Présentation d'un personnage avec un chef mort");
        System.out.println(Service.presentation(soldatJeanne) + '\n');

        prompt("// *** [18] Proteger un soldat et un chef mort");
        System.out.println("Attribution une protection a un chef mort ");
        System.out.println(Service.presentation(chefDye));
        System.out.println("Attribution d'un Armure de protection 400 au Chef Dye");
        System.out.println(ServiceV2.proteger(chefDye, "Armure", 400));
        System.out.println(Service.presentation(chefDye) + '\n');

        System.out.println("Attribution une protection a un soldat mort ");
        System.out.println(Service.presentation(soldatBernard));
        System.out.println("Attribution d'un Bouclier de protection 100 au Soldat Bernard");
        System.out.println(ServiceV2.proteger(soldatBernard, "Bouclier", 100));
        System.out.println(Service.presentation(soldatBernard) + '\n');

        prompt("// *** [19] La protection diminue les dégâts subis d’un montant égal à son efficacité ");
        System.out.println("Efficacite des equipement de protection");
        System.out.println("Presentation du mannequin avant le combat");
        System.out.println(Service.presentation(soldatEntrainement));
        System.out.println("Presentation du soldat Alex qui va le frapper avant le combat");
        System.out.println(Service.presentation(soldatAlex));
        System.out.println("Le soldat Alex frappe le manequin !");
        System.out.println(Service.frapper(soldatAlex, soldatEntrainement));
        System.out.println("Il a perdu de la vie !");
        System.out.println(Service.presentation(soldatEntrainement));
        System.out.println("Attribution d'une patate d'entrainement au mannequin de bois");
        System.out.println("Presentation du soldat Alex qui va le frapper avant le combat");
        System.out.println(Service.presentation(soldatAlex));
        System.out.println("Le soldat Alex retape le mannequi!");
        System.out.println(Service.frapper(soldatAlex, soldatEntrainement));
        System.out.println("Il a perdu moins de vie cette fois !");
        System.out.println(Service.presentation(soldatEntrainement) + '\n');

        prompt("// *** [19] Supprimer un soldat et un chef vivant");
        System.out.println("Supprimer un soldat vivant");
        try {
            System.out.println(Service.supprimerPers(soldatAlex) + '\n');
            throw new AssertionError("Managed to delete someone alive.");
        }
        catch (UnsupportedOperationException exc) {
            System.err.println(exc.getMessage());
        }

        System.out.println("Supprimer un chef vivant");
        try {
            System.out.println(Service.supprimerPers(chefBob) + '\n');
            throw new AssertionError("Managed to delete someone alive.");
        }
        catch (UnsupportedOperationException exc) {
            System.err.println(exc.getMessage());
        }

        prompt("// *** [20] Supprimer un soldat et un chef mort");
        System.out.println("Supprimer un soldat mort");
        System.out.println(Service.supprimerPers(soldatBernard) + '\n');

        System.out.println("Supprimer un chef mort");
        System.out.println(Service.supprimerPers(chefDye) + '\n');

        prompt("// *** [21] Supprimer un supprimé");
        System.out.println("Supprimer un supprimé");
        try {
            System.out.println(Service.supprimerPers(soldatBernard) + '\n');
            throw new AssertionError("The character was not deleted.");
        }
        catch (PersonneInexistanteException exc) {
            System.err.println(exc.getMessage());
        }

        return null;
    }

    public static void main(String[] args) throws PersonneInexistanteException, OperationNotSupportedException, IOException {
        scenario();
    }
}
