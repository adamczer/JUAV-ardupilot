package ub.cse.juav.payloads.manager;

import ub.cse.juav.jni.FijiJniSwitch;
import ub.cse.juav.payloads.DumbyAStarRunnable;

import javax.realtime.RealtimeThread;
import java.util.ArrayList;
import java.util.List;

public class PayloadManager {

    private List<Payload> payloads;

    public PayloadManager() {
        this.payloads = new ArrayList<>();
    }

    public void addPayload(Payload p) {
        payloads.add(p);
    }

    public void start() {
        for (Payload p : payloads) {
            Thread payload;
            if (FijiJniSwitch.usingFiji) {
                payload = new RealtimeThread(p.getRunnable());
                if (p.getSchedulingParameters()!=null)
                    ((RealtimeThread)payload).setSchedulingParameters(p.getSchedulingParameters());
                if(p.getReleaseParameters()!=null)
                    ((RealtimeThread)payload).setReleaseParameters(p.getReleaseParameters());
                if(p.getMemoryParameters()!=null)
                    ((RealtimeThread)payload).setMemoryParameters(p.getMemoryParameters());
            } else {
                payload = new Thread(p.getRunnable());
            }
            if (p.getName()!=null)
                payload.setName(p.getName());
            payload.start();
            System.out.println("STARTED PAYLOAD "+p.getName());
        }
    }
}
