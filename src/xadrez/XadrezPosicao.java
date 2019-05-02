package xadrez;

import camada.tabuleiro.Posicao;

public class XadrezPosicao {
	private char coluna;
	private int linha;
	
	public XadrezPosicao(char coluna, int linha) {
	/*
	 * Programação defensiva.
	 * Se coluna for menor que A, ou se a coluna for menor que H
	 * ou se a linha for menor que 1, ou se a linha for menor que 8
	 * Se alguma dessas condições cair aqui
	 * eu não vou aceitar a instanciação da minha XadrezPosicao
	 */
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8 ) {
			throw new XadrezException("Erro na instanciação do XadrezPosicao: Os valores válidos são de a1 até h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	/*public void setColuna(char coluna) {
		this.coluna = coluna;
	} */

	public int getLinha() {
		return linha;
	}

	/*public void setLinha(int linha) {
		this.linha = linha;
	}*/
	
	/*
	 * Criando um metodo para converter esse XadrezPosicao
	 * Para uma posição normal.
	 * 
	 * Como que vou fazer isso?
	 * Exemplo, olhando pro desenho
	 * quem seria a posição A8?
	 * seria o 0 0 da matriz
	 * Quem seria o A7?
	 * seria o 1 0 da matriz.
	 * Para achar a linha da matriz
	 * É só você pegar o 8 e subtrair
	 * do valor da linha aqui na posição do XADREZ
	 * EXEMPLO
	 * a linha 8 no xadrez
	 * corresponde a 8 - 8 que é 0 na matriz.
	 * a linha 8 no xadrez
	 * corresponde a 8 0 7 que é 1 na matriz.
	 * 
	 * Vale lembrar que:
	 * 
	 * LINHA da MATRIZ = matriz_linha ela é 8 menos a linha do XADREZ.
	 * sintaxe:
	 * matriz_linha = 8 - xadrez_linha
	 * 
	 * COLUNA da MATRIZ = 

	 * a = 0 
	 * b = 1 
	 * c = 2
	 * d = 3.
	 * 
	 * Temos uma forma geral. Por exemplo 
	 * se você substrair o 'a' por ele mesmo vai dar 0.
	 * Por que é o mesmo código
	 * Mas se você pegar o código unicode do caractere b
	 * e subtrair pelo 'a' vai dar 1.
	 * 'a' - 'a' = 0
	 * 'b' - 'a' = 1
	 * 'c' - 'a' = 2
	 * 
	 * e a formula da coluna é assim:
	 *
	 * xadrez_coluna - 'a'
	 * 
	 * 
	 * 
	 * 
	 */
	
	protected Posicao toPosicao() {
		/*
		 * Esse metodo vai me retornar
		 * uma nova posição.
		 * a linha dessa classe aqui posição que é 8
		 * menos a linha do xadrez.
		 */
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	/*
	 * Implementar a posição inversa
	 * Para converter a posição para uma posição de xadrez.
	 * STATIC = SUBLINHADA.
	 */
	protected static XadrezPosicao fromPosicao(Posicao posicao) {
		/*
		 * Esse metodo vai ter que retornar
		 * Vai ter que retornar a formula inversa aqui.
		 * Tenho que utilizar o casting
		 * para converter para char
		 * PRIMEIRA CONDIÇÃO:
		 * a linha vai ser o caractere 'a'
		 * menos a coluna da classe Posição normal
		 * = COLUNA do XADREZ.
		 * SEGUNDA CONDIÇÃO:
		 * 'a' = coluna 
		 * menos a linha da classe Posição
		 * = LINHA do XADREZ.
		 * 
		 */
		return new XadrezPosicao((char)('a' - posicao.getColuna()), 8 - posicao.getLinha() );
	}
	
	/*
	 * toString da posição do XADREZ
	 */
	@Override
	public String toString() {
		return "" + coluna + linha;
		/*
		 * Estou concatenando, pois vai ficar A1, A2.
		 * E para forçar a retornar uma String,
		 * vou colocar o "".
		 */
	}
	
	
	
}
