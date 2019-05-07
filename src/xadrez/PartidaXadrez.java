package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import camada.tabuleiro.Peca;
import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	/*
	 * Cora��o do sistema de Xadrez
	 * Nessa classe vai ter as regras
	 */
	private int turno;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	/*
	 * Declarando as duas listas
	 * no lugar de colocar na hora da declara��o;
	 * Fica melhor eu colocar direto no construtor 
	 * a parte do new ArrayList
	 */
	private List<Peca> pecasNoTabuleiro;
	private List<Peca> pecasCapturadas;
	
	
	
	/*
	 * Esse tabuleiro tem uma matriz de pe�as
	 * S� que essas pe�as s�o do tipo Peca
	 */
	
	public PartidaXadrez () {
		turno = 1;
		jogadorAtual = Color.WHITE;
		pecasNoTabuleiro = new ArrayList<>();
		pecasCapturadas = new ArrayList<>();
		/*
		 * Quem tem que saber a dimens�o de um
		 * tabuleiro de xadrez
		 * � a classe PartidaXadrez.
		 * Ent�o quando come�ar a partida
		 * eu crio um tabuleiro 8,8
		 * e chamo o metodo iniciarPartida()
		 */
		tabuleiro = new Tabuleiro(8, 8);
		iniciarPartida();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Color getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	
	public PecaXadrez[][] getPecas() {
		/*
		 * Esse metodo vai ter que retornar uma matriz
		 * de pe�as de xadrez correspondentes
		 * a essa partida.
		 */
		/*
	 * O que � que eu vou ter que fazer?
	 * as pe�as dentro do construtor da classe Tabuleiro
	 * ele tem as pe�as. Se voc� ver l� na classe Tabuleiro
	 * Ele tem uma matriz de pe�as e elas s�o do tipo Peca
	 * mas o meu metodo aqui na classe PartidaXadrez
	 * ele retorna o PecaXadrez
	 * Por que?
	 * Por que estou na camada(PACOTE) de xadrez
	 * Para a minha aplicacao que vai ser a classe
	 * Programa no pacote aplicacao
	 * Eu n�o quero liberar as pecas do tipo Peca
	 * Mas sim do tipo PecaXadrez
	 * Por que?
	 * Por que estou fazendo um desenvolvimento em camada
	 * Ent�o a principal ele vai poder enchergar somente
	 * a pe�a de Xadrez.
	 */
		/*
		 * Vou criar tempor�riamente uma vari�vel auxiliar
		 * do tipo PecaXadrez, vou chamar ela de mat (MATRIZ DE PE�AS)
		 * vou iniciar ela instanciando a classe PecaXadrez
		 * quantas linhas? as linhas do tabuleiro
		 * quantas colunas? as colunas do tabuleiro
		 * 
		 */
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		/*
		 * Agora vou pecorrer a matriz de pe�as do tabuleiro
		 */
		for (int i =0; i < tabuleiro.getLinhas(); i++) {
			for(int j=0; j < tabuleiro.getColunas(); j++) {
				/*
				 * Para cada posi��o i, j do meu tabuleiro
				 * vou fazer a minha matriz mat receba
				 * o tabuleiro.peca(i,j)
				 * Vou ter que fazer o casting
				 * para a classe PecaXadrez
				 * Por que ai sim ele vai interpretar
				 * como uma pe�a de XADREZ
				 * e n�o como pe�a comum
				 */
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	/*
	 * Movimentos poss�veis
	 */
	
	public boolean[][] movimentosPossiveis(XadrezPosicao posicaoOrigem) {
		/*
		 * Al�m de retornar uma matriz de boolean
		 * que s�o as posi��o de xadrez
		 * 
		 * o que vou ter que fazer?
		 * vou converter essa posi��o de xadrez
		 * para uma posi��o de matriz normal
		 */
		Posicao posicao = posicaoOrigem.toPosicao();
		//Antes de prosseguir vou validar a posi��o
		validarPosicaoOrigem(posicao);
		/* Agora vou retornar os movimentos poss�veis da pe�a 
		dessa posi��o
		Eu fiz isso para que na principal eu possa imprimir
		os movimentos possiveis dessa pe�a.  */
		return tabuleiro.peca(posicao).possivelMovimento();
	}
	
	
	
	
	public PecaXadrez executarMovimentoXadrez(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
		/*
		 * Primeiro eu vou converter essas duas Posi��es
		 * para as posi��es da MATRIZ
		 */
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		/*
		 * Antes de realizar o movimento eu preciso
		 * validar se nessa posi��o havia uma pe�a
		 * Essa opera��o vai ser respons�vel por validar
		 * essa posi��o de origem
		 * Se n�o existir, esse metodo vai lan�ar uma excess�o
		 */
		validarPosicaoOrigem(origem);
		/*
		 * Validar a posi��o de destino tamb�m
		 */
		validarPosicaoDestino(origem, destino);
		Peca capturarPeca = fazerMovimento(origem, destino);
		/*
		 * Quando eu executo o movimento
		 * e capturo a pe�a
		 * eu vou ter que testar se esse movimento
		 * colocou o pr�prio jogador em check
		 * e isso eu n�o posso deixar.
		 * 
		 */
		if(testeCheck(jogadorAtual)) {
			/*
			 * Se isso for verdade ele se colocou em cheque
			 * Ent�o eu vou ter que desfazer o movimento
			 * e lan�ar uma excesao.
			 */
			desfazerMovimento(origem, destino, capturarPeca);
			throw new XadrezException("Voc� n�o pode se colocar em cheque");
		}
		/*
		 * Se esse if falhar significa que o jogador
		 * n�o se colocou em cheque
		 * 
		 * resta agora testar se o oponente ficou em cheque
		 * 
		 * vou ter que colocar o atributo check 
		 * tem que ser verdadeiro.
		 * 
		 * vai receber se o oponente ficou em check
		 * vai receber verdadeiro.
		 * se n�o ela recebe falso.
		 * 
		 * se teste check do oponente do jogadorAtual
		 * se isso for verdade 
		 * ? Ent�o TRUE est� em check
		 * : Se n�o FALSE n�o est� em check
		 */
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		
		/*
		 * Implementando o pr�ximo Turno
		 */
		proximoTurno();
	
		/*Agora vou retornar a pe�a capturada. 
		 * Vou ter que dar um downcasting antes
		 * Por que?
		 * Por que essa pe�a capturada era do tipo Peca
		 * E estou convertendo para PecaXadrez  */
		return (PecaXadrez)capturarPeca;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		/*
		 * Se n�o existir uma pe�a no tabueleiro
		 * nessa posi��o, lan�o a exces�o
		 */
		if(!tabuleiro.temUmaPecaNaPosicao(posicao)) {
			throw new XadrezException("N�o existe pe�a na posi��o de origem.");
		}
		
		if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getColor()) {
		/*
		 * Se o jogadorAtual for diferente do 
		 * tabuleiro a pe�a na posi��o do getColor
		 * S� que o GetColor n�o est� dando certo aqui
		 * pois ela � uma propriedade do PecaXadrez
		 * pois o tabuleiro.peca � da classe mais generica
		 * que � o PECA, ent�o vou ter que dar um downcasting
		 * aqui para a classe PecaXadrez
		 * 
		 * Ent�o fica assim eu pego a pe�a do tabuleiro
		 * nessa posi��o fa�o o downcasting para PecaXadrez
		 * e testo a Cor dela com o getColor
		 * se essa cor for diferente do cor do jogadorAtual
		 * Significa que � uma pe�a do jogadorAdvers�rio
		 * e nisso eu n�o posso move-la
		 */
			throw new XadrezException("Peca escolhida nao e a sua!");
		}
		
		
		/**************************************/
		/*
		 * Verificar se existe movimentos poss�veis
		 * para a pe�a.
		 * Vou chamar o tabuleiro
		 * vou acessar a peca(posicao) (DE ORIGEM)
		 * e apartir dessa peca vou chamar o atributo
		 * que criei de movimentos
		 * Se n�o tiver nenhum movimento poss�vel
		 * nessa peca do tabuleiro
		 * vou lan�ar uma excess�o
		 */
		if(!tabuleiro.peca(posicao).existeAlgumaMovimentacaoPossivel()) {
			throw new XadrezException("N�o existe movimentos poss�veis para a pe�a escolhida");
		}
		
		
	}
	
	
	/*
	 * Validando posi��o de destino
	 */
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		/*
		 * Basta eu testar se a posi��o de destino
		 * ela � um movimento poss�vel em rela��o a posi��o de origem
		 * SE A PECA DE ORIGEM, A POSI��O DE DESTINO
		 * N�O � UM MOVIMENTO POSS�VEL
		 * ENT�O EU N�O POSSO MEXER PARA L�
		 */
		if(!tabuleiro.peca(origem).possivelMovimentacao(destino)) {
			throw new XadrezException("A pe�a escolhida n�o pode se mover para a posi��o de destino");
		}
	}
	
	
	
	
	
	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		/*
		 * Implementar a l�gica de realizar o movimento.
		 */
		Peca peca = tabuleiro.removaPeca(origem);
		/*
		 * Agora eu vou declarar uma outra pe�a
		 * que vai ser a pecaCapturada
		 * e remover ela do Destino.
		 */
		Peca capturarPeca = tabuleiro.removaPeca(destino);
		/*
		 * Colocando a pe�a de origem no lugar
		 * da pe�a de destino.
		 */
		tabuleiro.colocarPeca(peca, destino);
		/*
		 * Vou testar se essa pe�a capturada for diferente
		 * de nulo
		 * Opa. Capturei essa pe�a, vou ter que remover
		 * essa pe�a l� da lista de pe�as do tabuleiro.
		 * e adicionar essa pe�a na lista de pe�asCapturadas
		 */
		if(capturarPeca != null) {
			pecasNoTabuleiro.remove(capturarPeca);
			/*
			 * Deu o seguinte erro
			 * pois a pecaCapturada � do tipo Peca
			 * E a minha lista � do tipo PecaXadrez
			 * Para ficar uma lista mais generica
			 * Vou colocar a lista como Peca
			 * Para ficar mais gen�rica
			 * e aceitar todo tipo de pe�a.
			 */
			pecasCapturadas.add(capturarPeca);
		}
		return capturarPeca;
	}
	
	/*
	 * Desfazer movimento
	 * Para em caso dele colocar o REI 
	 * em situa��o de cheque.
	 * 
	 * Nos parametro vai receber a pe�a de origem
	 * e uma poss�vel pe�a capturada
	 */
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		/*
		 * Tira aquela pe�a que voc� moveu l� do destino
		 */
		Peca peca = tabuleiro.removaPeca(destino);
		/*
		 * Devolvendo a pe�a para a origem onde estava
		 * Pois esse movimento n�o � possivel
		 */
		tabuleiro.colocarPeca(peca, origem);
		/*
		 * E se tinha sido capturada uma pe�a?
		 * Eu vou ter que voltar essa pe�a capturada para a origem
		 * de destino.
		 */
		if(pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			/*
			 * E agora eu tenho que tirar essa peca
			 * da lista de pecasCapturadas
			 * e colocar ela novamente na lista de pe�as
			 * no tabuleiro.
			 */
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	
	
	
	
	
	/*
	 * Metodo respons�vel pela proximoTurno
	 */
	
	private void proximoTurno() {
		/*
		 * Vou ter que incrementar para ir de 
		 * 1 vai para o 2, e do 2 vai para o 3
		 * 
		 * JogadorAtual recebe
		 * EXPRESS�O TERN�RIA
		 * (SE O JOGADORATUAL FOR IGUAL A COR BRANCA
		 * ENT�O AGORA ELE VAI SER A COR PRETA
		 * CASO CONTRARIO : ELE VAI SER A COR BRANCA
		 */
		turno++;
		jogadorAtual = (jogadorAtual == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	
	
	
	
	
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
	/*
	 * Esse metodo coloqueNovaPeca
	 * Vai receber as coordenadas do xadrez
	 * Nos parametro recebe a coluna, e a linha
	 * e recebe a PecaXadrez
	 * Vou atribuir o toPosicao aqui 
	 * Para que a gente possa: instanciar as pe�as 
	 * de xadrez, informando as coordenadas do sistema
	 * do xadrez, e n�o do sistema da matriz.
	 * Pois fica confuso.
	 */
	/*
	 * esse metodo vai chamar o TABULEIRO
	 * e chamar o metodo colocarPeca
	 * passando a peca, e instanciando
	 * o XadrezPosicao com os dados que recebi no parametro
	 * e para converter isso para a posi��o de MATRIZ
	 * vou utilizar o toPosicao
	 * 
	 * Ent�o agora eu tenho a opera��o de colocar pe�a
	 * Passando a posi��o nas coordenada do xadrez
	 * que est� vindo no parametro
		 */
		tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
		/*
		 * Aqui al�m de colocar as pe�as no tabuleiro
		 * Eu j� coloco ela na lista de pe�as do tabuleiro
		 */
		pecasNoTabuleiro.add(peca);
		
	}
	
	/*
	 * Saber qual � a cor do rei
	 * 
	 */
	private PecaXadrez rei(Color color) {
		/*
		 * Eu vou ter que procurar na lista
		 * do pecasNoTabuleiro, qual � a cor desse rei
		 * 
		 * strem().filter(PREDICADO)
		 * Vou procurar ent�o
		 * (PREDICADO) = "Toda pe�a x 
		 * -> = TAL QUE a cor dessa pe�a x, seja a mesma cor
		 * que eu passar por aqui pelo parametro
		 * 
		 * S� que essa lista � do tipo Peca, s� que Peca n�o tem cor
		 * Quem tem cor � a PecaXadrez, ent�o vou ter fazer um downcasting
		 * ((PecaXadrez) de x) . getColor
		 * 
		 * Express�es Lambdas
		 */
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
		/*
		 * Vou testar agora
		 * PARA cada Peca p na minha list {
		 */
		for(Peca p : list) {
			/*
			 * Se essa pe�a for uma inst�ncia de rei
			 * significa que eu encontrei o rei
			 * e assim eu retorno dando um downcasting
			 */
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		/*
		 * Se eu esgotar o meu for e n�o encontrar o
		 * meu rei
		 * vou lan�ar uma excess�o aqui
		 * que � uma exces�o pronta do java
		 * E ele vai conter a mensagem
		 * n�o existe o rei da cor TAL no tabuleiro
		 * Isso daqui � para nunca acontecer, se um dia acontecer
		 * � por que o meu sistema de xadrez est�
		 * com algum problema.
		 */
		throw new IllegalStateException("N�o existe o rei da cor" + color + "no tabuleiro.");
	}
	
	/*
	 * Criar o metodo check
	 * por que uma COR?
	 * Por que vou testar se o rei dessa cor
	 * est� em check.
	 */
	
	private boolean testeCheck(Color color) {
		/*
		 * Eu vou pegar a posi��o do rei
		 * vou chamar de posicaoRei
		 * como vou pegar?
		 * eu vou chamar o metodo rei, e passando
		 * a cor como argumento ai eu pego a pe�a
		 * correspondente ao rei.
		 * 
		 * E apartir dessa pe�a, eu vou chamar o
		 * .getXadrezPosicao(), e o toPosicao().
		 * E assim eu pego a posi��o do meu rei
		 * que est� em formato de matriz.
		 */
		Posicao posicaoRei = rei(color).getPosicaoXadrez().toPosicao();
		/*
		 * Pr�ximo passo, eu vou querer uma lista
		 * das pe�as do meu oponente dessa cor
		 * 
		 * Essa lista vai ser as pe�as no tabuleiros
		 * filtradas com a cor do oponente desse rei
		 * que vem como argumento]
		 * 
		 * Fui no metodo rei, e copiei
		 * a parte da express�o lambda
		 * Modifiquei s� na parte da cor
		 * pois tem que ser a cor do metodo oponente
		 * E essa � a lista de pe�as do oponente
		 */
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == oponente(color)).collect(Collectors.toList());
		/*
		 * Para cada pe�a continda nessa lista
		 * eu vou ter que testar se existe algum movimento
		 * poss�vel que leva a posi��o do meu rei
		 * que est� no atributo posicaoRei
		 * 
		 * Para cada Pe�a p continda na lista pecasOponente
		 */
		for(Peca p: pecasOponente) {
			/*
			 * Pegando os movimentos poss�veis, com uma matriz
			 */
			boolean[][] mat = p.possivelMovimento();
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()] ) {
				/*
				 * Se esse elemento da matriz for verdadeiro
				 * significa que o meu rei est� em check
				 * e assim vai retornar true
				 * dizendo que o metodo teste de check deu verdadeiro
				 */
				return true;
			}
		}
		/*
		 * Se o rei n�o for marcado como true
		 * � por que n�o est� em cheque
		 * e assim retorno falso depois de esgotar o FOR
		 */
		return false;
	}
	
	
	
	
	private Color oponente(Color color) {
		/*
		 * Se esta cor que eu passei como argumento
		 * for igual a Color.WHITE
		 * ? = ENT�O
		 * EU VOU RETORNAR Color.BLACK
		 * : = CASO CONTRARIO
		 * eu vou retornar = Color.WHITE
		 */
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	
	
	
	private void iniciarPartida() {

		/*
		 * Este metodo vai ser respons�vel por iniciar a
		 * partida de Xadrez.
		 * 
		 * na variavel tabuleiro, vou chamar o metodo colocarPeca
		 * E dentro dos parametro vou instanciar a Torre
		 * no primeiro parametro eu digo que est� se referenciando ao TABULEIRO
		 * e na outra eu digo a COR e a posi��o eu crio
		 * instanciando a classe Posicao e passando o parametro
		 * vou colocar esse metodo aqui no construtor do PartidaXadrez
		 */
		/*
		 * No lugar de ser assim:
		 *  tabuleiro.colocarPeca(new Torre(tabuleiro, Color.WHITE), new Posicao(2,1));
		 * eu agora vou atribuir a 'coluna' e a posi��o 6.  */
		
		//Coloca para min, na posi��o E8, um novo REI
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
