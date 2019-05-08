package xadrez.pecas;

import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

	public Bispo(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public boolean[][] possivelMovimento() {
		/*
		 * Copiei o código da torre para cá
		 */
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		/*
		 *  Testar as posições livres dessa torre
		 *  Primeiro vou instanciar a posição
		 *  e botar 0, só para iniciar.
		 */
		Posicao p = new Posicao(0,0);
		
		/*
		 * Verificar acima da minha peça
		 * Essa posicao que está no parametro
		 * é a posição da própria peça da classe PECA
		 * 
		 * posicao.getLinha() - 1 = LINHA ACIMA DA POSICAO
		 */
		
		//Noreste ou seja no sentindo esquerdo a CIMA
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			/*
			 * Enquanto a posição P existir e não tiver uma peça lá
			 * ou seja enquanto essa posição estiver vaga
			 * Eu vou marcar como verdadeira a posição da matriz
			 * e mover minha peça para lá!
			 * 
			 */
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() -1);
		}

		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/**************************************/
		//Nordeste - Direita e acima no sentindo horizontal
		p.setValor(posicao.getLinha() -1, posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() -1, p.getColuna() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Sudeste
		p.setValor(posicao.getLinha() +1, posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() +1, p.getColuna() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/***************************************/
		//Sudoeste = BAIXO Do lado ESQUERDO
		p.setValor(posicao.getLinha() +1, posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			//Agora é a coluna que vai andar
			p.setValor(p.getLinha() +1, p.getColuna() -1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		return mat;
	}
	
	public String toString() {
		return "B";
	}
	
}
