package aplicacao;

import xadrez.PartidaXadrez;

public class Programa {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		/*
		 * Vou criar uma fun��o para imprimir as pe�as
		 * dessa partida
		 * 
		 * Vou criar uma classe chamada UI = User Interface
		 * E dentro dessa classe vou criar um metodo
		 * chamado printBoard
		 * Esse metodo vai receber a matriz de pe�as
		 * da minha partida.
		 * 
		 */
		UI.imprimaTabuleiro(partidaXadrez.getPecas());

	}

}
