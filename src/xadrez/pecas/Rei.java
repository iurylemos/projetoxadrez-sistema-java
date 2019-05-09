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
		 * A itenção desse metodo é testar
		 * se nessa posição que eu informar
		 * existe torre, se essa torre
		 * está apta para o roque
		 * 
		 * Vou declarar uma variavel p do tipo PecaXadrez
		 * recebendo a Peca Xadrez do tabuleiro na peca
		 * da posição informada
		 * 
		 * Ou seja peguei a peça que está nessa posição aqui
		 * 
		 */
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		/*
		 * Vou testar se essa peça é diferente de nula
		 * se essa peça é uma torre 
		 * E se a cor dessa torre é igual a cor do meu rei
		 * e se a contagem dos movimentos é igual a ZERO
		 */
		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getContagemMovimentos() == 0;
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
		 * Vou pegar a posição p, e definir para essa p
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
		
		/*****************************************/
		// #Jogada Especial de Roque
		/*
		 * Se a contagem de movimentos do rei é igual a ZERO
		 * E se a partida não está em cheque para o meu rei
		 */
		if(getContagemMovimentos() == 0 && !partidaXadrez.getCheck()) {
			// #MovimentoEspecial RoquePequeno Rei e a Torre
			/*
			 * Vou primeiro pegar a posição da Torre 1
			 * Que é a do canto direito
			 * E já que a torre fica a 3 posições do lado direito do rei
			 * vou passar pro construtor da posição 
			 * a posição e a coluna do rei
			 * só que a coluna coloco mais 3, pois ai é a torre.
			 */
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			/*
			 * Vou testar se nessa posição tem uma torre lá
			 * que está disponivel para o roque
			 */
			if (testeTorreRoque(posT1)) {
				/*
				 * Testar se as duas casas estão vazias
				 * Vou criar um objeto p1, para verificar
				 * se a casa do lado direito está disponível
				 */
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() +2);
				/*
				 * Peguei as posições e vou testar
				 * SE no TABULEIRO a PECA p1 e p2
				 * for igual a nulo, ou seja não tem peça lá
				 */
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					/*
					 * Se isso ocorrer, é por que posso fazer o rock
					 * Vou criar a matriz e passar o rei para lá.
					 */
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
		}
		
		
		/*****************************************/
		// #Jogada Especial de Roque Grande
		/*
		 * Se a contagem de movimentos do rei é igual a ZERO
		 * E se a partida não está em cheque para o meu rei
		 */
		if(getContagemMovimentos() == 0 && !partidaXadrez.getCheck()) {
			// #MovimentoEspecial RoquePequeno Rei e a Torre
			/*
			 * Vou primeiro pegar a posição da Torre 1
			 * Que é a do canto direito
			 * E já que a torre fica a 3 posições do lado direito do rei
			 * vou passar pro construtor da posição 
			 * a posição e a coluna do rei
			 * só que a coluna coloco mais 3, pois ai é a torre.
			 */
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			/*
			 * Vou testar se nessa posição tem uma torre lá
			 * que está disponivel para o roque
			 */
			if (testeTorreRoque(posT1)) {
				/*
				 * Testar se as duas casas estão vazias
				 * Vou criar um objeto p1, para verificar
				 * se a casa do lado direito está disponível
				 */
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() -2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() -3);
				/*
				 * Peguei as posições e vou testar
				 * SE no TABULEIRO a PECA p1 e p2
				 * for igual a nulo, ou seja não tem peça lá
				 */
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					/*
					 * Se isso ocorrer, é por que posso fazer o rock
					 * Vou criar a matriz e passar o rei para lá.
					 */
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		
		return mat;
	}
}
