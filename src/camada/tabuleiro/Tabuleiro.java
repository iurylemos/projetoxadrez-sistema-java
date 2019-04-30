package camada.tabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	/*
	 * Criando uma matriz da classe peca
	 */
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		/*
		 * Programa��o defensiva
		 * Exemplo n�o faz sentindo as minhas linhas
		 * e colunas serem menor do que 1
		 * est�o se forem, os dados s�o invalidos
		 */
		if(linhas < 1 || colunas < 1) {
			throw new ExcecaoTabuleiro("Erro criando tabuleiro: � necess�rio que haja pelo menos 1 linha e 1 coluna. ");
		}
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
	/*
	 * Retirei o set de linhas e colunas
	 * pois n�o tem sentindo, uma vez criado o tabuleiro
	 * ser realizado a mudan�a no tamanho.
	 */
	public int getColunas() {
		return colunas;
	}

	
	public Peca peca(int linha, int coluna) {
		/*
		 * Programa��o defensiva:
		 * SE essa POSI��O n�o EXISTE (!)
		 * eu vou lan�ar uma nova excess�o
		 */
		if(!posicaoExiste(linha, coluna)) {
			throw new ExcecaoTabuleiro("Posicao n�o existe no tabuleiro");
		}
		
		
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
		/*
		 * Programa��o defensiva:
		 * SE essa POSI��O n�o EXISTE (!)
		 * eu vou lan�ar uma excess�o
		 */
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Essa posi��o n�o existe no tabuleiro");
		}
		
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		/*
		 * Programa��o defensiva:
		 * Antes de colocar uma pe�a na posi��o, 
		 * eu tenho que verificar se j� existe uma
		 * pe�a na posi��o.
		 */
		if(temUmaPecaNaPosicao(posicao)) {
			throw new ExcecaoTabuleiro("J� existe uma pe�a nessa posi��o do tabuleiro " +posicao);
		}
		
		
		
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
	
	/*
	 * Criando um metodo auxiliar para posicaoExiste
	 * Por que estou fazendo isso?
	 * Por que aqui dentro da classe vai ter um momento
	 * que vai ser mais f�cil testar pela linha e coluna
	 * do que pela posi��o.
	 */
	private boolean posicaoExiste(int linha, int coluna) {
		/*
		 * Quando uma posi��o existe?
		 * Quando essa linha ou coluna estiver no tabuleiro
		 * Ou seja quando a linha for maior que ZERO
		 * e quando a linha for menor que as linhas existentes
		 */
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
		
	}
	
	
	
	public boolean posicaoExiste(Posicao posicao) {
		/*
		 * E aqui eu fa�o do mesmo jeito
		 * aproveitando o metodo auxiliar
		 */
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean temUmaPecaNaPosicao(Posicao posicao) {
		/*
		 * Antes de testar se tem uma peca na posi��o
		 * � importante verificar se essa posi��o existe
		 */
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Essa posi��o n�o existe no tabuleiro");
		}
		/*
		 * Lembrando que essa peca(posicao) � um metodo
		 * que contem pecas que est�o na posi��o
		 * do getLinha e posicao getColuna
		 * Est�o se essa peca for diferente de nulo
		 * tem uma peca nessa posi��o
		 */
		return peca(posicao) != null;
	}

	
}
