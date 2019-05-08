package xadrez.pecas;

import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {
	
	public String toString() {
		return "P";
	}
	

	public Peao(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possivelMovimento() {
		/*
		 * Copiei essa parte abaixo da classe Torre
		 */
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		if(getColor() == Color.WHITE) {
			/*Se for igual a Color.WHITE
			 * Estou trabalhando com o pe�o branco
			 * Quando eu ando uma casa, eu coloco uma posi��o acima
			 * 
			 */
			p.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
				/*
				 * Se a posi��o existe, e se n�o tem uma pe�a na posi��o
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/*******************************************/
			/*
			 * Para ele pular duas casa no inicio da jogada
			 */
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			/*
			 * Um pequeno detalhe, para verificar se a casa da frente
			 * est� desoculpada ou n�o.
			 * 
			 * Criei um outro objeto p2
			 * para utilizar no caso de ter uma pe�a 
			 * na primeira casa, e assim ele n�o iria conseguir
			 * fazer os dois pulo do pe�o
			 */
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPecaNaPosicao(p2) && getContagemMovimentos() == 0) {
				/*
				 * Se a posi��o existe, e se n�o tem uma pe�a na posi��o
				 * e se a posi��o p2 existe, e se n�o tem uma pe�a nessa posi��o
				 * e se contagem movimentos desse pe�o for igual a ZERO
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/*
			 * Com esse linha -1, e coluna -1
			 * estou verificando a casa de cima
			 * do lado esquerdo
			 * para capturar a pe�a com o pe�o
			 */
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() -1);
			if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				/*
				 * Se a posi��o existe, e se n�o tem uma pe�a adves�ria na posi��o
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/*
			 * Com esse linha -1, e coluna +1
			 * estou verificando a casa de cima
			 * do lado direito
			 * para capturar a pe�a com o pe�o
			 */
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() +1);
			if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				/*
				 * Se a posi��o existe, e se n�o tem uma pe�a adves�ria na posi��o
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/******************** PE�O NEGRO ******************************************/
			/**************************************************************/
			
		}else {
			/*
			 * O if foi das pe�as da cor branca
			 * agora vou criar das negras
			 * 
			 * Copiei o mesmo c�digo das pe�as brancas
			 * a modifica��o ficou trocando o -1 por mais 1
			 * nas linhas
			 * pois as pe�as negras andam para baixo
			 * ent�o � linha MAIS 1
			 * 
			 */
			/*Se for igual a Color.BLACK
			 * Estou trabalhando com o pe�o preto
			 * Quando eu ando uma casa, eu coloco uma posi��o acima
			 */
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
				/*
				 * Se a posi��o existe, e se n�o tem uma pe�a na posi��o
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/*******************************************/
			/*
			 * Para ele pular duas casa no inicio da jogada
			 */
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			/*
			 * Um pequeno detalhe, para verificar se a casa da frente
			 * est� desoculpada ou n�o.
			 * 
			 * Criei um outro objeto p2
			 * para utilizar no caso de ter uma pe�a 
			 * na primeira casa, e assim ele n�o iria conseguir
			 * fazer os dois pulo do pe�o
			 */
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPecaNaPosicao(p2) && getContagemMovimentos() == 0) {
				/*
				 * Se a posi��o existe, e se n�o tem uma pe�a na posi��o
				 * e se a posi��o p2 existe, e se n�o tem uma pe�a nessa posi��o
				 * e se contagem movimentos desse pe�o for igual a ZERO
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/*
			 * Com esse linha -1, e coluna -1
			 * estou verificando a casa de cima
			 * do lado esquerdo
			 * para capturar a pe�a com o pe�o
			 */
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() -1);
			if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				/*
				 * Se a posi��o existe, e se n�o tem uma pe�a adves�ria na posi��o
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/*
			 * Com esse linha -1, e coluna +1
			 * estou verificando a casa de cima
			 * do lado direito
			 * para capturar a pe�a com o pe�o
			 */
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() +1);
			if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				/*
				 * Se a posi��o existe, e se n�o tem uma pe�a adves�ria na posi��o
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		
		
		
		
		return mat;
	}
	
}
