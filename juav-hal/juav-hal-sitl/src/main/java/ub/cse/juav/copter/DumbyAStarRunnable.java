package ub.cse.juav.copter;
import com.fiji.fivm.r1.fivmRuntime;
import ub.cse.juav.jni.FijiJniSwitch;


public class DumbyAStarRunnable implements Runnable {
	public DumbyAStarRunnable() {
	}
	public void run() {
		while (true) {
			if (FijiJniSwitch.usingFiji) {
				fivmRuntime.logPrint("STARTED ASTAR INIT AT ");
				fivmRuntime.logPrint(Long.toString(System.nanoTime()));
				fivmRuntime.logPrint("\n");
			} else {
				System.out.format("STARTED ASTAR INIT AT %d\n", System.nanoTime());
			}
			DumbyAStar a = new DumbyAStar();
			if (FijiJniSwitch.usingFiji) {
				fivmRuntime.logPrint("FINISHED ASTAR INIT AT ");
				fivmRuntime.logPrint(Long.toString(System.nanoTime()));
				fivmRuntime.logPrint("\n");
			} else {
				System.out.format("FINISHED ASTAR INIT AT %d\n", System.nanoTime());
			}
			// Create a closed list and initialise it to false which means 
			// that no cell has been included yet 
			// This closed list is implemented as a boolean 2D array 
			//memset(closedList, false, sizeof(closedList));
			if (FijiJniSwitch.usingFiji) {
				fivmRuntime.logPrint("STARTED ASTAR AT ");
				fivmRuntime.logPrint(Long.toString(System.nanoTime()));
				fivmRuntime.logPrint("\n");
			} else {
				System.out.format("STARTED ASTAR AT %d\n", System.nanoTime());
			}
			a.aStarSearch();
			if (FijiJniSwitch.usingFiji) {
				fivmRuntime.logPrint("FINISHED ASTAR AT ");
				fivmRuntime.logPrint(Long.toString(System.nanoTime()));
				fivmRuntime.logPrint("\n");
			} else {
				System.out.format("FINISHED ASTAR AT %d\n", System.nanoTime());
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.format("IOException: %s%n", e);
			}
		}
	}
}
