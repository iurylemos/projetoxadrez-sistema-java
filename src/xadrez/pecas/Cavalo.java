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
		 * Copiei todo o código do Rei
		 * E modifiquei sómente a posição
		 * de linhas e colunas sempre 2 e 1
		 * pois o cavalo se movimenta duas casas 
		 * e depois vai para o lado direito ou esquerdo
		 */
	
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		/*
		 * Vou criar novamente uma posição auxiliar
		 * vou começar com a posição 0 0
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
