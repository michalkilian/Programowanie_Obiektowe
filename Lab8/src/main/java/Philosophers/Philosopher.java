package Philosophers;

public class Philosopher implements Runnable {
    private Object leftFork;
    private Object rightFork;
    private int id;


    public Philosopher(Object leftFork, Object rightFork, int id) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;
    }


    @Override
    public void run() {
        try {
            while (true) {

                // thinking
                System.out.println("Philosopher #" + id + " is thinking");
                Thread.sleep((500 + (int) (Math.random() * 100)));

                synchronized (leftFork) {
                    System.out.println("Philosopher #" + id + " Picked up left fork");
                    Thread.sleep((500 + (int) (Math.random() * 100)));
                    synchronized (rightFork) {
                        // eating
                        System.out.println("Philosopher #" + id + " Picked up right fork - eating");
                        Thread.sleep((500 + (int) (Math.random() * 100)));

                        System.out.println("Philosopher #" + id + " Put down right fork");
                        Thread.sleep((500 + (int) (Math.random() * 100)));
                        // Back to thinking
                        System.out.println("Philosopher #" + id + " Put down left fork");
                        Thread.sleep((500 + (int) (Math.random() * 100)));
                    }
                }


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
