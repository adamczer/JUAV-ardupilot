package ub.cse.juav.mvm.payloads;
 
import javax.realtime.PeriodicParameters;
import javax.realtime.RealtimeThread;
import javax.realtime.RelativeTime;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

public class DumbyAStar extends RealtimeThread {

	public class IntPair {
		public int i;
		public int j;
	}

	public class pPair {
		public Boolean isOpen;
		public double first;
	}

	public class cell {
		public int parent_i;
		public int parent_j;
		public double f;
		public double g;
	}
	int WORK_MILLI = 500;
	int SLEEP_MILLI = 500;
	int FLT_MAX = 10000;
	int ROW = 400;
	int COL = 400;
	int openListCount;
	int openListBeginRetVal_i;
	int openListBeginRetVal_j;
	ArrayList<ArrayList<pPair>> openList;
	ArrayList<ArrayList<cell>> cellDetails;
	ArrayList<ArrayList<Boolean>> grid;
	int src_i;
	int src_j;
	int dest_i;
	int dest_j;
	boolean foundDest = false;
	long duration;
			
	boolean isValid(int i, int j) {
		if (i < ROW && i >= 0 && j < COL && j >= 0) return true;
		else return false;
	}

	boolean isDestination(int i, int j) {
		if (i == this.dest_i && j == this.dest_j) return true;
		else return false;
	}

	double calculateHValue(int i, int j) {
		return Math.sqrt((i - dest_i) * (j - dest_j) + (i - dest_i) * (j - dest_j));
	}

	boolean openListEmpty() {
		if (this.openListCount > 0) return false;
		else return false;
	}
	void openListInsert(int i, int j, double f) {
		this.openListCount++;
		this.openList.get(i).get(j).first = f;
		this.openList.get(i).get(j).isOpen = true;
	}
	void openListErase(int i, int j) {
		this.openListCount--;
		this.openList.get(i).get(j).isOpen = false;
	}
	
	void openListBegin() {
		int i, j;
		int i1 = 0;
		int j1 = 0;
		double least_found = 2147483647.0;
		for (i = 0; i < this.ROW; i++) {
			for (j = 0; j < this.COL; j++) {
				if (this.openList.get(i).get(j).isOpen && this.openList.get(i).get(j).first <= least_found) {
					least_found = this.openList.get(i).get(j).first;
					i1 = i;
					j1 = j;
				}
			}
		}
		this.openListBeginRetVal_i = i1;
		this.openListBeginRetVal_j = j1;
	}
	
	void processSuccessor(int ip, int jp, int i, int j) {
		double gNew, hNew, fNew;

		if (isValid(ip, jp) == true) {
			if (isDestination(ip, jp) == true) {
				this.cellDetails.get(ip).get(jp).parent_i = i;
				this.cellDetails.get(ip).get(jp).parent_j = j;
				this.foundDest = true;
				return;
			}
			else if (this.openList.get(ip).get(jp).isOpen == false &&
				this.grid.get(ip).get(jp)) {
					gNew = this.cellDetails.get(ip).get(jp).g + 1.414;
					hNew = this.calculateHValue(ip, jp);
					fNew = gNew + hNew;
				if (this.cellDetails.get(ip).get(jp).f == this.FLT_MAX ||
					this.cellDetails.get(ip).get(jp).f > fNew) {
					openListInsert(ip, jp, fNew);
					this.cellDetails.get(ip).get(jp).f = fNew;
					this.cellDetails.get(ip).get(jp).g = gNew;
					this.cellDetails.get(ip).get(jp).parent_i = i;
					this.cellDetails.get(ip).get(jp).parent_j = j;
				}
			}
		}
	}

