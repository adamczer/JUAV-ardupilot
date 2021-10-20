package ub.cse.juav.payloads;

import java.io.FileOutputStream;
import java.io.IOException;


public class DumbyAStarRunnable implements Runnable {
	public static boolean LOG_A_STAR = true;
	final int workTime;
	private final int rows;
	private final int columns;
	FileOutputStream aStarLog;

	public DumbyAStarRunnable(int workTime, int rows, int columns) {
		this.workTime = workTime;
		this.rows = rows;
		this.columns = columns;
		if (LOG_A_STAR) {
			try {
				aStarLog = new FileOutputStream("astar.log",true);
			} catch (IOException e) {
				throw new IllegalStateException("metrics logging enabled and could not create log file in working dir.",e);
			}
		}
	}
	public void run() {
//		try {
//			Thread.sleep(30000);
//			System.out.println("STARTING A*");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		while (true) {
			if(LOG_A_STAR) {
				try {
					aStarLog.write(("STARTED ASTAR INIT AT " +
                    Long.toString(System.nanoTime()) +"\n").getBytes());
					aStarLog.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			DumbyAStar a = new DumbyAStar(aStarLog,LOG_A_STAR,workTime,rows,columns);
			if (LOG_A_STAR) {
				try {
					aStarLog.write(("FINISHED ASTAR INIT AT "+
                    Long.toString(System.nanoTime())+"\n").getBytes());
					aStarLog.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// Create a closed list and initialise it to false which means 
			// that no cell has been included yet 
			// This closed list is implemented as a boolean 2D array 
			//memset(closedList, false, sizeof(closedList));
			if (LOG_A_STAR) {
				try {
					aStarLog.write(("STARTED ASTAR AT " +
                    Long.toString(System.nanoTime()) +"\n").getBytes());
					aStarLog.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			a.aStarSearch(aStarLog,LOG_A_STAR);
			if (LOG_A_STAR) {
				try {
					aStarLog.write(("FINISHED ASTAR AT "+Long.toString(System.nanoTime())+"\n").getBytes());
					aStarLog.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(this.workTime > 0)
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.err.format("IOException: %s%n", e);
			}
		}
	}
}
