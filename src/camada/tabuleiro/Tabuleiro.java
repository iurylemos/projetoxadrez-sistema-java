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
	 * Não criei o get da matriz e set
	 * pois o outro metodo só vai acessar uma peça
	 * por vez, e não todas as peças
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
	
	public Peca peca(int linha, int coluna) {
		/*
		 * Esse metodo me retorna a matriz
		 * na linha e coluna
		 */
		return pecas[linha][coluna];
	}
	/*
	 * Vou realizar uma sobrecarga
	 * Só que agora vai me retornar o POSICAO
	 * da classe POSICAO puxando as linhas e colunas
	 */
	
	public Peca peca(Posicao posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	

	
}
