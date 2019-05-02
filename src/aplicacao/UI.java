package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Color;
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
	 * Ler a Posicao
	 * vou criar um metodo
	 * da classe XadrezPosicao
	 * E vou passar como parametro o Scanner
	 * Que vai ser instanciado na Principal
	 * E eu vou receber ele aqui como argumento
	 * E vou fazer a leitura da minha posi��o
	 * atrav�s do Scanner
	 */
	public static XadrezPosicao lerXadrezPosicao(Scanner sc) {
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
				 */
				imprimaPeca(pecas[i][j]);
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
	 * Vou criar um metodo auxiliar para imprimir uma pe�a
	 */

	private static void imprimaPeca(PecaXadrez pecas) {
		if (pecas == null) {
			// Se essa pe�a for nulo, ela n�o existe no tabuleiro
			System.out.print("-");
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
}
