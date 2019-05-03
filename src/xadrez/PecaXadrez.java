package xadrez;

import camada.tabuleiro.Peca;
import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
/*
 * Eu n�o posso implementar 
 * o metodo abstrato da classe Peca
 * Pois a PecaXadrez � uma classe muito generica
 * Eu n�o posso definir um movimento espec�fico 
 * para uma pecaGenerica
 * 
 * S� vou ter condi��es de falar qual o movimento
 * espec�fico de uma peca dentro da classe dela.
 * EX: Rei, Torre, Cavalo, Bispo.
 * 
 * E para que eu possa herdar sem utilizar o metodo dela
 * basta eu colocar que ela � abstrata tbm
 */
public abstract class PecaXadrez extends Peca {
	
	private Color color;

	public PecaXadrez(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	/*
	 * Esse metodo vai ficar aqui
	 * pois vai ser reaproveitado por todas as pecas do xadrez
	 */
	
	protected boolean existePecaAdversaria(Posicao posicao) {
		/*
		 * Vou criar uma variavel do tipo PecaXadrez
		 * recebendo a peca que tiver na posi��o do parametro
		 * ou seja peguei a pe�a que est� nessa posi��o.
		 */
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		/*
		 * agora vou testar se p � diferente de nulo
		 * e se p.getColor � diferente da cor da minha pe�a onde eu estou
		 * ou seja da posi��o.
		 * Em outra palavras estou testando
		 * se cor da pe�a dessa posi��o que recebi por parametro
		 * � diferente da cor da minha pe�a
		 * Ou seja uma pe�a advers�ria
		 * 
		 */
		return p != null && p.getColor() != color;
	}
	
	

}
