package Habitant;

import memory.Memory;
import Gestionnaire.Gestionnaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestionHabitant {

    public Boolean isExistedHabitant(String username) throws IOException, ClassNotFoundException {
        Object obj = Memory.read("users.txt");
        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Habitant && Objects.equals(((Habitant) objSon).getUsername(), username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void listHabitant() throws IOException, ClassNotFoundException {
        Object obj = Memory.read("users.txt");
        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Habitant && !(objSon instanceof Gestionnaire)) {
                    System.out.println("----> " + ((Habitant) objSon).getUsername());
                }
            }
        }
    }

    public void createNewHabitant(String username, String password) throws IOException, ClassNotFoundException {
        Habitant newHab = new Habitant(username, password);

        List users = new ArrayList();

        Object obj = Memory.read("users.txt");
        if (obj instanceof ArrayList) {
            users.addAll(((ArrayList<?>) obj));
            users.add(newHab);
        } else if (obj instanceof Gestionnaire){
            users.add(obj);
            users.add(newHab);
        } else {
            users.add(newHab);
        }

        Memory.save(users, "users.txt");
    }

    public Habitant getHabitant(String username, String password) throws IOException, ClassNotFoundException {
        Object obj = Memory.read("users.txt");
        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Habitant && Objects.equals(((Habitant) objSon).getUsername(), username) && Objects.equals(((Habitant) objSon).getPassword(), password)) {
                    return new Habitant(((Habitant) objSon).getUsername(), ((Habitant) objSon).getPassword());
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public void deleteHabitant(String username) throws IOException, ClassNotFoundException {
        Object obj = Memory.read("users.txt");
        List allUsers = new ArrayList();

        if (obj instanceof ArrayList) {
            for (Object objSon : ((ArrayList<?>)  obj)) {
                if (objSon instanceof Gestionnaire) {
                    allUsers.add(objSon);
                    if (Objects.equals(((Gestionnaire) objSon).getUsername(), username)) {
                        System.out.println("❌ Vous ne pouvez pas supprimer le compte habitant lié au compte gestionnaire !");
                    }
                } else if (objSon instanceof Habitant && !Objects.equals(((Habitant) objSon).getUsername(), username)) {
                    allUsers.add(objSon);
                }
            }
        } else {
            if (obj instanceof Gestionnaire) {
                allUsers.add(obj);
                if (Objects.equals(((Gestionnaire) obj).getUsername(), username)) {
                    System.out.println("❌ Vous ne pouvez pas supprimer le compte habitant lié au compte gestionnaire !");
                }
            } else if (obj instanceof Habitant && !Objects.equals(((Habitant) obj).getUsername(), username)) {

            }
        }

        Memory.save(allUsers, "users.txt");
        System.out.println("✅ Habitant supprimé avec succès !");
    }
}
