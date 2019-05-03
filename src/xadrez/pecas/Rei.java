package xadrez.pecas;

import camada.tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possivelMovimento() {
		/*
		 * Criando uma variavel temporária que é uma matriz
		 * ou seja criei uma matriz de booleanos
		 * da mesma dimensão do TABULEIRO
		 * por padrão sabemos que todos elementos dessa matriz
		 * começa por falso.
		 * 
		 * Por enquanto vou simplemente retornar a matriz.
		 * Então sempre que eu chamar o possivelMovimento
		 * desse REI, vai retornar para mim uma matriz
		 * com todas posições valendo falso.
		 * É como se esse rei tivesse preso.
		 */
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
