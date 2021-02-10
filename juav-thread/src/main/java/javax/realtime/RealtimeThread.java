package javax.realtime;

import com.fiji.fivm.r1.ThreadStartHook;
import com.fiji.fivm.Time;

import java.lang.fivmSupport;

public class RealtimeThread extends Thread implements Schedulable {
	private SchedulingParameters scheduling;
	private ReleaseParameters release;
	private MemoryParameters memory;
	private long initialRelease = -1;
	private static ThreadStartHook tsh=
			new ThreadStartHook() {
				public void go(Object vmThread,
							   Thread javaThread) {
					RealtimeThread rtt=(RealtimeThread)javaThread;
					rtt.mogrifyReleaseParameters();
				}
			};

	private void mogrifyReleaseParameters() {
		if(release != null  && release instanceof PeriodicParameters){
			PeriodicParameters periodic = (PeriodicParameters)release;
			HighResolutionTime start = periodic.getStart();
			if (start == null) {
				initialRelease = System.nanoTime();
			} else if (start instanceof RelativeTime) {
				initialRelease = System.nanoTime() + start.getMilliseconds() * 1000000 + start.getNanoseconds();
				Time.sleepAbsolute(initialRelease);
			} else if (start instanceof AbsoluteTime) {
				initialRelease = start.getMilliseconds() * 1000000 + start.getNanoseconds();
				long now = System.nanoTime();
				if (initialRelease < now) {
					initialRelease = now;
				} else {
					Time.sleepAbsolute(initialRelease);
				}
			}
		}
	}

	public RealtimeThread(){
		super();
	}

	public RealtimeThread(Runnable logic){
		super(logic);
	}

	public RealtimeThread(SchedulingParameters scheduling,
						  ReleaseParameters release){
		this.scheduling = scheduling;
		this.release = release;
		if(scheduling instanceof PriorityParameters){
			this.setPriority(((PriorityParameters)scheduling).getPriorityLowLevel());

		}
	}

	public void start() {
		fivmSupport.registerThreadStartHook(this,tsh);
		super.start();
	}

	public void setReleaseParameters(ReleaseParameters release){
		this.release = release;
	}

	public void setSchedulingParameters(SchedulingParameters scheduling){
		this.scheduling = scheduling;
		if(scheduling instanceof PriorityParameters){
			this.setPriority(((PriorityParameters)scheduling).getPriorityLowLevel());
		}
	}

	public boolean waitForNextPeriod() {
		if(release != null && release instanceof PeriodicParameters){
			RelativeTime period = ((PeriodicParameters)release).getPeriod();
			long periodL = period.getMilliseconds()*1000000 + period.getNanoseconds();
			long time = System.nanoTime() - initialRelease;
			long periodCount = (time + periodL - 1) / periodL;
			long nextPeriod = periodCount * periodL;
			long nextTime = nextPeriod + initialRelease;
			while(System.nanoTime() < nextTime){
				Time.sleepAbsolute(nextTime);
				Thread.interrupted();
			}
//			RelativeTime deadline = release.getDeadline();
//			if(deadline != null){
//				long currentTime = System.nanoTime();
//				if(currentTime > deadline.getMilliseconds()*1000000 + deadline.getNanoseconds())
//					release.getDeadlineMissHandler().handleAsyncEvent();
//			}
			return true;

		}
		throw new IllegalThreadStateException();
	}



	public static RealtimeThread currentRealtimeThread() {
		return (RealtimeThread)Thread.currentThread();
	}

	public void setMemoryParameters(MemoryParameters memory){
		this.memory = memory;
	}
}
