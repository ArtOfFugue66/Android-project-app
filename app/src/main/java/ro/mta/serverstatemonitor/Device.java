package ro.mta.serverstatemonitor;

public class Device {
    private String name;
    private String brand;
    private String ip;
    private String location;
    private String type;
    private int image;

    public Device(String name, String brand, String ip, String location, String type, int image) {
        this.name = name;
        this.brand = brand;
        this.ip = ip;
        this.location = location;
        this.type = type;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getIp() {
        return ip;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
