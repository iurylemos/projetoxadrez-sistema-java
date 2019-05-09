package xadrez;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import camada.tabuleiro.Peca;
import camada.tabuleiro.Posicao;
import camada.tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
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
	private PecaXadrez peaoPassante;
	//Promocao quando um peão faz uma dama.
	private PecaXadrez promocao;
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
	
	public PecaXadrez getPeaoPassante() {
		return peaoPassante;
	}
	
	public PecaXadrez getPromocao() {
		return promocao;
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
		/***********************************/
		/*
		 * Realizando o peão passante.
		 * 
		 */
		PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino);
		
		
		
		
		/***********************************/
		/**************************************/
		 /* Movimento Especial - Promoção.
		  * 
		  * vou jogar na variavel promocao o valor nulo
		  * para assegurar que eu to fazendo um novo teste.
		  * 
		  * Se a peca que foi movida ela foi uma instancia de Peao.
		  * Ai eu vou testar, será que chegou no final?
		  * 
		  */
		promocao = null;
		if(pecaMovida instanceof Peao) {
			if((pecaMovida.getColor() == Color.WHITE && destino.getLinha() == 0 || (pecaMovida.getColor() == Color.BLACK && destino.getLinha() == 7) )) {
				/*
				 * Se uma das peça movida chegar no final da linha.
				 * PRIMEIRO EU JOGO O PEÃO, DEPOIS FAÇO A TROCA.
				 */
				promocao = (PecaXadrez)tabuleiro.peca(destino);
				/*Trocando por uma peça poderosa, vou botar Rainha
				 * Mas depois o usuário vai poder escolher outra peça. */
				promocao = substituirPecaPrometida("R");
			}
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
		
		// #Movimento Especial Peão Passante.
		/*
		 * Testar se a peça movida foi um peão
		 * e se foram duas casas.
		 * 
		 * Testar se a PecaMovida é uma instancia
		 * da classe Peao
		 * E testar se ela andou duas casas.
		 * (posicaoDeDestino.Linha ==  posicaoDeOrigem.Linha -2 
		 * Ou então MAIS 2 que é das peças brancas
		 */
		if(pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() -2 || destino.getLinha() == origem.getLinha() +2)) {
			/*
			 * Significa que foi uma peça peão que andou duas casas.
			 */
			peaoPassante = pecaMovida;
		}else {
			peaoPassante = null;
		}
		
		
		
		
		
		/*Agora vou retornar a peça capturada. 
		 * Vou ter que dar um downcasting antes
		 * Por que?
		 * Por que essa peça capturada era do tipo Peca
		 * E estou convertendo para PecaXadrez  */
		return (PecaXadrez)capturarPeca;
	}
	
	/***************************************/
	/*
	 * Esse metodo vai ser chamado de novo
	 * quando o usuário fizer a escolha da peça.
	 */
	public PecaXadrez substituirPecaPrometida(String tipo) {
		//Programação defensiva.
		if (promocao == null) {
			throw new IllegalStateException("Não há peça para ser promovida.");
		}
		/*
		 * Fazer a validação do argumento que foi passado
		 * Para os tipos que são Rainha, Cavalo, Torre..
		 * 
		 * Se o tipo, não for igual a letra "C"
		 * 
		 * Comparar se um String é igual a outro
		 * eu utilizo o equals
		 * 
		 * Pois o String é um tipo Classe e não primitivo.
		 * 
		 */
		if(!tipo.equals("C") && !tipo.equals("R") && !tipo.equals("T") && !tipo.equals("B")) {
			throw new InvalidParameterException("Tipo invalido dessa promoção");
		}
		
		//Pegando a peça que está na posição
		Posicao pos = promocao.getPosicaoXadrez().toPosicao();
		//E removendo ela do tabuleiro.
		Peca p = tabuleiro.removaPeca(pos);
		//Agora vou excluir ele da lista.
		pecasNoTabuleiro.remove(p);
		//Instanciei a nova peça do metodo que criei nova peça
		PecaXadrez novaPeca = novaPeca(tipo, promocao.getColor());
		//Colocando a nova peça no lugar da peça promovida que é a POS
		tabuleiro.colocarPeca(novaPeca, pos);
		//Na lista vou adicionar essa nova peça.
		pecasNoTabuleiro.add(novaPeca);
		//E vou retornar essa nova peça que instanciei.
		return novaPeca;
		
	}
	
	/*
	 * Criando um metodo auxiliar para a PROMOÇÃO.
	 */
	private PecaXadrez novaPeca(String tipo, Color color) {
		if(tipo.equals("B")) {
			return new Bispo(tabuleiro, color);
		}
		if(tipo.equals("C")) {
			return new Cavalo(tabuleiro, color);
		}
		if(tipo.equals("R")) {
			return new Rainha(tabuleiro,color);
		}
	//Se falha todo mundo acima, ele retorna a torre.
			return new Torre(tabuleiro, color);
		
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
		/****************************************/
		// # Movimentação ESPECIAL Roque Pequeno
		/*
		 * Se a PECA p que foi movida
		 * for uma instancia de Rei, 
		 * e também a posição de destino
		 * na coluna, for igual a posição de origem
		 * na coluna + 2, significa que o meu REI
		 * andou duas casas para a direita
		 * fazendo o roque pequeno
		 */
		if(peca instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			/*
			 * Fazendo a torre ir para a direita
			 * Vou pegar a torre que está a 3 colunas do REI
			 * e passar ela a duas a direita.
			 * Vou criar um objeto auxiliar de Posicao
			 * Posicao De Origem da torre
			 * no parametro da Instancia
			 * vai receber a mesma linha do rei
			 * e a origem da torre 
			 */
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			/*
			 * Vou criar um outro objeto auxiliar
			 * para dizer o destino da torre.
			 * Ela vai ser a mesma posição do rei
			 * só que uma coluna para a direita.
			 */
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			/*
			 * Criando uma variavel torre do tipo peça xadrez
			 * que ela remove a peça de destino do tabuleiro
			 */
			PecaXadrez torre = (PecaXadrez)tabuleiro.removaPeca(origemT);
			/*
			 * E coloca a peça na posicao de destino
			 */
			tabuleiro.colocarPeca(torre, destinoT);
			/*
			 * E incremento a quantindade de movimentos dela.
			 */
			torre.aumentarContagemMovimentos();
		}
		
		/****************************************/
		// # Movimentação ESPECIAL Roque GRANDE
		/*
		 * Se a PECA p que foi movida
		 * for uma instancia de Rei, 
		 * e também a posição de destino
		 * na coluna, for igual a posição de origem
		 * na coluna + 2, significa que o meu REI
		 * andou duas casas para a direita
		 * fazendo o roque pequeno
		 */
		if(peca instanceof Rei && destino.getColuna() == origem.getColuna() - 2 ) {
			/*
			 * Fazendo a torre ir para a direita
			 * Vou pegar a torre que está a 3 colunas do REI
			 * e passar ela a duas a direita.
			 * Vou criar um objeto auxiliar de Posicao
			 * Posicao De Origem da torre
			 * no parametro da Instancia
			 * vai receber a mesma linha do rei
			 * e a origem da torre 
			 */
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			/*
			 * Vou criar um outro objeto auxiliar
			 * para dizer o destino da torre.
			 * Ela vai ser a mesma posição do rei
			 * só que uma coluna para a direita.
			 */
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			/*
			 * Criando uma variavel torre do tipo peça xadrez
			 * que ela remove a peça de destino do tabuleiro
			 */
			PecaXadrez torre = (PecaXadrez)tabuleiro.removaPeca(origemT);
			/*
			 * E coloca a peça no destino
			 */
			tabuleiro.colocarPeca(torre, destinoT);
			/*
			 * E incremento a quantindade de movimentos dela.
			 */
			torre.aumentarContagemMovimentos();
		}
		
		/*********************************/
		// #MovimentoEspecial Peão Passante.
		/*
		 * Peão passante
		 * Vou testar se a peca que foi movida
		 * é uma instancia de peão
		 */
	
		if(peca instanceof Peao) {
		/*
		 * Um peão só pode andar na diagonal quando captura
		 * Mas a diferença é que no peão passante
		 * Ele só pode andar na diagonal, quando a casa estiver vázia
		 * 
		 * SE A POSIÇÃO DE ORIGEM FOR DIFERENTE DA POSIÇÃO DE DESTINO
		 * SIGNIFICA QUE MUDOU DE COLUNA, ELE ANDOU NA DIAGONAL.
		 * 
		 * E A PEÇACAPTURADA = NULO
		 * 
		 * Ou seja se o meu peão andou na diagonal e não capturouPeca
		 * significa que foi o PeãoPassante
		 * 
		 */
			if(origem.getColuna() != destino.getColuna() && capturarPeca == null) {
				Posicao posicaoPeao;
				/*
				 * Se a cor da peca que moveu
				 * for igual a cor branca
				 * significa que a peça que vai ser capturada
				 * ela está em baixo do meu peão
				 * 
				 * Então vai ser na mesma posição dele
				 * só que linha mais 1
				 * 
				 */
				if(peca.getColor() == Color.WHITE) {
					posicaoPeao = new Posicao(destino.getLinha() +1, destino.getColuna());
					
				}else {
					posicaoPeao = new Posicao(destino.getLinha() -1, destino.getColuna());
				}
				/*
				 * Vou falar então que a pecaCapturada 
				 * vai ser a posicaoPeao.
				 */
				capturarPeca = tabuleiro.removaPeca(posicaoPeao);
				//Adicionar na lista de peças capturadas
				pecasCapturadas.add(capturarPeca);
				//E removendo das peças do tabuleiro a peça capturada
				pecasNoTabuleiro.remove(capturarPeca);
			}
			/*************************************/
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
		/****************************************/
		// # Desfazendo Movimentação ESPECIAL Roque Pequeno
		
		if(peca instanceof Rei && destino.getColuna() == origem.getColuna() +2 ) {
			
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			/*No fazendo movimento removi de origem e coloquei no destino
			 * E aqui no desfazer movimento, vou tirar do destino, e colocar na origem
			 * assim estou desfazendo. */
			PecaXadrez torre = (PecaXadrez)tabuleiro.removaPeca(destinoT);
			/*
			 * E coloca a peça no destino de origem
			 * já que está desfazendo movimento
			 */
			tabuleiro.colocarPeca(torre, origemT);
			/*
			 * E vou decrementar a quantindade de movimentos dela.
			 */
			torre.diminuirContagemMovimentos();
		}
		
		/****************************************/
		// # Desfazendo Movimentação ESPECIAL Roque GRANDE
		/*
		 * Se a PECA p que foi movida
		 * for uma instancia de Rei, 
		 * e também a posição de destino
		 * na coluna, for igual a posição de origem
		 * na coluna + 2, significa que o meu REI
		 * andou duas casas para a direita
		 * fazendo o roque pequeno
		 */
		if(peca instanceof Rei && destino.getColuna() == origem.getColuna() - 2 ) {
			
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			
			PecaXadrez torre = (PecaXadrez)tabuleiro.removaPeca(destinoT);
			/*
			 * E coloca a peça no destino
			 */
			tabuleiro.colocarPeca(torre, origemT);
			/*
			 * E incremento a quantindade de movimentos dela.
			 */
			torre.diminuirContagemMovimentos();
		}
		
		
		// #MovimentoEspecial Peão Passante.
		/*
		 * Peão passante
		 * Vou testar se a peca que foi movida
		 * é uma instancia de peão
		 */
	
		if(peca instanceof Peao) {
			/*
			 * No lugar do nulo igual no fazendo movimento
			 * eu coloco o peãoPassante
			 * pois estou desfazendo
			 */
			if(origem.getColuna() != destino.getColuna() && pecaCapturada == peaoPassante) {
				/*
				 * No metodo desfazermovimento
				 * quando eu desfaço a peça iria voltar para uma casa antes
				 * Agora vou ter que implementar para ela voltar para a casa de origem.
				 * 
				 * Vou pegar a peça que foi devolvida que está no lugar erro
				 * pois ela está uma casa antes, e não duas, como era a posição de origem.
				 * e fazer manualmente ela voltar para lá.
				 * 
				 * Criar uma variavel peao e ela remove a peca do lugar errado
				 */
				PecaXadrez peao = (PecaXadrez)tabuleiro.removaPeca(destino);
				Posicao posicaoPeao;
				if(peca.getColor() == Color.WHITE) {
					/*
					 * Se foi a branca que foi capturada
					 * e estou desfazendo o movimento, pois deixou em cheque
					 * vou voltar essa peca para a linha onde ela deveria está
					 * que é na 3
					 * 
					 * E na peça preta é na 4.
					 */
					posicaoPeao = new Posicao(3, destino.getColuna());
					
				}else {
					posicaoPeao = new Posicao(4, destino.getColuna());
				}
				/*
				 * Vou pegar esse peão que tava no lugar erro
				 * e atribuir para o posicaoPeao.
				 */
				tabuleiro.colocarPeca(peao, posicaoPeao);
				/*
				 * E eu não preciso realizar a troca de listas
				 * igual eu fiz no fazerMovimento.
				 * pois já foi feita no primeiro if que a peca foi capturada
				 * O MEU ALGORITMO GENÉRICO JÁ TROCOU DE LISTAS
				 * A MINHA PEÇA CAPTURADA.
				 */
				
			}
			/*************************************/
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
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Color.WHITE));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Color.WHITE));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Color.WHITE));
		/*
		 * Como o peão agora tem a parte do peão passante
		 * O construtor dele aumentou
		 */
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Color.WHITE, this));
		

		colocarNovaPeca('a', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Color.BLACK));
		/*No construtor do rei são 3 parametros por que tem o roque
		 * Abaixo vou ter que colocar o this, referenciando a partida
		 * Que é essa partida, então eu coloco só o THIS
		 * Que no caso é a AUTOREFERENCIA chamada de THIS */
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Color.BLACK));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Color.BLACK));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Color.BLACK, this));
	}
	
	
}
