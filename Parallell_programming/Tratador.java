package Parallell_programming;

import java.io.*;
import java.util.concurrent.Semaphore;

public class Tratador extends Thread{

    private final Semaphore b;
    private final Semaphore d;
    private final Semaphore c;
    private final Semaphore a;

    private String textoModificado;
    Criador cr;

    public Tratador(Semaphore b, Semaphore d, Semaphore c, Semaphore a, Criador cr) {
        this.b = b;
        this.d = d;
        this.c = c;
        this.a = a;
        this.cr = cr;
    }

    // método para pegar o texto modificado pela thread.
    public String textoModificado() {
        return textoModificado;
    }

    // método para modificar a sequência que foi gerada pela thread Criador.
    public void transform() throws IOException {
        StringBuilder sb = new StringBuilder();
        String textoPrevio = cr.pegar_texto();
        sb.append(textoPrevio);
        if (sb.charAt(0) == 'b') {
            String aux = sb.toString();
            this.textoModificado = aux.toUpperCase();
        } else {
            sb.reverse();
            this.textoModificado = sb.toString();
        }
        System.out.println("Sequência modificada: " + this.textoModificado);
    }


    // método padrão utilizado para executar a sinalização entre os semáforos
    // e também para executar os métodos necessários da thread.
    public void run() {
        try {
            while (true) {
                b.acquire();
                c.acquire();
                transform();
                a.release();
                d.release();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

}