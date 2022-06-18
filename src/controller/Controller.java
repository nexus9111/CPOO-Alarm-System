package controller;

import console.UserConsole;
import Gestionnaire.*;
import Habitant.*;
import dispositif.*;
import zone.*;
import memory.Memory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

public class Controller {

    private UserConsole ui = new UserConsole();
    private Boolean isGestionnaire = false;

    private GestionGestionnaire gestGest = new GestionGestionnaire();
    private GestionHabitant gestHab = new GestionHabitant();
    private GestionDispositif gestDisp = new GestionDispositif();
    private GestionZone gestZone = new GestionZone();

    public Controller() throws Exception {
        Boolean hasSaveInstance = init();
        // Boolean hasSaveInstance = false;
        if (hasSaveInstance) {
            // login();
            System.out.println("login");
            login();
        } else {
            System.out.println("register");
            register();
        }
    }

    public void habStart() throws Exception {
        String cmd = ui.habConsole();
        int resp = Integer.parseInt(cmd);
        switch (resp) {
            case UserConsole.HAB_EDIT_ALARM: //1
                String alarms_choice = ui.activAny();
                if (Objects.equals(alarms_choice, "on")) {
                    gestDisp.activateAlarme(true);
                } else if (Objects.equals(alarms_choice, "off")) {
                    gestDisp.activateAlarme(false);
                } else {
                    ui.print("❌ Choix invalide ... ");
                }
                habStart();
                break;
            case UserConsole.HAB_SHUT_SIREN: //2
                String sirene_choice = ui.activAny();
                if (Objects.equals(sirene_choice, "on")) {
                    gestDisp.activateSirene(true);
                } else if (Objects.equals(sirene_choice, "off")) {
                    gestDisp.activateSirene(false);
                } else {
                    ui.print("❌ Choix invalide ... ");
                }
                habStart();
                break;
            case UserConsole.HAB_RECIEVE_IMAGES: //3
                ui.print(gestDisp.getImages());
                habStart();
                break;
            default:
                ui.print("❌ reponse inconnue ...");
                exit(0);
                break;
        }
    }

