package zone;

import java.io.Serializable;

public class Zone implements Serializable {
    private String name;

    public Zone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
