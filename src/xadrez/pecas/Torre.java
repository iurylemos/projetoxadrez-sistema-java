package xadrez.pecas;

import camada.tabuleiro.Posicao;
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
		
		//Acima
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			/*
			 * Enquanto a posição P existir e não tiver uma peça lá
			 * ou seja enquanto essa posição estiver vaga
			 * Eu vou marcar como verdadeira a posição da matriz
			 * e mover minha peça para lá!
			 * 
			 */
			mat[p.getLinha()][p.getColuna()] = true;
			/*
			 * Agora eu vou fazer a linha dessa posição
			 * andar mais uma para cima
			 * ou seja -1 ainda
			 */
			p.setLinha(p.getLinha() - 1);
		}
		/*
		 * Vou testar se existe uma casa e se tiver 
		 * uma peça advesária vou marca-lá como verdadeira
		 * ou seja come-la
		 */
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Aqui fecha a lógica para marcar as peças acima de verdadeira
		
		/* *******************************************
		 * Movimentar para a esquerda
		 * No lugar de a linha andar
		 * quem vai andar é a coluna
		 */
		//Esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna() -1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			//Agora é a coluna que vai andar
			p.setColuna(p.getColuna() - 1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Direita
		p.setValor(posicao.getLinha(), posicao.getColuna() +1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			//Agora é a coluna que vai andar
			p.setColuna(p.getColuna() + 1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//Baixo
		p.setValor(posicao.getLinha() +1, posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPecaNaPosicao(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			//Agora é a coluna que vai andar
			p.setLinha(p.getLinha() +1);
		}
		
		if(getTabuleiro().posicaoExiste(p) && existePecaAdversaria(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		return mat;
	}
}
