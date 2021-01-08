package ub.cse.juav.payloads;
public class DumbyPi implements Runnable {
    public DumbyPi() {
	
    }

    public void run() {
        int i = 0;
        double sum = 0;
        while (true) {
           sum += ((-1)^(i+1)) / (2 * i - 1);
	   i+=1;
	   if (i == 10000000) System.out.println("pi");
        }
    }
}

