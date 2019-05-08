package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import camada.tabuleiro.Peca;
import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	/*
	 * Coração do sistema de Xadrez
	 * Nessa classe vai ter as regras
	 */
	private int turno;
	private Color jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	/*
	 * Declarando as duas listas
	 * no lugar de colocar na hora da declaração;
	 * Fica melhor eu colocar direto no construtor 
	 * a parte do new ArrayList
	 */
	private List<Peca> pecasNoTabuleiro;
	private List<Peca> pecasCapturadas;
	
	
	
	/*
	 * Esse tabuleiro tem uma matriz de peças
	 * Só que essas peças são do tipo Peca
	 */
	
	public PartidaXadrez () {
		turno = 1;
		jogadorAtual = Color.WHITE;
		pecasNoTabuleiro = new ArrayList<>();
		pecasCapturadas = new ArrayList<>();
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
	
	public int getTurno() {
		return turno;
	}
	
	public Color getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		/*
		 * Quando eu executo o movimento
		 * e capturo a peça
		 * eu vou ter que testar se esse movimento
		 * colocou o próprio jogador em check
		 * e isso eu não posso deixar.
		 * 
		 */
		if(testeCheck(jogadorAtual)) {
			/*
			 * Se isso for verdade ele se colocou em cheque
			 * Então eu vou ter que desfazer o movimento
			 * e lançar uma excesao.
			 */
			desfazerMovimento(origem, destino, capturarPeca);
			throw new XadrezException("Você não pode se colocar em cheque");
		}
		/*
		 * Se esse if falhar significa que o jogador
		 * não se colocou em cheque
		 * 
		 * resta agora testar se o oponente ficou em cheque
		 * 
		 * vou ter que colocar o atributo check 
		 * tem que ser verdadeiro.
		 * 
		 * vai receber se o oponente ficou em check
		 * vai receber verdadeiro.
		 * se não ela recebe falso.
		 * 
		 * se teste check do oponente do jogadorAtual
		 * se isso for verdade 
		 * ? Então TRUE está em check
		 * : Se não FALSE não está em check
		 */
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		
		/*
		 * Se a jogada que eu fiz deixou o meu
		 * oponente em checkMate
		 * não vai haver próximo turno
		 * Implementando o próximo Turno
		 * 
		 * SE A JOGADA QUE EU FIZ DEIXOU O MEU OPONENTE
		 * EM CHECK MATE
		 * 
		 * VOU CHAMAR O TESTECHECKMATE do OPONENTE
		 * no JOGADOR ATUAL
		 * 
		 * Se o oponente da peça que mexeu ficou
		 * em cheque mate, significa que o jogo acabou.
		 * se não continua.
		 */
		if(testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}else {
			proximoTurno();
		}
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
		
		if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getColor()) {
		/*
		 * Se o jogadorAtual for diferente do 
		 * tabuleiro a peça na posição do getColor
		 * Só que o GetColor não está dando certo aqui
		 * pois ela é uma propriedade do PecaXadrez
		 * pois o tabuleiro.peca é da classe mais generica
		 * que é o PECA, então vou ter que dar um downcasting
		 * aqui para a classe PecaXadrez
		 * 
		 * Então fica assim eu pego a peça do tabuleiro
		 * nessa posição faço o downcasting para PecaXadrez
		 * e testo a Cor dela com o getColor
		 * se essa cor for diferente do cor do jogadorAtual
		 * Significa que é uma peça do jogadorAdversário
		 * e nisso eu não posso move-la
		 */
			throw new XadrezException("Peca escolhida nao e a sua!");
		}
		
		
		/**************************************/
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
		 * Vou retirar essa peca do tipo Peca
		 * e vou colocar para tipo PecaXadrez
		 * Utilizando o Casting
		 * Peca peca = tabuleiro.removaPeca(origem);
		 * Estava assim 
		 * e vai ficar como está agora.
		 * e isso não vai prejudar nada abaixo
		 * Por que?
		 * Por que o tabuleiro.colocarPeca
		 * recebe uma peca como parametro
		 * e isso faz um upcasting naturalmente
		 * ou seja, volta pro tipo Peca
		 */
		PecaXadrez peca = (PecaXadrez)tabuleiro.removaPeca(origem);
		/*
		 * A cada movimento que eu realizar vou
		 * acrescentar a contagem de movimentos
		 */
		peca.aumentarContagemMovimentos();
		
		
		
		/*
		 * Agora eu vou declarar uma outra peça
		 * que vai ser a pecaCapturada
		 * e remover ela do Destino.
		 */
		Peca capturarPeca = tabuleiro.removaPeca(destino);
		/*
		 * Colocando a peça de origem no lugar
		 * da peça de destino.
		 */
		tabuleiro.colocarPeca(peca, destino);
		/*
		 * Vou testar se essa peça capturada for diferente
		 * de nulo
		 * Opa. Capturei essa peça, vou ter que remover
		 * essa peça lá da lista de peças do tabuleiro.
		 * e adicionar essa peça na lista de peçasCapturadas
		 */
		if(capturarPeca != null) {
			pecasNoTabuleiro.remove(capturarPeca);
			/*
			 * Deu o seguinte erro
			 * pois a pecaCapturada é do tipo Peca
			 * E a minha lista é do tipo PecaXadrez
			 * Para ficar uma lista mais generica
			 * Vou colocar a lista como Peca
			 * Para ficar mais genérica
			 * e aceitar todo tipo de peça.
			 */
			pecasCapturadas.add(capturarPeca);
		}
		return capturarPeca;
	}
	
	/*
	 * Desfazer movimento
	 * Para em caso dele colocar o REI 
	 * em situação de cheque.
	 * 
	 * Nos parametro vai receber a peça de origem
	 * e uma possível peça capturada
	 */
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		/*
		 * Tira aquela peça que você moveu lá do destino
		 */
		PecaXadrez peca = (PecaXadrez)tabuleiro.removaPeca(destino);
		/*
		 * Quando eu desfazer o movimento
		 * Eu tenho que decrementar a quantidade
		 * de movimento dessa peça.
		 */
		peca.diminuirContagemMovimentos();
		
		/*
		 * Devolvendo a peça para a origem onde estava
		 * Pois esse movimento não é possivel
		 */
		tabuleiro.colocarPeca(peca, origem);
		/*
		 * E se tinha sido capturada uma peça?
		 * Eu vou ter que voltar essa peça capturada para a origem
		 * de destino.
		 */
		if(pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			/*
			 * E agora eu tenho que tirar essa peca
			 * da lista de pecasCapturadas
			 * e colocar ela novamente na lista de peças
			 * no tabuleiro.
			 */
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	
	
	
	
	
	/*
	 * Metodo responsável pela proximoTurno
	 */
	
	private void proximoTurno() {
		/*
		 * Vou ter que incrementar para ir de 
		 * 1 vai para o 2, e do 2 vai para o 3
		 * 
		 * JogadorAtual recebe
		 * EXPRESSÃO TERNÁRIA
		 * (SE O JOGADORATUAL FOR IGUAL A COR BRANCA
		 * ENTÃO AGORA ELE VAI SER A COR PRETA
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
		/*
		 * Aqui além de colocar as peças no tabuleiro
		 * Eu já coloco ela na lista de peças do tabuleiro
		 */
		pecasNoTabuleiro.add(peca);
		
	}
	
	/*
	 * Saber qual é a cor do rei
	 * 
	 */
	private PecaXadrez rei(Color color) {
		/*
		 * Eu vou ter que procurar na lista
		 * do pecasNoTabuleiro, qual é a cor desse rei
		 * 
		 * strem().filter(PREDICADO)
		 * Vou procurar então
		 * (PREDICADO) = "Toda peça x 
		 * -> = TAL QUE a cor dessa peça x, seja a mesma cor
		 * que eu passar por aqui pelo parametro
		 * 
		 * Só que essa lista é do tipo Peca, só que Peca não tem cor
		 * Quem tem cor é a PecaXadrez, então vou ter fazer um downcasting
		 * ((PecaXadrez) de x) . getColor
		 * 
		 * Expressões Lambdas
		 */
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
		/*
		 * Vou testar agora
		 * PARA cada Peca p na minha list {
		 */
		for(Peca p : list) {
			/*
			 * Se essa peça for uma instância de rei
			 * significa que eu encontrei o rei
			 * e assim eu retorno dando um downcasting
			 */
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		/*
		 * Se eu esgotar o meu for e não encontrar o
		 * meu rei
		 * vou lançar uma excessão aqui
		 * que é uma excesão pronta do java
		 * E ele vai conter a mensagem
		 * não existe o rei da cor TAL no tabuleiro
		 * Isso daqui é para nunca acontecer, se um dia acontecer
		 * é por que o meu sistema de xadrez está
		 * com algum problema.
		 */
		throw new IllegalStateException("Não existe o rei da cor" + color + "no tabuleiro.");
	}
	
	/*
	 * Criar o metodo check
	 * por que uma COR?
	 * Por que vou testar se o rei dessa cor
	 * está em check.
	 */
	
	private boolean testeCheck(Color color) {
		/*
		 * Eu vou pegar a posição do rei
		 * vou chamar de posicaoRei
		 * como vou pegar?
		 * eu vou chamar o metodo rei, e passando
		 * a cor como argumento ai eu pego a peça
		 * correspondente ao rei.
		 * 
		 * E apartir dessa peça, eu vou chamar o
		 * .getXadrezPosicao(), e o toPosicao().
		 * E assim eu pego a posição do meu rei
		 * que está em formato de matriz.
		 */
		Posicao posicaoRei = rei(color).getPosicaoXadrez().toPosicao();
		/*
		 * Próximo passo, eu vou querer uma lista
		 * das peças do meu oponente dessa cor
		 * 
		 * Essa lista vai ser as peças no tabuleiros
		 * filtradas com a cor do oponente desse rei
		 * que vem como argumento]
		 * 
		 * Fui no metodo rei, e copiei
		 * a parte da expressão lambda
		 * Modifiquei só na parte da cor
		 * pois tem que ser a cor do metodo oponente
		 * E essa é a lista de peças do oponente
		 */
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == oponente(color)).collect(Collectors.toList());
		/*
		 * Para cada peça continda nessa lista
		 * eu vou ter que testar se existe algum movimento
		 * possível que leva a posição do meu rei
		 * que está no atributo posicaoRei
		 * 
		 * Para cada Peça p continda na lista pecasOponente
		 */
		for(Peca p: pecasOponente) {
			/*
			 * Pegando os movimentos possíveis, com uma matriz
			 */
			boolean[][] mat = p.possivelMovimento();
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()] ) {
				/*
				 * Se esse elemento da matriz for verdadeiro
				 * significa que o meu rei está em check
				 * e assim vai retornar true
				 * dizendo que o metodo teste de check deu verdadeiro
				 */
				return true;
			}
		}
		/*
		 * Se o rei não for marcado como true
		 * é por que não está em cheque
		 * e assim retorno falso depois de esgotar o FOR
		 */
		return false;
	}
	
	/*
	 * Metodo checkMate
	 */
	private boolean testeCheckMate(Color color) {
		if(!testeCheck(color)) {
			/*
			 * Se essa cor não estiver em check
			 * significa que não está em check mate.
			 */
			return false;
		}
		/*
		 * Se todas as peças dessa cor, não tiver
		 * um movimento possível que tire o rei do check
		 * 
		 * Vou criar uma lista e essa lista vai ter todas
		 * as peças dessa cor aqui
		 */
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
		for(Peca p: list) {
			/*
			 * Se possuir algum movimento possível da peças
			 * dessa cor, que tire o check do rei
			 * ai eu retorno falso.
			 */
			boolean[][] mat = p.possivelMovimento();
			for(int i=0; i<tabuleiro.getLinhas(); i++) {
				for(int j=0; j<tabuleiro.getColunas(); j++) {
					/*
					 * Para cada elemento da matriz, eu
					 * tenho que saber, se é um movimento possível.s
					 */
					if(mat[i][j]) {
					/*
					 * Vou pegar essa peça p,
					 * mover essa peça para essa posição [i][j]
					 * que é um movimento possível, e ai 
					 * sim vou testar se é um movimento possível.
					 * 
					 * Primeiro vou ter que pegar uma Posição
					 * origem que é a posição da peça P
					 * 
					 * Eu poderia simplesmente colocar
					 * p.getPosicao mas como p é PROTECTED
					 * Como se trata de uma classe de outro pacote
					 * que não é uma subclasse
					 * eu vou ter que dar um DOWNCASTING
					 * para PecaXadrez desse P
					 * e partir desse objeto que agora é do tipo PecaXadrez
					 * eu vou chamar o getPosicaoXadrez
					 * que vai ser a posição no formato do xadrez
					 * e ai a partir dessa posição no formato do xadrez
					 * eu posso converter ela para toPosition()
					 * 
					 * Posicao de destino vai ser o i j da matriz
					 * 
					 * Agora vou realizar o movimento da peça p, 
					 * da origem para o destino
					 * 
					 */
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().toPosicao();
						Posicao destino = new Posicao(i,j);
						//Esse metodo realizar o movimento da peça de origem para destino
						Peca pecaCapturada = fazerMovimento(origem, destino);
						/*
						 * Agora eu vou testar se ainda está em check
						 * 
						 * Vou primeiro criar uma variavel auxiliar
						 * chamada de testeCheck e ela vai receber
						 * a chamada do metodo teste check
						 * e ela vai testar se o rei da minha cor
						 * ainda está em check
						 */
						boolean testeCheck = testeCheck(color);
						/*
						 * Antes de concluir vou chamar o
						 * desfazermovimento
						 * Por que?
						 * Por que eu fiz o movimento ali só para testar
						 * mas eu tenho que desfazer o Movimento
						 * não posso esquecer
						 * se não vou deixar o meu tabuleiro maluco.
						 */
						desfazerMovimento(origem, destino, pecaCapturada);
						/*
						 * Agora vou testar
						 * Se não estava em check, significa que esse movimento
						 * tirou o meu rei do check
						 * no caso vou retornar false, 
						 * pois não estava em checkmate
						 */
						if(!testeCheck) {
							return false;
						}
						
					}
				}
			}
		}
		return true;
	}
	
	
	private Color oponente(Color color) {
		/*
		 * Se esta cor que eu passei como argumento
		 * for igual a Color.WHITE
		 * ? = ENTÃO
		 * EU VOU RETORNAR Color.BLACK
		 * : = CASO CONTRARIO
		 * eu vou retornar = Color.WHITE
		 */
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	
	
	
	private void iniciarPartida() {

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
		/*
		 * No lugar de ser assim:
		 *  tabuleiro.colocarPeca(new Torre(tabuleiro, Color.WHITE), new Posicao(2,1));
		 * eu agora vou atribuir a 'coluna' e a posição 6.  */
		
		//Coloca para min, na posição E8, um novo REI
		
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Color.WHITE));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Color.WHITE));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Color.WHITE));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Color.WHITE));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Color.WHITE));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Color.WHITE));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Color.WHITE));
		

		colocarNovaPeca('a', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Color.BLACK));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Color.BLACK));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Color.BLACK));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Color.BLACK));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Color.BLACK));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Color.BLACK));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Color.BLACK));
	}
	
	
}
