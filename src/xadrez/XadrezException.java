package xadrez;

import camada.tabuleiro.ExcecaoTabuleiro;

/*
 * Vou trocar o RuntimeException
 * por uma ExcecaoTabuleiro
 * para ficar mais simples do meu programa tratar
 * pois basta eu capturar o XadrezException
 * Que eu vou capturar também o ExcecaoTabuleiro
 */
public class XadrezException extends ExcecaoTabuleiro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public XadrezException(String msg) {
		super(msg);
	}
}
