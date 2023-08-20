package com.victor.cm.visao;

import com.victor.cm.excecao.ExplosaoException;
import com.victor.cm.excecao.SairException;
import com.victor.cm.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner sc = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
           boolean continuar = true;

           while(continuar) {
               cicloDoJogo();

               System.out.println("Outra partida? (S/n) ");
               String resposta = sc.nextLine();

               if("n".equalsIgnoreCase(resposta)) {
                   continuar = false;
               } else {
                   tabuleiro.reiniciar();
               }
           }
        } catch (SairException e) {
            System.out.println("Tchau!!!");
        } finally {
            sc.close();
        }
    }

    private void cicloDoJogo() {
        try {

            while(!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);

                String entrada = capturarValorDigitado("Digite (x, y): ");

                Iterator<Integer> xy = Arrays.stream(entrada.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();

                entrada = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");

                if("1".equals(entrada)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equals(entrada)) {
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }
            }

            System.out.println("Você ganhou!");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você perdeu!");
        }
    }

    public String capturarValorDigitado(String texto) {
        System.out.print(texto);
        String entrada = sc.nextLine();

        if ("sair".equalsIgnoreCase(entrada)) {
            throw new SairException();
        }

        return entrada;
    }
}
