package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.Mode;
import ub.cse.juav.copter.modes.ModeRtl;
import ub.cse.juav.copter.modes.ModeStabilize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HalSitl {
    void run(int argc, String[] argv, List<Callback> callbacks) {
        nativeInitizationPriorToControlLoop();


        while (!getHalSitlSchedulerShouldReboot()){
            if (getHalSitlSchedulerShouldExit()){
            System.out.println("Exiting\n");
                System.exit(0);
            }
            fillStackNan();
            for (Callback c:callbacks) {
                c.loop();
            }

            halSitlInnerLoopAfterCallbacks();


        }
        actuallyReboot();
    }

    private void halSitlInnerLoopAfterCallbacks() {
        //todo native
//        HALSITL::Scheduler::_run_io_procs();
//
//        uint32_t now = AP_HAL::millis();
//        if (now - last_watchdog_save >= 100 && using_watchdog) {
//            // save persistent data every 100ms
//            last_watchdog_save = now;
//            watchdog_save((uint32_t *)&utilInstance.persistent_data, (sizeof(utilInstance.persistent_data)+3)/4);
//        }
//
//        if (using_watchdog) {
//            // note that this only works for a speedup of 1
//            alarm(2);
//        }
        throw new IllegalStateException("unimplemented");
    }

    private void fillStackNan() {
//        fill_stack_nan
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void nativeInitizationPriorToControlLoop() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private boolean getHalSitlSchedulerShouldReboot() {
//        HALSITL::Scheduler::_should_reboot
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private boolean getHalSitlSchedulerShouldExit() {
//        HALSITL::Scheduler::_should_exit
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void actuallyReboot() {
//        actually_reboot
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    public static void main(String[] args) {
        AcAttitudeControl acAttitudeControl = new AcAttitudeControl();
        Map<Integer,Mode> modes = new HashMap<>();
        modes.put(0,new ModeStabilize(acAttitudeControl));
        modes.put(6,new ModeRtl(acAttitudeControl));
        Copter copter = new Copter();
        copter.setModes(modes);
        ApScheduler apScheduler = new ApScheduler();
        apScheduler.setCopter(copter);
        ApVehicle vehicle = new ApVehicle();
        vehicle.setScheduler(apScheduler);
        List<Callback> callbacks = new ArrayList<>();
        callbacks.add(vehicle);
        HalSitl halSitl = new HalSitl();
        halSitl.run(args.length, args, callbacks);
    }
}
