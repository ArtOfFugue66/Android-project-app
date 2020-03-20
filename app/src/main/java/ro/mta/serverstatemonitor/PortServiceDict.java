package ro.mta.serverstatemonitor;
import java.util.*;

public class PortServiceDict {
    private static PortServiceDict instance = null;
    private Dictionary<Integer, String> portNrsServices = new Hashtable<Integer, String>();

    private PortServiceDict() {
        portNrsServices.put(22, "SSH");
        portNrsServices.put(23, "TELNET");
        portNrsServices.put(25, "SMTP");
        portNrsServices.put(37, "TIME");
        portNrsServices.put(53, "DNS");
        portNrsServices.put(80, "HTTP");
        portNrsServices.put(443, "HTTPS");
    }

    public Dictionary<Integer, String> getPortNrsServices() {
        return portNrsServices;
    }

    public static PortServiceDict getInstance() {
        return instance;
    }
}
