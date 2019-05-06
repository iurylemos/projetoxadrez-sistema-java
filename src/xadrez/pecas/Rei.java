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
	 * chamado podeMover recebendo a posi��o
	 * esse metodo vai retornar para mim 
	 * se essa pe�a pode mover para uma determinada
	 *  posi�a�.
	 */
	private boolean podeMover(Posicao posicao) {
		/*
		 * EM 1� lugar vou pegar a pe�a p que estiver nessa posi��o
		 * ent�o vou chamar o PecaXadrez p, contendo
		 * um casting de Peca Xadrez, do tabuleiro, na peca
		 * nessa posi��o que veio no parametro
		 */
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		/*
		 * Verificar se essa pe�a � igual nula
		 * ou se � uma pe�a advers�ria
		 * 
		 * ou se p.getColor() que � a cor de pe�a for
		 *  DIFERENTE da COR desse REI
		 *  OU A CASA T� VAZIA == NULL
		 *  OU SE TEM UMA PE�A ADVES�RIA
		 */
		return p == null || p.getColor() != getColor();
	}
	

	@Override
	public boolean[][] possivelMovimento() {
		/*
		 * Criando uma variavel tempor�ria que � uma matriz
		 * ou seja criei uma matriz de booleanos
		 * da mesma dimens�o do TABULEIRO
		 * por padr�o sabemos que todos elementos dessa matriz
		 * come�a por falso.
		 * 
		 * Por enquanto vou simplemente retornar a matriz.
		 * Ent�o sempre que eu chamar o possivelMovimento
		 * desse REI, vai retornar para mim uma matriz
		 * com todas posi��es valendo falso.
		 * � como se esse rei tivesse preso.
		 */
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		/*
		 * Vou criar novamente uma posi��o auxiliar
		 * vou come�ar com a posi��o 0 0
		 */
		
		Posicao p = new Posicao(0,0);
		/*
		 * Agora vou ter que testar as cada uma das 8
		 * dire��es poss�veis que o rei pode se mover
		 */
		
		/********************************************/
		//ACIMA
		/*
		 * Vou pegar a posi��o p, e definir para essa posi��o
		 * os valores da posi��o acima do rei
		 * que seria no primeiro parametro
		 * a posi��o do REI - 1 =
		 * posicao.getLinha() - 1
		 * 
		 * lembrando a variavel auxiliar
		 * vai receber da variavel posi��o que cont�m a posi��o
		 * que o usu�rio digitou.
		 * 
		 */
		p.setValor(posicao.getLinha() -1, posicao.getColuna());
		/* Vou testar
		 * Se essa posi��o existe, e se pode mover
		 * Significa ent�o que o meu rei pode ir para essa posi��o P */
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
