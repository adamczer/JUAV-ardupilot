package ub.cse.juav.payloads;

import java.util.LinkedList;
import java.util.List;

public class GreedyFailureRunnable implements Runnable{
    List<Long> longList;
    @Override
    public void run() {
        longList = new LinkedList<>();
        while(true) {
            longList.add(new Long(Long.MAX_VALUE));
        }
    }
}
