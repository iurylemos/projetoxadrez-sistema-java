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
			 * Estou trabalhando com o peão branco
			 * Quando eu ando uma casa, eu coloco uma posição acima
			 * 
			 */
			p.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
				/*
				 * Se a posição existe, e se não tem uma peça na posição
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
			 * está desoculpada ou não.
			 * 
			 * Criei um outro objeto p2
			 * para utilizar no caso de ter uma peça 
			 * na primeira casa, e assim ele não iria conseguir
			 * fazer os dois pulo do peão
			 */
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPecaNaPosicao(p2) && getContagemMovimentos() == 0) {
				/*
				 * Se a posição existe, e se não tem uma peça na posição
				 * e se a posição p2 existe, e se não tem uma peça nessa posição
				 * e se contagem movimentos desse peão for igual a ZERO
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/*
			 * Com esse linha -1, e coluna -1
			 * estou verificando a casa de cima
			 * do lado esquerdo
			 * para capturar a peça com o peão
			 */
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() -1);
			if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				/*
				 * Se a posição existe, e se não tem uma peça advesária na posição
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/*
			 * Com esse linha -1, e coluna +1
			 * estou verificando a casa de cima
			 * do lado direito
			 * para capturar a peça com o peão
			 */
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() +1);
			if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				/*
				 * Se a posição existe, e se não tem uma peça advesária na posição
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/******************** PEÃO NEGRO ******************************************/
			/**************************************************************/
			
		}else {
			/*
			 * O if foi das peças da cor branca
			 * agora vou criar das negras
			 * 
			 * Copiei o mesmo código das peças brancas
			 * a modificação ficou trocando o -1 por mais 1
			 * nas linhas
			 * pois as peças negras andam para baixo
			 * então é linha MAIS 1
			 * 
			 */
			/*Se for igual a Color.BLACK
			 * Estou trabalhando com o peão preto
			 * Quando eu ando uma casa, eu coloco uma posição acima
			 */
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
				/*
				 * Se a posição existe, e se não tem uma peça na posição
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
			 * está desoculpada ou não.
			 * 
			 * Criei um outro objeto p2
			 * para utilizar no caso de ter uma peça 
			 * na primeira casa, e assim ele não iria conseguir
			 * fazer os dois pulo do peão
			 */
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPecaNaPosicao(p2) && getContagemMovimentos() == 0) {
				/*
				 * Se a posição existe, e se não tem uma peça na posição
				 * e se a posição p2 existe, e se não tem uma peça nessa posição
				 * e se contagem movimentos desse peão for igual a ZERO
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/*
			 * Com esse linha -1, e coluna -1
			 * estou verificando a casa de cima
			 * do lado esquerdo
			 * para capturar a peça com o peão
			 */
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() -1);
			if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				/*
				 * Se a posição existe, e se não tem uma peça advesária na posição
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
			/**************************************************************/
			/*
			 * Com esse linha -1, e coluna +1
			 * estou verificando a casa de cima
			 * do lado direito
			 * para capturar a peça com o peão
			 */
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() +1);
			if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
				/*
				 * Se a posição existe, e se não tem uma peça advesária na posição
				 * Significa que eu posso ir para essa casa
				 */
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		
		
		
		
		return mat;
	}
	
}
