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
		 * Programação defensiva
		 * Exemplo não faz sentindo as minhas linhas
		 * e colunas serem menor do que 1
		 * estão se forem, os dados são invalidos
		 */
		if(linhas < 1 || colunas < 1) {
			throw new ExcecaoTabuleiro("Erro criando tabuleiro: é necessário que haja pelo menos 1 linha e 1 coluna. ");
		}
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
	/*
	 * Retirei o set de linhas e colunas
	 * pois não tem sentindo, uma vez criado o tabuleiro
	 * ser realizado a mudança no tamanho.
	 */
	public int getColunas() {
		return colunas;
	}

	
	public Peca peca(int linha, int coluna) {
		/*
		 * Programação defensiva:
		 * SE essa POSIÇÃO não EXISTE (!)
		 * eu vou lançar uma nova excessão
		 */
		if(!posicaoExiste(linha, coluna)) {
			throw new ExcecaoTabuleiro("Posicao não existe no tabuleiro");
		}
		
		
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
		/*
		 * Programação defensiva:
		 * SE essa POSIÇÃO não EXISTE (!)
		 * eu vou lançar uma excessão
		 */
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Essa posição não existe no tabuleiro");
		}
		
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		/*
		 * Programação defensiva:
		 * Antes de colocar uma peça na posição, 
		 * eu tenho que verificar se já existe uma
		 * peça na posição.
		 */
		if(temUmaPecaNaPosicao(posicao)) {
			throw new ExcecaoTabuleiro("Já existe uma peça nessa posição do tabuleiro " +posicao);
		}
		
		
		
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
	
	/*
	 * Criando um metodo auxiliar para posicaoExiste
	 * Por que estou fazendo isso?
	 * Por que aqui dentro da classe vai ter um momento
	 * que vai ser mais fácil testar pela linha e coluna
	 * do que pela posição.
	 */
	private boolean posicaoExiste(int linha, int coluna) {
		/*
		 * Quando uma posição existe?
		 * Quando essa linha ou coluna estiver no tabuleiro
		 * Ou seja quando a linha for maior que ZERO
		 * e quando a linha for menor que as linhas existentes
		 */
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
		
	}
	
	
	
	public boolean posicaoExiste(Posicao posicao) {
		/*
		 * E aqui eu faço do mesmo jeito
		 * aproveitando o metodo auxiliar
		 */
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean temUmaPecaNaPosicao(Posicao posicao) {
		/*
		 * Antes de testar se tem uma peca na posição
		 * é importante verificar se essa posição existe
		 */
		if(!posicaoExiste(posicao)) {
			throw new ExcecaoTabuleiro("Essa posição não existe no tabuleiro");
		}
		/*
		 * Lembrando que essa peca(posicao) é um metodo
		 * que contem pecas que estão na posição
		 * do getLinha e posicao getColuna
		 * Estão se essa peca for diferente de nulo
		 * tem uma peca nessa posição
		 */
		return peca(posicao) != null;
	}

	
}
