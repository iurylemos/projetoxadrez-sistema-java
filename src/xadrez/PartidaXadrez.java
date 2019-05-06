package xadrez;

import camada.tabuleiro.Peca;
import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	/*
	 * Coração do sistema de Xadrez
	 * Nessa classe vai ter as regras
	 */
	
	private Tabuleiro tabuleiro;
	/*
	 * Esse tabuleiro tem uma matriz de peças
	 * Só que essas peças são do tipo Peca
	 */
	
	public PartidaXadrez () {
		/*
		 * Quem tem que saber a dimensão de um
		 * tabuleiro de xadrez
		 * É a classe PartidaXadrez.
		 * Então quando começar a partida
		 * eu crio um tabuleiro 8,8
		 * e chamo o metodo iniciarPartida()
		 */
		tabuleiro = new Tabuleiro(8, 8);
		iniciarPartida();
	}
	
	public PecaXadrez[][] getPecas() {
		/*
		 * Esse metodo vai ter que retornar uma matriz
		 * de peças de xadrez correspondentes
		 * a essa partida.
		 */
		/*
	 * O que é que eu vou ter que fazer?
	 * as peças dentro do construtor da classe Tabuleiro
	 * ele tem as peças. Se você ver lá na classe Tabuleiro
	 * Ele tem uma matriz de peças e elas são do tipo Peca
	 * mas o meu metodo aqui na classe PartidaXadrez
	 * ele retorna o PecaXadrez
	 * Por que?
	 * Por que estou na camada(PACOTE) de xadrez
	 * Para a minha aplicacao que vai ser a classe
	 * Programa no pacote aplicacao
	 * Eu não quero liberar as pecas do tipo Peca
	 * Mas sim do tipo PecaXadrez
	 * Por que?
	 * Por que estou fazendo um desenvolvimento em camada
	 * Então a principal ele vai poder enchergar somente
	 * a peça de Xadrez.
	 */
		/*
		 * Vou criar temporáriamente uma variável auxiliar
		 * do tipo PecaXadrez, vou chamar ela de mat (MATRIZ DE PEÇAS)
		 * vou iniciar ela instanciando a classe PecaXadrez
		 * quantas linhas? as linhas do tabuleiro
		 * quantas colunas? as colunas do tabuleiro
		 * 
		 */
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		/*
		 * Agora vou pecorrer a matriz de peças do tabuleiro
		 */
		for (int i =0; i < tabuleiro.getLinhas(); i++) {
			for(int j=0; j < tabuleiro.getColunas(); j++) {
				/*
				 * Para cada posição i, j do meu tabuleiro
				 * vou fazer a minha matriz mat receba
				 * o tabuleiro.peca(i,j)
				 * Vou ter que fazer o casting
				 * para a classe PecaXadrez
				 * Por que ai sim ele vai interpretar
				 * como uma peça de XADREZ
				 * e não como peça comum
				 */
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	/*
	 * Movimentos possíveis
	 */
	
	public boolean[][] movimentosPossiveis(XadrezPosicao posicaoOrigem) {
		/*
		 * Além de retornar uma matriz de boolean
		 * que são as posição de xadrez
		 * 
		 * o que vou ter que fazer?
		 * vou converter essa posição de xadrez
		 * para uma posição de matriz normal
		 */
		Posicao posicao = posicaoOrigem.toPosicao();
		//Antes de prosseguir vou validar a posição
		validarPosicaoOrigem(posicao);
		/* Agora vou retornar os movimentos possíveis da peça 
		dessa posição
		Eu fiz isso para que na principal eu possa imprimir
		os movimentos possiveis dessa peça.  */
		return tabuleiro.peca(posicao).possivelMovimento();
	}
	
	
	
	
	public PecaXadrez executarMovimentoXadrez(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
		/*
		 * Primeiro eu vou converter essas duas Posições
		 * para as posições da MATRIZ
		 */
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		/*
		 * Antes de realizar o movimento eu preciso
		 * validar se nessa posição havia uma peça
		 * Essa operação vai ser responsável por validar
		 * essa posição de origem
		 * Se não existir, esse metodo vai lançar uma excessão
		 */
		validarPosicaoOrigem(origem);
		/*
		 * Validar a posição de destino também
		 */
		validarPosicaoDestino(origem, destino);
		Peca capturarPeca = fazerMovimento(origem, destino);
		
		/*Agora vou retornar a peça capturada. 
		 * Vou ter que dar um downcasting antes
		 * Por que?
		 * Por que essa peça capturada era do tipo Peca
		 * E estou convertendo para PecaXadrez  */
		return (PecaXadrez)capturarPeca;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		/*
		 * Se não existir uma peça no tabueleiro
		 * nessa posição, lanço a excesão
		 */
		if(!tabuleiro.temUmaPecaNaPosicao(posicao)) {
			throw new XadrezException("Não existe peça na posição de origem.");
		}
		/*
		 * Verificar se existe movimentos possíveis
		 * para a peça.
		 * Vou chamar o tabuleiro
		 * vou acessar a peca(posicao) (DE ORIGEM)
		 * e apartir dessa peca vou chamar o atributo
		 * que criei de movimentos
		 * Se não tiver nenhum movimento possível
		 * nessa peca do tabuleiro
		 * vou lançar uma excessão
		 */
		if(!tabuleiro.peca(posicao).existeAlgumaMovimentacaoPossivel()) {
			throw new XadrezException("Não existe movimentos possíveis para a peça escolhida");
		}
	}
	
	
	/*
	 * Validando posição de destino
	 */
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		/*
		 * Basta eu testar se a posição de destino
		 * ela é um movimento possível em relação a posição de origem
		 * SE A PECA DE ORIGEM, A POSIÇÃO DE DESTINO
		 * NÃO É UM MOVIMENTO POSSÍVEL
		 * ENTÃO EU NÃO POSSO MEXER PARA LÁ
		 */
		if(!tabuleiro.peca(origem).possivelMovimentacao(destino)) {
			throw new XadrezException("A peça escolhida não pode se mover para a posição de destino");
		}
	}
	
	
	
	
	
	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		/*
		 * Implementar a lógica de realizar o movimento.
		 */
		Peca peca = tabuleiro.removaPeca(origem);
		/*
		 * Agora eu vou declarar uma outra peça
		 * que vai ser a pecaCapturada
		 * e remover ela do Destino.
		 */
		Peca capturarPeca = tabuleiro.removaPeca(destino);
		/*
		 * Colocando a peça de origem na peça de destino.
		 */
		tabuleiro.colocarPeca(peca, destino);
		return capturarPeca;
	}
	
	
	
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
	/*
	 * Esse metodo coloqueNovaPeca
	 * Vai receber as coordenadas do xadrez
	 * Nos parametro recebe a coluna, e a linha
	 * e recebe a PecaXadrez
	 * Vou atribuir o toPosicao aqui 
	 * Para que a gente possa: instanciar as peças 
	 * de xadrez, informando as coordenadas do sistema
	 * do xadrez, e não do sistema da matriz.
	 * Pois fica confuso.
	 */
	/*
	 * esse metodo vai chamar o TABULEIRO
	 * e chamar o metodo colocarPeca
	 * passando a peca, e instanciando
	 * o XadrezPosicao com os dados que recebi no parametro
	 * e para converter isso para a posição de MATRIZ
	 * vou utilizar o toPosicao
	 * 
	 * Então agora eu tenho a operação de colocar peça
	 * Passando a posição nas coordenada do xadrez
	 * que está vindo no parametro
		 */
		tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
		
	}
	
	
	
	
	
	
	
	/*
	 * Este metodo vai ser responsável por iniciar a
	 * partida de Xadrez.
	 * 
	 * na variavel tabuleiro, vou chamar o metodo colocarPeca
	 * E dentro dos parametro vou instanciar a Torre
	 * no primeiro parametro eu digo que está se referenciando ao TABULEIRO
	 * e na outra eu digo a COR e a posição eu crio
	 * instanciando a classe Posicao e passando o parametro
	 * vou colocar esse metodo aqui no construtor do PartidaXadrez
	 */
	
	private void iniciarPartida() {
		/*
		 * No lugar de ser assim:
		 *  tabuleiro.colocarPeca(new Torre(tabuleiro, Color.WHITE), new Posicao(2,1));
		 * eu agora vou atribuir a 'coluna' e a posição 6.  */
		
		//Coloca para min, na posição E8, um novo REI
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Color.WHITE));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Color.BLACK));
	}
	
	
}