    public void gestStart() throws Exception {
        String cmd = ui.gestConsole();
        int resp = Integer.parseInt(cmd);
        switch (resp) {
            case UserConsole.HAB_EDIT_ALARM: //1
                String alarms_choice = ui.activAny();
                if (Objects.equals(alarms_choice, "on")) {
                    gestDisp.activateAlarme(true);
                } else if (Objects.equals(alarms_choice, "off")) {
                    gestDisp.activateAlarme(false);
                } else {
                    ui.print("❌ Choix invalide ... ");
                }
                gestStart();
                break;
            case UserConsole.HAB_SHUT_SIREN: //2
                String sirene_choice = ui.activAny();
                if (Objects.equals(sirene_choice, "on")) {
                    gestDisp.activateSirene(true);
                } else if (Objects.equals(sirene_choice, "off")) {
                    gestDisp.activateSirene(false);
                } else {
                    ui.print("❌ Choix invalide ... ");
                }
                gestStart();
                break;
            case UserConsole.HAB_RECIEVE_IMAGES: //3
                ui.print(gestDisp.getImages());
                gestStart();
                break;
            case UserConsole.GEST_LIST_DEVICE: //4
                gestDisp.listDispositifs();
                gestStart();
            case UserConsole.GEST_LIST_BATTERY: //5
                gestDisp.listDispositifsExtended();
                gestStart();
            case UserConsole.GEST_EDIT_DEVICE: //6
                String name = ui.askName();
                String any_choice = ui.activAny();

                if (Objects.equals(any_choice, "on")) {
                    gestDisp.activateAny(true, name);
                } else if (Objects.equals(any_choice, "off")) {
                    gestDisp.activateAny(false, name);
                } else {
                    ui.print("❌ Choix invalide ... ");
                }
                gestStart();
                break;
            case UserConsole.GEST_ADD_DEVICE: //7
                String[] deviceInfos = ui.askDeviceInfo();
                if (gestZone.isExistedZone(deviceInfos[2])) {
                    gestDisp.createDispositif(deviceInfos[0], deviceInfos[1], deviceInfos[2]);
                } else {
                    ui.print("❌ La zone renseignée n'existe pas, merci de l'enregistrer... ");
                }
                gestStart();
            case UserConsole.GEST_CHANGE_GEST: //8
                String newGest = ui.askName();
                if (!gestHab.isExistedHabitant(newGest)) {
                    ui.print("❌ L'habitant choisi n'existe pas");
                } else {
                    gestGest.setNewGestionnaire(newGest);
                }
                login();
                break;
            case UserConsole.GEST_CAMERA_ON: //9
                String cameraName = ui.askName();
                String on = ui.activOnOffAny();
                if (Objects.equals(on, "on")) {
                    gestDisp.onOffCamera(true, cameraName);
                } else if (Objects.equals(on, "off")) {
                    gestDisp.onOffCamera(false, cameraName);
                } else {
                    ui.print("❌ Choix invalide ... ");
                }
                gestStart();
                break;
            case UserConsole.GEST_EDIT_HAB: //10
                String userEditChoice = ui.userEditChoice();
                if (Objects.equals(userEditChoice, "add")) {
                    String[] lgi = getLoginInformations();
                    gestHab.createNewHabitant(lgi[0], lgi[1]);
                    ui.print("✅ Habitant créé avec succès !");
                } else if (Objects.equals(userEditChoice, "del")) {
                    String getUserToDel = ui.askName();
                    gestHab.deleteHabitant(getUserToDel);
                } else {
                    ui.print("❌ Choix invalide ... ");
                }
                gestStart();
                break;
            case UserConsole.GEST_ADD_ZONE: //11
                String zone_choice = ui.addZone();
                if (Objects.equals(zone_choice, "del")) {
                    String zone_choice2 = ui.askZone();
                    gestZone.deleteZone(zone_choice2);
                    gestDisp.deleteByZone(zone_choice2);
                } else if (Objects.equals(zone_choice, "add")) {
                    gestZone.createZone(ui.askZone());
                } else {
                    ui.print("❌ Choix invalide ... ");
                }
                gestStart();
                break;
            case UserConsole.GEST_LIST_ZONE: //12
                gestZone.listZones();
                gestStart();
            case UserConsole.GEST_LIST_USERS: //13
                gestHab.listHabitant();
                gestStart();
            case UserConsole.ALL_QUIT:
                ui.print("✋ Au revoir ");
                exit(0);
                break;
            default:
                ui.print("❌ reponse inconnue ...");
                exit(0);
                break;
        }
    }

    private Boolean init() throws IOException, ClassNotFoundException {
        Object obj = Memory.read("users.txt");
        if (obj == null) {
            return false;
        } else {
            if (obj instanceof ArrayList) {
                if (((ArrayList<?>) obj).size() < 2 ) {
                    return false;
                }
                for (Object objSon : ((ArrayList<?>)  obj)) {
                    if (objSon instanceof Gestionnaire) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
    }

    private String[] getLoginInformations() {
        return new String[]{ui.getUsername(), ui.getPassword()};
    }

    private void register() throws IOException, ClassNotFoundException {
        ui.print("Bienvenue dans votre system d'alarme !");
        ui.print("❌ Nous n'avons trouvé aucun gestionnaire en mémoire, merci de vous enregistrer ...");
        String[] lgi = getLoginInformations();
        gestGest.defineNewGestionnaire(lgi[0], lgi[1]);
        ui.print("✅ Un habitant avec votre nom et votre mot de passe vient d'être créer automatiquement.");
        gestHab.createNewHabitant(lgi[0], lgi[1]);
        // typeOfUser = "gestionnaire";
    }

    private void login() throws Exception {
        ui.print("Bienvenue dans votre system d'alarme !");
        ui.print("✅ Nous avons trouvé un gestionnaire en mémoire, merci de vous identifier ...");
        String[] lgi = getLoginInformations();
        Gestionnaire currentGest = gestGest.getCurrentGestionnaire();
        if (Objects.equals(currentGest.getUsername(), lgi[0]) && Objects.equals(currentGest.getPassword(), lgi[1])) {
            ui.print("✋ Connexion réussie en tans que gestionnaire (un grand pouvoir implique de grande responsabilité) ...");
            isGestionnaire = true;
            gestStart();
            return;
        } else if (gestHab.getHabitant(lgi[0], lgi[1]) != null) {
            ui.print("✋ Connexion réussie en tans qu'habitant ...");
            habStart();
            return;
        }
        ui.print("❌ Les informations saisies ne correspondent a auncun utilisateur ...");
        System.exit(0);
    }

}
