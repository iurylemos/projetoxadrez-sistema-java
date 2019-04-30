package xadrez;

import camada.tabuleiro.Peca;
import camada.tabuleiro.Tabuleiro;

public class PecaXadrez extends Peca {
	private Color color;

	public PecaXadrez(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	
	
	

}
