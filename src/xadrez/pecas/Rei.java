package xadrez.pecas;

import camada.tabuleiro.Posicao;
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
	
	/*
	 * Vou criar um metodo auxiliar do possivelMovimento
	 * chamado podeMover recebendo a posição
	 * esse metodo vai retornar para mim 
	 * se essa peça pode mover para uma determinada
	 *  posiçaõ.
	 */
	private boolean podeMover(Posicao posicao) {
		/*
		 * EM 1º lugar vou pegar a peça p que estiver nessa posição
		 * então vou chamar o PecaXadrez p, contendo
		 * um casting de Peca Xadrez, do tabuleiro, na peca
		 * nessa posição que veio no parametro
		 */
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		/*
		 * Verificar se essa peça é igual nula
		 * ou se é uma peça adversária
		 * 
		 * ou se p.getColor() que é a cor de peça for
		 *  DIFERENTE da COR desse REI
		 *  OU A CASA TÁ VAZIA == NULL
		 *  OU SE TEM UMA PEÇA ADVESÁRIA
		 */
		return p == null || p.getColor() != getColor();
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
		/*
		 * Vou criar novamente uma posição auxiliar
		 * vou começar com a posição 0 0
		 */
		
		Posicao p = new Posicao(0,0);
		/*
		 * Agora vou ter que testar as cada uma das 8
		 * direções possíveis que o rei pode se mover
		 */
		
		/********************************************/
		//ACIMA
		/*
		 * Vou pegar a posição p, e definir para essa posição
		 * os valores da posição acima do rei
		 * que seria no primeiro parametro
		 * a posição do REI - 1 =
		 * posicao.getLinha() - 1
		 * 
		 * lembrando a variavel auxiliar
		 * vai receber da variavel posição que contém a posição
		 * que o usuário digitou.
		 * 
		 */
		p.setValor(posicao.getLinha() -1, posicao.getColuna());
		/* Vou testar
		 * Se essa posição existe, e se pode mover
		 * Significa então que o meu rei pode ir para essa posição P */
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/****************************************/
		//ABAIXO
		p.setValor(posicao.getLinha() +1, posicao.getColuna());
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/*****************************************/
		//ESQUERDA
		p.setValor(posicao.getLinha(), posicao.getColuna() -1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/****************************************/
		//DIREITA
		p.setValor(posicao.getLinha(), posicao.getColuna() +1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		/****************************************/
		//NOROESTE <- ^- Esquerda e Acima
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() -1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/******************************************/
		//NORDESTE -> ^- Direita e Acima
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/**************************************/
		//SULDOOESTE -> ^- Esquerda e Abaixo
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/****************************************/
		//SUDESTE -> ^- Direita e abaixo
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}
