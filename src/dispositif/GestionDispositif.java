package dispositif;

import memory.Memory;
import zone.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestionDispositif {

    private Boolean isExistedDispositif(String name) throws IOException, ClassNotFoundException {
        Object obj = Memory.read("devices.txt");
        List allDevices = new ArrayList();

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Dispositif && Objects.equals(((Dispositif) objSon).getName(), name)) {
                    return true;
                }
            }
        } else if (obj instanceof Dispositif) {
            if (Objects.equals(((Dispositif) obj).getName(), name)) {
                return true;
            }
        }
        return false;
    }

    private void saveDispositif(Dispositif dispositif) throws IOException, ClassNotFoundException {
        List allDispositifs = new ArrayList();

        Object obj = Memory.read("devices.txt");
        if (obj instanceof ArrayList) {
            allDispositifs.addAll(((ArrayList<?>) obj));
        } else {
            allDispositifs.add(obj);
        }

        allDispositifs.add(dispositif);
        Memory.save(allDispositifs, "devices.txt");
    }

    public void listDispositifs() throws IOException, ClassNotFoundException {
        Object obj = Memory.read("devices.txt");
        String _base = "----> ";

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Dispositif) {
                    System.out.println(_base + ((Dispositif) objSon).getName());
                }
            }
        } else if (obj instanceof Dispositif) {
            System.out.println(_base + ((Dispositif) obj).getName());
        }
    }

    public void listDispositifsExtended() throws IOException, ClassNotFoundException {
        Object obj = Memory.read("devices.txt");
        String _base = "----> ";

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Dispositif) {
                    System.out.println(_base
                            + "Zone: " + ((Dispositif) objSon).getZone()
                            + " / Type: " + ((objSon instanceof Camera)
                                                ? "camera" :  (objSon instanceof Alarme)
                                                ? "alarme" : (objSon instanceof Detecteur)
                                                ? "detecteur" : "sirene")
                            + " / Name: " + ((Dispositif) objSon).getName()
                            + " / Batterie: " + ((Dispositif) objSon).getBattery());
                }
            }
        } else if (obj instanceof Dispositif) {
            System.out.println(_base
                    + "Zone: " + ((Dispositif) obj).getZone()
                    + " / Type: " + ((obj instanceof Camera)
                    ? "camera" :  (obj instanceof Alarme)
                    ? "alarme" : (obj instanceof Detecteur)
                    ? "detecteur" : "sirene")
                    + " / Name: " + ((Dispositif) obj).getName()
                    + " / Batterie: " + ((Dispositif) obj).getBattery());
        }
    }

    public void createDispositif(String type, String name, String zone) throws IOException, ClassNotFoundException {
        String success = "✅ Dispositif ajouté avec succès";

        if (isExistedDispositif(name)) {
            System.out.println("❌ Le nom que vous souhaitez donner a votre nouveau dispositif existe deja...");
            return;
        }

        switch (type) {
            case "alarme":
                Alarme newAlarme = new Alarme(100, zone, false, name);
                saveDispositif(newAlarme);
                System.out.println(success);
                break;
            case "camera":
                Camera newCamera = new Camera(100, zone, false, false, name);
                saveDispositif(newCamera);
                System.out.println(success);
                break;
            case "sirene":
                Sirene newSirene = new Sirene(100, zone, false, name);
                saveDispositif(newSirene);
                System.out.println(success);
                break;
            case "detecteur":
                Detecteur newDetecteur = new Detecteur(100, zone, false, name);
                saveDispositif(newDetecteur);
                System.out.println(success);
                break;
            default:
                System.out.println("❌ Le type de dispositif renseigné est inconnu ...");
                break;
        }
    }

    public void activateAlarme(Boolean activated) throws IOException, ClassNotFoundException {
        Boolean hasAlarme = false;
        List allDispositifs = new ArrayList();

        Object obj = Memory.read("devices.txt");
        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Alarme) {
                    Alarme currentAlrme = new Alarme(((Alarme) objSon).getBattery(), ((Alarme) objSon).getZone(), activated, ((Alarme) objSon).getName());
                    hasAlarme = true;
                    if (activated) {
                        System.out.println("--> " + currentAlrme.getName() + " à été activée avec succès");
                    } else {
                        System.out.println("--> " + currentAlrme.getName() + " à été desactivée avec succès");
                    }
                    allDispositifs.add(currentAlrme);
                } else {
                    allDispositifs.add(objSon);
                }
            }
        } else {
            if (obj instanceof Alarme) {
                Alarme currentAlrme = new Alarme(((Alarme) obj).getBattery(), ((Alarme) obj).getZone(), activated, ((Alarme) obj).getName());
                if (activated) {
                    System.out.println("--> " + currentAlrme.getName() + " à été activée avec succès");
                } else {
                    System.out.println("--> " + currentAlrme.getName() + " à été desactivée avec succès");
                }
                hasAlarme = true;
            }
        }

        if (!hasAlarme) {
            System.out.println("❌ Aucune alarme détéctée, aucun changement effectué ...");
        } else {
            if (activated) {
                System.out.println("✅ Toutes les alarmes ont bien été activées ...");
            } else {
                System.out.println("✅ Toutes les alarmes ont bien été desactivées ...");
            }
            Memory.save(allDispositifs, "devices.txt");
        }
    }

    public void activateSirene(Boolean activated) throws IOException, ClassNotFoundException {
        Boolean hasSirene = false;
        List allDispositifs = new ArrayList();

        Object obj = Memory.read("devices.txt");
        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Sirene) {
                    Sirene currentSirene = new Sirene(((Sirene) objSon).getBattery(), ((Sirene) objSon).getZone(), activated, ((Sirene) objSon).getName());
                    hasSirene = true;
                    if (activated) {
                        System.out.println("--> " + currentSirene.getName() + " à été activée avec succès");
                    } else {
                        System.out.println("--> " + currentSirene.getName() + " à été desactivée avec succès");
                    }
                    allDispositifs.add(currentSirene);
                } else {
                    allDispositifs.add(objSon);
                }
            }
        } else {
            if (obj instanceof Sirene) {
                Sirene currentSirene = new Sirene(((Sirene) obj).getBattery(), ((Sirene) obj).getZone(), activated, ((Sirene) obj).getName());
                if (activated) {
                    System.out.println("--> " + currentSirene.getName() + " à été activée avec succès");
                } else {
                    System.out.println("--> " + currentSirene.getName() + " à été desactivée avec succès");
                }
                hasSirene = true;
            }
        }

        if (!hasSirene) {
            System.out.println("❌ Aucune sirène détéctée, aucun changement effectué ...");
        } else {
            if (activated) {
                System.out.println("✅ Toutes les sirènes ont bien été activées ...");
            } else {
                System.out.println("✅ Toutes les sirènes ont bien été desactivées ...");
            }
            Memory.save(allDispositifs, "devices.txt");
        }
    }

    public void activateAny(Boolean activated, String name) throws IOException, ClassNotFoundException {
        Boolean hasDisp = false;
        List allDispositifs = new ArrayList();

        Object obj = Memory.read("devices.txt");
        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Dispositif && Objects.equals(((Dispositif) objSon).getName(), name)) {
                    ((Dispositif) objSon).setActivated(activated);
                    hasDisp = true;
                    if (activated) {
                        System.out.println("--> " + ((Dispositif) objSon).getName() + " à été activée avec succès");
                    } else {
                        System.out.println("--> " + ((Dispositif) objSon).getName() + " à été desactivée avec succès");
                    }
                    allDispositifs.add(objSon);
                } else {
                    allDispositifs.add(objSon);
                }
            }
        } else {
            if (obj instanceof Sirene) {
                ((Dispositif) obj).setActivated(activated);
                if (activated) {
                    System.out.println("--> " + ((Dispositif) obj).getName() + " à été activée avec succès");
                } else {
                    System.out.println("--> " + ((Dispositif) obj).getName() + " à été desactivée avec succès");
                }
                hasDisp = true;
            }
        }

        if (!hasDisp) {
            System.out.println("❌ Aucune dispositif n'a été détécté avec ce nom, aucun changement effectué ...");
        } else {
            if (activated) {
                System.out.println("✅ " + name + " à bien été activées ...");
            } else {
                System.out.println("✅ " + name + " à bien été activées ...");
            }
            Memory.save(allDispositifs, "devices.txt");
        }
    }

    public void deleteByZone(String zone) throws IOException, ClassNotFoundException  {
        Object obj = Memory.read("devices.txt");
        List allDevices = new ArrayList();

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Dispositif && !Objects.equals(((Dispositif) objSon).getZone(), zone)) {
                    allDevices.add(objSon);
                }
            }
        } else if (obj instanceof Dispositif) {
            if (!Objects.equals(((Dispositif) obj).getZone(), zone)) {
                allDevices.add(obj);
            }
        }

        Memory.save(allDevices, "devices.txt");
        System.out.println("✅ Tous les dispositifs dans la zone suppirmée on été supprimés avec succès !");
    }

    public String getImages() {
        return("Image");
    }

    public void onOffCamera(Boolean on, String name) throws IOException, ClassNotFoundException  {
        Object obj = Memory.read("devices.txt");

        if (!isExistedDispositif(name)) {
            System.out.println("❌ ce dispositif n'existe pas ...");
            return;
        }

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Camera && Objects.equals(((Dispositif) objSon).getName(), name)) {
                    ((Camera) objSon).setOn(on);
                }
            }
        } else {
            if (obj instanceof Camera && Objects.equals(((Dispositif) obj).getName(), name)) {
                ((Camera) obj).setOn(on);
            }
        }

        Memory.save(obj, "devices.txt");
        System.out.println("✅ La caméra est " + (on ? "allumé !": "éteinte !"));
    }
}
