public class Task1 {
    public static void main(String[] args) {
          /*
        1. Создать три потока Т1, Т2 и ТЗ
Реализовать выполнение поток в последовательности ТЗ -> T2 -> T1 (используя метод join) +
         */
        MyThread t1 = new MyThread(1, 5);
        MyThread t2 = new MyThread(5, 20);
        MyThread t3 = new MyThread(20, 40);

        try {
            t3.start();
            t3.join();
            t2.start();
            t2.join();
            t1.start();
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
