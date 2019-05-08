package xadrez;

import camada.tabuleiro.Peca;
import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
/*
 * Eu não posso implementar 
 * o metodo abstrato da classe Peca
 * Pois a PecaXadrez é uma classe muito generica
 * Eu não posso definir um movimento específico 
 * para uma pecaGenerica
 * 
 * Só vou ter condições de falar qual o movimento
 * específico de uma peca dentro da classe dela.
 * EX: Rei, Torre, Cavalo, Bispo.
 * 
 * E para que eu possa herdar sem utilizar o metodo dela
 * basta eu colocar que ela é abstrata tbm
 */
public abstract class PecaXadrez extends Peca {
	
	private Color color;
	private int contagemMovimentos;

	public PecaXadrez(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}
	/*
	 * Implementando a lógica do cheque
	 */
	public XadrezPosicao getPosicaoXadrez() {
		/*
		 * Vou ter que pegar o posição que foi herdada
		 * lá da classe Peca e eu vou ter que converter
		 * para a XadrezPosicao
		 * 
		 * E para fazer isso vou utilizar o metodo
		 * stático fromPosition = da posição
		 * E esse metodo pega uma posição de matriz
		 * e converte para a posição Xadrez
		 */
		return XadrezPosicao.fromPosicao(posicao);
	}
	

	public Color getColor() {
		return color;
	}
	
	public int getContagemMovimentos() {
		return contagemMovimentos;
	}	
	
	public void aumentarContagemMovimentos() {
		contagemMovimentos++;
	}
	
	public void diminuirContagemMovimentos() {
		contagemMovimentos--;
	}
	
	
	
	/*
	 * Esse metodo vai ficar aqui
	 * pois vai ser reaproveitado por todas as pecas do xadrez
	 */
	
	protected boolean existePecaAdversaria(Posicao posicao) {
		/*
		 * Vou criar uma variavel do tipo PecaXadrez
		 * recebendo a peca que tiver na posição do parametro
		 * ou seja peguei a peça que está nessa posição.
		 */
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		/*
		 * agora vou testar se p é diferente de nulo
		 * e se p.getColor é diferente da cor da minha peça onde eu estou
		 * ou seja da posição.
		 * Em outra palavras estou testando
		 * se cor da peça dessa posição que recebi por parametro
		 * é diferente da cor da minha peça
		 * Ou seja uma peça adversária
		 * 
		 */
		return p != null && p.getColor() != color;
	}
	
	

}
