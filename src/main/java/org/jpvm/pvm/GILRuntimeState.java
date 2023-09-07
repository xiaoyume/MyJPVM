package org.jpvm.pvm;

import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Data
public class GILRuntimeState {
    private final long interval;
    private volatile boolean dropGILRequest;
    private volatile long a1, a2, a3, a4, a5, a6, a7, a8;
    private final AtomicBoolean locked;
    private volatile long b1, b2, b3, b4, b5, b6, b7, b8;
    private volatile long switchNumber;
    private volatile long c1, c2, c3, c4, c5, c6, c7, c8;
    private volatile Thread lastHolder;
    private final ReentrantLock lock;
    private final Condition condition;
    public GILRuntimeState(long interval){
        this.interval = interval;
        lock = new ReentrantLock();
        condition = lock.newCondition();
        switchNumber = 0;
        locked = new AtomicBoolean();
    }
    public void takeGIL(){

        try {
            lock.lock();
            for(;;){
                while(locked.get()){
                    long saveSwichNumber = switchNumber;
                    long start = System.currentTimeMillis();
                    try {
                        condition.wait(interval);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    long end = System.currentTimeMillis();
                    if((end - start) >= interval && locked.get() && !dropGILRequest
                            && saveSwichNumber == switchNumber){
                        dropGILRequest = true;
                    }
                }
                if(locked.compareAndSet(false, true)){
                    dropGILRequest = false;
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void dropGIL(){
        try {
            lock.lock();
            locked.set(false);
            lastHolder = Thread.currentThread();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }


}