	void aStarSearch() {
		int i,j;
		int index = 0;
		ArrayList al = new ArrayList<Integer>(500);
		for (int ind = 0; ind < 500; ind++) {
			al.add(ind, 0);
		}
		int iterations = 0;
		int iterations_before_sleep = 10; 
		while (!this.openListEmpty()) {
			al.set(index,1000);
			index++;
			iterations++;
			this.openListBegin();
			this.openListErase(openListBeginRetVal_i, openListBeginRetVal_j);
			i = this.openListBeginRetVal_i;
			j = this.openListBeginRetVal_j;
			this.processSuccessor(i - 1, j, i, j);
			if (this.foundDest == true) return;
			this.processSuccessor(i + 1, j, i, j);
			if (this.foundDest == true) return;
			this.processSuccessor(i, j + 1, i, j);
			if (this.foundDest == true) return;
			this.processSuccessor(i, j - 1, i, j);
			if (this.foundDest == true) return;
			this.processSuccessor(i - 1, j + 1, i, j);
			if (this.foundDest == true) return;
			this.processSuccessor(i - 1, j - 1, i, j);
			if (this.foundDest == true) return;
			this.processSuccessor(i + 1, j + 1, i, j);
			if (this.foundDest == true) return;
			this.processSuccessor(i + 1, j - 1, i, j);
			if (this.foundDest == true) return;
			if ( iterations >= iterations_before_sleep ) {
				System.out.println("Interupt time: " + System.currentTimeMillis());
				RealtimeThread.currentRealtimeThread().waitForNextPeriod();
				System.out.println("Wakeup time: " + System.currentTimeMillis());
				iterations = 0;
			}
		}
		return;
	}

	public DumbyAStar(long d) {
		this.duration = d;
	}

	void DumbyAStarInit() {
		int i, j;
		this.openList = new ArrayList<ArrayList<pPair>> ();
		this.cellDetails = new ArrayList<ArrayList<cell>>();
		this.openList = new ArrayList<ArrayList<pPair>>();
		this.grid = new ArrayList<ArrayList<Boolean>>();
		Random rand = new Random();
		for (i = 0; i < ROW; i++) {
			ArrayList<Boolean> g1 = new ArrayList<Boolean>();
			this.grid.add(g1);
			ArrayList<pPair> p1 = new ArrayList<pPair>();
			this.openList.add(p1);
			ArrayList<cell> c1 = new ArrayList<cell>();
			this.cellDetails.add(c1);
			for (j = 0; j < COL; j++) {
				if (!(i == 0 && j == 0) && !(i == ROW-1 && j == COL-1))
					this.grid.get(i).add(rand.nextInt(15) >= 1 ? true : false);
				else this.grid.get(i).add(true);
				pPair p = new pPair();
				this.openList.get(i).add(p);
				this.openList.get(i).get(j).first = 0.0;
				this.openList.get(i).get(j).isOpen = false;
				cell c = new cell();
				this.cellDetails.get(i).add(c);
				this.cellDetails.get(i).get(j).f = FLT_MAX;
				this.cellDetails.get(i).get(j).g = FLT_MAX;
				this.cellDetails.get(i).get(j).parent_i = -1;
				this.cellDetails.get(i).get(j).parent_j = -1;
	
				//RealtimeThread.currentRealtimeThread.waitForNextPeriod();
			}
		}
		this.src_i = 0;
		this.src_j = 0;
		this.dest_i = this.ROW - 1;
		this.dest_j = this.COL - 1;
		i = this.src_i;
		j = this.src_j;
		this.cellDetails.get(i).get(j).f = 0.0;
		this.cellDetails.get(i).get(j).g = 0.0;
		this.cellDetails.get(i).get(j).parent_i = i;
		this.cellDetails.get(i).get(j).parent_j = j;
		openListInsert(i, j, 0.0);
	}

	public void run() {
		while (true) {
			System.out.println("Start the AStarPayload Executions...");
			DumbyAStar payload = new DumbyAStar(50000000);
			payload.DumbyAStarInit();
			payload.aStarSearch();
			System.out.println("Finish the AStarPayload Executions...");
		}
	}

	public static void main(String[] args) {
		
		DumbyAStar app1 = new DumbyAStar(50000000); // Thread will run for 50 ms and records wakeup time
		
		
		app1.setReleaseParameters(new PeriodicParameters(new RelativeTime(1000,0)));
		app1.start();
	}
}
