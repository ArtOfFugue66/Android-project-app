package ro.mta.serverstatemonitor;

import java.text.DateFormat;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class MockServer {
    // list of open port-service pairs on the server
    private Dictionary<Integer, String> openPortsAndServices = new Hashtable<Integer, String>();
    // server temperature in Celsius degrees
    private int temperature;
    // server state (up/down)
    private boolean state;
    // TODO: implement uptime (DateTime or something type); stored in the SQLite database
    
    // server DISA STIG compliance state
    private boolean disa_compliant;

    public MockServer(List<String> portsAndServices) {
        for (int i=0; i<portsAndServices.size(); i++) {
            String service = portsAndServices.get(i);
            if (service.contains("SSH")) { openPortsAndServices.put(22, "SSH"); }
            else if (service.contains("TELNET")) { openPortsAndServices.put(23, "TELNET"); }
            else if (service.contains("SMTP")) { openPortsAndServices.put(25, "SMTP"); }
            else if (service.contains("TIME")) { openPortsAndServices.put(37, "TIME"); }
            else if (service.contains("DNS")) { openPortsAndServices.put(53, "DNS"); }
            else if (service.contains("HTTP")) { openPortsAndServices.put(80, "HTTP"); }
            else if (service.contains("HTTPS")) { openPortsAndServices.put(443, "HTTPs"); }
        }
        // generate random temperature between 50 and 90 degrees
        temperature = ThreadLocalRandom.current().nextInt(50, 91);
        // randomly generated state
        state = ThreadLocalRandom.current().nextBoolean();
        // randomly generated state of compliance
        disa_compliant = ThreadLocalRandom.current().nextBoolean();
    }

    public Dictionary<Integer, String> getOpenPortsAndServices() {
        return openPortsAndServices;
    }
}
