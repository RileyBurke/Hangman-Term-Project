package hangman;

import java.util.Timer;
import java.util.TimerTask;

/**
 *  Class used to track users inactivity and time them out if too much time passes without any input.
 */
class IdleTimer {

    Timer timer;

    /**
     * Schedules a new 30-second timer.
     */
    public IdleTimer() {
        timer = new Timer();
        timer.schedule(new IdleTask(), 30 * 1000);
    }

    /**
     * An inner class for handling the timers tasks.
     */
    class IdleTask extends TimerTask {
        /**
         * The timer task, where if 30 seconds elapse the program will exit.
         */
        public void run() {
            System.out.println("\nTimed out, exiting program.");
            System.exit(0);
            timer.cancel();
        }
    }
}
