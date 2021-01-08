package ub.cse.juav.payloads.manager;

import com.fiji.fivm.r1.MemoryAreas;

import javax.realtime.MemoryParameters;
import javax.realtime.ReleaseParameters;
import javax.realtime.SchedulingParameters;

public class Payload {
    private Runnable runnable;
    private String name;
    private SchedulingParameters schedulingParameters;
    private ReleaseParameters releaseParameters;
    private MemoryParameters memoryParameters;

    public Payload(Runnable runnable, String name, SchedulingParameters sp, ReleaseParameters rp, MemoryParameters mp) {
        this.runnable = runnable;
        this.name = name;
        this.schedulingParameters = sp;
        this.releaseParameters = rp;
        this.memoryParameters = mp;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public String getName() {
        return name;
    }

    public SchedulingParameters getSchedulingParameters() {
        return schedulingParameters;
    }

    public ReleaseParameters getReleaseParameters() {
        return releaseParameters;
    }

    public MemoryParameters getMemoryParameters() {
        return memoryParameters;
    }
}
