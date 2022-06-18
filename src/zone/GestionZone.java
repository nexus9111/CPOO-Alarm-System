package zone;

import memory.Memory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestionZone {

    public void createZone(String zone) throws IOException, ClassNotFoundException {
        Object obj = Memory.read("zones.txt");
        List allZone = new ArrayList();

        if (isExistedZone(zone)) {
            System.out.println("❌ La zone que vous tentez d'ajouter existe deja ... ");
        }

        if (obj instanceof ArrayList) {
            allZone.addAll(((ArrayList<?>) obj));
        } else if (obj instanceof Zone) {
            allZone.add(obj);
        }

        allZone.add(new Zone(zone));
        Memory.save(allZone, "zones.txt");
        System.out.println("✅ Zone ajoutée avec succès !");
    }

    public void deleteZone(String zone) throws IOException, ClassNotFoundException {
        Object obj = Memory.read("zones.txt");
        List allZone = new ArrayList();

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Zone && !Objects.equals(((Zone) objSon).getName(), zone)) {
                    allZone.add(objSon);
                }
            }
        } else if (obj instanceof Zone) {
            if (!Objects.equals(((Zone) obj).getName(), zone)) {
                allZone.add(obj);
            }
        }

        Memory.save(allZone, "zones.txt");
        System.out.println("✅ Zone supprimé avec succès !");
    }

    public Boolean isExistedZone(String zone) throws IOException, ClassNotFoundException {
        Object obj = Memory.read("zones.txt");
        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Zone) {
                    if (Objects.equals(((Zone) objSon).getName(), zone)) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            if (obj instanceof Zone) {
                if (Objects.equals(((Zone) obj).getName(), zone)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void listZones() throws IOException, ClassNotFoundException {
        Object obj = Memory.read("zones.txt");

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Zone) {
                    System.out.println("----> " + ((Zone) objSon).getName());
                }
            }
        } else {
            if (obj instanceof Zone) {
                System.out.println("----> " + ((Zone) obj).getName());
            }
        }
    }

}
