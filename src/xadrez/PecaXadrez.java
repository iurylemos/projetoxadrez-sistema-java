package xadrez;

import camada.tabuleiro.Peca;
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
	
	
	
	

}
