package camada.tabuleiro;

public abstract class Peca {
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
	
	/*
	 * Operação de movimentos possíveis da peça
	 * Abstrata pois é um tipo específico.
	 * EX: Eu sei quais são os movimentos da torre
	 * Mas da peça generica eu não sei.
	 * E vai ser uma matriz bidimensional
	 * de acordo com o tabuleiro.
	 */
	
	public abstract boolean[][] possivelMovimento();
	
	public boolean possivelMovimentacao(Posicao posicao) {
		/*
		 * Retorne o possivelMovimento que é um metodo 
		 * que retorna uma Matriz.
		 * Na linha posicao.getLinha, e posicao.getColuna
		 * Ou seja aqui é um metodo concreto que está
		 * utilizando o metodo abstrato.
		 * Isso se chama: HOOK METODOS
		 * HOOK vem de GANCHO
		 * Ou seja ele é um metodo que faz um gancho
		 * com a SUB CLASSE (POSICAO)
		 * Na verdade
		 * Esse metodo aqui pode ser concreto
		 * Por que?
		 * Por que ele tá chamando um possivelMovimento
		 * de alguma subCLASSE concreta, da classe PECA
		 * e isso é totalmente possível na OO
		 * 
		 * Existe até um padrão de projeto 
		 * chamado TEMPLATE METODO
		 * Você consegue fornecer uma implementação padrão
		 * de um metodo que depende de um metodo abstrato
		 * Isso só vai fazer sentido quando existir
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
		 * para verificar se existe pelo menos 1 posição
		 * da matriz que seja verdadeira.
		 * 
		 * Vou criar uma variavel auxiliar
		 * que também é uma matriz booleana
		 * que ela vai receber uma possivelMovimento()
		 * e essa matriz que for retornada aqui
		 * ela vai ter que ser pecorrida agora
		 * para verificar se existe alguma posição
		 * que seja verdadeira.
		 * 
		 */
		boolean[][] mat = possivelMovimento();
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++) {
			/*
			 * Estou colocando j menor que tamanho mat
			 * por que estou presumindo que a matriz é quadrada
			 */
				if(mat[i][j]) {
					/*
					 * Se a matriz for verdadeira 
					 * vou retornar TRUE
					 * pois ai sim vai dizer que existe
					 * um movimento possível
					 */
					return true;
				}
				
			}
			
		}
		/*
		 * Depois do FOR
		 * Por outro lado se a varredura da matriz
		 * se esgostar e em lugar nenhum encontrar o true
		 * significa que nenhuma posição era verdadeira
		 * ai vou retornar false
		 */
		return false;
	}
	
	
	

}
