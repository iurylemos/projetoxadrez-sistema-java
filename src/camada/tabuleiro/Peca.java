package camada.tabuleiro;

public class Peca {
	/*
	 * Vou criar um atributo posi��o
	 * do tipo protected, pois n�o quero que ela seja
	 * visivel na camada de xadrez
	 * Essa pe�a vai ter uma associa��o com a classe
	 * tabuleiro
	 */
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		/*
		 * Estou atribuindo o valor nulo a posi��o
		 * mas � opcional, pois o java por padr�o
		 * j� coloca o valor nulo.s
		 */
		posicao = null;
	}

	/*
	 * Somente classe dentro do mesmo pacote 
	 * e subclasse de pe�as que poder�o ter acesso ao tabuleiro
	 * que s�o pe�as de xadrez e as pe�as espec�ficas do xadrez
	 * Essa limita��o � importante para evitar erros.
	 */
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	
	
	
	
	

}
