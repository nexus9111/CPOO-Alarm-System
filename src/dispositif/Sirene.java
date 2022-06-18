package dispositif;

import java.io.Serializable;

public class Sirene extends Dispositif implements Serializable {


    public Sirene(int battery, String zone, Boolean activated, String name) {
        super(battery, zone, name, activated);
    }
}
