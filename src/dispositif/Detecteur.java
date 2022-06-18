package dispositif;

import java.io.Serializable;

public class Detecteur extends Dispositif implements Serializable {

    public Detecteur(int battery, String zone, Boolean activated, String name) {
        super(battery, zone, name, activated);
    }

}
