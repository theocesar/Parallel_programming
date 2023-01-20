package Parallell_programming;

import java.util.concurrent.Semaphore;

public class Analisador extends Thread{

    private final Semaphore d;
    private final Semaphore c;
    private final Tratador tratador;
    private String texto;

    public Analisador(Semaphore d, Semaphore c, Tratador tratador) {
        this.d = d;
        this.c = c;
        this.tratador = tratador;
    }

    // método para validar a sequência modificada pela thread Tratador.
    public void validar() {
        this.texto = this.tratador.textoModificado();
        int quant_a = 0;
        int quant_b = 0;

        for(int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == 'a' || texto.charAt(i) == 'A') {
                quant_a++;
            }

            if (texto.charAt(i) == 'b' || texto.charAt(i) == 'B') {
                quant_b++;
            }
        }

        if (quant_a > quant_b) {
            System.out.println("Caractere 'a'|'A' é mais frequente!");
            System.out.println("Sequência inválida!");
        }

        if(quant_b > quant_a) {
            System.out.println("Caractere 'b'|'B' é mais frequente!");

            if (quant_a * 2 == quant_b) {
                System.out.println("Sequencia válida!");
            }

            else {
                System.out.println("Sequência Inválida!");
            }
        }

        if (quant_a == quant_b){
            System.out.println("Frequência igual para ambos os caracteres!");
            System.out.println("Sequência válida!");
        }
    }

    // método padrão utilizado para executar a sinalização entre os semáforos
    // e também para executar os métodos necessários da thread.
    public void run() {
        try {
            while (true) {
                d.acquire();
                validar();
                c.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}