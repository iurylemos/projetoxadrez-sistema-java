package camada.tabuleiro;

public abstract class Peca {
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
	
	/*
	 * Opera��o de movimentos poss�veis da pe�a
	 * Abstrata pois � um tipo espec�fico.
	 * EX: Eu sei quais s�o os movimentos da torre
	 * Mas da pe�a generica eu n�o sei.
	 * E vai ser uma matriz bidimensional
	 * de acordo com o tabuleiro.
	 */
	
	public abstract boolean[][] possivelMovimento();
	
	public boolean possivelMovimentacao(Posicao posicao) {
		/*
		 * Retorne o possivelMovimento que � um metodo 
		 * que retorna uma Matriz.
		 * Na linha posicao.getLinha, e posicao.getColuna
		 * Ou seja aqui � um metodo concreto que est�
		 * utilizando o metodo abstrato.
		 * Isso se chama: HOOK METODOS
		 * HOOK vem de GANCHO
		 * Ou seja ele � um metodo que faz um gancho
		 * com a SUB CLASSE (POSICAO)
		 * Na verdade
		 * Esse metodo aqui pode ser concreto
		 * Por que?
		 * Por que ele t� chamando um possivelMovimento
		 * de alguma subCLASSE concreta, da classe PECA
		 * e isso � totalmente poss�vel na OO
		 * 
		 * Existe at� um padr�o de projeto 
		 * chamado TEMPLATE METODO
		 * Voc� consegue fornecer uma implementa��o padr�o
		 * de um metodo que depende de um metodo abstrato
		 * Isso s� vai fazer sentido quando existir
		 * uma classe concreta que implementar esse
		 * metodo abstrato aqui
		 */
		return possivelMovimento()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean existeAlgumaMovimentacaoPossivel() {
		/*
		 * O que essa operacao vai ter que fazer
		 * Ela vai ter que chamar novamente
		 * o metodo abstrato possivelMovimento
		 * que vai me retornar uma matriz de Booleano
		 * ai eu vou varrer essa matriz
		 * para verificar se existe pelo menos 1 posi��o
		 * da matriz que seja verdadeira.
		 * 
		 * Vou criar uma variavel auxiliar
		 * que tamb�m � uma matriz booleana
		 * que ela vai receber uma possivelMovimento()
		 * e essa matriz que for retornada aqui
		 * ela vai ter que ser pecorrida agora
		 * para verificar se existe alguma posi��o
		 * que seja verdadeira.
		 * 
		 */
		boolean[][] mat = possivelMovimento();
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++) {
			/*
			 * Estou colocando j menor que tamanho mat
			 * por que estou presumindo que a matriz � quadrada
			 */
				if(mat[i][j]) {
					/*
					 * Se a matriz for verdadeira 
					 * vou retornar TRUE
					 * pois ai sim vai dizer que existe
					 * um movimento poss�vel
					 */
					return true;
				}
				
			}
			
		}
		/*
		 * Depois do FOR
		 * Por outro lado se a varredura da matriz
		 * se esgostar e em lugar nenhum encontrar o true
		 * significa que nenhuma posi��o era verdadeira
		 * ai vou retornar false
		 */
		return false;
	}
	
	
	

}
