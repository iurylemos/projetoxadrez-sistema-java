package xadrez;

import camada.tabuleiro.Tabuleiro;

public class PartidaXadrez {
	/*
	 * Coração do sistema de Xadrez
	 * Nessa classe vai ter as regras
	 */
	
	private Tabuleiro tabuleiro;
	/*
	 * Esse tabuleiro tem uma matriz de peças
	 * Só que essas peças são do tipo Peca
	 */
	
	public PartidaXadrez () {
		/*
		 * Quem tem que saber a dimensão de um
		 * tabuleiro de xadrez
		 * É a classe PartidaXadrez.
		 */
		tabuleiro = new Tabuleiro(8, 8);
	}
	
	public PecaXadrez[][] getPecas() {
		/*
		 * Esse metodo vai ter que retornar uma matriz
		 * de peças de xadrez correspondentes
		 * a essa partida.
		 */
		/*
	 * O que é que eu vou ter que fazer?
	 * as peças dentro do construtor da classe Tabuleiro
	 * ele tem as peças. Se você ver lá na classe Tabuleiro
	 * Ele tem uma matriz de peças e elas são do tipo Peca
	 * mas o meu metodo aqui na classe PartidaXadrez
	 * ele retorna o PecaXadrez
	 * Por que?
	 * Por que estou na camada(PACOTE) de xadrez
	 * Para a minha aplicacao que vai ser a classe
	 * Programa no pacote aplicacao
	 * Eu não quero liberar as pecas do tipo Peca
	 * Mas sim do tipo PecaXadrez
	 * Por que?
	 * Por que estou fazendo um desenvolvimento em camada
	 * Então a principal ele vai poder enchergar somente
	 * a peça de Xadrez.
	 */
		/*
		 * Vou criar temporáriamente uma variável auxiliar
		 * do tipo PecaXadrez, vou chamar ela de mat (MATRIZ DE PEÇAS)
		 * vou iniciar ela instanciando a classe PecaXadrez
		 * quantas linhas? as linhas do tabuleiro
		 * quantas colunas? as colunas do tabuleiro
		 * 
		 */
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		/*
		 * Agora vou pecorrer a matriz de peças do tabuleiro
		 */
		for (int i =0; i < tabuleiro.getLinhas(); i++) {
			for(int j=0; j < tabuleiro.getColunas(); j++) {
				/*
				 * Para cada posição i, j do meu tabuleiro
				 * vou fazer a minha matriz mat receba
				 * o tabuleiro.peca(i,j)
				 * Vou ter que fazer o casting
				 * para a classe PecaXadrez
				 * Por que ai sim ele vai interpretar
				 * como uma peça de XADREZ
				 * e não como peça comum
				 */
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	
}
