package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Color;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezPosicao;

public class UI {
	/*
	 * Peguei tudo do commit dele de acordo com a AULA
	 * S�o c�digos especiais das cores
	 * Para imprimir no console
	 */
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	//Essas s�o as cores do texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	//Essas s�o as cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	/*
	 * Vou criar um metodo para limpar a tela
	 * Depois que eu inserir os dados da origem e destino
	 * mostre o resultado, e depois limpe a tela
	 */
//https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limparTela() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	
	public static XadrezPosicao lerXadrezPosicao(Scanner sc) {
		/*
		 * Ler a Posicao
		 * vou criar um metodo
		 * da classe XadrezPosicao
		 * E vou passar como parametro o Scanner
		 * Que vai ser instanciado na Principal
		 * E eu vou receber ele aqui como argumento
		 * E vou fazer a leitura da minha posi��o
		 * atrav�s do Scanner
		 */
		/*
		 * Para evitar qualquer erro de formato
		 * Eu vou colocar tudo isso dentro do bloco try
		 * E qualquer erro que acontecer vai cair no catch
		 */
		try {
		String s = sc.nextLine();
		/*
		 * Por que charAt 0? 
		 * Por que a coluna � a primeira posicao
		 * da minha String
		 */
		char coluna = s.charAt(0);
		/*
		 * Para receber a linha, eu vou ter que recortar
		 * o String a partir da posi��o 1
		 * E converter o resultado para inteiro
		 */
		int linha = Integer.parseInt(s.substring(1));
		return new XadrezPosicao(coluna, linha);
		} catch (RuntimeException e) {
			/*
			 * Vou lan�ar essa excess�o
			 * que significa qualquer erro
			 * de entrada de dados.
			 */
			throw new InputMismatchException("Erro lendo XadrezPosicao. Os valores v�lidos s�o de a1 at� h8");
		}
	}
	/*
	 * Imprimir a partida e n�o s� o tabuleiro
	 */
	public static void imprimaPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturadas) {
		imprimaTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		imprimaPecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno: " + partidaXadrez.getTurno());
		/*
		 * Se n�o tiver em checkMate eu espero a proxima jogada
		 * se tiver em check mate, imprimo o ELSE
		 */
		if(!partidaXadrez.getCheckMate()) {
			System.out.println("Aguardando jogador da cor: " + partidaXadrez.getJogadorAtual());
			if(partidaXadrez.getCheck()) {
				/*
				 * Se cair aqui significa que a partida est� em check
				 */
				System.out.println("CHECK!");
				}
		}else {
				System.out.println("CHECKMATE!");
				System.out.println("Vencedor: " +partidaXadrez.getJogadorAtual());
		}
	}
	
	
	public static void imprimaTabuleiro(PecaXadrez[][] pecas) {
		/*
		 * Imprimindo os tracinhos dizendo qual o valor de cada linha e coluna
		 */
		for (int i = 0; i < pecas.length; i++) {
			// Aqui abaixo vai imprimir a linha
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				/*
				 * Estou colocando o lenght pois estou considerando que a matriz vai ser
				 * quadrada
				 * 
				 * E aqui abaixo vou imprimir a peca utilizando o metodo imprimaPeca
				 * ************************************
				 * Quando for para imprimir o tabuleiro
				 * sem a quest�o dos movimentos poss�veis
				 * eu simplesmente vou passar o valor false
				 * indicando que nenhuma pe�a � para ter o fundo colorido
				 */
				imprimaPeca(pecas[i][j], false);
			}
			/*
			 * Depois do for das colunas Eu tenho que dar uma quebra de linha Para ele
			 * imprimir a pr�xima linha Antes de fechar o for da linha
			 * 
			 */
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	/*
	 * Sobre carga do imprimir tabuleiro
	 * s� que agora passando os movimentos poss�veis
	 * que � uma matriz
	 * com cores.
	 */
	public static void imprimaTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				/*
				 * Verifique no imprimaTabuleiro que modifiquei
				 * o imprima pe�a dizendo que � falso
				 * mas quando eu colocar as cores
				 * vai ficar positivo com as cores.
				 * Ai sim vai pintar o fundo colorido
				 * dependendo dessa vari�vel
				 */
				imprimaPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	/*
	 * Fim do imprimir tabuleiro de sobrecarga
	 */
	
	
	
	
	
	
	

	/*
	 * Vou criar um metodo auxiliar para imprimir uma pe�a
	 * Vou atribuir aqui no parametro um boolean background
	 * que � para dizer se � para IMPRIMIR COLORIDO OU n�o
	 */
	private static void imprimaPeca(PecaXadrez pecas, boolean background) {
	if(background) {
		/*
		 * Se o background for verdadeira, vou ter que mudar a cor do fundo da tela
		 */
		System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (pecas == null) {
			// Se essa pe�a for nulo, ela n�o existe no tabuleiro
			System.out.print("-" + ANSI_RESET);
		} else {
			// Caso contr�rio vou imprimir a peca
			/* De acordo com as cores
			 * E para isso tem o teste saber se � branca
			 * ou preta. */
			if (pecas.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + pecas + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + pecas + ANSI_RESET);
            }
		}
		/*
		 * De qualquer forma vou imprimir um espa�o em branco para que as pe�as n�o
		 * fiquem grudadas uma na outra
		 */
		System.out.print(" ");
	}
	
	/**************************************/
	/*
	 * Imprimir as pe�as capturadas
	 * Esse metodo vai receber por parametro
	 * uma lista de PE�AS DE XADREZ
	 */
	
	private static void imprimaPecasCapturadas(List<PecaXadrez> capturadas) {
		/*
		 * Vou criar uma lista das pecas capturadas BRANCA
		 * Essa lista vai ser uma lista das pecasCapturadas
		 * E aqui vou fazer uma express�o lambda
		 * = stream().filtrando(PREDICADO)
		 * PREDICADO vai pegar um elemento da lista X
		 * e ai vou verificar a codi��o desse elemento
		 * -> x.getColor() == Color.WHITE
		 * 
		 * E assim estou filtrando da minha lista
		 * todo mundo cujo a cor � BRANCA
		 * Isso aqui � opera��o b�sica para filtrar minah lista
		 */
		List<PecaXadrez> white = capturadas.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<PecaXadrez> black = capturadas.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Pecas capturadas:");
		System.out.print("Pecas brancas: ");
		/* Vou fazer um macete para imprimir as pe�as brancas
		 * que � uma lista
		 * 
		 * Isso daqui � um jeitinho de imprimir um array
		 * de valores no JAVA
		 * 
		 */
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		/*********************************************/
		System.out.print("Pecas negras: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
		
	}
}
