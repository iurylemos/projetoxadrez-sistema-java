package camada.tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	/*
	 * Criando uma matriz da classe peca
	 */
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
	//Inserindo o numero de linhas e colunas na matriz
		pecas = new Peca[linhas][colunas];
	}
	/*
	 * N�o criei o get da matriz e set
	 * pois o outro metodo s� vai acessar uma pe�a
	 * por vez, e n�o todas as pe�as
	 */

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	

	
}
