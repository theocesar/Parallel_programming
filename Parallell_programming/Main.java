package Parallell_programming;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // creating the semaphores.
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(1);
        Semaphore d = new Semaphore(0);

        // creating the threads.
        Criador cr = new Criador(a, b);
        Tratador t = new Tratador(b, d, c, a, cr);
        Analisador ans = new Analisador(d, c, t);

        // starting the threads.
        cr.start();
        t.start();
        ans.start();

    }
}

