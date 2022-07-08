public class MyThread extends Thread {

    private int a;
    private int b;


    public MyThread(int a, int b) {

        this.a = a;
        this.b = b;

    }

    @Override
    public void run() {
        doSmth();

    }

    public void doSmth() {
        for (int i = a; i < b; i++) {
            System.out.println(i);
        }

    }
}
