package xadrez.pecas;

import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}


	@Override
	public boolean[][] possivelMovimento() {
boolean [][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		/*
		 *  Testar as posi��es livres dessa torre
		 *  Primeiro vou instanciar a posi��o
		 *  e botar 0, s� para iniciar.
		 */
		Posicao p = new Posicao(0,0);
		
		/*
		 * Verificar acima da minha pe�a
		 * Essa posicao que est� no parametro
		 * � a posi��o da pr�pria pe�a da classe PECA
		 * 
		 * posicao.getLinha() - 1 = LINHA ACIMA DA POSICAO
		 */
		
		//Acima
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			/*
			 * Enquanto a posi��o P existir e n�o tiver uma pe�a l�
			 * ou seja enquanto essa posi��o estiver vaga
			 * Eu vou marcar como verdadeira a posi��o da matriz
			 * e mover minha pe�a para l�!
			 * 
			 */
			mat[p.getLinha()][p.getColuna()] = true;
			/*
			 * Agora eu vou fazer a linha dessa posi��o
			 * andar mais uma para cima
			 * ou seja -1 ainda
			 */
			p.setLinha(p.getLinha() - 1);
		}
		/*
		 * Vou testar se existe uma casa e se tiver 
		 * uma pe�a adves�ria vou marca-l� como verdadeira
		 * ou seja come-la
		 */
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Aqui fecha a l�gica para marcar as pe�as acima de verdadeira
		
		/* *******************************************
		 * Movimentar para a esquerda
		 * No lugar de a linha andar
		 * quem vai andar � a coluna
		 */
		//Esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			//Agora � a coluna que vai andar
			p.setColuna(p.getColuna() - 1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Direita
		p.setValor(posicao.getLinha(), posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			//Agora � a coluna que vai andar
			p.setColuna(p.getColuna() + 1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Baixo
		p.setValor(posicao.getLinha() +1, posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			//Agora � a coluna que vai andar
			p.setLinha(p.getLinha() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		/******************************************/
		/******************************************/
		/************    RAINHA   *****************/
		/******************************************/
		/******************************************/
		/*
		 * J� tem todas l�gicas da torre
		 * Agora vou copiar a l�gica do bispo
		 * para pecorrer pelas laterais
		 */

		//Noreste ou seja no sentindo esquerdo a CIMA
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			/*
			 * Enquanto a posi��o P existir e n�o tiver uma pe�a l�
			 * ou seja enquanto essa posi��o estiver vaga
			 * Eu vou marcar como verdadeira a posi��o da matriz
			 * e mover minha pe�a para l�!
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
			//Agora � a coluna que vai andar
			p.setValor(p.getLinha() +1, p.getColuna() -1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		
		return mat;
	}
	
	
	public String toString() {
		return "R";
	}
	
}
