package Gestionnaire;

import Habitant.Habitant;
import memory.Memory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestionGestionnaire {

    public void defineNewGestionnaire(String username, String password) throws IOException, ClassNotFoundException {
        Gestionnaire newGestionnaire = new Gestionnaire(username, password);
        List users = new ArrayList();

        Object obj = Memory.read("users.txt");
        if (obj instanceof ArrayList) {
            users.addAll(((ArrayList<?>) obj));
            users.add(newGestionnaire);
        } else {
            users.add(newGestionnaire);
        }

        Memory.save(newGestionnaire, "users.txt");
    }

    public Gestionnaire getCurrentGestionnaire() throws IOException, ClassNotFoundException {
        Object obj = Memory.read("users.txt");
        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Gestionnaire) {
                    return new Gestionnaire(((Gestionnaire) objSon).getUsername(), ((Gestionnaire) objSon).getPassword());
                }
            }
            System.exit(0);
            return null;
        } else {
            System.exit(0);
            return null;
        }
    }

    public void setNewGestionnaire(String username) throws IOException, ClassNotFoundException {
        Object obj = Memory.read("users.txt");
        List allBasicUser = new ArrayList();

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (!(objSon instanceof Gestionnaire)) {
                    if (objSon instanceof Habitant && Objects.equals(((Habitant) objSon).getUsername(), username)) {
                        Gestionnaire newGest = new Gestionnaire(((Habitant) objSon).getUsername(), ((Habitant) objSon).getPassword());
                        allBasicUser.add(newGest);
                    }
                    allBasicUser.add(objSon);
                }
            }
        }
        Memory.save(allBasicUser, "users.txt");
        System.out.println("✅ Nouveau gestionnaire défini avec succès ! Merci de vous reconnecter !");
    }


}
