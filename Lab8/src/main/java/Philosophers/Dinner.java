package Philosophers;

public class Dinner {
    public static void main(String[] args) throws Exception {

        final Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == philosophers.length - 1) {

                // The last philosopher picks up the right fork first
                philosophers[i] = new Philosopher(rightFork, leftFork, i+1);
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork, i+1);
            }

            Thread t
                    = new Thread(philosophers[i]);
            t.start();
        }
    }
}
