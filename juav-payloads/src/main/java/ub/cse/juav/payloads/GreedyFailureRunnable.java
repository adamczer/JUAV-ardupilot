package ub.cse.juav.payloads;

import java.util.LinkedList;
import java.util.List;

public class GreedyFailureRunnable implements Runnable{
    private static void doTheSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        Thread.currentThread().setName("GREEDY-THREAD");
        List<byte[]> bal = new LinkedList<>();
        int count = 1;
        int multiplier = 10000000;
        while (count*multiplier >250) {
            try {
//                System.out.println("Allocating "+ multiplier +"*"+ count);
                bal.add(new byte[multiplier * count]);
                doTheSleep(5000);
                count = count+1;
            } catch (OutOfMemoryError e) {
//                System.out.println("count was " + count+ ". multiplier was "+ multiplier);
                multiplier = multiplier/5;
                count = count - 1;
            }
        }
        doTheSleep(10000);
        bal.add(new byte[Integer.MAX_VALUE]);
        for(byte[] ba : bal)
            for(byte b:ba)
                ;
    }
}
