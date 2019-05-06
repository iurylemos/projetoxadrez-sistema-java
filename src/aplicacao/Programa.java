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
		 * Vou criar uma fun��o para imprimir as pe�as
		 * dessa partida
		 * 
		 * Vou criar uma classe chamada UI = User Interface
		 * E dentro dessa classe vou criar um metodo
		 * chamado printBoard
		 * Esse metodo vai receber a matriz de pe�as
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
			 * Imprimir as posi��o possiveis a partir
			 * da pe�a de origem.
			 * 
			 * Depois que o usu�rio digitar a posi��o de origem
			 * 
			 * Vou declarar uma matriz booleana
			 * vou chamar ela de movimentosPossiveis
			 * e ela vai receber
			 * o meu partidaXadrez . movimentosPossiveis
			 * a partida minha posi��o de origem
			 * que veio do usu�rio
			 * 
			 */
				
				boolean[][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				/*
				 * Limpar minha tela
				 */
				UI.limparTela();
				/*
				 * ap�s a limpeza
				 * vou imprimir de novo o tabuleiro
				 */
				UI.imprimaTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				
				
				System.out.println();
				System.out.print("Digite a posicao de destino: ");
				XadrezPosicao destino = UI.lerXadrezPosicao(sc);
				
				PecaXadrez capturarPeca = partidaXadrez.executarMovimentoXadrez(origem, destino);
			}catch(XadrezException e) {
				/*
				 * Vou fazer um tratamento b�sico
				 * Vou imprimir a mensagem na tela
				 * e vou fazer um nextLine, 
				 * para o programa aguardar eu apertar ENTER
				 */
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				/*
				 * Esse InputMimatchException
				 * existe l� no IU
				 * E existe uma mensagem dentro dele
				 * que corresponde a
				 * Erro lendo XadrezPosicao. Os valores v�lidos s�o de a1 at� h8
				 * Vou fazer um tratamento b�sico
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
