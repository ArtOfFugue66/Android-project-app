package ro.mta.serverstatemonitor;

import java.util.List;

// TODO: implement open ports & service pairs (LIST or something type)
// TODO: make temperature variable change randomly every 3 seconds (INTEGER/int type)
// TODO: implement state (online/offline or up/down) (BOOLEAN type)
// TODO: implement uptime (DateTime or something type)
// TODO: implement DISA STIG compliant/not-compliant variable (BOOLEAN type)
public class MockServer {
    private List<Integer> open_ports;

    public MockServer(List<Integer> open_ports) {
        this.open_ports = open_ports;
    }

    public List<Integer> getOpen_ports() {
        return open_ports;
    }
}
