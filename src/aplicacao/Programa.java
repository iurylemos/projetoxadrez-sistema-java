package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		/*
		 * Vou criar uma função para imprimir as peças
		 * dessa partida
		 * 
		 * Vou criar uma classe chamada UI = User Interface
		 * E dentro dessa classe vou criar um metodo
		 * chamado printBoard
		 * Esse metodo vai receber a matriz de peças
		 * da minha partida.
		 * 
		 */
		while(true) {
				UI.imprimaTabuleiro(partidaXadrez.getPecas());
				System.out.println();
				System.out.println("Digite a posição de origem: ");
				XadrezPosicao origem = UI.lerXadrezPosicao(sc);
				
				System.out.println();
				System.out.println("Digite a posição de destino: ");
				XadrezPosicao destino = UI.lerXadrezPosicao(sc);
				
				PecaXadrez capturarPeca = partidaXadrez.executarMovimentoXadrez(origem, destino);
		}

	}

}
