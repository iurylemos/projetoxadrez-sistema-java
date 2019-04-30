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
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		/*
		 * Este metodo vai ter que ir na matriz de PECAS
		 * do meu TABULEIRO que está no construtor 
		 * na linha .getLinha()
		 * e na coluna .getColuna
		 * e atribuir a essa posição da matriz de peça
		 * a peça que veio aqui no parametro
		 */
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		/*
		 * E vou ter que falar aqui abaixo, que essa peça 
		 * não está mais na posição nula, ela está na 
		 * posição que veio no parametro
		 * 
		 * E eu posso colocar ela assim diretamente
		 * pois a posição na classe Peca é protected
		 * ou seja pode ser acessada pelo mesmo pacote
		 * ou por uma sub classe
		 */
		peca.posicao = posicao;
	}
	
	

	
}
