package xadrez;

import camada.tabuleiro.Peca;
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

	public PecaXadrez(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	
	
	

}
