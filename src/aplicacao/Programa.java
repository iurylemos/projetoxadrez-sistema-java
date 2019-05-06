package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezException;
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
			try {
				UI.limparTela();
				UI.imprimaPartida(partidaXadrez);
				System.out.println();
				System.out.print("Digite a posicao de origem: ");
				XadrezPosicao origem = UI.lerXadrezPosicao(sc);
				
			/*
			 * Imprimir as posição possiveis a partir
			 * da peça de origem.
			 * 
			 * Depois que o usuário digitar a posição de origem
			 * 
			 * Vou declarar uma matriz booleana
			 * vou chamar ela de movimentosPossiveis
			 * e ela vai receber
			 * o meu partidaXadrez . movimentosPossiveis
			 * a partida minha posição de origem
			 * que veio do usuário
			 * 
			 */
				
				boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				/*
				 * Limpar minha tela
				 */
				UI.limparTela();
				/*
				 * após a limpeza
				 * vou imprimir de novo o tabuleiro
				 */
				UI.imprimaTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				
				
				System.out.println();
				System.out.print("Digite a posicao de destino: ");
				XadrezPosicao destino = UI.lerXadrezPosicao(sc);
				
				PecaXadrez capturarPeca = partidaXadrez.executarMovimentoXadrez(origem, destino);
			}catch(XadrezException e) {
				/*
				 * Vou fazer um tratamento básico
				 * Vou imprimir a mensagem na tela
				 * e vou fazer um nextLine, 
				 * para o programa aguardar eu apertar ENTER
				 */
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				/*
				 * Esse InputMimatchException
				 * existe lá no IU
				 * E existe uma mensagem dentro dele
				 * que corresponde a
				 * Erro lendo XadrezPosicao. Os valores válidos são de a1 até h8
				 * Vou fazer um tratamento básico
				 * Vou imprimir a mensagem na tela
				 * e vou fazer um nextLine, 
				 * para o programa aguardar eu apertar ENTER
				 */
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}

	}

}
