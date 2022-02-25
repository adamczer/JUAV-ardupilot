package ub.cse.juav.mvm.vmconfig;

import com.fiji.fivm.ThreadPriority;
import com.fiji.mvm.Payload;
import com.fiji.mvm.SpawnMode;
import com.fiji.mvm.TimeSliceManager;
import com.fiji.mvm.VMConfiguration;

import java.util.List;

public class VMConfigPriority {

	public static void main(String[] args) {
		long quantum = 50 * 1000 * 1000;
		List<Payload> payloads = Payload.subPayloads();
		System.out.println("Will run: " + payloads.size());
		TimeSliceManager tsm = new TimeSliceManager(payloads.size(), ThreadPriority.FIFO_MAX);
	
        System.out.println("Size of payload:" + payloads.size());

		for (int i = 0; i < payloads.size(); ++i) {
			Payload p = payloads.get(i);
			System.out.println("Creating timeslice for " + p);
			int numThreads = p.getNumInternalVMThreads() + 3;
			System.out.println(" Allowing " + numThreads + " threads");
			
			System.out.println("Thread Priority Normal Max: "+ThreadPriority.NORMAL_MAX);
			System.out.println("Thread Priority Normal Min: "+ThreadPriority.NORMAL_MIN);
			System.out.println("Thread Priority FIFO  Max: "+ThreadPriority.FIFO_MAX);
			System.out.println("Thread Priority FIFO  Min: "+ThreadPriority.FIFO_MIN);
			System.out.println("Thread Priority FAKE RT: "+ThreadPriority.FAKE_RT);
			System.out.println("Thread Priority RR Max: "+ThreadPriority.RR_MAX);
			System.out.println("Thread Priority RR  Min: "+ThreadPriority.RR_MIN);

			if (i==0) {
				tsm.initTimeSlice(i, quantum, numThreads, ThreadPriority.FIFO_MAX - 2);
			} else {
				tsm.initTimeSlice(i, quantum/10, numThreads, ThreadPriority.FIFO_MAX - 1);
			}
		}

		tsm.start();

		for (int i = 0; i < payloads.size(); ++i) {
			System.out.println("Launching VM for " + payloads.get(i));
			tsm.getTimeSlice(i).spawn(new VMConfiguration(payloads.get(i)), 
                    SpawnMode.SPAWN_ONE_SHOT);
		}
	}
}
