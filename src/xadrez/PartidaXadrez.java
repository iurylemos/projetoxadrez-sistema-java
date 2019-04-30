package xadrez;

import camada.tabuleiro.Tabuleiro;

public class PartidaXadrez {
	/*
	 * Cora��o do sistema de Xadrez
	 * Nessa classe vai ter as regras
	 */
	
	private Tabuleiro tabuleiro;
	/*
	 * Esse tabuleiro tem uma matriz de pe�as
	 * S� que essas pe�as s�o do tipo Peca
	 */
	
	public PartidaXadrez () {
		/*
		 * Quem tem que saber a dimens�o de um
		 * tabuleiro de xadrez
		 * � a classe PartidaXadrez.
		 */
		tabuleiro = new Tabuleiro(8, 8);
	}
	
	public PecaXadrez[][] getPecas() {
		/*
		 * Esse metodo vai ter que retornar uma matriz
		 * de pe�as de xadrez correspondentes
		 * a essa partida.
		 */
		/*
	 * O que � que eu vou ter que fazer?
	 * as pe�as dentro do construtor da classe Tabuleiro
	 * ele tem as pe�as. Se voc� ver l� na classe Tabuleiro
	 * Ele tem uma matriz de pe�as e elas s�o do tipo Peca
	 * mas o meu metodo aqui na classe PartidaXadrez
	 * ele retorna o PecaXadrez
	 * Por que?
	 * Por que estou na camada(PACOTE) de xadrez
	 * Para a minha aplicacao que vai ser a classe
	 * Programa no pacote aplicacao
	 * Eu n�o quero liberar as pecas do tipo Peca
	 * Mas sim do tipo PecaXadrez
	 * Por que?
	 * Por que estou fazendo um desenvolvimento em camada
	 * Ent�o a principal ele vai poder enchergar somente
	 * a pe�a de Xadrez.
	 */
		/*
		 * Vou criar tempor�riamente uma vari�vel auxiliar
		 * do tipo PecaXadrez, vou chamar ela de mat (MATRIZ DE PE�AS)
		 * vou iniciar ela instanciando a classe PecaXadrez
		 * quantas linhas? as linhas do tabuleiro
		 * quantas colunas? as colunas do tabuleiro
		 * 
		 */
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		/*
		 * Agora vou pecorrer a matriz de pe�as do tabuleiro
		 */
		for (int i =0; i < tabuleiro.getLinhas(); i++) {
			for(int j=0; j < tabuleiro.getColunas(); j++) {
				/*
				 * Para cada posi��o i, j do meu tabuleiro
				 * vou fazer a minha matriz mat receba
				 * o tabuleiro.peca(i,j)
				 * Vou ter que fazer o casting
				 * para a classe PecaXadrez
				 * Por que ai sim ele vai interpretar
				 * como uma pe�a de XADREZ
				 * e n�o como pe�a comum
				 */
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	
}
