package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		 * Essa lista de peças capturadas
		 * vou passar ela também como argumento
		 * no metodo imprimaPartida
		 * 
		 */
		List<PecaXadrez> capturadas = new ArrayList<>();
		
		/*
		 * Vou criar uma função para imprimir as peças
		 * dessa partida
		 * 
		 * Vou criar uma classe chamada UI = User Interface
		 * E dentro dessa classe vou criar um metodo
		 * chamado imprimaTabuleiro
		 * Esse metodo vai receber a matriz de peças
		 * da minha partida.
		 * 
		 * Enquanto minha partida não estiver em checkMate.
		 */
		while(!partidaXadrez.getCheckMate()) {
			try {
				UI.limparTela();
				UI.imprimaPartida(partidaXadrez, capturadas);
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
				/*
				 * Sempre que eu executar um movimento, e retornar uma peça capturada
				 * eu vou testar se essa pecaCapturada for diferente de nulo
				 * é por que alguma peça foi capturada
				 */
				if(capturarPeca != null) {
					/*
					 * Eu vou acrescentar
					 * essa PecaCapturada
					 * dentro da lista capturadas
					 * 
					 * Então sempre que eu executarUmMovimento
					 * E essa movimento resultar em um capturarPeca
					 * Eu vou adicionar essa peca capturada
					 * a minha lista capturadas
					 */
					capturadas.add(capturarPeca);
				}
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
		/*
		 * Terminou o meu enquanto, significa que deu checkMate
		 * Ai vou chamar o meu IU
		 * Limpando a tela
		 * E vou imprimir novamente a partida
		 * para termos a visão da partida finalizada.
		 */
		UI.limparTela();
		UI.imprimaPartida(partidaXadrez, capturadas);

	}

}
