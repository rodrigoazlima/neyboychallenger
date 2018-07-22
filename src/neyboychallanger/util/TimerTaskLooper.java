/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neyboychallanger.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rodrigo
 */
public class TimerTaskLooper {

    private Timer timer;
    private Runnable runnable;

    public TimerTaskLooper(Timer timer, Runnable runnable) {
        this.timer = timer;
        this.runnable = runnable;
    }

    public void schedule() {
        try {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        schedule();
                    }
                }
            }, 1000L);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
