package xadrez;

import camada.tabuleiro.Posicao;

public class XadrezPosicao {
	private char coluna;
	private int linha;
	
	public XadrezPosicao(char coluna, int linha) {
	/*
	 * Programa��o defensiva.
	 * Se coluna for menor que A, ou se a coluna for menor que H
	 * ou se a linha for menor que 1, ou se a linha for menor que 8
	 * Se alguma dessas condi��es cair aqui
	 * eu n�o vou aceitar a instancia��o da minha XadrezPosicao
	 */
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8 ) {
			throw new XadrezException("Erro na instancia��o do XadrezPosicao: Os valores v�lidos s�o de a1 at� h8");
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
	 * Para uma posi��o normal.
	 * 
	 * Como que vou fazer isso?
	 * Exemplo, olhando pro desenho
	 * quem seria a posi��o A8?
	 * seria o 0 0 da matriz
	 * Quem seria o A7?
	 * seria o 1 0 da matriz.
	 * Para achar a linha da matriz
	 * � s� voc� pegar o 8 e subtrair
	 * do valor da linha aqui na posi��o do XADREZ
	 * EXEMPLO
	 * a linha 8 no xadrez
	 * corresponde a 8 - 8 que � 0 na matriz.
	 * a linha 8 no xadrez
	 * corresponde a 8 0 7 que � 1 na matriz.
	 * 
	 * Vale lembrar que:
	 * 
	 * LINHA da MATRIZ = matriz_linha ela � 8 menos a linha do XADREZ.
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
	 * se voc� substrair o 'a' por ele mesmo vai dar 0.
	 * Por que � o mesmo c�digo
	 * Mas se voc� pegar o c�digo unicode do caractere b
	 * e subtrair pelo 'a' vai dar 1.
	 * 'a' - 'a' = 0
	 * 'b' - 'a' = 1
	 * 'c' - 'a' = 2
	 * 
	 * e a formula da coluna � assim:
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
		 * uma nova posi��o.
		 * a linha dessa classe aqui posi��o que � 8
		 * menos a linha do xadrez.
		 */
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	/*
	 * Implementar a posi��o inversa
	 * Para converter a posi��o para uma posi��o de xadrez.
	 * STATIC = SUBLINHADA.
	 */
	protected static XadrezPosicao fromPosicao(Posicao posicao) {
		/*
		 * Esse metodo vai ter que retornar
		 * Vai ter que retornar a formula inversa aqui.
		 * Tenho que utilizar o casting
		 * para converter para char
		 * PRIMEIRA CONDI��O:
		 * a linha vai ser o caractere 'a'
		 * menos a coluna da classe Posi��o normal
		 * = COLUNA do XADREZ.
		 * SEGUNDA CONDI��O:
		 * 'a' = coluna 
		 * menos a linha da classe Posi��o
		 * = LINHA do XADREZ.
		 * 
		 */
		return new XadrezPosicao((char)('a' - posicao.getColuna()), 8 - posicao.getLinha() );
	}
	
	/*
	 * toString da posi��o do XADREZ
	 */
	@Override
	public String toString() {
		return "" + coluna + linha;
		/*
		 * Estou concatenando, pois vai ficar A1, A2.
		 * E para for�ar a retornar uma String,
		 * vou colocar o "".
		 */
	}
	
	
	
}
