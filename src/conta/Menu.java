package conta;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import conta.util.Cores;
import conta.Controller.ContaController;
import conta.Model.Conta;
import conta.Model.ContaCorrente;
import conta.Model.ContaPoupanca;


public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		
		/*
		// Teste da Classe Conta
		Conta c1 = new Conta(1, 123, 1, "Adriana", 10000.0f);
		c1.visualizar();
		c1.sacar(12000.0f);
		c1.visualizar();
		c1.depositar(5000.0f);
		c1.visualizar();
		
		
		//Teste da classe conta corrente
		ContaCorrente cc1 = new ContaCorrente(1, 123, 1, "José da Silva", 0.0f, 1000.0f);
		cc1.visualizar();
		cc1.sacar(12000.0f);
		cc1.visualizar();
		cc1.depositar(5000.0f);
		cc1.visualizar();
		
		//teste da classe conta poupança
		ContaPoupanca cp1 = new ContaPoupanca(2, 123, 2, "Maria dos Santos", 100000.0f, 15);
		cp1.visualizar();
		cp1.depositar(5000.0f);
		cp1.visualizar();
		*/
		
		
		Scanner leia = new Scanner(System.in);
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		do{
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextInt();
				opcao = 0;
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta\n\n");
				System.out.println("\nDigite o número da agência: ");
				agencia = leia.nextInt();
				System.out.println("\nDigite o nome do titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				do {
					System.out.println("Digite o tipo de conta (1 - CC ou 2 - CP): ");
					tipo = leia.nextInt();
				}while(tipo < 1 && tipo > 2);
				System.out.println("Digite o saldo da conta (R$): ");
				saldo = leia.nextFloat();
				switch(tipo) {
					case 1 -> {
						System.out.println("Digite o limite de crédito(R$): ");
						limite = leia.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do aniversario da conta: ");
						aniversario = leia.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}				
				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta - por número\n\n");
				System.out.println("\nDigite o número da conta: ");
				numero = leia.nextInt();
				contas.procurarPorNumero(numero);
				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta\n\n");
				System.out.println("\nDigite o número da conta: ");
				numero = leia.nextInt();
				if(contas.buscarNaCollection(numero) != null) {
					System.out.println("\nDigite o número da agência: ");
					agencia = leia.nextInt();
					System.out.println("\nDigite o nome do titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();
					System.out.println("\nDigite o saldo da conta (R$): ");
					saldo = leia.nextFloat();
					tipo = contas.retornaTipo(numero);
					switch(tipo) {
					case 1 -> {
						System.out.println("\nDigite o limite de crédito (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("\nDigite o dia de aniversário da conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("\nTipo de conta inválido!");
					}
					}
				}else {
					System.out.println("\nConta não encontrada!");
				}
				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta\n\n");
				System.out.println("\nDigite o número da conta: ");
				numero = leia.nextInt();
				contas.deletar(numero);
				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");
				System.out.println("\nDigite o número da conta: ");
				numero = leia.nextInt();
				do {
					System.out.println("\nDigite o valor do saque (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				contas.sacar(numero, valor);
				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");
				System.out.println("\nDigite o número da conta: ");
				numero = leia.nextInt();
				do {
					System.out.println("\nDigite o valor do depósito (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				contas.depositar(numero, valor);
				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas\n\n");
				System.out.println("\nDigite o número da conta de origem");
				numero = leia.nextInt();
				System.out.println("\nDigite o número de conta de destino");
				numeroDestino = leia.nextInt();
				do {
					System.out.println("\nDigite o valor da transferência (R$): ");
					valor = leia.nextShort();
				}while(valor <= 0);
				contas.transferir(numero, numeroDestino, valor);
				keyPress();
				break;
			case 9:
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.TEXT_WHITE_BOLD + 
						"\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				leia.close();
				System.exit(0);
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n");
				keyPress();
				break;
			}
		}while (true);
	}
	
	public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar: ");
			System.in.read();
		}catch(IOException e) {
			System.out.println("Você pressionou uma tecla diferente de Enter!");
		}
	}
}