import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task2 {
    public static void main(String[] args) {
//2. Напишите многопоточный ограниченный буфер с использованием ReentrantLock.
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        final String[] message = {""};
        new Thread(() -> {
            try {
                lock.lock();
                while (!message[0].equals("have a nice day")) {
                    System.out.println("good night is waiting good day");
                    condition.await();
                }
                message[0] = "good night";
                System.out.println(message[0]);
                condition.signalAll();
                lock.unlock();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                lock.unlock();
                while (!message[0].equals("")) {
                    System.out.println("good morning is waiting empty thread");
                    condition.await();
                }
                message[0] = "good morning";
                System.out.println(message[0]);
                condition.signalAll();
                lock.unlock();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                while (!message[0].equals("good morning")) {
                    System.out.println("have a nice day is waiting good morning");
                    condition.await();
                }
                message[0] = "have a nice day";
                System.out.println(message[0]);
                condition.signalAll();
                lock.unlock();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}