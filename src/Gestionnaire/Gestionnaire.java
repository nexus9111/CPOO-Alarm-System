package Gestionnaire;

import Habitant.Habitant;

import java.io.Serializable;

public class Gestionnaire extends Habitant implements Serializable {
    private String username;
    private String password;

    public Gestionnaire(String username, String password) {
        super(username, password);
    }

    public Habitant addHabitant(String username, String password) {
        Habitant newHabitant = new Habitant(username, password);
        // persistent storage newHabitant
        return newHabitant;
    }

    public void removeHabitant(Habitant user) {
        //remove persistent
        user.setUsername(null);
        user.setPassword(null);
    }


}
