package xadrez.pecas;

import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}
	
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
		 * Copiei todo o c�digo do Rei
		 * E modifiquei s�mente a posi��o
		 * de linhas e colunas sempre 2 e 1
		 * pois o cavalo se movimenta duas casas 
		 * e depois vai para o lado direito ou esquerdo
		 */
	
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		/*
		 * Vou criar novamente uma posi��o auxiliar
		 * vou come�ar com a posi��o 0 0
		 */
		
		Posicao p = new Posicao(0,0);

		
		/********************************************/
		p.setValor(posicao.getLinha() -1, posicao.getColuna() -2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/****************************************/
		p.setValor(posicao.getLinha() -2, posicao.getColuna() -1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/*****************************************/
		p.setValor(posicao.getLinha() -2, posicao.getColuna() +1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/****************************************/
		p.setValor(posicao.getLinha() -1, posicao.getColuna() +2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		/****************************************/
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() +2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/******************************************/
		p.setValor(posicao.getLinha() +2, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/**************************************/
		p.setValor(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/****************************************/
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
	
	public String toString() {
		return "C";
	}

}
