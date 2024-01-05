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

    // method to validate the sequence modified by the Tratador thread.
    public void validar() {
        this.texto = this.tratador.textoModificado();
        int quant_a = 0;
        int quant_b = 0;

        // counting the number of A and B.
        for(int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == 'a' || texto.charAt(i) == 'A') {
                quant_a++;
            }

            if (texto.charAt(i) == 'b' || texto.charAt(i) == 'B') {
                quant_b++;
            }
        }

        // Perform the necessary checks to validate the expression.

        // if the character A has a higher frequency then the expression is invalid.
        if (quant_a > quant_b) {
            System.out.println("Caractere 'a'|'A' é mais frequente!");
            System.out.println("Sequência inválida!");
        }

        // if the character B has a higher frequency then another verification must be done.
        // if the amount of characters B is exactly the double the amount of characters A, then the expression is valid.
        // Othersiwe, the expression is invalid.
        if(quant_b > quant_a) {
            System.out.println("Caractere 'b'|'B' é mais frequente!");

            if (quant_a * 2 == quant_b) {
                System.out.println("Sequencia válida!");
            }

            else {
                System.out.println("Sequência Inválida!");
            }
        }

        // if the characters A and B have the same frequency, then the expression is valid.
        if (quant_a == quant_b){
            System.out.println("Frequência igual para ambos os caracteres!");
            System.out.println("Sequência válida!");
        }
    }

    // default method used to perform the signaling action between semaphores
    // and also to execute the necessary methods of the thread.
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