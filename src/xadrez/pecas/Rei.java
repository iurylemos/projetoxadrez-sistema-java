package xadrez.pecas;

import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
	
	/*
	 * Implementando o roque
	 * uma depedencia para a partida
	 */
	private PartidaXadrez partidaXadrez;

	/*
	 * E estou botando mais um argumento no construtor
	 */
	public Rei(Tabuleiro tabuleiro, Color color, PartidaXadrez partidaXadrez) {
		super(tabuleiro, color);
		this.partidaXadrez = partidaXadrez;
	}
	
	/*
	 * Vou criar um metodo auxiliar para ajudar
	 * no roque
	 */
	
	private boolean testeTorreRoque(Posicao posicao) {
		/*
		 * A iten��o desse metodo � testar
		 * se nessa posi��o que eu informar
		 * existe torre, se essa torre
		 * est� apta para o roque
		 * 
		 * Vou declarar uma variavel p do tipo PecaXadrez
		 * recebendo a Peca Xadrez do tabuleiro na peca
		 * da posi��o informada
		 * 
		 * Ou seja peguei a pe�a que est� nessa posi��o aqui
		 * 
		 */
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		/*
		 * Vou testar se essa pe�a � diferente de nula
		 * se essa pe�a � uma torre 
		 * E se a cor dessa torre � igual a cor do meu rei
		 * e se a contagem dos movimentos � igual a ZERO
		 */
		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getContagemMovimentos() == 0;
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
		 * Vou pegar a posi��o p, e definir para essa p
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
		
		/*****************************************/
		// #Jogada Especial de Roque
		/*
		 * Se a contagem de movimentos do rei � igual a ZERO
		 * E se a partida n�o est� em cheque para o meu rei
		 */
		if(getContagemMovimentos() == 0 && !partidaXadrez.getCheck()) {
			// #MovimentoEspecial RoquePequeno Rei e a Torre
			/*
			 * Vou primeiro pegar a posi��o da Torre 1
			 * Que � a do canto direito
			 * E j� que a torre fica a 3 posi��es do lado direito do rei
			 * vou passar pro construtor da posi��o 
			 * a posi��o e a coluna do rei
			 * s� que a coluna coloco mais 3, pois ai � a torre.
			 */
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			/*
			 * Vou testar se nessa posi��o tem uma torre l�
			 * que est� disponivel para o roque
			 */
			if (testeTorreRoque(posT1)) {
				/*
				 * Testar se as duas casas est�o vazias
				 * Vou criar um objeto p1, para verificar
				 * se a casa do lado direito est� dispon�vel
				 */
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() +2);
				/*
				 * Peguei as posi��es e vou testar
				 * SE no TABULEIRO a PECA p1 e p2
				 * for igual a nulo, ou seja n�o tem pe�a l�
				 */
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					/*
					 * Se isso ocorrer, � por que posso fazer o rock
					 * Vou criar a matriz e passar o rei para l�.
					 */
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
		}
		
		
		/*****************************************/
		// #Jogada Especial de Roque Grande
		/*
		 * Se a contagem de movimentos do rei � igual a ZERO
		 * E se a partida n�o est� em cheque para o meu rei
		 */
		if(getContagemMovimentos() == 0 && !partidaXadrez.getCheck()) {
			// #MovimentoEspecial RoquePequeno Rei e a Torre
			/*
			 * Vou primeiro pegar a posi��o da Torre 1
			 * Que � a do canto direito
			 * E j� que a torre fica a 3 posi��es do lado direito do rei
			 * vou passar pro construtor da posi��o 
			 * a posi��o e a coluna do rei
			 * s� que a coluna coloco mais 3, pois ai � a torre.
			 */
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			/*
			 * Vou testar se nessa posi��o tem uma torre l�
			 * que est� disponivel para o roque
			 */
			if (testeTorreRoque(posT1)) {
				/*
				 * Testar se as duas casas est�o vazias
				 * Vou criar um objeto p1, para verificar
				 * se a casa do lado direito est� dispon�vel
				 */
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() -2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() -3);
				/*
				 * Peguei as posi��es e vou testar
				 * SE no TABULEIRO a PECA p1 e p2
				 * for igual a nulo, ou seja n�o tem pe�a l�
				 */
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					/*
					 * Se isso ocorrer, � por que posso fazer o rock
					 * Vou criar a matriz e passar o rei para l�.
					 */
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		
		return mat;
	}
}
