package dispositif;

import java.io.Serializable;

public class Camera extends Dispositif implements Serializable {
    private Boolean on = false;

    public Camera(int battery, String zone, Boolean on, Boolean activated, String name) {
        super(battery, zone, name, activated);
        this.on = on;
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }
}
