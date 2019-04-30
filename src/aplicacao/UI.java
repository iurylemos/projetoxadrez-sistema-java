package aplicacao;

import xadrez.PecaXadrez;

public class UI {
	public static void imprimaTabuleiro(PecaXadrez[][] pecas) {
		/*
		 * Imprimindo os tracinhos dizendo qual o valor
		 * de cada linha e coluna
		 */
		for(int i=0; i<pecas.length; i++) {
			//Aqui abaixo vai imprimir a linha
			System.out.print((8 - i) + " ");
			for(int j=0; j<pecas.length; j++) {
				/*
				 * Estou colocando o lenght pois
				 * estou considerando que a matriz
				 * vai ser quadrada
				 * 
				 * E aqui abaixo vou imprimir a peca
				 * utilizando o metodo imprimaPeca
				 */
				imprimaPeca(pecas[i][j]);
			}
			/*
			 * Depois do for das colunas
			 * Eu tenho que dar uma quebra de linha
			 * Para ele imprimir a próxima linha
			 * Antes de fechar o for da linha
			 * 
			 */
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	/*
	 * Vou criar um metodo auxiliar para imprimir uma peça
	 */
	
	private static void imprimaPeca(PecaXadrez pecas) {
		if(pecas == null) {
	//Se essa peça for nulo, ela não existe no tabuleiro
			System.out.print("-");
		}else {
			//Caso contrário vou imprimir a peca
			System.out.print(pecas);
		}
	/*
	 * De qualquer forma vou imprimir um espaço em branco
	 * para que as peças não fiquem grudadas uma na outra
	 */
		System.out.print(" ");
	}
}
