package xadrez.pecas;

import camada.tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}
	
@Override
public String toString() {
	/*
	 * Convertendo uma torre para String
	 * convertir pois vai aparecer no TABULEIRO apenas
	 * a INICIAL
	 */
	return "T";
}

	@Override
	public boolean[][] possivelMovimento() {
		// TODO Auto-generated method stub
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
