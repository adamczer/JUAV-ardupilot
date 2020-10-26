package ub.cse.juav.copter;
import java.util.concurrent.TimeUnit ;
public class DumbyMem implements Runnable {
    public DumbyMem() {
	
    }

    public void dumby_func_3() {
        int[] a = new int[100000];
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (java.lang.InterruptedException e) {
            System.out.println("CANNOT SLEEP");
        }
    }

    public void dumby_func_2() {
        int[] a = new int[100000];
        try {
            TimeUnit.SECONDS.sleep(1);
        }  catch (java.lang.InterruptedException e) {
            System.out.println("CANNOT SLEEP");
        }
        dumby_func_3();
        try {
            TimeUnit.SECONDS.sleep(1);
        }  catch (java.lang.InterruptedException e) {
            System.out.println("CANNOT SLEEP");
        }
    }

    public void dumby_func_1() {
        int[] a = new int[100000];
        try {
            TimeUnit.SECONDS.sleep(1);
        }  catch (java.lang.InterruptedException e) {
            System.out.println("CANNOT SLEEP");
        }
        dumby_func_2();
        try {
            TimeUnit.SECONDS.sleep(1);
        }  catch (java.lang.InterruptedException e) {
            System.out.println("CANNOT SLEEP");
        }
    }

    public void run() {
        while (true) {
            int[] a = new int[100000];
            try {
                TimeUnit.SECONDS.sleep(1);
            }  catch (java.lang.InterruptedException e) {
                System.out.println("CANNOT SLEEP");
            }
	    dumby_func_1();
            try {
                TimeUnit.SECONDS.sleep(1);
            }  catch (java.lang.InterruptedException e) {
                System.out.println("CANNOT SLEEP");
            }
        }
    }
}

