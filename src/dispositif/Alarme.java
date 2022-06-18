package dispositif;

import java.io.Serializable;

public class Alarme extends Dispositif implements Serializable {


    public Alarme(int battery, String zone, Boolean activated, String name) {
        super(battery, zone, name, activated);
    }

}
