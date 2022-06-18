package dispositif;

import java.io.Serializable;

public class Dispositif implements Serializable {

    private int battery = 100;
    private String zone;
    private String name;
    private Boolean activated;

    public Dispositif(int battery, String zone, String name, Boolean activated) {
        this.name = name;
        this.battery = battery;
        this.zone = zone;
        this.activated = activated;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
