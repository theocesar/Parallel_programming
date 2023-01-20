package Parallell_programming;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.io.IOException;

public class Criador extends Thread {

    private String texto;
    private final Semaphore a;
    private final Semaphore b;

    public Criador(Semaphore a, Semaphore b) {
        this.a = a;
        this.b = b;
    }
    // método para pegar o texto gerado pela thread.
    public String pegar_texto() {
        return texto;
    }

    // método para gerar a sequência de forma aleatória.
    public void gerar_texto() throws IOException {
        this.texto = "";
        int cont = 0;
        Random random = new Random();
        while (cont < 12) {
            char caracter = ' ';
            caracter = (char) (97 + random.nextInt(2));
            this.texto += caracter;
            cont++;
        }
        System.out.println("Sequência: " + texto);
    }

    // método padrão utilizado para executar a sinalização entre os semáforos
    // e também para executar os métodos necessários da thread.
    public void run() {
        try {
            while (true) {
                a.acquire();
                gerar_texto();
                b.release();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }

}