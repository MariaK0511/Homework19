public class Task3 {
    public static void main(String[] args) {
        // 3. Напишите многопоточный ограниченный буфер с использованием synchronized.
        Object monitor = new Object();
        final String[] message = {""};
        new Thread(() -> {
            try {
                synchronized (monitor) {
                    while (!message[0].equals("have a nice day")) {
                        System.out.println("good night is waiting good day");
                        monitor.wait();
                    }
                    message[0] = "good night";
                    System.out.println(message[0]);
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                synchronized (monitor) {
                    while (!message[0].equals("")) {
                        System.out.println("good morning is waiting empty thread");
                        monitor.wait();
                    }
                    message[0] = "good morning";
                    System.out.println(message[0]);
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                synchronized (monitor) {
                    while (!message[0].equals("good morning")) {
                        System.out.println("have a nice day is waiting good morning");
                        monitor.wait();
                    }
                    message[0] = "have a nice day";
                    System.out.println(message[0]);
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}