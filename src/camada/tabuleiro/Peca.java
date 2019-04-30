package camada.tabuleiro;

public class Peca {
	/*
	 * Vou criar um atributo posição
	 * do tipo protected, pois não quero que ela seja
	 * visivel na camada de xadrez
	 * Essa peça vai ter uma associação com a classe
	 * tabuleiro
	 */
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		/*
		 * Estou atribuindo o valor nulo a posição
		 * mas é opcional, pois o java por padrão
		 * já coloca o valor nulo.s
		 */
		posicao = null;
	}

	/*
	 * Somente classe dentro do mesmo pacote 
	 * e subclasse de peças que poderão ter acesso ao tabuleiro
	 * que são peças de xadrez e as peças específicas do xadrez
	 * Essa limitação é importante para evitar erros.
	 */
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	
	
	
	
	

}
