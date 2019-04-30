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
	
	public Peca peca(int linha, int coluna) {
		/*
		 * Esse metodo me retorna a matriz
		 * na linha e coluna
		 */
		return pecas[linha][coluna];
	}
	/*
	 * Vou realizar uma sobrecarga
	 * S� que agora vai me retornar o POSICAO
	 * da classe POSICAO puxando as linhas e colunas
	 */
	
	public Peca peca(Posicao posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		/*
		 * Este metodo vai ter que ir na matriz de PECAS
		 * do meu TABULEIRO que est� no construtor 
		 * na linha .getLinha()
		 * e na coluna .getColuna
		 * e atribuir a essa posi��o da matriz de pe�a
		 * a pe�a que veio aqui no parametro
		 */
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		/*
		 * E vou ter que falar aqui abaixo, que essa pe�a 
		 * n�o est� mais na posi��o nula, ela est� na 
		 * posi��o que veio no parametro
		 * 
		 * E eu posso colocar ela assim diretamente
		 * pois a posi��o na classe Peca � protected
		 * ou seja pode ser acessada pelo mesmo pacote
		 * ou por uma sub classe
		 */
		peca.posicao = posicao;
	}
	
	

	
}
