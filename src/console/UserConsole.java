package console;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserConsole {
    Scanner sc = new Scanner(System.in);

    public static final int HAB_EDIT_ALARM = 1;
    public static final int HAB_SHUT_SIREN = 2;
    public static final int HAB_RECIEVE_IMAGES = 3;

    public static final int GEST_LIST_DEVICE = 4;
    public static final int GEST_LIST_BATTERY = 5;
    public static final int GEST_EDIT_DEVICE = 6;
    public static final int GEST_ADD_DEVICE = 7;
    public static final int GEST_CHANGE_GEST = 8;
    public static final int GEST_CAMERA_ON = 9;
    public static final int GEST_EDIT_HAB = 10;
    public static final int GEST_ADD_ZONE = 11;
    public static final int GEST_LIST_ZONE = 12;
    public static final int GEST_LIST_USERS = 13;

    public static final int ALL_QUIT = 99;

    private final void sop(String s) {
        System.out.println(s);
    }
    public void print(String str) {
        sop(str);
    }

    public String gestConsole() {
        sop("------- COMMANDES GESTIONNAIRES ----------");
        sop(HAB_EDIT_ALARM + " --> Activer/Desactiver l'alarme");
        sop(HAB_SHUT_SIREN + " --> Activer/Desactiver les sirènes");
        sop(HAB_RECIEVE_IMAGES + " --> Recevoir les images des caméras");
        sop(GEST_LIST_DEVICE + " --> Lister l'ensemble des dispositifs");
        sop(GEST_LIST_BATTERY + " --> Lister l'etat de chargement des dispositifs");
        sop(GEST_EDIT_DEVICE + " --> Activer/Desactiver dispositif");
        sop(GEST_ADD_DEVICE + " --> Ajouter dispositif");
        sop(GEST_CHANGE_GEST + " --> Déléguer droit gestionnaire");
        sop(GEST_CAMERA_ON + " --> Allumer/Eteindre camera");
        sop(GEST_EDIT_HAB + " --> Ajouter/Supprimer habitant");
        sop(GEST_ADD_ZONE + " --> Ajouter/Supprimer zone");
        sop(GEST_LIST_ZONE + " --> Lister les zones");
        sop(GEST_LIST_USERS + " --> Lister les habitants");
        sop(ALL_QUIT + " --> Quitter");
        return sc.nextLine().toLowerCase();
    }

    public String habConsole() {
        sop("------- COMMANDES HABITANTS ----------");
        sop(HAB_EDIT_ALARM + " --> Activer/Desactiver l'alarme");
        sop(HAB_SHUT_SIREN + " --> Activer/Desactiver les sirènes");
        sop(HAB_RECIEVE_IMAGES + " --> Recevoir les images des caméras");
        sop(ALL_QUIT + " --> Quitter");
        return sc.nextLine().toLowerCase();
    }

    public String getUsername() {
        sop("-> Username: ");
        return(sc.nextLine().toLowerCase());
    }

    public String getPassword() {
        sop("-> Password: ");
        return(sc.nextLine().toLowerCase());
    }

    public String askZone() {
        sop("-> Zone: ");
        return(sc.nextLine().toLowerCase());
    }

    public String askName() {
        sop("-> Nom: ");
        return(sc.nextLine().toLowerCase());
    }

    public String askType() {
        sop("-> Type de dispositif (alarme, sirene, detecteur, camera): ");
        return(sc.nextLine().toLowerCase());
    }

    public String[] askDeviceInfo() {
        return new String[]{
                askType(),
                askName(),
                askZone(),
        };
    }

    public String addZone() {
        sop("-> Voulez vous ajouter ou supprimer une zone ? (add/del): ");
        return(sc.nextLine().toLowerCase());
    }

    public String activAny() {
        sop("-> Voulez vous activer ou desactiver le dispositif ? (on/off): ");
        return(sc.nextLine().toLowerCase());
    }

    public String activOnOffAny() {
        sop("-> Voulez vous allumer ou eteindre le dispositif ? (on/off): ");
        return(sc.nextLine().toLowerCase());
    }

    public String userEditChoice() {
        sop("-> Voulez vous créer ou supprimer un habitant ? (add/del): ");
        return(sc.nextLine().toLowerCase());
    }
}
